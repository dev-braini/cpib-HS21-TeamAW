package ch.fhnw.cpib.Errors;

import ch.fhnw.cpib.Token.Ident;

public class GlobalInitializationProhibitedError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public GlobalInitializationProhibitedError () {
		
	}
	
	public GlobalInitializationProhibitedError(String errorMessage) {
		super(errorMessage);
	}
	
	public GlobalInitializationProhibitedError(Ident ident) {
		super(setupMessage(ident));
	}
	
	public GlobalInitializationProhibitedError(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GlobalInitializationProhibitedError(Ident ident, Throwable cause) {
		super(setupMessage(ident), cause);
	}

	public GlobalInitializationProhibitedError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(Ident ident) {
		return "Global Identifier [" + ident.getValue() + "] cannot be initialized in protected scope.";
	}
}
