package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term1 ::= term2 term1NTS
public class Term1 implements IConcSyn.ITerm1 {
    public final IConcSyn.ITerm2    term2;
    public final IConcSyn.ITerm1NTS term1NTS;

	public Term1(final IConcSyn.ITerm2 term2, final IConcSyn.ITerm1NTS term1NTS) {
		this.term2    = term2;
		this.term1NTS = term1NTS;
	}

	@Override
	public IAbsSyn.IExpr toAbsSyn() {
        return term1NTS.toAbsSyn(term2.toAbsSyn());
	}
}
