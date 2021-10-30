package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// factor ::= LPAREN expr RPAREN
public class FactorLParen implements IConcSyn.IFactor {
    public FactorLParen() {

    }

    @Override
    public IAbsSyn.IFactor toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
