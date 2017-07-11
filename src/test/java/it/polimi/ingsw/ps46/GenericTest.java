package it.polimi.ingsw.ps46;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.card.ExchageResourcesEffect;
import it.polimi.ingsw.ps46.server.card.IncreaseResourcesByElementsEffect;
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


/**
 * TESTING CLASS MalusDecreaseTEST.
 * per settare la classe in modo che funzioni bisogna cambiare il costruttore di Game a public e
 * aggiungere questa linea di codice nel costruttore di Game
 * 			
 * 
 * @author Andrea.Masi
 */


public class GenericTest {

	private Game game = new Game(2);
	private ResourceSet playerResourceSet;

	
	private ResourceSet immediateCardResources;
	private ResourceSet permanentCardResources;

	private TerritoryCard territoryCard;
	private BuildingCard buildingCard;
	private DecreaseResourcesMalus malus;
	private static final int INTVALUE0 = 0;
	private static final int INTVALUE1 = 1;
	private static final int INTVALUE2 = 2;
	private static final int INTVALUE3 = 3;
	private static final int INTVALUE4 = 4;

	
	/**
	 * Constructor of the TEST class MalusDecreaseTEST
	 */
	public GenericTest() {
		
		
		//CREATING RESOURCESET FOR PLAYER RESOURCESET//
		//////////////////////////////////////////////
		game.setCurrentPlayer(game.getPlayers().get(0));
		ArrayList<Resource> resourcesList = new ArrayList<>();
		
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
		resourcesList.add(new Money				(INTVALUE0));
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
		resourcesList.add(new Stones			(INTVALUE0));
		resourcesList.add(new Servants			(INTVALUE0));
		resourcesList.add(new Money				(INTVALUE0));
		resourcesList.add(new FaithPoints		(INTVALUE0));
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
	
		
		
		
		
		
		
		
		
		//SETTING CARD BUILDING PROPERTIES & EFFECTS//
		//////////////////////////////////////////////	
		
		boolean doubleChoise = false;
		
		resourcesList.clear();
		resourcesList.add(new Wood				(INTVALUE2));
		resourcesList.add(new Stones			(INTVALUE2));
		resourcesList.add(new Servants			(INTVALUE2));
		resourcesList.add(new Money				(INTVALUE2));
		resourcesList.add(new FaithPoints		(INTVALUE2));
		resourcesList.add(new MilitaryPoints	(INTVALUE2));
		resourcesList.add(new VictoryPoints		(INTVALUE2));
		resourcesList.add(new CouncilPrivilege	(INTVALUE2));
		
		ResourceSet requiredResources = new ResourceSet(resourcesList);

		resourcesList.clear();
		resourcesList.add(new Wood				(INTVALUE2));
		resourcesList.add(new Stones			(INTVALUE2));
		resourcesList.add(new Servants			(INTVALUE2));
		resourcesList.add(new Money				(INTVALUE2));
		resourcesList.add(new FaithPoints		(INTVALUE2));
		resourcesList.add(new MilitaryPoints	(INTVALUE2));
		resourcesList.add(new VictoryPoints		(INTVALUE2));
		resourcesList.add(new CouncilPrivilege	(INTVALUE2));

		ResourceSet gainedResources = new ResourceSet(resourcesList);

		System.out.println("\n" + "STAMPO: ||REQUIRED resources||" + "\n" );
		System.out.println( requiredResources.toString());	
		System.out.println("\n" + "STAMPO: ||GAINED resources||" + "\n" );
		System.out.println( gainedResources.toString());	
		
		IncreaseResourcesByElementsEffect increaseResourcesByElementsEffect = new IncreaseResourcesByElementsEffect(immediateCardResources, "TerritoryCards");
		ExchageResourcesEffect exchageResourcesEffect = new ExchageResourcesEffect(requiredResources, gainedResources);
	
		buildingCard = new BuildingCard("BuildingCard", 1, increaseResourcesByElementsEffect, doubleChoise, exchageResourcesEffect, new ExchageResourcesEffect(immediateCardResources, permanentCardResources), playerResourceSet, new Dice()){};
		game.getCurrentPlayer().getPersonalBoard().putBuildingCardInPlayerSet(buildingCard);

		System.out.println("\n----PLAYER BUILDING CARDs----\n");
		System.out.println("Numero di carte Building: " + game.getCurrentPlayer().getPersonalBoard().getBuildingDeck().size() +"\n");

		int m = 1;
		for (BuildingCard buildingCard : game.getCurrentPlayer().getPersonalBoard().getBuildingDeck()) {
			System.out.println("Carta n°"+ m +"\nEra:"+ buildingCard.getCardEra() +"\nNome: " + buildingCard.getCardName() +
		"\nValore del Dado: " + buildingCard.getProductionValue().toString());
		}
		System.out.println("\n----Fine controllo della lista carte edificio----");

	};
		
	
	//--------------------------------------------------//
	//-----------------START TESTING METHOD-------------//
	//--------------------------------------------------//
	
