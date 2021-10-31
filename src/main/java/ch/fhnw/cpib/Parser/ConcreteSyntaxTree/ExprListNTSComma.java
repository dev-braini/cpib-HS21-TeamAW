package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// exprListNTS ::= COMMA expr exprListNTS
public class ExprListNTSComma implements IConcSyn.IExprListNTS {
    private final IToken comma;
    private final IConcSyn.IExpr expr;
    private final IConcSyn.IExprListNTS exprListNTS;

    public ExprListNTSComma(final IToken comma, final IConcSyn.IExpr expr, final IConcSyn.IExprListNTS exprListNTS) {
        this.comma = comma;
        this.expr = expr;
        this.exprListNTS = exprListNTS;
    }

    @Override
    public ArrayList<IAbsSyn.IExpr> toAbsSyn(ArrayList<IAbsSyn.IExpr> tmp) {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
