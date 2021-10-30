package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsDecl ::= decl cpsDeclNTS
public class CpsDecl implements IConcSyn.ICpsDecl {
    public CpsDecl() {

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
