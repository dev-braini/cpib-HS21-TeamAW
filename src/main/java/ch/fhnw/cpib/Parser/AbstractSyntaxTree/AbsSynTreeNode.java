package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.VM.CodeArray;
import ch.fhnw.cpib.VM.ICodeArray;

import java.util.HashMap;

public abstract class AbsSynTreeNode implements IAbsSyn.IAbsSynTreeNode {
    static HashMap<String, TypedIdent> globalStoresNamespace = new HashMap<>();
    static HashMap<String, IAbsSyn.IDecl> globalRoutinesNamespace = new HashMap<>();
    HashMap<String, TypedIdent> localStoresNamespace = new HashMap<>();

    static HashMap<String, Integer> globalStoresLocation = new HashMap<>();
    static HashMap<String, Integer> globalRoutinesLocation = new HashMap<>();
    static int codeArrayPointer = 0;
    static ICodeArray codeArray = new CodeArray(1024);

    public void setInit(TypedIdent ident)  {
        if(localStoresNamespace.containsKey(ident.getValue()))
            localStoresNamespace.get(ident.getValue()).setInit();
    }
}
