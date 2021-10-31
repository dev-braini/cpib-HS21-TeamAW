package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.Ident;

// factorNTS ::= exprList
public class FactorNTSExprList implements IConcSyn.IFactorNTS {
    private final IConcSyn.IExprList exprList;

    public FactorNTSExprList(final IConcSyn.IExprList exprList) {
        this.exprList = exprList;
    }

    @Override
    public IAbsSyn.IFactor toAbsSyn(Ident ident) {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