	public void testingMethod(){
		game.setCurrentPlayer(game.getPlayers().get(0));

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
		
		for (TerritoryCard territoryCard : game.getCurrentPlayer().getPersonalBoard().getTerritoryDeck()) {
			territoryCard.getImmediateEffects().activateEffect(game);
		}
		
		System.out.println("\n" + "STAMPO: ||playerResourceSet|| DOPO l'attivazione dell'effetto Immediato (seconda volta)" );
		System.out.println( game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString() + "\n");	
		
		/*
		for (BuildingCard buildingCard : game.getCurrentPlayer().getPersonalBoard().getBuildingDeck()) {
				buildingCard.getImmediateEffects().activateEffect(game);	
		}
		
		System.out.println("\n" + "STAMPO: ||playerResourceSet|| DOPO l'attivazione dell'effetto" );
		System.out.println( game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString() + "\n" + 
		"____________________________________________________________________________________________________________________");
		
		ResourceSet differenceResourceSet = new ResourceSet(clone, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet());
		
		System.out.println("\n" + "STAMPO: ||differenceResourceSet|| " );
		System.out.println( differenceResourceSet.toString() + "\n" + 
		"____________________________________________________________________________________________________________________");
		
		
		System.out.println("\n" + "STAMPO: ||IL CLONE DEL playerResourceSet||" );
		System.out.println( clone.toString() + "\n" + 
		"____________________________________________________________________________________________________________________");
		*/
		
		ArrayList<Integer> playerOrderForVictoryPoints = new ArrayList<>(4);
		
			playerOrderForVictoryPoints.add(12);
			playerOrderForVictoryPoints.add(7);
			playerOrderForVictoryPoints.add(44);
			playerOrderForVictoryPoints.add(12);
			
			System.out.println("PRIMA: " + playerOrderForVictoryPoints.toString());

			Collections.sort(playerOrderForVictoryPoints);
			Collections.reverse(playerOrderForVictoryPoints);
			
			System.out.println("DOPO: " + playerOrderForVictoryPoints.toString());
			
			LinkedHashMap< Integer, VictoryPoints> finalOrderPlusVP = new LinkedHashMap<>();
			
			int indexVictoryPointsForMilitaryPoints = 0;
			
			for (int i = 0; i < game.getPlayers().size(); i++){
				if(indexVictoryPointsForMilitaryPoints < game.getVictoryPointsForMilitaryPoints().size()){
					System.out.println("grandezza dell'arrayList VictoryPointsForMilitaryPoints: " + game.getVictoryPointsForMilitaryPoints().size());
					if (playerOrderForVictoryPoints.get(i) != playerOrderForVictoryPoints.get(i+1)) {
						System.out.println("dentro al IF (culo di alessia) con index: " + i);
						System.out.println("dentro al IF (culo di alessia) con indexVictoryPointsForMilitaryPoints: " + indexVictoryPointsForMilitaryPoints);

						finalOrderPlusVP.put(i, game.getVictoryPointsForMilitaryPoints().get(indexVictoryPointsForMilitaryPoints));
							indexVictoryPointsForMilitaryPoints++;
					}
					else 
					{
						System.out.println("dentro al ELSE (culo di alessia) con index: " + i);
						System.out.println("dentro al ELSE (culo di alessia) con indexVictoryPointsForMilitaryPoints: " + indexVictoryPointsForMilitaryPoints);
					}

						finalOrderPlusVP.put(i, game.getVictoryPointsForMilitaryPoints().get(indexVictoryPointsForMilitaryPoints));
				}
			}
			System.out.println("DOPODOPO: " + finalOrderPlusVP.toString());

			
		/*
		
		System.out.println("//--------------------------------------------------//");
		System.out.println("//----------------TESTING SANTA RITA----------------//");
		System.out.println("//--------------------------------------------------//");
		
		//TODO andrebbero aggiunti solo money, stone, wood, servants x2 FATTO
		if(true){
			//salvo il resourceSet iniziale del player
			ResourceSet temporaryResourceSet = new ResourceSet(game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet());
			
			System.out.println("\n" + "STAMPO: ||playerResourceSet|| PRIMA l'attivazione dell'effetto" );
			System.out.println( game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString() + "\n" + 
			"____________________________________________________________________________________________________________________");
			
			System.out.println("\n" + "STAMPO: ||IL CLONE DEL playerResourceSet||" );
			System.out.println( temporaryResourceSet.toString() + "\n" + 
			"____________________________________________________________________________________________________________________");
			//colleziono la carta 
			territoryCard.collectCard(game);
			
			System.out.println("\n" + "STAMPO: ||playerResourceSet|| DOPO il collect Card" );
			System.out.println( game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString() + "\n" + 
			"____________________________________________________________________________________________________________________");
			
							
			//vedo che cosa è stato aumentato dall'effetto immediato della carta.
			ResourceSet difference = new ResourceSet(temporaryResourceSet, game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet());
				
			System.out.println("\n" + "STAMPO: ||La DIFFERENZA tra playerResourceSetCLONED & playerResourceSet|| " );
			System.out.println( difference.toString() + "\n" + 
				"____________________________________________________________________________________________________________________");
				
				//se ho dei malus...aggiungo ciò che è stato tolto dal malus
				if (game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources() != null){
					
					difference.add(game.getCurrentPlayer().getDecreaseResourcesMalus().getDecreasedResources());
					
					System.out.println("\n" + "STAMPO: ||La DIFFERENZA sommata al MALUS|| " );
					System.out.println( difference.toString() + "\n" + 
					"____________________________________________________________________________________________________________________");
				}
				
				for (String key : difference.getResourcesMap().keySet()) {
					if((key != "Wood") && (key != "Stones") && (key != "Money") && ( key != "Servants")) {
						difference.getResourcesMap().get(key).setQuantity(0);
					}
				}
				
				System.out.println("\n" + "STAMPO: ||La DIFFERENZA dopo aver messo gli ZERI|| " );
				System.out.println( difference.toString() + "\n" + 
				"____________________________________________________________________________________________________________________");
				
				game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().add(difference);
				
				System.out.println("\n" + "STAMPO: ||playerResourceSet|| FINALE" );
				System.out.println( game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString() + "\n" + 
				"____________________________________________________________________________________________________________________");
			
		}
		else{
			territoryCard.collectCard(game);
			System.out.println("\n" + "STAMPO: ||playerResourceSet|| FINALE" );
			System.out.println( game.getCurrentPlayer().getPersonalBoard().getPlayerResourceSet().toString() + "\n" + 
			"____________________________________________________________________________________________________________________");
		}	
		
		int primo = 1;
		int resto = 0;
		
		System.out.println("primo:" + primo);
		primo /= 2;
		System.out.println("primo:" + primo);
		resto = primo % 2;
		System.out.println("resto:" + resto);
		primo*= 2;
		System.out.println("moltiplicando:" + primo);
	*/

		
	}
	
	
	
	
	
	
	

	//--------------------------------------------------//
	//-----------------MAIN METHOD----------------------//
	//--------------------------------------------------//
	
	public static void main(String[] args) {
	
		GenericTest test = new GenericTest();
		test.testingMethod();
	}
	
	
	
	//-------------------------------------------------------------//
	//-----------------GETTER & SETTER METHODS---------------------//
	//-------------------------------------------------------------//

	public ResourceSet getAdditionalCardResources() {
		return immediateCardResources;
	}

	public void setAdditionalCardResources(ResourceSet additionalCardResources) {
		this.immediateCardResources = additionalCardResources;
	}

	public ResourceSet getPlayerResourceSet() {
		return playerResourceSet;
	}

	public void setPlayerResourceSet(ResourceSet playerResourceSet) {
		this.playerResourceSet = playerResourceSet;
	}

	public DecreaseResourcesMalus getMalus() {
		return malus;
	}

	public void setMalus(DecreaseResourcesMalus malus) {
		this.malus = malus;
	}

	
	public TerritoryCard getTerritoryCard() {
		return territoryCard;
	}


	public void setTerritoryCard(TerritoryCard territoryCard) {
		this.territoryCard = territoryCard;
	}

}
