package it.polimi.ingsw.ps46.server;

import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps46.server.action.Action;
import it.polimi.ingsw.ps46.server.action.MoveToActionSpaceAction;
import it.polimi.ingsw.ps46.server.card.Card;


/**
 * The GameController controls and coordinates the game logic.
 * 
 * @author Alessia Mondolo
 */
public class GameController implements Observer, ViewEventVisitor {

	private Game game;
	
	private String actionName = null;
	private String familyMemberName = null;
	private int servants = 0;


	/**
	 * Creates a new GameController object.
	 */
	public GameController(Game game) {
		this.game = game;
	}

	
	
	public void update(Observable o, Object obj) {
		((ViewEventAcceptor) obj).accept(this);
	}
	
	
	public void visit(EventMessage eventMessage) {
		switch(eventMessage.getMessage()) {
		case ACTION_SENT :
			startAction();
		default:
			break; 
			
		}
	}
	
	
	public void visit(EventStringInput eventStringInput) {
		switch(eventStringInput.getType()) {
		case PLAYER_USERNAME :
			game.getCurrentPlayer().setUsername(eventStringInput.getString());
			break;
		case PLAYER_COLOR : 
			game.getCurrentPlayer().setColor(eventStringInput.getString());
			break;
		case PLAYER_ACTION :
			actionName = eventStringInput.getString();
			break;
		case FAMILY_MEMBER_CHOICE :
			familyMemberName = eventStringInput.getString();
			break;
		case SERVANTS_USED : 
			//TODO temporaneo
			servants = 1;
			break;
		default:
			break;
		}
	}

	
	
	/**
	 * Main method that controls all the game logic, from the setup of the game until the end of the game.
	 */
	public void run() {
		
		setupGame();
		
		while((game.getCurrentPeriod() < game.getPERIODS()) || (game.getCurrentRound() < game.getROUNDS_PER_PERIOD())) {
			
			roundSetup();
			
			game.setGameState(GameState.GET_PLAYER_ACTION);
			for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
				Player player=iterator.next();
				game.setCurrentPlayer(player);
				playerActions();
			}
			if(game.getCurrentRound() == game.getROUNDS_PER_PERIOD())
				vaticanReport();
			endRound();
		}
				
	}

	
	
	/**
	 * Sets up the game, by calling other methods to welcome the players, ask the players' username, 
	 * select a random initial order for the game and ask the players which color they want to use.
	 */
	private void setupGame() {
		game.setGameState(GameState.SETUP_GAME);
		game.startGame();
		setupPlayers();
		setupInitialOrder();
		setupPlayersColor();
		setupInitialResources();
	}

	
	
	/**
	 * Iterates through the list of players, asking each player through the view the username that they want to use during the game.
	 */
	private void setupPlayers() {
		game.setGameState(GameState.SETUP_PLAYERS_USERNAME);
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			game.setCurrentPlayer(player);
			//TODO check per avere username univoco?
		}
	}
	
	
	
	/**
	 * Chooses a random order for the first turn of the game and shows the order to the players through the view.
	 */
	private void setupInitialOrder() {
		game.setGameState(GameState.SETUP_INITIAL_ORDER); 
		game.setInitialOrder();
	}
	
	
	
	/**
	 * Asks the players through the view the color that they want to use during the game.
	 */
	private void setupPlayersColor() {
		game.setGameState(GameState.SETUP_PLAYERS_COLOR);
		for(ListIterator<Player> iterator=game.getPlayers().listIterator(); iterator.hasNext();){
			Player player=iterator.next();
			game.setCurrentPlayer(player);			
		}
	}
	
	
	
	private void setupInitialResources() {
		game.setGameState(GameState.SETUP_INITIAL_RESOURCES);
		game.giveInitialResources();
	}
	
	
	
	/**
	 * @param round 
	 * @param period 
	 * 
	 */
	private void roundSetup() {
		game.setGameState(GameState.SETUP_ROUND);
		if(game.getCurrentRound() == game.getROUNDS_PER_PERIOD()) {
			int period = game.getCurrentPeriod() + 1;
			game.setCurrentPeriod(period);
			game.setCurrentRound(1);
		}
		else {
			int round = game.getCurrentRound() + 1;
			game.setCurrentRound(round);
		}
		
		//Girare le carte associate a quel periodo nelle torri
		Card card;
		for(int tower = 0; tower < game.getBoard().getNumberOfTowers(); tower++) {
			for(int floor = 0; floor < game.getBoard().getTower(tower).getNumberOfFloors(); floor++) {
				switch(tower) {
				case 0 : //First tower
					card = game.getTerritoryCardsDeck().get(((floor)+4*(game.getCurrentRound()-1)));
					game.getBoard().getTower(tower).getTowerFloor(floor).setCard(card);
					break;
				case 1 : //Second Tower
					card = game.getCharacterCardsDeck().get(((floor)+4*(game.getCurrentRound()-1)));
					game.getBoard().getTower(tower).getTowerFloor(floor).setCard(card);
					break; 
				case 2 : //Third tower
					card = game.getBuildingCardsDeck().get(((floor)+4*(game.getCurrentRound()-1)));
					game.getBoard().getTower(tower).getTowerFloor(floor).setCard(card);
					break;
				case 3 : //Fourth tower
					card = game.getVentureCardsDeck().get(((floor)+4*(game.getCurrentRound()-1)));
					game.getBoard().getTower(tower).getTowerFloor(floor).setCard(card);
					break;
				}
			}
			
		}
		
		
		//Throw dice and update board in the view
		game.throwDice();
		
	}
	
	
	
	/**
	 * 
	 */
	private void playerActions() {
		//ActionSpaceName move = 
				//view.getPlayerAction();
	}
	
	
	
	/*
	 * 
	 */
	private void startAction() {
		FamilyMember familyMember = game.getCurrentPlayer().getFamilyMember(familyMemberName);
		ActionSpace actionSpace = null;
		switch(actionName) {
		case "GREEN_TOWER_FLOOR_1" : 
			actionSpace = game.getBoard().getTower(1).getTowerFloor(1).getActionSpace();
			break;
		case "GREEN_TOWER_FLOOR_2" : 
			actionSpace = game.getBoard().getTower(1).getTowerFloor(2).getActionSpace();
			break;
		case "GREEN_TOWER_FLOOR_3" : 
			actionSpace = game.getBoard().getTower(1).getTowerFloor(3).getActionSpace();
			break;
		case "GREEN_TOWER_FLOOR_4" : 
			actionSpace = game.getBoard().getTower(1).getTowerFloor(4).getActionSpace();
			break;
		
		}
		
		Action action = new MoveToActionSpaceAction(game, game.getCurrentPlayer(), familyMember, servants, actionSpace);
		action.execute();
	}
	
	
	
	/**
	 * 
	 */
	private void vaticanReport() {
		// TODO Auto-generated method stub
		//Da implementare con le regole avanzate
	}
	
	
	
	/**
	 * 
	 */
	private void endRound() {
		// TODO Auto-generated method stub
	}

}
