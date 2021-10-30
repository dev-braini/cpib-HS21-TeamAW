package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

//term0 ::= term1 term0NTS
public class Term0 implements IConcSyn.ITerm0 {
	public Term0() {

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
