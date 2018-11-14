package conrad.weiser.robinhood.api.endpoint.authorize.methods;

import conrad.weiser.robinhood.api.parameters.UrlParameter;
import conrad.weiser.robinhood.api.endpoint.authorize.Authentication;
import conrad.weiser.robinhood.api.endpoint.authorize.data.Token;
import conrad.weiser.robinhood.api.parameters.HttpHeaderParameter;
import conrad.weiser.robinhood.api.request.RequestMethod;
import okhttp3.MediaType;

public class AuthorizeWithoutMultifactor extends Authentication {

	
	public AuthorizeWithoutMultifactor(String username, String password) {
		
		setUrlBase("https://api.robinhood.com/oauth2/token/");
		
		//Add the parameters into the request
		this.addUrlParameter(new UrlParameter("username", username));
		this.addUrlParameter(new UrlParameter("password", password));
		this.addUrlParameter(new UrlParameter("grant_type", "password"));
		this.addUrlParameter(new UrlParameter("client_id", client_id));
		
		//We're going to want a Json response
		
		this.mediaType = MediaType.parse("application/x-www-form-urlencoded");
		
		//This needs to be ran as POST
		this.setMethod(RequestMethod.POST);
		
		//Body cannot be empty
		this.setBody("{}");

		//Declare what the response should look like
		this.setReturnType(Token.class);
		
	}

}
