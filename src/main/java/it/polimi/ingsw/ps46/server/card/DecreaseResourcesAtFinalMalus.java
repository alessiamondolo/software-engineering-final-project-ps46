package it.polimi.ingsw.ps46.server.card;

public class DecreaseResourcesAtFinalMalus extends DecreaseResourcesMalus{
	
	String from;
	
	
	public DecreaseResourcesAtFinalMalus(){
		from = null;
	}
	
	public DecreaseResourcesAtFinalMalus(String from){
		 this.from = from;
		
	}
	
	
	public String getType() {
		return from;
	}
	
}
