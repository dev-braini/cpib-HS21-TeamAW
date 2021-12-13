package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import java.util.ArrayList;

// paramListNTS ::= param paramNTS
public class ParamListNTSParam implements IConcSyn.IParamListNTS {
    public final IConcSyn.IParam param;
    public final IConcSyn.IParamNTS paramNTS;

	public ParamListNTSParam(final IConcSyn.IParam param, final IConcSyn.IParamNTS paramNTS) {
		this.param = param;
		this.paramNTS = paramNTS;
	}

	@Override
	public ArrayList<IAbsSyn.IParam> toAbsSyn() {
        ArrayList<IAbsSyn.IParam> tmp = new ArrayList<>();
        tmp.add(param.toAbsSyn());

        return paramNTS.toAbsSyn(tmp);
	}
}
