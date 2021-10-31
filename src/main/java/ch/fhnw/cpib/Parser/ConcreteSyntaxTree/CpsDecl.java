package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsDecl ::= decl cpsDeclNTS
public class CpsDecl implements IConcSyn.ICpsDecl {
    private final IConcSyn.IDecl decl;
    private final IConcSyn.ICpsDeclNTS cpsDeclNTS;

    public CpsDecl(final IConcSyn.IDecl decl, final IConcSyn.ICpsDeclNTS cpsDeclNTS) {
        this.decl = decl;
        this.cpsDeclNTS = cpsDeclNTS;
    }

    @Override
    public ArrayList<IAbsSyn.IDecl> toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
