package it.polimi.ingsw.ps46.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.IncreaseResourcesEffect;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.ResourcesFactory;


public class Game extends Observable {
	
	private final int ROUNDS_PER_PERIOD = 2;
	private final int PERIODS = 3;
	
	private int numberPlayers; 
	private List<Player> players;
	private Player currentPlayer;
	private Board board;
	private Set<TerritoryCard> territoryCardsDeck;
	private Set<BuildingCard> buildingCardsDeck;
	private Set<CharacterCard> characterCardsDeck;
	private Set<VentureCard> ventureCardsDeck;
	private Map<String, Dice> dice;
	
	
	Game(int numberPlayers) {
		this.numberPlayers = numberPlayers;
		//creates the players objects and adds them to the list of players
		for(int idPlayer = 1; idPlayer<=numberPlayers; idPlayer++) {
			players.add(new Player(idPlayer));
		}
		configDice();
		configDecks();
		configBoard();
		
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
		//board = new Board();
		//lettura di file di configurazione
	}
	
	
	
	/**
	 * Configures the decks of cards that will be used during the game.
	 */
	private void configDecks() {
		//TODO lettura di file di configurazione e costruzione della mappa tipologiaDiCarta-directoryFile 
		//che verrà poi utilizzata per chiamare un metodo configCards per ogni tipologia di carta
		configTerritoryCards();
		configBuildingCards();
		configCharacterCards();
		configVentureCards();
	}
	
	
	
	/**
	 * Reads the configuration file for Territory Cards and creates the deck of territory cards.
	 */
	private void configTerritoryCards() {
		
		territoryCardsDeck = new HashSet<TerritoryCard>();

		JSONParser parser = new JSONParser();
		ResourcesFactory resourcesFactory = new ResourcesFactory();

        try {

        	URL url = getClass().getResource("TerritoryCards.json");
        	Object obj = parser.parse(new FileReader(url.getPath()));
        	
        	JSONArray territoryCards = (JSONArray) obj;
        	Iterator iterator = territoryCards.iterator();
        	while (iterator.hasNext()) {
        		
        		JSONObject jsonObject = (JSONObject) iterator.next();
        		
        		//Parsing of cardName field
                String cardName = (String) jsonObject.get("cardName");

                //BEGIN of parsing of immediateEffect field
                JSONObject effect = (JSONObject) jsonObject.get("immediateEffects");
                JSONArray additionalResourcesArray = (JSONArray) effect.get("additionalResources");
                List<Resource> immediateEffectResourceList = new ArrayList<Resource>();
                Iterator i = additionalResourcesArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    immediateEffectResourceList.add(newResource);
                }
                ResourceSet additionalResources = new ResourceSet(immediateEffectResourceList);
                IncreaseResourcesEffect immediateEffects = new IncreaseResourcesEffect(additionalResources);
                //END of parsing of immediateEffect field
                
                //BEGIN of parsing of permanentEffect field
                effect = (JSONObject) jsonObject.get("permanentEffects");
                additionalResourcesArray = (JSONArray) effect.get("additionalResources");
                List<Resource> permanentEffectResourceList = new ArrayList<Resource>();
                i = additionalResourcesArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    permanentEffectResourceList.add(newResource);
                }
                IncreaseResourcesEffect permanentEffects = new IncreaseResourcesEffect(new ResourceSet(permanentEffectResourceList));
                //END of parsing of permanentEffect field
                
                //BEGIN of parsing of cost field
                JSONArray costArray = (JSONArray) jsonObject.get("cost");
                List<Resource> costResourceList = new ArrayList<Resource>();
                i = costArray.iterator();
                while (i.hasNext()) {
                    JSONObject resource = (JSONObject) i.next();
                    String id = (String)resource.get("id");
                    int quantity = ((Long) resource.get("quantity")).intValue();
                    Resource newResource = resourcesFactory.getResource(id, quantity);
                    costResourceList.add(newResource);
                }
                ResourceSet cost = new ResourceSet(costResourceList);
                //END of parsing of cost field
                
                TerritoryCard territoryCard = new TerritoryCard(cardName, immediateEffects, permanentEffects, cost);
                territoryCardsDeck.add(territoryCard);
    	        System.out.println(territoryCard);
        		
        	}
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

	}
	
	

	private void configVentureCards() {
		// TODO Auto-generated method stub
		
	}


	
	private void configCharacterCards() {
		// TODO Auto-generated method stub
		
	}


	
	private void configBuildingCards() {
		// TODO Auto-generated method stub
		
	}
//--------------------------------------------------//
//-----------END OF CONFIGURATION METHODS-----------//
//--------------------------------------------------//
	
	
	
//--------------------------------------------------//
//---------------BEGIN OF GET METHODS---------------//
//--------------------------------------------------//
	public Player getCurrentPlayer() {
		return this.currentPlayer;		
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
//--------------------------------------------------//
//----------------END OF GET METHODS----------------//
//--------------------------------------------------//


	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}
}
