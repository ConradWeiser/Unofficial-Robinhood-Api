package robinhood.api.endpoint.account.methods;

import robinhood.api.endpoint.account.Account;
import robinhood.api.endpoint.account.data.BasicUserInfoElement;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;

public class GetBasicUserInfo extends Account {
	
	public GetBasicUserInfo() {
		
		this.setUrlBase("https://api.robinhood.com/user/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		
		//This method is ran as GET
		this.setMethod(RequestMethod.GET);
		
		//Declare what the response should look like
		this.setReturnType(BasicUserInfoElement.class);
		
		
	}

}
