package it.polimi.ingsw.ps46.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Observable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.ExcommunicationTile;
import it.polimi.ingsw.ps46.server.card.ExtraMoveEffect;
import it.polimi.ingsw.ps46.server.card.FactoryCards;
import it.polimi.ingsw.ps46.server.card.LeaderCard;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.FaithPoints;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.VictoryPoints;
import it.polimi.ingsw.ps46.utils.MyJSONParser;


/**
 * This class represents the main Model class for the MVC architecture of the application.
 * It contains all the information about the current game state.
 * 
 * @author Alessia Mondolo
 * @version 1.1
 */
public class Game extends Observable implements Serializable {
	
	private static final long serialVersionUID = 2038242402419462794L;
	
	private final int ROUNDS_PER_PERIOD = 2;
	private final int PERIODS = 3;
	private final int PHASES_PER_ROUND = 4;
	private int currentRound = 0;
	private int currentPeriod = 1;
	private int currentPhase = 0;	
	
	private int numberPlayers; 
	private ArrayList<Player> players;
	private ArrayList<Player> councilPalaceOrder = new ArrayList<Player>();
	private Player currentPlayer;
	private Board board;
	private ArrayList<TerritoryCard> territoryCardsDeck;
	private ArrayList<BuildingCard> buildingCardsDeck;
	private ArrayList<CharacterCard> characterCardsDeck;
	private ArrayList<VentureCard> ventureCardsDeck;
	private Map<String, Dice> dice;
	private ArrayList<BonusTile> bonusTiles = new ArrayList<BonusTile>();
	private ArrayList<ExcommunicationTile> excommunicationTiles;
	private ArrayList<LeaderCard> leaderCards;
	
	private ArrayList<ResourceSet> councilPrivileges;
	
	private LinkedHashMap<Integer, VictoryPoints> victoryPointsFromTerritoryCards;
	private LinkedHashMap<Integer, VictoryPoints> victoryPointsFromCharacterCards;
	private Map<Integer, VictoryPoints> finalScores;
	private LinkedHashMap<Integer, VictoryPoints> vaticanReportVictoryPoints;
	private ArrayList <FaithPoints> faithPointsRequiredForPeriod;
	private ArrayList <VictoryPoints> victoryPointsForMilitaryPoints;
	private LinkedHashMap<Integer, ArrayList<Player>> finalScoresOrder = null ;

	private GameState gameState;
	private String configFilesPath = "./src/main/java/it/polimi/ingsw/ps46/server/config/";
	
	
	/**
	 * Creates a new Game object.
	 * 
	 * @param numberPlayers : the number of players that will play the game.
	 */
	public Game(int numberPlayers) {
		this.numberPlayers = numberPlayers;
		players = new ArrayList<Player>();
		//creates the players objects and adds them to the list of players
		for(int idPlayer = 1; idPlayer<=numberPlayers; idPlayer++) {
			players.add(new Player(idPlayer));
		}
	
		configDice();
		configDecks();
		configBoard();
		configBonusTiles();
		configCouncilPrivileges();
		configLeaderCards();
		configFinalPoints();
		configVaticanReportVictoryPoints();
		configFaithPointsRequiredForPeriod();
		configVictoryPointsForMilitaryPoints();

		finalScores = new LinkedHashMap<Integer, VictoryPoints>();
	}

	private void newState(Object event) {
		setChanged();
		notifyObservers(event);
	}
	
//--------------------------------------------------------------------------------------------------------------//
//----------------------------------------BEGIN OF CONFIGURATION METHODS----------------------------------------//
//--------------------------------------------------------------------------------------------------------------//
	/**
	 * Configures the dices that will be used during the game.
	 */
	private void configDice() {		
		dice = new HashMap<String, Dice>();
		dice.put("Black", new Dice());
		dice.put("Orange", new Dice());
		dice.put("White", new Dice());
	}
	
	
	
	/**
	 * Configures the board, parsing from json file the Towers and the other Action Spaces.
	 * Also, it configures the Excommunication Tiles, parsing them from json file and assigning to the board one 
	 * tile per period.
	 */
	private void configBoard() {
		FactoryBoard factoryBoard = FactoryBoard.getFactoryBoard();
		board = factoryBoard.createBoard("Towers.json", "ActionSpaces.json", numberPlayers);
		excommunicationTiles = factoryBoard.buildExcomunicationTiles("ExcommunicationTile.json");

		//Shuffle excommunication tiles
		for(int period = 1; period <= PERIODS; period++) {
			Collections.shuffle(excommunicationTiles.subList((excommunicationTiles.size()/PERIODS)*(period-1), 
					(excommunicationTiles.size()/PERIODS)*period));
		}
		
		ArrayList<ExcommunicationTile> tiles = new ArrayList<ExcommunicationTile>();
		for(int period = 1; period <= PERIODS; period++) {
			tiles.add(excommunicationTiles.get((excommunicationTiles.size()/PERIODS)*(period-1)));
		}
		
		getBoard().setExcommunicationTiles(tiles);
	}
	
	
	
