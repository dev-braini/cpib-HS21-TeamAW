package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;

// procDecl ::= PROC IDENT paramList procDeclNTS DO cpsCmd ENDPROC
public class ProcDecl implements IConcSyn.IProcDecl {
    protected final IToken                proc;
    protected final IToken                ident;
    protected final IConcSyn.IParamList   paramList;
    protected final IConcSyn.IProcDeclNTS procDeclNTS;
    protected final IToken                do_;
    protected final IConcSyn.ICpsCmd      cpsCmd;
    protected final IToken                endproc;

    public ProcDecl(final IToken proc, final IToken ident, final IConcSyn.IParamList paramList,
                    final IConcSyn.IProcDeclNTS procDeclNTS, final IToken do_, final IConcSyn.ICpsCmd cpsCmd,
                    final IToken endproc) {
        this.proc        = proc;
        this.ident       = ident;
        this.paramList   = paramList;
        this.procDeclNTS = procDeclNTS;
        this.do_         = do_;
        this.cpsCmd      = cpsCmd;
        this.endproc     = endproc;
    }

    @Override
    public IAbsSyn.IDecl toAbsSyn() {
        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.ProcDecl((Ident)ident, paramList.toAbsSyn(), procDeclNTS.toAbsSyn(), cpsCmd.toAbsSyn());
    }
}
