package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of BuildingCard.
 * 
 * @author a.mondolo
 */
public class BuildingCard extends Card {
	/**
	 * Description of the property requiredResources.
	 */
	public Resources requiredResources = null;

	/**
	 * Description of the property bonusPoints.
	 */
	public Object bonusPoints;

	/**
	 * Description of the property productionCost.
	 */
	public Integer productionCost = Integer.valueOf(0);

	/**
	 * Description of the property giveResourcesToProduce.
	 */
	public Resources giveResourcesToProduce = null;

	/**
	 * Description of the property givePointsToProduce.
	 */
	public Object givePointsToProduce;

	/**
	 * Description of the property obtainResourcesFromProduction.
	 */
	public Resources obtainResourcesFromProduction = null;

	/**
	 * Description of the property obtainPointsFromProduction.
	 */
	public Object obtainPointsFromProduction;

	/**
	 * Description of the property obtainCouncilPrivilegeFromProduction.
	 */
	public Integer obtainCouncilPrivilegeFromProduction = Integer.valueOf(0);

	// Start of user code (user defined attributes for BuildingCard)

	// End of user code

	/**
	 * The constructor.
	 */
	public BuildingCard() {
		// Start of user code constructor for BuildingCard)
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

	// Start of user code (user defined methods for BuildingCard)

	// End of user code
	/**
	 * Returns requiredResources.
	 * @return requiredResources 
	 */
	public Resources getRequiredResources() {
		return this.requiredResources;
	}

	/**
	 * Sets a value to attribute requiredResources. 
	 * @param newRequiredResources 
	 */
	public void setRequiredResources(Resources newRequiredResources) {
		this.requiredResources = newRequiredResources;
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
	 * Returns productionCost.
	 * @return productionCost 
	 */
	public Integer getProductionCost() {
		return this.productionCost;
	}

	/**
	 * Sets a value to attribute productionCost. 
	 * @param newProductionCost 
	 */
	public void setProductionCost(Integer newProductionCost) {
		this.productionCost = newProductionCost;
	}

	/**
	 * Returns giveResourcesToProduce.
	 * @return giveResourcesToProduce 
	 */
	public Resources getGiveResourcesToProduce() {
		return this.giveResourcesToProduce;
	}

	/**
	 * Sets a value to attribute giveResourcesToProduce. 
	 * @param newGiveResourcesToProduce 
	 */
	public void setGiveResourcesToProduce(Resources newGiveResourcesToProduce) {
		this.giveResourcesToProduce = newGiveResourcesToProduce;
	}

	/**
	 * Returns givePointsToProduce.
	 * @return givePointsToProduce 
	 */
	public Object getGivePointsToProduce() {
		return this.givePointsToProduce;
	}

	/**
	 * Sets a value to attribute givePointsToProduce. 
	 * @param newGivePointsToProduce 
	 */
	public void setGivePointsToProduce(Object newGivePointsToProduce) {
		this.givePointsToProduce = newGivePointsToProduce;
	}

	/**
	 * Returns obtainResourcesFromProduction.
	 * @return obtainResourcesFromProduction 
	 */
	public Resources getObtainResourcesFromProduction() {
		return this.obtainResourcesFromProduction;
	}

	/**
	 * Sets a value to attribute obtainResourcesFromProduction. 
	 * @param newObtainResourcesFromProduction 
	 */
	public void setObtainResourcesFromProduction(Resources newObtainResourcesFromProduction) {
		this.obtainResourcesFromProduction = newObtainResourcesFromProduction;
	}

	/**
	 * Returns obtainPointsFromProduction.
	 * @return obtainPointsFromProduction 
	 */
	public Object getObtainPointsFromProduction() {
		return this.obtainPointsFromProduction;
	}

	/**
	 * Sets a value to attribute obtainPointsFromProduction. 
	 * @param newObtainPointsFromProduction 
	 */
	public void setObtainPointsFromProduction(Object newObtainPointsFromProduction) {
		this.obtainPointsFromProduction = newObtainPointsFromProduction;
	}

	/**
	 * Returns obtainCouncilPrivilegeFromProduction.
	 * @return obtainCouncilPrivilegeFromProduction 
	 */
	public Integer getObtainCouncilPrivilegeFromProduction() {
		return this.obtainCouncilPrivilegeFromProduction;
	}

	/**
	 * Sets a value to attribute obtainCouncilPrivilegeFromProduction. 
	 * @param newObtainCouncilPrivilegeFromProduction 
	 */
	public void setObtainCouncilPrivilegeFromProduction(Integer newObtainCouncilPrivilegeFromProduction) {
		this.obtainCouncilPrivilegeFromProduction = newObtainCouncilPrivilegeFromProduction;
	}

}
