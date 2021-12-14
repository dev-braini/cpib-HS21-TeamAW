package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;

// funDecl ::= FUN IDENT paramList RETURNS stoDecl funDeclNTS DO cpsCmd ENDFUN
public class FunDecl implements IConcSyn.IFunDecl {
    protected final IToken               fun;
    protected final IToken               ident;
    protected final IConcSyn.IParamList  paramList;
    protected final IToken               returns;
    protected final IConcSyn.IStoDecl    stoDecl;
    protected final IConcSyn.IFunDeclNTS funDeclNTS;
    protected final IToken               do_;
    protected final IConcSyn.ICpsCmd     cpsCmd;
    protected final IToken               endfun;

    public FunDecl(final IToken fun, final IToken ident, final IConcSyn.IParamList paramList, final IToken returns,
                   final IConcSyn.IStoDecl stoDecl, final IConcSyn.IFunDeclNTS funDeclNTS, final IToken do_,
                   final IConcSyn.ICpsCmd cpsCmd, final IToken endfun) {

        this.fun        = fun;
        this.ident      = ident;
        this.paramList  = paramList;
        this.returns    = returns;
        this.stoDecl    = stoDecl;
        this.funDeclNTS = funDeclNTS;
        this.do_        = do_;
        this.cpsCmd     = cpsCmd;
        this.endfun     = endfun;
    }

    @Override
    public IAbsSyn.IDecl toAbsSyn() {
        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.FunDecl(
                (Ident)ident,
                paramList.toAbsSyn(),
                stoDecl.toAbsSyn(),
                funDeclNTS.toAbsSyn(),
                cpsCmd.toAbsSyn()
        );
    }
}
