package it.polimi.ingsw.ps46.server;

import it.polimi.ingsw.ps46.server.card.ExtraMoveEffect;

public class EventExtraMove implements EventAcceptor, ViewEventAcceptor {

	NewStateMessage message;
	ExtraMoveEffect extraMoveEffect;
	
	public EventExtraMove(NewStateMessage message, ExtraMoveEffect extraMoveEffect) {
		this.message = message;
		this.extraMoveEffect = extraMoveEffect;
	}
	
	public NewStateMessage getMessage() {
		return message;
	}
	
	public ExtraMoveEffect getExtraMoveEffect() {
		return extraMoveEffect;
	}
	
	public void accept(EventVisitor v) {
		v.visit(this);
	}

	@Override
	public void accept(ViewEventVisitor v) {
		v.visit(this);
	}
	
}
