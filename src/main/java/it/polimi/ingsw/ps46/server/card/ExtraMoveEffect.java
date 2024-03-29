package it.polimi.ingsw.ps46.server.card;

import java.io.Serializable;

import it.polimi.ingsw.ps46.server.Dice;
import it.polimi.ingsw.ps46.server.Game;
import it.polimi.ingsw.ps46.server.resources.ResourceSet;


/** 
 * ExtraMove implements Effect, ... TODO activateEffect
 * 
 * @author Andrea.Masi
 */
public class ExtraMoveEffect implements Effect, Serializable {
	
	private static final long serialVersionUID = -1452018894242891182L;
	
	private String extraMoveType;
	private String whichActionSpace;
	private Dice extraMoveValue;
	private ResourceSet additionalResources;
	private boolean resourcesDiscounted; // se questo booleano è a true le risorse di additionalResources vanno scontate dal costo della carta della casella in cui fai l'EXTRAMOVE 

	/**
	 * The constructor.
	 */
	public ExtraMoveEffect(String extraMoveType, String whichActionSpace, Dice extraMoveValue, boolean resourcesDiscounted, ResourceSet additionalResources) {
		super();
		this.extraMoveType = extraMoveType;
		this.whichActionSpace = whichActionSpace; // per capire se è possibile far fare al giocatore un'azione su una torre in particolare o in tutti i tipi di torre.
		this.extraMoveValue = extraMoveValue; 
		this.additionalResources = additionalResources;
		this.resourcesDiscounted = resourcesDiscounted ;
	}

	/**
	 * Description of the method activateEffect.
	 */
	public void activateEffect(Game game) {
		game.extraMove(this);		
	}
	
	
	public String getType(){
		return extraMoveType;
	
	}

	public Dice getValueOfTheExtraMove() {
		return extraMoveValue;
	}

	public ResourceSet getAdditionalResources() {
		return additionalResources;
	}

	public String getWhichActionSpace() {
		return whichActionSpace;
	}

	public boolean isResoucesDiscounted() {
		return resourcesDiscounted;
	}
	
	//TODO completare
	public String toString() {
		return "Extra move type: " + extraMoveType;
	}
}