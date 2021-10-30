package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// stoDecl ::= CHANGEMODE typedIdent
public class StoDeclChangemode implements IConcSyn.IStoDecl {
	public StoDeclChangemode() {

	}

	@Override
	public IAbsSyn.IStoDecl toAbsSyn() {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}
