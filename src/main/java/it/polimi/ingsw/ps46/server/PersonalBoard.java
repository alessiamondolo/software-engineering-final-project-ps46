package it.polimi.ingsw.ps46.server;

import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * Description of the Class PersonalBoard
 * 
 * @author Andrea.Masi
 */


public class PersonalBoard {
	
	private Dice requiredFamilyMemberValue;
	private ResourceSet gainedFromPersonalBoardProduction;
	private ResourceSet gainedFromPersonalBoardHarvest;
	
	public PersonalBoard (Dice productionHarvestValue, ResourceSet gainedFromPersonalBoardProduction, ResourceSet gainedFromPersonalBoardHarvest){
		
		this.gainedFromPersonalBoardHarvest = gainedFromPersonalBoardHarvest;
		this.gainedFromPersonalBoardProduction = gainedFromPersonalBoardProduction;
		this.requiredFamilyMemberValue = productionHarvestValue;
		
	}
	
	
	public Dice getProductionHarvestValue() {
		return requiredFamilyMemberValue;
	}

	public ResourceSet getGainedFromPersonalBoardProduction() {
		return gainedFromPersonalBoardProduction;
	}

	public ResourceSet getGainedFromPersonalBoardHarvest() {
		return gainedFromPersonalBoardHarvest;
	}

}
