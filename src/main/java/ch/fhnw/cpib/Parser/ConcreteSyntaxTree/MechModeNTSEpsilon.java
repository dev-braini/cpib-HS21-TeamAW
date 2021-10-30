package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Enums.MechModes;

// mechModeNTS::= ε
public class MechModeNTSEpsilon implements IConcSyn.IMechModeNTS {
    public MechModeNTSEpsilon() {

    }

    @Override
    public MechModes toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
