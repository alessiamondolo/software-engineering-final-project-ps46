package it.polimi.ingsw.ps46.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class WaitingList {
	
	private static final int TIMEOUT = 6000;
	private Timer timer;
	
	private ArrayList<Socket> clients;
	private HashMap<Socket, ObjectOutputStream> writers;
	private HashMap<Socket, ObjectInputStream> readers;
	
	public WaitingList() {
		clients = new ArrayList<Socket>();
		writers = new HashMap<Socket, ObjectOutputStream>();
		readers = new HashMap<Socket, ObjectInputStream>();
	}
	
	public synchronized void add(Socket socket) {
		
		clients.add(socket);
		System.out.println("New client added to the waiting list.");
		
		try {
			
			OutputStream output = socket.getOutputStream();
			ObjectOutputStream writer = new ObjectOutputStream(output);
			writers.put(socket, writer);
			
			InputStream input = socket.getInputStream();
			ObjectInputStream reader = new ObjectInputStream(input);
			readers.put(socket, reader);
			
			writer.writeObject("You are now connected to the server.");
			writer.flush();
			
			writer.writeObject("STORE_YOUR_ID");
			writer.writeObject(clients.size());
			writer.flush();
			
			if(clients.size() == 1) {
				writer.writeObject("Waiting for other players to connect...");
				writer.flush();
			}
			
			if(clients.size() == 2 || clients.size() == 3) {
				writer.writeObject("Waiting for other players to connect...");
				writer.flush();
				timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						newGame();
					}
				}, TIMEOUT);
			}
			
			if(clients.size() == 4)
				newGame();
			//writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void newGame() {
		
		timer.cancel();
		
		for(Socket currentSocket : clients) {
			ObjectOutputStream writer = writers.get(currentSocket);
			try {
				writer.writeObject("Ready for the game!\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArrayList<Socket> clients = new ArrayList<Socket>(this.clients);
		HashMap<Socket, ObjectOutputStream> writers = new HashMap<Socket, ObjectOutputStream>(this.writers);
		HashMap<Socket, ObjectInputStream> readers = new HashMap<Socket, ObjectInputStream>(this.readers);
		
		this.clients.clear();
		this.writers.clear();
		this.readers.clear();
		
		GameCreator gameCreator = new GameCreator(clients, writers, readers);
		Thread game = new Thread(gameCreator);
		System.out.println("Starting a new game...");
		game.start();
	}
	
}
