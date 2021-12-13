package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.DebugOutCmd;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= DEBUGOUT expr
public class CmdDebugout implements IConcSyn.ICmd {
    public final IConcSyn.IExpr expr;

	public CmdDebugout(final IToken debugout, final IConcSyn.IExpr expr) {
		this.expr = expr;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        return new DebugOutCmd(expr.toAbsSyn());
	}
}
