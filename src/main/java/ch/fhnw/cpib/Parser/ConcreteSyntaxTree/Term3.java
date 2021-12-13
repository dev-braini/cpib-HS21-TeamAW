package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term3 ::= factor term3NTS
public class Term3 implements IConcSyn.ITerm3 {
	private final IConcSyn.IFactor   factor;
	private final IConcSyn.ITerm3NTS term3NTS;

	public Term3(final IConcSyn.IFactor factor, final IConcSyn.ITerm3NTS term3NTS) {
		this.factor   = factor;
		this.term3NTS = term3NTS;
	}

	@Override
	public IAbsSyn.IExpr toAbsSyn() {
        return term3NTS.toAbsSyn(factor.toAbsSyn());
	}
}
