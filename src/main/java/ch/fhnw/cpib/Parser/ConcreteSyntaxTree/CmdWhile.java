package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.WhileCmd;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= WHILE expr DO cpsCmd ENDWHILE
public class CmdWhile implements IConcSyn.ICmd {
    protected final IToken           while_;
    protected final IConcSyn.IExpr   expr;
    protected final IToken           do_;
    protected final IConcSyn.ICpsCmd cpsCmd;
    protected final IToken           endwhile;

	public CmdWhile(final IToken while_, final IConcSyn.IExpr expr, final IToken do_,
					final IConcSyn.ICpsCmd cpsCmd, final IToken endwhile) {
		this.while_   = while_;
		this.expr     = expr;
		this.do_      = do_;
		this.cpsCmd   = cpsCmd;
		this.endwhile = endwhile;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        return new WhileCmd(expr.toAbsSyn(), cpsCmd.toAbsSyn());
	}
}
