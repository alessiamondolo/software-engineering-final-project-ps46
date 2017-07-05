package it.polimi.ingsw.ps46.server;

import it.polimi.ingsw.ps46.server.card.BuildingCard;

public class EventEffectChoice implements EventAcceptor, ViewEventAcceptor {

	NewStateMessage message;
	BuildingCard card;
	int choice = 0;
	
	public EventEffectChoice(NewStateMessage message, BuildingCard card) {
		this.message = message;
		this.card = card;
	}
	
	public NewStateMessage getMessage() {
		return message;
	}
	
	public BuildingCard getCard() {
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
