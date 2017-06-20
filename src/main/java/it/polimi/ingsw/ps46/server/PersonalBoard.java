package it.polimi.ingsw.ps46.server;

import it.polimi.ingsw.ps46.server.resources.ResourceSet;

/**
 * This class is used to create the personal tile board with them effects on the actions of production and harvest.
 * 
 * @author Andrea.Masi
 */


public class PersonalBoard {
	
	private Dice requiredFamilyMemberValue;
	private ResourceSet gainedFromPersonalBoardProduction;
	private ResourceSet gainedFromPersonalBoardHarvest;
	
	
	/**
	 * This Constructor creates a new object PersonalBoard, used to initialize the personal board by .json file.
	 * 
	 * @param productionHarvestValue
	 * @param gainedFromPersonalBoardProduction
	 * @param gainedFromPersonalBoardHarvest
	 */
	
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
