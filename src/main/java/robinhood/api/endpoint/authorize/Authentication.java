package robinhood.api.endpoint.authorize;

import robinhood.api.ApiMethod;

public abstract class Authentication extends ApiMethod {
	
	protected Authentication() {
		
		super("authentication");
		
		//We do not require a token to use this section, thus we do not run the require method
		
	}

}
