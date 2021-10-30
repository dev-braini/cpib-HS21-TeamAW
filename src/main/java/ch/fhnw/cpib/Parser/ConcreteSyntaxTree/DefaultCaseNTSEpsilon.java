package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// defaultCaseNTS ::= ε
public class DefaultCaseNTSEpsilon implements IConcSyn.IDefaultCaseNTS {
    public DefaultCaseNTSEpsilon() {

    }

    @Override
    public IAbsSyn.IDefaultCase toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
