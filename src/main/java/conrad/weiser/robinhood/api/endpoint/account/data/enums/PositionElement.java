package conrad.weiser.robinhood.api.endpoint.account.data.enums;


import conrad.weiser.robinhood.api.ApiMethod;
import conrad.weiser.robinhood.api.endpoint.fundamentals.data.InstrumentFundamentalElement;
import conrad.weiser.robinhood.api.endpoint.fundamentals.methods.GetInstrumentFundamental;
import conrad.weiser.robinhood.api.request.RequestManager;
import conrad.weiser.robinhood.api.throwables.RobinhoodApiException;
import conrad.weiser.robinhood.api.throwables.RobinhoodNotLoggedInException;

/**
 * Element containing information of a given position which exists on a users watchlist.
 */
public class PositionElement {


    private float shares_held_for_stock_grants;
    private float intraday_quantity;
    private float intraday_average_buy_price;

    //TODO: created_at and updated_at

    private float shares_held_for_buys;
    private float average_buy_price;
    private float shares_held_for_sells;
    private float quantity;

    private String instrument;


    public float getShares_held_for_stock_grants() {
        return shares_held_for_stock_grants;
    }

    public float getIntraday_quantity() {
        return intraday_quantity;
    }

    public float getIntraday_average_buy_price() {
        return intraday_average_buy_price;
    }

    public float getShares_held_for_buys() {
        return shares_held_for_buys;
    }

    public float getAverage_buy_price() {
        return average_buy_price;
    }

    public float getShares_held_for_sells() {
        return shares_held_for_sells;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getStockName() throws RobinhoodNotLoggedInException {

        ApiMethod method = new GetInstrumentFundamental(this.instrument);
        InstrumentFundamentalElement element;

        try {

            element = RequestManager.getInstance().makeApiRequest(method);
            return element.getStockName();

        } catch (RobinhoodApiException e) {

            return "";

        }

    }

    public String getStockTicker() throws RobinhoodNotLoggedInException {

        ApiMethod method = new GetInstrumentFundamental(this.instrument);
        InstrumentFundamentalElement element;

        try {

            element = RequestManager.getInstance().makeApiRequest(method);
            return element.getSymbol();

        } catch (RobinhoodApiException e) {

            return "";

        }

    }


}
