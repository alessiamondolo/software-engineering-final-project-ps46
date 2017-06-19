package it.polimi.ingsw.ps46.client.GUI;
import javax.swing.JFrame;

public class BoardLauncher {  //forse dovrei creare un Window Launcher che appunto istanzia la Window e poi successivamente questo

	
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
	 private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Board");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Set up the content pane.
	        frame.add(new MainBoard());
			

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	        
	    }
	
}
