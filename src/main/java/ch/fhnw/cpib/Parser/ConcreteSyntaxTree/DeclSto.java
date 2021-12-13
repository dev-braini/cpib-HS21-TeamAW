package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// decl ::= stoDecl
public class DeclSto implements IConcSyn.IDecl {
    private final IConcSyn.IStoDecl stoDecl;

    public DeclSto(final IConcSyn.IStoDecl stoDecl) {
        this.stoDecl = stoDecl;
    }

    @Override
    public IAbsSyn.IDecl toAbsSyn() {
        return (IAbsSyn.IDecl) stoDecl.toAbsSyn();
    }
}
