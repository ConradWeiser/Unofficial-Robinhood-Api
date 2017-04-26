package robinhood.api.endpoint.orders.wrappers;

import robinhood.api.endpoint.orders.enums.OrderType;

public class OrderTypeWrapper {
	
	private OrderType orderType;
	private float price;
	
	/**
	 * If a price is included, that makes this order type LIMIT
	 * Price: The price you're willing to accept in a sell or pay in a buy
	 */
	public OrderTypeWrapper(float price) {
		
		this.price = price;
		this.orderType = OrderType.LIMIT;
		
	}
	
	/**
	 * If you do not include a price value, this order type is MARKET
	 */
	public OrderTypeWrapper() {
		
		this.orderType = OrderType.MARKET;
		
	}

	public OrderType getOrderType() {
		return this.orderType;
	}

	public float getPrice() {
		
		return this.price;
	}
	
	

}