	/**
	 * Configures the decks of cards that will be used during the game and shuffles the decks,
	 * maintaining all the cards of the same periods all together (cards of a minor period are always
	 * before cards of successive periods, even if they are shuffled).
	 */
	private void configDecks() {

		FactoryCards factoryCards = FactoryCards.getFactoryCards();
		territoryCardsDeck = factoryCards.createTerritoryCards("TerritoryCards.json");
		for(int period = 1; period <= PERIODS; period++) {
			Collections.shuffle(territoryCardsDeck.subList((territoryCardsDeck.size()/PERIODS)*(period-1), 
					(territoryCardsDeck.size()/PERIODS)*period));
		}
		
		buildingCardsDeck = factoryCards.createBuildingCards("BuildingCards.json");
		for(int period = 1; period <= PERIODS; period++) {
			Collections.shuffle(buildingCardsDeck.subList((buildingCardsDeck.size()/PERIODS)*(period-1), 
					(buildingCardsDeck.size()/PERIODS)*period));
		}
		
		characterCardsDeck = factoryCards.createCharacterCards("CharacterCards.json");
		for(int period = 1; period <= PERIODS; period++) {
			Collections.shuffle(characterCardsDeck.subList((characterCardsDeck.size()/PERIODS)*(period-1), 
					(characterCardsDeck.size()/PERIODS)*period));
		}
		
		ventureCardsDeck = factoryCards.createVentureCards("VentureCards.json");
		for(int period = 1; period <= PERIODS; period++) {
			Collections.shuffle(ventureCardsDeck.subList((ventureCardsDeck.size()/PERIODS)*(period-1), 
					(ventureCardsDeck.size()/PERIODS)*period));
		}
		
	}
	
	
	
	/**
	 * Creates the bonus tiles, that will be used from the players during the game, by parsing them
	 *  from a json file.
	 * 
	 */
	private void configBonusTiles() {
		FactoryBoard factoryBoard = FactoryBoard.getFactoryBoard();
		bonusTiles = factoryBoard.createBonusTiles("BonusTiles.json");			
	}
	
	
	
