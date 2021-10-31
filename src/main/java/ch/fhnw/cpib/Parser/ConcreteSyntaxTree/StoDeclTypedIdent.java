package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// stoDecl ::= typedIdent
public class StoDeclTypedIdent implements IConcSyn.IStoDecl {
	private final IConcSyn.ITypedIdent typedIdent;

	public StoDeclTypedIdent(final IConcSyn.ITypedIdent typedIdent) {
		this.typedIdent = typedIdent;
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
