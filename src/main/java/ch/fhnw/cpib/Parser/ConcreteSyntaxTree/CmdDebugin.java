package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= DEBUGIN expr
public class CmdDebugin implements IConcSyn.ICmd {
	private final IToken debugin;
	private final IConcSyn.IExpr expr;

	public CmdDebugin(final IToken debugin, final IConcSyn.IExpr expr) {
		this.debugin = debugin;
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
