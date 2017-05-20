package it.polimi.ingsw.ps46.server;

/**
 * Description of BoxCounsil.
 * 
 * @author a.mondolo
 */
public class BoxCounsil extends Box {
	/**
	 * Description of the property nextTurnOrder.
	 */
	public Object nextTurnOrder;
	
	/**
	 * The constructor.
	 */
	public BoxCounsil() {
		super();
	}

	/**
	 * Description of the method chooseBonus.
	 */
	public void chooseBonus() {
	}

	/**
	 * Returns nextTurnOrder.
	 * @return nextTurnOrder 
	 */
	public Object getNextTurnOrder() {
		return this.nextTurnOrder;
	}

	/**
	 * Sets a value to attribute nextTurnOrder. 
	 * @param newNextTurnOrder 
	 */
	public void setNextTurnOrder(Object newNextTurnOrder) {
		this.nextTurnOrder = newNextTurnOrder;
	}

}
