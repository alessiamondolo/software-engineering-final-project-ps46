package it.polimi.ingsw.ps46.server;

public class EventMessage implements EventAcceptor, ViewEventAcceptor {

	NewStateMessage message;
	
	public EventMessage(NewStateMessage message) {
		this.message = message;
	}
	
	public NewStateMessage getMessage() {
		return message;
	}
	
	public void accept(EventVisitor v) {
		v.visit(this);
	}

	@Override
	public void accept(ViewEventVisitor v) {
		v.visit(this);
	}

}
