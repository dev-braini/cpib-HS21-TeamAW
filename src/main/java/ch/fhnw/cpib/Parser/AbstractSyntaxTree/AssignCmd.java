package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class AssignCmd extends AbsSynTreeNode implements IAbsSyn.ICmd {
	private IAbsSyn.IExpr exprLeft;
	private IAbsSyn.IExpr exprRight;

	public AssignCmd(IAbsSyn.IExpr exprLeft, IAbsSyn.IExpr exprRight) {
		this.exprLeft = exprLeft;
		this.exprRight = exprRight;
	}
	
	@Override
	public void saveNamespaceInfoToNode(
			HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		exprLeft.saveNamespaceInfoToNode(this.localStoresNamespace);
		exprRight.saveNamespaceInfoToNode(this.localStoresNamespace);
		
	}

	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		exprLeft.doScopeChecking();
		exprRight.doScopeChecking();
		
		if(exprLeft.getLRValue() == LRVal.RVAL)
			throw new LRValError(LRVal.LVAL, exprLeft.getLRValue());
	}

	@Override
	public void doTypeChecking() throws TypeCheckError {
		exprLeft.doTypeChecking();
		exprRight.doTypeChecking();
		
		if(exprLeft.getType() != exprRight.getType())
			throw new TypeCheckError(exprLeft.getType(), exprRight.getType());		
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		// lets check if we try to write something into an already written constant
		// exprLeft can only be an Init-Factor 
		InitFactor factor = (InitFactor)exprLeft;
		// is the variable already initialized (= written once) and is a constant?
		// if yes, we are writing to an already initialized constant --> not allowed
		TypedIdent typedIdent = null;
		if(this.localStoresNamespace.containsKey(factor.ident.getValue()))
			typedIdent = this.localStoresNamespace.get(factor.ident.getValue());			
		if(globalStoresNamespace.containsKey(factor.ident.getValue())) {
			typedIdent = globalStoresNamespace.get(factor.ident.getValue());
		}
		// If this is a const and it is already initialized (once written to), throw an error
		if(typedIdent.getConst() && typedIdent.getInit())
			throw new CannotAssignToConstError(factor.ident);		
		
		exprLeft.doInitChecking(globalProtected);
		exprRight.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		// Get the address of the left expression
		InitFactor factor = (InitFactor)exprLeft;
		int address;
		if(!simulateOnly) {
			if(globalStoresLocation.containsKey(factor.ident.getValue())) {
				address = globalStoresLocation.get(factor.ident.getValue());
				codeArray.put(codeArrayPointer, new IInstructions.LoadAddrAbs(address));
			} else if (localLocations.containsKey(factor.ident.getValue())) {
				address = localLocations.get(factor.ident.getValue());
				codeArray.put(codeArrayPointer, new IInstructions.LoadAddrRel(address));
			} else {
				throw new RuntimeException("No location found for variable " + factor.ident.getValue() + " ?????");
			}
		}
		codeArrayPointer++;
		
		// If this needs to be dereferenced (=Param), dereference it once more
		TypedIdent variableIdent = null;	
		if(globalStoresNamespace.containsKey(factor.ident.getValue())) {
			variableIdent = globalStoresNamespace.get(factor.ident.getValue());
		} else {
			variableIdent = localStoresNamespace.get(factor.ident.getValue());
		}		
		if(variableIdent.getNeedToDeref()) {
			if(!simulateOnly)
				codeArray.put(codeArrayPointer, new IInstructions.Deref());
			codeArrayPointer++;			
		}
		
		// Get the value of the exprRight (RVal)
		exprRight.addIInstrToCodeArray(localLocations, simulateOnly);
		
		// Now copy our value to the "remote" stack place (store)
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.Store());
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
        		
		s += argumentIndent + "<exprLeft>:\n";
		s += exprLeft.toString(subIndent);
		s += argumentIndent + "<exprRight>:\n";
		s += exprRight.toString(subIndent);
		
		return s;
	}	
}
