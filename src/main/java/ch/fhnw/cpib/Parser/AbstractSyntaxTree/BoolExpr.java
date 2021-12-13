package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Operator;
import ch.fhnw.cpib.Token.Type;

public class BoolExpr implements IAbsSyn.IExpr {
    public BoolExpr(Operator boolopr, IAbsSyn.IExpr expr, IAbsSyn.IExpr iExpr) {
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
