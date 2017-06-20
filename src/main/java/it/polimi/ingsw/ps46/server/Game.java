package it.polimi.ingsw.ps46.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.FactoryCards;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.utils.MyJSONParser;


public class Game extends Observable {
	
	private final int ROUNDS_PER_PERIOD = 2;
	private final int PERIODS = 3;
	private int currentRound = 0;
	private int currentPeriod = 1;
	
	private int numberPlayers; 
	private List<Player> players;
	private List<Player> nextTurnOrder = new ArrayList<Player>();
	private Player currentPlayer;
	private Board board;
	private Set<TerritoryCard> territoryCardsDeck;
	private Set<BuildingCard> buildingCardsDeck;
	private Set<CharacterCard> characterCardsDeck;
	private Set<VentureCard> ventureCardsDeck;
	private Map<String, Dice> dice;
	
	private GameState gameState;
	
	
	Game(int numberPlayers) {
		this.numberPlayers = numberPlayers;
		players = new ArrayList<Player>();
		//creates the players objects and adds them to the list of players
		for(int idPlayer = 1; idPlayer<=numberPlayers; idPlayer++) {
			players.add(new Player(idPlayer));
		}
		configDice();
		configDecks();
		configBoard();
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
		//TODO da configurare tramite File
		dice.put("Black", new Dice());
		dice.put("Orange", new Dice());
		dice.put("White", new Dice());
	}
	
	
	
	private void configBoard() {
		FactoryBoard factoryBoard = FactoryBoard.getFactoryBoard();
		board = factoryBoard.createBoard("Towers.json", "ActionSpaces.json", numberPlayers);
	}
	
	
	
	/**
	 * Configures the decks of cards that will be used during the game.
	 */
	private void configDecks() {
		//TODO lettura di file di configurazione e costruzione della mappa tipologiaDiCarta-directoryFile 
		//che verrà poi utilizzata per chiamare un metodo createCards per ogni tipologia di carta
		FactoryCards factoryCards = FactoryCards.getFactoryCards();
		territoryCardsDeck = factoryCards.createTerritoryCards("TerritoryCards.json");
		buildingCardsDeck = factoryCards.createBuildingCards("BuildingCards.json");
		characterCardsDeck = factoryCards.createCharacterCards("CharacterCards.json");
		ventureCardsDeck = factoryCards.createVentureCards("VentureCards.json");
		
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


	public Set<TerritoryCard> getTerritoryCardsDeck() {
		return territoryCardsDeck;
	}


	public Set<BuildingCard> getBuildingCardsDeck() {
		return buildingCardsDeck;
	}


	public Set<CharacterCard> getCharacterCardsDeck() {
		return characterCardsDeck;
	}


	public Set<VentureCard> getVentureCardsDeck() {
		return ventureCardsDeck;
	}


	public Map<String, Dice> getDice() {
		return dice;
	}
	

	public List<Player> getNextTurnOrder() {
		return nextTurnOrder;
	}
//--------------------------------------------------//
//----------------END OF GET METHODS----------------//
//--------------------------------------------------//

	public void startGame() {
		newState(new EventMessage(NewStateMessage.START_GAME));
	}
	
	
	public void setNextTurnOrder(ArrayList<Player> nextTurnOrder) {
		this.nextTurnOrder = nextTurnOrder;
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
        	URL url = getClass().getResource("SetupResources.json");
        	Object obj = parser.parse(new FileReader(url.getPath()));
        	JSONArray resourcesJSON = (JSONArray) obj;        	
        	
            Iterator resourcesIterator = resourcesJSON.iterator();
            ListIterator<Player> playersIterator = getPlayers().listIterator();
            while (resourcesIterator.hasNext() && playersIterator.hasNext()) {
            	JSONArray resourceSetJSON = (JSONArray) resourcesIterator.next();
                initialResources = myJSONParser.buildResourceSet(resourceSetJSON);
                Player currentPlayer = playersIterator.next();
                currentPlayer.setResources(initialResources);
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
		for(String key : dice.keySet())
			dice.get(key).throwDice();
		newState(new EventMessage(NewStateMessage.THROWN_DICE));
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public GameState getGameState() {
		return gameState;
	}

}
