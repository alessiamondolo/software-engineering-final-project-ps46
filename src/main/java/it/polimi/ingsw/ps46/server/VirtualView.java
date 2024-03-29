package it.polimi.ingsw.ps46.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.ExtraMoveEffect;
import it.polimi.ingsw.ps46.server.card.VentureCard;


/**
 * This class implements the virtual view that will be the communication point between the server and the client.<br>
 * It receives notifications from the model, and sends to the clients the proper message requests.
 * When there is a request for an input from the client, it also takes care of notifying the observers of the input received.
 * 
 * @author Alessia Mondolo
 * @version 1.1
 */
public class VirtualView extends Observable implements Observer, EventVisitor {

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
			case SETUP_BONUS_TILES :
				getBonusTile(game.getCurrentPlayer().getIdPlayer());
				break;
			case GET_PLAYER_ACTION : 
				printCurrentPlayer();
				printPlayerStatus();
				getPlayerAction();
				break;
			case ACTION_NOT_VALID :
				getPlayerAction();
				break;
			case COUNCIL_PRIVILEGE : 
				getCouncilPrivilege();
				break;
			case VATICAN_REPORT :
				getVaticanSupport();
				break;
			case ACTIVATION_LEADER_CARDS :
				getActivationLeaderCard();
				break;
			case DISCARD_LEADER_CARDS :
				getDiscardLeaderCard();
				break;
			case MISSING_TURN :
				printMissingTurn();
				break;
			case EXTRA_MOVE :
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
		case UPDATE_PHASE_INFO :
			printBoard();
			break;
		case SET_NEXT_TURN_ORDER :
			showNextTurnOrder();
			break;	
		case UPDATE_FINAL_SCORES :
			showFinalScores();
			break;
		case END_GAME :
			endGame();
		default:
			break;
		}
	}



	public void visit(EventEffectChoice eventEffectChoice) {
		switch(eventEffectChoice.getMessage()) {
		case EXCHANGE_RESOURCES_CHOICE :
			getEffectChoice(eventEffectChoice.getCard());
			break;
		default:
			break;
		}
	}
	
	
	
	public void visit(EventCostChoice eventCostChoice) {
		switch(eventCostChoice.getMessage()) {
		case CARD_COST_CHOICE :
			getCostChoice(eventCostChoice.getCard());
			break;
		default:
			break;
		}
	}



	public void visit(EventExtraMove eventExtraMove) {
		switch(eventExtraMove.getMessage()) {
		case EXTRA_MOVE :
			getExtraMove(eventExtraMove.getExtraMoveEffect());
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
			writer.reset();
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
	 * Sends to the client with the ID received as parameter a message request to get the id of the
	 * bonus tile that the client wants to use during the game.<br>
	 * Then, the method notifies the observers with the input received by the client.
	 * 
	 * @param id : the ID of the client from which we want to receive the username.
	 */
	public void getBonusTile(int id) {
		int bonusTile = 0;
		int clientPosition = id - 1;
		Socket currentSocket = clients.get(clientPosition);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_BONUS_TILE");
			writer.flush();
			writer.writeObject(game);
			writer.flush();
			writer.reset();
			try {
				bonusTile = (int) reader.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setChanged();
		notifyObservers(new EventIntInput(bonusTile, InputType.BONUS_TILE_CHOICE));
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
	 * Sends to the all the clients a message request to print the current player.<br>
	 * After the message request, it sends a serialized version of the current game object.
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
	 * Sends to the current client a message request to print his status.
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
	 * Sends to the client with the ID of the current player a message request to get the action 
	 * that the client wants to perform during his current turn.<br>
	 * If the model is in the game state ACTION_NOT_VALID, it will also send a message request to show
	 * the message that the previous action wasn't valid.<br>
	 * After the message request for the client's action, the method notifies the observers with the inputs 
	 * received by the client:
	 * <ul>
	 * <li>The ID of the action space where the client wants to move;</li>
	 * <li>The family member that he wants to use for the action;</li>
	 * <li>The number of servants that he wants to add to the family member for the acion.</li>
	 * </ul>
	 * Finally, the method notifies the observers with a message saying that the action has been sent.
	 */
	public void getPlayerAction() {
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			if(game.getGameState().equals(GameState.ACTION_NOT_VALID)) {
				writer.writeObject("PREVIOUS_ACTION_NOT_VALID");
				writer.flush();
			}
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
	 * Sends to the client with the ID of the current player a message request to get which of the optional costs 
	 * the client wants to activate for the card received as a parameter.<br>
	 * Then, the method notifies the observers with the input received by the client.
	 * 
	 * @param card : the card for which we want to know the optional cost chosen by the client
	 */
	public void getCostChoice(VentureCard card) {
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_COST_CHOICE");
			writer.flush();
			writer.writeObject(card.getCost());
			writer.flush();
			writer.writeObject(card.getCostTwo());
			writer.flush();
			writer.reset();
			int choice = (int) reader.readObject();
			setChanged();
			EventCostChoice event = new EventCostChoice(NewStateMessage.CARD_COST_CHOICE, card);
			event.setChoice(choice);
			notifyObservers(event);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Sends to the client with the ID of the current player a message request to get which of the optional effect 
	 * the client wants to activate for the card received as a parameter.<br>
	 * Then, the method notifies the observers with the input received by the client.
	 * 
	 * @param card : the card for which we want to know the optional cost chosen by the client
	 */
	public void getEffectChoice(BuildingCard card) {
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_EFFECT_CHOICE");
			writer.flush();
			writer.writeObject(card.getPermanentEffects());
			writer.flush();
			writer.writeObject(card.getPermanentEffectsTwo());
			writer.flush();
			writer.reset();
			int choice = (int) reader.readObject();
			setChanged();
			EventEffectChoice event = new EventEffectChoice(NewStateMessage.EXCHANGE_RESOURCES_CHOICE, card);
			event.setChoice(choice);
			notifyObservers(event);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Sends to the client with the ID of the current player a message request to get which of the optional bonuses 
	 * the client wants to activate for the council privileges that he has available.<br>
	 * After receiving an array of integers representing the indexes of the bonuses chosen by the client, the method 
	 * notifies the observers with the inputs received by the client, one by one.
	 */
	private void getCouncilPrivilege() {
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_COUNCIL_PRIVILEGE");
			writer.flush();
			writer.writeObject(game);
			writer.flush();
			writer.reset();
			while(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("CouncilPrivilege").getQuantity() > 0) {
				int choice = (int) reader.readObject();
				setChanged();
				notifyObservers(new EventIntInput(choice, InputType.COUNCIL_PRIVILEGE_CHOICE));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Sends to the all the clients a message request to print the game order for the next turn.<br>
	 * After the message request, it sends a serialized version of the current game object.
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
	


	/**
	 * Sends to all the clients a message request to get if they want or not to support the Church.<br>
	 * Then, the method notifies the observers with the input received by the client.
	 * 
	 * @param card : the card for which we want to know the optional cost chosen by the client
	 */
	private void getVaticanSupport() {
		int vaticanSupport = 0;
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			ObjectInputStream reader = readers.get(currentSocket);
			try {
				writer.writeObject("GET_VATICAN_SUPPORT");
				writer.flush();
				writer.reset();
				try {
					vaticanSupport = (int) reader.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			setChanged();
			notifyObservers(new EventIntInput(vaticanSupport, InputType.VATICAN_SUPPORT_CHOICE));
		}
	}	
	
	
	
	/**
	 * Sends to the client with the ID of the current player a message request to get which of his activable leader cards 
	 * the client wants to activate for the current turn.<br>
	 * Then, the method notifies the observers with the inputs received by the client, one by one.
	 */
	private void getActivationLeaderCard(){
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_ACTIVATION_LEADER_CARDS");
			writer.flush();
			writer.writeObject(game);
			writer.flush();
			writer.reset();
			//finchè ci sono carte leader attivabili e non già attivate)
			while(game.checkIfHasLeaderCardsActivable()) {
				int choice = (int) reader.readObject();
				setChanged();
				if (choice != 0)
					notifyObservers(new EventIntInput(choice, InputType.ACTIVATION_LEADER_CARDS_CHOICE));
				else
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Sends to the client with the ID of the current player a message request to get which of his leader cards 
	 * the client wants to discard in exchange for a council privilege.<br>
	 * Then, the method notifies the observers with the inputs received by the client, one by one.
	 */
	private void getDiscardLeaderCard(){ 
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_DISCARD_LEADER_CARDS");
			writer.flush();
			writer.writeObject(game);
			writer.flush();
			writer.reset();
			//finchè ci sono carte leader ancora non attive e quindi scartabili)
			while(game.checkIfCouldDiscardLeaderCards()) {
				int choice = (int) reader.readObject();
				setChanged();
				if (choice != 0)
					notifyObservers(new EventIntInput(choice, InputType.DISCARD_LEADER_CARDS_CHOICE));
				else
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Sends to the all the clients a message request to print the message that he lost his first turn.<br>
	 * After the message request, it sends a serialized version of the current game object.
	 */
	private void printMissingTurn() {
		for(Socket currentSocket : writers.keySet()) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("SHOW_MISSING_TURN");
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
	 * Sends to the client with the ID of the current player a message request to get the extra move 
	 * that the client wants to perform during his current turn.<br>
	 * After the message request, it sends a serialized version of the current game object and the effect for which
	 * the client is doing this extra move.<br>
	 * After this, the method notifies the observers with the inputs received by the client:
	 * <ul>
	 * <li>The ID of the action space where the client wants to move;</li>
	 * <li>The number of servants that he wants to add to the family member for the action.</li>
	 * </ul>
	 * Finally, the method notifies the observers with an event containing the extra move effect for which
	 * the client is doing this extra move.
	 */
	private void getExtraMove(ExtraMoveEffect extraMoveEffect) {
		Socket currentSocket = clients.get((game.getCurrentPlayer().getIdPlayer())-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_EXTRA_MOVE");
			writer.flush();
			writer.writeObject(game);
			writer.flush();
			writer.writeObject(extraMoveEffect);
			writer.flush();
			writer.reset();
			try {					
				int actionSpaceID = (int) reader.readObject();
				setChanged();
				notifyObservers(new EventIntInput(actionSpaceID, InputType.PLAYER_ACTION));
				int servants = (int) reader.readObject();
				setChanged();
				notifyObservers(new EventIntInput(servants, InputType.SERVANTS_USED));
				setChanged();
				notifyObservers(new EventExtraMove(NewStateMessage.EXTRA_MOVE, extraMoveEffect));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Sends to the all the clients a message request to print the final scores.<br>
	 * After the message request, it sends a serialized version of the current game object.
	 */
	public void showFinalScores() {
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("SHOW_FINAL_SCORES");
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
	 * Sends to the all the clients a message request to end the game.
	 */
	public void endGame() {
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("END_GAME");
				writer.flush();
				writer.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
