package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// procDeclNTS ::= LOCAL cpsStoDecl
public class ProcDeclNTSLocal implements IConcSyn.IProcDeclNTS {
    private final IToken local;
    private final IConcSyn.ICpsStoDecl cpsStoDecl;

    public ProcDeclNTSLocal(final IToken local, final IConcSyn.ICpsStoDecl cpsStoDecl) {
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
