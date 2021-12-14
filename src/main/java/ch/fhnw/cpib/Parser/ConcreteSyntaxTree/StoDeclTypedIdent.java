package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.StoDecl;

// stoDecl ::= typedIdent
public class StoDeclTypedIdent implements IConcSyn.IStoDecl {
    protected final IConcSyn.ITypedIdent typedIdent;

	public StoDeclTypedIdent(final IConcSyn.ITypedIdent typedIdent) {
		this.typedIdent = typedIdent;
	}

	@Override
	public IAbsSyn.IStoDecl toAbsSyn() {
        return new StoDecl(typedIdent.toAbsSyn());
	}
}
