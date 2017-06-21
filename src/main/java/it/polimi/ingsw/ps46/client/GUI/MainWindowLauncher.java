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
	
	
	// Questa classe dovrà essere trasformata in Window Launcher
	
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
	 *  Cascade creation of the game GUI, this method creates the main JFrame
	 *  which will contain the Board and the Users Panels.
	 */
	
	 private static void createAndShowGUI() {
	        //Create and set up the window.
		 
		 /*
		  * This method should create an Object of type Window which 
		  * extends JFrame and where MainBoard and PlayerDashboards
		  * are placed
		  * 
		  */
		 
	        JFrame frame = new JFrame("Board");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Set up the content pane.
	        frame.add(new MainBoard());
			

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	        
	    }
	
}
