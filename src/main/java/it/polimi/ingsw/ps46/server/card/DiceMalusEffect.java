package it.polimi.ingsw.ps46.server.card;


import it.polimi.ingsw.ps46.server.Dice;

/**
 * This Class extends the class MalusEffect.
 * Its used to implement the behavior of some ExcommunicationTile giving some DiceMalus to the player who get the excomminication.
 * 
 * @author Andrea.Masi
 */

public class DiceMalusEffect extends MalusEffect {
	
	private String type;
	private Dice malus;
	
	
	/**
	 * This Constructor creates a new object DiceMalusEffect.
	 * Built by .json file.
	 * 
	 * @param name
	 * @param type
	 * @param malus
	 */
	public DiceMalusEffect (String name, String type, Dice malus){
		
		this.name = name;
		this.type = type;
		this.malus = malus;	
	}
	
	public DiceMalusEffect(){ 
		
		name = "DiceMalusEffect";
		type = null;
		malus = null;
	}


	
	
	public String getType() {
		return type;
	}

	public Dice getMalus() {
		return malus;
	}
	
	public int getMalusValue(){
		
		return malus.getValue();
	}
	
}
