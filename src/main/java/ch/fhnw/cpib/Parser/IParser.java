package ch.fhnw.cpib.Parser;

import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Parser.AbstractSyntaxTree.AbsSyn;
import ch.fhnw.cpib.Parser.ConcreteSyntaxTree.IConcSyn;

public interface IParser {
    AbsSyn parse() throws GrammarError, NameAlreadyDeclaredError, NameNotDeclaredError, NameAlreadyGloballyDeclaredError, LRValError, InvalidParamCountError, AlreadyInitializedError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError, TypeCheckError, NotInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError;
}
