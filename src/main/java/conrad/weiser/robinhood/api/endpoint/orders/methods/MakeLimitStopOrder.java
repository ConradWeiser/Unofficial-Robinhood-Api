package conrad.weiser.robinhood.api.endpoint.orders.methods;

import conrad.weiser.robinhood.api.parameters.UrlParameter;
import conrad.weiser.robinhood.api.ConfigurationManager;
import conrad.weiser.robinhood.api.endpoint.orders.Orders;
import conrad.weiser.robinhood.api.endpoint.orders.enums.OrderTransactionType;
import conrad.weiser.robinhood.api.endpoint.orders.enums.TimeInForce;
import conrad.weiser.robinhood.api.endpoint.orders.throwables.InvalidTickerException;
import conrad.weiser.robinhood.api.throwables.RobinhoodApiException;

/**
 * Created by SirensBell on 5/11/2017.
 */
public class MakeLimitStopOrder extends Orders {

    private String ticker = null;
    private TimeInForce time = null;
    private float limitPrice = 0;
    private int quantity = 0;
    private OrderTransactionType orderType = null;
    private float stopPrice = 0;

    private String tickerInstrumentUrl = null;

    public MakeLimitStopOrder(String ticker, TimeInForce time, float limitPrice, int quantity, OrderTransactionType orderType, float stopPrice) {

        this.ticker = ticker;
        this.time = time;
        this.limitPrice = limitPrice;
        this.quantity = quantity;
        this.orderType = orderType;
        this.stopPrice = stopPrice;

        //Set the normal parameters for this endpoint
        setEndpointParameters();

        try {

            //Verify the ticker, and add the instrument URL to be used for later
            this.tickerInstrumentUrl = verifyTickerData(this.ticker);

            // Set the order parameters
            setOrderParameters();

        } catch (Exception e) {

            //If there is an invalid ticker, set this order to be unsafe
            this.setOrderSafe(false);
        }

    }





    /**
     * Method which sets the URLParameters for correctly so the order is ran as a
     * Limit Buy order, given the settings from the constructor
     */
    private void setOrderParameters() {

        //Add the account URL for the currently logged in account
        this.addUrlParameter(new UrlParameter("account", ConfigurationManager.getInstance().getAccountUrl()));
        this.addUrlParameter(new UrlParameter("symbol", this.ticker));
        this.addUrlParameter(new UrlParameter("instrument", this.tickerInstrumentUrl));
        this.addUrlParameter(new UrlParameter("time_in_force", getTimeInForceString(this.time)));
        this.addUrlParameter(new UrlParameter("trigger", "immediate"));
        this.addUrlParameter(new UrlParameter("type", "limit"));
        this.addUrlParameter(new UrlParameter("price", this.limitPrice));
        this.addUrlParameter(new UrlParameter("side", getOrderSideString(this.orderType)));
        this.addUrlParameter(new UrlParameter("quantity", String.valueOf(this.quantity)));

    }

}
