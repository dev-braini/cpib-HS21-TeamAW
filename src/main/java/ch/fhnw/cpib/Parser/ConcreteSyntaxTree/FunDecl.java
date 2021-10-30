package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// funDecl ::= FUN IDENT paramList RETURNS stoDecl funDeclNTS DO cpsCmd ENDFUN
public class FunDecl implements IConcSyn.IFunDecl {
    public FunDecl() {

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
