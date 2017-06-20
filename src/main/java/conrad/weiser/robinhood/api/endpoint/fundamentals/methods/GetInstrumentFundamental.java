package conrad.weiser.robinhood.api.endpoint.fundamentals.methods;

import conrad.weiser.robinhood.api.endpoint.fundamentals.Fundamentals;
import conrad.weiser.robinhood.api.endpoint.fundamentals.data.InstrumentFundamentalElement;
import conrad.weiser.robinhood.api.parameters.HttpHeaderParameter;
import conrad.weiser.robinhood.api.request.RequestMethod;

/**
 * Given a instrument ID, this method returns a {@link conrad.weiser.robinhood.api.endpoint.fundamentals.data.InstrumentFundamentalElement}
 * for the given instrument.
 * This class is not implemented in {@link conrad.weiser.robinhood.api.RobinhoodApi} because these IDs are generally not public, and
 * must be retrieved from other API methods.
 */
public class GetInstrumentFundamental extends Fundamentals {

    public GetInstrumentFundamental(String instrumentId) {

        this.setUrlBase("https://api.robinhood.com/instruments/" + instrumentId + "/");

        //Add the headers into the request
        this.addHttpHeaderParameter(new HttpHeaderParameter("Accept", "appliation/json"));

        //This method is run as GET
        this.setMethod(RequestMethod.GET);

        //Declare what the response should look like
        this.setReturnType(InstrumentFundamentalElement.class);

    }
}
