package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.InitFactor;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;

// factorNTS ::= INIT
public class FactorNTSInit implements IConcSyn.IFactorNTS {
    public final IToken init;

    public FactorNTSInit(final IToken init) {
        this.init = init;
    }

    @Override
    public IAbsSyn.IFactor toAbsSyn(Ident ident) {
        return new InitFactor(ident, true);
    }
}
