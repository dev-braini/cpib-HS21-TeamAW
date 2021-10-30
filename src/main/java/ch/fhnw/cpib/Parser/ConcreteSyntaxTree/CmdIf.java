package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

// cmd ::= IF expr THEN cpsCmd ifelseNTS ENDIF
public class CmdIf implements IConcSyn.ICmd {
	public CmdIf() {

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
