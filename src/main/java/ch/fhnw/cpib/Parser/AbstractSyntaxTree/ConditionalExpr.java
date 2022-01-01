package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class ConditionalExpr extends AbsSynTreeNode implements IAbsSyn.IExpr {
	private IAbsSyn.IExpr exprConditional;
	private IAbsSyn.IExpr exprTrue;
	private IAbsSyn.IExpr exprFalse;
	
	public ConditionalExpr(IAbsSyn.IExpr exprConditional, IAbsSyn.IExpr exprTrue,
                           IAbsSyn.IExpr exprFalse) {
		this.exprConditional = exprConditional;
		this.exprTrue = exprTrue;
		this.exprFalse = exprFalse;
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		exprConditional.doScopeChecking();
		exprTrue.doScopeChecking();
		exprFalse.doScopeChecking();
	}

	@Override
	public void saveNamespaceInfoToNode(
			HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		exprConditional.saveNamespaceInfoToNode(this.localStoresNamespace);
		exprTrue.saveNamespaceInfoToNode(this.localStoresNamespace);
		exprFalse.saveNamespaceInfoToNode(this.localStoresNamespace);
		
	}

	@Override
	public LRVal getLRValue() {
		return LRVal.RVAL;
	}

	@Override
	public void doTypeChecking() throws TypeCheckError {
		exprConditional.doTypeChecking();
		exprTrue.doTypeChecking();
		exprFalse.doTypeChecking();
		
		if(exprConditional.getTypeValue() != Types.BOOL)
			throw new TypeCheckError(new Type(Types.BOOL), exprConditional.getType());
		if(exprTrue.getTypeValue() != exprFalse.getTypeValue())
			throw new TypeCheckError(exprTrue.getType(), exprFalse.getType());
	}

    @Override
    public Type getType() {
        return exprTrue.getType();
    }

    @Override
    public Types getTypeValue() {
        return getType().getValue();
    }

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		exprConditional.doInitChecking(globalProtected);
		exprTrue.doInitChecking(globalProtected);
		exprFalse.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		// get the size of exprTrue by simulating the add action
		int codeArrayPointerBefore = codeArrayPointer;
		
		exprTrue.addIInstrToCodeArray(localLocations, true);
		int exprTrueSize = codeArrayPointer - codeArrayPointerBefore + 1; // + 1 for unconditional jump after exprFalse

		// reset pointer
		codeArrayPointer = codeArrayPointerBefore;
		
		// get the size of exprFalse
		exprFalse.addIInstrToCodeArray(localLocations, true);
		int exprFalseSize = codeArrayPointer - codeArrayPointerBefore;
		
		// reset pointer
		codeArrayPointer = codeArrayPointerBefore;
		
		// now really add the staff
		// add the boolean for the conditional check onto the stack
		exprConditional.addIInstrToCodeArray(localLocations, simulateOnly);
		// now add the jump condition to see if we had to continue (true part) or to jump (false part)
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.CondJump(codeArrayPointer + 1 + exprTrueSize));
		codeArrayPointer++;
		// now add the true part
		exprTrue.addIInstrToCodeArray(localLocations, simulateOnly);
		// now add the unconditional jump to jump after the false part (we already processed the true part ...)
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.UncondJump(codeArrayPointer + 1 + exprFalseSize));
		codeArrayPointer++;
		// now add the false part		
		exprFalse.addIInstrToCodeArray(localLocations, simulateOnly);
		
	}

	@Override
	public String toString(String indent) {
		String argumentIndent = indent + " ";
		String subIndent = indent + "  ";
		String s = indent + this.getClass().getSimpleName() + "\n";
		if(localStoresNamespace != null) {
            s += argumentIndent + "[localStoresNamespace]: " + localStoresNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";
        }
        		
		s += argumentIndent + "<exprConditional>:\n";
		s += exprConditional.toString(subIndent);
		s += argumentIndent + "<exprTrue>:\n";
		s += exprTrue.toString(subIndent);
		s += argumentIndent + "<exprFalse>:\n";
		s += exprFalse.toString(subIndent);
		
		return s;
	}	
	
}
