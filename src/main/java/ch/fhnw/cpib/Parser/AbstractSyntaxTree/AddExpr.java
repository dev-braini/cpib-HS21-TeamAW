package ch.fhnw.cpib.Parser.AbstractSyntaxTree;


import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Operator;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class AddExpr extends AbsSynTreeNode implements IAbsSyn.IExpr {
	private Operator addOpr;
	private IAbsSyn.IExpr exprLeft;
	private IAbsSyn.IExpr exprRight;
	
	public AddExpr(Operator addOpr, IAbsSyn.IExpr exprLeft, IAbsSyn.IExpr exprRight) {
		this.addOpr = addOpr;
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
	}

	@Override
	public LRVal getLRValue() {
		return LRVal.RVAL;
	}

    @Override
    public Type getType() {
        return exprLeft.getType();
    }

    @Override
    public Types getTypeValue() {
        return getType().getValue();
    }

	@Override
	public void doTypeChecking() throws TypeCheckError {
		exprLeft.doTypeChecking();
		exprRight.doTypeChecking();
		
		if(exprLeft.getTypeValue() == Types.BOOL)
			throw new TypeCheckError(new Type(Types.INT64), exprLeft.getType());
		if(exprLeft.getType() != exprRight.getType())
			throw new TypeCheckError(exprLeft.getType(), exprRight.getType());
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		exprLeft.doInitChecking(globalProtected);
		exprRight.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws ICodeArray.CodeTooSmallError {
		
		exprLeft.addIInstrToCodeArray(localLocations, simulateOnly);
		exprRight.addIInstrToCodeArray(localLocations, simulateOnly);
		
		if(!simulateOnly) {
			switch(addOpr.getValue()) {
				case PLUS:
					codeArray.put(codeArrayPointer, new IInstructions.AddInt());
					break;
                case MINUS:
					codeArray.put(codeArrayPointer, new IInstructions.SubInt());
					break;
			}
		}		
		codeArrayPointer++;
	}

	@Override
	public String toString(String indent) {
		String nameIndent = indent;
		String argumentIndent = indent + " ";
		String subIndent = indent + "  ";
		String s = "";
		s += nameIndent + this.getClass().getName() + "\n";
		if(localStoresNamespace != null)
			s += argumentIndent + "[localStoresNamespace]: " + localStoresNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";		
		s += argumentIndent + "<addOpr>: " + addOpr.toString() + "\n";
		s += argumentIndent + "<exprLeft>:\n";
		s += exprLeft.toString(subIndent);
		s += argumentIndent + "<exprRight>:\n";
		s += exprRight.toString(subIndent);
		
		return s;
	}
}
