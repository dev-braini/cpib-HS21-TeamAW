package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// typedIdent ::= IDENT COLON TYPE
public class TypedIdent implements IConcSyn.ITypedIdent {
	private final IToken ident;
	private final IToken colon;
	private final IToken type;

	public TypedIdent(final IToken ident, final IToken colon, final IToken type) {
		this.ident = ident;
		this.colon = colon;
		this.type = type;
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
