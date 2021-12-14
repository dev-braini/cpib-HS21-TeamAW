package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AssignCmd;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= expr BECOMES expr
public class CmdExpr implements IConcSyn.ICmd {
    protected final IConcSyn.IExpr expr;
    protected final IConcSyn.IExpr expr2;

	public CmdExpr(final IConcSyn.IExpr expr, final IToken becomes, final IConcSyn.IExpr expr2) {
		this.expr  = expr;
		this.expr2 = expr2;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        return new AssignCmd(expr.toAbsSyn(), expr2.toAbsSyn());
	}
}
