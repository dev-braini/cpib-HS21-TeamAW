package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.ChangeModes;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Enums.MechModes;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.ChangeMode;
import ch.fhnw.cpib.Token.MechMode;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Param extends AbsSynTreeNode {
	private MechMode   mechMode;
	private ChangeMode changeMode;
	private TypedIdent typedIdent;
	private LRVal      lrVal;
	
	public Param(MechMode mechMode, ChangeMode changeMode, TypedIdent typedIdent) {
		this.mechMode = mechMode != null ? mechMode : new MechMode(MechModes.COPY);
		this.changeMode = changeMode != null ? changeMode : new ChangeMode(ChangeModes.CONST);
		this.typedIdent = typedIdent;
		this.typedIdent.setInit();
		// Set the const boolean value on the typedIdent to true

        if(changeMode != null && changeMode.getValue() == ChangeModes.CONST)
			this.typedIdent.setConst();		
		lrVal = this.mechMode.getValue() == MechModes.COPY ? LRVal.RVAL : LRVal.LVAL;
	}	
	
	public String getIdentString() {
		return typedIdent.getValue();
	}
	
	public TypedIdent getTypedIdent() {
		return typedIdent;
	}
	
	public LRVal getLRValue() {
		return lrVal;
	}
	
	public MechMode getMechMode() {
		return mechMode;
	}

	@Override
	public void saveNamespaceInfoToNode(HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
	}
	
	@Override
	public void doTypeChecking() throws TypeCheckError {
		// Do nothing
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError,
			AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		// Do nothing
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.AllocBlock(1));
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
        		
		if(mechMode != null)
			s += argumentIndent + "<mechMode>: " + mechMode.toString() + "\n";
		if(changeMode != null)
			s += argumentIndent + "<changeMode>: " + changeMode.toString() + "\n";
		s += argumentIndent + "<typedIdent>:\n";
		s += typedIdent.toString(subIndent);
		
		return s;
	}	
}
