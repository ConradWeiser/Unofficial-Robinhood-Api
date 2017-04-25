package robinhood.api.request;

import java.util.Iterator;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

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
	
	
	public <T> T makeApiRequest(ApiMethod method) throws UnirestException {
		
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
	 * @throws UnirestException 
	 */
	@SuppressWarnings("unchecked")
	private <T> T makePostRequest(ApiMethod method) throws UnirestException {
		
		HttpRequestWithBody request = Unirest.post(method.getBaseUrl());
		
			
		//Append each of the headers for the method
		Iterator<HttpHeaderParameter> headerIterator = method.getHttpHeaderParameters().iterator();
		while(headerIterator.hasNext()) {
			
			HttpHeaderParameter currentHeader = headerIterator.next();
			request.header(currentHeader.getKey(), currentHeader.getValue());
		}
				
		//Append the request body
		request.body(method.getUrlParametersAsPostBody());
				
		//Make the request
		HttpResponse<JsonNode> jsonResponse = request.asJson();
				
		//Parse the response with Gson
		Gson gson = new Gson();
		String responseJsonString = jsonResponse.getBody().toString();
		
		//If the response type for this is VOID (Meaning we are not expecting a response) do not
		//try to use Gson
		if(method.getReturnType() == Void.TYPE)
			return (T) Void.TYPE;
		
		T data = gson.fromJson(responseJsonString, method.getReturnType());
		return data;
		
		
		
		
	}
	
	/**
	 * Method which uses Unirest to send a GET request to the specified URL saved
	 * within the ApiMethod class 
	 * @throws UnirestException 
	 */
	private <T> T makeGetRequest(ApiMethod method) throws UnirestException {
	
		GetRequest request = Unirest.get(method.getBaseUrl());

		//Append each of the headers for the method
		Iterator<HttpHeaderParameter> headerIterator = method.getHttpHeaderParameters().iterator();
		while(headerIterator.hasNext()) {
			
			HttpHeaderParameter currentHeader = headerIterator.next();
			request.header(currentHeader.getKey(), currentHeader.getValue());
		}
		
		//Make the request
		HttpResponse<JsonNode> jsonResponse = request.asJson();
				
		//Parse the response with Gson
		Gson gson = new Gson();
		String responseJsonString = jsonResponse.getBody().toString();
				
		T data = gson.fromJson(responseJsonString,  method.getReturnType());
		return data;
		
	}

	
	

}
