package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term3 ::= factor term3NTS
public class Term3 implements IConcSyn.ITerm3 {
	public Term3() {

	}

	@Override
	public IAbsSyn.IExpr toAbsSyn() {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}