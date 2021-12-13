package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Token.Ident;
import ch.fhnw.cpib.Token.Type;

public class TypedIdent implements IAbsSyn.ITypedIdent {
    public TypedIdent(Ident ident, Type type) {
    }

    public String getValue() {
        return "";
    }

    public void setInit() {
    }
}
