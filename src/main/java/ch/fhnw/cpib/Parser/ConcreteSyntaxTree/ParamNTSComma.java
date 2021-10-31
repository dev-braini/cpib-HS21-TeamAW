package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// paramNTS ::= COMMA param paramNTS
public class ParamNTSComma implements IConcSyn.IParamNTS {
	private final IToken comma;
	private final IConcSyn.IParam param;
	private final IConcSyn.IParamNTS paramNTS;

	public ParamNTSComma(final IToken comma, final IConcSyn.IParam param, final IConcSyn.IParamNTS paramNTS) {
		this.comma = comma;
		this.param = param;
		this.paramNTS = paramNTS;
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
