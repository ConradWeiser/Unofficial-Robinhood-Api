package robinhood.api;

import java.util.logging.Logger;

import com.mashape.unirest.http.exceptions.UnirestException;

import robinhood.api.endpoint.account.data.AccountArrayWrapper;
import robinhood.api.endpoint.account.data.AccountElement;
import robinhood.api.endpoint.account.data.AccountHolderAffiliationElement;
import robinhood.api.endpoint.account.data.BasicAccountHolderInfoElement;
import robinhood.api.endpoint.account.data.BasicUserInfoElement;
import robinhood.api.endpoint.account.methods.GetAccountHolderAffiliationInfo;
import robinhood.api.endpoint.account.methods.GetAccounts;
import robinhood.api.endpoint.account.methods.GetBasicAccountHolderInfo;
import robinhood.api.endpoint.account.methods.GetBasicUserInfo;
import robinhood.api.endpoint.authorize.data.Token;
import robinhood.api.endpoint.authorize.methods.AuthorizeWithoutMultifactor;
import robinhood.api.endpoint.authorize.methods.LogoutFromRobinhood;
import robinhood.api.request.RequestManager;
import robinhood.api.request.RequestStatus;
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
		
		//Log the user in and store the auth token
		this.logUserIn(username, password);
		
	}
	
	/**
	 * Method which logs a user in given a username and password.
	 * this method automatically stores the authorization token in with the instance,
	 * allowing any method which requires the token to have immediate access to it.
	 * 
	 * This method is ran if you created the RobinhoodApi class using the constructor with 
	 * both a username and password, but is available if you wish to get the authorization token again.
	 * Usually ran after the user is logged out to refresh the otken
	 */
	public RequestStatus logUserIn(String username, String password) {
		
			
			//TODO: Implement multifactor authorization
			ApiMethod method = new AuthorizeWithoutMultifactor(username, password);
			
			try {
				
				Token token = requestManager.makeApiRequest(method);
				
				//Save the token into the configuration manager to be used with other methods
				configManager.setAuthToken(token.getToken());
				
				return RequestStatus.SUCCESS;
				
				
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return RequestStatus.FAILURE;
	}
	
	/**
	 * Method which forces the authorization token to expire, logging the user out if the user is
	 * currently logged in.
	 * You should never see a "FAILURE" response from this. If so, file a bug report on github
	 * @return an enum containing either "SUCCESS", "FAILURE" or "NOT_LOGGED_IN"
	 */
	public RequestStatus logUserOut() {
		
		try {
					
			//Create the APIMethod which attempts to log the user out, and run it
			ApiMethod method = new LogoutFromRobinhood();
			method.addAuthTokenParameter();
			requestManager.makeApiRequest(method);
						
			//If we made it to this point without throwing something, it worked!
			return RequestStatus.SUCCESS;
			
		} catch (TokenNotFoundException ex) {
			
			//If there was no token in the configManager, the user was never logged in
			return RequestStatus.NOT_LOGGED_IN;
		} catch (UnirestException e) {
			
			//API error.
			return RequestStatus.FAILURE;
		}
		
	}
	
	/**
	 * Method returning a {@link AccountElement} using the currently logged in user
	 * @throws TokenNotFoundException if the user is not logged in
	 */
	public AccountElement getAccountData() throws TokenNotFoundException {
		
		try {
			
			//Create the API method for this request
			ApiMethod method = new GetAccounts();
			method.addAuthTokenParameter();
			
			//TODO: This is a temporary fix, as the Robinhood API seems to have some features planned, without being implemented fully
			AccountArrayWrapper data = requestManager.makeApiRequest(method);
			return data.getResults();
		} catch(UnirestException ex) {
			
			//API error
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method returning a {@link BasicUserInfoElement} for the currently logged in user
	 * @throws TokenNotFoundException if the user is not logged in
	 */
	public BasicUserInfoElement getBasicUserInfo() throws TokenNotFoundException {
		
		try {
			
			//Create the API method for the request
			ApiMethod method = new GetBasicUserInfo();
			method.addAuthTokenParameter();
			
			return requestManager.makeApiRequest(method);
			
		} catch (UnirestException ex) {
			
			//API error
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method returning a {@link BasicAccountHolderInfoElement} for the currently logged in user
	 * @throws TokenNotFoundException if the user is not logged in
	 */
	public BasicAccountHolderInfoElement getAccountHolderInfo() throws TokenNotFoundException {
		
		try {
			
			//Create the API method
			ApiMethod method = new GetBasicAccountHolderInfo();
			method.addAuthTokenParameter();
			
			return requestManager.makeApiRequest(method);
			
		} catch (UnirestException ex) {
			
			//API error
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method returning a {@link AccountHolderAffiliationElement} for the currently loggedin user
	 * @throws TokenNotFoundException if the user is not logged in
	 */
	public AccountHolderAffiliationElement getAccountHolderAffiliation() throws TokenNotFoundException {
		
		try {
			
			//Create the API method
			ApiMethod method = new GetAccountHolderAffiliationInfo();
			method.addAuthTokenParameter();
			
			return requestManager.makeApiRequest(method);
			
		} catch (UnirestException ex) {
			
			//APi error
			ex.printStackTrace();
		}
		return null;
	}
	

	
	

}
