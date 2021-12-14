package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

import java.util.ArrayList;

// cpsCmd ::= cmd cpsCmdNTS
public class CpsCmd implements IConcSyn.ICpsCmd {
    protected final IConcSyn.ICmd cmd;
    protected final IConcSyn.ICpsCmdNTS cpsCmdNTS;

    public CpsCmd(final IConcSyn.ICmd cmd, final IConcSyn.ICpsCmdNTS cpsCmdNTS) {
        this.cmd = cmd;
        this.cpsCmdNTS = cpsCmdNTS;
    }

    @Override
    public ch.fhnw.cpib.Parser.AbstractSyntaxTree.CpsCmd toAbsSyn() {
        ArrayList<IAbsSyn.ICmd> tmp = new ArrayList<>();
        tmp.add(cmd.toAbsSyn());

        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.CpsCmd(cpsCmdNTS.toAbsSyn(tmp));
    }
}
