package it.polimi.ingsw.ps46.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps46.server.action.Action;
import it.polimi.ingsw.ps46.server.action.MoveToActionSpaceAction;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.Card;
import it.polimi.ingsw.ps46.server.card.ExtraMoveEffect;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.CouncilPrivilege;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.FaithPoints;
import it.polimi.ingsw.ps46.server.resources.MilitaryPoints;
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
	private FamilyMember familyMember = null;
	private int servants = 0;
	private ResourceSet cost = null;
	private ArrayList<Player> missingTurnPlayers = new ArrayList<Player>();
	private ExtraMoveEffect extraMove = null;


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
			familyMember = game.getCurrentPlayer().getFamilyMember(eventStringInput.getString());
			break;
		case FAMILY_MEMBER_DICE_BONUS_CHOICE :
			familyMemberChosenForLeaderCard(eventStringInput.getString());
			break;
		
		default:
			break;
		}
	}


	public void visit(EventIntInput eventIntInput) {
		switch(eventIntInput.getType()) {
		case BONUS_TILE_CHOICE :
			int choice = eventIntInput.getValue();
			game.getCurrentPlayer().getPersonalBoard().setBonusTile(new BonusTile(game.getBonusTiles().get(choice)));
			game.getBonusTiles().remove(choice);
			break;
		case PLAYER_ACTION :
			actionSpaceID = eventIntInput.getValue();
			break;
		case SERVANTS_USED : 
			servants = eventIntInput.getValue();
			break;
		case COUNCIL_PRIVILEGE_CHOICE :
			int privilege = eventIntInput.getValue();
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(game.getCouncilPrivileges().get(privilege));
			game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().sub(new CouncilPrivilege(1));
			if(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("CouncilPrivilege").getQuantity() == 0)
			break;
		case ACTIVATION_LEADER_CARDS_CHOICE :
			activationLeaderCards(eventIntInput.getValue());
			break;
		case DISCARD_LEADER_CARDS_CHOICE :
			discardLeaderCards(eventIntInput.getValue());
			break;
		case VATICAN_SUPPORT_CHOICE :
			vaticanReport(eventIntInput.getValue());
			break;
		default:
			break;
		}
	}
	
	
	
	private void discardLeaderCards(int playerChoice) {
		int index = 1;
		for (String string : game.getCurrentPlayer().getLeaderCards().keySet()) {
			if( index == playerChoice ){
				game.getCurrentPlayer().getLeaderCards().remove(string);
				game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(new CouncilPrivilege(1));
			}
			index++;
		}
		
	}


	//qui arriva già il comando " attiva la carta corrispondente all'int value "
	//attivare gli effetti immediati delle carte
	//metterle come attive
	private void activationLeaderCards(int playerChoice) {
		
		int index = 1;
		for (String string : game.getCurrentPlayer().getLeaderCards().keySet()) {
			if( index == playerChoice ){
				game.getCurrentPlayer().getLeaderCards().get(string).use(game);
			}
			index++;
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
	
	
	
	public void visit(EventCostChoice eventCostChoice) {
		switch(eventCostChoice.getMessage()) {
		case CARD_COST_CHOICE :
			VentureCard card = eventCostChoice.getCard();
			if(eventCostChoice.getChoice() == 1)
				cost = card.getCost();
			else 
				cost = card.getCostTwo();
			break;
		default:
			break;
		}
	}



	@Override
	public void visit(EventExtraMove eventExtraMove) {
		switch(eventExtraMove.getMessage()) {
		case EXTRA_MOVE :
			extraMove = eventExtraMove.getExtraMoveEffect();
			familyMember = new FamilyMember("");
			familyMember.setValueOfFamilyMember(eventExtraMove.getExtraMoveEffect().getValueOfTheExtraMove());
			startAction();
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
			game.setGameState(GameState.SETUP_ROUND);
			roundSetup();
			
			for(int turn = game.getCurrentPhase(); turn < game.getPHASES_PER_ROUND(); turn++) {
				turnSetup();
				
				for(Player player : game.getPlayers()) {
					
					if(game.getCurrentPhase()==1 && !(player.getGenericMalus().isEmpty()) && (player.getGenericMalus().containsKey("passYourFirstMove"))) {
						missingTurnPlayers.add(player);
						game.setGameState(GameState.MISSING_TURN);
						game.setCurrentPlayer(player);
					}
					else {
						newTurn(player);
					}
					
				}
				while(!missingTurnPlayers.isEmpty()) {
					Player player = missingTurnPlayers.get(0);
					missingTurnPlayers.remove(0);
					newTurn(player);
				}
			}
			turnSetup();
			
			if(game.getCurrentRound() == game.getROUNDS_PER_PERIOD()) {
				for(Player player : game.getPlayers()) {
					game.setGameState(GameState.VATICAN_REPORT);
					game.setCurrentPlayer(player);
				}
			}
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
		setupBonusTiles();
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
	
	private void setupBonusTiles() {
		game.setGameState(GameState.SETUP_BONUS_TILES);
		Collections.reverse(game.getPlayers());
		for(Player player : game.getPlayers())
			game.setCurrentPlayer(player);
		Collections.reverse(game.getPlayers());
	}
	
	
	
	/**
	 * @param round 
	 * @param period 
	 * 
	 */
	private void roundSetup() {
		
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
				if(key.equals("Neutral")) {
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
	
	
	
	/**
	 * 
	 */
	private void newTurn(Player player) {
		
		if(game.checkIfCouldDiscardLeaderCards()) {
			game.setGameState(GameState.DISCARD_LEADER_CARDS);
			game.setCurrentPlayer(player);
		}
		if(game.checkIfHasLeaderCardsActivable()) {
			game.setGameState(GameState.ACTIVATION_LEADER_CARDS);
			game.setCurrentPlayer(player);
		}
		
		game.setGameState(GameState.GET_PLAYER_ACTION);
		game.setCurrentPlayer(player);
		
		if(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("CouncilPrivilege").getQuantity() > 0) {
			game.setGameState(GameState.COUNCIL_PRIVILEGE);
			game.setCurrentPlayer(player);
		}
		
		if(game.checkIfCouldDiscardLeaderCards()) {
			game.setGameState(GameState.DISCARD_LEADER_CARDS);
			game.setCurrentPlayer(player);
			}
		//setto qui le azioni delle carte leader
		//se ha carte attivabili, creare un metodo che faccia il check...
		if(game.checkIfHasLeaderCardsActivable()) {
			game.setGameState(GameState.ACTIVATION_LEADER_CARDS);
			game.setCurrentPlayer(player);
		}
		
	}
	
	
	
	/*
	 * 
	 */
	private void startAction() {
		Player player = game.getCurrentPlayer();
		
		if(!game.getGameState().equals(GameState.EXTRA_MOVE)) {
			//checking if there are activated some bonus on the value of the family members (given by Sigismondo Malatesta)
			if(game.getCurrentPlayer().getBonusMap().containsKey("NeutralFamilyMember")){
				if(familyMember.getColor().equals("Neutral")){
					familyMember.getValueFamilyMember().sumDice(game.getCurrentPlayer().getBonusMap().get("NeutralFamilyMember"));
				}
			}
			
			//checking if there are activated some bonus on the value of the family members (given by Lucrezia Borgia)
			if(game.getCurrentPlayer().getBonusMap().containsKey("ColoredFamilyMember")){
				if(!familyMember.getColor().equals( "Neutral")){
					familyMember.getValueFamilyMember().sumDice(game.getCurrentPlayer().getBonusMap().get("ColoredFamilyMember"));
				}
			}
		}
		
		//activation of Ludovico il Moro's effect, setting every colored familyMembers (not already used) to the new DiceValue of 5
		if((game.getCurrentPlayer().getLeaderCards().containsKey("Ludovico il Moro") && (game.getCurrentPlayer().getLeaderCards().get("Ludovico il Moro").isActive())))
			player.getLeaderCards().get("Ludovico il Moro").getLeaderEffect().activateEffect(game);
		
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
		//save the value of the familyMember before all the changes
		int familyMemberValue = familyMember.getValueFamilyMember().getValue();
		
		//increases the value of the family member with the servants 
		//check if the specific malus is activated when  the family member value with servants. If it is it costs double.
		if(!player.getGenericMalus().isEmpty()) {
			if( player.getGenericMalus().containsKey("doubleServantsCost") ) 
				servants /= 2;
		}
		familyMember.setValueOfFamilyMember(new Dice(familyMemberValue+servants));
		player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").sub(new Servants(servants*2));
		
		//check excommunication malus that give -4 on the familyMember Value if you want to collect a type of card
		if(actionSpace.getType().equals("Tower")) {
			if(!player.getDiceMalus().isEmpty()) {
				if( player.getDiceMalus().containsKey("DiceMalusEffectForCards") ) {
					for (String string : player.getDiceMalus().keySet() ) {
						//check if the specific malus is activated on "this type of card" and if the player wants to meve into the same type of tower
						if( (player.getDiceMalus().get(string).getType().equals("TerritoryCards")) && (game.getBoard().getColorOfTower(actionSpace.getId()).equals("green"))) {
							if( familyMember.getValueFamilyMember().greaterOrEqual(player.getDiceMalus().get(string).getMalus())) {
								familyMember.getValueFamilyMember().subDice(player.getDiceMalus().get(string).getMalus());
							}
							else
								familyMember.setValueOfFamilyMember(new Dice(0));
						}
						
						else if( player.getDiceMalus().get(string).getType().equals("BuildingCards") && (game.getBoard().getColorOfTower(actionSpace.getId()).equals("yellow"))) {
							if( familyMember.getValueFamilyMember().greaterOrEqual(player.getDiceMalus().get(string).getMalus())) {
								familyMember.getValueFamilyMember().subDice(player.getDiceMalus().get(string).getMalus());
							}
							else
								familyMember.setValueOfFamilyMember(new Dice(0));
						}
						
						else if( player.getDiceMalus().get(string).getType().equals("VentureCards") && (game.getBoard().getColorOfTower(actionSpace.getId()).equals("purple"))) {
							if( familyMember.getValueFamilyMember().greaterOrEqual(player.getDiceMalus().get(string).getMalus())) {
								familyMember.getValueFamilyMember().subDice(player.getDiceMalus().get(string).getMalus());
							}
							else
								familyMember.setValueOfFamilyMember(new Dice(0));
						}
						
						else if( player.getDiceMalus().get(string).getType().equals("CharacterCards") && (game.getBoard().getColorOfTower(actionSpace.getId()).equals("blue"))) {
							if( familyMember.getValueFamilyMember().greaterOrEqual(player.getDiceMalus().get(string).getMalus())) {
								familyMember.getValueFamilyMember().subDice(player.getDiceMalus().get(string).getMalus());
							}
							else
								familyMember.setValueOfFamilyMember(new Dice(0));
						}
					}
				}
			}
		}
		
		if(actionSpace.getType().equals("Tower")) {
			int tower = (actionSpace.getId() - 1) / game.getBoard().getNumberOfTowers();
			if(tower == 3) {//ventureCard
				if(game.getBoard().getCardOfTheTowerFloor(actionSpace.getId()) != null) {
					game.getCardCost((VentureCard) game.getBoard().getCardOfTheTowerFloor(actionSpace.getId()));
				}
			}
		}
		
		Action action = new MoveToActionSpaceAction(game, player, familyMember, actionSpace, cost);
		boolean executed = action.execute();
		actionSpaceID = 0;
		servants = 0;
		cost = null;

		if(!executed) {

			//restores original value of the family member
			familyMember.setValueOfFamilyMember(new Dice(familyMemberValue));
			familyMember = null;
			player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").add(new Servants(servants));
			
			if(!game.getGameState().equals(GameState.EXTRA_MOVE)) {
				game.setGameState(GameState.ACTION_NOT_VALID);
				game.setCurrentPlayer(player);
			}
			else
				extraMove.activateEffect(game);
		}
	}
	
	
	
	/**
	 * 
	 */
	private void vaticanReport(int playerChoice) {
		Player player = game.getCurrentPlayer();
		FaithPoints playerFaithPoints = new FaithPoints(player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("FaithPoints").getQuantity());
		
		if ( playerFaithPoints.greaterOrEqual(game.getFaithPointsRequiredForPeriod().get(game.getCurrentPeriod()-1)) ){
			
				if (playerChoice == 1){ 
					player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("VictoryPoints").add(game.getVaticanReportVictoryPoints().get(playerFaithPoints.getQuantity()));
					player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("FaithPoints").setQuantity(0);
					if((player.getLeaderCards().containsKey("Sisto IV") && (player.getLeaderCards().get("Sisto IV").isActive())))
						player.getLeaderCards().get("Sisto IV").getLeaderEffect().activateEffect(game);

				}	
				else if( playerChoice == 2 )
					game.getBoard().getExcommunicationTiles().get(game.getCurrentPeriod()-1).getPermanentMalusEffect().activationMalus(game);
			}
			
			else
				game.getBoard().getExcommunicationTiles().get(game.getCurrentPeriod()-1).getPermanentMalusEffect().activationMalus(game);
	}
	
	
	private void familyMemberChosenForLeaderCard(String familyMemberChosen) {
		// TODO Auto-generated method stub
		//Player player = game.getCurrentPlayer();
	}
	
	/**
	 * 
	 */
	private void endRound() {

		//Remove all the all the faceup Development Cards from the board and remove the color of the players in the action spaces
		for(int tower = 0; tower < game.getBoard().getNumberOfTowers(); tower++) {
			for (int floor = 0; floor < game.getBoard().getTower(tower).getNumberOfFloors(); floor++) {
				game.getBoard().getTower(tower).getTowerFloor(floor).setCard(null);
				game.getBoard().getTower(tower).getTowerFloor(floor).getActionSpace().setPlayerColor("");
			}
		}
		
		//Remove the color of the players in the action spaces
		for(ActionSpace actionSpace : game.getBoard().getBoardBoxes()) {
			actionSpace.setPlayerColor("");
		}
		
		//Change the Turn Order following the order of the Family Members placed in the Council Palace.
		ArrayList<Player> councilPalaceOrder = game.getCouncilPalaceOrder();
		if(councilPalaceOrder.size() < game.getNumberPlayers())
			for(Player player : game.getPlayers()) {
				if(!councilPalaceOrder.contains(player))
					councilPalaceOrder.add(player);
			}
		
		//Face down every Leader Card of every Player at the end of the turn
		for (Player player: game.getPlayers()) {
			for (String string : player.getLeaderCards().keySet()) {
				 player.getLeaderCards().get(string).setAsInactive();
			}
		}
		
		game.setNextTurnOrder(councilPalaceOrder);
	}
	
	
	
	/**
	 * 
	 */
	private void finalScores() {
		Map<Integer, VictoryPoints> finalScores = game.getFinalScores();
		
		//calculating what is the player's final rate based on the military points and create a map with them victory points ready to be added to the final counting.
		ArrayList<Integer> playerOrderForMilitaryPoints = new ArrayList<>(game.getNumberPlayers());
		Map<Integer, MilitaryPoints> idPlayerAndMilitaryPointsMap = new HashMap<>();
		for (Player playerForIterate : game.getPlayers()) {
			playerOrderForMilitaryPoints.add(playerForIterate.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("MilitaryPoints").getQuantity());
			idPlayerAndMilitaryPointsMap.put(playerForIterate.getIdPlayer(), new MilitaryPoints(playerForIterate.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("MilitaryPoints").getQuantity()) );
		}
		Collections.sort(playerOrderForMilitaryPoints);
		Collections.reverse(playerOrderForMilitaryPoints);
					
		LinkedHashMap <Integer, VictoryPoints> finalOrderPlusVictoryPpoints = new LinkedHashMap<>();
		int indexVictoryPointsForMilitaryPointsGained = 0;
		
		for (int i = 0; i < game.getPlayers().size(); i++){
			
			if(indexVictoryPointsForMilitaryPointsGained < game.getVictoryPointsForMilitaryPoints().size()) {
				int idActualPlayer = 0;
				//looking for the player who has this amount of points and memorizing his id into idActualPlayer
				for (Integer integer : idPlayerAndMilitaryPointsMap.keySet()) {
					if (idPlayerAndMilitaryPointsMap.get(integer).getQuantity() == playerOrderForMilitaryPoints.get(i)){
						idActualPlayer = integer;
						idPlayerAndMilitaryPointsMap.remove(integer); //to not create copies of the same player
					}		
				}
				//filling the map with the player's id and how many victory points has got by militarypoints
				if (playerOrderForMilitaryPoints.get(i) != playerOrderForMilitaryPoints.get(i+1)) {
					finalOrderPlusVictoryPpoints.put(idActualPlayer, game.getVictoryPointsForMilitaryPoints().get(indexVictoryPointsForMilitaryPointsGained));
					indexVictoryPointsForMilitaryPointsGained++;		
				}
				else 
					finalOrderPlusVictoryPpoints.put(idActualPlayer, game.getVictoryPointsForMilitaryPoints().get(indexVictoryPointsForMilitaryPointsGained));
			}
		}
		
		for(Player player : game.getPlayers()) {
			
			//Get Victory points earned by the player during the game
			VictoryPoints victoryPoints = new VictoryPoints(player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("VictoryPoints").getQuantity());
			
			//check if there are some malus effect of the type "loseOneVictoryPointEveryXResource" from victory points 
			if( (player.getDecreaseAtFinalMalus().getFrom() != null) && (player.getDecreaseAtFinalMalus().getFrom().equals("VictoryPoints"))) {
				int temporaryvalue = victoryPoints.getQuantity();
				temporaryvalue /= player.getDecreaseAtFinalMalus().getDecreasedResources().getResourcesMap().get("VictoryPoints").getQuantity();
				victoryPoints.sub(new VictoryPoints(temporaryvalue));
				}
			
			//Add final victory points from venture cards
			//check if there are some malus effect of the type "notCountingVictoryPointsFromCards"
			if(player.getGenericMalus().isEmpty() || (!(player.getGenericMalus().isEmpty()) && !(player.getGenericMalus().containsKey("notCountingVictoryPointsFromCards"))) 
					|| (!(player.getGenericMalus().isEmpty()) && (player.getGenericMalus().containsKey("notCountingVictoryPointsFromCards")) && 
						(!player.getGenericMalus().get("notCountingVictoryPointsFromCards").getType().equals( "VentureCards")))) {
				
					for(Card card : player.getPersonalBoard().getVentureDeck()) {
						card.use(game);
					}
			}
			
			//Add final victory points from territory cards
			//check if there are some malus effect of the type "notCountingVictoryPointsFromCards"
			if(player.getGenericMalus().isEmpty() || (!(player.getGenericMalus().isEmpty()) && !(player.getGenericMalus().containsKey("notCountingVictoryPointsFromCards"))) 
					|| (!(player.getGenericMalus().isEmpty()) && (player.getGenericMalus().containsKey("notCountingVictoryPointsFromCards")) && 
						(!player.getGenericMalus().get("notCountingVictoryPointsFromCards").getType().equals("TerritoryCards"))))
				victoryPoints.add(game.getVictoryPointsFromTerritoryCards().get(player.getPersonalBoard().getTerritoryDeck().size()));
			
			//Add final victory points from character cards
			//check if there are some malus effect of the type "notCountingVictoryPointsFromCards"
			if(player.getGenericMalus().isEmpty() || (!(player.getGenericMalus().isEmpty()) && !(player.getGenericMalus().containsKey("notCountingVictoryPointsFromCards"))) 
					|| (!(player.getGenericMalus().isEmpty()) && (player.getGenericMalus().containsKey("notCountingVictoryPointsFromCards")) && 
							(!player.getGenericMalus().get("notCountingVictoryPointsFromCards").getType().equals("CharacterCards"))))
				victoryPoints.add(game.getVictoryPointsFromCharacterCards().get(player.getPersonalBoard().getCharacterDeck().size()));
			
			//Add final victory points from Military points based on the final placement for military points
			if(finalOrderPlusVictoryPpoints.containsKey(player.getIdPlayer()) ) {
				
				player.getPersonalBoard().getPlayerResourceSet().add(finalOrderPlusVictoryPpoints.get(player.getIdPlayer()));
				
			}
			
			
			//Add final victory points from number of resources
			int resources = 0;
			resources += player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Wood").getQuantity();
			resources += player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Stones").getQuantity();
			resources += player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Money").getQuantity();
			resources += player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("Servants").getQuantity();
			victoryPoints.add(new VictoryPoints(resources/5));
			
			//check if there are some malus effect of the type "loseOneVictoryPointEveryXResource" from playerResourceSet (wood,stones,servants,money)
			if( (player.getDecreaseAtFinalMalus().getFrom() != null) && (player.getDecreaseAtFinalMalus().getFrom().equals("PlayerResourceSet")))
				victoryPoints.sub(new VictoryPoints(resources));
			
			
			//check if there are some malus effect of the type "loseOneVictoryPointEveryXResource" from military points
			if( (player.getDecreaseAtFinalMalus().getFrom() != null) && (player.getDecreaseAtFinalMalus().getFrom().equals("MilitaryPoints"))) {
				int temporaryvalue = player.getPersonalBoard().getPlayerResourceSet().getResourcesMap().get("MilitaryPoints").getQuantity();
				temporaryvalue *= player.getDecreaseAtFinalMalus().getDecreasedResources().getResourcesMap().get("MilitaryPoints").getQuantity();

				victoryPoints.sub(new VictoryPoints(temporaryvalue));
			}
					
			//check if there are some malus effect of the type "loseOneVictoryPointEveryXResource" from building card cost (wood, stones)
			if( (player.getDecreaseAtFinalMalus().getFrom() != null) && (player.getDecreaseAtFinalMalus().getFrom() .equals( "BuildingCards"))) {
				int woodstonesCost = 0;
				if(!(player.getPersonalBoard().getBuildingDeck().isEmpty())){
					for (BuildingCard buildingCard : player.getPersonalBoard().getBuildingDeck()) {
						woodstonesCost += buildingCard.getCost().getResourcesMap().get("Wood").getQuantity();
						woodstonesCost += buildingCard.getCost().getResourcesMap().get("Stones").getQuantity();
					}
				}
				victoryPoints.sub(new VictoryPoints(woodstonesCost));
			}
			
			
			finalScores.put(new Integer(player.getIdPlayer()), victoryPoints);
		}
		
		game.setFinalScores(finalScores);
		
	}
	
}