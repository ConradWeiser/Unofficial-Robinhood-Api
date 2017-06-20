package conrad.weiser.robinhood.api.endpoint.account.data;

/**
 * Method containing the Margin_Balance data structure to be extracted from Json requests
 *
 */
public class MarginBalanceElement {
	
	private float day_trade_buying_power;
	
	//TODO: created_at
	
	private float overnight_buying_power_held_for_orders;
	private float cash_held_for_orders;
	private float day_trade_buying_power_held_for_orders;
	
	//TODO: marked_pattern_day_trader_date
	private float cash;
	private float unallocated_margin_cash;
	
	//TODO: updated_at
	private float cash_available_for_withdral;
	private float margin_limit;
	private float overnight_buying_power;
	private float uncleared_deposits;
	private float unsettled_funds;
	private float day_trade_ratio;
	private float overnight_ratio;
	
	/**
	 * @return the day_trade_buying_power
	 */
	public float getDay_trade_buying_power() {
		return day_trade_buying_power;
	}
	/**
	 * @return the overnight_buying_power_held_for_orders
	 */
	public float getOvernight_buying_power_held_for_orders() {
		return overnight_buying_power_held_for_orders;
	}
	/**
	 * @return the cash_held_for_orders
	 */
	public float getCash_held_for_orders() {
		return cash_held_for_orders;
	}
	/**
	 * @return the day_trade_buying_power_held_for_orders
	 */
	public float getDay_trade_buying_power_held_for_orders() {
		return day_trade_buying_power_held_for_orders;
	}
	/**
	 * @return the cash
	 */
	public float getCash() {
		return cash;
	}
	/**
	 * @return the unallocated_margin_cash
	 */
	public float getUnallocated_margin_cash() {
		return unallocated_margin_cash;
	}
	/**
	 * @return the cash_available_for_withdral
	 */
	public float getCash_available_for_withdral() {
		return cash_available_for_withdral;
	}
	/**
	 * @return the margin_limit
	 */
	public float getMargin_limit() {
		return margin_limit;
	}
	/**
	 * @return the overnight_buying_power
	 */
	public float getOvernight_buying_power() {
		return overnight_buying_power;
	}
	/**
	 * @return the uncleared_deposits
	 */
	public float getUncleared_deposits() {
		return uncleared_deposits;
	}
	/**
	 * @return the unsettled_funds
	 */
	public float getUnsettled_funds() {
		return unsettled_funds;
	}
	/**
	 * @return the day_trade_ratio
	 */
	public float getDay_trade_ratio() {
		return day_trade_ratio;
	}
	/**
	 * @return the overnight_ratio
	 */
	public float getOvernight_ratio() {
		return overnight_ratio;
	}

}
