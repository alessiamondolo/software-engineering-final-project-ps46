package it.polimi.ingsw.ps46.server.resources;


/**
 * Description of FaithPoint.
 * 
 * @author a.mondolo
 */
public class FaithPoint {
	/**
	 * Description of the property servant.
	 */
	public Integer servant = Integer.valueOf(0);


	/**
	 * The constructor.
	 */
	public FaithPoint() {
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
