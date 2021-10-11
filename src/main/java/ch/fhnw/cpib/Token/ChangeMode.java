package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.ChangeModes;
import ch.fhnw.cpib.Enums.Terminals;

public class ChangeMode extends Token {
    private final ChangeModes value;

    public ChangeMode(ChangeModes value) {
        super(Terminals.CHANGEMODE);
        this.value = value;
    }

    public ChangeModes getValue() {
        return this.value;
    }

    public String toString() {
        return "(" + super.getTerminal() + ", " + this.value + ")";
    }

}
