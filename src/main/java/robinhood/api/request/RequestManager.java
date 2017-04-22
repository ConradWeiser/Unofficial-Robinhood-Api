package robinhood.api.request;

import java.util.List;
import java.util.Stack;

import robinhood.api.ApiMethod;
import robinhood.api.ConfigurationManager;

public class RequestManager {
	
	/**
	 * A queue containing all of the request. A buffer may add if you are requesting past the ratelimit
	 */
	private List<ApiMethod> apiQueue = null;
	
	
	private final ConfigurationManager manager;
	
	public RequestManager() {
		
		this.manager = ConfigurationManager.getInstance();
		this.apiQueue = new Stack<ApiMethod>();
	}
	
	

}
