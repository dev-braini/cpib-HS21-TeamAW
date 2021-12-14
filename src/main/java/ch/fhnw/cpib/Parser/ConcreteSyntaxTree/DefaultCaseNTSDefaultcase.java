package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.DefaultCase;
import ch.fhnw.cpib.Token.IToken;

// defaultCaseNTS ::= DEFAULTCASE COLON cpsCmd ENDCASE
public class DefaultCaseNTSDefaultcase implements IConcSyn.IDefaultCaseNTS {
    protected final IToken           defaultcase;
    protected final IToken           colon;
    protected final IConcSyn.ICpsCmd cpsCmd;
    protected final IToken           endcase;

    public DefaultCaseNTSDefaultcase(final IToken defaultcase, final IToken colon, final IConcSyn.ICpsCmd cpsCmd,
                                     final IToken endcase) {
        this.defaultcase = defaultcase;
        this.colon       = colon;
        this.cpsCmd      = cpsCmd;
        this.endcase     = endcase;
    }

    @Override
    public DefaultCase toAbsSyn() {
        return new DefaultCase(cpsCmd.toAbsSyn());
    }
}
