package robinhood.api;

import java.io.IOException;
import java.util.logging.Logger;

import robinhood.api.endpoint.authorize.data.Token;
import robinhood.api.endpoint.authorize.methods.AuthorizeWithoutMultifactor;
import robinhood.api.request.RequestManager;

public class RobinhoodApi {
	
	/**
	 * The Logger object used for the custom error handling
	 */
	public static final Logger log = Logger.getLogger(RobinhoodApi.class.getName());
	
	/**
	 * The instance used to make the requests
	 */
	private static RequestManager requestManager = null;
	
	
	/**
	 * Constructor which creates all of the access points to use the API.
	 * This constructor does not require the Username and Password, thus giving limited
	 * access to the API. See Robinhood Unofficial Documentation at following link
	 * to see what can and cannot be used if you do not authorize a user
	 */
	public RobinhoodApi() {
		
		//Do nothing. Allow users to access the unauthorized sections of the API
		RobinhoodApi.requestManager = RequestManager.getInstance();
	}
	
	/**
	 * Constructor which creates all of the access points to use the API.
	 * This constructor requires both a Username and Password and attempts to authorize
	 * the user. On success, the Authorization Token will be stored in the 
	 * ConfigurationManager instance to be retrieved elsewhere.
	 * On failure, an error will be thrown.
	 */
	public RobinhoodApi(String username, String password) {
		
		//TODO: Implement multifactor authorization
		ApiMethod method = new AuthorizeWithoutMultifactor(username, password);
		try {
			Token token = RequestManager.getInstance().makeApiRequest(method);
			System.out.println(token.getToken());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	

}
