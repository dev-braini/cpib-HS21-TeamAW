package ch.fhnw.cpib.Parser.AbstractSyntaxTree;

import ch.fhnw.cpib.Enums.Types;
import ch.fhnw.cpib.Errors.*;
import ch.fhnw.cpib.Token.Type;
import ch.fhnw.cpib.Helper.CopyHelper;
import ch.fhnw.cpib.VM.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.VM.IInstructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SwitchCmd extends AbsSynTreeNode implements IAbsSyn.ICmd {
	private IAbsSyn.IExpr expr;
	private ArrayList<Case> cases;
	private DefaultCase defaultCase;
	
	public SwitchCmd(IAbsSyn.IExpr expr, ArrayList<Case> cases, DefaultCase defaultCase) {
		this.expr = expr;
		this.cases = cases;
		this.defaultCase = defaultCase;
	}
	
	@Override
	public void saveNamespaceInfoToNode(HashMap<String, TypedIdent> localStoresNamespace)
			throws NameAlreadyDeclaredError, NameAlreadyGloballyDeclaredError, AlreadyInitializedError {
		this.localStoresNamespace = localStoresNamespace;
		expr.saveNamespaceInfoToNode(this.localStoresNamespace);	
		
		// add deepCopy from local storage to every case
		for(Case c : cases)
			c.saveNamespaceInfoToNode(CopyHelper.deepCopy(this.localStoresNamespace));
		
		// add deepCopy from local storage to the default case
		defaultCase.saveNamespaceInfoToNode(CopyHelper.deepCopy(this.localStoresNamespace));
	}
	
	@Override
	public void doScopeChecking() throws NameNotDeclaredError, LRValError, InvalidParamCountError, CaseAlreadyDeclaredError, DefaultCaseBoolOverkillError {
		expr.doScopeChecking();
		// Check if we have duplicate cases
		for(Case c1 : cases) {
			for(Case c2 : cases) {
				if(c1 != c2) {
					if(c1.getLiteral().getTypeValue() == Types.INT64) {
						if(c1.getLiteral().getInteger() == c2.getLiteral().getInteger())
							throw new CaseAlreadyDeclaredError("" + c1.getLiteral().getInteger());
					} else if (c1.getLiteral().getTypeValue() == Types.BOOL) {
						if(c1.getLiteral().getBool() == c2.getLiteral().getBool())
							throw new CaseAlreadyDeclaredError("" + c1.getLiteral().getBool());
					}
				}
			}
		}
		
		// Check if we have an unnecessary default case
		if(cases.size() == 2 && cases.get(0).getLiteral().getTypeValue() == Types.BOOL && defaultCase != null)
			throw new DefaultCaseBoolOverkillError();			
		
		// do scope check on every case
		for(Case c : cases)
			c.doScopeChecking();
			
		if(defaultCase != null)
			defaultCase.doScopeChecking();
	}
	
	@Override
	public void doTypeChecking() throws TypeCheckError {
		expr.doTypeChecking();
		// do type check on every case
		for(Case c : cases)
			c.doTypeChecking();
			
		if(defaultCase != null)
			defaultCase.doTypeChecking();
		
		// check for each case, if the type of the literal is the same as the type of the expression
		for(Case c : cases) {
			if(expr.getType() != c.getType())
				throw new TypeCheckError(expr.getType(), c.getType());
		}
	}

	@Override
	public void doInitChecking(boolean globalProtected) throws NotInitializedError, AlreadyInitializedError, GlobalInitializationProhibitedError, CannotAssignToConstError {
		expr.doInitChecking(globalProtected);
		// set recursively all initialized variables also on the child-nodes to init
		for(TypedIdent ident : localStoresNamespace.values()) {
			if(ident.getInit()) {
				for(Case c : cases)
					c.setInit(ident);
				defaultCase.setInit(ident);
			}
		}		
		
		// Do init check on every case		
		// Global variables cannot be initialized from now on	
		for(Case c : cases)
			c.doInitChecking(true);
		// Do the init checking
		// Global variables cannot be initialized from now on			
		if(defaultCase != null)
			defaultCase.doInitChecking(true);
	}

//	@Override
//	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
//			throws CodeTooSmallError {
//		// save the pointer before simulating the add actions for getting the size
//		int codeArrayPointerBefore = codeArrayPointer;
//		
//		// get the size of the expression
//		expr.addIInstrToCodeArray(localLocations, true);
//		int exprSize = codeArrayPointer - codeArrayPointerBefore;
//		
//		// reset pointer
//		codeArrayPointer = codeArrayPointerBefore;
//		
//		int[] caseSizes = new int[cases.size()];
//		int totalSize = 0;
//		for (int i = 0; i < cases.size(); i++) {
//			codeArrayPointer = codeArrayPointerBefore;
//			cases.get(i).addIInstrToCodeArray(localLocations, true);
//			int caseSize = codeArrayPointer - codeArrayPointerBefore;
//			caseSizes[i] = caseSize;
//			totalSize += exprSize + 4 + caseSize; 
//		}
//		
//		// reset pointer
//		codeArrayPointer = codeArrayPointerBefore;
//		
//		// get the size of defaultCase
//		defaultCase.addIInstrToCodeArray(localLocations, true);
//		int defaultCaseSize = codeArrayPointer - codeArrayPointerBefore;
//		
//		// reset pointer
//		codeArrayPointer = codeArrayPointerBefore;
//		
//		totalSize += defaultCase != null ? defaultCaseSize : 0;
//		
//		int endPosition = codeArrayPointer + totalSize;
//		for (int i = 0; i < cases.size(); i++) {
//			// add the expression / condition on top of the stack
//			expr.addIInstrToCodeArray(localLocations, simulateOnly);
//			// add the value (literal) of this case on top of the stack
//			if(!simulateOnly)
//				codeArray.put(codeArrayPointer, new IInstructions.LoadImInt(cases.get(i).getLiteral().getInteger()));
//			codeArrayPointer++;
//			// compare the two values and save it as boolean
//			if(!simulateOnly)
//				codeArray.put(codeArrayPointer, new IInstructions.EqInt());
//			codeArrayPointer++;
//			// add the conditional jump for this case
//			if(!simulateOnly)
//				codeArray.put(codeArrayPointer, new IInstructions.CondJump(codeArrayPointer + caseSizes[i] + 2));
//			codeArrayPointer++;
//			// execute the case commands
//			cases.get(i).addIInstrToCodeArray(localLocations, simulateOnly);
//			// add the unconditional jump to jump after the whole switch case construct
//			if(!simulateOnly)
//				codeArray.put(codeArrayPointer, new IInstructions.UncondJump(endPosition));
//			codeArrayPointer++;
//		}
//		
//		// Execute the default case if there is any
//		if(defaultCase != null)
//			defaultCase.addIInstrToCodeArray(localLocations, simulateOnly);
//		
//		
//	}

	// Verbesserte Implementierung mit Jump-Tabelle
	@Override
	public void addIInstrToCodeArray(HashMap<String, Integer> localLocations, boolean simulateOnly)
			throws CodeTooSmallError {
		int minCaseLiteral = 0;
		int maxCaseLiteral = 0;
		if(cases.size() > 0 && cases.get(0).getLiteral().getTypeValue() == Types.INT64) {
			// find the case with the smallest and largest case literal
			minCaseLiteral = cases.get(0).getLiteral().getInteger();
			maxCaseLiteral = cases.get(0).getLiteral().getInteger();
			for(int i = 1; i < cases.size(); i++) {
				int caseLiteral = cases.get(i).getLiteral().getInteger(); 
				if(caseLiteral < minCaseLiteral)
					minCaseLiteral = caseLiteral;
				if(caseLiteral > maxCaseLiteral)
					maxCaseLiteral = caseLiteral;
			}
		// Type is boolean
		} else if (cases.size() > 0 && cases.get(0).getLiteral().getTypeValue() == Types.BOOL) {
			minCaseLiteral = cases.get(0).getLiteral().getBool() ? 1 : 0;
			maxCaseLiteral = cases.get(0).getLiteral().getBool() ? 1 : 0;
			int caseLiteral = cases.size() > 1 ? (cases.get(1).getLiteral().getBool() ? 1 : 0) : 0; 
			if(caseLiteral < minCaseLiteral)
				minCaseLiteral = caseLiteral;
			if(caseLiteral > maxCaseLiteral)
				maxCaseLiteral = caseLiteral;
		}
		
		int caseLiteralOffset = minCaseLiteral;
		
		int jumpTableSize = maxCaseLiteral - minCaseLiteral + 1; // + 1 because e.g. a switch case with three cases 0, 1, 2
		
		// save the pointer before simulating the add actions for getting the size
		int codeArrayPointerBefore = codeArrayPointer;
		
		// get the size of the expression
		expr.addIInstrToCodeArray(localLocations, true);
		int exprSize = codeArrayPointer - codeArrayPointerBefore;
		
		// reset pointer
		codeArrayPointer = codeArrayPointerBefore;
		
		int[] caseSizes = new int[cases.size()];
		int[] caseRelPositions = new int[cases.size()];
		int totalSize = 0;
		for (int i = 0; i < cases.size(); i++) {
			codeArrayPointer = codeArrayPointerBefore;
			cases.get(i).addIInstrToCodeArray(localLocations, true);
			int caseSize = codeArrayPointer - codeArrayPointerBefore + 1; // + 1 because of unconditional jump at the end
			caseSizes[i] = caseSize;
			caseRelPositions[i] = totalSize;
			totalSize += caseSize; //TODO
		}
		
		// reset pointer
		codeArrayPointer = codeArrayPointerBefore;
		
		// get the size of defaultCase
		if(defaultCase != null)
			defaultCase.addIInstrToCodeArray(localLocations, true);
		int defaultCaseSize = codeArrayPointer - codeArrayPointerBefore;
		
		// reset pointer
		codeArrayPointer = codeArrayPointerBefore;
		
		// size of case block without default case
		int caseBlockSize = totalSize;
		
		// size of whole case block
		totalSize += defaultCase != null ? defaultCaseSize : 0;
		
		// calculate the position of the first case
		// current pointer + exprSize + 5 (check lower) + 5 (check higher) + 2 (correct value with offset) + 1 (RelJump) + jumpTableSize
		int firstCasePosition = codeArrayPointer + exprSize + 5 + 5 + 2 + 1 + jumpTableSize;
		
		// calculate the defaultCase position:
		// firstCasePosition + caseBlockSize
		int defaultCasePosition = firstCasePosition + caseBlockSize;
		
		// calculate the end position:
		// defaultCase position + defaultCase size
		int endPosition = defaultCasePosition + defaultCaseSize;
		
		// Create an array for the addresses of the jump table
		int[] jumpTableAddresses = new int[jumpTableSize];
		// In a first pass, set all addresses to defaultCasePosition
		for(int i = 0; i < jumpTableAddresses.length; i++) {
			jumpTableAddresses[i] = defaultCasePosition;
		}
		// In a second pass, add the correct addresses for all existing cases
		for(int i = 0; i < cases.size(); i++) {
			if(cases.get(i).getLiteral().getTypeValue() == Types.INT64) {
				jumpTableAddresses[cases.get(i).getLiteral().getInteger() - caseLiteralOffset] = firstCasePosition + caseRelPositions[i];
			} else if (cases.get(i).getLiteral().getTypeValue() == Types.BOOL) {
				if(cases.get(i).getLiteral().getBool() == true) {
					jumpTableAddresses[1 - caseLiteralOffset] = firstCasePosition + caseRelPositions[i];	
				} else {
					jumpTableAddresses[0 - caseLiteralOffset] = firstCasePosition + caseRelPositions[i];
				}
					
			}
		}
		
		// ######## NOW ADD THE CRAP #########
		
		// add the expression / condition on top of the stack
		expr.addIInstrToCodeArray(localLocations, simulateOnly);
		
		// now check if the value of the expression is smaller than minCaseLiteral, if yes, jump to the default case
		// duplicate the value (so the original calculated value is not beeing modified)
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.Dup());
		codeArrayPointer++;
		// load the minCaseLiteral onto the stack
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.LoadImInt(minCaseLiteral));
		codeArrayPointer++;
		// add the command to check if it is lt
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.LtInt());
		codeArrayPointer++;
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.NegBool());
		codeArrayPointer++;		
		// jump to default case, if previous command put "false" onto the stack
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.CondJump(defaultCasePosition));
		codeArrayPointer++;

		
		// now check if the value of the expression is larger than maxCaseLiteral, if yes, jump to the default case
		// duplicate the value (so the original calculated value is not beeing modified)
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.Dup());
		codeArrayPointer++;
		// load the maxCaseLiteral onto the stack
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.LoadImInt(maxCaseLiteral));
		codeArrayPointer++;
		// add the command to check if it is gt
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.GtInt());
		codeArrayPointer++;
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.NegBool());
		codeArrayPointer++;				
		// jump to default case, if previous command put "false" onto the stack
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.CondJump(defaultCasePosition));
		codeArrayPointer++;
		
		// correct the value so we get a 0-based index
		// note: no need to Dup, because we can use the value now directly for further processing
		// load the offset onto the stack
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.LoadImInt(caseLiteralOffset));
		codeArrayPointer++;		
		// add the command to check if it is gt
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.SubInt());
		codeArrayPointer++;
		
		// add the relative jump command to jump to the index in the jumpTable based on the value on top of the stack
		// note: jumpTable will follow directly afterwards, so just +1 of the current pointer
		// load the offset onto the stack
		if(!simulateOnly)
			codeArray.put(codeArrayPointer, new IInstructions.RelJump(codeArrayPointer + 1));
		codeArrayPointer++;	
		
		// now add the jump table with the unconditional jumps to the correct addresses (either position default case or position of case)
		for(int i = 0; i < jumpTableSize; i++) {
			// load the offset onto the stack			
			if(!simulateOnly)
				codeArray.put(codeArrayPointer, new IInstructions.UncondJump(jumpTableAddresses[i]));
			codeArrayPointer++;				
		}
		
		// now add the commands for the cases
		for (int i = 0; i < cases.size(); i++) {
			// execute the case commands
			cases.get(i).addIInstrToCodeArray(localLocations, simulateOnly);
			// add the unconditional jump to jump after the whole switch case construct
			if(!simulateOnly)
				codeArray.put(codeArrayPointer, new IInstructions.UncondJump(endPosition));
			codeArrayPointer++;
		}

		// Execute the default case if there is any
		if(defaultCase != null)
			defaultCase.addIInstrToCodeArray(localLocations, simulateOnly);
		
		
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
		s += argumentIndent + "<cases>:\n";
		for(Case c : cases) {
			s += c.toString(subIndent);
		}
		s += argumentIndent + "<defaultCase>:";
		if(defaultCase != null)
			s += defaultCase.toString(subIndent);
		
		return s;
	}	
	
}
