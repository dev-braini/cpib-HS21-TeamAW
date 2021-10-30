package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term1 ::= term2 term1NTS
public class Term1 implements IConcSyn.ITerm1 {
	public Term1() {

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
