package ch.fhnw.cpib.Errors;

public class NameAlreadyGloballyDeclaredError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NameAlreadyGloballyDeclaredError () {
		
	}
	
	public NameAlreadyGloballyDeclaredError(String errorMessage) {
		super(setupMessage(errorMessage));
	}
	
	public NameAlreadyGloballyDeclaredError(String message, Throwable cause) {
		super(setupMessage(message), cause);
	}

	public NameAlreadyGloballyDeclaredError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(String string) {
		return "Name already globally declared [" + string + "]";
	}
}