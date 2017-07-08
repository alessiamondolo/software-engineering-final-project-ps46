package it.polimi.ingsw.ps46;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import it.polimi.ingsw.ps46.server.Dice;

public class DiceTest {
	
	
	@Test
	public void testConstructor1() {

		Dice dice = new Dice();
		assertTrue (dice.getValue() > 0 && dice.getValue() < 7);
	}

	@Test
	public void testConstructor2() {
		
		Dice dice = new Dice(4);
		assertEquals(4, dice.getValue());
	}
	
	@Test
	public void testThrowDice() {
		ArrayList<Integer> setOfValues;
		setOfValues = new ArrayList<Integer>();
		for (int n=1; n < 7; n++){
			setOfValues.add(n);
		}
		
		Dice dice = new Dice();
		for(int i = 0; i < 100; i++) {
			dice.throwDice();
			assertTrue("Il dado n°"+ i + " ha valore: " + dice.getValue() + ". Risultato del assertTrue: " + setOfValues.contains(dice.getValue()), setOfValues.contains(dice.getValue()));
		//	System.out.println("Il dado n°"+ i + " ha valore: " + dice.getValue() + ". Risultato del assertTrue: " + setOfValues.contains(dice.getValue()));
		}
	}
	
	
	@Test
	public void testToString() {
		Dice dice = new Dice(2);
		assertEquals(2, dice.getValue());
		System.out.println(dice.toString());
	}
}
