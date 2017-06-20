package it.polimi.ingsw.ps46.server;

public interface EventVisitor {

	void visit(EventMessage eventMessage);
	
	void visit(EventMV eventMV);

}
