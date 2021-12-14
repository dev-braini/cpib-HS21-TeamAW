package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IfCmd;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= IF expr THEN cpsCmd ifelseNTS ENDIF
public class CmdIf implements IConcSyn.ICmd {
    protected final IConcSyn.IExpr      expr;
    protected final IConcSyn.ICpsCmd    cpsCmd;
    protected final IConcSyn.IIfelseNTS ifelseNTS;

	public CmdIf(final IToken if_, final IConcSyn.IExpr expr, final IToken then, final IConcSyn.ICpsCmd cpsCmd,
				 final IConcSyn.IIfelseNTS ifelseNTS, final IToken endif) {
		this.expr      = expr;
		this.cpsCmd    = cpsCmd;
		this.ifelseNTS = ifelseNTS;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        return new IfCmd(expr.toAbsSyn(), cpsCmd.toAbsSyn(), ifelseNTS.toAbsSyn());
	}
}
