package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Enums.ChangeModes;
import ch.fhnw.cpib.Token.IToken;

// changeModeNTS ::= CHANGEMODE
public class ChangeModeNTSChangeMode implements IConcSyn.IChangeModeNTS {
    private final IToken changemode;

    public ChangeModeNTSChangeMode(final IToken changemode) {
        this.changemode = changemode;
    }

    @Override
    public ChangeModes toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
