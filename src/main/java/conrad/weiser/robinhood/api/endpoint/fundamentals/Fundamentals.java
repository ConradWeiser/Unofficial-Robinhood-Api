package conrad.weiser.robinhood.api.endpoint.fundamentals;

import conrad.weiser.robinhood.api.ApiMethod;
import conrad.weiser.robinhood.api.throwables.RobinhoodNotLoggedInException;

public class Fundamentals extends ApiMethod {
	
	protected Fundamentals() throws RobinhoodNotLoggedInException {
		
		super("fundamentals");
	}

}
