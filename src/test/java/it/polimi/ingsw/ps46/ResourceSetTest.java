package it.polimi.ingsw.ps46;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import it.polimi.ingsw.ps46.server.resources.CouncilPrivilege;
import it.polimi.ingsw.ps46.server.resources.FaithPoints;
import it.polimi.ingsw.ps46.server.resources.MilitaryPoints;
import it.polimi.ingsw.ps46.server.resources.Money;
import it.polimi.ingsw.ps46.server.resources.Resource;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;
import it.polimi.ingsw.ps46.server.resources.VictoryPoints;
import it.polimi.ingsw.ps46.server.resources.Wood;
import it.polimi.ingsw.ps46.server.resources.Servants;
import it.polimi.ingsw.ps46.server.resources.Stones;

public class ResourceSetTest {
	
	final static int INTVALUE0 = 0;
	final static int INTVALUE1 = 1;
	final static int INTVALUE2 = 2;
	final static int INTVALUE3 = 3;
	final static int INTVALUE4 = 4;
		
	
	@Test
	public void ResourceTest() {
		Wood wood = new Wood										(7);
		Stones stones = new Stones									(14);
		Servants servants = new Servants							(27);
		Money money = new Money										(77);
		FaithPoints faithPoints = new FaithPoints					(4);
		MilitaryPoints militaryPoints = new MilitaryPoints			(22);
		VictoryPoints victoryPoints = new VictoryPoints				(22);
		CouncilPrivilege councilPrivilege =  new CouncilPrivilege	(1);
		
		Assert.assertEquals(7,wood.getQuantity());	
		Assert.assertEquals(14, stones.getQuantity());
		Assert.assertEquals(27, servants.getQuantity());
		Assert.assertEquals(77, money.getQuantity());	
		Assert.assertEquals(4, faithPoints.getQuantity());
		Assert.assertEquals(22, militaryPoints.getQuantity());
		Assert.assertEquals(22, victoryPoints.getQuantity());	
		Assert.assertEquals(1, councilPrivilege.getQuantity());
	}
	
	
	@Test
	public void constructor1Test() {
		
		ArrayList<Resource> resourcesList = new ArrayList<>();
		
		resourcesList.add(new Wood				(7));
		resourcesList.add(new Stones			(14));
		resourcesList.add(new Servants			(27));
		resourcesList.add(new Money				(77));
		resourcesList.add(new FaithPoints		(4));
		resourcesList.add(new MilitaryPoints	(22));
		resourcesList.add(new VictoryPoints		(22));
		resourcesList.add(new CouncilPrivilege	(1));
		
		ResourceSet resourceSet = new ResourceSet(resourcesList);
		
		int index = 0;
		
		for (String string : resourceSet.getResourcesMap().keySet()) {
			if (index == 0){
				Assert.assertSame(resourceSet.getResourcesMap().get(string).getId(), "Wood");
				Assert.assertSame(string , "Wood");
				
				Assert.assertEquals(7,resourceSet.getResourcesMap().get(string).getQuantity());
				}
			else if (index == 1){
				Assert.assertSame(resourceSet.getResourcesMap().get(string).getId(), "Stones");
				Assert.assertSame(string, "Stones");

				Assert.assertEquals(14,resourceSet.getResourcesMap().get(string).getQuantity());
				}
			else if (index == 2){
				Assert.assertSame(resourceSet.getResourcesMap().get(string).getId(), "Servants");
				Assert.assertSame(string, "Servants");

				Assert.assertEquals(27,resourceSet.getResourcesMap().get(string).getQuantity());
				}
			else if (index == 3){
				Assert.assertSame(resourceSet.getResourcesMap().get(string).getId(), "Money");
				Assert.assertSame(string, "Money");

				Assert.assertEquals(77,resourceSet.getResourcesMap().get(string).getQuantity());
				}
			else if (index == 4){
				Assert.assertSame(resourceSet.getResourcesMap().get(string).getId(), "FaithPoints");
				Assert.assertSame(string, "FaithPoints");

				Assert.assertEquals(4,resourceSet.getResourcesMap().get(string).getQuantity());
				}
			else if (index == 5){
				Assert.assertSame(resourceSet.getResourcesMap().get(string).getId(), "MilitaryPoints");
				Assert.assertSame(string, "MilitaryPoints");

				Assert.assertEquals(22,resourceSet.getResourcesMap().get(string).getQuantity());
				}
			else if (index == 6){
				Assert.assertSame(resourceSet.getResourcesMap().get(string).getId(), "VictoryPoints");
				Assert.assertSame(string, "VictoryPoints");

				Assert.assertEquals(22,resourceSet.getResourcesMap().get(string).getQuantity());
				}
			else if (index == 7){
				Assert.assertSame(resourceSet.getResourcesMap().get(string).getId(), "CouncilPrivilege");
				Assert.assertSame(string, "CouncilPrivilege");

				Assert.assertEquals(1,resourceSet.getResourcesMap().get(string).getQuantity());
				}
			index++;
		}
	}
	
