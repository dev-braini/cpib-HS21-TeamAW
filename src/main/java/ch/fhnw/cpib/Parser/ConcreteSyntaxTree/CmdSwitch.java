package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= SWITCH expr caseNTS defaultCaseNTS ENDSWITCH
public class CmdSwitch implements IConcSyn.ICmd {
	private final IToken switch_;
	private final IConcSyn.IExpr expr;
	private final IConcSyn.ICaseNTS caseNTS;
	private final IConcSyn.IDefaultCaseNTS defaultCaseNTS;
	private final IToken endswitch;

	public CmdSwitch(final IToken switch_, final IConcSyn.IExpr expr, final IConcSyn.ICaseNTS caseNTS,
					 final IConcSyn.IDefaultCaseNTS defaultCaseNTS, final IToken endswitch) {
		this.switch_ = switch_;
		this.expr = expr;
		this.caseNTS = caseNTS;
		this.defaultCaseNTS = defaultCaseNTS;
		this.endswitch = endswitch;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
		return null;
	}

	@Override
	public String toString(String indent) {
		return null;
	}
}
