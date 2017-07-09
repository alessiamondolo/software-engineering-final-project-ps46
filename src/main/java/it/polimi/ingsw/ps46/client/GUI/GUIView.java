package it.polimi.ingsw.ps46.client.GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	private WelcomeWindow welcomeWindow;
	private GameWindow gameWindow;
	private boolean firstTime = true;
	private boolean firstTime2 = true;
	private String playerUsername;
	private String playerColor;
	
	private static Object monitor = new Object();
	protected static Object getMonitor() {
		return monitor;
	}
	
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
		
		if (firstTime2) {
			gameWindow.setPlayerInfo(this.playerUsername, this.playerColor);
			firstTime2 = false;
		}
		
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
		String player = game.getCurrentPlayer().getUsername();
		System.out.println("il current player è " +player);
		
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
	
	
/*	private volatile static String gameMode;
	protected static void setGameMode(String mode) {
		GUIView.gameMode = mode;
	}
	public String getGameMode() {
	
		if (!(welcomeWindow instanceof WelcomeWindow))
			return "";
		welcomeWindow.setGameMode();
		welcomeWindow.pack();
		welcomeWindow.setLocationRelativeTo(null);
		welcomeWindow.setVisible(true);
		welcomeWindow.repaint();
		
		GUIView.setColor(null);
		synchronized (monitor) {
			while (gameMode == null) {
				
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		return GUIView.gameMode;
	}*/
	
	
	@Override  //eseguita appena dopo aver inserito username 
	public void showInitialOrder() {
	
		if (!(welcomeWindow instanceof WelcomeWindow))
			return;
		welcomeWindow.showInitialOrder(this.game);
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
		
		System.out.println("sto chiedendo nome");

		welcomeWindow.setPlayerUsername();
		
		GUIView.setUsername(null);
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
		this.playerUsername = GUIView.username;
		
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
		
		System.out.println("Sto chiedendo colore");
		welcomeWindow.setColors(colors);
		
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
		this.playerColor = GUIView.color;
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
		
		String message = ("Player " + this.playerUsername + " it is your turn. Choose a space action");
		
		/* INIT */
		GUIView.setAction(0);
	
		JOptionPane.showMessageDialog(gameWindow, message);
		
		/* WAIT */
		synchronized (monitor) {
			while (GUIView.action == 0) {
				
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("Ho ricevuto azione " +GUIView.action+ " da " +this.playerUsername);
		/* GET */
		return GUIView.action;
	}

	private volatile static String familyMember;
	protected static void setFamilyMember(String familyMember) {
		GUIView.familyMember = familyMember;
	}
	@Override
	public String getFamilyMember() {
		
		String message = "Now choose the family member you want to use for this action";
		/* INIT */
		GUIView.setFamilyMember("");
		
		JOptionPane.showMessageDialog(gameWindow, message);
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
		System.out.println("Ho ricevuto FM " +GUIView.familyMember+ " da " +this.playerUsername);
		return GUIView.familyMember;
	}



	@Override
	public int getServants() {
		
		for (;;) {
			String answ = JOptionPane.showInputDialog(
					gameWindow,
					"How many servants do you want to use?",
					"Player " + this.playerUsername + " choose the number of servants",
					JOptionPane.QUESTION_MESSAGE
				);
		    
			if (answ == null)
				return getServants();
			
			answ = answ.trim();
			if (answ == "")
				return 0;
			
			
			try {
				
				System.out.println(this.playerUsername+ " Ha chiesto " +answ+ " servi");
				return Integer.parseInt(answ);
				
			} catch (NumberFormatException e) {
				this.printMessage("Il testo inserito non è un formato di numero valido");
			}
		}

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


	@Override
	public int getBonusTile() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVaticanSupport() {
		// TODO Auto-generated method stub
		return 0;
	}


	
}
