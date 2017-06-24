package it.polimi.ingsw.ps46;

import java.util.ArrayList;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.DecreaseResourcesMalus;
import it.polimi.ingsw.ps46.server.card.ExchageResourcesEffect;
import it.polimi.ingsw.ps46.server.card.IncreaseResourcesEffect;
import it.polimi.ingsw.ps46.server.card.TerritoryCard;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/**
 * TESTING CLASS MalusDecreaseTEST.
 * per settare la classe in modo che funzioni bisogna cambiare il costruttore di Game a public e
 * aggiungere questa linea di codice nel costruttore di Game
 * ///
 *  currentPlayer = new Player(1); // AGGIUNTO PER IL TESTING DA TOGLIERE//
 * ///
 * 
 * @author Andrea.Masi
 */


public class MalusDecreaseTEST {

	private Game game;
	private ResourceSet playerResourceSet;

	
	private ResourceSet immidiateCardResources;
	private ResourceSet permanentCardResources;

	private TerritoryCard territoryCard;
	private BuildingCard buildingCard;
	private DecreaseResourcesMalus malus;
	private static final int INTVALUE0 = 0;
	private static final int INTVALUE1 = 1;
	private static final int INTVALUE2 = 2;
	private static final int INTVALUE3 = 3;

	
	/**
	 * Constructor of the TEST class MalusDecreaseTEST
	 */
	public MalusDecreaseTEST() {
		
		game = new Game(2);
		
		//CREATING RESOURCESET FOR PLAYER RESOURCESET//
		//////////////////////////////////////////////
		
		ArrayList<Resource> resourcesList = new ArrayList<>();
		
		resourcesList.add(new Resource("Wood", 				INTVALUE2){});
		resourcesList.add(new Resource("Stones", 			INTVALUE2){});
		resourcesList.add(new Resource("Servants", 			INTVALUE2){});
		resourcesList.add(new Resource("Money", 			INTVALUE2){});
		resourcesList.add(new Resource("FaithPoints", 		INTVALUE2){});
		resourcesList.add(new Resource("MilitaryPoints", 	INTVALUE2){});
		resourcesList.add(new Resource("VictoryPoints", 	INTVALUE2){});
		resourcesList.add(new Resource("CounsilPrivilege", 	INTVALUE2){});
		
		System.out.println("STAMPO: ||resourcesList||" + "\n" );
		for (Resource resource : resourcesList) {
			System.out.println("Tipo di risorsa: " + resource.getId() + " || " + "Quantità: " + resource.getQuantity());	
		}
		
		playerResourceSet = new ResourceSet(resourcesList);

		System.out.println("\n" + "STAMPO: ||playerResourceSet||" + "\n" );
		System.out.println( playerResourceSet.toString());	
			
		
		//CREATING RESOURCESET FOR CARD IMMEDIATE EFFECT//
		/////////////////////////////////////////////////
		
		resourcesList.clear();
		resourcesList.add(new Resource("Wood", 				INTVALUE0){});
		resourcesList.add(new Resource("Stones", 			INTVALUE0){});
		resourcesList.add(new Resource("Servants", 			INTVALUE0){});
		resourcesList.add(new Resource("Money", 			INTVALUE0){});
		resourcesList.add(new Resource("FaithPoints", 		INTVALUE0){});
		resourcesList.add(new Resource("MilitaryPoints", 	INTVALUE2){});
		resourcesList.add(new Resource("VictoryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("CounsilPrivilege", 	INTVALUE0){});
		
		immidiateCardResources = new ResourceSet(resourcesList);

		System.out.println("\n" + "STAMPO: ||immediateCardResources||" + "\n" );
		System.out.println( immidiateCardResources.toString());	
		
		
		//CREATING RESOURCESET FOR CARD PERMANENT EFFECT//
		/////////////////////////////////////////////////
		resourcesList.clear();
		resourcesList.add(new Resource("Wood", 				INTVALUE0){});
		resourcesList.add(new Resource("Stones", 			INTVALUE0){});
		resourcesList.add(new Resource("Servants", 			INTVALUE0){});
		resourcesList.add(new Resource("Money", 			INTVALUE0){});
		resourcesList.add(new Resource("FaithPoints", 		INTVALUE0){});
		resourcesList.add(new Resource("MilitaryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("VictoryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("CounsilPrivilege", 	INTVALUE0){});
		
		permanentCardResources = new ResourceSet(resourcesList);

		System.out.println("\n" + "STAMPO: ||permanentCardResources||" + "\n" );
		System.out.println( permanentCardResources.toString());	
			
		
		
		//CREATING RESOURCESET & SETTING EXCOMMUNICATION MALUS DECREASE RESOURCES MALUS//
		////////////////////////////////////////////////////////////////////////////////
		resourcesList.clear();
		resourcesList.add(new Resource("Wood", 				INTVALUE0){});
		resourcesList.add(new Resource("Stones", 			INTVALUE0){});
		resourcesList.add(new Resource("Servants", 			INTVALUE0){});
		resourcesList.add(new Resource("Money", 			INTVALUE1){});
		resourcesList.add(new Resource("FaithPoints", 		INTVALUE0){});
		resourcesList.add(new Resource("MilitaryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("VictoryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("CounsilPrivilege", 	INTVALUE0){});
		
		ResourceSet temporaryResourcetForMalus = new ResourceSet(resourcesList);
		
		malus = new DecreaseResourcesMalus("DecreaseResourcesMalus", temporaryResourcetForMalus);

		System.out.println("\n" + "STAMPO: ||malus resources||" + "\n" );
		System.out.println( malus.getDecreasedResources().toString());	
		
		
		//SETTING PLAYER RESOURCES AND MALUS EFFECT//
		////////////////////////////////////////////
		
		game.getCurrentPlayer().getDecreaseResourcesMalus().add(malus);
		System.out.println("\n" + "STAMPO: ||MALUS of the player||" + "\n" );
		System.out.println("Grandezza ArrayList: " + game.getCurrentPlayer().getDecreaseResourcesMalus().size());
		int i = 1;
		for (DecreaseResourcesMalus decreaseResourcesMalus : game.getCurrentPlayer().getDecreaseResourcesMalus()) {
			System.out.println("Nome del " + i + "° Malus del giocatore: " + decreaseResourcesMalus.getName());
			System.out.println(decreaseResourcesMalus.getDecreasedResources().toString());
			i++;
		}
		System.out.println("\n----Fine controllo della lista Malus----\n\n\n");

		System.out.println("\n----PLAYER RESOURCES BEFORE----\n");
		System.out.println(game.getCurrentPlayer().getPlayerResourceSet().toString());

		game.getCurrentPlayer().getPlayerResourceSet().add(playerResourceSet);
		
		System.out.println("\n----PLAYER RESOURCES AFTER----\n");
		System.out.println(game.getCurrentPlayer().getPlayerResourceSet().toString());

		
		//SETTING CARD TERRITORY PROPERTIES & EFFECTS//
		//////////////////////////////////////////////
		IncreaseResourcesEffect increaseResourcesImmEffect = new IncreaseResourcesEffect(immidiateCardResources);
		IncreaseResourcesEffect increaseResourcesPerEffect = new IncreaseResourcesEffect(permanentCardResources);

		territoryCard = new TerritoryCard("TerritoryCard", 1, increaseResourcesImmEffect, increaseResourcesPerEffect, new ResourceSet(), new Dice()){};
		game.getCurrentPlayer().putTerritoryCardInPlayerSet(territoryCard);

		System.out.println("\n----PLAYER TERRITORY CARDs----\n");
		System.out.println("Numero di carte Territorio: " + game.getCurrentPlayer().getTerritoryDeck().size() +"\n");

		int j = 1;
		for (TerritoryCard territoryCard : game.getCurrentPlayer().getTerritoryDeck()) {
			System.out.println("Carta n°"+ j +"\nEra:"+ territoryCard.getCardEra() +"\nNome: " + territoryCard.getCardName() +
		"\nValore del Dado: " + territoryCard.getHarvestValue().toString());
		}
		System.out.println("\n----Fine controllo della lista carte territorio----");		
	
		
		//SETTING CARD BUILDING PROPERTIES & EFFECTS//
		//////////////////////////////////////////////	
		
		boolean doubleChoise = false;
		
		resourcesList.clear();
		resourcesList.add(new Resource("Wood", 				INTVALUE1){});
		resourcesList.add(new Resource("Stones", 			INTVALUE1){});
		resourcesList.add(new Resource("Servants", 			INTVALUE0){});
		resourcesList.add(new Resource("Money", 			INTVALUE0){});
		resourcesList.add(new Resource("FaithPoints", 		INTVALUE0){});
		resourcesList.add(new Resource("MilitaryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("VictoryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("CounsilPrivilege", 	INTVALUE0){});
		
		ResourceSet requiredResources = new ResourceSet(resourcesList);

		resourcesList.clear();
		resourcesList.add(new Resource("Wood", 				INTVALUE0){});
		resourcesList.add(new Resource("Stones", 			INTVALUE0){});
		resourcesList.add(new Resource("Servants", 			INTVALUE0){});
		resourcesList.add(new Resource("Money", 			INTVALUE3){});
		resourcesList.add(new Resource("FaithPoints", 		INTVALUE0){});
		resourcesList.add(new Resource("MilitaryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("VictoryPoints", 	INTVALUE0){});
		resourcesList.add(new Resource("CounsilPrivilege", 	INTVALUE0){});

		ResourceSet gainedResources = new ResourceSet(resourcesList);

		System.out.println("\n" + "STAMPO: ||REQUIRED resources||" + "\n" );
		System.out.println( requiredResources.toString());	
		System.out.println("\n" + "STAMPO: ||GAINED resources||" + "\n" );
		System.out.println( gainedResources.toString());	
		
		
		ExchageResourcesEffect exchageResourcesEffect = new ExchageResourcesEffect(requiredResources, gainedResources);
	
		buildingCard = new BuildingCard("BuildingCard", 1, increaseResourcesImmEffect, doubleChoise, exchageResourcesEffect, new ExchageResourcesEffect(immidiateCardResources, permanentCardResources), new ResourceSet(), new Dice()){};
		game.getCurrentPlayer().putBuildingCardInPlayerSet(buildingCard);

		System.out.println("\n----PLAYER BUILDING CARDs----\n");
		System.out.println("Numero di carte Building: " + game.getCurrentPlayer().getBuildingDeck().size() +"\n");

		int m = 1;
		for (BuildingCard buildingCard : game.getCurrentPlayer().getBuildingDeck()) {
			System.out.println("Carta n°"+ m +"\nEra:"+ buildingCard.getCardEra() +"\nNome: " + buildingCard.getCardName() +
		"\nValore del Dado: " + buildingCard.getProductionValue().toString());
		}
		System.out.println("\n----Fine controllo della lista carte edificio----");
		
	};
		
	
	//--------------------------------------------------//
	//-----------------START TESTING METHOD-------------//
	//--------------------------------------------------//
	
	public void testingMethod(){
		
		System.out.println("\n----FINE SETTAGGIO----\n\n\n");	
		System.out.println("\n----INIZIO TESTING----");		
		
		System.out.println("\n" + "STAMPO: ||playerResourceSet|| PRIMA l'attivazione dell'effetto\n" );
		System.out.println( game.getCurrentPlayer().getPlayerResourceSet().toString());	
		/*
		for (TerritoryCard territoryCard : game.getCurrentPlayer().getTerritoryDeck()) {
			territoryCard.getImmediateEffects().activateEffect(game);
		}
		
		System.out.println("\n" + "STAMPO: ||playerResourceSet|| DOPO l'attivazione dell'effetto Immediato\n" );
		System.out.println( game.getCurrentPlayer().getPlayerResourceSet().toString());	
		
		for (TerritoryCard territoryCard : game.getCurrentPlayer().getTerritoryDeck()) {
			territoryCard.getPermanentEffects().activateEffect(game);
		}
		
		System.out.println("\n" + "STAMPO: ||playerResourceSet|| DOPO l'attivazione dell'effetto Permanente\n" );
		System.out.println( game.getCurrentPlayer().getPlayerResourceSet().toString());	
		*/
		for (BuildingCard buildingCard : game.getCurrentPlayer().getBuildingDeck()) {
			buildingCard.getPermanentEffects().activateEffect(game);
		}
		
		System.out.println("\n" + "STAMPO: ||playerResourceSet|| DOPO l'attivazione di exchange resources\n" );
		System.out.println( game.getCurrentPlayer().getPlayerResourceSet().toString());	
		
// RISULTATO DEL TEST quando il Malus è troppo grande per le risorse, NON AGISCE! 
		//esempio: Player resources 2 ; increase resources +1; decrese resources -2 ==> RISULTATO = 3
		//il malus potrebbe aggiornarsi al valore massimo di incremento il malus era di -2 ma in questo caso sarà di -1  ==> risultato = 2
		//così avrebbe bilanciato l'incremento (così facendo avrebbe agito!)
// FINE RISULTATO TODO implementare il cambiamento
		
		
	/*
		game.getCurrentPlayer().getPlayerResourceSet().add(immidiateCardResources);
		
		System.out.println("\n" + "STAMPO: ||playerResourceSet|| SOMMA FORZATA\n" );
		System.out.println( game.getCurrentPlayer().getPlayerResourceSet().toString());	
		//FUNGE
		 
	 */
		
		
	}

	//--------------------------------------------------//
	//-----------------MAIN METHOD----------------------//
	//--------------------------------------------------//
	
	public static void main(String[] args) {
	
		MalusDecreaseTEST test = new MalusDecreaseTEST();
		test.testingMethod();
	}
	
	
	

	public ResourceSet getAdditionalCardResources() {
		return immidiateCardResources;
	}

	public void setAdditionalCardResources(ResourceSet additionalCardResources) {
		this.immidiateCardResources = additionalCardResources;
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
