package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// expr ::= term0 condExprNTS
public class Expr implements IConcSyn.IExpr {
    private final IConcSyn.ITerm0 term0;
    private final IConcSyn.ICondExprNTS condExprNTS;

    public Expr(final IConcSyn.ITerm0 term0, final IConcSyn.ICondExprNTS condExprNTS) {
        this.term0 = term0;
        this.condExprNTS = condExprNTS;
    }

    @Override
    public IAbsSyn.IExpr toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
