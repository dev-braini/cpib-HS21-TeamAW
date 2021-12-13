package ch.fhnw.cpib.Errors;

import ch.fhnw.cpib.Token.Ident;

public class AlreadyInitializedError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public AlreadyInitializedError () {
		
	}
	
	public AlreadyInitializedError(String errorMessage) {
		super(errorMessage);
	}
	
	public AlreadyInitializedError(Ident ident) {
		super(setupMessage(ident));
	}
	
	public AlreadyInitializedError(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AlreadyInitializedError(Ident ident, Throwable cause) {
		super(setupMessage(ident), cause);
	}

	public AlreadyInitializedError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(Ident ident) {
		return "Identifier [" + ident.getValue() + "] is already initialised.";
	}
}
