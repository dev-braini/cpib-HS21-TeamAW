package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.MultExpr;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Operator;

// term3NTS ::= MULTOPR factor term3NTS
public class Term3NTSMultopr implements IConcSyn.ITerm3NTS {
	private final IToken multopr;
	private final IConcSyn.IFactor factor;
	private final IConcSyn.ITerm3NTS term3NTS;

	public Term3NTSMultopr(final IToken multopr, final IConcSyn.IFactor factor, final IConcSyn.ITerm3NTS term3NTS) {
		this.multopr = multopr;
		this.factor = factor;
		this.term3NTS = term3NTS;
	}

	@Override
	public IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr) {
        IAbsSyn.IExpr expr2 = new MultExpr((Operator)multopr, expr, factor.toAbsSyn());
        return term3NTS.toAbsSyn(expr2);
	}
}
