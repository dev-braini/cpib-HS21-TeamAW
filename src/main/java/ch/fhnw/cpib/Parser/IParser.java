package ch.fhnw.cpib.Parser;

import ch.fhnw.cpib.Errors.GrammarError;
import ch.fhnw.cpib.Parser.ConcreteSyntaxTree.IConcSyn;

public interface IParser {
    IConcSyn.IProgram parse() throws GrammarError;
}
