package it.polimi.ingsw.ps46.server;

public interface EventAcceptor {
	void accept(EventVisitor v);
}
