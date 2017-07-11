package it.polimi.ingsw.ps46.server;

import java.io.Serializable;

/**
 * This Class is used to create an object Dice used (as an int) 
 * to create a random number or to set a value usefull to compare or change the value as we prefer.
 * @author Andrea.Masi
 *
 */

public class Dice implements Serializable {
	
	private static final long serialVersionUID = 8456130205960916201L;
	
	private int value = 0;
	private static final int NFACES = 6;
	
	
	/**
 	* This constructor is used to create a new Dice element of a random value using the method throwDice();
 	*/
	public Dice(){
		throwDice();
	};
	
	
	
	/**
	 * This constructor is used to create a new Dice element with a preset value.
	 * 
	 * @param newValue
	 */
	public Dice(int newValue){
		value = newValue;
	}
	
	///////////////////METHODS////////////////////
	//////////////////////////////////////////////
	/**
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
	 * This method sum the value of two dice
	 * 
	 * @param dice2
	 */
	
	public void sumDice(Dice dice2){
		value += dice2.getValue();
		}
	
	
	/**
	 * This method sub the value of two dice, it's avoid having the resultant dice with a negative value (0 is the minimum)
	 * 
	 * @param dice2
	 */
	public void subDice(Dice dice2){
		value -= dice2.getValue(); 
		
		if (value < 0){
			value = 0;
		}
	}
	
	
	/**
	 * Return the value of the dice as a string.
	 */
	
	
	@Override
	public String toString(){
		return Integer.toString(value);
	}
	
	///////////////GETTER METHODS//////////////////
	//////////////////////////////////////////////

	/**
	 * Description of the method getValue()
	 * 
	 * @return value 
	 */
	public int getValue() {
		return value;
	}
	

}

