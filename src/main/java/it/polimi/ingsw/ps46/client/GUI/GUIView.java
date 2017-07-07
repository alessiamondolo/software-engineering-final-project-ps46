package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.polimi.ingsw.ps46.client.View;

import it.polimi.ingsw.ps46.server.EventMessage;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;

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
	private WelcomeWindow welcomeWindow;
	private GameWindow gameWindow;
	private boolean firstTime = true;
	
	// TODO Rimuovere, solo per debug
	private PrintStream output = System.out;
	
	private static Object monitor = new Object();
	protected static Object getMonitor() {
		return monitor;
	}
	
	
/*	public static void main(String[] args) {
		
		GUIView g = new GUIView(true);
		Game game = null;
		Player p = new Player(1);
		
		GameWindow gw = new GameWindow(game);
		gw.pack();
		gw.setVisible(true);
		
		int a;
		
		while ((a=g.getPlayerAction()) != 4) {
			System.out.println("Action: " + a);
		}
	}*/
	
	public GUIView () {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				boolean visible = false;
				createAndShowGUI(visible);
			}
		});
	}
	
	/**
	 *  Constructor that creates all the containers for the GUI screens. No interaction with
	 *  the model is needed to create the framework, only then, via Controller's request,
	 *  the containers wil be populated with model's data.
	 */
	
	public GUIView (boolean b) {   //costruttore per test
		
		//poi qui rimarrà solo createandshowgui che sarà il metodo del costruttore per creare tutto il framework
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				createAndShowGUI(b);
			}
		});
		
	}
	
	
	/**
	 *  Cascade creation of the game GUI, this method displays the main JFrame 
	 *  (MainWindow) which will contain the Board and the Users Panels.
	 */
	
	// Solo per debug
	private void createAndShowGUI(boolean visible) {
		// Create and set up the window.
		initUI(visible);
		return;
	}
	
	private void initUI(boolean visible) {
		
		//Create and set up the window.
		welcomeWindow = new WelcomeWindow();
		welcomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeWindow.pack();
		welcomeWindow.setVisible(visible); //
		welcomeWindow.repaint();
		return;
	}
	
	/**
	 *  Central method to refresh all the game's graphical representations
	 */
	
	@Override
	public void printBoard() {
		
		if (!(gameWindow instanceof GameWindow))
			return;
		
		gameWindow.repaint();
		gameWindow.setVisible(true);
		gameWindow.update(this.game);
		gameWindow.pack();
		gameWindow.setVisible(true);
		gameWindow.repaint();
		
	}
	
	
	//pensare quando va chiamato il metodo setPlayer delle dashboard che le associa ad un giocatore
	@Override
	public void setGame(Game game) {

		this.game = game;
		System.out.println("set game è stato chiamato");
		
		if (firstTime) {	
		
		gameWindow = new GameWindow(this.game);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstTime = false;
		
		}
		firstTime = false;
		
	}

	
	@Override
	public void welcomeMessage() {
		
		welcomeWindow.setVisible(false);
		System.out.println("sono welcome message");
		
	}
	
	
	private volatile static String gameMode;
	protected static void setGameMode(String mode) {
		GUIView.gameMode = mode;
	}
	public String getGameMode() {
	
		if (!(welcomeWindow instanceof WelcomeWindow))
			return "";
		welcomeWindow.setGameMode();
		System.out.println("ho appena invocato la gamemode");
		welcomeWindow.pack();
		welcomeWindow.setLocationRelativeTo(null);
		welcomeWindow.setVisible(true);
		welcomeWindow.repaint();
		
		GUIView.setColor(null);
		synchronized (monitor) {
			while (gameMode == null) {
				System.out.println("Sto aspettando modalità");
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		return GUIView.gameMode;
	}
	
	
	@Override  //eseguita appena dopo aver inserito username 
	public void showInitialOrder() {
	
		if (!(welcomeWindow instanceof WelcomeWindow))
			return;
		welcomeWindow.showInitialOrder(this.game);
		System.out.println("ho appena invocato la showorder");
		welcomeWindow.pack();
		welcomeWindow.setLocationRelativeTo(null);
		welcomeWindow.setVisible(true);
		welcomeWindow.repaint();
	}
	
	@Override
	public void printPlayerStatus() {
		printBoard();
	}

	@Override
	public void printMessage(String message) {
		JOptionPane.showMessageDialog(welcomeWindow, message);
	}

	
	private volatile static String username;
	protected static void setUsername(String username) {
		GUIView.username = username;
	}
	@Override
	public String getPlayerUserame() {
		
		if (!(welcomeWindow instanceof WelcomeWindow))
			return "";
	
		welcomeWindow.setPlayerUsername();
		welcomeWindow.pack();
		welcomeWindow.setLocationRelativeTo(null);
		welcomeWindow.setVisible(true);
		
		GUIView.setColor(null);
		synchronized (monitor) {
			while (username == null) {
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		System.out.println(GUIView.username);
		return GUIView.username;
	}
	

	private volatile static String color;
	protected static void setColor(String color) {
		GUIView.color = color;
	}
	@Override
	public String getPlayerColor(ArrayList<String> colors) {
		
		if (!(welcomeWindow instanceof WelcomeWindow))
			return "";
		
		
		welcomeWindow.setColors(colors);
		welcomeWindow.pack();
		welcomeWindow.setLocationRelativeTo(null);
		welcomeWindow.setVisible(true);
		welcomeWindow.repaint();
		
		GUIView.setColor(null);
		
		synchronized (monitor) {
			while (color == null) {
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		welcomeWindow.dispose();
		return GUIView.color;
	}

	@Override
	public void updateRoundInfo() {
		printBoard();
	}

	@Override
	public void printCurrentPlayer() {
		printBoard();
	}

	private volatile static int action = 0;
	protected static void setAction(int action) {
		GUIView.action = action;
	}
	@Override
	public int getPlayerAction() {
		/* INIT */
		GUIView.setAction(0);
		
		/* WAIT */
		synchronized (monitor) {
			System.out.println("Sto per entrare nel while, action vale; " +GUIView.action);
			while (GUIView.action == 0) {
				System.out.println("sono dentro while");
				System.out.println("Sono dentro while e aspetto azione da " + game.getCurrentPlayer().getUsername());
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("sono fuori da while e action vale" +GUIView.action);
		/* GET */
		return GUIView.action;
	}

	private volatile static String familyMember;
	protected static void setFamilyMember(String familyMember) {
		GUIView.familyMember = familyMember;
	}
	@Override
	public String getFamilyMember() {
		/* INIT */
		GUIView.setFamilyMember("");
		
		/* WAIT */
		synchronized (monitor) {
			while (GUIView.familyMember == "") {
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		/* GET */
		return GUIView.familyMember;
	}

	private volatile static int servants;
	protected static void setServants(int servants) {
		GUIView.servants = servants;
	}
	@Override
	public int getServants() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printPlayerAction() {
		printBoard();
	}

	@Override
	public void showNextTurnOrder() {
		printBoard();
	}


	@Override
	public int getEffectCoice(Effect effect1, Effect effect2) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ArrayList<Integer> getCouncilPrivilege() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
