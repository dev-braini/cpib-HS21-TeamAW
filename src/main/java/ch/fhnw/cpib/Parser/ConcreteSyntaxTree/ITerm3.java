package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

public interface ITerm3 extends IProduction {
    IAbsSyn.IExpr toAbsSyn();
}
