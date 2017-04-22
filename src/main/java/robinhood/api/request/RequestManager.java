package robinhood.api.request;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Request.Builder;
import okhttp3.Response;
import robinhood.api.ApiMethod;
import robinhood.api.parameters.HttpHeaderParameter;

public class RequestManager {
	
		
	/**
	 * Singleton instance of this class. 
	 * Only one instance is used for future ratelimiting support
	 */
	private static RequestManager instance = null;
	
	/**
	 * Method to get the active instance of the RequestManager.
	 * If one does not exist, it creates one 
	 */
	public static RequestManager getInstance() {
		
		if(RequestManager.instance == null) {
			
			RequestManager.instance = new RequestManager();
		}
		
		return RequestManager.instance;
	}
	
	
	public <T> T makeApiRequest(ApiMethod method) throws IOException {
		
		T response = null;
				
		//Which request type are we using? Delegate it to the proper method
		switch(method.getMethod()) {
		case DELETE:
			break;
		case GET: response = this.makeGetRequest(method);
			break;
		case HEAD:
			break;
		case OPTIONS:
			break;
		case POST: response = this.makePostRequest(method);
			break;
		case PUT:
			break;
		case TRACE:
			break;
		default:
			break;
		
		}
		
		return response;
		
	}
	
	/**
	 * Method which uses OKHTTP to send a POST request to the specified URL saved
	 * within the APIMethod class
	 * @throws IOException 
	 */
	private <T> T makePostRequest(ApiMethod method) throws IOException {
		
		OkHttpClient client = new OkHttpClient();
		Builder builder = new Request.Builder();
		
		//Add the URL into the builder
		builder.url(method.getUrl());
		
		//We do not use request bodies, Specify that we are using POST without one
		RequestBody body = RequestBody.create(null, new byte[0]);
		builder.method("POST", body);
		
		//Append any header information if available
		Iterator<HttpHeaderParameter> it = method.getHttpHeaderParameters().iterator();
		while(it.hasNext()) {
			
			HttpHeaderParameter currentElement = it.next();
			builder.addHeader(currentElement.getKey(), currentElement.getValue());
		}
		
		System.out.println("Execute the post");
		Response response = client.newCall(builder.build()).execute();
		System.out.println("Done");
		
		//If there is no type defined, throw an error
		if (method.getReturnType() == null) {
			
			throw new NullPointerException("This API method: " + method + " doesn't have a type setup."
					+ "File a bug if you see this. An endpoint is not setup properly");
		}
		
		//Use Gson to convert the type into it's proper class wrapper
		T data = null;
		try {
			
			data = new Gson().fromJson(response.body().string(), method.getReturnType());
		} catch (JsonSyntaxException ex) {
			
			//TODO: Throw custom exception
		}
		
		//If we didn't get any data, throw an error
		if(data == null) {
			
			//TODO: Throw custom exception
			
		}
		
		return data;	
		
		
	}
	
	/**
	 * Method which uses OKHTTP to send a GET request to the specified URL saved
	 * within the ApiMethod class
	 * @throws IOException 
	 */
	private <T> T makeGetRequest(ApiMethod method) throws IOException {
		
		OkHttpClient client = new OkHttpClient();
		Builder builder = new Request.Builder();
		
		//Add the URL into the builder
		builder.url(method.getUrl());
		
		//Append any header information if available
		Iterator<HttpHeaderParameter> it = method.getHttpHeaderParameters().iterator();
		while(it.hasNext()) {
			
			HttpHeaderParameter currentElement = it.next();
			builder.addHeader(currentElement.getKey(), currentElement.getValue());
		}
		
		Response response = client.newCall(builder.build()).execute();
		
		//If there is no type defined, throw an error
		if (method.getReturnType() == null) {
			
			throw new NullPointerException("This API method: " + method + " doesn't have a type setup."
					+ "File a bug if you see this. An endpoint is not setup properly");
		}
		
		//Use Gson to convert the type into it's proper class wrapper
		T data = null;
		try {
			
			data = new Gson().fromJson(response.body().string(), method.getReturnType());
		} catch (JsonSyntaxException ex) {
			
			//TODO: Throw custom exception
		}
		
		//If we didn't get any data, throw an error
		if(data == null) {
			
			//TODO: Throw custom exception
			
		}
		
		return data;	
		
	}
	
	

}
