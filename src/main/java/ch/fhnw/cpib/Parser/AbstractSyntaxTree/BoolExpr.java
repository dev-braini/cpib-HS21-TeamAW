package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Operator;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class BoolExpr extends AbsSynTreeNode implements IAbsSyn.IExpr{
	private Operator boolOpr;
	private IAbsSyn.IExpr exprLeft;
	private IAbsSyn.IExpr exprRight;
	
	public BoolExpr(Operator boolOpr, IAbsSyn.IExpr exprLeft, IAbsSyn.IExpr exprRight) {
		this.boolOpr = boolOpr;
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
	public void doTypeChecking() throws TypeCheckError {
		exprLeft.doTypeChecking();
		exprRight.doTypeChecking();

		if(exprLeft.getTypeValue() != Types.BOOL)
			throw new TypeCheckError(new Type(Types.BOOL), exprLeft.getType());
		if(exprRight.getTypeValue() != Types.BOOL)
			throw new TypeCheckError(new Type(Types.BOOL), exprRight.getType());
	}

    @Override
    public Type getType() {
        return new Type(getTypeValue());
    }

    @Override
    public Types getTypeValue() {
        return Types.BOOL;
    }

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		exprLeft.doInitChecking(globalProtected);
		exprRight.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {

		exprLeft.addIInstrToCodeArray(localLocations, simulateOnly);
		exprRight.addIInstrToCodeArray(localLocations, simulateOnly);
		
		if(!simulateOnly) {
			switch(boolOpr.getValue()) {
				case AND:
					codeArray.put(codeArrayPointer, new IInstructions.AndBool());
					break;
				case OR:
					codeArray.put(codeArrayPointer, new IInstructions.OrBool());
					break;
				case CAND:
					codeArray.put(codeArrayPointer, new IInstructions.CAndBool());
					break;
				case COR:
					codeArray.put(codeArrayPointer, new IInstructions.COrBool());
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
		s += argumentIndent + "<boolOpr>: " + boolOpr.toString() + "\n";
		s += argumentIndent + "<exprLeft>:\n";
		s += exprLeft.toString(subIndent);
		s += argumentIndent + "<exprRight>:\n";
		s += exprRight.toString(subIndent);
		
		return s;
	}	
}
