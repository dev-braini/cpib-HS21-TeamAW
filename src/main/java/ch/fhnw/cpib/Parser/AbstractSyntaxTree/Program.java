package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Ident;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Program extends AbsSynTreeNode {
	private Ident ident;
	private ArrayList<IAbsSyn.IDecl> globalDeclarations;
	private CpsCmd cpsCmd;
	
	public Program(Ident ident, ArrayList<IAbsSyn.IDecl> globalDeclarations,
			CpsCmd cpsCmd) {
		this.ident = ident;
		this.globalDeclarations = globalDeclarations;
		this.cpsCmd = cpsCmd;
	}

	private void analyzeGlobalDeclarations() throws NameAlreadyDeclaredError {
		for(IAbsSyn.IDecl decl : globalDeclarations) {
			if(decl instanceof StoDecl) {
				if(AbsSynTreeNode.globalStoresNamespace.containsKey(decl.getIdentString()))
					throw new NameAlreadyDeclaredError(decl.getIdentString());
				AbsSynTreeNode.globalStoresNamespace.put(decl.getIdentString(), ((StoDecl)decl).getTypedIdent());
			} else {
				if(AbsSynTreeNode.globalRoutinesNamespace.containsKey(decl.getIdentString()))
					throw new NameAlreadyDeclaredError(decl.getIdentString());	
				AbsSynTreeNode.globalRoutinesNamespace.put(decl.getIdentString(), decl);
			}
		}
	}	
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		for(IAbsSyn.IDecl decl : globalDeclarations) {
			// For funDecl and procDecl, do the scope checking
			if(!(decl instanceof StoDecl))
				decl.doScopeChecking();
		}
		
		cpsCmd.doScopeChecking();
	}
	
	@Override
	public void doTypeChecking() throws TypeCheckError {
		for(IAbsSyn.IDecl decl : globalDeclarations) {
			// For funDecl and procDecl, do the type checking
			if(!(decl instanceof StoDecl))
				decl.doTypeChecking();
		}
		
		cpsCmd.doTypeChecking();		
	}

	@Override
	public void saveNamespaceInfoToNode(HashMap<String, TypedIdent> localStoresNamespace) throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		// Ignore the passed namespaces
		analyzeGlobalDeclarations();
		
		for(IAbsSyn.IDecl decl : globalDeclarations) {
			// For funDecl and procDecl, store the local variables into the nodes and child nodes
			if(!(decl instanceof StoDecl))
				decl.saveNamespaceInfoToNode(new HashMap<String, TypedIdent>());
		}
	}
	
	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		// We check only the cpsCmd, as the globalDeclarations (e.g. FunDecl) will be checked at the call location
		cpsCmd.doInitChecking(globalProtected);		
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		// For all global storage declarations, allocate blocks and save addresses to globalStoresLocation-map
		for(IAbsSyn.IDecl decl : globalDeclarations) {
			if(decl instanceof StoDecl) {
				globalStoresLocation.put(decl.getIdentString(), codeArrayPointer);
				decl.addIInstrToCodeArray(localLocations, simulateOnly);
			}
		}
		
		// save current codeArrayPointer
		int codeArrayPointerBefore = codeArrayPointer;
		// For all global function and procedure declarations, simulate add IInstr and save addresses to globalRoutinesLocation-map
		for(IAbsSyn.IDecl decl : globalDeclarations) {
			if(!(decl instanceof StoDecl)) {
				globalRoutinesLocation.put(decl.getIdentString(), codeArrayPointer + 1); // + 1 because we will have a conditional jump before the declaration block (see below)
				decl.addIInstrToCodeArray(localLocations, true);
			}
		}
		// after going through all declarations, the pointer is at the position after the declaration (= start of programm)
		// +1 for the conditional jump execution
		int addressAfterDeclaration = codeArrayPointer + 1;
		// restore the previous codeArrayPointer
		codeArrayPointer = codeArrayPointerBefore;
		
		// Jump to beginning of programm
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.UncondJump(addressAfterDeclaration));
		codeArrayPointer++;		
		
		// For all global function and procedure declarations, now really add IInstr and save addresses to globalRoutinesLocation-map
		for(IAbsSyn.IDecl decl : globalDeclarations) {
			if(!(decl instanceof StoDecl)) {
				decl.addIInstrToCodeArray(localLocations, simulateOnly);
			}
		}		
		// For cpsCommand
		cpsCmd.addIInstrToCodeArray(localLocations, simulateOnly);
		
		// Add stop exec
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.Stop());
		codeArrayPointer++;
		
		System.out.println("codeArrayPoiner: " + codeArrayPointer);
		
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
		if(AbsSynTreeNode.globalStoresNamespace != null)
			s += argumentIndent + "[globalStoresNamespace]: " + globalStoresNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";
		if(AbsSynTreeNode.globalRoutinesNamespace != null)
			s += argumentIndent + "[globalRoutinesNamespace]: " + globalRoutinesNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";		
		s += argumentIndent + "<ident>: " + ident.toString() + "\n";
		s += argumentIndent + "<globalDeclarations>:\n";
		for(IAbsSyn.IDecl decl : globalDeclarations) {
			s += decl.toString(subIndent);
		}
		s += argumentIndent + "<cpsCmd>:";
		s += cpsCmd.toString(subIndent);
		
		return s;
	}

}
