package robinhood.api;

import java.util.logging.Logger;

import com.mashape.unirest.http.exceptions.UnirestException;

import robinhood.api.endpoint.account.data.AccountArrayWrapper;
import robinhood.api.endpoint.account.data.AccountElement;
import robinhood.api.endpoint.account.data.AccountHolderAffiliationElement;
import robinhood.api.endpoint.account.data.AccountHolderEmploymentElement;
import robinhood.api.endpoint.account.data.AccountHolderInvestmentElement;
import robinhood.api.endpoint.account.data.BasicAccountHolderInfoElement;
import robinhood.api.endpoint.account.data.BasicUserInfoElement;
import robinhood.api.endpoint.account.methods.GetAccountHolderAffiliationInfo;
import robinhood.api.endpoint.account.methods.GetAccountHolderEmploymentInfo;
import robinhood.api.endpoint.account.methods.GetAccountHolderInvestmentInfo;
import robinhood.api.endpoint.account.methods.GetAccounts;
import robinhood.api.endpoint.account.methods.GetBasicAccountHolderInfo;
import robinhood.api.endpoint.account.methods.GetBasicUserInfo;
import robinhood.api.endpoint.authorize.data.Token;
import robinhood.api.endpoint.authorize.methods.AuthorizeWithoutMultifactor;
import robinhood.api.endpoint.authorize.methods.LogoutFromRobinhood;
import robinhood.api.endpoint.fundamentals.data.TickerFundamentalElement;
import robinhood.api.endpoint.fundamentals.methods.GetTickerFundamental;
import robinhood.api.endpoint.orders.data.SecurityOrderElement;
import robinhood.api.endpoint.orders.enums.OrderTransactionType;
import robinhood.api.endpoint.orders.enums.TimeInForce;
import robinhood.api.endpoint.orders.methods.MakeLimitOrder;
import robinhood.api.endpoint.orders.methods.MakeLimitStopOrder;
import robinhood.api.endpoint.orders.methods.MakeMarketOrder;
import robinhood.api.endpoint.orders.methods.MakeMarketStopOrder;
import robinhood.api.endpoint.orders.throwables.InvalidTickerException;
import robinhood.api.request.RequestManager;
import robinhood.api.request.RequestStatus;
import robinhood.api.throwables.RobinhoodApiException;
import robinhood.api.throwables.RobinhoodNotLoggedInException;

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
	 * @throws RobinhoodApiException 
	 */
	public RobinhoodApi(String username, String password) throws RobinhoodApiException {
		
		//Construct the managers
		RobinhoodApi.requestManager = RequestManager.getInstance();
		RobinhoodApi.configManager = ConfigurationManager.getInstance();
		
		//Log the user in and store the auth token
		this.logUserIn(username, password);
		
	}
	
	/**
	 * Method which returns the authentication for the logged in user, if one exists.
	 * @throws RobinhoodNotLoggedInException 
	 */
	public String getAccountAuthToken() throws RobinhoodNotLoggedInException {
		
		return configManager.getToken();
	}
	
	/**
	 * Method allowing a user to input a token without logging in.
	 * It is not suggested you use this.
	 */
	@Deprecated
	public void setAuthToken(String token) {
		
		configManager.setAuthToken(token);
	}
	
	/**
	 * Method which logs a user in given a username and password.
	 * this method automatically stores the authorization token in with the instance,
	 * allowing any method which requires the token to have immediate access to it.
	 * 
	 * This method is ran if you created the RobinhoodApi class using the constructor with 
	 * both a username and password, but is available if you wish to get the authorization token again.
	 * Usually ran after the user is logged out to refresh the otken
	 * 
	 * @throws Exception if the API could not retrieve an account number for your account. You should never see this,
	 * 
	 */
	public RequestStatus logUserIn(String username, String password) throws RobinhoodApiException {
		
			
			//TODO: Implement multifactor authorization
			ApiMethod method = new AuthorizeWithoutMultifactor(username, password);
			
			try {
				
				Token token = requestManager.makeApiRequest(method);
				
				//Save the token into the configuration manager to be used with other methods
				configManager.setAuthToken(token.getToken());
				
				//Save the account number into the configuraiton manager to be used with other methods
				ApiMethod accountMethod = new GetAccounts();
				accountMethod.addAuthTokenParameter();
				//TODO: Clean up the following line, it should not have to use the array wrapper. Tuck that code elsewhere
				AccountArrayWrapper requestData = requestManager.makeApiRequest(accountMethod);
				AccountElement data = requestData.getResults();
				
				//If there is no account number, something went wrong. Throw an exception
				//TODO: Make this more graceful
				if(data.getAccountNumber() == null) 
					throw new RobinhoodApiException("Failed to get account data for the account.");

				configManager.setAccountNumber(data.getAccountNumber());
				
				return RequestStatus.SUCCESS;
				
				
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RobinhoodNotLoggedInException e) {
				System.out.println("[Error] User is not logged in. You should never see this error. File a bug report if you do!");
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
			
		} catch (RobinhoodNotLoggedInException ex) {
			
			//If there was no token in the configManager, the user was never logged in
			return RequestStatus.NOT_LOGGED_IN;
		} catch (UnirestException e) {
			
			//API error.
			return RequestStatus.FAILURE;
		}
		
	}
	
	/**
	 * Method returning a {@link AccountElement} using the currently logged in user
	 * @throws RobinhoodNotLoggedInException if the user is not logged in
	 */
	public AccountElement getAccountData() throws RobinhoodNotLoggedInException {
		
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
	 * @throws RobinhoodNotLoggedInException if the user is not logged in
	 */
	public BasicUserInfoElement getBasicUserInfo() throws RobinhoodNotLoggedInException {
		
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
	 * @throws RobinhoodNotLoggedInException if the user is not logged in
	 */
	public BasicAccountHolderInfoElement getAccountHolderInfo() throws RobinhoodNotLoggedInException {
		
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
	 * Method returning a {@link AccountHolderAffiliationElement} for the currently logged in user
	 * @throws RobinhoodNotLoggedInException if the user is not logged in
	 */
	public AccountHolderAffiliationElement getAccountHolderAffiliation() throws RobinhoodNotLoggedInException {
		
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
	
	/**
	 * Method returning a {@link AccountHolderEmploymentElement} for the currently logged in user
	 * @throws RobinhoodNotLoggedInException if the user is not logged in
	 */
	public AccountHolderEmploymentElement getAccountHolderEmployment() throws RobinhoodNotLoggedInException {
		
		try {
			
			//Create the API method
			ApiMethod method = new GetAccountHolderEmploymentInfo();
			method.addAuthTokenParameter();
			
			return requestManager.makeApiRequest(method);
			
		} catch (UnirestException ex) {
			
			//Api error
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method returning a {@link AccountHolderInvestmentElement} for the currently logged in user
	 * @throws RobinhoodNotLoggedInException if the user is not logged in
	 */
	public AccountHolderInvestmentElement getAccountHolderInvestment() throws RobinhoodNotLoggedInException {
		
		try {
			
			//Create the API method
			ApiMethod method = new GetAccountHolderInvestmentInfo();
			method.addAuthTokenParameter();
			
			return requestManager.makeApiRequest(method);
			
		} catch (UnirestException ex) {
			
			//Api error
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method returning a {@link TickerFundamentalElement} for the supplied ticker name
	 */
	public TickerFundamentalElement getTickerFundamental(String ticker) {
		
		try {
			
			//Create the API method
			ApiMethod method = new GetTickerFundamental(ticker);
			return requestManager.makeApiRequest(method);
		} catch (UnirestException ex) {
			
			//Api error
			ex.printStackTrace();
		}
		return null;
	} 
	
	/**
	 * Method which returns a {@link SecurityOrderElement} after running a LIMIT order 
	 * given the supplied parameters.
	 * @param ticker The ticker which the buy or sell order should be performed on
	 * @param timeInForce The Enum representation for when this order should be made
	 * @param limitPrice The price you're willing to accept in a sell, or pay in a buy
	 * @param quantity The number of shares you would like to buy or sell
	 * @param orderType Which type of order is being made. A buy, or sell.
	 * @throws InvalidTickerException Thrown when the ticker supplied to the method is invalid.
	 * @throws RobinhoodNotLoggedInException  Thrown when this Robinhood Api instance is not logged into an account. Run the login method first.
	 */
	public SecurityOrderElement makeLimitOrder(String ticker, TimeInForce timeInForce, float limitPrice, int quantity, OrderTransactionType orderType) throws InvalidTickerException, RobinhoodNotLoggedInException, RobinhoodApiException {
		
		try {
			
			//Create the API method
			ApiMethod method = new MakeLimitOrder(ticker, timeInForce, limitPrice, quantity, orderType);
			method.addAuthTokenParameter();
			
			return requestManager.makeApiRequest(method);
			
		} catch (UnirestException ex) {
			
			//Api error
			ex.printStackTrace();
		}
		return null;
	}

    /**
     * Method which returns a {@link SecurityOrderElement} after running a LIMIT STOP order given the supplied
     * parameters
     * @param ticker The ticker which the buy or sell order should be performed on
     * @param timeInForce The Enum representation for when this order should be made
     * @param limitPrice The price you're willing to accept in a sell, or pay in a buy
     * @param quantity The number of shares you would like to buy or sell
     * @param orderType Which type of order is being made. A buy, or a sell
     * @param stopPrice The price at which the stop trigger converts the order into a market order
     * @throws InvalidTickerException The ticker supplied is not valid.
     * @throws RobinhoodApiException There is a general problem with the API.
     * @throws RobinhoodNotLoggedInException Thrown when the current instance is not logged into an account. Run the login method first.
     */
	public SecurityOrderElement makeLimitStopOrder(String ticker, TimeInForce timeInForce, float limitPrice, int quantity, OrderTransactionType orderType, float stopPrice) throws InvalidTickerException, RobinhoodApiException, RobinhoodNotLoggedInException {

		try {

			//Create the API method
			ApiMethod method = new MakeLimitStopOrder(ticker, timeInForce, limitPrice, quantity, orderType, stopPrice);
			method.addAuthTokenParameter();

			return requestManager.makeApiRequest(method);

		} catch (UnirestException ex) {

		    //Api error
            ex.printStackTrace();
        }

        return null;
	}

	/**
	 *
	 * @param ticker What ticker you are performing this order on
	 * @param quantity How many shares should be transacted
	 * @param orderType Which type of order is being made. A buy, or a sell.
	 * @param time The Enum representation of when this order should be made.
	 * @return The SecurityOrderElement object with the API response.
	 * @throws InvalidTickerException if the ticker supplied was invalid
	 * @throws RobinhoodNotLoggedInException if you are not logged into Robinhood on this API object
	 */
	public SecurityOrderElement makeMarketOrder(String ticker, int quantity, OrderTransactionType orderType, TimeInForce time) throws InvalidTickerException, RobinhoodNotLoggedInException, RobinhoodApiException {

		try {

			//Create the API method
			ApiMethod method = new MakeMarketOrder(ticker, quantity, orderType, time);
			method.addAuthTokenParameter();

			return requestManager.makeApiRequest(method);

		} catch (UnirestException ex) {

			//Api error
			ex.printStackTrace();
		}

		return null;
	}

	public SecurityOrderElement makeMarketStopOrder(String ticker, int quantity, OrderTransactionType orderType, TimeInForce time, float stopPrice) throws RobinhoodApiException, InvalidTickerException, RobinhoodNotLoggedInException {

	    try {

	        //Create the API method
            ApiMethod method = new MakeMarketStopOrder(ticker, quantity, orderType, time, stopPrice);
            method.addAuthTokenParameter();

            return requestManager.makeApiRequest(method);
        } catch (UnirestException ex) {

	        //Api error
            ex.printStackTrace();
        }

        return null;
    }
	

	
	

}
