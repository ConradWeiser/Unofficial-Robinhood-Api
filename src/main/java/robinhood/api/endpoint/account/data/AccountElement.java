package robinhood.api.endpoint.account.data;

import java.net.URL;

public class AccountElement {
	
	/**
	 * Public class declaring the response structure for the api.robinhood.com/accounts/ endpoint
	 * allowing Gson to turn the Json directly into this object
	 */
	private boolean deactivated = false;
	
	//TODO: updated_at
	private MarginBalanceElement margin_balances;
	private URL portfolio;
	private CashBalancesElement cash_balances;
	private boolean withdrawl_halted;
	private float cash_available_for_withdrawl;
	private String type;
	private float sma;
	private boolean sweep_enabled;
	private boolean deposit_halted;
	private float buying_power;
	private URL user;
	private float max_ach_early_access_amount;
	private float cash_held_for_orders;
	private boolean only_position_closing_trades;
	private URL url;
	private URL positions;
	
	//TODO: Created_at
	private float cash;
	private float sma_held_for_orders;
	private String account_number;
	private float uncleared_deposits;
	private float unsettled_funds;
	
	/**
	 * @return the deactivated
	 */
	public boolean isDeactivated() {
		return deactivated;
	}
	/**
	 * @return the margin_balances
	 */
	public MarginBalanceElement getMargin_balances() {
		return margin_balances;
	}
	/**
	 * @return the portfolio
	 */
	public URL getPortfolio() {
		return portfolio;
	}
	/**
	 * @return the cash_balances
	 */
	public CashBalancesElement getCash_balances() {
		return cash_balances;
	}
	/**
	 * @return the withdrawl_halted
	 */
	public boolean isWithdrawl_halted() {
		return withdrawl_halted;
	}
	/**
	 * @return the cash_available_for_withdrawl
	 */
	public float getCash_available_for_withdrawl() {
		return cash_available_for_withdrawl;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the sma
	 */
	public float getSma() {
		return sma;
	}
	/**
	 * @return the sweep_enabled
	 */
	public boolean isSweep_enabled() {
		return sweep_enabled;
	}
	/**
	 * @return the deposit_halted
	 */
	public boolean isDeposit_halted() {
		return deposit_halted;
	}
	/**
	 * @return the buying_power
	 */
	public float getBuying_power() {
		return buying_power;
	}
	/**
	 * @return the user
	 */
	public URL getUser() {
		return user;
	}
	/**
	 * @return the max_ach_early_access_amount
	 */
	public float getMax_ach_early_access_amount() {
		return max_ach_early_access_amount;
	}
	/**
	 * @return the cash_held_for_orders
	 */
	public float getCash_held_for_orders() {
		return cash_held_for_orders;
	}
	/**
	 * @return the only_position_closing_trades
	 */
	public boolean isOnly_position_closing_trades() {
		return only_position_closing_trades;
	}
	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}
	/**
	 * @return the positions
	 */
	public URL getPositions() {
		return positions;
	}
	/**
	 * @return the cash
	 */
	public float getCash() {
		return cash;
	}
	/**
	 * @return the sma_held_for_orders
	 */
	public float getSma_held_for_orders() {
		return sma_held_for_orders;
	}
	/**
	 * @return the account_number
	 */
	public String getAccount_number() {
		return account_number;
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
