package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.Border;

/**
 * An abstract cell of the board. The board is made of different cells characterized
 * by different functionalities and attributes. Each concrete implementations needs 
 * to define the setAvailability method based on the cell type specific requirements. 
 * @author lorenzo
 *
 */

public abstract class Cell<T> extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4753827311083808447L;
	
	protected ArrayList<T> itemList;
	
	public Cell () {
		
		itemList = new ArrayList<T> ();
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.setBorder(border);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setRolloverEnabled(false);
		this.setContentAreaFilled(false);
		
		this.removeAll();
		
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		// repaint();
	}
	
	public void add(T el) {
		itemList.add(el);
	}
	
	public void remove(T el) {
		itemList.remove(el);
	}
	
}
