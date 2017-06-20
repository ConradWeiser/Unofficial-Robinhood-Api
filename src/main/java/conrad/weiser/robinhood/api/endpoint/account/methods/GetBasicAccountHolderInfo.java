package conrad.weiser.robinhood.api.endpoint.account.methods;

import conrad.weiser.robinhood.api.endpoint.account.Account;
import conrad.weiser.robinhood.api.endpoint.account.data.BasicAccountHolderInfoElement;
import conrad.weiser.robinhood.api.request.RequestMethod;
import conrad.weiser.robinhood.api.parameters.HttpHeaderParameter;

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
