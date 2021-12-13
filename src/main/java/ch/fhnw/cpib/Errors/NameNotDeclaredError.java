package ch.fhnw.cpib.Errors;

public class NameNotDeclaredError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NameNotDeclaredError () {
		
	}
	
	public NameNotDeclaredError(String errorMessage) {
		super(setupMessage(errorMessage));
	}
	
	public NameNotDeclaredError(String message, Throwable cause) {
		super(setupMessage(message), cause);
	}

	public NameNotDeclaredError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(String string) {
		return "Name not declared [" + string + "]";
	}
}