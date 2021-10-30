package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// typedIdent ::= IDENT COLON TYPE
public class TypedIdent implements IConcSyn.ITypedIdent {
	public TypedIdent() {

	}

	@Override
	public IAbsSyn.ITypedIdent toAbsSyn() {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}
