package it.polimi.ingsw.ps46.server.card;

import java.io.Serializable;

/**
 * This Class is used to create a single object of ExcommunicationTile, with its permanent Malus effect.
 * 
 * @author Andrea.Masi
 */


public class ExcommunicationTile implements Serializable {
	
	private static final long serialVersionUID = -4348291631021816102L;
	
	private int era;
	private MalusEffect permanentMalus;
	
	
	public ExcommunicationTile (int era, MalusEffect permanentMalus){
		
		this.era = era;
		this.permanentMalus = permanentMalus; 
		
	}
	
	
	public int getEra() {
		return era;
	}

	public MalusEffect getPermanentMalusEffect() {
		return permanentMalus;
	}


}
