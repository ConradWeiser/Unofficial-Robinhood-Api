package robinhood.api.endpoint.fundamentals.methods;

import robinhood.api.endpoint.fundamentals.Fundamentals;
import robinhood.api.endpoint.fundamentals.data.TickerFundamentalElement;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;

public class GetTickerFundamental extends Fundamentals {
	
	public GetTickerFundamental(String ticker) {
		
		this.setUrlBase("https://api.robinhood.com/fundamentals/" + ticker +"/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "appliation/json"));
		
		//This method is ran as GET
		this.setMethod(RequestMethod.GET);
		
		//Declare what the response should look like
		this.setReturnType(TickerFundamentalElement.class);
	}

}
