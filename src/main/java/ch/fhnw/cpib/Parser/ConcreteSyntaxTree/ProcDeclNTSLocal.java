package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// procDeclNTS ::= LOCAL cpsStoDecl
public class ProcDeclNTSLocal implements IConcSyn.IProcDeclNTS {
    public ProcDeclNTSLocal() {

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
