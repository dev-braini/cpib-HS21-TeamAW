package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.CpsCmd;

import java.util.ArrayList;

// ifelseNTS ::= ε
public class IfelseNTSEpsilon implements IConcSyn.IIfelseNTS {
    public IfelseNTSEpsilon() { }

    @Override
    public IAbsSyn.ICpsCmd toAbsSyn() {
        return new CpsCmd(new ArrayList<>());
    }
}
