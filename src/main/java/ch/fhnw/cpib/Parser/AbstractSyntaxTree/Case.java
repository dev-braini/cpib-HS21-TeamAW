package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Literal;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Case extends AbsSynTreeNode {
	private Literal literal;
	private CpsCmd cpsCmd;
	
	public Case(Literal literal, CpsCmd cpsCmd) {
		this.literal = literal;
		this.cpsCmd = cpsCmd;
	}
	
	public Type getType() {
		return literal.getType();
	}
	
	public Literal getLiteral() {
		return literal;
	}
	
	@Override
	public void saveNamespaceInfoToNode(
			HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		cpsCmd.saveNamespaceInfoToNode(this.localStoresNamespace);		
	}

	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		cpsCmd.doScopeChecking();
	}
	
	@Override
	public void doTypeChecking() throws TypeCheckError {
		cpsCmd.doTypeChecking();
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		cpsCmd.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		cpsCmd.addIInstrToCodeArray(localLocations, simulateOnly);
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
		s += argumentIndent + "<literal>: " + literal.toString() + "\n";
		s += argumentIndent + "<cpsCmd>:\n";
		s += cpsCmd.toString(subIndent);
		
		return s;
	}	
}
