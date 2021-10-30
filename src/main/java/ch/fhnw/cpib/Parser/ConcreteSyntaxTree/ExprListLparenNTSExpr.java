package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// exprListLparenNTS ::= expr exprListNTS
public class ExprListLparenNTSExpr implements IConcSyn.IExprListLparenNTS {
    public ExprListLparenNTSExpr() {

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
