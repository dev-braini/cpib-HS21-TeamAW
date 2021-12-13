package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;

// funDecl ::= FUN IDENT paramList RETURNS stoDecl funDeclNTS DO cpsCmd ENDFUN
public class FunDecl implements IConcSyn.IFunDecl {
    public final IToken               ident;
    public final IConcSyn.IParamList  paramList;
    public final IConcSyn.IStoDecl    stoDecl;
    public final IConcSyn.IFunDeclNTS funDeclNTS;
    public final IConcSyn.ICpsCmd     cpsCmd;

    public FunDecl(final IToken fun, final IToken ident, final IConcSyn.IParamList paramList, final IToken returns,
                   final IConcSyn.IStoDecl stoDecl, final IConcSyn.IFunDeclNTS funDeclNTS, final IToken do_,
                   final IConcSyn.ICpsCmd cpsCmd, final IToken endfun) {

        this.ident      = ident;
        this.paramList  = paramList;
        this.stoDecl    = stoDecl;
        this.funDeclNTS = funDeclNTS;
        this.cpsCmd     = cpsCmd;
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
