package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// cpsCmdNTS ::= SEMICOLON cmd cpsCmdNTS
public class CpsCmdNTSSemicolon implements IConcSyn.ICpsCmdNTS {
    private final IConcSyn.ICmd       cmd;
    private final IConcSyn.ICpsCmdNTS cpsCmdNTS;

    public CpsCmdNTSSemicolon(final IToken semicolon, final IConcSyn.ICmd cmd, final IConcSyn.ICpsCmdNTS cpsCmdNTS) {
        this.cmd       = cmd;
        this.cpsCmdNTS = cpsCmdNTS;
    }

    @Override
    public ArrayList<IAbsSyn.ICmd> toAbsSyn(ArrayList<IAbsSyn.ICmd> tmp) {
        tmp.add(cmd.toAbsSyn());
        return cpsCmdNTS.toAbsSyn(tmp);
    }
}
