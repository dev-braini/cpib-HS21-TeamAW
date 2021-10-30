package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// ifelseNTS ::= ELSE cpsCmd
public class IfelseNTSElse implements IConcSyn.IIfelseNTS {
    public IfelseNTSElse() {

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
