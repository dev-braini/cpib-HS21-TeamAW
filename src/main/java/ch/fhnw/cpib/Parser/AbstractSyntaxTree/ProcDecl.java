package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.MechModes;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Ident;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ProcDecl extends AbsSynTreeNode implements IAbsSyn.IDecl {
	private Ident ident;
	private CpsCmd cpsCmd;
	private ArrayList<Param> params;
	private ArrayList<StoDecl> stoDecls;
	private boolean initCheckDone = false;
	
	public ProcDecl(Ident ident, ArrayList<Param> params, ArrayList<StoDecl> stoDecls, CpsCmd cpsCmd) {
		this.ident = ident;
		this.params = params;
		this.stoDecls = stoDecls;
		this.cpsCmd = cpsCmd;
	}

	public ArrayList<Param> getParams() {
		return params;
	}	
	
	public boolean getInitCheckDone() {
		return initCheckDone;
	}

	public void setInitCheckDone() {
		this.initCheckDone = true;
	}	
	
	@Override
	public String getIdentString() {
		return ident.getValue();
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
	public void saveNamespaceInfoToNode(
			HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		
		// Save the given namespace into the local namespace
		this.localStoresNamespace = new HashMap<>();
		
		// Save param list variables into local namespace
		for(Param param : params) {
			if(globalStoresNamespace.containsKey(param.getIdentString()))
				throw new NameAlreadyGloballyDeclaredError(param.getIdentString());			
			if(this.localStoresNamespace.containsKey(param.getIdentString()))
				throw new NameAlreadyDeclaredError(param.getIdentString());
			if(param.getMechMode().getValue() == MechModes.REF)
				param.getTypedIdent().setNeedToDeref();			
			this.localStoresNamespace.put(param.getIdentString(), param.getTypedIdent());
		}
		
		// Save local variables into local namespace
		for(StoDecl stoDecl : stoDecls) {
			if(globalStoresNamespace.containsKey(stoDecl.getIdentString()))
				throw new NameAlreadyGloballyDeclaredError(stoDecl.getIdentString());			
			if(this.localStoresNamespace.containsKey(stoDecl.getIdentString()))
				throw new NameAlreadyDeclaredError(stoDecl.getIdentString());
			this.localStoresNamespace.put(stoDecl.getIdentString(), stoDecl.getTypedIdent());
		}
		
		cpsCmd.saveNamespaceInfoToNode(this.localStoresNamespace);
	}	
	
	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		cpsCmd.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		localLocations = new HashMap<>();
		
		// add addresses of params to localLocations-map
		for(int i = 0; i < params.size(); i++) {
			// will be initialized from outside @ FunCallFactor
			localLocations.put(params.get(i).getIdentString(), i - params.size());
		}
		
		// add addresses of local variables to localLocations-map
		// first local variable is at relAddress 3
		for(int i = 0; i < stoDecls.size(); i++) {
			stoDecls.get(i).addIInstrToCodeArray(localLocations, simulateOnly);
			localLocations.put(stoDecls.get(i).getIdentString(), i+3);
		}		
		
		cpsCmd.addIInstrToCodeArray(localLocations, simulateOnly);
		
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.Return(params.size()));
		codeArrayPointer++;
	}	
	
	@Override
	public String toString(String indent) {
		String argumentIndent = indent + " ";
		String subIndent = indent + "  ";
		String s = indent + this.getClass().getSimpleName() + "\n";
		if(localStoresNamespace != null) {
            s += argumentIndent + "[localStoresNamespace]: " + localStoresNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";
        }
        		
		s += argumentIndent + "<ident>: " + ident.toString() + "\n";
		s += argumentIndent + "<cpsCmd>:";
		s += cpsCmd.toString(subIndent);	
		s += argumentIndent + "<params>:\n";
		for(Param param : params) {
			s += param.toString(subIndent);
		}
		s += argumentIndent + "<stoDecls>:\n";
		for(StoDecl stoDecl : stoDecls) {
			s += stoDecl.toString(subIndent);
		}		
		
		return s;
	}		

}
