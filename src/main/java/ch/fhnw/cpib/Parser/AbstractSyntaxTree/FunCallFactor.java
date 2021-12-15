package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Ident;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class FunCallFactor extends IdentFactor{
	private ArrayList<IAbsSyn.IExpr> expressions;

	public FunCallFactor(Ident ident, ArrayList<IAbsSyn.IExpr> expressions) {
		this.ident = ident;
		this.expressions = expressions;
	}
	
	@Override
	public void saveNamespaceInfoToNode(
			HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		for(IAbsSyn.IExpr expr : expressions) {
			expr.saveNamespaceInfoToNode(this.localStoresNamespace);
		}		
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		// Check if this function identifier is declared in the global namespace
		boolean declared = globalRoutinesNamespace.containsKey(ident.getValue());
		// If function is not declared in global namespace, throw exception
		if(!declared)
			throw new NameNotDeclaredError(ident.getValue());
		
		// check scope for each expression
		for(IAbsSyn.IExpr expr : expressions) {
			expr.doScopeChecking();
		}		
		
		// Param check
		// Same number of parameters as in declaration?
		FunDecl funDecl = (FunDecl)globalRoutinesNamespace.get(ident.getValue());
		int sizeFound = expressions.size();
		int sizeExpected = funDecl.getParams().size();
		if(sizeExpected != sizeFound)
			throw new InvalidParamCountError(sizeExpected, sizeFound);
		
		// Check if r- and l-value of parameters are correct
		for(int i = 0; i < funDecl.getParams().size(); i++) {
			LRVal lrValExpected = funDecl.getParams().get(i).getLRValue();
			LRVal lrValFound = expressions.get(i).getLRValue();
			if(lrValExpected == LRVal.LVAL && lrValFound == LRVal.RVAL)
				throw new LRValError(lrValExpected, lrValFound);
		}
	}
	
	@Override
	public LRVal getLRValue() {
		return LRVal.RVAL;
	}

    @Override
    public Type getType() {
        FunDecl funDecl = (FunDecl)globalRoutinesNamespace.get(ident.getValue());
        return funDecl.getReturnType();
    }

    @Override
    public Types getTypeValue() {
        return getType().getValue();
    }

	@Override
	public void doTypeChecking() throws TypeCheckError {
		for(IAbsSyn.IExpr expr : expressions) {
			expr.doTypeChecking();
		}
		
		FunDecl funDecl = (FunDecl)globalRoutinesNamespace.get(ident.getValue());
		for(int i = 0; i < funDecl.getParams().size(); i++) {
			Type typeExpected = funDecl.getParams().get(i).getTypedIdent().getType();
			Type typeFound = expressions.get(i).getType();
			if(typeExpected != typeFound)
				throw new TypeCheckError(typeExpected, typeFound);
		}
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		// Run the init checking for the function declaration
		FunDecl funDecl = (FunDecl)globalRoutinesNamespace.get(ident.getValue());
		// We need to run the init checking only once for the declaration
		if(!funDecl.getInitCheckDone()) {
			funDecl.setInitCheckDone();
			funDecl.doInitChecking(globalProtected);
		}
		
		for(IAbsSyn.IExpr expr : expressions) {
			expr.doInitChecking(globalProtected);
		}
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		FunDecl funDecl = (FunDecl)globalRoutinesNamespace.get(ident.getValue());
		// initialize return value
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.AllocBlock(1));
		codeArrayPointer++;
		
		for(int i = 0; i < expressions.size(); i++) {
			LRVal callerLRVal = expressions.get(i).getLRValue();
			LRVal calleeLRVal = funDecl.getParams().get(i).getLRValue();
			// callee wants a RVAL, so we can pass either an RVAL or LVAL (will be used as value)
			if(calleeLRVal == LRVal.RVAL) {
				expressions.get(i).addIInstrToCodeArray(localLocations, simulateOnly);
			
			// calee wants a LVAL, so it's only valid to pass an LVAL
			} else if (callerLRVal == LRVal.LVAL && calleeLRVal == LRVal.LVAL) {
				// Only LVal we have is a InitFactor
				InitFactor factor = (InitFactor)expressions.get(i);
				// Get the address
				if(!simulateOnly) {
					int address;
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
			// callee wants an LVAL, but an RVAL is passed (invalid)
			} else {
				throw new RuntimeException("caller.RVAL not supported for callee.LVAL");
			}
		}
		
		if(!simulateOnly) {
			int funAddress = globalRoutinesLocation.get(ident.getValue());
			codeArray.put(codeArrayPointer, new IInstructions.Call(funAddress));
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
        		
		s += argumentIndent + "<expressions>:\n";
		for(IAbsSyn.IExpr expr : expressions) {
			s += expr.toString(subIndent);
		}
		
		return s;
	}	

}
