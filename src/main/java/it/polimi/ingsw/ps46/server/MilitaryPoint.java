package it.polimi.ingsw.ps46.server;


/**
 * Description of MilitaryPoint.
 * 
 * @author a.mondolo
 */
public class MilitaryPoint {
	/**
	 * Description of the property servant.
	 */
	public Integer servant = Integer.valueOf(0);

	/**
	 * The constructor.
	 */
	public MilitaryPoint() {
		super();
	}

	/**
	 * Returns servant.
	 * @return servant 
	 */
	public Integer getServant() {
		return this.servant;
	}

	/**
	 * Sets a value to attribute servant. 
	 * @param newServant 
	 */
	public void setServant(Integer newServant) {
		this.servant = newServant;
	}

}
