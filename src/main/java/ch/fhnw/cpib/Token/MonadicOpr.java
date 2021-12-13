package ch.fhnw.cpib.Token;

import ch.fhnw.cpib.Enums.Terminals;

public class MonadicOpr extends Token {
    private IToken operator;

    public MonadicOpr(IToken operator) {
        super(operator.getTerminal());
    }

    public IToken getOperator() {
        return operator;
    }
}
