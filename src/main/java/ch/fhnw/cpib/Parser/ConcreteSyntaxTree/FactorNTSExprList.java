package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.Ident;

// factorNTS ::= exprList
public class FactorNTSExprList implements IConcSyn.IFactorNTS {
    public FactorNTSExprList() {

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
