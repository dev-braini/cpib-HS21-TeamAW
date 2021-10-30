package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// globalNTS ::= GLOBAL cpsDecl
public class GlobalNTSGlobal implements IConcSyn.IGlobalNTS {
    public GlobalNTSGlobal() {

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
