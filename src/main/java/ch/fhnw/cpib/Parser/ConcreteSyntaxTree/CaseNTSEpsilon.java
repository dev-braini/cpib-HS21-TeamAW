package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// caseNTS::= ε
public class CaseNTSEpsilon implements IConcSyn.ICaseNTS {
    public CaseNTSEpsilon() {

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
