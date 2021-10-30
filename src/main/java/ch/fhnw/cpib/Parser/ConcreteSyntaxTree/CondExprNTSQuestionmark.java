package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// condExprNTS ::= QUESTIONMARK expr COLON expr
public class CondExprNTSQuestionmark implements IConcSyn.ICondExprNTS {
	public CondExprNTSQuestionmark() {

	}

	@Override
	public IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr) {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}
