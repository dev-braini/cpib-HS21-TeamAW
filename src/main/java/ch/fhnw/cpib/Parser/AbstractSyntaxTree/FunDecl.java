package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Token.Ident;

import java.util.ArrayList;

public class FunDecl  implements IAbsSyn.IDecl {
    public FunDecl(Ident ident, ArrayList<IAbsSyn.IParam> toAbsSyn, IAbsSyn.IStoDecl toAbsSyn1, ArrayList<IAbsSyn.IStoDecl> toAbsSyn2, IAbsSyn.ICpsCmd toAbsSyn3) {
    }

    @Override
    public String getIdentString() {
        return null;
    }
}
