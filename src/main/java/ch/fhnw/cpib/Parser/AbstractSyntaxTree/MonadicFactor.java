package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.MonadicOpr;
import token.Monadicopr;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class MonadicFactor extends AbsSynTreeNode implements IAbsSyn.IFactor {
	private MonadicOpr monadicOpr;
	private IAbsSyn.IFactor factor;
	
	public MonadicFactor(MonadicOpr monadicOpr, IAbsSyn.IFactor factor) {
		this.monadicOpr = monadicOpr;
		this.factor = factor;
	}

	public MonadicFactor(MonadicOpr monadicOpr) {
		this.monadicOpr = monadicOpr;
	}
	
	@Override
	public void saveNamespaceInfoToNode(HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		factor.saveNamespaceInfoToNode(this.localStoresNamespace);
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		factor.doScopeChecking();
	}
	
	@Override
	public LRVal getLRValue() {
		return LRVal.RVAL;
	}

    @Override
    public Type getType() {
        return factor.getType();
    }

    @Override
    public Types getTypeValue() {
        return getType().getValue();
    }

	@Override
	public void doTypeChecking() throws TypeCheckError {
		factor.doTypeChecking();
		
		if(monadicOpr.getOperator() == token.Token.NOTOPR && factor.getTypeValue() != Types.BOOL)
			throw new TypeCheckError(new Type(Types.BOOL), factor.getType());
		if(monadicOpr.getOperator() instanceof token.Addopr && factor.getTypeValue() != Types.INT64)
			throw new TypeCheckError(new Type(Types.INT64), factor.getType());
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		factor.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		
		// Add the value on top of stack
		factor.addIInstrToCodeArray(localLocations, simulateOnly);
		
		// Negate it
		if(!simulateOnly) {
			if(monadicOpr.getOperator() == token.Token.NOTOPR) {
				codeArray.put(codeArrayPointer, new IInstructions.NegBool());
			} else if(monadicOpr.getOperator() instanceof token.Addopr && (token.Addopr)monadicOpr.getOperator() == token.Addopr.MINUS) {
				codeArray.put(codeArrayPointer, new IInstructions.NegInt());		
			} else {
				throw new RuntimeException("UNSUPPORTED MONADIC OPERATOR!");
			}
		}
		codeArrayPointer++;	
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
		s += argumentIndent + "<monadicOpr>: " + monadicOpr.toString() + "\n";
		s += argumentIndent + "<factor>:\n";
		s += factor.toString(subIndent);
		
		return s;
	}	
}
