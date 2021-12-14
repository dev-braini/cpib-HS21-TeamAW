package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.Case;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.SwitchCmd;
import ch.fhnw.cpib.Token.IToken;

import java.util.ArrayList;

// cmd ::= SWITCH expr caseNTS defaultCaseNTS ENDSWITCH
public class CmdSwitch implements IConcSyn.ICmd {
    protected final IToken                   switch_;
    protected final IConcSyn.IExpr           expr;
    protected final IConcSyn.ICaseNTS        caseNTS;
    protected final IConcSyn.IDefaultCaseNTS defaultCaseNTS;
    protected final IToken                   endswitch;

	public CmdSwitch(final IToken switch_, final IConcSyn.IExpr expr, final IConcSyn.ICaseNTS caseNTS,
					 final IConcSyn.IDefaultCaseNTS defaultCaseNTS, final IToken endswitch) {
        this.switch_        = switch_;
        this.expr           = expr;
        this.caseNTS        = caseNTS;
        this.defaultCaseNTS = defaultCaseNTS;
        this.endswitch      = endswitch;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        ArrayList<Case> tmp = new ArrayList<>();
        return new SwitchCmd(expr.toAbsSyn(), caseNTS.toAbsSyn(tmp), defaultCaseNTS.toAbsSyn());
	}
}
