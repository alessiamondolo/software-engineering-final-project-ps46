package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.GameState;

public class infoArea extends JPanel {
	
	int width;
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	public infoArea(Dimension dimension) {
		
		this.setOpaque(true);
		this.setBackground(new Color(200, 134, 145, 123));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Game State Info:"));
		this.width = dimension.width;

		GridBagLayout panelGridBagLayout = new GridBagLayout();

		this.setLayout(panelGridBagLayout);

		addLabelAndTextField("Current Period:", 0, 0, this);
		addLabelAndTextField("Current Turn:", 0, 1, this);
		addLabelAndTextField("Current Phase:", 2, 0, this);
		addLabelAndTextField("Game State:", 2, 1, this);
	}
	
	private void addLabelAndTextField(String labelText, int xPos, int yPos, Container containingPanel) {

		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		
	    JLabel label = new JLabel(labelText);
	    label.setBorder(border);
	    
	    GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
	    gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForLabel.insets = new Insets(0, width*4/65, 0, width/130);
	    gridBagConstraintForLabel.gridx = xPos;
	    gridBagConstraintForLabel.gridy = yPos;
	    containingPanel.add(label, gridBagConstraintForLabel);

	    JLabel textField = new JLabel("");
	    textField.setBorder(border);
	    labels.add(textField);
	    GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
	    gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForTextField.insets = new Insets(0, width/130, 0, width/130);
	    gridBagConstraintForTextField.gridx = xPos + 1;
	    gridBagConstraintForTextField.gridy = yPos;
	    containingPanel.add(textField, gridBagConstraintForTextField);
	}
	
	public void update(Game game) {
		
		int period = game.getCurrentPeriod();
		int round = game.getCurrentRound();
		int phase = game.getCurrentPhase();
		GameState gameState = game.getGameState();
		
		for (int i = 0; i < labels.size(); i++) {
			
			JLabel label = labels.get(i);
			if ( i == 0) {
				label.setText(String.valueOf(period));
			} else if ( i == 1) {
				label.setText(String.valueOf(round));
			} else if ( i == 2) {
				if (phase == 0) {
					label.setText("Round Setup");
				} else if (phase == 1) {
					label.setText("Actions");
				} else if (phase == 2) {
					label.setText("Vatican Report");
				} else if (phase == 3) {
					label.setText("Round End");
				}
			} else if ( i == 3) {
				label.setText(gameState.toString());
			}
		}
	}
}
