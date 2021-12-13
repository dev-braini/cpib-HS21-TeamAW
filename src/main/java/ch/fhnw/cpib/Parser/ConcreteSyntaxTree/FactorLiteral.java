package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.LiteralFactor;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Literal;

// factor ::= LITERAL
public class FactorLiteral implements IConcSyn.IFactor {
    public final IToken literal;

    public FactorLiteral(final IToken literal) {
        this.literal = literal;
    }

    @Override
    public IAbsSyn.IFactor toAbsSyn() {
        return new LiteralFactor((Literal)literal);
    }
}
