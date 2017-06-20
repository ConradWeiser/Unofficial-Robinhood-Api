package conrad.weiser.robinhood.api.endpoint.account.data.enums;

import conrad.weiser.robinhood.api.throwables.RobinhoodApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SirensBell on 6/19/2017.
 */
public class PositionListElement {

    /**
     * This is here due to what looks to be an unimplemented feature from the Robinhood team
     */
    private PositionElement[] previous;

    /**
     * The list of positions that the user is currently in
     */
    private PositionElement[] results;

    /**
     * Get a Vector object containing every position on the account watchlist. You have a position in that stock if
     * the quantity value is above 0
     * @return A vector containing all of the positions of your account
     */
    public List<PositionElement> getPositionList() throws RobinhoodApiException {

        if(results != null) {

            //Return the array as a list for ease-of-use
            List<PositionElement> elementList = new ArrayList();
            elementList = Arrays.asList(results);

            return elementList;

        }
        else
            throw new RobinhoodApiException("Error retrieving the list of positions for the current user.");
    }

}
