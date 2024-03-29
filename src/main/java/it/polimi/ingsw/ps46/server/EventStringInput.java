package it.polimi.ingsw.ps46.server;

public class EventStringInput implements ViewEventAcceptor {
	String string;
	InputType type;
	
	public EventStringInput(String string, InputType type) {
		this.string = string;
		this.type = type;
	}

	public InputType getType() {
		return type;
	}

	public String getString() {
		return string;
	}

	@Override
	public void accept(ViewEventVisitor v) {
		v.visit(this);		
	}
}
