package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// factor ::= IDENT factorNTS
public class FactorIdent implements IConcSyn.IFactor {
    private final IToken ident;
    private final IConcSyn.IFactorNTS factorNTS;

    public FactorIdent(final IToken ident, final IConcSyn.IFactorNTS factorNTS) {
        this.ident = ident;
        this.factorNTS = factorNTS;
    }

    @Override
    public IAbsSyn.IFactor toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
