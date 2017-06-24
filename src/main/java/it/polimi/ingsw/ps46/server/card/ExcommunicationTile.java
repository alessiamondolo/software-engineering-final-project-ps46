package it.polimi.ingsw.ps46.server.card;


/**
 * This Class is used to create a single object of ExcommunicationTile, with its permanent Malus effect.
 * 
 * @author Andrea.Masi
 */


public class ExcommunicationTile {
	
	private int era;
	private MalusEffect permanentMalus;
	
	
	public ExcommunicationTile (int era, MalusEffect permanentMalus){
		
		this.era = era;
		this.permanentMalus = permanentMalus; 
		
	}
	
	
	public ExcommunicationTile() {
		era = 0;
		permanentMalus = null;
	}


	public int getEra() {
		return era;
	}

	public MalusEffect getPermanentMalusEffect() {
		return permanentMalus;
	}


}
