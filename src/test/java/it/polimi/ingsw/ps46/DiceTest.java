package it.polimi.ingsw.ps46;



import org.junit.Assert;
import org.junit.Test;

import it.polimi.ingsw.ps46.server.Dice;



public class DiceTest {

	@Test
	public void test1costruttore() {

		Dice d1 = new Dice();
		assert (1 <= d1.getValue() && d1.getValue() <= 6);

	}

	@Test
	public void test2costruttore() {
		
		Dice d1 = new Dice(4);
		Assert.assertEquals(4, d1.getValue());
	}
	
	@Test
	public void test3throwDice() {
		
		Dice d1 = new Dice();
		for(int i = 0; i < 50; i++) {
			d1.throwDice();
			assert (1 <= d1.getValue() && d1.getValue() <= 6);
		}
		
	}
	

	
	
	@Test
	public void test4toString() {
		
		Dice d1 = new Dice(4);
		assert (d1.toString().equals("4"));
		
	}


}
