package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// paramList ::= LPAREN paramListNTS RPAREN
public class ParamList implements IConcSyn.IParamList {
	public ParamList() {

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
