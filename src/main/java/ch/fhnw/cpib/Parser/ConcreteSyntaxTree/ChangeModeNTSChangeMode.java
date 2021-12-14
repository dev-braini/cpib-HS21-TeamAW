package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Token.ChangeMode;
import ch.fhnw.cpib.Token.IToken;

// changeModeNTS ::= CHANGEMODE
public class ChangeModeNTSChangeMode implements IConcSyn.IChangeModeNTS {
    protected final IToken changemode;

    public ChangeModeNTSChangeMode(final IToken changemode) {
        this.changemode = changemode;
    }

    @Override
    public ChangeMode toAbsSyn() {
        return (ChangeMode)changemode;
    }
}
