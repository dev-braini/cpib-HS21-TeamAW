package ch.fhnw.cpib.Parser.ConcreteSyntaxTree;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.IAbsSyn;

//program ::= PROGRAM IDENT globalNTS DO cpsCmd ENDPROGRAM
public class Program implements IConcSyn.IProgram {
    public Program() {

    }

    @Override
    public IAbsSyn.IProgram toAbsSyn() {
        return null;
    }

    @Override
    public String toString(String indent) {
        return null;
    }
}
