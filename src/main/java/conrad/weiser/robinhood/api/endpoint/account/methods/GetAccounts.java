package conrad.weiser.robinhood.api.endpoint.account.methods;

import conrad.weiser.robinhood.api.endpoint.account.Account;
import conrad.weiser.robinhood.api.endpoint.account.data.AccountArrayWrapper;
import conrad.weiser.robinhood.api.request.RequestMethod;
import conrad.weiser.robinhood.api.parameters.HttpHeaderParameter;

public class GetAccounts extends Account {
	
	public GetAccounts()  {
		
		this.setUrlBase("https://api.robinhood.com/accounts/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		
		//This method is ran as GET
		this.setMethod(RequestMethod.GET);
		
		//Declare what the response should look like
		this.setReturnType(AccountArrayWrapper.class);
	}

}
