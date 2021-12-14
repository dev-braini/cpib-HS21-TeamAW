package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;

import java.util.HashMap;
import java.util.stream.Collectors;

public class ExprFactor extends AbsSynTreeNode implements IAbsSyn.IFactor{
	private IAbsSyn.IExpr expr;

	public ExprFactor(IAbsSyn.IExpr expr) {
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
	public LRVal getLRValue() {
		return LRVal.RVAL;
	}

    @Override
    public Type getType() {
        return expr.getType();
    }

    @Override
    public Types getTypeValue() {
        return expr.getType().getValue();
    }

	@Override
	public void doTypeChecking() throws TypeCheckError {
		expr.doTypeChecking();
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		expr.doInitChecking(globalProtected);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		expr.addIInstrToCodeArray(localLocations, simulateOnly);
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
		s += argumentIndent + "<expr>:\n";
		s += expr.toString(subIndent);
		
		return s;
	}	
}
