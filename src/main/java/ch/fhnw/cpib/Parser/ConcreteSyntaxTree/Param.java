package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

// param ::= mechModeNTS changeModeNTS typedIdent
public class Param implements IConcSyn.IParam {
    protected final IConcSyn.IMechModeNTS   mechModeNTS;
    protected final IConcSyn.IChangeModeNTS changeModeNTS;
    protected final IConcSyn.ITypedIdent    typedIdent;

	public Param(final IConcSyn.IMechModeNTS mechModeNTS, final IConcSyn.IChangeModeNTS changeModeNTS,
				 final IConcSyn.ITypedIdent typedIdent) {
		this.mechModeNTS   = mechModeNTS;
		this.changeModeNTS = changeModeNTS;
		this.typedIdent    = typedIdent;
	}

	@Override
	public ch.fhnw.cpib.Parser.AbstractSyntaxTree.Param toAbsSyn() {
        return new ch.fhnw.cpib.Parser.AbstractSyntaxTree.Param(
                mechModeNTS.toAbsSyn(),
                changeModeNTS.toAbsSyn(),
                typedIdent.toAbsSyn()
        );
	}
}
