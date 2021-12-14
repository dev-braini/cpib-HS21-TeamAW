package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.StoDecl;

import java.util.ArrayList;

// procDeclNTS ::= ε
public class ProcDeclNTSEpsilon implements IConcSyn.IProcDeclNTS {
    public ProcDeclNTSEpsilon() { }

    @Override
    public ArrayList<StoDecl> toAbsSyn() {
        return new ArrayList<>();
    }
}
