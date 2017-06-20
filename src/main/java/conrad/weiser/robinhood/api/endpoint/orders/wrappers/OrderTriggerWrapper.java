package conrad.weiser.robinhood.api.endpoint.orders.wrappers;

import conrad.weiser.robinhood.api.endpoint.orders.enums.OrderTrigger;

public class OrderTriggerWrapper {
	
	private float stopPrice;
	OrderTrigger orderTriggerEnum = null;
	
	/**
	 * If the order type is going to be STOP, require the stop_price for the transaction
	 * @param transactionType
	 */
	public OrderTriggerWrapper(float stopPrice) {
		
		this.stopPrice = stopPrice;
		this.orderTriggerEnum = OrderTrigger.STOP;
		
	}
	
	/**
	 * Otherwise, you're creating an IMMEDIATE order.
	 */
	
	public OrderTriggerWrapper() {
		
		this.orderTriggerEnum = OrderTrigger.IMMEDIATE;
	}

	public float getStopPrice() {
		return stopPrice;
	}
	
	public OrderTrigger getOrderTriggerEnum() {
		
		return this.orderTriggerEnum;
	}

}
