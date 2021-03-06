package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.DebugInCmd;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= DEBUGIN expr
public class CmdDebugin implements IConcSyn.ICmd {
    protected final IToken            debugin;
    protected final IConcSyn.IExpr expr;

	public CmdDebugin(final IToken debugin, final IConcSyn.IExpr expr) {
        this.debugin = debugin;
		this.expr    = expr;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        return new DebugInCmd(expr.toAbsSyn());
	}
}
