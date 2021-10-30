package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsStoDecl ::= stoDecl cpsStoDeclNTS
public class CpsStoDecl implements IConcSyn.ICpsStoDecl {
    public CpsStoDecl() {

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
