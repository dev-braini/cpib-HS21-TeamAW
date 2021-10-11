package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.Terminals;

public class Token implements IToken {

    private final Terminals terminal;

    public Token(Terminals terminal){
        this.terminal = terminal;
    }

    public Terminals getTerminal(){
        return terminal;
    }

    public String toString() {
        return terminal.toString();
    }
}