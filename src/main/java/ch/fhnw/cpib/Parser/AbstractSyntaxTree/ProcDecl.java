package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Token.Ident;

import java.util.ArrayList;

public class ProcDecl implements IAbsSyn.IDecl {
    public ProcDecl(Ident ident, ArrayList<IAbsSyn.IParam> toAbsSyn, ArrayList<IAbsSyn.IStoDecl> toAbsSyn1, IAbsSyn.ICpsCmd toAbsSyn2) {
    }

    @Override
    public String getIdentString() {
        return null;
    }
}
