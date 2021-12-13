package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.Case;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Literal;

import java.util.ArrayList;

// caseNTS::= CASE LITERAL COLON cpsCmd ENDCASE caseNTS
public class CaseNTSCase implements IConcSyn.ICaseNTS {
    private final IToken            literal;
    private final IConcSyn.ICpsCmd  cpsCmd;
    private final IConcSyn.ICaseNTS caseNTS;

    public CaseNTSCase(final IToken case_, final IToken literal, final IToken colon, final IConcSyn.ICpsCmd cpsCmd,
                       final IToken endcase, final IConcSyn.ICaseNTS caseNTS) {
        this.literal = literal;
        this.cpsCmd  = cpsCmd;
        this.caseNTS = caseNTS;
    }

    @Override
    public ArrayList<IAbsSyn.ICase> toAbsSyn(ArrayList<IAbsSyn.ICase> tmp) {
        tmp.add(new Case((Literal)literal, cpsCmd.toAbsSyn()));
        return caseNTS.toAbsSyn(tmp);
    }
}
