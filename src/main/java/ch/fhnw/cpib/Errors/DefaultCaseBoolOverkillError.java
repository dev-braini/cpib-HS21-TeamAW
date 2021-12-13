package ch.fhnw.cpib.Errors;

public class DefaultCaseBoolOverkillError extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DefaultCaseBoolOverkillError () {
		super(setupMessage(""));
	}
	
	public DefaultCaseBoolOverkillError(String errorMessage) {
		super(setupMessage(errorMessage));
	}
	
	public DefaultCaseBoolOverkillError(String message, Throwable cause) {
		super(setupMessage(message), cause);
	}

	public DefaultCaseBoolOverkillError(Throwable cause) {
		super(cause);
	} 
	
	private static String setupMessage(String string) {
		return "DefaultCase in boolean SwitchCmd with more then 2 cases makes no sense.!";
	}
}