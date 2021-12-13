package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Operator;
import ch.fhnw.cpib.Token.Type;

public class MultExpr implements IAbsSyn.IExpr {
    public MultExpr(Operator multopr, IAbsSyn.IExpr expr, IAbsSyn.IFactor iFactor) {
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public LRVal getLRValue() {
        return null;
    }
}
