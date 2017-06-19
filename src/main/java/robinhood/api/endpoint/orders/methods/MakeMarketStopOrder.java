package robinhood.api.endpoint.orders.methods;

import robinhood.api.ConfigurationManager;
import robinhood.api.endpoint.orders.Orders;
import robinhood.api.endpoint.orders.enums.OrderTransactionType;
import robinhood.api.endpoint.orders.enums.TimeInForce;
import robinhood.api.endpoint.orders.throwables.InvalidTickerException;
import robinhood.api.parameters.UrlParameter;
import robinhood.api.throwables.RobinhoodApiException;

/**
 * Created by SirensBell on 6/18/2017.
 */
public class MakeMarketStopOrder extends Orders{

    private String ticker = null;
    private int quantity = 0;
    private OrderTransactionType orderType = null;
    private String tickerInstrumentUrl = "";
    private TimeInForce time = null;
    private float stopPrice = 0f;

    public MakeMarketStopOrder(String ticker, int quantity, OrderTransactionType orderType, TimeInForce time, float stopPrice) throws RobinhoodApiException, InvalidTickerException {

        this.ticker = ticker;
        this.quantity = quantity;
        this.orderType = orderType;
        this.time = time;
        this.stopPrice = stopPrice;

        //Set the normal parameters for this endpoint
        setEndpointParameters();

        //Set the order parameters
        setOrderParameters();

        try {

            //Verify the ticker and add it to the instrument URL to be used for later
            this.tickerInstrumentUrl = verifyTickerData(this.ticker);

        } catch (Exception e) {

            this.setOrderSafe(false);
        }
    }

    /**
     * Method which sets the URLParameters so the order is ran as a
     * Market Stop order, given the settings from the constructor
     */
    private void setOrderParameters() {
        this.addUrlParameter(new UrlParameter("account", ConfigurationManager.getInstance().getAccountUrl()));
        this.addUrlParameter(new UrlParameter("instrument", this.tickerInstrumentUrl));
        this.addUrlParameter(new UrlParameter("symbol", this.ticker));
        this.addUrlParameter(new UrlParameter("type", "market"));
        this.addUrlParameter(new UrlParameter("time_in_force", getTimeInForceString(this.time)));
        this.addUrlParameter(new UrlParameter("trigger", "stop"));
        this.addUrlParameter(new UrlParameter("quantity", String.valueOf(this.quantity)));
        this.addUrlParameter(new UrlParameter("side", getOrderSideString(orderType)));
        this.addUrlParameter(new UrlParameter("stop_price", this.stopPrice));
    }

}
