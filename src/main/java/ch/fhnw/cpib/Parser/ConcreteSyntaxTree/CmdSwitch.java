package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.SwitchCmd;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// cmd ::= SWITCH expr caseNTS defaultCaseNTS ENDSWITCH
public class CmdSwitch implements IConcSyn.ICmd {
    protected final IConcSyn.IExpr           expr;
    protected final IConcSyn.ICaseNTS        caseNTS;
    protected final IConcSyn.IDefaultCaseNTS defaultCaseNTS;

	public CmdSwitch(final IToken switch_, final IConcSyn.IExpr expr, final IConcSyn.ICaseNTS caseNTS,
					 final IConcSyn.IDefaultCaseNTS defaultCaseNTS, final IToken endswitch) {
		this.expr           = expr;
		this.caseNTS        = caseNTS;
		this.defaultCaseNTS = defaultCaseNTS;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        ArrayList<IAbsSyn.ICase> tmp = new ArrayList<>();
        return new SwitchCmd(expr.toAbsSyn(), caseNTS.toAbsSyn(tmp), defaultCaseNTS.toAbsSyn());
	}
}
