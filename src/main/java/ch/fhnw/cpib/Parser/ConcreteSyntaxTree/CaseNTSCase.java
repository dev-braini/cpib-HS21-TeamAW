package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// caseNTS::= CASE LITERAL COLON cpsCmd ENDCASE caseNTS
public class CaseNTSCase implements IConcSyn.ICaseNTS {
    private final IToken case_;
    private final IToken literal;
    private final IToken colon;
    private final IConcSyn.ICpsCmd cpsCmd;
    private final IToken endcase;
    private final IConcSyn.ICaseNTS caseNTS;

    public CaseNTSCase(final IToken case_, final IToken literal, final IToken colon, final IConcSyn.ICpsCmd cpsCmd,
                       final IToken endcase, final IConcSyn.ICaseNTS caseNTS) {
        this.case_ = case_;
        this.literal = literal;
        this.colon = colon;
        this.cpsCmd = cpsCmd;
        this.endcase = endcase;
        this.caseNTS = caseNTS;
    }

    @Override
    public ArrayList<IAbsSyn.ICase> toAbsSyn(ArrayList<IAbsSyn.ICase> tmp) {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
