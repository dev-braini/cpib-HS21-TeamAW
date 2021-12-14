package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.ProcCallCmd;
import ch.fhnw.cpib.Token.IToken;
import ch.fhnw.cpib.Token.Ident;

// cmd ::= CALL IDENT exprList
public class CmdCall implements IConcSyn.ICmd {
    protected final IToken             ident;
    protected final IConcSyn.IExprList exprList;

	public CmdCall(final IToken call, final IToken ident, final IConcSyn.IExprList exprList) {
		this.ident    = ident;
		this.exprList = exprList;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        return new ProcCallCmd((Ident)ident, exprList.toAbsSyn());
	}
}
