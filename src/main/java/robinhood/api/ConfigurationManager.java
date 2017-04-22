package robinhood.api;

import robinhood.api.throwables.TokenNotFoundException;

/**
 * Method which stores the current configuration for the library.
 * The authentication key used for most of the other functions is stored here.
 * 
 * Singleton is used so there can only be one instance of this running.
 * @author Conrad
 *
 */
public class ConfigurationManager {
	
	/**
	 * The authentication token for the logged in user, if one exists
	 */
	private String authToken = null;
	
	/**
	 * The current instance of the ConfigurationManager
	 */
	private static ConfigurationManager instance = null;
	
	/**
	 * Method which gets the current instance of the ConfigurationManager
	 * If one does not exist, it creates one and returns it
	 */
	public static ConfigurationManager getInstance() {
		
		if(ConfigurationManager.instance == null) {
			ConfigurationManager.instance = new ConfigurationManager();
		}
		
		return ConfigurationManager.instance;
	}
	
	/**
	 * Method which gets the saved authorization token if the user is logged in.
	 * If one does not exist, it throws an error reminding the user to run the login functions
	 * first.
	 * 
	 * @return the saved Token for the logged in user
	 * @throws TokenNotFoundException
	 */
	public String getToken() throws TokenNotFoundException {
		
		if(authToken == null)
			throw new TokenNotFoundException();
		
		return this.authToken;
	}
	
	/**
	 * Method which registers the authToken for the user into the Configuration Manager
	 * 
	 * @param The verified Authorization Token for the user
	 */
	public void setAuthToken(String token) {
		
		ConfigurationManager.instance.authToken = token;
	}
	

}
