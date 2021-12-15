package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class DebugOutCmd extends AbsSynTreeNode implements IAbsSyn.ICmd {
	private IAbsSyn.IExpr expr;
	
	public DebugOutCmd(IAbsSyn.IExpr expr) {
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
	}

	@Override
	public void doTypeChecking() throws TypeCheckError {
		expr.doTypeChecking();
		// TODO: Check LRVal		
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		expr.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		
		expr.addIInstrToCodeArray(localLocations, simulateOnly);
		
		String indicator;
		if(expr instanceof InitFactor) {
			indicator = ((InitFactor)expr).ident.getValue();
		} else {
			indicator = "<anonymous>";
		}
		
		if(!simulateOnly) {
			if(expr.getTypeValue() == Types.BOOL) {
				codeArray.put(codeArrayPointer, new IInstructions.OutputBool(indicator));
			} else if (expr.getTypeValue() == Types.INT64) {
				codeArray.put(codeArrayPointer, new IInstructions.OutputInt(indicator));
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
