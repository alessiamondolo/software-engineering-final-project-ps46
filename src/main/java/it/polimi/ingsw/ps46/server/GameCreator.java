package it.polimi.ingsw.ps46.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class GameCreator implements Runnable {

	private ArrayList<Socket> clients;
	private HashMap<Socket, ObjectOutputStream> writers;
	private HashMap<Socket, ObjectInputStream> readers;
	
	private Game game;	
	private VirtualView view;
	private GameController controller;
	
	public GameCreator(ArrayList<Socket> clients, HashMap<Socket, ObjectOutputStream> writers, HashMap<Socket, ObjectInputStream> readers) {
		this.clients = clients;
		this.writers = writers;
		this.readers = readers;
	}
	
	@Override
	public void run() {

		game = new Game(clients.size());
		view = new VirtualView(clients, writers, readers, game);
		controller = new GameController(game);
		
		view.addObserver(controller);
		game.addObserver(view);
		
		controller.run();

	}

}
