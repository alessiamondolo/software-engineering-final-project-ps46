package it.polimi.ingsw.ps46.server;


/**
 * This Class is used to create an object Dice used (as an int) 
 * to create a random number or to set a value usefull to compare or change the value as we prefer.
 * @author Andrea.Masi
 *
 */

public class Dice {
	
	private int value = 0;
	private static final int NFACES = 6;
	
	
	/**
 	* Description of the constructor Dice.
 	* This constructor is used to create a new Dice element of a random value using the method throwDice();
 	*/
	public Dice(){
		throwDice();
	};
	
	
	
	/**
	 * Description of the constructor Dice(int).
	 * This constructor is used to create a new Dice element with a preset value.
	 * 
	 * @param newValue
	 */
	public Dice(int newValue){
		value = newValue;
	}
	
	
	/**
	 * Description of the method throwDice
	 * This method is used to create a random value between 1-6
	 * 
	 * @return value: _Int_ the result of the throw.
	 * @param nFaces number of faces of the dice
	 */
	public void throwDice(){
		
		Double x = Math.ceil(Math.random()*NFACES);
		value = x.intValue();
	}


	/**
	 * Description of the method getValue()
	 * 
	 * @return value 
	 */
	public int getValue() {
		return value;
	}
	
	
	/**
	 * Description of the method greaterOrEqual
	 * This method compares two different Dice
	 * 
	 * @param diceValue
	 * @return boolean 
	 */

	public Boolean greaterOrEqual(Dice diceValue){
	
		if (value >= diceValue.getValue())
		{
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * Description of the method sumDice
	 * This method sum the value of two dice
	 * 
	 * @param dice2
	 * @return summedDice
	 */
	
	public void sumDice(Dice dice2){
		value =+ dice2.getValue();
		}
	
	
	/**
	 * Description of the method toString()
	 * Return the value of the dice as a string.
	 */
	
	
	@Override
	public String toString(){
		return Integer.toString(value);
	}

}

