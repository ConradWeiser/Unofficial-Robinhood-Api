package conrad.weiser.robinhood.api.endpoint.fundamentals.data;

/**
 * Created by SirensBell on 6/20/2017.
 */
public class InstrumentFundamentalElement {

    //TODO: Change 'state' to an Enum


    private float min_tick_size;
    private float margin_initial_ratio;

    private String symbol;
    private String bloomberg_unique;
    private String list_date;
    private String name;
    private String state;
    private String country;

    private float day_trade_ratio;
    private boolean tradeable;
    private float maintenance_ratio;
    private String id;
    private String simple_name;

    public float getMin_tick_size() {
        return min_tick_size;
    }

    public float getMarginInitialRatio() {
        return margin_initial_ratio;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getBloombergUnique() {
        return bloomberg_unique;
    }

    public String getListingDate() {
        return list_date;
    }

    public String getStockName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public float getDayTradeRatio() {
        return day_trade_ratio;
    }

    public boolean isTradeable() {
        return tradeable;
    }

    public float getMaintenanceRatio() {
        return maintenance_ratio;
    }

    public String getId() {
        return id;
    }

    public String getSimpleName() {
        return simple_name;
    }


}
