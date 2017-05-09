package robinhood.api.endpoint.orders.data;

import java.net.URL;

import robinhood.api.endpoint.orders.enums.OrderState;

public class SecurityOrderElement {
	
	//TODO: updated_at
	private String[] executions;
	/**
	 * Total fees incurred. Normally this is 0.00 - Robinhood just likes to
	 * flaunt the fact
	 */
	private float fees;
	
	/**
	 * If this is not NULL, you can POST to this URL to cancel the order
	 */
	private URL cancel;
	
	/**
	 * The internal ID of this order
	 */
	private String id;
	
	/**
	 * The number of shares which have executed so far
	 */
	private float cumulative_quantity;
	
	/**
	 * A String description of why the order was rejected. This can also be NULL
	 */
	private String reject_reason;
	
	/**
	 * The state of the order. This is returned as an {@link OrderState} enum in the getter
	 */
	private String state;
	
	
	
	//TODO: last_transaction_at
	/**
	 * Self explanatory
	 */
	private String client_id;
	
	/**
	 * A link to this order with up to date information
	 */
	private URL url;
	
	//TODO: created_at
	/**
	 * A link to positions for this account with this instrument
	 */
	private URL position;
	
	/**
	 * Average price for all shares executed so far
	 */
	private float average_price;
	
	/**
	 * Should this execute after the exchanges are closed?
	 * Only really returns true if you have Robinhood Gold
	 */
	private boolean extended_hours;
	
	/**
	 * Do you have pattern day trading checking enabled?
	 */
	private boolean override_day_trade_checks;
	
	private boolean override_dtbp_checks;

	/**
	 * @return the executions
	 */
	public String[] getExecutions() {
		return executions;
	}

	/**
	 * @return the fees
	 */
	public float getFees() {
		return fees;
	}

	/**
	 * @return the cancel
	 */
	public URL getCancel() {
		return cancel;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the cumulative_quantity
	 */
	public float getCumulativeQuantity() {
		return cumulative_quantity;
	}

	/**
	 * @return the reject_reason
	 */
	public String getRejectReason() {
		return reject_reason;
	}

	/**
	 * @return the state as an ENUM value representing each possible response. See {@link OrderState}
	 */
	public OrderState getTransactionState() {
	
		switch(this.state) {
		
		case "queued": return OrderState.QUEUED;
		case "unconfirmed": return OrderState.UNCONFIRMED;
		case "confirmed": return OrderState.CONFIRMED;
		case "partially_filled": return OrderState.PARTIALLY_FILLED;
		case "filled": return OrderState.FILLED;
		case "rejected": return OrderState.REJECTED;
		case "canceled": return OrderState.CANCELED;
		case "failed": return OrderState.FAILED;
		default: return OrderState.API_ERROR;
		
		}
		
	}
	
	/**
	 * @return the state of the transaction as a readable string.
	 */
	public String getTransactionStateAsString() {
		
		return this.state;
	}

	/**
	 * @return the client_id
	 */
	public String getClientId() {
		return client_id;
	}

	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * @return the position
	 */
	public URL getPosition() {
		return position;
	}

	/**
	 * @return the average_price
	 */
	public float getAveragePrice() {
		return average_price;
	}

	/**
	 * @return the extended_hours
	 */
	public boolean isExtendedHours() {
		return extended_hours;
	}

	/**
	 * @return the override_day_trade_checks
	 */
	public boolean doesOverrideDayTradeChecks() {
		return override_day_trade_checks;
	}

	/**
	 * @return the override_dtbp_checks
	 */
	public boolean doesOverrideDtbpChecks() {
		return override_dtbp_checks;
	}
	
	
	
}
