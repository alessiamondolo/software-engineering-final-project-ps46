package it.polimi.ingsw.ps46.server;

public interface ViewEventVisitor {
	void visit (EventStringInput eventStringInput);
	
	void visit(EventMessage eventMessage);
}
