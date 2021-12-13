package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// paramListNTS ::= ε
public class ParamListNTSEpsilon implements IConcSyn.IParamListNTS {
	public ParamListNTSEpsilon() { }

	@Override
	public ArrayList<IAbsSyn.IParam> toAbsSyn() {
        return new ArrayList<>();
	}
}
