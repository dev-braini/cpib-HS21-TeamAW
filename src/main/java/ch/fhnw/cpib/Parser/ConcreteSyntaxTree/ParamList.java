package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// paramList ::= LPAREN paramListNTS RPAREN
public class ParamList implements IConcSyn.IParamList {
    protected final IToken                 lparen;
    protected final IConcSyn.IParamListNTS paramListNTS;
    protected final IToken                 rparen;

	public ParamList(final IToken lparen, final IConcSyn.IParamListNTS paramListNTS, final IToken rparen) {
        this.lparen       = lparen;
		this.paramListNTS = paramListNTS;
        this.rparen       = rparen;
	}

	@Override
	public ArrayList<IAbsSyn.IParam> toAbsSyn() {
        return paramListNTS.toAbsSyn();
	}
}
