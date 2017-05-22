package it.polimi.ingsw.ps46.client;

import java.util.Scanner;

public class Client {

	private String serverIP;
	private Integer serverPort;
	private String connectionType;
	private String userInterfaceType;
	

	public Client(String ip, int port, String connectionType, String userInterfaceType) {
		serverIP = ip;
		serverPort = port;
		this.connectionType = connectionType;
		this.userInterfaceType = userInterfaceType;
		
	}
	
	public static void main(String[] args) {
		
		//We assume that the user already knows the IP and port of the server, that will be passed as arguments for the main
		if (args.length != 2){
			throw new IllegalArgumentException("You need to input the IP address and Port number of the server.");
		}
		
		//First, we ask the user which type of connection he prefers
		System.out.println("Do you want to connect to the server with Sockets or RMI?");
		Scanner in = new Scanner(System.in);
		String connectionType = in.nextLine();
		while(!connectionType.equals("Sockets") || !connectionType.equals("RMI")) {
			System.err.println("The answer is not valid, please try again.");
			connectionType = in.nextLine();
		}
		
		//Second, we ask the user which type of User Interface he wants
		System.out.println("Do you want to play the game with the GUI or through the CLI?");
		String userInterfaceType = in.nextLine();
		while(!userInterfaceType.equals("GUI") || !userInterfaceType.equals("CLI")) {
			System.err.println("The answer is not valid, please try again.");
			connectionType = in.nextLine();
		}
        in.close();
        
		Client client = new Client(args[0], Integer.parseInt(args[1]), connectionType, userInterfaceType);
		
		client.init();

	}
	
	private void init() {
		
	}

}
