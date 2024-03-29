package it.polimi.ingsw.ps46.server.card;

import it.polimi.ingsw.ps46.server.Game;

/**
 * This Class extends MalusEffect.
 * Used to implement the most generic MalusEffects of the ExcommunicationTales.
 * 
 * @author Andrea.Masi
 */

public class GenericMalusEffect extends MalusEffect{

	private static final long serialVersionUID = -5303989501452852134L;
	
	private String type;
	
	public GenericMalusEffect(){
		name = null;
		type = null;	
	}
	

	
	/**
	 * This Constructor creates a new object GenericMalusEffect.
	 * Built by .json file.	 
	 * 
	 * @param name
	 * @param type
	 */
	public GenericMalusEffect(String name, String type){
		this.name = name;
		this.type = type;
		
	}

	
	@Override
	public void activationMalus(Game game){
		
		game.getCurrentPlayer().getGenericMalus().put(name, this);

	}
	
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "Name: " + name  + ", type: " + type;
	}

}
