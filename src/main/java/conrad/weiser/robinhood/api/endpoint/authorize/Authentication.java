package conrad.weiser.robinhood.api.endpoint.authorize;

import conrad.weiser.robinhood.api.ApiMethod;

public abstract class Authentication extends ApiMethod {
	
	protected Authentication() {
		
		super("authentication");
		
		//We do not require a token to use this section, thus we do not run the require method
		
	}

}
