package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term2 ::= term3 term2NTS
public class Term2 implements IConcSyn.ITerm2 {
    protected final IConcSyn.ITerm3    term3;
    protected final IConcSyn.ITerm2NTS term2NTS;

    public Term2(final IConcSyn.ITerm3 term3, final IConcSyn.ITerm2NTS term2NTS) {
        this.term3    = term3;
        this.term2NTS = term2NTS;
    }

    @Override
    public IAbsSyn.IExpr toAbsSyn() {
        return term2NTS.toAbsSyn(term3.toAbsSyn());
    }
}
