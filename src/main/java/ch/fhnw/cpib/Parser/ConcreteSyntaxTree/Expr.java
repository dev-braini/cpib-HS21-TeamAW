package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// expr ::= term0 condExprNTS
public class Expr implements IConcSyn.IExpr {
    public Expr() {

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
