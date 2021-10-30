package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// exprListNTS ::= COMMA expr exprListNTS
public class ExprListNTSComma implements IConcSyn.IExprListNTS {
    public ExprListNTSComma() {

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
