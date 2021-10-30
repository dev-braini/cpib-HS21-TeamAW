package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// exprList ::= LPAREN exprListLparenNTS RPAREN
public class ExprList implements IConcSyn.IExprList {
    public ExprList() {

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
