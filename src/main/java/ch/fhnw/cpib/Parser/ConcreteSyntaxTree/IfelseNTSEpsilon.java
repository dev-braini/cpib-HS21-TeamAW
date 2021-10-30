package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// ifelseNTS ::= ε
public class IfelseNTSEpsilon implements IConcSyn.IIfelseNTS {
    public IfelseNTSEpsilon() {

    }

    @Override
    public IAbsSyn.ICpsCmd toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
