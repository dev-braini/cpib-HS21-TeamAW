package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.MonadicOpr;

// monadicOpr ::= NOT
public class MonadicOprNot implements IConcSyn.IMonadicOpr {
    public final IToken not_;

    public MonadicOprNot(final IToken not_) {
        this.not_ = not_;
    }

    @Override
    public MonadicOpr toAbsSyn() {
        return new MonadicOpr(not_);
    }
}
