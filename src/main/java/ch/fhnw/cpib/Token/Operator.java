package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.Operators;
import ch.fhnw.cpib.Enums.Terminals;

public class Operator extends Token{
    private final Operators value;

    public Operator(Terminals terminal, Operators value){
        super(terminal);
        this.value = value;
    }

    public Operators getValue(){
        return this.value;
    }

    public String toString(){
        return "(" + super.getTerminal() + ", " + this.value + ")";
    }

}
