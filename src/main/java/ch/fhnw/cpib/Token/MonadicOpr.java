package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.Terminals;

public class MonadicOpr extends Token {
    private Token operator;

    public MonadicOpr(Terminals terminal) {
        super(terminal);
    }

    public Token getOperator() {
        return this;
    }
}
