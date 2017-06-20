package it.polimi.ingsw.ps46.server.card;


import it.polimi.ingsw.ps46.server.Dice;

/**
 * Description of the Class DiceMalusEffect.
 * 
 * @author Andrea.Masi
 */

public class DiceMalusEffect extends MalusEffect {
	
	private String type;
	private Dice malus;
	
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
