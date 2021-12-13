package ch.fhnw.cpib.Errors;

public class NameAlreadyDeclaredError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NameAlreadyDeclaredError () {
		
	}
	
	public NameAlreadyDeclaredError(String errorMessage) {
		super(setupMessage(errorMessage));
	}
	
	public NameAlreadyDeclaredError(String message, Throwable cause) {
		super(setupMessage(message), cause);
	}

	public NameAlreadyDeclaredError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(String string) {
		return "Name already exists [" + string + "]";
	}
}