package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

//term2NTS ::= ADDOPR term3 term2NTS
public class Term2NTSAddopr implements IConcSyn.ITerm2NTS {
	public Term2NTSAddopr() {

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
