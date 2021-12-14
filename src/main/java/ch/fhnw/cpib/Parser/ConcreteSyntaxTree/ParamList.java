package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// paramList ::= LPAREN paramListNTS RPAREN
public class ParamList implements IConcSyn.IParamList {
    protected final IConcSyn.IParamListNTS paramListNTS;

	public ParamList(final IToken lparen, final IConcSyn.IParamListNTS paramListNTS, final IToken rparen) {
		this.paramListNTS = paramListNTS;
	}

	@Override
	public ArrayList<IAbsSyn.IParam> toAbsSyn() {
        return paramListNTS.toAbsSyn();
	}
}
