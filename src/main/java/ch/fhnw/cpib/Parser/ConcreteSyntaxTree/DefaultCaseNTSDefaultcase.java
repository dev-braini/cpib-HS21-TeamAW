package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// defaultCaseNTS ::= DEFAULTCASE COLON cpsCmd ENDCASE
public class DefaultCaseNTSDefaultcase implements IConcSyn.IDefaultCaseNTS {
    private final IToken defaultcase;
    private final IToken colon;
    private final IConcSyn.ICpsCmd cpsCmd;
    private final IToken endcase;

    public DefaultCaseNTSDefaultcase(final IToken defaultcase, final IToken colon, final IConcSyn.ICpsCmd cpsCmd,
                                     final IToken endcase) {
        this.defaultcase = defaultcase;
        this.colon = colon;
        this.cpsCmd = cpsCmd;
        this.endcase = endcase;
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
