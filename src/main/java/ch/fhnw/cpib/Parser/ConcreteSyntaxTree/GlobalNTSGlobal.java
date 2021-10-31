package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// globalNTS ::= GLOBAL cpsDecl
public class GlobalNTSGlobal implements IConcSyn.IGlobalNTS {
    private final IToken global;
    private final IConcSyn.ICpsDecl cpsDecl;

    public GlobalNTSGlobal(final IToken global, final IConcSyn.ICpsDecl cpsDecl) {
        this.global = global;
        this.cpsDecl = cpsDecl;
    }

    @Override
    public ArrayList<IAbsSyn.IDecl> toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
