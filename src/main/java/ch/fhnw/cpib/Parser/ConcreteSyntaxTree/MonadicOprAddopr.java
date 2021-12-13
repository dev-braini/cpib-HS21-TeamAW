package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.MonadicOpr;

// monadicOpr ::= ADDOPR
public class MonadicOprAddopr implements IConcSyn.IMonadicOpr {
    private final IToken addopr;

    public MonadicOprAddopr(final IToken addopr) {
        this.addopr = addopr;
    }

    @Override
    public MonadicOpr toAbsSyn() {
        return new MonadicOpr(addopr);
    }
}
