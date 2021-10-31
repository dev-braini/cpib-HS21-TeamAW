package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= WHILE expr DO cpsCmd ENDWHILE
public class CmdWhile implements IConcSyn.ICmd {
	final IToken while_;
	final IConcSyn.IExpr expr;
	final IToken do_;
	final IConcSyn.ICpsCmd cpsCmd;
	final IToken endwhile;

	public CmdWhile(final IToken while_, final IConcSyn.IExpr expr, final IToken do_,
					final IConcSyn.ICpsCmd cpsCmd, final IToken endwhile) {
		this.while_ = while_;
		this.expr = expr;
		this.do_ = do_;
		this.cpsCmd = cpsCmd;
		this.endwhile = endwhile;
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
