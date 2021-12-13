package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

import java.util.ArrayList;

// cpsStoDecl ::= stoDecl cpsStoDeclNTS
public class CpsStoDecl implements IConcSyn.ICpsStoDecl {
    public final IConcSyn.IStoDecl       stoDecl;
    public final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS;

    public CpsStoDecl(final IConcSyn.IStoDecl stoDecl, final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS) {
        this.stoDecl       = stoDecl;
        this.cpsStoDeclNTS = cpsStoDeclNTS;
    }

    @Override
    public ArrayList<IAbsSyn.IStoDecl> toAbsSyn() {
        ArrayList<IAbsSyn.IStoDecl> tmp = new ArrayList<>();
        tmp.add(stoDecl.toAbsSyn());
        return cpsStoDeclNTS.toAbsSyn(tmp);
    }
}
