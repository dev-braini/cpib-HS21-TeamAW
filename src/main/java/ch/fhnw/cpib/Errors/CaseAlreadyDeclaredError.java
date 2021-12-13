package ch.fhnw.cpib.Errors;

public class CaseAlreadyDeclaredError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public CaseAlreadyDeclaredError () {
		
	}
	
	public CaseAlreadyDeclaredError(String errorMessage) {
		super(setupMessage(errorMessage));
	}
	
	public CaseAlreadyDeclaredError(String message, Throwable cause) {
		super(setupMessage(message), cause);
	}

	public CaseAlreadyDeclaredError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(String string) {
		return "Case with literal [" + string + "] already exists.";
	}
}