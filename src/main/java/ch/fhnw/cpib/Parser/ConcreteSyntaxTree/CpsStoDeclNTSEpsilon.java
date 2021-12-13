package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsStoDeclNTS ::= ε
public class CpsStoDeclNTSEpsilon implements IConcSyn.ICpsStoDeclNTS {
    public CpsStoDeclNTSEpsilon() { }

    @Override
    public ArrayList<IAbsSyn.IStoDecl> toAbsSyn(ArrayList<IAbsSyn.IStoDecl> tmp) {
        return tmp;
    }
}
