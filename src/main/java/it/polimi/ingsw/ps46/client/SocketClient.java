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

/**
 * This class implements the client that communicates with the server with Sockets.
 * It handles the network communication, receiving the messages from the server and
 * sending back the messages required from the server.
 * 
 * @author Alessia Mondolo
 * @version 1.1
 */
public class SocketClient implements Runnable {
	
	private String serverIP;
	private int serverPort;
	
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	
	private View view;

	
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
				message = (String) reader.readObject();
				view.printMessage(message);
				while(!message.equals("END_GAME")) {
					message = (String) reader.readObject();
					interpreter(message);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			view.printMessage("The game is over, thanks for playing with us!");
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			view.printMessage(message);
			break;
		}
	}
	
}
