package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.DefaultCase;

// defaultCaseNTS ::= ε
public class DefaultCaseNTSEpsilon implements IConcSyn.IDefaultCaseNTS {
    public DefaultCaseNTSEpsilon() { }

    @Override
    public DefaultCase toAbsSyn() {
        return null;
    }
}
