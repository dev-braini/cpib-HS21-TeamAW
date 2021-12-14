package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import java.util.HashMap;

import ch.fhnw.cpib.Parser.ConcreteSyntaxTree.IConcSyn;
import ch.fhnw.cpib.VM.ICodeArray;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.Errors.*;

public class AbsSyn {
    private final AbsSynTreeNode root;

    public AbsSyn(IConcSyn.IProgram conSynTreeRoot) {
        this.root = (AbsSynTreeNode) conSynTreeRoot.toAbsSyn();
    }

    @Override
    public String toString() {
        return root.toString("");
    }

    public void doScopeChecking() throws NameAlreadyDeclaredError, NameNotDeclaredError, NameAlreadyGloballyDeclaredError, LRValError, InvalidParamCountError, AlreadyInitializedError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
        root.saveNamespaceInfoToNode(null);
        root.doScopeChecking();
    }

    public void doTypeChecking() throws TypeCheckError {
        root.doTypeChecking();
    }

    public void doInitChecking() throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
        root.doInitChecking(false);
    }

    public ICodeArray getCodeArray() throws CodeTooSmallError {
        root.addIInstrToCodeArray(new HashMap<String, Integer>(), false);
        // shrink the array
        AbsSynTreeNode.codeArray.resize();
        return AbsSynTreeNode.codeArray;
    }
}
