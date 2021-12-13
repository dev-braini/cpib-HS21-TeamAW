package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// exprListLparenNTS ::= ε
public class ExprListLparenNTSEpsilon implements IConcSyn.IExprListLparenNTS {
    public ExprListLparenNTSEpsilon() { }

    @Override
    public ArrayList<IAbsSyn.IExpr> toAbsSyn() {
        return new ArrayList<>();
    }
}
