package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

//term3NTS ::= MULTOPR factor term3NTS
public class Term3NTSMultopr implements IConcSyn.ITerm3NTS {
	public Term3NTSMultopr() {

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
