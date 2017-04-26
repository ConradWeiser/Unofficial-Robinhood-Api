package robinhood.api.throwables;

@SuppressWarnings("serial")
public class RobinhoodNotLoggedInException extends Exception {
	
	/**
	 * Override the default method to get more information about
	 * why the error is thrown
	 * 
	 * @return more information about the exception
	 */
	
	@Override public String getMessage() {
		return "There is no Token available. Make sure that the login method is being used first.";
	}

}
