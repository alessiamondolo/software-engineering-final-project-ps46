package it.polimi.ingsw.ps46;


import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.FactoryCards;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.card.VentureCard;
import it.polimi.ingsw.ps46.server.resources.CouncilPrivilege;
import it.polimi.ingsw.ps46.server.resources.FaithPoints;
import it.polimi.ingsw.ps46.server.resources.MilitaryPoints;
import it.polimi.ingsw.ps46.server.resources.Money;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.Servants;
import it.polimi.ingsw.ps46.server.resources.Stones;
import it.polimi.ingsw.ps46.server.resources.VictoryPoints;
import it.polimi.ingsw.ps46.server.resources.Wood;

public class IncreaseResourcesTest {

		private Game game = new Game(2);
		private ResourceSet playerResourceSet;
		private ArrayList <TerritoryCard> territoryCardsDeck;	
		private ArrayList <BuildingCard> buildingCardsDeck;
		private ArrayList <VentureCard> ventureCardsDeck;



		
		private static final int INTVALUE0 = 0;
		private static final int INTVALUE1 = 1;
		private static final int INTVALUE2 = 2;
		private static final int INTVALUE3 = 3;
		private static final int INTVALUE4 = 4;
		private final int PERIODS = 3;

		
		@Before
		public void setUp() {
			ArrayList<Resource> resourcesList = new ArrayList<>();
			resourcesList.add(new Wood				(INTVALUE0));
			resourcesList.add(new Stones			(INTVALUE0));
			resourcesList.add(new Servants			(INTVALUE0));
			resourcesList.add(new Money				(INTVALUE0));
			resourcesList.add(new FaithPoints		(INTVALUE0));
			resourcesList.add(new MilitaryPoints	(INTVALUE0));
			resourcesList.add(new VictoryPoints		(INTVALUE0));
			resourcesList.add(new CouncilPrivilege	(INTVALUE0));
			
			playerResourceSet = new ResourceSet(resourcesList);
		}
		
		
		@Test
		public void increaseResourcesOnCards1Test() {	
			
			game.setCurrentPlayer(game.getPlayers().get(0));
			game.getCurrentPlayer().getPersonalBoard().setResources(playerResourceSet);

			FactoryCards factoryCards = FactoryCards.getFactoryCards();
			
			territoryCardsDeck = factoryCards.createTerritoryCards("TerritoryCards.json");
			for(int period = 1; period <= PERIODS; period++) {
				Collections.shuffle(territoryCardsDeck.subList((territoryCardsDeck.size()/PERIODS)*(period-1), 
						(territoryCardsDeck.size()/PERIODS)*period));
				}			
			for (TerritoryCard territoryCard : territoryCardsDeck) {
				territoryCard.getImmediateEffects().activateEffect(game);
			}
			/*
			 * RESOURCES GOT BY THE IMMEDIATE EFFECT OF THE TERRITORY CARDS
			 * 	Wood: 5		
			 *	Stones: 4	
			 *	Servants: 8		
			 *	Money: 11			
			 *	FaithPoints: 2	
			 *	MilitaryPoints: 6
			 *	VictoryPoints: 6
			 * 	CouncilPrivilege: 1	
			 */			
			
			System.out.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
			int index = 0;
			
			for (String string : game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().keySet()) {
				if (index == 0){
					Assert.assertEquals(string, 5, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 1){
					Assert.assertEquals(string, 4, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 2){
					Assert.assertEquals(string, 8, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					} 
				else if (index == 3){
					Assert.assertEquals(string, 11, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 4){
					Assert.assertEquals(string, 2, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 5){
					Assert.assertEquals(string, 6, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 6){
					Assert.assertEquals(string, 6, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 7){
					Assert.assertEquals(string, 1, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				index++;
			}
			System.out.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
		}
		
		
		
		@Test
		public void increaseResourcesOnCards2Test() {	
			/*
			 * RESOURCES GOT BY THE IMMEDIATE EFFECT OF THE TERRITORY CARDS		
			 *	FaithPoints: 6
			 *	VictoryPoints: 125
			 */
			game.setCurrentPlayer(game.getPlayers().get(0));
			game.getCurrentPlayer().getPersonalBoard().setResources(playerResourceSet);

			FactoryCards factoryCards = FactoryCards.getFactoryCards();

			buildingCardsDeck = factoryCards.createBuildingCards("BuildingCards.json");
			for(int period = 1; period <= PERIODS; period++) {
				Collections.shuffle(buildingCardsDeck.subList((buildingCardsDeck.size()/PERIODS)*(period-1), 
						(buildingCardsDeck.size()/PERIODS)*period));
			}
			
			for (BuildingCard buildingCard : buildingCardsDeck) {
				buildingCard.getImmediateEffects().activateEffect(game);
			}
			System.out.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
			int index = 0;
			
			for (String string : game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().keySet()) {
				if (index == 6){
					Assert.assertEquals(string, 125, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 4){
					Assert.assertEquals(string, 6, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				index++;
			}
			System.out.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
		}
		
		
		@Test
		public void increaseResourcesOnCards3Test() {	
			/* RESOURCES GOT BY THE IMMEDIATE EFFECT OF THE TERRITORY CARDS
			 * 	Wood: 3		
			 *	Stones: 3	
			 *	Servants: 9	
			 *	Money: 16		
			 *	FaithPoints: 21
			 *	MilitaryPoints: 27
			 *	VictoryPoints: 0
			 * 	CouncilPrivilege: 6	
			 */
			game.setCurrentPlayer(game.getPlayers().get(0));
			game.getCurrentPlayer().getPersonalBoard().setResources(playerResourceSet);

			FactoryCards factoryCards = FactoryCards.getFactoryCards();

			ventureCardsDeck = factoryCards.createVentureCards("VentureCards.json");
			for(int period = 1; period <= PERIODS; period++) {
				Collections.shuffle(ventureCardsDeck.subList((ventureCardsDeck.size()/PERIODS)*(period-1), 
						(ventureCardsDeck.size()/PERIODS)*period));
			}

			for (VentureCard ventureCard : ventureCardsDeck) {
				ventureCard.getImmediateEffects().activateEffect(game);
			}
			
			
			System.out.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
			int index = 0;
			
			for (String string : game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().keySet()) {
				if (index == 0){
					Assert.assertEquals(string, 3, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 1){
					Assert.assertEquals(string, 3, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 2){
					Assert.assertEquals(string, 9, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					} 
				else if (index == 3){
					Assert.assertEquals(string, 16, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 4){
					Assert.assertEquals(string, 21, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 5){
					Assert.assertEquals(string, 27, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 6){
					Assert.assertEquals(string, 0, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				else if (index == 7){
					Assert.assertEquals(string, 6, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
					}
				index++;
			}
			System.out.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
		}
		
}
