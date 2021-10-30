package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// paramNTS ::= COMMA param paramNTS
public class ParamNTSComma implements IConcSyn.IParamNTS {
	public ParamNTSComma() {

	}

	@Override
	public ArrayList<IAbsSyn.IParam> toAbsSyn(ArrayList<IAbsSyn.IParam> tmp) {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}
