package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// cpsDeclNTS ::= SEMICOLON decl cpsDeclNTS
public class CpsDeclNTSSemicolon implements IConcSyn.ICpsDeclNTS {
    private final IToken semicolon;
    private final IConcSyn.IDecl decl;
    private final IConcSyn.ICpsDeclNTS cpsDeclNTS;

    public CpsDeclNTSSemicolon(final IToken semicolon, final IConcSyn.IDecl decl,
                               final IConcSyn.ICpsDeclNTS cpsDeclNTS) {
        this.semicolon = semicolon;
        this.decl = decl;
        this.cpsDeclNTS = cpsDeclNTS;
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
