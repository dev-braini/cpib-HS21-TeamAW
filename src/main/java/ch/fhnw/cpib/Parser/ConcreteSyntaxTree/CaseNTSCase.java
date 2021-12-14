package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.Case;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Literal;

import java.util.ArrayList;

// caseNTS::= CASE LITERAL COLON cpsCmd ENDCASE caseNTS
public class CaseNTSCase implements IConcSyn.ICaseNTS {
    protected final IToken            case_;
    protected final IToken            literal;
    protected final IToken            colon;
    protected final IConcSyn.ICpsCmd  cpsCmd;
    protected final IToken            endcase;
    protected final IConcSyn.ICaseNTS caseNTS;

    public CaseNTSCase(final IToken case_, final IToken literal, final IToken colon, final IConcSyn.ICpsCmd cpsCmd,
                       final IToken endcase, final IConcSyn.ICaseNTS caseNTS) {
        this.case_   = case_;
        this.literal = literal;
        this.colon   = colon;
        this.cpsCmd  = cpsCmd;
        this.endcase = endcase;
        this.caseNTS = caseNTS;
    }

    @Override
    public ArrayList<Case> toAbsSyn(ArrayList<Case> tmp) {
        tmp.add(new Case((Literal)literal, cpsCmd.toAbsSyn()));
        return caseNTS.toAbsSyn(tmp);
    }
}
