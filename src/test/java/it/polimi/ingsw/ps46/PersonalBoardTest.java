package it.polimi.ingsw.ps46;

import static org.junit.Assert.*;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.PersonalBoard;
import it.polimi.ingsw.ps46.server.Player;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.FactoryCards;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;

public class PersonalBoardTest {

	private ArrayList<TerritoryCard> territoryCardsDeck;
	private ArrayList<BuildingCard> buildingCardsDeck;
	private ArrayList<CharacterCard> characterCardsDeck;
	private ArrayList<VentureCard> ventureCardsDeck;
	Game game = new Game(2);
	Player player = new Player(1);
	
	private final int PERIODS = 3;
	boolean result;
	
	int period;
	
	
	
	public PersonalBoardTest() {
		
		
		player = game.getPlayers().get(0);
		game.setCurrentPlayer(player);
		
		
		FactoryCards factoryCards = FactoryCards.getFactoryCards();
		territoryCardsDeck = factoryCards.createTerritoryCards("TerritoryCards.json");
		for(period = 1; period <= PERIODS; period++) {
			Collections.shuffle(territoryCardsDeck.subList((territoryCardsDeck.size()/PERIODS)*(period-1), 
					(territoryCardsDeck.size()/PERIODS)*period));
		}
			
		buildingCardsDeck = factoryCards.createBuildingCards("BuildingCards.json");
		for(period = 1; period <= PERIODS; period++) {
			Collections.shuffle(buildingCardsDeck.subList((buildingCardsDeck.size()/PERIODS)*(period-1), 
					(buildingCardsDeck.size()/PERIODS)*period));
		}
		
		ventureCardsDeck = factoryCards.createVentureCards("VentureCards.json");
		for(int period = 1; period <= PERIODS; period++) {
			Collections.shuffle(ventureCardsDeck.subList((ventureCardsDeck.size()/PERIODS)*(period-1), 
					(ventureCardsDeck.size()/PERIODS)*period));
		}
		
		characterCardsDeck = factoryCards.createCharacterCards("CharacterCards.json");
		for(int period = 1; period <= PERIODS; period++) {
			Collections.shuffle(characterCardsDeck.subList((characterCardsDeck.size()/PERIODS)*(period-1), 
					(characterCardsDeck.size()/PERIODS)*period));
		}	
		
	}
	
	/**
	 * Verifies the behaviour of putCardInPlayerSet method
	 */
	
	@Test
	public void maxCardsTest() {
		
		
		int x = 1;

		
		for (TerritoryCard tcard : territoryCardsDeck) {
			
			result = game.getCurrentPlayer().getPersonalBoard().putTerritoryCardInPlayerSet(tcard);
			
			
			if ( x < 7) {
			
			
				assertTrue(result);
				
				x ++;
			
			} else {
				
				assertFalse(result);
			}
			
		}
		
		x = 1;
		
	
		
		result = true;
		for (BuildingCard bcard : buildingCardsDeck) {
			
			result = game.getCurrentPlayer().getPersonalBoard().putBuildingCardInPlayerSet(bcard);
			
			if ( x < 7) {
			
				
				assertTrue(result);
				
				x ++;
			
			} else {
				
				assertFalse(result);
			
			}
			
		}
		
		x = 1;
		
		
		
		result = true;
		for (CharacterCard card : characterCardsDeck) {
			
			result = game.getCurrentPlayer().getPersonalBoard().putCharacterCardInPlayerSet(card);
			
			if ( x < 7) {
			
				
				assertTrue(result);
				
				x ++;
			
			
			} else {
				
				assertFalse(result);
			
			}
			
		}
		
		x = 1;

		
		result = true;
		
		for (VentureCard card : ventureCardsDeck) {
			
			result = game.getCurrentPlayer().getPersonalBoard().putVentureCardInPlayerSet(card);
			
			if ( x < 7) {
			
				
				assertTrue(result);
				
				x ++;
			
			} else {
				
				assertFalse(result);
			}
			
		}
	}
	
	
	
	@Test
	public void testing() {
		
	}
	
}
