package it.polimi.ingsw.ps46.server;

public interface EventVisitor {

	void visit(EventMessage eventMessage);

	void visit(EventEffectChoice eventEffectChoice);

	void visit(EventCostChoice eventCostChoice);

	void visit(EventExtraMove eventExtraMove);
	
}
