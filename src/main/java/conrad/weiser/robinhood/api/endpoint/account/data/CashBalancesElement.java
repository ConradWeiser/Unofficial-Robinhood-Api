package conrad.weiser.robinhood.api.endpoint.account.data;

public class CashBalancesElement {
	
	private float cash_held_for_orders;
	
	//TODO: created_at
	private float cash;
	private float buying_power;
	
	//TODO: updated_at
	
	private float cash_available_for_withdrawl;
	private float uncleared_deposits;
	private float unsettled_funds;
	
	/**
	 * @return the cash_held_for_orders
	 */
	public float getCash_held_for_orders() {
		return cash_held_for_orders;
	}
	/**
	 * @return the cash
	 */
	public float getCash() {
		return cash;
	}
	/**
	 * @return the buying_power
	 */
	public float getBuying_power() {
		return buying_power;
	}
	/**
	 * @return the cash_available_for_withdrawl
	 */
	public float getCash_available_for_withdrawl() {
		return cash_available_for_withdrawl;
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

}
