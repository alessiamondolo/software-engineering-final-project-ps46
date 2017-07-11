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
import it.polimi.ingsw.ps46.server.card.ExtraMoveEffect;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * Graphic User Interface of the game Lorenzo il Magnifico. It implements all the methods
 * required by the supertype View in order to display the information to the user through
 * Swing API's containers. 
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
	 *  Cascade creation of the game GUI 
	 *  
	 *  @param visible needed to set what the user can see at a given time
	 */

	private void createAndShowGUI(boolean visible) {
		// Create and set up the window.
		initUI(visible);
		return;
	}
	
	/**
	 * Create and set up the game configuration window
	 * 
	 * @param visible
	 */
	
	private void initUI(boolean visible) {
		
		welcomeWindow = new WelcomeWindow();
		welcomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeWindow.pack();
		welcomeWindow.setVisible(visible); 
		welcomeWindow.repaint();
		return;
	}
	
	/**
	 *  Central method called to update all GUI's children with the current game situation
	 */

	@Override
	public void printBoard() {
		
		// to associate a client view with its player's username and color
		if (firstTime2) {
			gameWindow.setPlayerInfo(this.playerUsername, this.playerColor);
			firstTime2 = false;
		}

		gameWindow.update(this.game);
		gameWindow.pack();
		gameWindow.setVisible(true);
		gameWindow.repaint();
		
	}
	
	/**
	 * Sets a new value for the attribute game in order to provide update info
	 * 
	 * @param game : the new game object that we want to assign to the attribute game.
	 */
	
	@Override
	public void setGame(Game game) {

		this.game = game;
		
		// to allocate only a single game window with updated information
		if (firstTime) {	
		gameWindow = new GameWindow(this.game);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstTime = false;
		}
		firstTime = false;
		
	}


	/**
	 * 	Show the first screen of the game 
	 */
	
	@Override
	public void welcomeMessage() {
		
		welcomeWindow.setVisible(false);

	}
	
	/**
	 * Show the initial order of the game
	 */
	
	@Override 
	public void showInitialOrder() {
	
		welcomeWindow.showInitialOrder(this.game);
	}
	
	/**
	 * Update the GUI
	 */
	
	@Override
	public void printPlayerStatus() {
		printBoard();
	}

	/**
	 * Show a pop-up message
	 */ 
	
	@Override
	public void printMessage(String message) {
		JOptionPane.showMessageDialog(welcomeWindow, message);
	}

	
	private volatile static String username;
	protected static void setUsername(String username) {
		GUIView.username = username;
	}
	
	/**
	 * Gets the username that the client wants to use for the game.
	 * 
	 * @return username : the username received by input from the client.
	 */
	
	@Override
	public String getPlayerUserame() {

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
		this.playerUsername = GUIView.username;
		return GUIView.username;
	}
	
	/**
	 * Gets the color that the client wants to use for the game. The color can be chosen 
	 * from a list of colors that is received as parameter.
	 * 
	 * @param colors : a list of colors from which the client can choose his color.
	 * @return color : the color received by input from the client.
	 */

	private volatile static String color;
	protected static void setColor(String color) {
		GUIView.color = color;
	}
	@Override
	public String getPlayerColor(ArrayList<String> colors) {
		
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
		welcomeWindow = new WelcomeWindow();
		this.playerColor = GUIView.color;
		return GUIView.color;
	}
	
	/**
	 * Updates the game board
	 */

	@Override
	public void updateRoundInfo() {
		printBoard();
	}
	
	/**
	 * Updates the game board
	 */

	@Override
	public void printCurrentPlayer() {
		printBoard();
	}
	
	/**
	 * Gets the ID of the action space where the client wants to move during his action.
	 * 
	 * @return action : the ID of the action space.
	 */
	
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
		
		/* GET */
		
		return GUIView.action;
	}
	
	/**
	 * Gets the family member that the client wants to move during his action.
	 * 
	 * @return color : the color of the family member.
	 */

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
		
		return GUIView.familyMember;
	}

	
	/**
	 * Gets the number of servants that the client wants to add to his family member for the current action.
	 * The maximum number of servants that the player can use is taken from the number of servants that he has at the moment of the action.
	 * Additional input control to check whether the input number is negative
	 * 
	 * @return servants : the number of servants that the client wants to use.
	 */

	private ImageIcon servantsIcon;
	@Override
	public int getServants() {
		
		int playerServants = this.game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").getQuantity();
		
		BufferedImage image = null;
		if (servantsIcon == null) {
			try {
				image =  Token.getImagePathMode("mixed/excomm_1_3.png");
			} catch (IOException e) {
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
				
				int choice = Integer.parseInt(answ);
				if ( choice < 0 || choice > playerServants) {
					this.printMessage("Servants quantity is not valid");
					return getServants();
				} else return(choice);
				
			} catch (NumberFormatException e) {
				this.printMessage("Servants value is not a valid format");
			}
		}

	}

	/**
	 * Update game board
	 */
	
	@Override
	public void printPlayerAction() {
		printBoard();
	}

	/**
	 * Update game board
	 */
	@Override
	public void showNextTurnOrder() {
		printBoard();
	}

	/**
	 * Gets which of the two optional effects the player wants to activate.
	 * 
	 * @return choice : the effect chosen by the player.
	 */

	@Override
	public int getEffectCoice(Effect effect1, Effect effect2) {

		int result;
		final String[] effects = { "Upper Effect", "Lower Effect"};
		String message = ("Look at the card you want to produce with. Which of the two effects you wish to activate?");
		JOptionPane.showMessageDialog(gameWindow, message);
		
		String choice = (String) JOptionPane.showInputDialog(gameWindow, 
		"Choose Effect",
		"Effect",
		JOptionPane.QUESTION_MESSAGE, 
		null, 
		effects, 
		effects[0]);
				
		if (choice == null) {
	    	getEffectCoice(effect1, effect2);
	    }
		
		switch (choice) {
			
		case "Upper Effect":
			result = new Integer(0);
			break;
			
		case "Lower Effect":
			result = new Integer(1);
			break;
		
		default:
			return(getEffectCoice(effect1, effect2));
		}
		
		return result;
	}
			
	/**
	 * Gets which council privileges the player wants to get.
	 * If the player has more than one council privilege, it will get all of them, excluding each time the privileges
	 * already taken.
	 * 
	 * @return councilPrivileges : the list of the council privileges chosen by the player.
	 */
	
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
			
			JFrame frame = new JFrame("Counsil Privilege");
		    String choice = (String) JOptionPane.showInputDialog(frame, 
		        "Select the Counsil Privilege",
		        "Type",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        privilegesTypes, 
		        privilegesTypes[0]);

		    // choice will be null if the user clicks Cancel
		    
		    frame.pack();
		    frame.setVisible(true);
		    
		    if (choice == null) {
		    	getCouncilPrivilege();
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
						
				privileges--;
				
				if(privileges > 0) {
					message = ("You still have " + privileges + " council privileges.");
					JOptionPane.showMessageDialog(gameWindow, message);
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

	
	private volatile static int bonusTile  = -1;
	protected static void setBonusTile(int bonusTile) {
		GUIView.bonusTile = bonusTile;
	}
	
	/**
	 * Graphic implementation of the getBonusTile method. Asks the player which bonus tile
	 * he wants to use and return an int that indicates the chosen one.
	 * 
	 * @return int bonus tile
	 */
	
	
	@Override
	public int getBonusTile() {

		welcomeWindow.setBonusTile(this.game);
		
		GUIView.setBonusTile(-1);
		
		synchronized (monitor) {
			while (bonusTile == -1) {
				try {
					monitor.wait();
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			}
		}
		
		welcomeWindow.dispose();
		
		return GUIView.bonusTile;
	}
	
	/**
	 * Gets whether the user wants to support the Vatican
	 * 
	 * @return int : to map the user input
	 */

	@Override
	public int getVaticanSupport() {
		
		String message = "Do you want to support the church?";
		int answ = JOptionPane.showConfirmDialog(gameWindow, message);
		if (answ == JOptionPane.YES_OPTION) return 1;
		if (answ == JOptionPane.NO_OPTION) return 2;
		else return getVaticanSupport();
		
	}

	@Override
	public int getCostCoice(ResourceSet cost1, ResourceSet cost2) {
		// TODO Auto-generated method stub
		return 1;
	}
	
	
	@Override
	public int getExtraMove(ExtraMoveEffect effect) {
		int move = 0;
		switch(effect.getType()) {
		case "ActivateHarvestAction":
		
			return(17);
			
		case "ActivateProdutionAction":
			
			return(19);
			
		
		case "MoveToActionSpaceAction": {
			
			switch(effect.getWhichActionSpace()) {
			case "AllTowers" :
				return(1);
				
			
			case "GreenTower" :
				
				return(1);
			
			
			case "YellowTower" :
			
				return(9);
				
			case "BlueTower" :
			
				return(5);
				
			case "PurpleTower" :
				
				return(13);	
			}
			}
		}
		
		return move;
	}
	
	
	
	
	
	
	@Override
	public ArrayList<Integer> getActivationLeaderCards() {
		ArrayList<Integer> leaderCardsActivated = new ArrayList<Integer>();
	
		leaderCardsActivated.add(new Integer(0));
		
		return leaderCardsActivated;
	
	}

	
	
	@Override
	public ArrayList<Integer> getDiscardLeaderCards() {
		ArrayList<Integer> leaderCardsDiscarded = new ArrayList<Integer>();
	
		leaderCardsDiscarded.add(new Integer(0));
		
		return leaderCardsDiscarded;
	}

	@Override
	public void showFinalScores() {
		
		printBoard();
		
	}


	
}
