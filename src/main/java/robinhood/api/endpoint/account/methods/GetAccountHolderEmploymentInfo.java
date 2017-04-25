package robinhood.api.endpoint.account.methods;

import robinhood.api.endpoint.account.Account;
import robinhood.api.endpoint.account.data.AccountHolderEmploymentElement;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;

public class GetAccountHolderEmploymentInfo extends Account {
	
	public GetAccountHolderEmploymentInfo() {
		
		this.setUrlBase("https://api.robinhood.com/user/employment/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		
		//This method is ran as GET
		this.setMethod(RequestMethod.GET);
		
		//Declare what the response should look like
		this.setReturnType(AccountHolderEmploymentElement.class);
	}

}
