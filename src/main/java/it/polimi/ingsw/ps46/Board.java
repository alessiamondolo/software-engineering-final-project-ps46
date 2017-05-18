package it.polimi.ingsw.ps46;

import java.util.HashSet;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Board.
 * 
 * @author a.mondolo
 */
public class Board {
	/**
	 * Description of the property casellas.
	 */
	public HashSet<Casella> casellas = new HashSet<Casella>();

	/**
	 * Description of the property carteScomunica.
	 */
	private Object carteScomunica;

	/**
	 * Description of the property positions.
	 */
	private Object positions;

	/**
	 * Description of the property victoryPoints.
	 */
	public Object victoryPoints;

	/**
	 * Description of the property militaryPoints.
	 */
	public Object militaryPoints;

	/**
	 * Description of the property faithPoints.
	 */
	public Object faithPoints;
	
	// Start of user code (user defined attributes for Board)
	
	// End of user code

	/**
	 * The constructor.
	 */
	public Board() {
		// Start of user code constructor for Board)
		super();
		// End of user code
	}

	/**
	 * Description of the method cardOnBoard.
	 * @param  
	 */
	public void cardOnBoard() {
		// Start of user code for method cardOnBoard
		// End of user code
	}

	// Start of user code (user defined methods for Board)

	// End of user code
	/**
	 * Returns casellas.
	 * @return casellas 
	 */
	public HashSet<Casella> getCasellas() {
		return this.casellas;
	}

	/**
	 * Returns carteScomunica.
	 * @return carteScomunica 
	 */
	public Object getCarteScomunica() {
		return this.carteScomunica;
	}

	/**
	 * Sets a value to attribute carteScomunica. 
	 * @param newCarteScomunica 
	 */
	public void setCarteScomunica(Object newCarteScomunica) {
		this.carteScomunica = newCarteScomunica;
	}

	/**
	 * Returns positions.
	 * @return positions 
	 */
	public Object getPositions() {
		return this.positions;
	}

	/**
	 * Sets a value to attribute positions. 
	 * @param newPositions 
	 */
	public void setPositions(Object newPositions) {
		this.positions = newPositions;
	}

	/**
	 * Returns victoryPoints.
	 * @return victoryPoints 
	 */
	public Object getVictoryPoints() {
		return this.victoryPoints;
	}

	/**
	 * Sets a value to attribute victoryPoints. 
	 * @param newVictoryPoints 
	 */
	public void setVictoryPoints(Object newVictoryPoints) {
		this.victoryPoints = newVictoryPoints;
	}

	/**
	 * Returns militaryPoints.
	 * @return militaryPoints 
	 */
	public Object getMilitaryPoints() {
		return this.militaryPoints;
	}

	/**
	 * Sets a value to attribute militaryPoints. 
	 * @param newMilitaryPoints 
	 */
	public void setMilitaryPoints(Object newMilitaryPoints) {
		this.militaryPoints = newMilitaryPoints;
	}

	/**
	 * Returns faithPoints.
	 * @return faithPoints 
	 */
	public Object getFaithPoints() {
		return this.faithPoints;
	}

	/**
	 * Sets a value to attribute faithPoints. 
	 * @param newFaithPoints 
	 */
	public void setFaithPoints(Object newFaithPoints) {
		this.faithPoints = newFaithPoints;
	}

}
