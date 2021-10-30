package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// cpsStoDeclNTS ::= SEMICOLON stoDecl cpsStoDeclNTS
public class CpsStoDeclNTSSemicolon implements IConcSyn.ICpsStoDeclNTS {
    public CpsStoDeclNTSSemicolon() {

    }

    @Override
    public ArrayList<IAbsSyn.IStoDecl> toAbsSyn(ArrayList<IAbsSyn.IStoDecl> tmp) {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
