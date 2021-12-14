package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.Case;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// caseNTS::= ε
public class CaseNTSEpsilon implements IConcSyn.ICaseNTS {
    public CaseNTSEpsilon() { }

    @Override
    public ArrayList<Case> toAbsSyn(ArrayList<Case> tmp) {
        return tmp;
    }
}
