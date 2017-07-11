package it.polimi.ingsw.ps46;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.card.IncreaseResourcesEffect;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
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

public class MalusTest {
	
	Game game = new Game(2);
	ResourceSet playerResourceSet;
	ResourceSet immediateCardResources;
	ResourceSet permanentCardResources;
	ResourceSet playerMalus;
	
	private TerritoryCard territoryCard;
	private DecreaseResourcesMalus malus;
	private static final int INTVALUE0 = 0;
	private static final int INTVALUE1 = 1;
	private static final int INTVALUE2 = 2;
	private static final int INTVALUE3 = 3;

	@Before
	public void setUp() {
	
	}
	
	@Test
	public void decreaseMalusTest() {
		
		game.setCurrentPlayer(game.getPlayers().get(0));
		
	ArrayList<Resource> resourcesList = new ArrayList<>();
			
	
	
	//CREATING RESOURCESET FOR PLAYER RESOURCESET//
	//////////////////////////////////////////////
	game.setCurrentPlayer(game.getPlayers().get(0));
	resourcesList = new ArrayList<>();
	
	resourcesList.add(new Wood				(INTVALUE2));
	resourcesList.add(new Stones			(INTVALUE2));
	resourcesList.add(new Servants			(INTVALUE2));
	resourcesList.add(new Money				(INTVALUE2));
	resourcesList.add(new FaithPoints		(INTVALUE2));
	resourcesList.add(new MilitaryPoints	(INTVALUE2));
	resourcesList.add(new VictoryPoints		(INTVALUE2));
	resourcesList.add(new CouncilPrivilege	(INTVALUE2));
	
	System.out.println("STAMPO: ||resourcesList||" + "\n" );
	for (Resource resource : resourcesList) {
		System.out.println("Tipo di risorsa: " + resource.getId() + " || " + "Quantità: " + resource.getQuantity());	
	}
	
	playerResourceSet = new ResourceSet(resourcesList);
	
	//assegno al player il resourceSet.
	game.getCurrentPlayer().getPersonalBoard().setResources(playerResourceSet);
	
	
	System.out.println("\n" + "STAMPO: ||playerResourceSet||" + "\n" );
	System.out.println( playerResourceSet.toString());	
		
	
	//CREATING RESOURCESET FOR CARD IMMEDIATE EFFECT//
	/////////////////////////////////////////////////
	
	resourcesList.clear();
	resourcesList.add(new Wood				(INTVALUE3));
	resourcesList.add(new Stones			(INTVALUE3));
	resourcesList.add(new Servants			(INTVALUE3));
	resourcesList.add(new Money				(INTVALUE3));
	resourcesList.add(new FaithPoints		(INTVALUE3));
	resourcesList.add(new MilitaryPoints	(INTVALUE3));
	resourcesList.add(new VictoryPoints		(INTVALUE3));
	resourcesList.add(new CouncilPrivilege	(INTVALUE3));
	
	immediateCardResources = new ResourceSet(resourcesList);
	
	System.out.println("\n" + "STAMPO: ||immediateCardResources||");
	System.out.println( immediateCardResources.toString() + "\n");	
	
	
	//CREATING RESOURCESET FOR CARD PERMANENT EFFECT//
	/////////////////////////////////////////////////
	resourcesList.clear();
	resourcesList.add(new Wood				(INTVALUE0));
	resourcesList.add(new Stones			(INTVALUE0));
	resourcesList.add(new Servants			(INTVALUE0));
	resourcesList.add(new Money				(INTVALUE0));
	resourcesList.add(new FaithPoints		(INTVALUE0));
	resourcesList.add(new MilitaryPoints	(INTVALUE0));
	resourcesList.add(new VictoryPoints		(INTVALUE0));
	resourcesList.add(new CouncilPrivilege	(INTVALUE0));
	
	permanentCardResources = new ResourceSet(resourcesList);
	
	System.out.println("\n" + "STAMPO: ||permanentCardResources||");
	System.out.println( permanentCardResources.toString() + "\n");	
		
	
	
	//CREATING RESOURCESET & SETTING EXCOMMUNICATION MALUS DECREASE RESOURCES MALUS//
	////////////////////////////////////////////////////////////////////////////////
	resourcesList.clear();
	resourcesList.add(new Wood				(INTVALUE1));
	resourcesList.add(new Stones			(INTVALUE1));
	resourcesList.add(new Servants			(INTVALUE0));
	resourcesList.add(new Money				(2));
	resourcesList.add(new FaithPoints		(2));
	resourcesList.add(new MilitaryPoints	(INTVALUE0));
	resourcesList.add(new VictoryPoints		(INTVALUE0));
	resourcesList.add(new CouncilPrivilege	(INTVALUE0));
	
	ResourceSet temporaryResourcetForMalus = new ResourceSet(resourcesList);
	
	malus = new DecreaseResourcesMalus("DecreaseResourcesMalus", temporaryResourcetForMalus);
	
	System.out.println("\n" + "STAMPO: ||malus resources||");
	System.out.println( malus.getDecreasedResources().toString() + "\n");	
	
	
	/////////SETTING MALUS EFFECT///////////////
	////////////////////////////////////////////
	
	
	game.getCurrentPlayer().setDecreaseResourcesMalus(malus);
	
	System.out.println("Nome del Malus del giocatore: " + game.getCurrentPlayer().getDecreaseResourcesMalus().getName());
	System.out.println(game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources().toString());
	
	System.out.println("\n----Fine controllo del Malus----\n\n\n");
	
	
	
	//SETTING CARD TERRITORY PROPERTIES & EFFECTS//
	//////////////////////////////////////////////
	IncreaseResourcesEffect increaseResourcesImmEffect = new IncreaseResourcesEffect(immediateCardResources);
	IncreaseResourcesEffect increaseResourcesPerEffect = new IncreaseResourcesEffect(permanentCardResources);
	
	territoryCard = new TerritoryCard("TerritoryCard", 1, increaseResourcesImmEffect, increaseResourcesPerEffect, playerResourceSet, new Dice()){};
	game.getCurrentPlayer().getPersonalBoard().putTerritoryCardInPlayerSet(territoryCard);
	
	System.out.println("\n----PLAYER TERRITORY CARDs----");
	System.out.println("Numero di carte Territorio: " + game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck().size() +"\n");
	
	int j = 1;
	for (TerritoryCard territoryCard : game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck()) {
		System.out.println("Carta n°"+ j +"\nEra:"+ territoryCard.getCardEra() +"\nNome: " + territoryCard.getCardName() +
	"\nValore del Dado: " + territoryCard.getHarvestValue().toString());
	}
	System.out.println("\n----Fine controllo della lista carte territorio----");	
	System.out.println(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString());
	
	
	System.out.println("\n----FINE SETTAGGIO----\n\n\n");	
	System.out.println("\n----INIZIO TESTING----");		
			
			
	ResourceSet clone = new ResourceSet(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet());
		
	System.out.println("\n" + "STAMPO: ||IL CLONE DEL playerResourceSet||" );
	System.out.println( clone.toString() + "\n" + 
	"____________________________________________________________________________________________________________________");
			
			
	System.out.println("\n" + "STAMPO: ||playerResourceSet|| PRIMA l'attivazione dell'effetto" );
	System.out.println( game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString() + "\n" + 
	"____________________________________________________________________________________________________________________");	
			
	for (TerritoryCard territoryCard : game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck()) {
		territoryCard.getImmediateEffects().activateEffect(game);
	}
			
	System.out.println("\n" + "STAMPO: ||playerResourceSet|| DOPO l'attivazione dell'effetto Immediato( prima volta)" );
	System.out.println( game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString() + "\n");
	
	int index = 0;
	
	for (String string : game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().keySet()) {
		if (index == 0){
			Assert.assertEquals(string, 4, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
			}
		else if (index == 1){
			Assert.assertEquals(string, 4, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
			}
		else if (index == 2){
			Assert.assertEquals(string, 5, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
			} 
		else if (index == 3){
			Assert.assertEquals(string, 3, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
			}
		else if (index == 4){
			Assert.assertEquals(string, 3, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
			}
		else if (index == 5){
			Assert.assertEquals(string, 5, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
			}
		else if (index == 6){
			Assert.assertEquals(string, 5, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
			}
		else if (index == 7){
			Assert.assertEquals(string, 5, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().getResourcesMap().get(string).getQuantity());
			}
		index++;
		}
	}
}
