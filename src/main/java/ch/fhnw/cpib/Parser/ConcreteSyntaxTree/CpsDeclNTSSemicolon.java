package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsDeclNTS ::= SEMICOLON decl cpsDeclNTS
public class CpsDeclNTSSemicolon implements IConcSyn.ICpsDeclNTS {
    public CpsDeclNTSSemicolon() {

    }

    @Override
    public ArrayList<IAbsSyn.IDecl> toAbsSyn(ArrayList<IAbsSyn.IDecl> tmp) {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
