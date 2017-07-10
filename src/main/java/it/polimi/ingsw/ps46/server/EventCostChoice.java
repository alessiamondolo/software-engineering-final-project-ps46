package it.polimi.ingsw.ps46.server;

import it.polimi.ingsw.ps46.server.card.VentureCard;

public class EventCostChoice implements EventAcceptor, ViewEventAcceptor {

	NewStateMessage message;
	VentureCard card;
	int choice = 0;
	
	public EventCostChoice(NewStateMessage message, VentureCard card) {
		this.message = message;
		this.card = card;
	}
	
	public NewStateMessage getMessage() {
		return message;
	}
	
	public VentureCard getCard() {
		return card;
	}
	
	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	public int getChoice() {
		return choice;
	}
	
	public void accept(EventVisitor v) {
		v.visit(this);
	}

	@Override
	public void accept(ViewEventVisitor v) {
		v.visit(this);
	}
	
}
