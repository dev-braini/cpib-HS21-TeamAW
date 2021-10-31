package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// factor ::= LPAREN expr RPAREN
public class FactorLParen implements IConcSyn.IFactor {
    private final IToken lparen;
    private final IConcSyn.IExpr expr;
    private final IToken rparen;

    public FactorLParen(final IToken lparen, final IConcSyn.IExpr expr, final IToken rparen) {
        this.lparen = lparen;
        this.expr = expr;
        this.rparen = rparen;
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
