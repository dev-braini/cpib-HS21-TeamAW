package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// procDecl ::= PROC IDENT paramList procDeclNTS DO cpsCmd ENDPROC
public class ProcDecl implements IConcSyn.IProcDecl {
    public ProcDecl() {

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
