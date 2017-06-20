package it.polimi.ingsw.ps46.server.card;


/**
 * This Class extends MalusEffect.
 * Used to implement the most generic MalusEffects of the ExcommunicationTales.
 * 
 * @author Andrea.Masi
 */

public class GenericMalusEffect extends MalusEffect{

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

	public String getType() {
		return type;
	}

}
