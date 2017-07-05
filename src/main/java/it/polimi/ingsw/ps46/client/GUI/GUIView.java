package it.polimi.ingsw.ps46.client.GUI;

import java.util.ArrayList;

import javax.swing.JFrame;

import it.polimi.ingsw.ps46.client.View;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.Effect;

/**
 * A WindowClass acts as the entry point for the game User Interface by launching the 
 * @MainWindowCLass.
 * 
 * @author lorenzo
 *
 */

public class GUIView implements View {
	
	private Game game;
	private MainWindow mainWindow;
	
	
	public static void main(String[] args) {
		new GUIView();
	}
	
/*	public GUIView (Game game) {
		
		
		//il costruttore della GUI view dovrebbe creare le classi principali senza visualizzare nulla
		
		this.game = game;  //serve salvarne una copia o basta propoagarla alle sottoclassi?
		
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}*/
	
	/**
	 *  Constructor that creates all the containers for the GUI screens. No interaction with
	 *  the model is needed to create the framework, only then, via Controller's request,
	 *  the containers wil be populated with model's data.
	 */
	
	public GUIView () {
		
		//poi qui rimarrà solo createandshowgui che sarà il metodo del costruttore per creare tutto il framework
		
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
	//problema di denominazione, i nomi della view non sono ideali per la semantica della GUI
	
	private void createAndShowGUI() {
		// Create and set up the window.
		mainWindow = new MainWindow();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();
		mainWindow.setVisible(true);
		mainWindow.repaint();
	}
	
	
	
	
	/**
	 *  Central method to refresh all the game's graphical representations
	 */
	@Override
	public void printBoard() {
		
		MainBoard mainBoard = mainWindow.getMainBoard();
		
		ArrayList<PlayerDashboard> list = mainWindow.getPlayerArea().getDashboards();
		for ( PlayerDashboard pd : list) {
			pd.update(this.game);
		}
		
		mainBoard.update(this.game);
		
	}
	
	
	//pensare quando va chiamato il metodo setPlayer delle dashboard che le associa ad un giocatore
	
	public void setGame(Game game) {
		this.game = game;
	}

	
	@Override
	public void welcomeMessage() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public String getGameMode() {
		// TODO Auto-generated method stub
		return null;
	}

	
/*	@Override
	public String getPlayerUserame(int id) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	
	@Override
	public void showInitialOrder() {
		// TODO Auto-generated method stub
		
	}

	
/*	@Override
	public String getPlayerColor(String username) {
		// TODO Auto-generated method stub
		return null;
	}*/

	
/*	@Override
	public ActionSpaceName getPlayerAction() {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	@Override
	public void printPlayerStatus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPlayerUserame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlayerColor(ArrayList<String> colors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRoundInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printCurrentPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPlayerAction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getFamilyMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServants() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printPlayerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showNextTurnOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getEffectCoice(Effect effect1, Effect effect2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
