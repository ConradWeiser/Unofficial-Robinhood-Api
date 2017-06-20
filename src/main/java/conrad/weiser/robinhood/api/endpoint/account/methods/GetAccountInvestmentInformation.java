package conrad.weiser.robinhood.api.endpoint.account.methods;

import conrad.weiser.robinhood.api.endpoint.account.Account;
import conrad.weiser.robinhood.api.endpoint.account.data.AccountHolderInvestmentElement;
import conrad.weiser.robinhood.api.parameters.HttpHeaderParameter;
import conrad.weiser.robinhood.api.request.RequestMethod;

/**
 * Created by SirensBell on 5/23/2017.
 */
public class GetAccountInvestmentInformation extends Account {

    public GetAccountInvestmentInformation() {

        this.setUrlBase("https://api.robinhood.com/user/investment_profile");

        //Add the headers into the request
        this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "application/json"));

        //This method is to be ran as GET
        this.setMethod(RequestMethod.GET);

        //Declare what the response should look like
        this.setReturnType(AccountHolderInvestmentElement.class);

    }
}
