package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.InitFactor;
import ch.fhnw.cpib.Token.Ident;

// factorNTS ::= ε
public class FactorNTSEpsilon implements IConcSyn.IFactorNTS {
    public FactorNTSEpsilon() { }

    @Override
    public IAbsSyn.IFactor toAbsSyn(Ident ident) {
        return new InitFactor(ident, false);
    }
}
