package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// term2NTS ::= ADDOPR term3 term2NTS
public class Term2NTSAddopr implements IConcSyn.ITerm2NTS {
	private final IToken addopr;
	private final IConcSyn.ITerm3 term3;
	private final IConcSyn.ITerm2NTS term2NTS;

	public Term2NTSAddopr(final IToken addopr, final IConcSyn.ITerm3 term3, final IConcSyn.ITerm2NTS term2NTS) {
		this.addopr = addopr;
		this.term3 = term3;
		this.term2NTS = term2NTS;
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
