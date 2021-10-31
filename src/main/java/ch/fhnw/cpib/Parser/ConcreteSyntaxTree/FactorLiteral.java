package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// factor ::= LITERAL
public class FactorLiteral implements IConcSyn.IFactor {
    private final IToken literal;

    public FactorLiteral(final IToken literal) {
        this.literal = literal;
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
