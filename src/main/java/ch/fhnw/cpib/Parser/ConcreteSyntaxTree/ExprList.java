package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// exprList ::= LPAREN exprListLparenNTS RPAREN
public class ExprList implements IConcSyn.IExprList {
    private final IToken lparen;
    private final IConcSyn.IExprListLparenNTS exprListLparenNTS;
    private final IToken rparen;

    public ExprList(final IToken lparen, final IConcSyn.IExprListLparenNTS exprListLparenNTS, final IToken rparen) {
        this.lparen = lparen;
        this.exprListLparenNTS = exprListLparenNTS;
        this.rparen = rparen;
    }

    @Override
    public ArrayList<IAbsSyn.IExpr> toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
