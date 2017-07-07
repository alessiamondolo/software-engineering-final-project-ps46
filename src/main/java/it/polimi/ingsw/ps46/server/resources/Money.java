package it.polimi.ingsw.ps46.server.resources;


/**
 * Money is a type of Resource.
 * 
 * @author Alessia Mondolo
 */
public class Money extends Resource {
	
	private static final long serialVersionUID = 6426072209737422602L;

	/**
	 * The constructor.
	 */
	public Money(int quantity) {
		super("Money", quantity);
	}
	
	public Money(Money beforeMoney, Money afterMoney){
		super(beforeMoney, afterMoney);
	}

}
