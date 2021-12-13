package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.DebugInCmd;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= DEBUGIN expr
public class CmdDebugin implements IConcSyn.ICmd {
    public final IConcSyn.IExpr expr;

	public CmdDebugin(final IToken debugin, final IConcSyn.IExpr expr) {
		this.expr = expr;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        return new DebugInCmd(expr.toAbsSyn());
	}
}
