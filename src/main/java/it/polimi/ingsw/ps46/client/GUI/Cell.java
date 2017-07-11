package it.polimi.ingsw.ps46.client.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * An abstract parameterized cell of the board. It has an arrayList to store and visualize 
 * player's tokens that are placed inside. 
 * The board is made of two sub types of cell, 
 * each one needs to implement the abstract method update in order to display the token 
 * that are stored in the attribute itemList
 * @author lorenzo
 *
 */

public abstract class Cell<T> extends JButton {
	

	
	private static final long serialVersionUID = -4753827311083808447L;
	
	protected ArrayList<T> itemList;
	
	/**
	 * Basic constructor that sets the fundamental layout
	 */
	public Cell () {
		
		itemList = new ArrayList<T> ();
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.setBorder(border);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setRolloverEnabled(false);
		this.setEnabled(false);
		this.setContentAreaFilled(false);
	}
	
	/**
	 * Specific constructor used to create cells that need to send information 
	 * to the external infrastructure. An action listener to catch clicks is added
	 * to the cell.
	 * @param action : the value of the int cell needs to be specified in order 
	 * for the cell to know what to send back whenever clicked 
	 */
	
	public Cell (int action) {
		this();
		
		this.setEnabled(true);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object mon;
				synchronized (mon = GUIView.getMonitor()) {
					GUIView.setAction(action);
					mon.notifyAll();
				}
			}
		});
	}
	
	/**
	 *	Used to specify what Family Member the user wants to use to complete an action.
	 * @param fmColor : the type of family member, its dice's value
	 */
	public Cell (String fmColor) {
		this();
		
		this.setEnabled(true);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object mon;
				synchronized (mon = GUIView.getMonitor()) {
					GUIView.setFamilyMember(fmColor);
					mon.notifyAll();
				}
			}
		});
	}

	public void add(T el) {
		itemList.add(el);
		update();
	}
	
	public void remove(T el) {
		itemList.remove(el);
		update();
	}
	
	@Override
	public void removeAll() {
		if (itemList.isEmpty() == false) {
			itemList.clear();
			update();
		}
	}
	
	public ArrayList<T> getItemList() {
		return this.itemList;
	}
	
	public abstract void update();
}
