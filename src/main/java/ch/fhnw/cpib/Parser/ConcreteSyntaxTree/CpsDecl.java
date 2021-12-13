package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsDecl ::= decl cpsDeclNTS
public class CpsDecl implements IConcSyn.ICpsDecl {
    public final IConcSyn.IDecl       decl;
    public final IConcSyn.ICpsDeclNTS cpsDeclNTS;

    public CpsDecl(final IConcSyn.IDecl decl, final IConcSyn.ICpsDeclNTS cpsDeclNTS) {
        this.decl       = decl;
        this.cpsDeclNTS = cpsDeclNTS;
    }

    @Override
    public ArrayList<IAbsSyn.IDecl> toAbsSyn() {
        ArrayList<IAbsSyn.IDecl> tmp = new ArrayList<>();
        tmp.add(decl.toAbsSyn());

        return cpsDeclNTS.toAbsSyn(tmp);
    }
}
