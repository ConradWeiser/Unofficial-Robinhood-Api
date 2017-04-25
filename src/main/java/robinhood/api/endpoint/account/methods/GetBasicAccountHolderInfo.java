package robinhood.api.endpoint.account.methods;

import robinhood.api.endpoint.account.Account;
import robinhood.api.endpoint.account.data.BasicAccountHolderInfoElement;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;

public class GetBasicAccountHolderInfo extends Account {
	
	public GetBasicAccountHolderInfo() {
		
		this.setUrlBase("https://api.robinhood.com/user/basic_info/");
		
		//Add the headers into the request
		this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));
		
		//This method is to be ran as GET
		this.setMethod(RequestMethod.GET);
		
		//Declare what the response should look like 
		this.setReturnType(BasicAccountHolderInfoElement.class);
	}

}
