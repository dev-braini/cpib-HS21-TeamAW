package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term0NTS ::= ε
public class Term0NTSEpsilon implements IConcSyn.ITerm0NTS {
	public Term0NTSEpsilon() { }

	@Override
	public IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr) {
        return expr;
	}
}
