package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// defaultCaseNTS ::= DEFAULTCASE COLON cpsCmd ENDCASE
public class DefaultCaseNTSDefaultcase implements IConcSyn.IDefaultCaseNTS {
    public DefaultCaseNTSDefaultcase() {

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
