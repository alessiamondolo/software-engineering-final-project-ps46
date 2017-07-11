package it.polimi.ingsw.ps46.server;

public interface ViewEventVisitor {
	void visit(EventStringInput eventStringInput);
	
	void visit(EventMessage eventMessage);
	
	void visit(EventIntInput eventIntInput);

	void visit(EventEffectChoice eventEffectChoice);

	void visit(EventCostChoice eventCostChoice);

	void visit(EventExtraMove eventExtraMove);
}
