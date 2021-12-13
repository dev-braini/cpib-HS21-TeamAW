package ch.fhnw.cpib.Errors;

public class InvalidParamCountError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InvalidParamCountError () {
		
	}
	
	public InvalidParamCountError(String errorMessage) {
		super(errorMessage);
	}
	
	public InvalidParamCountError(int expected, int found) {
		super(setupMessage(expected, found));
	}
	
	public InvalidParamCountError(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidParamCountError(int expected, int found, Throwable cause) {
		super(setupMessage(expected, found), cause);
	}

	public InvalidParamCountError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(int expected, int found) {
		return "Expected [" + expected + "] params but found [" + found + "] params";
	}
}
