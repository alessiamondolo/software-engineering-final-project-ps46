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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.FactoryCards;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.VictoryPoints;
import it.polimi.ingsw.ps46.utils.MyJSONParser;


/**
 * 
 * @author Alessia Mondolo
 *
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
	
	private ArrayList<ResourceSet> councilPrivileges;
	
	private LinkedHashMap<Integer, VictoryPoints> victoryPointsFromTerritoryCards = new LinkedHashMap<Integer, VictoryPoints>();
	private LinkedHashMap<Integer, VictoryPoints> victoryPointsFromCharacterCards = new LinkedHashMap<Integer, VictoryPoints>();
	private Map<Integer, VictoryPoints> finalScores;
	
	private GameState gameState;
	private String configFilesPath = "./src/main/java/it/polimi/ingsw/ps46/server/config/";
	
	
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
		configFinalPoints();
		
		finalScores = new LinkedHashMap<Integer, VictoryPoints>();
	}

	private void newState(Object event) {
		setChanged();
		notifyObservers(event);
	}
	
//--------------------------------------------------//
//----------BEGIN OF CONFIGURATION METHODS----------//
//--------------------------------------------------//
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
	 * 
	 */
	private void configBoard() {
		FactoryBoard factoryBoard = FactoryBoard.getFactoryBoard();
		board = factoryBoard.createBoard("Towers.json", "ActionSpaces.json", numberPlayers);
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
	 * 
	 * 
	 */
	private void configBonusTiles() {
		FactoryBoard factoryBoard = FactoryBoard.getFactoryBoard();
		bonusTiles = factoryBoard.createBonusTiles("BonusTiles.json");
	}
	
	
	
	/**
	 * 
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
	 * 
	 */
	private void configFinalPoints() {
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
//--------------------------------------------------//
//-----------END OF CONFIGURATION METHODS-----------//
//--------------------------------------------------//
	
	
	
//--------------------------------------------------//
//---------------BEGIN OF GET METHODS---------------//
//--------------------------------------------------//
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
//--------------------------------------------------//
//----------------END OF GET METHODS----------------//
//--------------------------------------------------//

	
	public void startGame() {
		newState(new EventMessage(NewStateMessage.START_GAME));
	}
	
	public void giveBonusTile(Player player, int bonusTile) {
		player.getPersonalBoard().setBonusTile(bonusTiles.get(bonusTile));
	}
	
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
	
	public void setCurrentRound(int newRound) {
		currentRound = newRound;
		newState(new EventMessage(NewStateMessage.UPDATE_ROUND_INFO));
	}
	
	public void setCurrentPeriod(int newPeriod) {
		currentPeriod = newPeriod;
	}
	
	public void throwDice() {
		for(String key : dice.keySet()) {
			dice.get(key).throwDice();
			for(Player player : players)
				player.getFamilyMember(key).setValueOfFamilyMember(dice.get(key));
		}
		newState(new EventMessage(NewStateMessage.THROWN_DICE));
	}
	
	public void addToCouncilPalaceOrder(Player player) {
		councilPalaceOrder.add(player);
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public void useCard(BuildingCard card) {
		if(!card.getDoubleChoice())
			card.use(this);
		else {
			newState(new EventEffectChoice(NewStateMessage.EXCHANGE_RESOURCES_CHOICE, card));
		}
	}

	public int getPHASES_PER_ROUND() {
		return PHASES_PER_ROUND;
	}

	public int getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase(int currentPhase) {
		this.currentPhase = currentPhase;
		newState(new EventMessage(NewStateMessage.UPDATE_PHASE_INFO));
	}

	public Map<Integer, VictoryPoints> getFinalScores() {
		return finalScores;
	}

	public void setFinalScores(Map<Integer, VictoryPoints> finalScores) {
		this.finalScores = finalScores;
		newState(new EventMessage(NewStateMessage.UPDATE_FINAL_SCORES));
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

	public void setCouncilPrivileges(ArrayList<ResourceSet> councilPrivileges) {
		this.councilPrivileges = councilPrivileges;
	}

}
