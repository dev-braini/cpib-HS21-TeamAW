package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// procDeclNTS ::= ε
public class ProcDeclNTSEpsilon implements IConcSyn.IProcDeclNTS {
    public ProcDeclNTSEpsilon() { }

    @Override
    public ArrayList<IAbsSyn.IStoDecl> toAbsSyn() {
        return new ArrayList<>();
    }
}
