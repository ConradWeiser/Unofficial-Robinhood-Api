package robinhood.api;

import robinhood.api.throwables.RobinhoodApiException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			RobinhoodApi api = new RobinhoodApi("vibrantclouds", "7850encryptionbagels");
		} catch (RobinhoodApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
