package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// caseNTS::= CASE LITERAL COLON cpsCmd ENDCASE caseNTS
public class CaseNTSCase implements IConcSyn.ICaseNTS {
    public CaseNTSCase() {

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
