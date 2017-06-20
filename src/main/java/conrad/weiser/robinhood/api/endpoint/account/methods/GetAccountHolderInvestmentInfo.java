package conrad.weiser.robinhood.api.endpoint.account.methods;

import conrad.weiser.robinhood.api.ConfigurationManager;
import conrad.weiser.robinhood.api.endpoint.account.Account;
import conrad.weiser.robinhood.api.request.RequestMethod;
import conrad.weiser.robinhood.api.endpoint.account.data.AccountHolderInvestmentElement;
import conrad.weiser.robinhood.api.parameters.HttpHeaderParameter;

public class GetAccountHolderInvestmentInfo extends Account {
	
	public GetAccountHolderInvestmentInfo() {
		
		this.setUrlBase("https://api.robinhood.com/accounts" + ConfigurationManager.getInstance().getAccountNumber() + "/positions/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		
		//This method is to be ran as GET
		this.setMethod(RequestMethod.GET);
		
		//Declare what the response should look like
		this.setReturnType(AccountHolderInvestmentElement.class);
	}

}
