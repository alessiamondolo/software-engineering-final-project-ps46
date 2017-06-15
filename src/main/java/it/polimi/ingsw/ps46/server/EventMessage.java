package it.polimi.ingsw.ps46.server;

public class EventMessage implements EventAcceptor {

	NewStateMessage message;
	
	EventMessage(NewStateMessage message) {
		this.message = message;
	}
	
	public NewStateMessage getMessage() {
		return message;
	}
	
	public void accept(EventVisitor v) {
		v.visit(this);
	}

}
