package it.polimi.ingsw.ps46.server.card;


import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;

/**
 * This Class extends the class MalusEffect.
 * Its used to implement the behavior of some ExcommunicationTile giving some DiceMalus to the player who get the excomminication.
 * 
 * @author Andrea.Masi
 */

public class DiceMalusEffect extends MalusEffect {
	
	private static final long serialVersionUID = 2766283436360709364L;
	
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

	
	
	@Override
	public void activationMalus(Game game){
		
		game.getCurrentPlayer().getDiceMalus().put(name, this);
	}

	public String getType() {
		return type;
	}

	public Dice getMalus() {
		return malus;
	}
	
	@Override
	public String toString() {
		return "Name: " + name  + ", type: " + type + ", malus: " + malus;
	}


}
