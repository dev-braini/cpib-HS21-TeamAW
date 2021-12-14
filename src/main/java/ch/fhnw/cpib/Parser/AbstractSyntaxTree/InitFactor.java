package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Ident;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class InitFactor extends IdentFactor{
	private boolean init;

	public InitFactor(Ident ident, boolean init) {
		this.ident = ident;
		this.init = init;
	}
	
	@Override
	public void saveNamespaceInfoToNode(HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;		
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		// Check if this variable identifier is declared in the local or global namespace
		boolean declared = false;
		if(localStoresNamespace.containsKey(ident.getValue()))
			declared = true;
		if(globalStoresNamespace.containsKey(ident.getValue()))
			declared = true;
		// If variable is not declared in local or global namespace, throw exception
		if(!declared)
			throw new NameNotDeclaredError(ident.getValue());
	}

	@Override
	public LRVal getLRValue() {
		return LRVal.LVAL;
	}

    @Override
    public Type getType() {
        TypedIdent typedIdent;
        if(localStoresNamespace.containsKey(ident.getValue())) {
            typedIdent = localStoresNamespace.get(ident.getValue());
        } else {
            typedIdent = globalStoresNamespace.get(ident.getValue());
        }
        return typedIdent.getType();
    }

    @Override
    public Types getTypeValue() {
        return getType().getValue();
    }

	@Override
	public void doTypeChecking() throws TypeCheckError {
		// Do nothing
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		// Get the typedIdent for this factor
		TypedIdent typedIdent = null;
		boolean isGlobal = false;
		if(this.localStoresNamespace.containsKey(ident.getValue()))
			typedIdent = this.localStoresNamespace.get(ident.getValue());			
		if(globalStoresNamespace.containsKey(ident.getValue())) {
			typedIdent = globalStoresNamespace.get(ident.getValue());
			isGlobal = true;
		}
		if(init) {
			// If this is a global variable, and we try to initialize it in a protected scope, throw an error
			if(globalProtected && isGlobal)
				throw new GlobalInitializationProhibitedError(typedIdent.getIdent());
			// Throw an error if this typedIdent is already initialized
			if(typedIdent.getInit()) {
				throw new  AlreadyInitializedError(typedIdent.getIdent());
			} else {
				typedIdent.setInit();
			}
		} else {	
			// Throw an error if this typedIdent is not yet initialized and we try to use it
			if(!typedIdent.getInit())
				throw new NotInitializedError(typedIdent.getIdent());
		}
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		// Only LVal we have is a InitFactor
		// Get the address
		if(!simulateOnly) {
			int address;
			if(globalStoresLocation.containsKey(ident.getValue())) {
				address = globalStoresLocation.get(ident.getValue());
				codeArray.put(codeArrayPointer, new IInstructions.LoadAddrAbs(address));
			} else if (localLocations.containsKey(ident.getValue())) {
				address = localLocations.get(ident.getValue());
				codeArray.put(codeArrayPointer, new IInstructions.LoadAddrRel(address));
			} else {
				throw new RuntimeException("No location found for variable " + ident.getValue());
			}
		}
		codeArrayPointer++;
		
		// Now copy the real value to this stack place (dereference)
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.Deref());
		codeArrayPointer++;	
		
		// If this needs to be dereferenced (=Param), dereference it once more
		TypedIdent variableIdent = null;	
		if(globalStoresNamespace.containsKey(ident.getValue())) {
			variableIdent = globalStoresNamespace.get(ident.getValue());
		} else {
			variableIdent = localStoresNamespace.get(ident.getValue());
		}		
		if(variableIdent.getNeedToDeref()) {
			if(!simulateOnly)
				codeArray.put(codeArrayPointer, new IInstructions.Deref());
			codeArrayPointer++;			
		}
	}

	@Override
	public String toString(String indent) { System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> toString(): " + indent);
		String nameIndent = indent;
		String argumentIndent = indent + " ";
		String s = "";
		s += nameIndent + this.getClass().getName() + "\n";
		if(localStoresNamespace != null)
			s += argumentIndent + "[localStoresNamespace]: " + localStoresNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";		
		s += argumentIndent + "<init>: " + init + "\n";
		
		return s;
	}	
}
