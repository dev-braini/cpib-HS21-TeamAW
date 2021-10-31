package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= IF expr THEN cpsCmd ifelseNTS ENDIF
public class CmdIf implements IConcSyn.ICmd {
	private final IToken if_;
	private final IConcSyn.IExpr expr;
	private final IToken then;
	private final IConcSyn.ICpsCmd cpsCmd;
	private final IConcSyn.IIfelseNTS ifelseNTS;
	private final IToken endif;

	public CmdIf(final IToken if_, final IConcSyn.IExpr expr, final IToken then, final IConcSyn.ICpsCmd cpsCmd,
				 final IConcSyn.IIfelseNTS ifelseNTS, final IToken endif) {
		this.if_ = if_;
		this.expr = expr;
		this.then = then;
		this.cpsCmd = cpsCmd;
		this.ifelseNTS = ifelseNTS;
		this.endif = endif;
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
