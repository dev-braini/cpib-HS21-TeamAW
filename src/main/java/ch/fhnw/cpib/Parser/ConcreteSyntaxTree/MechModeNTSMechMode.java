package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Enums.MechModes;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.MechMode;

// mechModeNTS ::= MECHMODE
public class MechModeNTSMechMode implements IConcSyn.IMechModeNTS {
    public final IToken mechmode;

    public MechModeNTSMechMode(final IToken mechmode) {
        this.mechmode = mechmode;
    }

    @Override
    public MechMode toAbsSyn() {
        return (MechMode) mechmode;
    }
}
