package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class DebugInCmd extends AbsSynTreeNode implements IAbsSyn.ICmd {
	private IAbsSyn.IExpr expr;
	
	public DebugInCmd(IAbsSyn.IExpr expr) {
		this.expr = expr;
	}
	
	@Override
	public void saveNamespaceInfoToNode(
			HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		expr.saveNamespaceInfoToNode(this.localStoresNamespace);		
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		expr.doScopeChecking();
		// We need an 
		if(expr.getLRValue() != LRVal.LVAL)
			throw new LRValError(LRVal.LVAL, expr.getLRValue());
	}

	@Override
	public void doTypeChecking() throws TypeCheckError {
		expr.doTypeChecking();
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		// now lets check if we try to write something into an already written constant
		// expr can only be an Init-Factor 
		InitFactor factor = (InitFactor)expr;
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
		
		expr.doInitChecking(globalProtected);
		
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		InitFactor factor = (InitFactor)expr;
		// Get address
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
		if(!simulateOnly) {
			if(factor.getTypeValue() == Types.BOOL) {
				codeArray.put(codeArrayPointer, new IInstructions.InputBool(factor.ident.getValue()));
			} else if (factor.getTypeValue() == Types.INT64) {
				codeArray.put(codeArrayPointer, new IInstructions.InputInt(factor.ident.getValue()));
			} else {
				throw new RuntimeException("No type found");
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
        		
		s += argumentIndent + "<expr>:\n";
		s += expr.toString(subIndent);
		
		return s;
	}	
}
