package it.polimi.ingsw.ps46.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class WaitingList {
	
	private ArrayList<Socket> clients;
	private HashMap<Socket, BufferedWriter> writers;
	private HashMap<Socket, BufferedReader> readers;
	
	public WaitingList() {
		clients = new ArrayList<Socket>();
		writers = new HashMap<Socket, BufferedWriter>();
		readers = new HashMap<Socket, BufferedReader>();
	}
	
	public void add(Socket socket) {
		
		clients.add(socket);
		System.out.println("New client added to the waiting list.");
		
		try {
			
			OutputStream output = socket.getOutputStream();
			OutputStreamWriter outputWriter = new OutputStreamWriter(output);
			BufferedWriter writer = new BufferedWriter(outputWriter);
			writers.put(socket, writer);
			
			InputStream input = socket.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(inputReader);
			readers.put(socket, reader);
			
			writers.get(socket).write("You are now connected to the server.\n");
			writer.flush();
			
			if(clients.size() < 3) {
				writer.write("Waiting for other players to connect...\n");
				writer.flush();
				
			}
			
			else if(clients.size() == 3) {
				for(Socket currentSocket : writers.keySet()) {
					writer = writers.get(currentSocket);
					writer.write("Ready for the game!\n");
					writer.flush();
				}
				newGame();				
			}
			//writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void newGame() {
		GameCreator gameCreator = new GameCreator(clients, writers, readers);
		Thread game = new Thread(gameCreator);
		System.out.println("Starting a new game...");
		game.start();
	}
	
}
