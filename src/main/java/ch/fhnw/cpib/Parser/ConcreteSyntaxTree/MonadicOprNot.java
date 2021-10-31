package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.MonadicOpr;

// monadicOpr ::= NOT
public class MonadicOprNot implements IConcSyn.IMonadicOpr {
    private final IToken not;

    public MonadicOprNot(final IToken not) {
        this.not = not;
    }

    @Override
    public MonadicOpr toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
