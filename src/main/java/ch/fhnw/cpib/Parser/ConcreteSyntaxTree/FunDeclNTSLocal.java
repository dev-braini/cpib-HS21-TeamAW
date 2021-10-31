package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// funDeclNTS ::= LOCAL cpsStoDecl
public class FunDeclNTSLocal implements IConcSyn.IFunDeclNTS {
    private final IToken local;
    private final IConcSyn.ICpsStoDecl cpsStoDecl;

    public FunDeclNTSLocal(final IToken local, final IConcSyn.ICpsStoDecl cpsStoDecl) {
        this.local = local;
        this.cpsStoDecl = cpsStoDecl;
    }

    @Override
    public ArrayList<IAbsSyn.IStoDecl> toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
