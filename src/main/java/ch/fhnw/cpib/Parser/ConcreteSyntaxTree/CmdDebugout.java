package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= DEBUGOUT expr
public class CmdDebugout implements IConcSyn.ICmd {
	private final IToken debugout;
	private final IConcSyn.IExpr expr;

	public CmdDebugout(final IToken debugout, final IConcSyn.IExpr expr) {
		this.debugout = debugout;
		this.expr = expr;
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
