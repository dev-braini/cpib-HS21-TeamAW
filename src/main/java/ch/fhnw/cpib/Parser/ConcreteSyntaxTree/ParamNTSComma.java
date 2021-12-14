package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// paramNTS ::= COMMA param paramNTS
public class ParamNTSComma implements IConcSyn.IParamNTS {
    protected final IConcSyn.IParam    param;
    protected final IConcSyn.IParamNTS paramNTS;

	public ParamNTSComma(final IToken comma, final IConcSyn.IParam param, final IConcSyn.IParamNTS paramNTS) {
		this.param    = param;
		this.paramNTS = paramNTS;
	}

	@Override
	public ArrayList<IAbsSyn.IParam> toAbsSyn(ArrayList<IAbsSyn.IParam> tmp) {
        tmp.add(param.toAbsSyn());
        return paramNTS.toAbsSyn(tmp);
	}
}
