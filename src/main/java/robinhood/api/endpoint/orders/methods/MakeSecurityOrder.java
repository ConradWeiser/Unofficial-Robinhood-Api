package robinhood.api.endpoint.orders.methods;

import java.net.URL;

import robinhood.api.endpoint.orders.Orders;
import robinhood.api.endpoint.orders.enums.OrderTransactionType;
import robinhood.api.endpoint.orders.enums.OrderTrigger;
import robinhood.api.endpoint.orders.enums.OrderType;
import robinhood.api.endpoint.orders.wrappers.OrderTriggerWrapper;
import robinhood.api.endpoint.orders.wrappers.OrderTypeWrapper;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;

public class MakeSecurityOrder extends Orders {
	
	private URL accountUrl;
	private URL instrumentUrl;
	
	private String ticker;
	private OrderType orderType;
	
	//TODO: time_in_force
	
	private OrderTrigger orderTrigger;
	private float price;
	private float stop_price;
	private int quantity;
	private OrderTransactionType side;
	private String client_id;
	private boolean extended_hours = false;
	private boolean override_Day_trade_checks = false;
	
	
	public MakeSecurityOrder(String ticker, OrderTriggerWrapper orderTrigger, OrderTransactionType orderTransactionType, OrderTypeWrapper orderType) {
		
		//Set the regular parameters
		this.setRegularParameters();
		
		
	}
	
	
	/**
	 * Method which sets up the required parameters for the order API call.
	 */
	private void setRegularParameters() {
		
		this.setUrlBase("https://api.robinhood.com/orders/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		this.addHttpHeaderParameter(new HttpHeaderParameter("Content-Type", "application/x-www-form-urlencoded"));
		
		//This method is ran as POST
		this.setMethod(RequestMethod.POST);
		
	}

	/**
	 * Private method which retrieves all of the information needed to make the request given the ticker
	 */
	private void getRequiredInformation() {
		
		
	}
}
