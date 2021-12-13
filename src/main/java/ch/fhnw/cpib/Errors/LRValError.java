package ch.fhnw.cpib.Errors;

import ch.fhnw.cpib.Enums.LRVal;

public class LRValError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public LRValError () {
		
	}
	
	public LRValError(String errorMessage) {
		super(errorMessage);
	}
	
	public LRValError(LRVal expected, LRVal found) {
		super(setupMessage(expected, found));
	}
	
	public LRValError(String message, Throwable cause) {
		super(message, cause);
	}
	
	public LRValError(LRVal expected, LRVal found, Throwable cause) {
		super(setupMessage(expected, found), cause);
	}

	public LRValError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(LRVal expected, LRVal found) {
		return "Expected [" + expected.name() + "] but found [" + found.name() + "]";
	}
}
