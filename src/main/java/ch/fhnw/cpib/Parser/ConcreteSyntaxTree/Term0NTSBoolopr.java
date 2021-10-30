package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term0NTS ::= BOOLOPR term1 term0NTS
public class Term0NTSBoolopr implements IConcSyn.ITerm0NTS {
	public Term0NTSBoolopr() {

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
