package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// funDeclNTS ::= ε
public class FunDeclNTSEpsilon implements IConcSyn.IFunDeclNTS {
    public FunDeclNTSEpsilon() {

    }

    @Override
    public ArrayList<IAbsSyn.IStoDecl> toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
