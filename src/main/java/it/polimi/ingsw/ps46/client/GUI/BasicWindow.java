package it.polimi.ingsw.ps46.client.GUI;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class BasicWindow extends JFrame {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4652712246887673573L;

	private JPanel panel1 = new JPanel(); 
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JTextField inputForm;
	private JButton playGameButton = new JButton("Play");
	private Image image;
	private JLabel picLabel;
	
	
	public BasicWindow() {
		
		initUI();
	
	}
	
	public void initUI() {
		
		setTitle("Lorenzo Il Magnifico");	

		setPreferredSize(new Dimension(700, 600));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		playGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		
		panel1.add(playGameButton);
		//Container framework = getContentPane();
		//framework.setBackground(Color.BLUE);
		
			
//	
//		ImageIcon webIcon = new ImageIcon(this.getClass().getResource("/images/coin_1_front.png"));
//
//		
//		setIconImage(webIcon.getImage().getScaledInstance(33, 33, Image.SCALE_SMOOTH));
//		System.out.println("Icon dimensions are " + webIcon.getIconWidth() + " by " + webIcon.getIconHeight());
	
		try {
			image = ImageIO.read(getClass().getResource("/Lorenzo_Punchboard_BACK_compressed/punchboard_b_c_03.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		
		setLayout(new BorderLayout());
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		panel1.setBackground(Color.BLUE);
		
		pack();
	

		image = image.getScaledInstance(panel2.getWidth(), panel2.getHeight(),Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(image));
		
		panel2.add(picLabel);

	}
	
	public void startGame() {
		panel1.setVisible(false);
		panel2.setVisible(false);
		setSize(300, 50);
		inputForm = new JTextField("Insert here your username");
		panel3.add(inputForm);
		add(panel3, BorderLayout.CENTER);
		

		inputForm.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	String content = inputForm.getText();
		        System.out.println("The entered text is: " + inputForm.getText());
		    }
		});
		
		
	}
	

	public static void main (String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BasicWindow firstWindow = new BasicWindow();
				firstWindow.setLocationRelativeTo(null);
			    firstWindow.setVisible(true);
			}
		  
		});
		
	}
/*	
	public static void main(String[] args) {
	    JFrame frame = new JFrame("Glossary");
	    frame.setSize(400, 200);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JButton LookUpWord = new JButton("Look up word");  // create the button
	    JPanel panel1 = new JPanel();  // create the panel
	    panel1.add(LookUpWord);  // add the button to the panel
	    frame.add(panel1, BorderLayout.NORTH);  // add the panel to the frame

	    JButton SubmitNewWord = new JButton("Submit word");
	    JPanel panel2 = new JPanel();
	    panel2.add(SubmitNewWord);
	    frame.add(panel2, BorderLayout.SOUTH);

	    frame.setVisible(true);
	}
	*/
	
}
