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

import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.Player;


public class WelcomeWindow extends JFrame {

	/**
	 * 
	 */
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel gameModePanel = new JPanel();
	private JTextField userText;
	private JLabel userLabel;
	private JButton okButton = new JButton("OK");
	private String playerUsername;
	private String playerColor;
	private String gameMode;
	JLabel waitLabel;
	
	
	
	private static final long serialVersionUID = 471361046696464958L;
	
/*	public static void main(String[] args) {
		
		new WelcomeWindow();	
	}*/

	public WelcomeWindow() {
		
		this.add(panel);
		this.setTitle("Welcome to Lorenzo Il Magnifico");
		this.setPreferredSize(new Dimension (510, 500));
		panel.setLayout(new BorderLayout());
		
		// set image icon for the panel
		JLabel imageLabel = new JLabel();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("img/dashboard_back/punchboard_b_c_03.jpg"));
		} catch (IOException e) {	
			e.printStackTrace();
		}
		Image img = image.getScaledInstance(500, 351, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(img);
		imageLabel.setIcon(imageIcon);	
		panel.add(imageLabel, BorderLayout.NORTH);
		panel.add(okButton, BorderLayout.SOUTH);		
		
/*	ArrayList<String> colors = new ArrayList<String>();
		colors.add("White");
		colors.add("Blue");
		this.setPlayerUsername();
		this.setColors(colors);
		this.setVisible(true);*/
	}
	
	

	private JLabel mode = null;
	private ButtonGroup modeButtonGroup;
	
	public void setGameMode() {
		

		if (mode == null) {
			mode = new JLabel("Choose game mode:");
			gameModePanel.add(mode);
		}
		
		modeButtonGroup = new ButtonGroup();
	
		WelcomeWindow window = this;
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object mon;
				synchronized (mon = GUIView.getMonitor()) {
					GUIView.setGameMode(window.gameMode);
					gameModePanel.remove(mode);
					panel2.remove(gameModePanel);
					panel.remove(okButton);
					mon.notifyAll();
				}
			}
			
		});
		
		
			JRadioButton button = new JRadioButton("Basic Mode");
			modeButtonGroup.add(button);
			
			JRadioButton button2 = new JRadioButton("Advanced Mode");
			modeButtonGroup.add(button2);

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					window.gameMode = "BASIC_GAME_MODE";
				}
			});
			
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					window.gameMode = "ADVANCED_GAME_MODE";
				}
			});
			
			gameModePanel.add(button);
			gameModePanel.add(button2);
		
		panel2.add(gameModePanel);
		panel.add(panel2, BorderLayout.CENTER);
		
		
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
		
		
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object mon;
				synchronized (mon = GUIView.getMonitor()) {
					window.playerUsername = userText.getText();
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
		
		int position = 1;
		JPanel players = new JPanel();
		for(Player player : game.getPlayers()){
			JLabel labelPlayer = new JLabel(position + ". " + player.getUsername());
			position ++;
			players.add(labelPlayer);
		}
		orderPanel.add(players, BorderLayout.CENTER);
		panel2.add(orderPanel);
	}
	
	private JPanel panel3 = new JPanel();
	private JLabel colorLabel = null;
	private ButtonGroup colorGroup;
	
	/**
	 * A method to get player's color choice via JRadioButton menù
	 * @param colors
	 */
	
	public void setColors(ArrayList<String> colors) {
		
		panel2.remove(waitLabel);
		if (colorLabel == null) {
			colorLabel = new JLabel("Choose your color:");
			panel3.add(colorLabel);
		}
		
		
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
		
		panel2.add(panel3);
		panel.add(panel2, BorderLayout.CENTER);
		
	}
	
}
