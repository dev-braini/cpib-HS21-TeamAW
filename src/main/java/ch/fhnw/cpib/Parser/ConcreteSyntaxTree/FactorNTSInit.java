package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.Ident;

// factorNTS ::= INIT
public class FactorNTSInit implements IConcSyn.IFactorNTS {
    public FactorNTSInit() {

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