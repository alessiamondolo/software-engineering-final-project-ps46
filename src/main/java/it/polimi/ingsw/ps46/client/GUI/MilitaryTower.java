package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class MilitaryTower extends JPanel {

	
	public MilitaryTower() {
		
		Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
		this.setBorder(border);
		this.setOpaque(false);
		
		//probabilmente il layout più intelligente qui sarà BoxLayout 
		//con una serie di jLabel per casella
	}
}
