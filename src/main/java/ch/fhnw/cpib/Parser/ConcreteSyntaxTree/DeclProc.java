package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// decl ::= procDecl
public class DeclProc implements IConcSyn.IDecl {
    private final IConcSyn.IProcDecl procDecl;

    public DeclProc(final IConcSyn.IProcDecl procDecl) {
        this.procDecl = procDecl;
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
