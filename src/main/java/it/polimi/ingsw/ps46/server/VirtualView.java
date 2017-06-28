package it.polimi.ingsw.ps46.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;


/**
 * This class implements the virtual view that will be the communication point between the server and the client.<br>
 * It receives notifications from the model, and sends to the clients the proper message requests.
 * When there is a request for an input from the client, it also takes care of notifying the observers of the input received.
 * 
 * @author Alessia Mondolo
 * @version 1.1
 */
public class VirtualView extends View {

	private ArrayList<Socket> clients;
	private HashMap<Socket, ObjectOutputStream> writers;
	private HashMap<Socket, ObjectInputStream> readers;
	
	private Game game;
	
	private List<String> colors = new ArrayList<String>();
	
	
	/**
	 * Creates a new VirtualView object.
	 * 
	 * @param clients : the list of clients that will play the game.
	 * @param writers : the object output streams of the clients.
	 * @param readers : the object input streams of the clients.
	 * @param game	  : the reference of the game model.
	 */
	public VirtualView(ArrayList<Socket> clients, HashMap<Socket, ObjectOutputStream> writers,
			HashMap<Socket, ObjectInputStream> readers, Game game) {
		this.clients = clients;
		this.writers = writers;
		this.readers = readers;
		this.game = game;
		
		colors.add("Red");
		colors.add("Yellow");
		colors.add("Blue");
		colors.add("Green");
	}

	
	
	/**
	 * When the model sends notifications, this method visits the event using the visitor pattern.
	 * Based on the different type of events, the actions might be different.
	 */
	public void update(Observable obs, Object obj) {
		((EventAcceptor) obj).accept(this);
	}

	
	
	/**
	 * Depending on the content of the event message received by the model, it calls the right method.
	 * 
	 * @param eventMessage : the EventMessage object that contains the notification message from the model.
	 */
	public void visit(EventMessage eventMessage) {
		switch(eventMessage.getMessage()) {
		case START_GAME :
			welcomeMessage();
			getGameMode();
			break;
		case CHANGED_CURRENT_PLAYER :
			GameState gameState = game.getGameState();
			switch(gameState) {
			case SETUP_PLAYERS_USERNAME :
				getPlayerUsername(game.getCurrentPlayer().getIdPlayer());
				break;
			case SETUP_PLAYERS_COLOR :
				getPlayerColor(game.getCurrentPlayer().getIdPlayer());
				break;
			case GET_PLAYER_ACTION : 
				printCurrentPlayer();
				printPlayerStatus();
				getPlayerAction();
				break;
			case ACTION_NOT_VALID :
				getPlayerAction();
				break;
			default:
				break;
			}
			break;
		case SET_INITIAL_ORDER :
			showInitialOrder();
			break;
		case UPDATE_ROUND_INFO : 
			updateRoundInfo();
			break;
		case THROWN_DICE :
			printBoard();
			break;
		case SET_NEXT_TURN_ORDER :
			showNextTurnOrder();
			break;	
		default:
			break;
		}
	}

	
	
