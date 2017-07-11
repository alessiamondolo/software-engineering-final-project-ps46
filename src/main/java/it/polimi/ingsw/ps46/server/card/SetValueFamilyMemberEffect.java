package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;

/**
 * 
 * @author Andrea.Masi
 */
public class SetValueFamilyMemberEffect implements Effect {

	private String whichFamilyMember;
	private Dice newValue; 
	
	
	public SetValueFamilyMemberEffect (String whichFamilyMember, Dice newValue) {
		this.whichFamilyMember = whichFamilyMember;
		this.newValue = newValue;
		
	}
	
	//TODO da qualche parte andrà attivato questo effetto! 
	// ma priva va controllato se la carta è attivabile tramite il metodo opportuno DONE
	@Override
	public void activateEffect(Game game) {
		if(whichFamilyMember == "allColored"){
			//set every colored familyMembers (not already used) to the new DiceValue.
			
			for (String familyMemberColor : game.getCurrentPlayer().getFamilyMembersMap().keySet()) {
				if( familyMemberColor != "Neutral"){
					if (!game.getCurrentPlayer().getFamilyMember(familyMemberColor).isUsed()) {
						game.getCurrentPlayer().getFamilyMember(familyMemberColor).setValueOfFamilyMember(newValue);
					}
				}
			}
			
		}
		else {
			//TODO INTERAZIONE COL GIOCATORE PER CHIEDERE QUALE FAMILY MEMBER VUOLE SETTARE
		}
	}


	public String getWhichFamilyMember() {
		return whichFamilyMember;
	}

	
	public Dice getNewValue() {
		return newValue;
	}
	
	@Override
	public String toString() {
		return "Which family member: " + whichFamilyMember + "\n" +
				"New value: " + newValue;
	}
	
}
