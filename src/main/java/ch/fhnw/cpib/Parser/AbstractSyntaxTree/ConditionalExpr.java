package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Type;

public class ConditionalExpr implements IAbsSyn.IExpr {
    public ConditionalExpr(IAbsSyn.IExpr expr, IAbsSyn.IExpr iExpr, IAbsSyn.IExpr iExpr1) {
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
