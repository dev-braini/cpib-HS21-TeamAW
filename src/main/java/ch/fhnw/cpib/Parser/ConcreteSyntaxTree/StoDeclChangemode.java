package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.StoDecl;
import ch.fhnw.cpib.Token.ChangeMode;
import ch.fhnw.cpib.Token.IToken;

// stoDecl ::= CHANGEMODE typedIdent
public class StoDeclChangemode implements IConcSyn.IStoDecl {
    protected final IToken               changemode;
    protected final IConcSyn.ITypedIdent typedIdent;

    public StoDeclChangemode(final IToken changemode, final IConcSyn.ITypedIdent typedIdent) {
		this.changemode = changemode;
		this.typedIdent = typedIdent;
    }

    @Override
	public IAbsSyn.IStoDecl toAbsSyn() {
        return new StoDecl((ChangeMode)changemode, typedIdent.toAbsSyn());
	}
}
