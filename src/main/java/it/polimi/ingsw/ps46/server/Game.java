package it.polimi.ingsw.ps46.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;


public class Game extends Observable {
	
	private Integer numberPlayers; 
	private Integer roundsPerPeriod;
	private Integer periods;
	
	private List<Player> players;
	private Player currentPlayer;
	private Board board;
	private Set<TerritoryCard> territoryCards;
	private Set<BuildingCard> buildingCards;
	private Set<CharacterCard> characterCards;
	private Set<VentureCard> ventureCards;
	private Dice orangeDice;
	private Dice blackDice;
	private Dice whiteDice;
	
	
	Game(Integer numberPlayers) {
		this.numberPlayers = numberPlayers;
		//creates the players objects and adds them to the list of players
		for(int idPlayer = 1; idPlayer<=numberPlayers; idPlayer++) {
			players.add(new Player(idPlayer));
		}
		configGame();
		configBoard();
		configDecks();
	}

	
	private void configGame() {
		//temporano, poi le configurazioni verranno lette da file
		periods = 3;
		roundsPerPeriod = 2;
		
	}
	
	private void configBoard() {
		board = new Board();
		//lettura di file di configurazione
	}
	
	private void configDecks() {
		//lettura di file di configurazione e costruzione della mappa tipologiaDiCarta-directoryFile 
		//che verrà poi utilizzata per chiamare un metodo configCards per ogni tipologia di carta
		
	}
	
	private void configTerritoryCards() throws FileNotFoundException {
		territoryCards = new HashSet<TerritoryCard>();
		TerritoryCard[] t = null;
	
	    Gson gson = new Gson();
	    JsonReader jsonReader = new JsonReader(new FileReader("provaCard.json"));
	    t = gson.fromJson(jsonReader, TerritoryCard[].class);
	
	    for (TerritoryCard territoryCard : t) {
	    	territoryCards.add(territoryCard);
	        System.out.println(territoryCard);
	    }
	}
	
	private void configCards() throws FileNotFoundException {
		////lettura di file di configurazione e costruzione delle carte tramite Factory Pattern
		
		
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;		
	}

}
