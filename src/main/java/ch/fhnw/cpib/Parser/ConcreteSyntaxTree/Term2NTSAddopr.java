package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AddExpr;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Operator;

// term2NTS ::= ADDOPR term3 term2NTS
public class Term2NTSAddopr implements IConcSyn.ITerm2NTS {
    protected final IToken             addopr;
    protected final IConcSyn.ITerm3    term3;
    protected final IConcSyn.ITerm2NTS term2NTS;

	public Term2NTSAddopr(final IToken addopr, final IConcSyn.ITerm3 term3, final IConcSyn.ITerm2NTS term2NTS) {
		this.addopr   = addopr;
		this.term3    = term3;
		this.term2NTS = term2NTS;
	}

	@Override
	public IAbsSyn.IExpr toAbsSyn(IAbsSyn.IExpr expr) {
        IAbsSyn.IExpr expr2 = new AddExpr((Operator)addopr, expr, term3.toAbsSyn());
        return term2NTS.toAbsSyn(expr2);
	}
}
