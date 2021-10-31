package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= CALL IDENT exprList
public class CmdCall implements IConcSyn.ICmd {
	private final IToken call;
	private final IToken ident;
	private final IConcSyn.IExprList exprList;

	public CmdCall(final IToken call, final IToken ident, final IConcSyn.IExprList exprList) {
		this.call = call;
		this.ident = ident;
		this.exprList = exprList;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}
