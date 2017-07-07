package it.polimi.ingsw.ps46.server;


/**
 * Main class for the server.
 * It is possible to choose between playing a local game or starting the server for handling online games.
 * 
 * @author Alessia Mondolo
 */
public class ServerApplication {

	/**
	 * Description of the method main.
	 */
	public static void main(String[] args) {
		//TODO setup of RMI and Socket connection for online game
		SocketManager socketManager = new SocketManager();
		Thread socketThread = new Thread(socketManager);
		socketThread.start();
	}

}
