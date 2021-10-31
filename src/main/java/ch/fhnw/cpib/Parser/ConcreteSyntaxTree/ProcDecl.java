package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// procDecl ::= PROC IDENT paramList procDeclNTS DO cpsCmd ENDPROC
public class ProcDecl implements IConcSyn.IProcDecl {
    private final IToken proc;
    private final IToken ident;
    private final IConcSyn.IParamList paramList;
    private final IConcSyn.IProcDeclNTS procDeclNTS;
    private final IToken do_;
    private final IConcSyn.ICpsCmd cpsCmd;
    private final IToken endproc;

    public ProcDecl(final IToken proc, final IToken ident, final IConcSyn.IParamList paramList,
                    final IConcSyn.IProcDeclNTS procDeclNTS, final IToken do_, final IConcSyn.ICpsCmd cpsCmd,
                    final IToken endproc) {
        this.proc = proc;
        this.ident = ident;
        this.paramList = paramList;
        this.procDeclNTS = procDeclNTS;
        this.do_ = do_;
        this.cpsCmd = cpsCmd;
        this.endproc = endproc;
    }

    @Override
    public IAbsSyn.IDecl toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
