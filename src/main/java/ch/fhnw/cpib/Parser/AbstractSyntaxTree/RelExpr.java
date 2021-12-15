package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Operator;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class RelExpr extends AbsSynTreeNode implements IAbsSyn.IExpr{
	private Operator relOpr;
	private IAbsSyn.IExpr exprLeft;
	private IAbsSyn.IExpr exprRight;
	
	public RelExpr(Operator relOpr, IAbsSyn.IExpr exprLeft, IAbsSyn.IExpr exprRight) {
		this.relOpr = relOpr;
		this.exprLeft = exprLeft;
		this.exprRight = exprRight;
	}
	
	@Override
	public void saveNamespaceInfoToNode(HashMap<String, TypedIdent> localStoresNamespace)
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
		
		if(exprLeft.getTypeValue() == Types.BOOL)
			throw new TypeCheckError(new Type(Types.INT64), exprLeft.getType());
		if(exprLeft.getType().getValue() != exprRight.getType().getValue())
			throw new TypeCheckError(exprLeft.getType(), exprRight.getType());
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
			switch(relOpr.getValue()) {
				case EQ:
					codeArray.put(codeArrayPointer, new IInstructions.EqInt());
					break;
				case GE:
					codeArray.put(codeArrayPointer, new IInstructions.GeInt());
					break;
				case GT:
					codeArray.put(codeArrayPointer, new IInstructions.GtInt());
					break;
				case LE:
					codeArray.put(codeArrayPointer, new IInstructions.LeInt());
					break;
				case LT:
					codeArray.put(codeArrayPointer, new IInstructions.LtInt());
					break;	
				case NE:
					codeArray.put(codeArrayPointer, new IInstructions.NeInt());
					break;					
			}
		}
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
        		
		s += argumentIndent + "<relOpr>: " + relOpr.toString() + "\n";
		s += argumentIndent + "<exprLeft>:\n";
		s += exprLeft.toString(subIndent);
		s += argumentIndent + "<exprRight>:\n";
		s += exprRight.toString(subIndent);
		
		return s;
	}	
}
