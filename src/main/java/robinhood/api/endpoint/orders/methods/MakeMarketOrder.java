package robinhood.api.endpoint.orders.methods;

import robinhood.api.ConfigurationManager;
import robinhood.api.endpoint.orders.Orders;
import robinhood.api.endpoint.orders.enums.OrderTransactionType;
import robinhood.api.endpoint.orders.enums.TimeInForce;
import robinhood.api.endpoint.orders.throwables.InvalidTickerException;
import robinhood.api.parameters.UrlParameter;

/**
 * Created by SirensBell on 6/15/2017.
 */
public class MakeMarketOrder extends Orders {

    private String ticker = null;
    private int quantity = 0;
    private OrderTransactionType orderType = null;
    private String tickerInstrumentUrl = "";
    private TimeInForce time = null;

    public MakeMarketOrder(String ticker, int quantity, OrderTransactionType orderType, TimeInForce time) throws InvalidTickerException {

        this.ticker = ticker;
        this.quantity = quantity;
        this.orderType = orderType;

        //Set the normal parameters for this endpoint
        setEndpointParameters();

        //Set the order parameters
        setOrderParameters();

        try {

            //Verify the ticker, and add the instrument URL to be used for later
            this.tickerInstrumentUrl = verifyTickerData(this.ticker);

        } catch (Exception e) {

            //If there is an invalid ticker, set this order to be unsafe
            this.setOrderSafe(false);
        }

    }

    /**
     * Method which sets the URLParameters for correctly so the order is ran as a
     * Market Buy order, given the settings from the constructor
     */
    private void setOrderParameters() {

        this.addUrlParameter(new UrlParameter("account", ConfigurationManager.getInstance().getAccountUrl()));
        this.addUrlParameter(new UrlParameter("instrument", this.tickerInstrumentUrl));
        this.addUrlParameter(new UrlParameter("symbol", this.ticker));
        this.addUrlParameter(new UrlParameter("type", "market"));
        this.addUrlParameter(new UrlParameter("time_in_force", getTimeInForceString(this.time)));
        this.addUrlParameter(new UrlParameter("trigger", "immediate"));
        this.addUrlParameter(new UrlParameter("quantity", String.valueOf(this.quantity)));
        this.addUrlParameter(new UrlParameter("side", getOrderSideString(orderType)));

    }




}
