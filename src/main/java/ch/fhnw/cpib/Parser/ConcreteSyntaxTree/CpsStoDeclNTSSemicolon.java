package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.StoDecl;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// cpsStoDeclNTS ::= SEMICOLON stoDecl cpsStoDeclNTS
public class CpsStoDeclNTSSemicolon implements IConcSyn.ICpsStoDeclNTS {
    protected final IToken                  semicolon;
    protected final IConcSyn.IStoDecl       stoDecl;
    protected final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS;

    public CpsStoDeclNTSSemicolon(final IToken semicolon, final IConcSyn.IStoDecl stoDecl,
                                  final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS) {
        this.semicolon     = semicolon;
        this.stoDecl       = stoDecl;
        this.cpsStoDeclNTS = cpsStoDeclNTS;
    }

    @Override
    public ArrayList<StoDecl> toAbsSyn(ArrayList<StoDecl> tmp) {
        tmp.add(stoDecl.toAbsSyn());
        return cpsStoDeclNTS.toAbsSyn(tmp);
    }
}
