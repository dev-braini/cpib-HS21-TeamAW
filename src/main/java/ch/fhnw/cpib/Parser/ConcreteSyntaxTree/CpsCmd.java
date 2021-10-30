package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// cpsCmd ::= cmd cpsCmdNTS
public class CpsCmd implements IConcSyn.ICpsCmd {
    public CpsCmd() {

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
