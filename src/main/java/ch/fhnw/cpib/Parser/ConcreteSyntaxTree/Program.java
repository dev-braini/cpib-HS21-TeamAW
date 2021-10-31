package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// program ::= PROGRAM IDENT globalNTS DO cpsCmd ENDPROGRAM
public class Program implements IConcSyn.IProgram {
    private final IToken program;
    private final IToken ident;
    private final IConcSyn.IGlobalNTS globalNTS;
    private final IToken do_;
    private final IConcSyn.ICpsCmd cpsCmd;
    private final IToken endprogram;

    public Program(final IToken program, final IToken ident, final IConcSyn.IGlobalNTS globalNTS,
                   final IToken do_, final IConcSyn.ICpsCmd cpsCmd, final IToken endprogram) {
        this.program = program;
        this.ident = ident;
        this.globalNTS = globalNTS;
        this.do_ = do_;
        this.cpsCmd = cpsCmd;
        this.endprogram = endprogram;
    }

    @Override
    public IAbsSyn.IProgram toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
