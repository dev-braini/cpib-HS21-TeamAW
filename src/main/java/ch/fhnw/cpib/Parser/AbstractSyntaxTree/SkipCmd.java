package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;

import java.util.HashMap;
import java.util.stream.Collectors;

public class SkipCmd extends AbsSynTreeNode implements IAbsSyn.ICmd {
	public SkipCmd() { }
	
	@Override
	public void saveNamespaceInfoToNode(HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError { }
	
	@Override
	public void doTypeChecking() throws TypeCheckError { }

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError { }

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError { }

	@Override
	public String toString(String indent) {
		String nameIndent = indent;
		String argumentIndent = indent + " ";
		String s = "";
		s += nameIndent + this.getClass().getName() + "\n";
		if(localStoresNamespace != null)
			s += argumentIndent + "[localStoresNamespace]: " + localStoresNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";		
		
		return s;
	}	
}
