package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

//card layout dopo per navigare tra le varie dashboard giocatore

/**
 * A dashboard that allows the player to visualizes resources and cards. 
 * @author lorenzo
 *
 */

public class PlayerDashboard extends JPanel{

	private String playerUsername;
	private Dimension dashboardDimension;
	
	public PlayerDashboard(String playerUsername, Dimension playerAreaDimension) {
		
		double dashBoardHeight = (playerAreaDimension.getHeight()*8)/28;
		double dashBoardWidth = (playerAreaDimension.getWidth()*12)/13;
		
		this.dashboardDimension = new Dimension((int) dashBoardWidth, (int) dashBoardHeight);
		
		System.out.println(String.valueOf(dashBoardHeight));
		System.out.println(String.valueOf(dashBoardWidth));
		
		this.playerUsername = playerUsername;
		
		this.add(createDashboard());
		
		
	}

	/**
	 * Arrange a GridBagLayout in which are placed the dashboard image and CardCells
	 * @return
	 */
	
	private JPanel createDashboard() {
		
		//la prima cosa da fare è strutturare il gridbaglayout
		
		JPanel dashboard = new JPanel();
		dashboard.setPreferredSize(dashboardDimension);
		GridBagLayout gbl = new GridBagLayout();
		Border border = BorderFactory.createLineBorder(Color.RED, 1);
		dashboard.setBorder(border);
		
		
		dashboard.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		
		
		
		
		
	
		
		
		
		//La image prenderà solo tot posti della griglia in quanto ci sono a destra altre carte viola e blu

		
		
		//dashboardImage.setIcon(returndashboardImage(boardDimension));
		//
		/*gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;  
		gbc.gridwidth = 23;
		gbc.gridheight = 33;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(dashboardImage, gbc);*/
		
		return dashboard;
		
	}
	
	/*	private ImageIcon returndashboardImage(Dimension boardDimension) {
	
	 BufferedImage image = null;
	try {
		image = ImageIO.read(getClass().getResource("gameboard.png"));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	Image img = image.getScaledInstance((int) boardDimension.getWidth(), (int) boardDimension.getHeight(), Image.SCALE_SMOOTH);
	//Image img = image.getScaledInstance(471, 650, Image.SCALE_SMOOTH);
	
	ImageIcon imageIcon = new ImageIcon(img);
   String path = this.getClass().getClassLoader().getResource("gameboard.png").toExternalForm();
	System.out.println(path);
   
	return imageIcon;
	
}*/
}
