package it.polimi.ingsw.ps46;


/**
 * Description of Money.
 * 
 * @author a.mondolo
 */
public class Money {
	/**
	 * Description of the property coin.
	 */
	private Integer coin = Integer.valueOf(0);


	/**
	 * The constructor.
	 */
	public Money() {
		super();
	}

	/**
	 * Returns coin.
	 * @return coin 
	 */
	public Integer getCoin() {
		return this.coin;
	}

	/**
	 * Sets a value to attribute coin. 
	 * @param newCoin 
	 */
	public void setCoin(Integer newCoin) {
		this.coin = newCoin;
	}

}
