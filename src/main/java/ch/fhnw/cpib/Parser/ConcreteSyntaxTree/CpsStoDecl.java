package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.StoDecl;

import java.util.ArrayList;

// cpsStoDecl ::= stoDecl cpsStoDeclNTS
public class CpsStoDecl implements IConcSyn.ICpsStoDecl {
    protected final IConcSyn.IStoDecl       stoDecl;
    protected final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS;

    public CpsStoDecl(final IConcSyn.IStoDecl stoDecl, final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS) {
        this.stoDecl       = stoDecl;
        this.cpsStoDeclNTS = cpsStoDeclNTS;
    }

    @Override
    public ArrayList<StoDecl> toAbsSyn() {
        ArrayList<StoDecl> tmp = new ArrayList<>();
        tmp.add(stoDecl.toAbsSyn());
        return cpsStoDeclNTS.toAbsSyn(tmp);
    }
}
