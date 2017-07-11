package it.polimi.ingsw.ps46;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.FamilyMember;

public class FamilyMemberTest {
	private FamilyMember familyMemberWhite; 
	private	FamilyMember familyMemberBlack;
	private FamilyMember familyMemberOrange;
	private FamilyMember familyMemberNeutral;
	private Map <String, Dice > bonus;
	private Map <String, Dice > malus;
	private static final int CONSTANT = 3;
	
	@Before
	public void setup() {
		String white = "white";
		String black = "black";
		String orange = "orange";
		String neutral = "neutral";		
		
		familyMemberWhite = new FamilyMember(white);
		familyMemberWhite.setValueOfFamilyMember(new Dice(CONSTANT));
		familyMemberBlack = new FamilyMember(black);
		familyMemberBlack.setValueOfFamilyMember(new Dice(CONSTANT));
		familyMemberOrange = new FamilyMember(orange);
		familyMemberOrange.setValueOfFamilyMember(new Dice(CONSTANT));
		familyMemberNeutral = new FamilyMember(neutral);
		familyMemberNeutral.setValueOfFamilyMember(new Dice(CONSTANT));
		
		bonus = new HashMap<>();
		bonus.put(neutral, new Dice(4));
		bonus.put(orange, new Dice(3));
		bonus.put(black, new Dice(3));
		bonus.put(white, new Dice(1));

		malus = new HashMap<>();
		malus.put(neutral, new Dice(1));
		malus.put(orange, new Dice(1));
		malus.put(black, new Dice(1));
		malus.put(white, new Dice(1));


	System.out.println("END SETTING FAMILY MEMBERS \n");
		System.out.println("value of familyMember " + familyMemberWhite.getColor() + " : " + familyMemberWhite.getValueFamilyMember().toString());
		System.out.println("value of familyMember " + familyMemberBlack.getColor() + " : " + familyMemberBlack.getValueFamilyMember().toString());
		System.out.println("value of familyMember " + familyMemberOrange.getColor() + " : " + familyMemberOrange.getValueFamilyMember().toString());
		System.out.println("value of familyMember " + familyMemberNeutral.getColor() + " : " + familyMemberNeutral.getValueFamilyMember().toString());
		
	}
	
	@Test
	public void constructorTest() {
		
		assertSame("black", familyMemberBlack.getColor());
		assertSame("neutral", familyMemberNeutral.getColor());
		assertSame("orange", familyMemberOrange.getColor());
		assertSame("white", familyMemberWhite.getColor());



	}
	
	
	@Test
	public void setTest() {
		assertEquals(CONSTANT, familyMemberBlack.getValueFamilyMember().getValue());
		assertEquals(CONSTANT, familyMemberNeutral.getValueFamilyMember().getValue());
		assertEquals(CONSTANT, familyMemberOrange.getValueFamilyMember().getValue());
		assertEquals(CONSTANT, familyMemberWhite.getValueFamilyMember().getValue());

	}
	
	@Test
	public void sumTest() {
		
		for (String string : bonus.keySet()) {
			if(string.equals( "neutral"))
				familyMemberNeutral.getValueFamilyMember().sumDice(bonus.get(string));
			if(string.equals( "orange"))
				familyMemberOrange.getValueFamilyMember().sumDice(bonus.get(string));
			if(string.equals( "black"))
				familyMemberBlack.getValueFamilyMember().sumDice(bonus.get(string));
			if(string.equals( "white"))
				familyMemberWhite.getValueFamilyMember().sumDice(bonus.get(string));
		}
		
		assertEquals(7, familyMemberNeutral.getValueFamilyMember().getValue());
		assertEquals(6, familyMemberOrange.getValueFamilyMember().getValue());
		assertEquals(6, familyMemberBlack.getValueFamilyMember().getValue());
		assertEquals(4, familyMemberWhite.getValueFamilyMember().getValue());
		System.out.println("\nStampo sumTest\n");
		System.out.println("value of familyMember " + familyMemberOrange.getColor() + " : " + familyMemberOrange.getValueFamilyMember().toString());
		System.out.println("value of familyMember " + familyMemberNeutral.getColor() + " : " + familyMemberNeutral.getValueFamilyMember().toString());
		System.out.println("value of familyMember " + familyMemberWhite.getColor() + " : " + familyMemberWhite.getValueFamilyMember().toString());
		System.out.println("value of familyMember " + familyMemberBlack.getColor() + " : " + familyMemberBlack.getValueFamilyMember().toString());


	}
}
