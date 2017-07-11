package it.polimi.ingsw.ps46;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.CharacterCard;
import it.polimi.ingsw.ps46.server.card.FactoryCards;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;


public class CardTest {
	
	private ArrayList <TerritoryCard> territoryCardsDeck;
	private ArrayList <BuildingCard> buildingCardsDeck;
	private ArrayList <CharacterCard> characterCardsDeck;
	private ArrayList <VentureCard> ventureCardsDeck;
	private final int PERIODS = 3;

	
	@Test
	public void cardParsingTest() {
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
		
		
		int deckSize = 0;
		
		deckSize += territoryCardsDeck.size();
		assertEquals(24, deckSize);

		deckSize += buildingCardsDeck.size();
		assertEquals(48, deckSize);

		deckSize += characterCardsDeck.size();
		assertEquals(72, deckSize);

		deckSize += ventureCardsDeck.size();
		assertEquals(96, deckSize);
		
		int era1 = 0;
		int era2 = 0;
		int era3 = 0;
		for (BuildingCard buildingCard : buildingCardsDeck) {
			if (buildingCard.getCardEra() == 1)
				era1++;
			if (buildingCard.getCardEra() == 2)
				era2++;
			if (buildingCard.getCardEra() == 3)
				era3++;
		}
		assertEquals(8, era1);
		assertEquals(8, era2);
		assertEquals(8, era3);
		
		era1 = 0;
		era2 = 0;
		era3 = 0;
		for (TerritoryCard territoryCard : territoryCardsDeck) {
			if (territoryCard.getCardEra() == 1)
				era1++;
			if (territoryCard.getCardEra() == 2)
				era2++;
			if (territoryCard.getCardEra() == 3)
				era3++;
		}
		assertEquals(8, era1);
		assertEquals(8, era2);
		assertEquals(8, era3);
		
		era1 = 0;
		era2 = 0;
		era3 = 0;
		for (CharacterCard characterCard : characterCardsDeck) {
			if (characterCard.getCardEra() == 1)
				era1++;
			if (characterCard.getCardEra() == 2)
				era2++;
			if (characterCard.getCardEra() == 3)
				era3++;
		}
		assertEquals(8, era1);
		assertEquals(8, era2);
		assertEquals(8, era3);
		
		era1 = 0;
		era2 = 0;
		era3 = 0;
		for (VentureCard ventureCard : ventureCardsDeck) {
			if (ventureCard.getCardEra() == 1)
				era1++;
			if (ventureCard.getCardEra() == 2)
				era2++;
			if (ventureCard.getCardEra() == 3)
				era3++;
		}
		assertEquals(8, era1);
		assertEquals(8, era2);
		assertEquals(8, era3);
	}
}
