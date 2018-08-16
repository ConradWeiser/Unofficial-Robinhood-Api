package conrad.weiser.robinhood.api.throwables;

public class TickerNotExistsException extends Exception {

	private String ticker;

	public TickerNotExistsException(String ticker)
	{
		this.ticker = ticker;
	}

	@Override
	public String getMessage() {

		return "ticker " + ticker + " did not return as a valid target according to the Robinhood Service";
	}
}
