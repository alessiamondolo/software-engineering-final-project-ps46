package it.polimi.ingsw.ps46.client;

import java.io.PrintStream;

import it.polimi.ingsw.ps46.utils.ReadInput;

public class Client {

	private static PrintStream printStream = System.out;
	private static ReadInput readInput = new ReadInput();
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 1025;
	private static String connectionType;
	private static String userInterfaceType;
	

	public static void main(String[] args) {
		
		printStream.println("How do you want to connect to the server?");
		printStream.println("1. Sockets");
		printStream.println("2. RMI");
		int networkTechnology = readInput.IntegerFromConsole(1, 2);
		switch(networkTechnology) {
		case 1:
			connectionType = "Sockets";
			break;
		case 2 :
			connectionType = "RMI";
			break;
		}
		
		
		printStream.println("Do you want to play with the CLI or with the GUI?");
		printStream.println("1. CLI");
		printStream.println("2. GUI");
		int UI = readInput.IntegerFromConsole(1, 2);
		switch(UI) {
		case 1:
			userInterfaceType = "CLI";
			break;
		case 2 :
			userInterfaceType = "GUI";
			break;
		}
        
		Client client = new Client();
		client.init();

	}
	
	private void init() {
		
		View view = null;
		
		switch(userInterfaceType) {
		case "CLI" :
			view = new ConsoleView(System.out);
			break;
		case "GUI" :
			//TODO sostituire con costruttore della GUI
			view = new ConsoleView(System.out);
			break;
		}
		
		switch(connectionType) {
		case "Sockets" :
			SocketClient socketClient = new SocketClient(SERVER_IP, SERVER_PORT, view);
			Thread clientThread = new Thread(socketClient);
			clientThread.start();
			break;
		}
	}

}
