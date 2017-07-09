package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.polimi.ingsw.ps46.client.View;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.Effect;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


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
	
	
	@Override 
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

					e.printStackTrace();
				}
			}
		}
		/* GET */
		System.out.println("Ho ricevuto FM " +GUIView.familyMember+ " da " +this.playerUsername);
		return GUIView.familyMember;
	}


	
	private ImageIcon servantsIcon;
	@Override
	public int getServants() {
		
		BufferedImage image = null;
		if (servantsIcon == null) {
			try {
				image =  Token.getImagePathMode("mixed/excomm_1_3.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			servantsIcon =  new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		}
		
		for (;;) {
			String answ = (String) JOptionPane.showInputDialog( 
					gameWindow,
					"How many servants do you want to use?",
					"Player " + this.playerUsername + " choose the number of servants",
					JOptionPane.QUESTION_MESSAGE,
					servantsIcon,
					null,
					null
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
		return 0;
	}

	
	private static final String[] privilegesTypes = { "Wood: 1 - Stones: 1", "Servants: 2", 
			 												"Money: 2", "MilitaryPoints: 2", "FaithPoints: 1" };
	@Override
	public ArrayList<Integer> getCouncilPrivilege() {
		
		ArrayList<Integer> councilPrivileges = new ArrayList<Integer>();
		int privileges = game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("CouncilPrivilege").getQuantity();
		String message = "You have " + privileges + " council privileges.";
		JOptionPane.showMessageDialog(gameWindow, message);
		
		while(privileges > 0) {
			
			boolean choiceDefault = false;
			int x = 0;
			System.out.println("ciclo " +x);
			JFrame frame = new JFrame("Counsil Privilege");
		    String choice = (String) JOptionPane.showInputDialog(frame, 
		        "Select the Counsil Privilege",
		        "Type",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        privilegesTypes, 
		        privilegesTypes[0]);

		    // choice will be null if the user clicks Cancel
		    System.out.printf("Privilege chosen is %s.\n", choice);
		    frame.pack();
		    frame.setVisible(true);
		    
		    if (choice == null) {
		    	getCouncilPrivilege();
		    }
			
			int index = 1;
			for(ResourceSet resourceSet : game.getCouncilPrivileges()) {
				System.out.println(index + ". " + resourceSet.toString());
				index++;
			}
			
			Integer bonus = null;
			
			switch (choice) {
				
			case "Wood: 1 - Stones: 1":
				bonus = new Integer(0);
				break;
				
			case "Servants: 2":
				bonus = new Integer(1);
				break;
				
			case "Money: 2":
				bonus = new Integer(2);
				break;
				
			case "MilitaryPoints: 2":
				bonus = new Integer(3);
				break;
			
			case "FaithPoints: 1":
				bonus = new Integer(4);
				break;
				
			default:
				choiceDefault = true;
			}
			
			if (choiceDefault == false) {
			if(!councilPrivileges.contains(bonus)) {
				councilPrivileges.add(bonus);
				System.out.println("prima del -- ho privilegi: " +privileges);
						
				privileges--;
				
				if(privileges > 0) {
					message = ("You still have " + privileges + " council privileges.");
					JOptionPane.showMessageDialog(gameWindow, message);
					System.out.println("ho ancora privilegi");
				}
			}
			else {
				message = ("You already have this privilege");
				JOptionPane.showMessageDialog(gameWindow, message);
			}
			x++;
			}
		}
		
		
		return councilPrivileges;
		  
	}


	@Override
	public int getBonusTile() {

		return 0;
	}


	
}
