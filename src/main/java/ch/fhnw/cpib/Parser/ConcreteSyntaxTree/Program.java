package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;

// program ::= PROGRAM IDENT globalNTS DO cpsCmd ENDPROGRAM
public class Program implements IConcSyn.IProgram {
    protected final IToken              ident;
    protected final IConcSyn.IGlobalNTS globalNTS;
    protected final IConcSyn.ICpsCmd    cpsCmd;

    public Program(final IToken program, final IToken ident, final IConcSyn.IGlobalNTS globalNTS,
                   final IToken do_, final IConcSyn.ICpsCmd cpsCmd, final IToken endprogram) {
        this.ident     = ident;
        this.globalNTS = globalNTS;
        this.cpsCmd    = cpsCmd;
    }

    @Override
    public IAbsSyn.IProgram toAbsSyn() {
        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.Program((Ident)ident, globalNTS.toAbsSyn(), cpsCmd.toAbsSyn());
    }
}
