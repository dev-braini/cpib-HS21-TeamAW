package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Literal;
import ch.fhnw.cpib.Enums.LRVal;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.HashMap;
import java.util.stream.Collectors;

public class LiteralFactor extends AbsSynTreeNode implements IAbsSyn.IFactor {
	private Literal literal;

	public LiteralFactor(Literal literal) {
		this.literal = literal;
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
	public LRVal getLRValue() {
		return LRVal.RVAL;
	}

    @Override
    public Type getType() {
        return literal.getType();
    }

    @Override
    public Types getTypeValue() {
        return getType().getValue();
    }

	@Override
	public void doTypeChecking() throws TypeCheckError {
		// Do nothing
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		// Do nothing		
	}

	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		if(!simulateOnly) {
			if(literal.getTypeValue() == Types.BOOL) {
				codeArray.put(codeArrayPointer, new IInstructions.LoadImBool(literal.getBool()));
			} else if (literal.getTypeValue() == Types.INT64) {
				codeArray.put(codeArrayPointer, new IInstructions.LoadImInt(literal.getInteger()));
			} else {
				throw new RuntimeException("Unknown type found");
			}
		}
		codeArrayPointer++;
	}

	@Override
	public String toString(String indent) {
		String argumentIndent = indent + " ";
		String s = indent + this.getClass().getName() + "\n";
		if(localStoresNamespace != null) {
            s += argumentIndent + "[localStoresNamespace]: " + localStoresNamespace.keySet().stream().map(Object::toString).collect(Collectors.joining(",")) + "\n";
        }		
		s += argumentIndent + "<literal>: " + literal.toString() + "\n";
		
		return s;
	}	
}
