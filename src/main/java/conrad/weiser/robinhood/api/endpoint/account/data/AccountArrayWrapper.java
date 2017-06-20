package conrad.weiser.robinhood.api.endpoint.account.data;

/**
 * This element should not exist for very long hopefully as Robinhood updates their API
 * Currently the getAccounts command returns the data inside of an array after a "next" and "previous"
 * field which are never filled out. This class should never be accessed directly, as it merely is filtered
 * out pulling the only account from the results.
 * Gson needs this in order to work, though.
 *
 */
public class AccountArrayWrapper {
	
	private String next;
	private String previous;
	
	private AccountElement[] results;

	public String getNext() {
		return next;
	}



	public String getPrevious() {
		return previous;
	}


	/**
	 * Currently, there will NEVER be more than just one of these. To make it easy for the user to use the methods requiring
	 * this, we only return the existing data.
	 * @return
	 */
	public AccountElement getResults() {
		return results[0];
	}



}
