package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsStoDecl ::= stoDecl cpsStoDeclNTS
public class CpsStoDecl implements IConcSyn.ICpsStoDecl {
    private final IConcSyn.IStoDecl stoDecl;
    private final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS;

    public CpsStoDecl(final IConcSyn.IStoDecl stoDecl, final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS) {
        this.stoDecl = stoDecl;
        this.cpsStoDeclNTS = cpsStoDeclNTS;
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
