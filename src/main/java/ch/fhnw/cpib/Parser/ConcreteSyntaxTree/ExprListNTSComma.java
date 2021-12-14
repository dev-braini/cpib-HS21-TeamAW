package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// exprListNTS ::= COMMA expr exprListNTS
public class ExprListNTSComma implements IConcSyn.IExprListNTS {
    protected final IToken                comma;
    protected final IConcSyn.IExpr        expr;
    protected final IConcSyn.IExprListNTS exprListNTS;

    public ExprListNTSComma(final IToken comma, final IConcSyn.IExpr expr, final IConcSyn.IExprListNTS exprListNTS) {
        this.comma       = comma;
        this.expr        = expr;
        this.exprListNTS = exprListNTS;
    }

    @Override
    public ArrayList<IAbsSyn.IExpr> toAbsSyn(ArrayList<IAbsSyn.IExpr> tmp) {
        tmp.add(expr.toAbsSyn());
        return exprListNTS.toAbsSyn(tmp);
    }
}
