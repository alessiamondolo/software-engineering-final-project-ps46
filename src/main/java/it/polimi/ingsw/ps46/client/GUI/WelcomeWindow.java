package it.polimi.ingsw.ps46.client.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.polimi.ingsw.ps46.server.BonusTile;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;


public class WelcomeWindow extends JFrame {

	/**
	 * 
	 */
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();

	private JTextField userText;
	private JLabel userLabel;
	private JLabel imageLabel;
	private JButton okButton = new JButton("OK");
	private String playerUsername;
	private String playerColor;
	JLabel waitLabel;

	
	
	private static final long serialVersionUID = 471361046696464958L;
	
/*	public static void main(String[] args) {
		
		new WelcomeWindow();	
	}*/

	public WelcomeWindow() {
		
		this.add(panel);
		this.setTitle("Welcome to Lorenzo Il Magnifico");
		this.setPreferredSize(new Dimension (700, 700));
		panel.setLayout(new BorderLayout());
		
		// set image icon for the panel
		this.imageLabel = new JLabel();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("img/dashboard_back/punchboard_b_c_03.jpg"));
		} catch (IOException e) {	
			e.printStackTrace();
		}
		Image img = image.getScaledInstance(690, 400, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(img);
		imageLabel.setIcon(imageIcon);	
		panel.add(imageLabel, BorderLayout.NORTH);
		this.setVisible(true);
	}
	

	
	private int intBonusTile;
	
	public void setBonusTile(Game game) {
		
		
		ButtonGroup bonusTilesGroup;
		ArrayList<Integer> bonusTiles = new ArrayList<Integer>();
		
		JPanel panel1 = new JPanel();
		this.setPreferredSize(new Dimension (700, 600));
		
		
		panel.remove(imageLabel);
		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.CENTER);
		panel.add(okButton, BorderLayout.PAGE_END);
		userLabel = new JLabel("Choose your bonus tile");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel2.setLayout(new BorderLayout());
		panel2.add(userLabel, BorderLayout.NORTH);
		panel2.add(panel3, BorderLayout.CENTER);
	
		WelcomeWindow window = this;
		bonusTilesGroup = new ButtonGroup();
			
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object mon;
				synchronized (mon = GUIView.getMonitor()) {
					GUIView.setBonusTile(window.intBonusTile);
					
					mon.notifyAll();
				}
			}
			
		});
		
		
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 30));
	
		int bTile;
		
		
		for(  bTile = 1; bTile < game.getBonusTiles().size() ; bTile++ ) {
			
			
			bonusTiles.add(bTile);
			String path = "mixed/personalbonustile_" +game.getBonusTiles().get(bTile).getId() + ".png";
			
			BufferedImage img = null;
			try {
				img = Token.getImagePathMode(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			JLabel tile = new JLabel();
			tile.setIcon(new ImageIcon(img.getScaledInstance(50, 300, Image.SCALE_SMOOTH)));
			panel1.add(tile);
			
		}
		
	
		for ( bTile = 1;  bTile < game.getBonusTiles().size() ; bTile++) {
			JRadioButton button = new JRadioButton("Bonus Tile " +
					(String.valueOf(game.getBonusTiles().get(bTile).getId())));
			bonusTilesGroup.add(button);
			int i = bTile;
			
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					window.intBonusTile = i;
				}
				
			});
			
			panel3.add(button);
		}
	
		this.pack();
		this.setVisible(true);
		
	}
	
	
	public void setPlayerUsername() {
		
		userLabel = new JLabel("Insert your username");
		panel2.add(userLabel);

		userText = new JTextField(25);
		panel2.add(userText);
		okButton = new JButton("OK");
		panel.add(okButton, BorderLayout.SOUTH);
		
		panel.add(panel2, BorderLayout.CENTER);
		WelcomeWindow window = this;
		this.pack();
		this.setVisible(true);
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object mon;
				synchronized (mon = GUIView.getMonitor()) {
					window.playerUsername = userText.getText();
					System.out.println(playerUsername);
					GUIView.setUsername(window.playerUsername);
					panel2.remove(userLabel);
					panel2.remove(userText);
					panel.remove(okButton);
					waitLabel = new JLabel("Wait for other players' color choices...");
					
					panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
				
					panel2.add(waitLabel);
					
					repaint();
					
					mon.notifyAll();
					
				}
			}
			
		});
		
	}
	
	/**
	 * Show player's initial turn order
	 */

	public void showInitialOrder(Game game) {
		
		JPanel orderPanel = new JPanel();
		orderPanel.setLayout(new BorderLayout());
		
		
		JLabel label = new JLabel("Initial Game order:");
		label.setFont(new Font("Arial", Font.BOLD, 14));
		orderPanel.add(label, BorderLayout.PAGE_START);
		panel2.setLayout(new BorderLayout());
		
		int position = 1;
		JPanel players = new JPanel();
		for(Player player : game.getPlayers()){
			JLabel labelPlayer = new JLabel(position + ". " + player.getUsername());
			position ++;
			players.add(labelPlayer);
		}
		orderPanel.add(players, BorderLayout.CENTER);
		panel2.add(orderPanel, BorderLayout.NORTH);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel panel3 = new JPanel();
	private JLabel colorLabel = null;
	private ButtonGroup colorGroup;
	
	/**
	 * A method to get player's color choice via JRadioButton menù
	 * @param colors
	 */
	
	public void setColors(ArrayList<String> colors) {
		
		
		if (colorLabel == null) {
			colorLabel = new JLabel("Choose your color:");
			panel3.add(colorLabel);
		}
		
		panel2.remove(waitLabel);
		
		colorGroup = new ButtonGroup();
	
		WelcomeWindow window = this;
		
		okButton = new JButton("OK");
		panel.add(okButton, BorderLayout.SOUTH);
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object mon;
				synchronized (mon = GUIView.getMonitor()) {
					GUIView.setColor(window.playerColor);
					
					mon.notifyAll();
				}
			}
			
		});
		
		for (String color : colors) {
			JRadioButton button = new JRadioButton(color);
			colorGroup.add(button);

			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					window.playerColor = color;
				}
				
			});
			
			panel3.add(button);
		}
		
		panel2.add(panel3, BorderLayout.SOUTH);
		panel.add(panel2, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		
	}
	
}
