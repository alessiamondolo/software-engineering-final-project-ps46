package it.polimi.ingsw.ps46.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
public class SocketManager implements Runnable {

	private final static int PORT = 1025;
	private ServerSocket listener;
	private PrintStream output = System.out;
	
	private WaitingList waitingList;
	
	public SocketManager() {
		waitingList = new WaitingList();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			listener = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output.println("Socket server is running...");
		
		while(true) {
			try {
				Socket socket = listener.accept();
				waitingList.add(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
