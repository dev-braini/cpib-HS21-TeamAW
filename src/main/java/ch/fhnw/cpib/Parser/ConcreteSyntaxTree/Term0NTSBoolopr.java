package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// term0NTS ::= BOOLOPR term1 term0NTS
public class Term0NTSBoolopr implements IConcSyn.ITerm0NTS {
	private final IToken boolopr;
	private final IConcSyn.ITerm1 term1;
	private final IConcSyn.ITerm0NTS term0NTS;

	public Term0NTSBoolopr(final IToken boolopr, final IConcSyn.ITerm1 term1, final IConcSyn.ITerm0NTS term0NTS) {
		this.boolopr = boolopr;
		this.term1 = term1;
		this.term0NTS = term0NTS;
	}

	@Override
	public IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr) {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}
