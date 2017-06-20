package it.polimi.ingsw.ps46.client.GUI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ActionTower extends JPanel {
	
	public ActionTower() {
		
		this.setPreferredSize(new Dimension(40, 80*4));
		this.setOpaque(false);
		Border border = BorderFactory.createLineBorder(Color.ORANGE, 1);
		this.setBorder(border);
		
	}
	

}
