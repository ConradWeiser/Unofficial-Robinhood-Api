package robinhood.api.endpoint.account;

import robinhood.api.ApiMethod;

public class Account extends ApiMethod {
	
	protected Account() {
		
		super("account");
		
		//We do require a token for these methods. Thus we require it for all methods using this service
		this.requireToken();
	}

}
