package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// paramListNTS ::= param paramNTS
public class ParamListNTSParam implements IConcSyn.IParamListNTS {
	public ParamListNTSParam() {

	}

	@Override
	public ArrayList<IAbsSyn.IParam> toAbsSyn() {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}
