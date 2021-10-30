package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

public class Term3 implements ITerm3 {
    private final IFactor factor;
    private final IRepMULTOPRfactor repMULTOPRfactor;
    private Term3(final IFactor factor, final IRepMULTOPRfactor repMULTOPRfactor) {
        this.factor           = factor;
        this.repMULTOPRfactor = repMULTOPRfactor;
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
