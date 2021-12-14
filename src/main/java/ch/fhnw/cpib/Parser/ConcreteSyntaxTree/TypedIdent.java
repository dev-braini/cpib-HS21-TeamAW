package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;
import ch.fhnw.cpib.Token.Type;

// typedIdent ::= IDENT COLON TYPE
public class TypedIdent implements IConcSyn.ITypedIdent {
    protected final IToken ident;
    protected final IToken colon;
    protected final IToken type;

	public TypedIdent(final IToken ident, final IToken colon, final IToken type) {
        this.ident = ident;
        this.colon = colon;
		this.type  = type;
	}

	@Override
	public ch.fhnw.cpib.Parser.AbstractSyntaxTree.TypedIdent toAbsSyn() {
        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.TypedIdent((Ident)ident, (Type)type);
	}
}
