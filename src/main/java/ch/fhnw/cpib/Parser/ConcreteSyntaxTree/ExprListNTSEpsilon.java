package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// exprListNTS ::= ε
public class ExprListNTSEpsilon implements IConcSyn.IExprListNTS {
    public ExprListNTSEpsilon() { }

    @Override
    public ArrayList<IAbsSyn.IExpr> toAbsSyn(ArrayList<IAbsSyn.IExpr> tmp) {
        return tmp;
    }
}
