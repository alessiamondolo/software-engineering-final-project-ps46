package it.polimi.ingsw.ps46.client.GUI;

import java.util.Observable;

import javax.swing.JFrame;

import it.polimi.ingsw.ps46.server.EventAcceptor;
import it.polimi.ingsw.ps46.server.EventMessage;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.View;

/**
 * A WindowClass acts as the entry point for the game User Interface by launching the 
 * @MainWindowCLass.
 * 
 * @author lorenzo
 *
 */

public class GUIView extends View {  
	
	private Game game;
	private MainWindow mainWindow;
	
	public static void main(String[] args) {
		new GUIView(null);
	}
	
	public GUIView (Game game) {
		this.game = game;
		
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	/**
	 *  Cascade creation of the game GUI, this method displays the main JFrame 
	 *  (MainWindow) which will contain the Board and the Users Panels.
	 */
	
	private void createAndShowGUI() {
		// Create and set up the window.
		mainWindow = new MainWindow();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();
		mainWindow.setVisible(true);
	}

	@Override
	public void update(Observable obs, Object obj) {
		((EventAcceptor) obj).accept(this);
		
		mainWindow.update(obs, obj);
	}
	
	@Override
	public void visit(EventMessage eventMessage) {
		// TODO Auto-generated method stub
		
	}
	
}
