package it.polimi.ingsw.ps46.server.action;


import it.polimi.ingsw.ps46.server.ActionSpace;
import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.FamilyMember;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.card.BuildingCard;
import it.polimi.ingsw.ps46.server.card.DiceMalusEffect;


/**
 * TODO commentare
 * 
 * @author Andrea.Masi
 */


public class ActivateProductionAction implements Action {
	
	private Game game;
	private ActionSpace productionActionSpace;
	private Dice familyMemberValue;
	
	private Dice penality;
	private final static int PENALITYOFTHEACTIONSPACE = 3;
	
	
	
	public ActivateProductionAction(Game game, ActionSpace productionActionSpace,  FamilyMember familyMemberUsed){
		
		this.game = game;
		this.productionActionSpace = productionActionSpace;
		familyMemberValue = familyMemberUsed.getValueFamilyMember();
		
		}
	
	
	@Override
	public void execute() {
		/*
		 *  "parametri in entrata" il resourceSet, valore del familiare usato (già aggiornato), le carte relative alla produzione.
		 * TODO cosa farà il metodo execute?
		 *  - passare le carte una ad una e ...
		 *	- controllare quali effetti delle carte possono essere attivate col valore attuale del dado
		 *	- chiedere al giocatore quale delle due scelte di risorse vuole effettuare (per alcuni tipi di carta)?
		 *  - togliere le risorse dal resourceSet del giocatore 
		 *	- aggiornare il resourceSet del giocatore
		 *  - * Se con malus -1 ... fare in modo che ogni carta venga vista come una sorgente e togliere -1 della risorsa X.
		 */
		if (isLegal() == true){
			for (BuildingCard buildingCard : game.getCurrentPlayer().getPersonalBoard().getBuildingDeck()) {
				
				if(familyMemberValue.greaterOrEqual(buildingCard.getProductionValue())){
					buildingCard.getPermanentEffects().activateEffect(game);	
				}
			}
		}
		else {
			//TODO throw exception! Illegal Action
		}
		
		
		
	}

	@Override
	public boolean isLegal() {
		 /* 
		 *  Cosa farà il metodo #isValid?
		 *	- controllare dove si è posizionato il family member e modifica il penality (se si trova nello spazio di produzione più grosso)
		 *	- controllare il bonus (dalle carte personaggio)
		 *	- * Controllare malus delle carte scomunica
		 *	- passare le carte una ad una e capire se almeno una carta è attivabile/ personal board
		 *  - verificare se il giocatore ha abbastanza servi e se può chiedere al giocatore se vuole aumentare ancora il valore del dado usando altri servi?
		 * 	- **ECCEZIONE	se dopo aver aggiornato il dado il giocatore ha finito i servi e non non avesse la più possibilità di effettuare l'azione?
		 */
		Dice temporaryDice = new Dice();
		temporaryDice = familyMemberValue;

		if ( productionActionSpace.isMaxOnePlayer() != true )
		{
			penality = new Dice( PENALITYOFTHEACTIONSPACE );
		}
		else
			penality = new Dice(0);
		
		temporaryDice.subDice( penality );
		temporaryDice.sumDice( game.getCurrentPlayer().getBonusMap().get( "ProductionAction" ) );
		
	
		if( !game.getCurrentPlayer().getDiceMalus().isEmpty() )
			for( DiceMalusEffect diceMalusEffect : game.getCurrentPlayer().getDiceMalus() ){
				
				if ( diceMalusEffect.getType() == "ProductionAction" ){
					temporaryDice.subDice( diceMalusEffect.getMalus() );	
				}
			}
				
		//TODO RICHIEDERE AL CONTROLLER SE SI VUOLE AUMENTARE IL VALORE DEL DADO BRUCIANDO DEI SERVI
		
		for (BuildingCard buildingCard : game.getCurrentPlayer().getPersonalBoard().getBuildingDeck()) {
		
			if(temporaryDice.greaterOrEqual(buildingCard.getProductionValue())){
				familyMemberValue = temporaryDice;	
				return true;
			}
		}
		
		if(temporaryDice.greaterOrEqual(game.getCurrentPlayer().getPersonalBoard().getProductionValue())){
			familyMemberValue = temporaryDice;	
			return true;
		}
		
		return false;
	}
}
