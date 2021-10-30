package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// globalNTS ::= ε
public class GlobalNTSEpsilon implements IConcSyn.IGlobalNTS {
    public GlobalNTSEpsilon() {

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
