package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// cpsStoDeclNTS ::= SEMICOLON stoDecl cpsStoDeclNTS
public class CpsStoDeclNTSSemicolon implements IConcSyn.ICpsStoDeclNTS {
    private final IConcSyn.IStoDecl       stoDecl;
    private final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS;

    public CpsStoDeclNTSSemicolon(final IToken semicolon, final IConcSyn.IStoDecl stoDecl,
                                  final IConcSyn.ICpsStoDeclNTS cpsStoDeclNTS) {
        this.stoDecl       = stoDecl;
        this.cpsStoDeclNTS = cpsStoDeclNTS;
    }

    @Override
    public ArrayList<IAbsSyn.IStoDecl> toAbsSyn(ArrayList<IAbsSyn.IStoDecl> tmp) {
        tmp.add(stoDecl.toAbsSyn());
        return cpsStoDeclNTS.toAbsSyn(tmp);
    }
}
