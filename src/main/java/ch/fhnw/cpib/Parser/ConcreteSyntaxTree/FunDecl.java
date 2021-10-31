package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// funDecl ::= FUN IDENT paramList RETURNS stoDecl funDeclNTS DO cpsCmd ENDFUN
public class FunDecl implements IConcSyn.IFunDecl {
    private final IToken fun;
    private final IToken ident;
    private final IConcSyn.IParamList paramList;
    private final IToken returns;
    private final IConcSyn.IStoDecl stoDecl;
    private final IConcSyn.IFunDeclNTS funDeclNTS;
    private final IToken do_;
    private final IConcSyn.ICpsCmd cpsCmd;
    private final IToken endfun;

    public FunDecl(final IToken fun, final IToken ident, final IConcSyn.IParamList paramList, final IToken returns,
                   final IConcSyn.IStoDecl stoDecl, final IConcSyn.IFunDeclNTS funDeclNTS, final IToken do_,
                   final IConcSyn.ICpsCmd cpsCmd, final IToken endfun) {

        this.fun = fun;
        this.ident = ident;
        this.paramList = paramList;
        this.returns = returns;
        this.stoDecl = stoDecl;
        this.funDeclNTS = funDeclNTS;
        this.do_ = do_;
        this.cpsCmd = cpsCmd;
        this.endfun = endfun;
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
