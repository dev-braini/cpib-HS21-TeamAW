package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.ExprFactor;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// factor ::= LPAREN expr RPAREN
public class FactorLParen implements IConcSyn.IFactor {
    protected final IConcSyn.IExpr expr;

    public FactorLParen(final IToken lparen, final IConcSyn.IExpr expr, final IToken rparen) {
        this.expr = expr;
    }

    @Override
    public IAbsSyn.IFactor toAbsSyn() {
        return new ExprFactor(expr.toAbsSyn());
    }
}
