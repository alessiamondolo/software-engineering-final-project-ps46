package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class LowerPiece extends JPanel {

	public LowerPiece(double widthSmall, double heightSmall) {
		
		this.setPreferredSize(new Dimension((int) widthSmall*17, (int) heightSmall*13));
		this.setLayout(new GridBagLayout());
		
		for (int i = 0; i < 3; i ++) {
			
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
			panel.setBorder(border);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			if ( i == 0) {
				gbc.gridx = 0;
				gbc.gridy = i;
				gbc.weightx = 1;
				gbc.weighty = 0.7;
				gbc.fill = GridBagConstraints.BOTH;
				this.add(panel, gbc);	
			}
			if ( i == 1) {
				gbc.gridx = 0;
				gbc.gridy = i;
				gbc.weightx = 1;
				gbc.weighty = 0.3;
				gbc.fill = GridBagConstraints.BOTH;
				this.add(panel, gbc);
			}
			if ( i == 2) {
				gbc.gridx = 0;
				gbc.gridy = i;
				gbc.weightx = 1;
				gbc.weighty = 0.7;
				gbc.fill = GridBagConstraints.BOTH;
				this.add(panel, gbc);
			}
		}

	}
}
	
