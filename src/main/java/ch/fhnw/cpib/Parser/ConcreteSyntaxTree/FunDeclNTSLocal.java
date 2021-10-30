package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// funDeclNTS ::= LOCAL cpsStoDecl
public class FunDeclNTSLocal implements IConcSyn.IFunDeclNTS {
    public FunDeclNTSLocal() {

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
