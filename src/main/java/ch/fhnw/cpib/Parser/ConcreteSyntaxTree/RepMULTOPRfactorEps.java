package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

public class RepMULTOPRfactorEps implements IRepMULTOPRfactor {
    private RepMULTOPRfactorEps() {

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
