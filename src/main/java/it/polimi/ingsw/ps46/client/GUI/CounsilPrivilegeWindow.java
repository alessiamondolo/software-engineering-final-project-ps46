package it.polimi.ingsw.ps46.client.GUI;

import javax.swing.*;

/**
 * JOptionPane showInputDialog example #3.
 * Using a combo box in an input dialog (showInputDialog).
 * 
 * @author alvin alexander, http://alvinalexander.com
 */
public class CounsilPrivilegeWindow {
	
  public static final String[] privilegesTypes = { "Wood: 1 - Stones: 1", "Servants: 2", "Money: 2", "MilitaryPoints : 1" };

  public static void main(String[] args)
  {
    JFrame frame = new JFrame("Counsil Privilege");
    String choice = (String) JOptionPane.showInputDialog(frame, 
        "Select the Counsil Privilege",
        "Type",
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        privilegesTypes, 
        privilegesTypes[0]);

    // favoritePizza will be null if the user clicks Cancel
    System.out.printf("Privilege chosen is %s.\n", choice);
   
  }

}
