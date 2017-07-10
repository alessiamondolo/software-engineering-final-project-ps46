package it.polimi.ingsw.ps46.server.card;

import java.io.Serializable;

/**
 * This Class is used to create a single object of ExcommunicationTile, with its permanent Malus effect.
 * 
 * @author Andrea.Masi
 */


public class ExcommunicationTile implements Serializable {
	
	private static final long serialVersionUID = -4348291631021816102L;
	
	private int id;
	private int era;
	private MalusEffect permanentMalus;
	
	
	public ExcommunicationTile (int id, int era, MalusEffect permanentMalus){
		
		this.id = id;
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


	public int getId() {
		return id;
	}

}
