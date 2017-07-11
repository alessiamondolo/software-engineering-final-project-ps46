package it.polimi.ingsw.ps46.server;

import java.io.Serializable;

import it.polimi.ingsw.ps46.server.resources.ResourceSet;


public class BonusTile implements Serializable {

	private static final long serialVersionUID = 5161001177516211182L;
	
	private int id;
	private boolean advancedPersonalBoard;
	private Dice productionValue;
	private Dice harvestValue;
	private ResourceSet gainedFromPersonalBoardProduction;
	private ResourceSet gainedFromPersonalBoardHarvest;
	
	public BonusTile(int id, boolean advancedPersonalBoard, Dice productionValue, Dice harvestValue, ResourceSet gainedFromPersonalBoardProduction, ResourceSet gainedFromPersonalBoardHarvest) {
		
		this.setId(id);
		this.setAdvancedPersonalBoard(advancedPersonalBoard);
		this.setProductionValue(productionValue);
		this.setHarvestValue(harvestValue);
		this.setGainedFromPersonalBoardHarvest(gainedFromPersonalBoardHarvest);
		this.setGainedFromPersonalBoardProduction(gainedFromPersonalBoardProduction);
		
	}
	
	/**
	 * This constructor is used to create a deep copy of a bonus tile passed as a parameter.
	 * 
	 * @param bonusTileToBeCloned
	 */
	public BonusTile(BonusTile bonusTileToBeCloned){
		advancedPersonalBoard = bonusTileToBeCloned.isAdvancedPersonalBoard();
		productionValue = bonusTileToBeCloned.getProductionValue();
		harvestValue = bonusTileToBeCloned.getHarvestValue();
		gainedFromPersonalBoardProduction = bonusTileToBeCloned.getGainedFromPersonalBoardProduction();
		gainedFromPersonalBoardHarvest = bonusTileToBeCloned.getGainedFromPersonalBoardHarvest();
	}
	
	public Dice getProductionValue() {
		return productionValue;
	}

	private void setProductionValue(Dice productionValue) {
		this.productionValue = productionValue;
	}

	public Dice getHarvestValue() {
		return harvestValue;
	}

	private void setHarvestValue(Dice harvestValue) {
		this.harvestValue = harvestValue;
	}

	public ResourceSet getGainedFromPersonalBoardProduction() {
		return gainedFromPersonalBoardProduction;
	}

	private void setGainedFromPersonalBoardProduction(ResourceSet gainedFromPersonalBoardProduction) {
		this.gainedFromPersonalBoardProduction = gainedFromPersonalBoardProduction;
	}

	public ResourceSet getGainedFromPersonalBoardHarvest() {
		return gainedFromPersonalBoardHarvest;
	}

	private void setGainedFromPersonalBoardHarvest(ResourceSet gainedFromPersonalBoardHarvest) {
		this.gainedFromPersonalBoardHarvest = gainedFromPersonalBoardHarvest;
	}


	public boolean isAdvancedPersonalBoard() {
		return advancedPersonalBoard;
	}


	public void setAdvancedPersonalBoard(boolean advancedPersonalBoard) {
		this.advancedPersonalBoard = advancedPersonalBoard;
	}
	
	@Override
	public String toString() {
		return "Production value: " + productionValue + ", resources gained from production: " + gainedFromPersonalBoardProduction.toString() + "\n" +
				"Harvest value: " + harvestValue + ", resources gained from harvest: " + gainedFromPersonalBoardHarvest.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
