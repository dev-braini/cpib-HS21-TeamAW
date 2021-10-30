package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

public interface IRepMULTOPRfactor extends IProduction {
    IAbsSyn.IExpr toAbsSyn(AbsSyn.IExpr exprLeft);
}
