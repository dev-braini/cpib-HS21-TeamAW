package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// param ::= mechModeNTS changeModeNTS typedIdent
public class Param implements IConcSyn.IParam {
	private final IConcSyn.IMechModeNTS   mechModeNTS;
	private final IConcSyn.IChangeModeNTS changeModeNTS;
	private final IConcSyn.ITypedIdent    typedIdent;

	public Param(final IConcSyn.IMechModeNTS mechModeNTS, final IConcSyn.IChangeModeNTS changeModeNTS,
				 final IConcSyn.ITypedIdent typedIdent) {
		this.mechModeNTS   = mechModeNTS;
		this.changeModeNTS = changeModeNTS;
		this.typedIdent    = typedIdent;
	}

	@Override
	public IAbsSyn.IParam toAbsSyn() {
        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.Param(
                mechModeNTS.toAbsSyn(),
                changeModeNTS.toAbsSyn(),
                typedIdent.toAbsSyn()
        );
	}
}
