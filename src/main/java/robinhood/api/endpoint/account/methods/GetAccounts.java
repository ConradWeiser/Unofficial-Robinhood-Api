package robinhood.api.endpoint.account.methods;

import robinhood.api.endpoint.account.Account;
import robinhood.api.endpoint.account.data.AccountArrayWrapper;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;
import robinhood.api.throwables.TokenNotFoundException;

public class GetAccounts extends Account {
	
	public GetAccounts() throws TokenNotFoundException {
		
		this.setUrlBase("https://api.robinhood.com/accounts/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		
		//This method is ran as GET
		this.setMethod(RequestMethod.GET);
		
		//Declare what the response should look like
		this.setReturnType(AccountArrayWrapper.class);
	}

}
