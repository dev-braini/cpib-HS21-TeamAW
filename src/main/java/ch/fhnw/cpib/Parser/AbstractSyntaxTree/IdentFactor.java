package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Token.Ident;

public abstract class IdentFactor extends AbsSynTreeNode implements IAbsSyn.IFactor {
	protected Ident ident;
}
