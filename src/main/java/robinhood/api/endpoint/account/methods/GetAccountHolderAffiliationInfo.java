package robinhood.api.endpoint.account.methods;

import robinhood.api.endpoint.account.Account;
import robinhood.api.endpoint.account.data.AccountHolderAffiliationElement;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;

public class GetAccountHolderAffiliationInfo extends Account {
	
	public GetAccountHolderAffiliationInfo() {
		
		this.setUrlBase("https://api.robinhood.com/user/additional_info/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		
		//This method is ran as GET
		this.setMethod(RequestMethod.GET);
		
		//Declare what the response should look like
		this.setReturnType(AccountHolderAffiliationElement.class);
	}
	

}
