package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Enums.ChangeModes;

// changeModeNTS::= ε
public class ChangeModeNTSEpsilon implements IConcSyn.IChangeModeNTS {
    public ChangeModeNTSEpsilon() {

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
