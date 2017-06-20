package it.polimi.ingsw.ps46.server;

import org.json.simple.JSONObject;


public class EventMV implements EventAcceptor {

	GameState gameState;
	JSONObject obj = new JSONObject();
	
	

	public EventMV(GameState gameState, JSONObject obj) {
		this.gameState = gameState;
		this.obj = obj;
	}



	public void accept(EventVisitor v) {
		v.visit(this);
	}



	public GameState getState() {
		return gameState;
	}



	public JSONObject getJSONObject() {
		return obj;
	}

}
