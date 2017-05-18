package it.polimi.ingsw.ps46;


/**
 * Description of VictoryPoint.
 * 
 * @author a.mondolo
 */
public class VictoryPoint {
	/**
	 * Description of the property servant.
	 */
	public Integer servant = Integer.valueOf(0);


	/**
	 * The constructor.
	 */
	public VictoryPoint() {
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
