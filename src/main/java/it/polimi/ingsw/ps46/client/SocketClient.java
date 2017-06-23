package it.polimi.ingsw.ps46.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient implements Runnable {
	
	private String serverIP;
	private int serverPort;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
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
			InputStreamReader inputReader = new InputStreamReader(input);
			reader = new BufferedReader(inputReader);
			
			OutputStream output = socket.getOutputStream();
			OutputStreamWriter outputWriter = new OutputStreamWriter(output);
			writer = new BufferedWriter(outputWriter);

			String message = reader.readLine();
			System.out.println(message);
			while(!message.equals("END_GAME")) {
				message = reader.readLine();
				interpreter(message);
			}
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
				writer.write(view.getGameMode() + "\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default :
			System.out.println(message);
			break;
		}
	}
	
}
