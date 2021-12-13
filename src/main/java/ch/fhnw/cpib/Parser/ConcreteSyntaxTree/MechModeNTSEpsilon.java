package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Enums.MechModes;
import ch.fhnw.cpib.Token.MechMode;

// mechModeNTS::= ε
public class MechModeNTSEpsilon implements IConcSyn.IMechModeNTS {
    public MechModeNTSEpsilon() {

    }

    @Override
    public MechMode toAbsSyn() {
        return null;
    }
}
