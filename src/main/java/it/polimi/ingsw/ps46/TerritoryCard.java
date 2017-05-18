package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of TerritoryCard.
 * 
 * @author a.mondolo
 */
public class TerritoryCard extends Card {
	/**
	 * Description of the property harvestCost.
	 */
	public Integer harvestCost = Integer.valueOf(0);

	/**
	 * Description of the property bonusResources.
	 */
	public Resources bonusResources = null;

	/**
	 * Description of the property bonusPoints.
	 */
	public Object bonusPoints;

	/**
	 * Description of the property bonusCouncilPrivilege.
	 */
	public Integer bonusCouncilPrivilege = Integer.valueOf(0);

	/**
	 * Description of the property obtainResourcesFromHarvest.
	 */
	public Resources obtainResourcesFromHarvest = null;

	/**
	 * Description of the property obtainPointsFromHarvest.
	 */
	public Object obtainPointsFromHarvest;
	
	// Start of user code (user defined attributes for TerritoryCard)
	
	// End of user code

	/**
	 * The constructor.
	 */
	public TerritoryCard() {
		// Start of user code constructor for TerritoryCard)
		super();
		// End of user code
	}

	/**
	 * Description of the method acquireCard.
	 */
	public void acquireCard() {
		// Start of user code for method acquireCard
		// End of user code
	}

	// Start of user code (user defined methods for TerritoryCard)

	// End of user code
	/**
	 * Returns harvestCost.
	 * @return harvestCost 
	 */
	public Integer getHarvestCost() {
		return this.harvestCost;
	}

	/**
	 * Sets a value to attribute harvestCost. 
	 * @param newHarvestCost 
	 */
	public void setHarvestCost(Integer newHarvestCost) {
		this.harvestCost = newHarvestCost;
	}

	/**
	 * Returns bonusResources.
	 * @return bonusResources 
	 */
	public Resources getBonusResources() {
		return this.bonusResources;
	}

	/**
	 * Sets a value to attribute bonusResources. 
	 * @param newBonusResources 
	 */
	public void setBonusResources(Resources newBonusResources) {
		this.bonusResources = newBonusResources;
	}

	/**
	 * Returns bonusPoints.
	 * @return bonusPoints 
	 */
	public Object getBonusPoints() {
		return this.bonusPoints;
	}

	/**
	 * Sets a value to attribute bonusPoints. 
	 * @param newBonusPoints 
	 */
	public void setBonusPoints(Object newBonusPoints) {
		this.bonusPoints = newBonusPoints;
	}

	/**
	 * Returns bonusCouncilPrivilege.
	 * @return bonusCouncilPrivilege 
	 */
	public Integer getBonusCouncilPrivilege() {
		return this.bonusCouncilPrivilege;
	}

	/**
	 * Sets a value to attribute bonusCouncilPrivilege. 
	 * @param newBonusCouncilPrivilege 
	 */
	public void setBonusCouncilPrivilege(Integer newBonusCouncilPrivilege) {
		this.bonusCouncilPrivilege = newBonusCouncilPrivilege;
	}

	/**
	 * Returns obtainResourcesFromHarvest.
	 * @return obtainResourcesFromHarvest 
	 */
	public Resources getObtainResourcesFromHarvest() {
		return this.obtainResourcesFromHarvest;
	}

	/**
	 * Sets a value to attribute obtainResourcesFromHarvest. 
	 * @param newObtainResourcesFromHarvest 
	 */
	public void setObtainResourcesFromHarvest(Resources newObtainResourcesFromHarvest) {
		this.obtainResourcesFromHarvest = newObtainResourcesFromHarvest;
	}

	/**
	 * Returns obtainPointsFromHarvest.
	 * @return obtainPointsFromHarvest 
	 */
	public Object getObtainPointsFromHarvest() {
		return this.obtainPointsFromHarvest;
	}

	/**
	 * Sets a value to attribute obtainPointsFromHarvest. 
	 * @param newObtainPointsFromHarvest 
	 */
	public void setObtainPointsFromHarvest(Object newObtainPointsFromHarvest) {
		this.obtainPointsFromHarvest = newObtainPointsFromHarvest;
	}

}
