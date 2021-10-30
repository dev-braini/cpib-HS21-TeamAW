package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// term2 ::= term3 term2NTS
public class Term2 implements IConcSyn.ITerm2 {
    public Term2() {

    }

    @Override
    public IAbsSyn.IExpr toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
