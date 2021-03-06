package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term0 ::= term1 term0NTS
public class Term0 implements IConcSyn.ITerm0 {
    protected final IConcSyn.ITerm1    term1;
    protected final IConcSyn.ITerm0NTS term0NTS;

	public Term0(final IConcSyn.ITerm1 term1, final IConcSyn.ITerm0NTS term0NTS) {
		this.term1    = term1;
		this.term0NTS = term0NTS;
	}

	@Override
	public IAbsSyn.IExpr toAbsSyn() {
        return term0NTS.toAbsSyn(term1.toAbsSyn());
	}
}
