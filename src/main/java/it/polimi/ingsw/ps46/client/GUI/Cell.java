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
 * An abstract cell of the board. The board is made of different cells characterized
 * by different functionalities and attributes. Each concrete implementations needs 
 * to define the setAvailability method based on the cell type specific requirements. 
 * @author lorenzo
 *
 */

public abstract class Cell<T> extends JButton {
	

	
	private static final long serialVersionUID = -4753827311083808447L;
	
	protected ArrayList<T> itemList;
	
	public Cell () {
		
		itemList = new ArrayList<T> ();
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.setBorder(border);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setRolloverEnabled(false);
		this.setEnabled(false);
		this.setContentAreaFilled(false);
	}
	
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
