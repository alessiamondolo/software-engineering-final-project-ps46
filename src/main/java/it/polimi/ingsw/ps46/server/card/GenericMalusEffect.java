package it.polimi.ingsw.ps46.server.card;

/**
 * Description of the Class GenericMalusEffect.
 * 
 * @author Andrea.Masi
 */

public class GenericMalusEffect extends MalusEffect{

	private String type;
	
	public GenericMalusEffect(){
		name = null;
		type = null;	
	}
	
	public GenericMalusEffect(String name, String type){
		this.name = name;
		this.type = type;
		
	}

	public String getType() {
		return type;
	}

}
