package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;

// program ::= PROGRAM IDENT globalNTS DO cpsCmd ENDPROGRAM
public class Program implements IConcSyn.IProgram {
    protected final IToken              program;
    protected final IToken              ident;
    protected final IConcSyn.IGlobalNTS globalNTS;
    protected final IToken              do_;
    protected final IConcSyn.ICpsCmd    cpsCmd;
    protected final IToken              endprogram;

    public Program(final IToken program, final IToken ident, final IConcSyn.IGlobalNTS globalNTS,
                   final IToken do_, final IConcSyn.ICpsCmd cpsCmd, final IToken endprogram) {
        this.program    = program;
        this.ident      = ident;
        this.globalNTS  = globalNTS;
        this.do_        = do_;
        this.cpsCmd     = cpsCmd;
        this.endprogram = endprogram;
    }

    @Override
    public ch.fhnw.cpib.Parser.AbstractSyntaxTree.Program toAbsSyn() {
        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.Program((Ident)ident, globalNTS.toAbsSyn(), cpsCmd.toAbsSyn());
    }
}
