package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.StoDecl;

import java.util.ArrayList;

// funDeclNTS ::= ε
public class FunDeclNTSEpsilon implements IConcSyn.IFunDeclNTS {
    public FunDeclNTSEpsilon() { }

    @Override
    public ArrayList<StoDecl> toAbsSyn() {
        return new ArrayList<>();
    }
}