	@Test
	public void constructorCloneTest(){
		
		ArrayList<Resource> resourcesList = new ArrayList<>();
		
		resourcesList.add(new Wood				(INTVALUE2));
		resourcesList.add(new Stones			(INTVALUE2));
		resourcesList.add(new Servants			(INTVALUE2));
		resourcesList.add(new Money				(INTVALUE2));
		resourcesList.add(new FaithPoints		(INTVALUE2));
		resourcesList.add(new MilitaryPoints	(INTVALUE2));
		resourcesList.add(new VictoryPoints		(INTVALUE2));
		resourcesList.add(new CouncilPrivilege	(INTVALUE2));
		
		ResourceSet firstResourceSet = new ResourceSet(resourcesList);
	
		resourcesList.clear();
		resourcesList.add(new Wood				(INTVALUE1));
		resourcesList.add(new Stones			(INTVALUE1));
		resourcesList.add(new Servants			(INTVALUE1));
		resourcesList.add(new Money				(INTVALUE1));
		resourcesList.add(new FaithPoints		(INTVALUE1));
		resourcesList.add(new MilitaryPoints	(INTVALUE1));
		resourcesList.add(new VictoryPoints		(INTVALUE1));
		resourcesList.add(new CouncilPrivilege	(INTVALUE1));
		
		ResourceSet secondResourceSet = new ResourceSet(resourcesList);
	
		ResourceSet referenceCopy = firstResourceSet;
		ResourceSet deepCopy = new ResourceSet(firstResourceSet);
		
		referenceCopy.sub(secondResourceSet);
		
		for (String string : referenceCopy.getResourcesMap().keySet()) {
			Assert.assertEquals(INTVALUE1, referenceCopy.getResourcesMap().get(string).getQuantity());
		}
		
		for (String string : deepCopy.getResourcesMap().keySet()) {
			Assert.assertEquals(INTVALUE2, deepCopy.getResourcesMap().get(string).getQuantity());
		}
	}
	
	
	@Test
	public void constructorDifference(){
		
		ArrayList<Resource> resourcesList = new ArrayList<>();
		
		resourcesList.add(new Wood				(INTVALUE2));
		resourcesList.add(new Stones			(INTVALUE2));
		resourcesList.add(new Servants			(INTVALUE2));
		resourcesList.add(new Money				(INTVALUE2));
		resourcesList.add(new FaithPoints		(INTVALUE2));
		resourcesList.add(new MilitaryPoints	(INTVALUE2));
		resourcesList.add(new VictoryPoints		(INTVALUE2));
		resourcesList.add(new CouncilPrivilege	(INTVALUE2));
		
		ResourceSet firstResourceSet = new ResourceSet(resourcesList);
	
		resourcesList.clear();
		resourcesList.add(new Wood				(INTVALUE1));
		resourcesList.add(new Stones			(INTVALUE1));
		resourcesList.add(new Servants			(INTVALUE1));
		resourcesList.add(new Money				(INTVALUE1));
		resourcesList.add(new FaithPoints		(INTVALUE1));
		resourcesList.add(new MilitaryPoints	(INTVALUE1));
		resourcesList.add(new VictoryPoints		(INTVALUE1));
		resourcesList.add(new CouncilPrivilege	(INTVALUE1));
		
		ResourceSet secondResourceSet = new ResourceSet(resourcesList);
	
		ResourceSet deepCopy = new ResourceSet(firstResourceSet);
		firstResourceSet.add(secondResourceSet);
		
		ResourceSet difference = new ResourceSet(deepCopy, firstResourceSet);
		
		for (String string : difference.getResourcesMap().keySet()) {
			for (String string2 : secondResourceSet.getResourcesMap().keySet()) {
				if(string == string2){
					Assert.assertEquals(difference.getResourcesMap().get(string).getQuantity(), secondResourceSet.getResourcesMap().get(string2).getQuantity());
				}
			}
		}
	}
	
