package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// stoDecl ::= CHANGEMODE typedIdent
public class StoDeclChangemode implements IConcSyn.IStoDecl {
	private final IToken changemode;
	private final IConcSyn.ITypedIdent typedIdent;

    public StoDeclChangemode(final IToken changemode, final IConcSyn.ITypedIdent typedIdent) {
		this.changemode = changemode;
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
