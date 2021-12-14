package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// ifelseNTS ::= ELSE cpsCmd
public class IfelseNTSElse implements IConcSyn.IIfelseNTS {
    protected final IToken           else_;
    protected final IConcSyn.ICpsCmd cpsCmd;

    public IfelseNTSElse(final IToken else_, final IConcSyn.ICpsCmd cpsCmd) {
        this.else_  = else_;
        this.cpsCmd = cpsCmd;
    }

    @Override
    public IAbsSyn.ICpsCmd toAbsSyn() {
        return cpsCmd.toAbsSyn();
    }
}
