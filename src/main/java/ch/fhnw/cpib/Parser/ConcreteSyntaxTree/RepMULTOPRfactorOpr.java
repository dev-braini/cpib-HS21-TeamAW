package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

public class RepMULTOPRfactorOpr implements IRepMULTOPRfactor {
    private final MultOprs opr;
    private final IFactor factor;
    private final IRepMULTOPRfactor repMULTOPRfactor; // recursive structure

    private RepMULTOPRfactorOpr(final MultOprs opr, final IFactor factor, final IRepMULTOPRfactor repMULTOPRfactor) {
        this.opr              = opr;
        this.factor           = factor;
        this.repMULTOPRfactor = repMULTOPRfactor;
    }

    @Override
    public IAbsSyn.IExpr toAbsSyn(AbsSyn.IExpr exprLeft) {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
