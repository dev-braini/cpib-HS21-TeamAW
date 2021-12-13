package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsDeclNTS ::= ε
public class CpsDeclNTSEpsilon implements IConcSyn.ICpsDeclNTS {
    public CpsDeclNTSEpsilon() { }

    @Override
    public ArrayList<IAbsSyn.IDecl> toAbsSyn(ArrayList<IAbsSyn.IDecl> tmp) {
        return tmp;
    }
}
