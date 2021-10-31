package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Enums.MechModes;
import ch.fhnw.cpib.Token.IToken;

// mechModeNTS ::= MECHMODE
public class MechModeNTSMechMode implements IConcSyn.IMechModeNTS {
    private final IToken mechmode;

    public MechModeNTSMechMode(final IToken mechmode) {
        this.mechmode = mechmode;
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
