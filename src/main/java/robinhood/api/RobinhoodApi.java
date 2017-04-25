package robinhood.api;

import java.util.logging.Logger;

import com.mashape.unirest.http.exceptions.UnirestException;

import robinhood.api.endpoint.authorize.data.Token;
import robinhood.api.endpoint.authorize.methods.AuthorizeWithoutMultifactor;
import robinhood.api.endpoint.authorize.methods.LogoutFromRobinhood;
import robinhood.api.request.LogoutStatus;
import robinhood.api.request.RequestManager;
import robinhood.api.throwables.TokenNotFoundException;

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
	 * The active instance of the Configuration Manager. The Auth-token is stored in this instance.
	 */
	private static ConfigurationManager configManager = null;
	
	
	/**
	 * Constructor which creates all of the access points to use the API.
	 * This constructor does not require the Username and Password, thus giving limited
	 * access to the API. See Robinhood Unofficial Documentation at following link
	 * to see what can and cannot be used if you do not authorize a user
	 */
	public RobinhoodApi() {
		
		//Do nothing. Allow users to access the unauthorized sections of the API
		RobinhoodApi.requestManager = RequestManager.getInstance();
		RobinhoodApi.configManager = ConfigurationManager.getInstance();
	}
	
	/**
	 * Constructor which creates all of the access points to use the API.
	 * This constructor requires both a Username and Password and attempts to authorize
	 * the user. On success, the Authorization Token will be stored in the 
	 * ConfigurationManager instance to be retrieved elsewhere.
	 * On failure, an error will be thrown.
	 */
	public RobinhoodApi(String username, String password) {
		
		//Construct the managers
		RobinhoodApi.requestManager = RequestManager.getInstance();
		RobinhoodApi.configManager = ConfigurationManager.getInstance();
		
		//TODO: Implement multifactor authorization
		ApiMethod method = new AuthorizeWithoutMultifactor(username, password);
		
		try {
			
			Token token = requestManager.makeApiRequest(method);
			
			//Save the token into the configuration manager to be used with other methods
			configManager.setAuthToken(token.getToken());
			
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method which forces the authorization token to expire, logging the user out if the user is
	 * currently logged in.
	 * You should never see a "FAILURE" response from this. If so, file a bug report on github
	 * @return an enum containing either "SUCCESS", "FAILURE" or "NOT_LOGGED_IN"
	 */
	public LogoutStatus logUserOut() {
		
		try {
					
			//Create the APIMethod which attempts to log the user out, and run it
			ApiMethod method = new LogoutFromRobinhood();
			method.addAuthTokenParameter();
			requestManager.makeApiRequest(method);
						
			//If we made it to this point without throwing something, it worked!
			return LogoutStatus.SUCCESS;
			
		} catch (TokenNotFoundException ex) {
			
			//If there was no token in the configManager, the user was never logged in
			return LogoutStatus.NOT_LOGGED_IN;
		} catch (UnirestException e) {
			
			//API error.
			return LogoutStatus.FAILURE;
		}
		
	}
	

	
	

}
