package robinhood.api.endpoint.orders.methods;

import com.mashape.unirest.http.exceptions.UnirestException;

import robinhood.api.ApiMethod;
import robinhood.api.ConfigurationManager;
import robinhood.api.endpoint.fundamentals.data.TickerFundamentalElement;
import robinhood.api.endpoint.fundamentals.methods.GetTickerFundamental;
import robinhood.api.endpoint.orders.Orders;
import robinhood.api.endpoint.orders.data.SecurityOrderElement;
import robinhood.api.endpoint.orders.enums.OrderTransactionType;
import robinhood.api.endpoint.orders.enums.TimeInForce;
import robinhood.api.endpoint.orders.throwables.InvalidTickerException;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.parameters.UrlParameter;
import robinhood.api.request.RequestManager;
import robinhood.api.request.RequestMethod;

public class MakeLimitOrder extends Orders {
	
	private String ticker = null;
	private TimeInForce time = null;
	private float limitPrice = 0;
	private int quantity = 0;
	private OrderTransactionType orderType = null;
	
	private String tickerInstrumentUrl = null;
	
	public MakeLimitOrder(String ticker, TimeInForce time, float limitPrice, int quantity, OrderTransactionType orderType) throws InvalidTickerException {
		
		this.ticker = ticker;
		this.time = time;
		this.limitPrice = limitPrice;
		this.quantity = quantity;
		this.orderType = orderType;
		
		//Set the normal parameters for this endpoint
		setEndpointParameters();
		
		//Verify the ticker, and add the instrument URL to be used for later
		verifyTickerData();
		
		//Set the order parameters
		setOrderParameters();
		
	}
	
	/**
	 * 
	 */
	
	/**
	 * Method which verifies that the ticker is a valid one. If not, throw an error.
	 * This method also supplies additional information of the Ticker symbol that the order class
	 * is required to use.
	 * @throws InvalidTickerException 
	 */
	private void verifyTickerData() throws InvalidTickerException {
		
		//Make a Ticker Fundamental API request for the supplied ticker
		RequestManager requestManager = RequestManager.getInstance();
		
		ApiMethod method = new GetTickerFundamental(this.ticker);
		try {
			
			TickerFundamentalElement response = requestManager.makeApiRequest(method);
			
			//Does the ticker have a valid Instrument URL? If not, this ticker is invalid. Throw an error.
			if(response.getInstrument() == null) 
				throw new InvalidTickerException();
			
			//Otherwise, supply the InstrumentURL to the class to be used in the request
			this.tickerInstrumentUrl = response.getInstrument().toString();
			
		} catch (UnirestException ex) {
			
			//Api error
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Method which sets up the basic parameters for the endpoint. 
	 * This does not include the order data.
	 */
	private void setEndpointParameters() {
		
		this.setUrlBase("https://api.robinhood.com/orders/");
		
		//Add the send-receive headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		this.addHttpHeaderParameter(new HttpHeaderParameter("Content-Type", "application/x-www-form-urlencoded"));
		
		//This method should be ran as POST
		this.setMethod(RequestMethod.POST);
		
		this.setReturnType(SecurityOrderElement.class);
	}
	
	/**
	 * Method which sets the URLParameters for correctly so the order is ran as a 
	 * Limit Buy order, given the settings from the constructor
	 */
	private void setOrderParameters() {
		
		//Add the account URL for the currently logged in account
		this.addUrlParameter(new UrlParameter("account", ConfigurationManager.getInstance().getAccountNumber()));
		this.addUrlParameter(new UrlParameter("instrument", this.tickerInstrumentUrl));
		this.addUrlParameter(new UrlParameter("symbol", this.ticker));
		this.addUrlParameter(new UrlParameter("type", "limit"));
		this.addUrlParameter(new UrlParameter("time_in_force", getTimeInForceString()));
		this.addUrlParameter(new UrlParameter("price", this.limitPrice));
		this.addUrlParameter(new UrlParameter("trigger", "immediate"));
		this.addUrlParameter(new UrlParameter("quantity", String.valueOf(this.quantity)));
		this.addUrlParameter(new UrlParameter("side", getOrderSideString()));
	}
	
	/**
	 * Method which parses the Time of Day enum variable and returns the simplified version which the API
	 * requires in order to make a request
	 */
	private String getTimeInForceString() {
		
		try {
		
			switch(this.time) {
		
			case FILL_OR_KILL:
				return "fok";

			case GOOD_FOR_DAY:
				return "gfd";
			
			case GOOD_UNTIL_CANCELED:
				return "gtc";
			
			case IMMEDIATE_OR_CANCEL:
				return "ioc";
			
			case ON_MARKET_OPEN:
				return "opg";
			
			//You should never see this.. 
			default:
				throw new Exception();
					
			}
		} catch (Exception ex) {
			
			System.err.println("[RobinhoodApi] ERROR - Time in Force parsing failed. You shouldn't see this, please file a bug report on github!");
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method which parses the Order Side enum value.
	 * This should only return BUY or SELL
	 */
	private String getOrderSideString() {
		
		try {
		
			switch(this.orderType) {
			case BUY:
				return "buy";
				
			case SELL:
				return "sell";
				
			default:
				throw new Exception();
				
			}
		
		} catch (Exception ex) {
			
			System.err.println("[Robinhood API] ERROR - Order Side parsing failed. You shouldn't see this. Please file a bug report on github!");
			ex.printStackTrace();
			
		}
		return null;
		
	}
	
	

}
