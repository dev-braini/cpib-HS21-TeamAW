package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// exprListLparenNTS ::= expr exprListNTS
public class ExprListLparenNTSExpr implements IConcSyn.IExprListLparenNTS {
    private final IConcSyn.IExpr expr;
    private final IConcSyn.IExprListNTS exprListNTS;

    public ExprListLparenNTSExpr(final IConcSyn.IExpr expr, final IConcSyn.IExprListNTS exprListNTS) {
        this.expr = expr;
        this.exprListNTS = exprListNTS;
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
