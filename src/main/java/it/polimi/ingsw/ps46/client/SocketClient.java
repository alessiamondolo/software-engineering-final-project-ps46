package it.polimi.ingsw.ps46.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.Effect;
import it.polimi.ingsw.ps46.server.card.ExtraMoveEffect;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * This class implements the client that communicates with the server with Sockets.
 * It handles the network communication, receiving the messages from the server and
 * sending back the messages required from the server.
 * 
 * @author Alessia Mondolo
 * @version 1.1
 */
public class SocketClient implements Runnable {
	
	private int clientID;
	
	private String serverIP;
	private int serverPort;
	
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	
	private View view;
	
	boolean listening = true;

	
	/**
	 * Creates a new SocketClient object.
	 * 
	 * @param serverIP	 : the IP address of the server to which the client will connect.
	 * @param serverPort : the port of the server to which the client will connect.
	 * @param view		 : the User Interface that will be used to interact with the client.
	 */
	public SocketClient(String serverIP, int serverPort, View view) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.view = view;
	}
	
	
	
	/**
	 * Creates a new Socket connection with the server and reads the messages sent by the server.
	 * When a message is received, it calls the interpreter method to interpret the message received.
	 * When the message "END_GAME" is received, the connection with the server is closed.
	 */
	@Override
	public void run() {
		try {
			Socket socket = new Socket(serverIP, serverPort);
			
			InputStream input = socket.getInputStream();
			reader = new ObjectInputStream(input);
			
			OutputStream output = socket.getOutputStream();
			writer = new ObjectOutputStream(output);

			String message;
			try {
				while(listening) {
					message = (String) reader.readObject();
					interpreter(message);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			view.printMessage("The game is over, thanks for playing with us!");
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Sets the ID of the client.
	 * 
	 * @param id : the ID that has to be assigned to the client.
	 */
	private void setClientID(int id) {
		clientID = id;
	}
	
	
	
	/**
	 * Interprets the message received as parameter, calling the proper method from the view.
	 * In case the message requires some input from the client, the interpreter also takes care
	 * of sending the required input back to the server.
	 * 
	 * @param message : the message that needs to be interpreted.
	 */
	public void interpreter(String message) {
		switch(message) {
		case "STORE_YOUR_ID" :
			try {
				setClientID((int) reader.readObject());
				view.printMessage("Your ID is " + clientID + ".");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "WELCOME_MESSAGE" : {
			view.welcomeMessage();
			break;
		}
		case "GET_PLAYER_USERNAME" :
			try {
				writer.writeObject(view.getPlayerUserame());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "SHOW_INITIAL_GAME_ORDER" :
			try {
				view.setGame((Game) reader.readObject());
				view.showInitialOrder();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "GET_PLAYER_COLOR" :
			try {
				@SuppressWarnings("unchecked")
				ArrayList<String> colors = (ArrayList<String>) reader.readObject();
				writer.writeObject(view.getPlayerColor(colors));
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "GET_BONUS_TILE" :
			try {
				view.setGame((Game) reader.readObject());
				writer.writeObject((int) view.getBonusTile());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "SHOW_ROUND_INFO" :
			try {
				view.setGame((Game) reader.readObject());
				view.updateRoundInfo();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "SHOW_BOARD" :
			try {
				view.setGame((Game) reader.readObject());
				view.printBoard();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "SHOW_CURRENT_PLAYER" :
			try {
				Game game = (Game) reader.readObject();
				view.setGame(game);
				if(game.getCurrentPlayer().getIdPlayer() != clientID)
					view.printCurrentPlayer();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "SHOW_PLAYER_STATUS" :
			view.printPlayerStatus();
			break;
		case "GET_PLAYER_ACTION" :
			try {
				writer.writeObject(view.getPlayerAction());
				writer.flush();
				writer.writeObject(view.getFamilyMember());
				writer.flush();
				writer.writeObject(view.getServants());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "PREVIOUS_ACTION_NOT_VALID" :
			view.printMessage("The action you chose is not valid, please try again.\n");
			break;
		case "GET_COST_CHOICE" : 
			try {
				ResourceSet cost1 = (ResourceSet) reader.readObject();
				ResourceSet cost2 = (ResourceSet) reader.readObject();
				writer.writeObject(view.getCostCoice(cost1, cost2));
				writer.flush();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case "GET_EFFECT_CHOICE" : 
			try {
				Effect effect1 = (Effect) reader.readObject();
				Effect effect2 = (Effect) reader.readObject();
				writer.writeObject(view.getEffectCoice(effect1, effect2));
				writer.flush();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case "GET_COUNCIL_PRIVILEGE" :
			try {
				view.setGame((Game) reader.readObject());
				for(Integer privilege : view.getCouncilPrivilege()) {
					writer.writeObject(privilege.intValue());
					writer.flush();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "SHOW_NEXT_TURN_ORDER" :
			try {
				view.setGame((Game) reader.readObject());
				view.showNextTurnOrder();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			break;
		case "GET_VATICAN_SUPPORT" :
			try {
				writer.writeObject((int) view.getVaticanSupport());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "GET_ACTIVATION_LEADER_CARDS" :
			try {
				view.setGame((Game) reader.readObject()); // riceve il game e lo passa alla view
				for(Integer leaderCard : view.getActivationLeaderCards()) {
					writer.writeObject(leaderCard.intValue());
					writer.flush();
				} 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "SHOW_MISSING_TURN" :
			try {
				Game game = (Game) reader.readObject();
				view.setGame(game);
				if(game.getCurrentPlayer().getIdPlayer() != clientID)
					view.printMessage("Player " + game.getCurrentPlayer().getUsername() + " will play his first turn at the end of the round.");
				else
					view.printMessage("You will play your first turn at the end of the round.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "GET_EXTRA_MOVE" :
			try {
				view.printMessage("You've got an extra move!");
				view.setGame((Game) reader.readObject());
				int idActionSpace = view.getExtraMove((ExtraMoveEffect) reader.readObject());
				writer.writeObject(idActionSpace);
				writer.flush();
				writer.writeObject(view.getServants());
				writer.flush();
				writer.reset();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			break;	
		case "GET_DISCARD_LEADER_CARDS" : 
			try {
				view.setGame((Game) reader.readObject());
				for(Integer leaderCard : view.getDiscardLeaderCards()) {
					writer.writeObject(leaderCard.intValue());
					writer.flush();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			break;	
		case "SHOW_FINAL_SCORES" :
			try {
				view.setGame((Game) reader.readObject());
				view.showFinalScores();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			break;
		case "END_GAME" :
			listening = false;
			break;
		default :
			view.printMessage(message);
			break;
		}
	}
}