package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.StoDecl;

import java.util.ArrayList;

// cpsStoDeclNTS ::= ε
public class CpsStoDeclNTSEpsilon implements IConcSyn.ICpsStoDeclNTS {
    public CpsStoDeclNTSEpsilon() { }

    @Override
    public ArrayList<StoDecl> toAbsSyn(ArrayList<StoDecl> tmp) {
        return tmp;
    }
}
