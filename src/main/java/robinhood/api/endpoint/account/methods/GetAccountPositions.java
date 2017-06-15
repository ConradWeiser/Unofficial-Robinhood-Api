package robinhood.api.endpoint.account.methods;

import robinhood.api.endpoint.account.Account;
import robinhood.api.endpoint.account.data.AccountHolderInvestmentElement;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;

/**
 * Created by SirensBell on 5/23/2017.
 */
public class GetAccountPositions extends Account {

    public GetAccountPositions() {

        this.setUrlBase("https://api.robinhood.com/user/investment_profile");

        //Add the headers into the request
        this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));

        //This method is to be ran as GET
        this.setMethod(RequestMethod.GET);

        //Declare what the response should look like
        this.setReturnType(AccountHolderInvestmentElement.class);

    }
}
