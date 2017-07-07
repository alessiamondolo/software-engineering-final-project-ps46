package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps46.server.action.Action;
import it.polimi.ingsw.ps46.server.action.MoveToActionSpaceAction;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.resources.CounsilPrivilege;
import it.polimi.ingsw.ps46.server.resources.Servants;
import it.polimi.ingsw.ps46.server.resources.VictoryPoints;


/**
 * The GameController controls and coordinates the game logic.
 * 
 * @author Alessia Mondolo
 */
public class GameController implements Observer, ViewEventVisitor {

	private Game game;
	
	private int actionSpaceID = 0;
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
			break;
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
		case FAMILY_MEMBER_CHOICE :
			familyMemberName = eventStringInput.getString();
			break;
		
		default:
			break;
		}
	}
	
	
	
	public void visit(EventIntInput eventIntInput) {
		switch(eventIntInput.getType()) {
		case PLAYER_ACTION :
			actionSpaceID = eventIntInput.getValue();
			break;
		case SERVANTS_USED : 
			servants = eventIntInput.getValue();
			break;
		case COUNCIL_PRIVILEGE_CHOICE :
			int privilege = eventIntInput.getValue();
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(game.getCouncilPrivileges().get(privilege));
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().sub(new CounsilPrivilege(1));
			if(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("CounsilPrivilege").getQuantity() == 0)
			break;
		default:
			break;
		}
	}
	
	
	
	public void visit(EventEffectChoice eventEffectChoice) {
		switch(eventEffectChoice.getMessage()) {
		case EXCHANGE_RESOURCES_CHOICE :
			BuildingCard card = eventEffectChoice.getCard();
			card.useOptional(eventEffectChoice.getChoice(), game);
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
			
			for(int turn = game.getCurrentPhase(); turn < game.getPHASES_PER_ROUND(); turn++) {
				turnSetup();
				
				for(Player player : game.getPlayers()) {
					game.setGameState(GameState.GET_PLAYER_ACTION);
					game.setCurrentPlayer(player);
				}
			}
			
			if(game.getCurrentRound() == game.getROUNDS_PER_PERIOD())
				vaticanReport();
			endRound();
		}
		
		finalScores();
				
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
		for(Player player : game.getPlayers())
			game.setCurrentPlayer(player);
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
		for(Player player : game.getPlayers())
			game.setCurrentPlayer(player);
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
		
		
		//Clear family members positions
		for(Player player : game.getPlayers()) {
			for (String key : player.getFamilyMembersMap().keySet()) {
				player.getFamilyMembersMap().get(key).clearPositionOfFamilyMember();
				if(key == "Neutral") {
					player.getFamilyMembersMap().get(key).setValueOfFamilyMember(new Dice(0));
				}
			}
		}
		
		
		//Throw dice and update board in the view
		game.throwDice();
		
	}
	
	
	
	/**
	 * 
	 */
	private void turnSetup() {
		if(game.getCurrentPhase() == game.getPHASES_PER_ROUND()) {
			game.setCurrentPhase(1);
		}
		else {
			int phase = game.getCurrentPhase() + 1;
			game.setCurrentPhase(phase);
		}
	}
	
	
	
	
	/*
	 * 
	 */
	private void startAction() {
		Player player = game.getCurrentPlayer();
		
		FamilyMember familyMember = game.getCurrentPlayer().getFamilyMember(familyMemberName);
		
		ActionSpace actionSpace = null;
		if(actionSpaceID <= (game.getBoard().getNumberOfTowers() * game.getBoard().getTower(0).getNumberOfFloors())) {
			int tower = (actionSpaceID - 1) / game.getBoard().getNumberOfTowers();
			int floor = (actionSpaceID - 1) % game.getBoard().getNumberOfTowers();
			actionSpace = game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace();
		}
		else {
			for(ActionSpace boardBox : game.getBoard().getBoardBoxes()) {
				if(boardBox.getId() == actionSpaceID) {
					actionSpace = boardBox;
					break;
				}
			}
		}	
		int familyMemberValue = familyMember.getValueFamilyMember().getValue();
		//increases the value of the family member with the servants
		familyMember.setValueOfFamilyMember(new Dice(familyMemberValue+servants));
		player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").sub(new Servants(servants));
		 
		Action action = new MoveToActionSpaceAction(game, player, familyMember, actionSpace);
		boolean executed = action.execute();
		if(!executed) {
			//restores original value of the family member
			familyMember.setValueOfFamilyMember(new Dice(familyMemberValue));
			player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").add(new Servants(servants));
			
			game.setGameState(GameState.ACTION_NOT_VALID);
			game.setCurrentPlayer(player);
		}
		if(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("CounsilPrivilege").getQuantity() > 0) {
			game.setGameState(GameState.COUNCIL_PRIVILEGE);
			game.setCurrentPlayer(player);
		}
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
		//Remove all the all the faceup Development Cards from the board
		for(int tower = 0; tower < game.getBoard().getNumberOfTowers(); tower++) {
			for (int floor = 0; floor < game.getBoard().getTower(tower).getNumberOfFloors(); floor++) {
				game.getBoard().getTower(tower).getTowerFloor(floor).setCard(null);
			}
		}
		
		//Change the Turn Order following the order of the Family Members placed in the Council Palace.
		ArrayList<Player> councilPalaceOrder = game.getCouncilPalaceOrder();
		if(councilPalaceOrder.size() < game.getNumberPlayers())
			for(Player player : game.getPlayers()) {
				if(!councilPalaceOrder.contains(player))
					councilPalaceOrder.add(player);
			}
		game.setNextTurnOrder(councilPalaceOrder);
	}
	
	
	
	/**
	 * 
	 */
	private void finalScores() {
		Map<Integer, VictoryPoints> finalScores = game.getFinalScores();
		for(Player player : game.getPlayers()) {
			//Add final victory points from venture cards
			for(Card card : player.getPersonalBoard().getVentureDeck()) {
				card.use(game);
			}
			
			//Get points from victory points
			VictoryPoints victoryPoints = new VictoryPoints(player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("VictoryPoints").getQuantity());
			
			//Add final victory points from number of resources
			int resources = 0;
			resources += player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Wood").getQuantity();
			resources += player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Stones").getQuantity();
			resources += player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Money").getQuantity();
			resources += player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").getQuantity();
			victoryPoints.add(new VictoryPoints(resources/5));
			
			//Add final victory points from cards
			victoryPoints.add(game.getVictoryPointsFromTerritoryCards().get(player.getPersonalBoard().getTerritoryDeck().size()));
			victoryPoints.add(game.getVictoryPointsFromCharacterCards().get(player.getPersonalBoard().getCharacterDeck().size()));
			
			finalScores.put(new Integer(player.getIdPlayer()), victoryPoints);
		}
		
		game.setFinalScores(finalScores);
	}

}
