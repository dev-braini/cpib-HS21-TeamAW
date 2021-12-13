package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;

// factor ::= IDENT factorNTS
public class FactorIdent implements IConcSyn.IFactor {
    private final IToken              ident;
    private final IConcSyn.IFactorNTS factorNTS;

    public FactorIdent(final IToken ident, final IConcSyn.IFactorNTS factorNTS) {
        this.ident     = ident;
        this.factorNTS = factorNTS;
    }

    @Override
    public IAbsSyn.IFactor toAbsSyn() {
        return factorNTS.toAbsSyn((Ident)ident);
    }
}
