package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Token.ChangeMode;

// changeModeNTS::= ε
public class ChangeModeNTSEpsilon implements IConcSyn.IChangeModeNTS {
    public ChangeModeNTSEpsilon() { }

    @Override
    public ChangeMode toAbsSyn() {
        return null;
    }
}
