package conrad.weiser.robinhood.api.endpoint.authorize.data;

/**
 * Class declaring the return structure so Gson can handle parsing the JSON
 *
 */
public class Token {
	
	private String access_token = null;
	
	public String getToken() {
		
		return this.access_token;
	}
	
	@Override
	public String toString() {
		return getToken();
	}

}
