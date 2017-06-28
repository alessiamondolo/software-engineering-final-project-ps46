package it.polimi.ingsw.ps46.server;

public class EventIntInput implements ViewEventAcceptor {
	int value;
	InputType type;
	
	public EventIntInput(int value, InputType type) {
		this.value = value;
		this.type = type;
	}

	public InputType getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	@Override
	public void accept(ViewEventVisitor v) {
		v.visit(this);		
	}
}
