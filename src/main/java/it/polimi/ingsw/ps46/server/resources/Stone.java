package it.polimi.ingsw.ps46.server.resources;


/**
 * Description of Stone.
 * 
 * @author a.mondolo
 */
public class Stone {
	/**
	 * Description of the property stone.
	 */
	private Integer stone = Integer.valueOf(0);

	/**
	 * The constructor.
	 */
	public Stone() {
		super();
	}

	/**
	 * Returns stone.
	 * @return stone 
	 */
	public Integer getStone() {
		return this.stone;
	}

	/**
	 * Sets a value to attribute stone. 
	 * @param newStone 
	 */
	public void setStone(Integer newStone) {
		this.stone = newStone;
	}

}
