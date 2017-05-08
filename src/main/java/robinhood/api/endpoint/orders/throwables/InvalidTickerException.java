package robinhood.api.endpoint.orders.throwables;

@SuppressWarnings("serial")
public class InvalidTickerException extends Exception {
	
	@Override
	public String getMessage() {
		
		return "Supplied ticker does not exist";
	}

}
