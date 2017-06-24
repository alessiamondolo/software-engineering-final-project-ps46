package it.polimi.ingsw.ps46.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class GameCreator implements Runnable {

	private ArrayList<Socket> clients;
	private HashMap<Socket, BufferedWriter> writers;
	private HashMap<Socket, BufferedReader> readers;
	
	private Game game;	
	private View view;
	private GameController controller;
	
	public GameCreator(ArrayList<Socket> clients, HashMap<Socket, BufferedWriter> writers, HashMap<Socket, BufferedReader> readers) {
		this.clients = clients;
		this.writers = writers;
		this.readers = readers;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		game = new Game(clients.size());
		view = new VirtualView(clients, writers, readers);
		controller = new GameController(game);
		
		view.addObserver(controller);
		game.addObserver(view);
		
		controller.run();

	}

}
