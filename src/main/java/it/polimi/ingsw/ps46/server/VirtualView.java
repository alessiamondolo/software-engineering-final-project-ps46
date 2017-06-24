package it.polimi.ingsw.ps46.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class VirtualView extends View {

	private ArrayList<Socket> clients;
	private HashMap<Socket, BufferedWriter> writers;
	private HashMap<Socket, BufferedReader> readers;
	
	
	public VirtualView(ArrayList<Socket> clients, HashMap<Socket, BufferedWriter> writers,
			HashMap<Socket, BufferedReader> readers) {
		this.clients = clients;
		this.writers = writers;
		this.readers = readers;
	}

	public void update(Observable obs, Object obj) {
		((EventAcceptor) obj).accept(this);
	}

	public void visit(EventMessage eventMessage) {
		switch(eventMessage.getMessage()) {
		case START_GAME :
			welcomeMessage();
			getGameMode();
			break;/*
		case CHANGED_CURRENT_PLAYER :
			GameState gameState = game.getGameState();
			switch(gameState) {
			case SETUP_PLAYERS_USERNAME :
				getPlayerUsername(game.getCurrentPlayer().getIdPlayer());
				break;
			case SETUP_PLAYERS_COLOR :
				getPlayerColor(game.getCurrentPlayer().getUsername());
				break;
			case GET_PLAYER_ACTION : 
				printPlayerStatus();
				String action = getPlayerAction().toString();
				setChanged();
				notifyObservers(new EventStringInput(action, InputType.PLAYER_ACTION));
				getFamilyMember();
				getServants();
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
			break;*/	
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
			BufferedWriter writer = writers.get(currentSocket);
			try {
				writer.write("WELCOME_MESSAGE\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	

	private void getGameMode() {
		
		for(Socket currentSocket : clients) {
			BufferedWriter writer = writers.get(currentSocket);
			BufferedReader reader = readers.get(currentSocket);
			try {
				writer.write("GET_GAME_MODE\n");
				writer.flush();
				String gameMode = reader.readLine();
				//inviare la stringa agli observer
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
