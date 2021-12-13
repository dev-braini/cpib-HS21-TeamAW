package ch.fhnw.cpib.Errors;

import ch.fhnw.cpib.Token.Ident;

public class NotInitializedError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NotInitializedError () {
		
	}
	
	public NotInitializedError(String errorMessage) {
		super(errorMessage);
	}
	
	public NotInitializedError(Ident ident) {
		super(setupMessage(ident));
	}
	
	public NotInitializedError(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotInitializedError(Ident ident, Throwable cause) {
		super(setupMessage(ident), cause);
	}

	public NotInitializedError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(Ident ident) {
		return "Identifier [" + ident.getValue() + "] is not initialised.";
	}
}
