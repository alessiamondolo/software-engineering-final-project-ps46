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
import it.polimi.ingsw.ps46.server.Player;

public class SocketClient implements Runnable {
	
	private String serverIP;
	private int serverPort;
	
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	
	private View view;
	
	public SocketClient(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		view = new ConsoleView(null, System.out);
	}
	
	@Override
	public void run() {
		try {
			Socket socket = new Socket(serverIP, serverPort);
			
			InputStream input = socket.getInputStream();
			reader = new ObjectInputStream(input);;
			
			OutputStream output = socket.getOutputStream();
			writer = new ObjectOutputStream(output);

			String message;
			try {
				message = (String) reader.readObject();
				System.out.println(message);
				while(!message.equals("END_GAME")) {
					message = (String) reader.readObject();
					interpreter(message);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("The game is over, thanks for playing with us!");
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void interpreter(String message) {
		
		switch(message) {
		case "WELCOME_MESSAGE" :
			view.welcomeMessage();
			break;
		case "GET_GAME_MODE" :
			try {
				writer.writeObject(view.getGameMode());
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "GET_PLAYER_USERNAME" :
			try {
				writer.writeObject(view.getPlayerUserame());
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "SHOW_INITIAL_GAME_ORDER" :
			try {
				Game game = (Game) reader.readObject();
				view.setGame(game);
				view.showInitialOrder();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "GET_PLAYER_COLOR" :
			try {
				@SuppressWarnings("unchecked")
				ArrayList<String> colors = (ArrayList<String>) reader.readObject();
				System.out.println(colors);
				writer.writeObject(view.getPlayerColor(colors));
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default :
			System.out.println(message);
			break;
		}
	}
	
}
