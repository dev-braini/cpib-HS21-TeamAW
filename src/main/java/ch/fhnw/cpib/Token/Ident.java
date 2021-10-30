package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.Terminals;

public class Ident extends Token {
    private final String value;

    public Ident(String value){
        super(Terminals.IDENT);
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.getTerminal() + ", " + this.value + ")";
    }
}
