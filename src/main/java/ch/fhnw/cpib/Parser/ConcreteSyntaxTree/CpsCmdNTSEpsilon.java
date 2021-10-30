package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsCmdNTS ::= ε
public class CpsCmdNTSEpsilon implements IConcSyn.ICpsCmdNTS {
    public CpsCmdNTSEpsilon() {

    }

    @Override
    public ArrayList<IAbsSyn.ICmd> toAbsSyn(ArrayList<IAbsSyn.ICmd> tmp) {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
