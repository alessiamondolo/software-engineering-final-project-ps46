package it.polimi.ingsw.ps46.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

public class VirtualView extends View {

	private ArrayList<Socket> clients;
	private HashMap<Socket, ObjectOutputStream> writers;
	private HashMap<Socket, ObjectInputStream> readers;
	
	private Game game;
	
	private List<String> colors = new ArrayList<String>();
	
	
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

	public void update(Observable obs, Object obj) {
		((EventAcceptor) obj).accept(this);
	}

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
				break;/*
			case GET_PLAYER_ACTION : 
				printPlayerStatus();
				String action = getPlayerAction().toString();
				setChanged();
				notifyObservers(new EventStringInput(action, InputType.PLAYER_ACTION));
				getFamilyMember();
				getServants();
				break;*/
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
			break;/*
		case SET_NEXT_TURN_ORDER :
			showNextTurnOrder();
			break;	*/
		default:
			break;
		}
	}

	@Override
	public void visit(EventMV eventMV) {
		// TODO Auto-generated method stub

	}

	public void welcomeMessage() {
		for(Socket currentSocket : writers.keySet()) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("WELCOME_MESSAGE");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	

	private void getGameMode() {
		
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			ObjectInputStream reader = readers.get(currentSocket);
			try {
				writer.writeObject("GET_GAME_MODE");
				writer.flush();
				try {
					String gameMode = (String) reader.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//inviare la stringa agli observer
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setChanged();
		notifyObservers(new EventStringInput(username, InputType.PLAYER_USERNAME));
		
	}
	
	
	
	public void showInitialOrder() {
		
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("SHOW_INITIAL_GAME_ORDER");
				writer.flush();
				writer.writeObject(game);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		setChanged();
		notifyObservers(new EventMessage(NewStateMessage.SET_INITIAL_ORDER));
	}

	
	
	public void getPlayerColor(int id) {
		
		String color = null;
		Socket currentSocket = clients.get(id-1);
		ObjectOutputStream writer = writers.get(currentSocket);
		ObjectInputStream reader = readers.get(currentSocket);
		try {
			writer.writeObject("GET_PLAYER_COLOR");
			writer.flush();
			writer.writeObject(color);
			writer.flush();
			try {
				color = (String) reader.readObject();
				colors.remove(color);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//inviare la stringa agli observer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setChanged();
		notifyObservers(new EventStringInput(color, InputType.PLAYER_COLOR));
		
	}
	
	
}
