package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// paramNTS ::= ε
public class ParamNTSEpsilon implements IConcSyn.IParamNTS {
	public ParamNTSEpsilon() { }

	@Override
	public ArrayList<IAbsSyn.IParam> toAbsSyn(ArrayList<IAbsSyn.IParam> tmp) {
		return tmp;
	}
}
