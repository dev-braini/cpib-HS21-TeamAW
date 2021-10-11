package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.Terminals;
import ch.fhnw.cpib.Enums.Types;

public class Type extends Token{
    private final Types value;

    public Type(Types value){
        super(Terminals.TYPE);
        this.value = value;
    }

    public Types getValue(){return this.value;}

    public String toString() {
        return "(" + super.getTerminal() + ", " + this.value + ")";
    }
}
