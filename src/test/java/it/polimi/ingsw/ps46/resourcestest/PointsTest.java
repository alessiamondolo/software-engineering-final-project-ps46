package it.polimi.ingsw.ps46.resourcestest;

import static org.junit.Assert.*;

import org.junit.*;
import it.polimi.ingsw.ps46.server.resources.FaithPoints;
import it.polimi.ingsw.ps46.server.resources.MilitaryPoints;
import it.polimi.ingsw.ps46.server.resources.VictoryPoints;
import it.polimi.ingsw.ps46.server.resources.Resource;

public class PointsTest {


		FaithPoints faithp = new FaithPoints(4);
		MilitaryPoints militaryp = new MilitaryPoints(78);
		VictoryPoints victoryp = new VictoryPoints(49);	
		
	
	@Test
	public void pointsTest() {
		Assert.assertEquals(faithp.getQuantity(), 4);	
		Assert.assertEquals(militaryp.getQuantity(), 78);
		Assert.assertEquals(victoryp.getQuantity(), 49);
	}

}
