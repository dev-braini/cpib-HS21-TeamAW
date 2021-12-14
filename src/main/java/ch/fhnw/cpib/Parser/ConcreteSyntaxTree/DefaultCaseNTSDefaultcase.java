package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.DefaultCase;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// defaultCaseNTS ::= DEFAULTCASE COLON cpsCmd ENDCASE
public class DefaultCaseNTSDefaultcase implements IConcSyn.IDefaultCaseNTS {
    protected final IConcSyn.ICpsCmd cpsCmd;

    public DefaultCaseNTSDefaultcase(final IToken defaultcase, final IToken colon, final IConcSyn.ICpsCmd cpsCmd,
                                     final IToken endcase) {
        this.cpsCmd = cpsCmd;
    }

    @Override
    public IAbsSyn.IDefaultCase toAbsSyn() {
        return new DefaultCase(cpsCmd.toAbsSyn());
    }
}
