package it.polimi.ingsw.ps46.client.GUI;

import javax.swing.JFrame;

/**
 * A WindowClass acts as the entry point for the game User Interface by launching the 
 * @MainWindowCLass.
 * 
 * @author lorenzo
 *
 */

public class MainWindowLauncher {  
	
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
	/**
	 *  Cascade creation of the game GUI, this method displays the main JFrame 
	 *  (MainWindow) which will contain the Board and the Users Panels.
	 */
	
	 private static void createAndShowGUI() {
	        //Create and set up the window.
		 
	        MainWindow mainWindow = new MainWindow();
	        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        mainWindow.pack();
	        mainWindow.setVisible(true);
	        
	    }
	
}
