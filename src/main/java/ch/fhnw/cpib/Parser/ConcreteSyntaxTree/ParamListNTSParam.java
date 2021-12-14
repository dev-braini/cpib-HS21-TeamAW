package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.Param;

import java.util.ArrayList;

// paramListNTS ::= param paramNTS
public class ParamListNTSParam implements IConcSyn.IParamListNTS {
    protected final IConcSyn.IParam param;
    protected final IConcSyn.IParamNTS paramNTS;

	public ParamListNTSParam(final IConcSyn.IParam param, final IConcSyn.IParamNTS paramNTS) {
		this.param = param;
		this.paramNTS = paramNTS;
	}

	@Override
	public ArrayList<Param> toAbsSyn() {
        ArrayList<Param> tmp = new ArrayList<>();
        tmp.add(param.toAbsSyn());

        return paramNTS.toAbsSyn(tmp);
	}
}