	/**
	 * Sends to the all the clients a message request to print the welcome message for the game.
	 */
	public void welcomeMessage() {
		for(Socket currentSocket : writers.keySet()) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("WELCOME_MESSAGE");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	/**
	 * Sends to the all the clients a message request to get the game mode that the client wants for the game.
	 * If the game mode chosen by the client is the advanced mode, it notifies the observers of this.<br>
	 * The notify is sent only when the game mode chosen is advanced because by default the game mode is the basic mode.
	 * For this, we need to change the game mode in the model only if it has to be set as advanced.
	 */
	private void getGameMode() {
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			ObjectInputStream reader = readers.get(currentSocket);
			try {
				writer.writeObject("GET_GAME_MODE");
				writer.flush();
				try {
					String gameMode = (String) reader.readObject();
					if(gameMode == "ADVANCED_GAME_MODE") {
						setChanged();
						notifyObservers(new EventMessage(NewStateMessage.ADVANCED_GAME_MODE));
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	/**
	 * Sends to the client with the ID received as parameter a message request to get the username
	 * that the client wants to use during the game.<br>
	 * Then, the method notifies the observers with the input received by the client.
	 * 
	 * @param id : the ID of the client from which we want to receive the username.
	 */
	public void getPlayerUsername(int id) {
		String username = null;
		Socket currentSocket = clients.get(id-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_PLAYER_USERNAME");
			writer.flush();
			try {
				username = (String) reader.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setChanged();
		notifyObservers(new EventStringInput(username, InputType.PLAYER_USERNAME));
	}
	
	
	
	/**
	 * Sends to the all the clients a message request to print the initial order of the game.<br>
	 * After the message request, it sends a serialized version of the current game object.
	 */
	public void showInitialOrder() {
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("SHOW_INITIAL_GAME_ORDER");
				writer.flush();
				writer.writeObject(game);
				writer.flush();
				writer.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	/**
	 * Sends to the client with the ID received as parameter a message request to get the color
	 * that the client wants to use during the game.<br>
	 * After the message request, it sends the list of available colors.<br>
	 * The color chosen by the client is then removed from the list of available colors.<br>
	 * Finally, the method notifies the observers with the input received by the client.
	 * 
	 * @param id : the ID of the client from which we want to receive the color.
	 */
	public void getPlayerColor(int id) {
		String color = null;
		int clientPosition = id - 1;
		Socket currentSocket = clients.get(clientPosition);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_PLAYER_COLOR");
			writer.flush();
			writer.writeObject(colors);
			writer.flush();
			try {
				color = (String) reader.readObject();
				colors.remove(color);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setChanged();
		notifyObservers(new EventStringInput(color, InputType.PLAYER_COLOR));
	}
	
	
	
	/**
	 * Sends to the all the clients a message request to print the info for the current round.<br>
	 * After the message request, it sends a serialized version of the current game object.
	 */
	public void updateRoundInfo() {
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("SHOW_ROUND_INFO");
				writer.flush();
				writer.writeObject(game);
				writer.flush();
				writer.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * Sends to the all the clients a message request to print the board.<br>
	 * After the message request, it sends a serialized version of the current game object.
	 */
	public void printBoard() {
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("SHOW_BOARD");
				writer.flush();
				writer.writeObject(game);
				writer.flush();
				writer.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 
	 */
	public void printCurrentPlayer() {
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("SHOW_CURRENT_PLAYER");
				writer.flush();
				writer.writeObject(game);
				writer.flush();
				writer.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * Sends to the all the clients a message request to print the board.<br>
	 * After the message request, it sends a serialized version of the current game object.
	 */
	public void printPlayerStatus() {
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		try {
			writer.writeObject("SHOW_PLAYER_STATUS");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	
	/**
	 * 
	 */
	public void getPlayerAction() {
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			if(game.getGameState().equals(GameState.ACTION_NOT_VALID))
				writer.writeObject("PREVIOUS_ACTION_NOT_VALID");
			writer.writeObject("GET_PLAYER_ACTION");
			writer.flush();
			try {					
				int actionSpaceID = (int) reader.readObject();
				setChanged();
				notifyObservers(new EventIntInput(actionSpaceID, InputType.PLAYER_ACTION));
				String familyMember = (String) reader.readObject();
				setChanged();
				notifyObservers(new EventStringInput(familyMember, InputType.FAMILY_MEMBER_CHOICE));
				int servants = (int) reader.readObject();
				setChanged();
				notifyObservers(new EventIntInput(servants, InputType.SERVANTS_USED));
				setChanged();
				notifyObservers(new EventMessage(NewStateMessage.ACTION_SENT));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 */
	public void showNextTurnOrder() {
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("SHOW_NEXT_TURN_ORDER");
				writer.flush();
				writer.writeObject(game);
				writer.flush();
				writer.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}