	public void sumTest() {
		
		ArrayList<Resource> resourcesList = new ArrayList<>();
		
		resourcesList.add(new Wood				(7));
		resourcesList.add(new Stones			(14));
		resourcesList.add(new Servants			(27));
		resourcesList.add(new Money				(77));
		resourcesList.add(new FaithPoints		(4));
		resourcesList.add(new MilitaryPoints	(22));
		resourcesList.add(new VictoryPoints		(22));
		resourcesList.add(new CouncilPrivilege	(1));
		
		ResourceSet firstResourceSet = new ResourceSet(resourcesList);
		
		resourcesList.clear();
		resourcesList.add(new Wood				(93));
		resourcesList.add(new Stones			(86));
		resourcesList.add(new Servants			(73));
		resourcesList.add(new Money				(23));
		resourcesList.add(new FaithPoints		(96));
		resourcesList.add(new MilitaryPoints	(78));
		resourcesList.add(new VictoryPoints		(78));
		resourcesList.add(new CouncilPrivilege	(99));
		
		ResourceSet secondResourceSet = new ResourceSet(resourcesList);

		firstResourceSet.add(secondResourceSet);
		
		for (String string : firstResourceSet.getResourcesMap().keySet()) {
			Assert.assertEquals(100, firstResourceSet.getResourcesMap().get(string).getQuantity());
		}
	}
	
	public void subTest() {
		
		ArrayList<Resource> resourcesList = new ArrayList<>();
		
		resourcesList.add(new Wood				(7));
		resourcesList.add(new Stones			(14));
		resourcesList.add(new Servants			(27));
		resourcesList.add(new Money				(77));
		resourcesList.add(new FaithPoints		(4));
		resourcesList.add(new MilitaryPoints	(22));
		resourcesList.add(new VictoryPoints		(22));
		resourcesList.add(new CouncilPrivilege	(100));
		
		ResourceSet firstResourceSet = new ResourceSet(resourcesList);
		
		resourcesList.clear();
		resourcesList.add(new Wood				(7));
		resourcesList.add(new Stones			(14));
		resourcesList.add(new Servants			(27));
		resourcesList.add(new Money				(77));
		resourcesList.add(new FaithPoints		(4));
		resourcesList.add(new MilitaryPoints	(22));
		resourcesList.add(new VictoryPoints		(22));
		resourcesList.add(new CouncilPrivilege	(0));
		
		ResourceSet secondResourceSet = new ResourceSet(resourcesList);

		firstResourceSet.sub(secondResourceSet);
		
		for (String string : firstResourceSet.getResourcesMap().keySet()) {
			Assert.assertEquals(0, firstResourceSet.getResourcesMap().get(string).getQuantity());
		}
		
		resourcesList.clear();
		resourcesList.add(new Wood				(1));
		resourcesList.add(new Stones			(1));
		resourcesList.add(new Servants			(1));
		resourcesList.add(new Money				(1));
		resourcesList.add(new FaithPoints		(1));
		resourcesList.add(new MilitaryPoints	(1));
		resourcesList.add(new VictoryPoints		(1));
		resourcesList.add(new CouncilPrivilege	(1));
		
		ResourceSet thirdResourceSet = new ResourceSet(resourcesList);

		thirdResourceSet.sub(firstResourceSet);
		
		for (String string : thirdResourceSet.getResourcesMap().keySet()) {
			Assert.assertEquals(1, thirdResourceSet.getResourcesMap().get(string).getQuantity());
		}
	}
	
	public void greaterOrEqualTest(){
		ArrayList<Resource> resourcesList = new ArrayList<>();
		
		resourcesList.add(new Wood				(INTVALUE2));
		resourcesList.add(new Stones			(INTVALUE2));
		resourcesList.add(new Servants			(INTVALUE2));
		resourcesList.add(new Money				(INTVALUE2));
		resourcesList.add(new FaithPoints		(INTVALUE2));
		resourcesList.add(new MilitaryPoints	(INTVALUE2));
		resourcesList.add(new VictoryPoints		(INTVALUE2));
		resourcesList.add(new CouncilPrivilege	(INTVALUE2));
		
		ResourceSet firstResourceSet = new ResourceSet(resourcesList);
	
		resourcesList.clear();
		resourcesList.add(new Wood				(INTVALUE0));
		resourcesList.add(new Stones			(INTVALUE2));
		resourcesList.add(new Servants			(INTVALUE2));
		resourcesList.add(new Money				(INTVALUE2));
		resourcesList.add(new FaithPoints		(INTVALUE2));
		resourcesList.add(new MilitaryPoints	(INTVALUE2));
		resourcesList.add(new VictoryPoints		(INTVALUE2));
		resourcesList.add(new CouncilPrivilege	(INTVALUE2));
		
		ResourceSet secondResourceSet = new ResourceSet(resourcesList);
		assertTrue(firstResourceSet.greaterOrEqual(secondResourceSet));
		assertFalse(secondResourceSet.greaterOrEqual(firstResourceSet));
	}
}
