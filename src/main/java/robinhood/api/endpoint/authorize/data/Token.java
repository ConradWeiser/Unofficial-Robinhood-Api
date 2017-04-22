package robinhood.api.endpoint.authorize.data;

/**
 * Class declaring the return structure so Gson can handle parsing the JSON
 *
 */
public class Token {
	
	private String token = null;
	
	public String getToken() {
		
		return this.token;
	}
	
	@Override
	public String toString() {
		return getToken();
	}

}
