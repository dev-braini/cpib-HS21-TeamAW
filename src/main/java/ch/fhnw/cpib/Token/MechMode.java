package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.MechModes;
import ch.fhnw.cpib.Enums.Terminals;

public class MechMode extends Token {
    private final MechModes value;

    public MechMode(MechModes value){
        super(Terminals.MECHMODE);
        this.value = value;
    }

    public MechModes getValue(){return this.value;}

    public String toString() {
        return "(" + super.getTerminal() + ", " + this.value + ")";
    }
}
