package it.polimi.ingsw.ps46.server;

import java.util.List;
import java.util.Observable;
import java.util.Set;
import it.polimi.ingsw.ps46.server.card.Card;

public class Game extends Observable {
	
	private Integer numberPlayers; 
	private Integer roundsPerPeriod;
	private Integer periods;
	
	private List<Player> players;
	private Player currentPlayer;
	private Board board;
	private Set<Card> cards;
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
		configCards();
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
	
	private void configCards() {
		//lettura di file di configurazione e costruzione carte tramite Factory Pattern
	}
	
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;		
	}

}
