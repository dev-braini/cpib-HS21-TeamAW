package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.RelExpr;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Operator;

// term1NTS::= RELOPR term2
public class Term1NTSRelopr implements IConcSyn.ITerm1NTS {
	private final IToken          relopr;
	private final IConcSyn.ITerm2 term2;

	public Term1NTSRelopr(final IToken relopr, final IConcSyn.ITerm2 term2) {
		this.relopr = relopr;
		this.term2  = term2;
	}

	@Override
	public IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr) {
        return new RelExpr((Operator)relopr, expr, term2.toAbsSyn());
	}
}
