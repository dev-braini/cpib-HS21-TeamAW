package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;
import ch.fhnw.cpib.Token.Type;

// typedIdent ::= IDENT COLON TYPE
public class TypedIdent implements IConcSyn.ITypedIdent {
    public final IToken ident;
    public final IToken type;

	public TypedIdent(final IToken ident, final IToken colon, final IToken type) {
		this.ident = ident;
		this.type  = type;
	}

	@Override
	public IAbsSyn.ITypedIdent toAbsSyn() {
        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.TypedIdent((Ident)ident, (Type)type);
	}
}
