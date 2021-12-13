package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Token.ChangeMode;

public class StoDecl implements IAbsSyn.IStoDecl {
    public StoDecl(ChangeMode changemode, IAbsSyn.ITypedIdent iTypedIdent) {
    }

    public StoDecl(IAbsSyn.ITypedIdent typedIdent) {
        // this.typedIdent = typedIdent;
    }
}
