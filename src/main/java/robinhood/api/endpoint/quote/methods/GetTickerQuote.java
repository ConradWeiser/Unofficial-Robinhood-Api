package robinhood.api.endpoint.quote.methods;

import robinhood.api.endpoint.quote.Quote;
import robinhood.api.endpoint.quote.data.TickerQuoteElement;
import robinhood.api.parameters.HttpHeaderParameter;
import robinhood.api.request.RequestMethod;

/**
 * Created by SirensBell on 6/19/2017.
 */
public class GetTickerQuote extends Quote {

    public GetTickerQuote(String ticker) {

        this.setUrlBase("https://api.robinhood.com/quotes/" + ticker + "/");

        //Add the header into the request accepting Json
        this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "appliation/json"));

        //This method is ran as GET
        this.setMethod(RequestMethod.GET);

        //Declare what the response should look like
        this.setReturnType(TickerQuoteElement.class);

    }
}
