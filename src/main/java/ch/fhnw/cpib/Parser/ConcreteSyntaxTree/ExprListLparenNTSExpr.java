package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// exprListLparenNTS ::= expr exprListNTS
public class ExprListLparenNTSExpr implements IConcSyn.IExprListLparenNTS {
    protected final IConcSyn.IExpr        expr;
    protected final IConcSyn.IExprListNTS exprListNTS;

    public ExprListLparenNTSExpr(final IConcSyn.IExpr expr, final IConcSyn.IExprListNTS exprListNTS) {
        this.expr        = expr;
        this.exprListNTS = exprListNTS;
    }

    @Override
    public ArrayList<IAbsSyn.IExpr> toAbsSyn() {
        ArrayList<IAbsSyn.IExpr> tmp = new ArrayList<>();
        tmp.add(expr.toAbsSyn());

        return exprListNTS.toAbsSyn(tmp);
    }
}