	/**
	 * Configures from a json file the bonuses between which the player can choose when he receives 
	 * a council privilege.
	 */
	private void configCouncilPrivileges() {
		councilPrivileges = new ArrayList<ResourceSet>();
		JSONParser parser = new JSONParser();
		MyJSONParser myJSONParser = new MyJSONParser();

		try {
        	Object obj = parser.parse(new FileReader(configFilesPath + "CouncilPrivileges.json"));
        	JSONArray resourcesJSON = (JSONArray) obj;        	
        	
            Iterator<?> resourcesIterator = resourcesJSON.iterator();
            while (resourcesIterator.hasNext()) {
            	JSONArray resourceSetJSON = (JSONArray) resourcesIterator.next();
            	councilPrivileges.add(myJSONParser.buildResourceSet(resourceSetJSON));
            }
            
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	/**
	 * Configures from a json file the leader cards that will be used during the game.<br>
	 * After the parsing of the cards, it gives 4 random cards for each player.
	 */
	private void configLeaderCards() {
		leaderCards = new ArrayList<LeaderCard>();
		JSONParser parser = new JSONParser();
		MyJSONParser myJSONParser = new MyJSONParser();

		try {
        	Object obj = parser.parse(new FileReader(configFilesPath + "LeaderCards.json"));
        	JSONArray leaderCardsJSON = (JSONArray) obj;        	
        	
            Iterator<?> iterator = leaderCardsJSON.iterator();
            while (iterator.hasNext()) {
            	JSONObject leaderCardJSON = (JSONObject) iterator.next();
            	leaderCards.add(myJSONParser.buildLeaderCard(leaderCardJSON));
            }
            
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

		Collections.shuffle(leaderCards);
		int index = 0;
		for(Player player : players) {
			for(int i = 0; i < 4; i++) {
				LeaderCard leaderCard = leaderCards.get(index);
				player.getLeaderCards().put(leaderCard.getCardName(), leaderCard);
				index++;
			}
		}
	}
	
	
	
	/**
	 * Configures from json files the maps that will be used at the end of the game to calculate 
	 * the final score bonuses.
	 */
	private void configFinalPoints() {
		victoryPointsFromTerritoryCards = new LinkedHashMap<Integer, VictoryPoints>();
		victoryPointsFromCharacterCards = new LinkedHashMap<Integer, VictoryPoints>();
		JSONParser parser = new JSONParser();
		MyJSONParser myJSONParser = new MyJSONParser();

		try {
        	Object obj = parser.parse(new FileReader(configFilesPath + "VictoryPointsFromTerritoryCards.json"));
        	JSONArray victoryPointsArray = (JSONArray) obj;        	
        	victoryPointsFromTerritoryCards = myJSONParser.buildVictoryPointsMap(victoryPointsArray);
        	obj = parser.parse(new FileReader(configFilesPath + "VictoryPointsFromCharacterCards.json"));
        	victoryPointsArray = (JSONArray) obj;        	
        	victoryPointsFromCharacterCards = myJSONParser.buildVictoryPointsMap(victoryPointsArray);
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
	}
	
	
	
	/**
	 * Configures from a json file the map that will be used at the end of the game to calculate 
	 * the final score bonus from the vatican report victory points.
	 */
	public void configVaticanReportVictoryPoints() {
		//TODO da file
		vaticanReportVictoryPoints = new LinkedHashMap<>();
		vaticanReportVictoryPoints.put(0, new VictoryPoints(0));
		vaticanReportVictoryPoints.put(1, new VictoryPoints(1));
		vaticanReportVictoryPoints.put(2, new VictoryPoints(2));
		vaticanReportVictoryPoints.put(3, new VictoryPoints(3));
		vaticanReportVictoryPoints.put(4, new VictoryPoints(4));
		vaticanReportVictoryPoints.put(5, new VictoryPoints(5));
		vaticanReportVictoryPoints.put(6, new VictoryPoints(7));
		vaticanReportVictoryPoints.put(7, new VictoryPoints(9));
		vaticanReportVictoryPoints.put(8, new VictoryPoints(11));
		vaticanReportVictoryPoints.put(9, new VictoryPoints(13));
		vaticanReportVictoryPoints.put(10, new VictoryPoints(15));
		vaticanReportVictoryPoints.put(11, new VictoryPoints(17));
	}
	
	
	
	/**
	 * Configures from a json file the list of the required faith points for each period.
	 */
	public void configFaithPointsRequiredForPeriod() {
		//TODO da file
		faithPointsRequiredForPeriod = new ArrayList<FaithPoints>();

		faithPointsRequiredForPeriod.add( new FaithPoints(3));
		faithPointsRequiredForPeriod.add( new FaithPoints(4));
		faithPointsRequiredForPeriod.add( new FaithPoints(5));
	}
	
	
	
	/**
	 * Configures from a json file the map that will be used at the end of the game to calculate 
	 * the final score bonus from the military points.
	 */
	public void configVictoryPointsForMilitaryPoints() {
		//TODO da file
		victoryPointsForMilitaryPoints = new ArrayList<VictoryPoints>();

		victoryPointsForMilitaryPoints.add( new VictoryPoints(5));
		victoryPointsForMilitaryPoints.add( new VictoryPoints(3));
	}

//--------------------------------------------------------------------------------------------------------------//
//-----------------------------------------END OF CONFIGURATION METHODS-----------------------------------------//
//--------------------------------------------------------------------------------------------------------------//
	
	
	
//--------------------------------------------------------------------------------------------------------------//
//---------------------------------------------BEGIN OF SET METHODS---------------------------------------------//
//--------------------------------------------------------------------------------------------------------------//
	public Player getCurrentPlayer() {
		return currentPlayer;		
	}

	
	public int getCurrentPeriod() {
		return currentPeriod;
	}
	
	
	public int getCurrentRound() {
		return currentRound;
	}

	
	public Dice getDice(String color) {
		return dice.get(color);
	}


	public Integer getROUNDS_PER_PERIOD() {
		return ROUNDS_PER_PERIOD;
	}


	public Integer getPERIODS() {
		return PERIODS;
	}


	public Integer getNumberPlayers() {
		return numberPlayers;
	}


	public List<Player> getPlayers() {
		return players;
	}


	public Board getBoard() {
		return board;
	}


	public ArrayList<BonusTile> getBonusTiles() {
		return bonusTiles;
	}

	
	public ArrayList<TerritoryCard> getTerritoryCardsDeck() {
		return territoryCardsDeck;
	}


	public ArrayList<BuildingCard> getBuildingCardsDeck() {
		return buildingCardsDeck;
	}


	public ArrayList<CharacterCard> getCharacterCardsDeck() {
		return characterCardsDeck;
	}


	public ArrayList<VentureCard> getVentureCardsDeck() {
		return ventureCardsDeck;
	}


	public Map<String, Dice> getDice() {
		return dice;
	}
	

	public ArrayList<Player> getCouncilPalaceOrder() {
		return councilPalaceOrder;
	}
	
	
	public GameState getGameState() {
		return gameState;
	}

	
	public void getCardCost(VentureCard card) {
		if(card.getdoubleCostChoice()) {
			newState(new EventCostChoice(NewStateMessage.CARD_COST_CHOICE, card));
		}
	}


	public int getPHASES_PER_ROUND() {
		return PHASES_PER_ROUND;
	}

	
	public int getCurrentPhase() {
		return currentPhase;
	}

	
	public Map<Integer, VictoryPoints> getFinalScores() {
		return finalScores;
	}

	
	public LinkedHashMap<Integer, VictoryPoints> getVictoryPointsFromTerritoryCards() {
		return victoryPointsFromTerritoryCards;
	}
	

	public LinkedHashMap<Integer, VictoryPoints> getVictoryPointsFromCharacterCards() {
		return victoryPointsFromCharacterCards;
	}

	
	public ArrayList<ResourceSet> getCouncilPrivileges() {
		return councilPrivileges;
	}

	
	public Map<Integer, VictoryPoints> getVaticanReportVictoryPoints() {
		return vaticanReportVictoryPoints;
	}
	
	
	public ArrayList<FaithPoints> getFaithPointsRequiredForPeriod() {
		return faithPointsRequiredForPeriod;
	}

	
	public ArrayList<VictoryPoints> getVictoryPointsForMilitaryPoints() {
		return victoryPointsForMilitaryPoints;
	}

	
	public ArrayList<ExcommunicationTile> getExcommunicationTiles() {
		return excommunicationTiles;
	}

	
	public LinkedHashMap<Integer, ArrayList<Player>> getFinalScoresOrder() {
		return finalScoresOrder;
	}
//--------------------------------------------------------------------------------------------------------------//
//----------------------------------------------END OF GET METHODS----------------------------------------------//
//--------------------------------------------------------------------------------------------------------------//

	
	
//--------------------------------------------------------------------------------------------------------------//
//---------------------------------------------BEGIN OF SET METHODS---------------------------------------------//
//--------------------------------------------------------------------------------------------------------------//
	public void setNextTurnOrder(ArrayList<Player> nextTurnOrder) {
		players = nextTurnOrder;
		newState(new EventMessage(NewStateMessage.SET_NEXT_TURN_ORDER));
	}


	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
		newState(new EventMessage(NewStateMessage.CHANGED_CURRENT_PLAYER));
	}
	
	
	public void setInitialOrder() {
		Collections.shuffle(players);
		newState(new EventMessage(NewStateMessage.SET_INITIAL_ORDER));
	}
	
	
	public void setCurrentRound(int newRound) {
		currentRound = newRound;
		newState(new EventMessage(NewStateMessage.UPDATE_ROUND_INFO));
	}
	
	
	public void setCurrentPeriod(int newPeriod) {
		currentPeriod = newPeriod;
	}

	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	
	public void setCurrentPhase(int currentPhase) {
		this.currentPhase = currentPhase;
		newState(new EventMessage(NewStateMessage.UPDATE_PHASE_INFO));
	}

	
	public void setFinalScores(Map<Integer, VictoryPoints> finalScores) {
		this.finalScores = finalScores;
	}

	
	public void setCouncilPrivileges(ArrayList<ResourceSet> councilPrivileges) {
		this.councilPrivileges = councilPrivileges;
	}


	public void setFinalScoresOrder(LinkedHashMap<Integer, ArrayList<Player>> finalScoresOrder) {
		this.finalScoresOrder = finalScoresOrder;
		newState(new EventMessage(NewStateMessage.UPDATE_FINAL_SCORES));
		newState(new EventMessage(NewStateMessage.END_GAME));
	}
//--------------------------------------------------------------------------------------------------------------//
//----------------------------------------------END OF SET METHODS----------------------------------------------//
//--------------------------------------------------------------------------------------------------------------//
	
	
	
//--------------------------------------------------------------------------------------------------------------//
//-------------------------------------------BEGIN OF SUPPORT METHODS-------------------------------------------//
//--------------------------------------------------------------------------------------------------------------//
	/**
	 * Starts a new game, setting a new game state and notifying this to its observer. 
	 */
	public void startGame() {
		newState(new EventMessage(NewStateMessage.START_GAME));
	}
	
	
	
	/**
	 * Assigns to the player received as parameter the bonus tile corresponding to the ID received as parameter. 
	 * 
	 * @param player	: the player to who assign the bonus tyle
	 * @param bonusTile : the ID of the bonus tile to be assigned
	 */
	public void giveBonusTile(Player player, int bonusTile) {
		player.getPersonalBoard().setBonusTile(bonusTiles.get(bonusTile));
	}
	
	
	
	/**
	 * Gives to all the player the initial set of resources, based on the initial game order.<br>
	 * The sets of resources are configured from a json file.
	 */
	public void giveInitialResources() {
		ResourceSet initialResources = null;
		JSONParser parser = new JSONParser();
		MyJSONParser myJSONParser = new MyJSONParser();
		try {
        	Object obj = parser.parse(new FileReader(configFilesPath + "SetupResources.json"));
        	JSONArray resourcesJSON = (JSONArray) obj;        	
        	
            Iterator<?> resourcesIterator = resourcesJSON.iterator();
            ListIterator<Player> playersIterator = getPlayers().listIterator();
            while (resourcesIterator.hasNext() && playersIterator.hasNext()) {
            	JSONArray resourceSetJSON = (JSONArray) resourcesIterator.next();
                initialResources = myJSONParser.buildResourceSet(resourceSetJSON);
                Player currentPlayer = playersIterator.next();
                currentPlayer.getPersonalBoard().setResources(initialResources);
            }
            
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	/**
	 * Throws the dice, and after this updates the family members of the players with the new values.
	 */
	public void throwDice() {
		for(String key : dice.keySet()) {
			dice.get(key).throwDice();
			for(Player player : players)
				player.getFamilyMember(key).setValueOfFamilyMember(dice.get(key));
		}
	}
	
	
	
	/**
	 * Adds a new player to the council palace order. This method is called when a player
	 * moves on the council palace space.<br>
	 * The council palace order will be used to determine the next round order, at the end of the 
	 * current round.
	 * 
	 * @param player : the player to be added to the council palace order
	 */
	public void addToCouncilPalaceOrder(Player player) {
		councilPalaceOrder.add(player);
	}
	
	
	
	/**
	 * Activates the permanent effect of the building card received as parameter.<br>
	 * If the card has a double choice effect, sends a notify to its observers to require
	 * the choice of the effect from the player that is using the card.
	 * 
	 * @param card : the building card that has to be used
	 */
	public void useCard(BuildingCard card) {
		if(!card.getDoubleChoice())
			card.use(this);
		else {
			newState(new EventEffectChoice(NewStateMessage.EXCHANGE_RESOURCES_CHOICE, card));
		}
	}
	
	
	
	/**
	 * Checks if the current player has some leader cards that can be activated.
	 * 
	 * @return true : if at least one leader card can be activated, otherwise false
	 */
	public boolean checkIfHasLeaderCardsActivable(){
		
		for (String string : getCurrentPlayer().getLeaderCards().keySet()) {
			if(getCurrentPlayer().getLeaderCards().get(string).isActivable(this))
				return true;
		}
		return false;
	}
	
	
	
	/**
	 * Checks if the current player has some leader cards that can be discarded.
	 * 
	 * @return true : if at least one leader card can be discarded, otherwise false
	 */
	public boolean checkIfCouldDiscardLeaderCards(){
		
		for (String string : getCurrentPlayer().getLeaderCards().keySet()) {
			if(!(getCurrentPlayer().getLeaderCards().get(string).isActive()) )
				return true;
		}
		return false;
	}
	
	
	
	/**
	 * Activates the extra move effect received as parameter, setting a new game state and notifying this
	 * to its observer. 
	 * 
	 * @param effect : the extra move to activate
	 */
	public void extraMove(ExtraMoveEffect effect) {
		setGameState(GameState.EXTRA_MOVE);
		newState(new EventExtraMove(NewStateMessage.EXTRA_MOVE, effect));
	}
//--------------------------------------------------------------------------------------------------------------//
//-------------------------------------------BEGIN OF SUPPORT METHODS-------------------------------------------//
//--------------------------------------------------------------------------------------------------------------//
}
