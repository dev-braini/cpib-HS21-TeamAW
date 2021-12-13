package ch.fhnw.cpib.Errors;

import ch.fhnw.cpib.Token.Type;

public class TypeCheckError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public TypeCheckError () {
		
	}
	
	public TypeCheckError(String errorMessage) {
		super(errorMessage);
	}
	
	public TypeCheckError(Type expected, Type found) {
		super(setupMessage(expected, found));
	}
	
	public TypeCheckError(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TypeCheckError(Type expected, Type found, Throwable cause) {
		super(setupMessage(expected, found), cause);
	}

	public TypeCheckError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(Type expected, Type found) {
		return "Expected [" + expected.getValue() + "] but found [" + found.getValue() + "]";
	}
}
