package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.MonadicFactor;

// factor ::= monadicOpr factor
public class FactorMonadicopr implements IConcSyn.IFactor {
    private final IConcSyn.IMonadicOpr monadicOpr;
    private final IConcSyn.IFactor     factor;

    public FactorMonadicopr(final IConcSyn.IMonadicOpr monadicOpr, final IConcSyn.IFactor factor) {
        this.monadicOpr = monadicOpr;
        this.factor     = factor;
    }

    @Override
    public IAbsSyn.IFactor toAbsSyn() {
        return new MonadicFactor(monadicOpr.toAbsSyn(), factor.toAbsSyn());
    }
}
