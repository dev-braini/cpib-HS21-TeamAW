package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// decl ::= funDecl
public class DeclFun implements IConcSyn.IDecl {
    protected final IConcSyn.IFunDecl funDecl;

    public DeclFun(final IConcSyn.IFunDecl funDecl) {
        this.funDecl = funDecl;
    }

    @Override
    public IAbsSyn.IDecl toAbsSyn() {
        return funDecl.toAbsSyn();
    }
}
