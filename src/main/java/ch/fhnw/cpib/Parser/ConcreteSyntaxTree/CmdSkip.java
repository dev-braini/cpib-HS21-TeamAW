package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.SkipCmd;
import ch.fhnw.cpib.Token.IToken;

// cmd ::= SKIP
public class CmdSkip implements IConcSyn.ICmd {
    protected final IToken skip;

	public CmdSkip(final IToken skip) {
		this.skip = skip;
	}

	@Override
	public IAbsSyn.ICmd toAbsSyn() {
        return new SkipCmd();
	}
}
