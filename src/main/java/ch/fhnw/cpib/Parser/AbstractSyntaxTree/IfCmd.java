package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.Helper.CopyHelper;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class IfCmd extends AbsSynTreeNode implements IAbsSyn.ICmd {
	private IAbsSyn.IExpr expr;
	private CpsCmd ifCpsCmd;
	private CpsCmd elseCpsCmd;
	
	public IfCmd(IAbsSyn.IExpr expr, CpsCmd ifCpsCmd, CpsCmd elseCpsCmd) {
		this.expr = expr;
		this.ifCpsCmd = ifCpsCmd;
		this.elseCpsCmd = elseCpsCmd;
	}
	
	@Override
	public void saveNamespaceInfoToNode(
			HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		expr.saveNamespaceInfoToNode(this.localStoresNamespace);
		ifCpsCmd.saveNamespaceInfoToNode(CopyHelper.deepCopy(this.localStoresNamespace));
		elseCpsCmd.saveNamespaceInfoToNode(CopyHelper.deepCopy(this.localStoresNamespace));
	}

	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		expr.doScopeChecking();
		ifCpsCmd.doScopeChecking();
		elseCpsCmd.doScopeChecking();
	}
	
	@Override
	public void doTypeChecking() throws TypeCheckError {
		expr.doTypeChecking();
		ifCpsCmd.doTypeChecking();
		elseCpsCmd.doTypeChecking();
		
		if(expr.getTypeValue() != Types.BOOL)
			throw new TypeCheckError(new Type(Types.BOOL), expr.getType());
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		expr.doInitChecking(globalProtected);
		// set recursively all initialized variables also on the child-nodes to init
		for(TypedIdent ident : localStoresNamespace.values()) {
			if(ident.getInit()) {
				ifCpsCmd.setInit(ident);
				elseCpsCmd.setInit(ident);
			}
		}
		// Do the init checking
		// Global variables cannot be initialized from now on
		ifCpsCmd.doInitChecking(true);	
		elseCpsCmd.doInitChecking(true);
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		// get the size of ifCpsCmd by simulating the add action
		int codeArrayPointerBefore = codeArrayPointer;
		
		ifCpsCmd.addIInstrToCodeArray(localLocations, true);
		int ifCpsCmdSize = codeArrayPointer - codeArrayPointerBefore + 1; // + 1 for unconditional jump after exprFalse

		// reset pointer
		codeArrayPointer = codeArrayPointerBefore;
		
		// get the size of elseCpsCmd
		elseCpsCmd.addIInstrToCodeArray(localLocations, true);
		int elseCpsCmdSize = codeArrayPointer - codeArrayPointerBefore;
		
		// reset pointer
		codeArrayPointer = codeArrayPointerBefore;
		
		// now really add the staff
		// add the boolean for the conditional check onto the stack
		expr.addIInstrToCodeArray(localLocations, simulateOnly);
		// now add the jump condition to see if we had to continue (true part) or to jump (false part)
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.CondJump(codeArrayPointer + 1 + ifCpsCmdSize));
		codeArrayPointer++;
		// now add the true part
		ifCpsCmd.addIInstrToCodeArray(localLocations, simulateOnly);
		// now add the unconditional jump to jump after the false part (we already processed the true part ...)
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.UncondJump(codeArrayPointer + 1 + elseCpsCmdSize));
		codeArrayPointer++;
		// now add the false part		
		elseCpsCmd.addIInstrToCodeArray(localLocations, simulateOnly);
		
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
		s += argumentIndent + "<ifCpsCmd>:\n";
		s += ifCpsCmd.toString(subIndent);
		s += argumentIndent + "<elseCpsCmd>:\n";
		s += elseCpsCmd.toString(subIndent);
		
		return s;
	}	
}
