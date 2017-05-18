package it.polimi.ingsw.ps46;
/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of VentureCard.
 * 
 * @author a.mondolo
 */
public class VentureCard extends Card {
	/**
	 * Description of the property requirementChoice.
	 */
	public Boolean requirementChoice = Boolean.FALSE;

	/**
	 * Description of the property requiredResources.
	 */
	public Resources requiredResources = null;

	/**
	 * Description of the property requiredMilitaryPoints.
	 */
	public Object requiredMilitaryPoints;

	/**
	 * Description of the property bonusResources.
	 */
	public Resources bonusResources = null;

	/**
	 * Description of the property bonusPoints.
	 */
	public Object bonusPoints;

	/**
	 * Description of the property bonusProduction.
	 */
	public Integer bonusProduction = Integer.valueOf(0);

	/**
	 * Description of the property bonusHarvest.
	 */
	public Integer bonusHarvest = Integer.valueOf(0);

	/**
	 * Description of the property bonusCouncilPrivilege.
	 */
	private Integer bonusCouncilPrivilege = Integer.valueOf(0);

	/**
	 * Description of the property bonusExtraCard.
	 */
	public Integer bonusExtraCard = Integer.valueOf(0);

	/**
	 * Description of the property twoDifferentCouncilPrivileges.
	 */
	public Boolean twoDifferentCouncilPrivileges = Boolean.FALSE;

	/**
	 * Description of the property finalVictoryPoints.
	 */
	public Integer finalVictoryPoints = Integer.valueOf(0);

	// Start of user code (user defined attributes for VentureCard)

	// End of user code

	/**
	 * The constructor.
	 */
	public VentureCard() {
		// Start of user code constructor for VentureCard)
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

	/**
	 * Description of the method Operation_2.
	 */
	public void Operation_2() {
		// Start of user code for method Operation_2
		// End of user code
	}

	// Start of user code (user defined methods for VentureCard)

	// End of user code
	/**
	 * Returns requirementChoice.
	 * @return requirementChoice 
	 */
	public Boolean getRequirementChoice() {
		return this.requirementChoice;
	}

	/**
	 * Sets a value to attribute requirementChoice. 
	 * @param newRequirementChoice 
	 */
	public void setRequirementChoice(Boolean newRequirementChoice) {
		this.requirementChoice = newRequirementChoice;
	}

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
	 * Returns requiredMilitaryPoints.
	 * @return requiredMilitaryPoints 
	 */
	public Object getRequiredMilitaryPoints() {
		return this.requiredMilitaryPoints;
	}

	/**
	 * Sets a value to attribute requiredMilitaryPoints. 
	 * @param newRequiredMilitaryPoints 
	 */
	public void setRequiredMilitaryPoints(Object newRequiredMilitaryPoints) {
		this.requiredMilitaryPoints = newRequiredMilitaryPoints;
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
	 * Returns bonusProduction.
	 * @return bonusProduction 
	 */
	public Integer getBonusProduction() {
		return this.bonusProduction;
	}

	/**
	 * Sets a value to attribute bonusProduction. 
	 * @param newBonusProduction 
	 */
	public void setBonusProduction(Integer newBonusProduction) {
		this.bonusProduction = newBonusProduction;
	}

	/**
	 * Returns bonusHarvest.
	 * @return bonusHarvest 
	 */
	public Integer getBonusHarvest() {
		return this.bonusHarvest;
	}

	/**
	 * Sets a value to attribute bonusHarvest. 
	 * @param newBonusHarvest 
	 */
	public void setBonusHarvest(Integer newBonusHarvest) {
		this.bonusHarvest = newBonusHarvest;
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
	 * Returns bonusExtraCard.
	 * @return bonusExtraCard 
	 */
	public Integer getBonusExtraCard() {
		return this.bonusExtraCard;
	}

	/**
	 * Sets a value to attribute bonusExtraCard. 
	 * @param newBonusExtraCard 
	 */
	public void setBonusExtraCard(Integer newBonusExtraCard) {
		this.bonusExtraCard = newBonusExtraCard;
	}

	/**
	 * Returns twoDifferentCouncilPrivileges.
	 * @return twoDifferentCouncilPrivileges 
	 */
	public Boolean getTwoDifferentCouncilPrivileges() {
		return this.twoDifferentCouncilPrivileges;
	}

	/**
	 * Sets a value to attribute twoDifferentCouncilPrivileges. 
	 * @param newTwoDifferentCouncilPrivileges 
	 */
	public void setTwoDifferentCouncilPrivileges(Boolean newTwoDifferentCouncilPrivileges) {
		this.twoDifferentCouncilPrivileges = newTwoDifferentCouncilPrivileges;
	}

	/**
	 * Returns finalVictoryPoints.
	 * @return finalVictoryPoints 
	 */
	public Integer getFinalVictoryPoints() {
		return this.finalVictoryPoints;
	}

	/**
	 * Sets a value to attribute finalVictoryPoints. 
	 * @param newFinalVictoryPoints 
	 */
	public void setFinalVictoryPoints(Integer newFinalVictoryPoints) {
		this.finalVictoryPoints = newFinalVictoryPoints;
	}

}
