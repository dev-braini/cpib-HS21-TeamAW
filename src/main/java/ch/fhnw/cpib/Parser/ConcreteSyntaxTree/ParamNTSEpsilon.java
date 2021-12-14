package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.Param;

import java.util.ArrayList;

// paramNTS ::= ε
public class ParamNTSEpsilon implements IConcSyn.IParamNTS {
    public ParamNTSEpsilon() { }

	@Override
	public ArrayList<Param> toAbsSyn(ArrayList<Param> tmp) {
		return tmp;
	}
}
