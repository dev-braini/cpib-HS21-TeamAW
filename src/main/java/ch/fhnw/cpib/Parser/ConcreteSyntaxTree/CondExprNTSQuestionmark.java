package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.ConditionalExpr;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// condExprNTS ::= QUESTIONMARK expr COLON expr
public class CondExprNTSQuestionmark implements IConcSyn.ICondExprNTS {
	private final IConcSyn.IExpr expr1;
	private final IConcSyn.IExpr expr2;

	public CondExprNTSQuestionmark(final IToken questionmark, final IConcSyn.IExpr expr1, final IToken colon,
								   final IConcSyn.IExpr expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	@Override
	public IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr) {
		return new ConditionalExpr(expr, expr1.toAbsSyn(), expr2.toAbsSyn());
	}
}
