package ch.fhnw.cpib.Parser.AbstractSyntaxTree;


import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CpsCmd extends AbsSynTreeNode implements IAbsSyn.ICmd {
	private ArrayList<IAbsSyn.ICmd> commands;

	public CpsCmd(ArrayList<IAbsSyn.ICmd> commands) {
		this.commands = commands;
	}
	
	@Override
	public void saveNamespaceInfoToNode(
			HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		for(IAbsSyn.ICmd command : commands) {
			command.saveNamespaceInfoToNode(this.localStoresNamespace);
		}		
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		for(IAbsSyn.ICmd command : commands) {
			command.doScopeChecking();
		}
	}

	@Override
	public void doTypeChecking() throws TypeCheckError {
		for(IAbsSyn.ICmd command : commands) {
			command.doTypeChecking();
		}
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		for(IAbsSyn.ICmd command : commands) {
			command.doInitChecking(globalProtected);
		}
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		for(IAbsSyn.ICmd command : commands) {
			command.addIInstrToCodeArray(localLocations, simulateOnly);
		}		
	}

	@Override
	public String toString(String indent) { System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> toString(): " + indent);
		String nameIndent = indent;
		String argumentIndent = indent + " ";
		String subIndent = indent + "  ";
		String s = "";
		s += nameIndent + this.getClass().getName() + "\n";
		if(localStoresNamespace != null)
			s += argumentIndent + "[localStoresNamespace]: " + localStoresNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";		
		s += argumentIndent + "<commands>:\n";
		for(IAbsSyn.ICmd cmd : commands) {
			s += cmd.toString(subIndent);
		}
		
		return s;
	}	
}
