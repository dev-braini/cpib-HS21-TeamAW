package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// cpsCmd ::= cmd cpsCmdNTS
public class CpsCmd implements IConcSyn.ICpsCmd {
    private final IConcSyn.ICmd cmd;
    private final IConcSyn.ICpsCmdNTS cpsCmdNTS;

    public CpsCmd(final IConcSyn.ICmd cmd, final IConcSyn.ICpsCmdNTS cpsCmdNTS) {
        this.cmd = cmd;
        this.cpsCmdNTS = cpsCmdNTS;
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
