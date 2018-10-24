package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ORDER_ITEMS database table.
 * 
 */
@Embeddable
public class OrderItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ORDER_ID", insertable=false, updatable=false)
	private long orderId;

	@Column(name="LINE_ITEM_ID")
	private long lineItemId;

	public OrderItemPK() {
	}
	public long getOrderId() {
		return this.orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getLineItemId() {
		return this.lineItemId;
	}
	public void setLineItemId(long lineItemId) {
		this.lineItemId = lineItemId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderItemPK)) {
			return false;
		}
		OrderItemPK castOther = (OrderItemPK)other;
		return 
			(this.orderId == castOther.orderId)
			&& (this.lineItemId == castOther.lineItemId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.orderId ^ (this.orderId >>> 32)));
		hash = hash * prime + ((int) (this.lineItemId ^ (this.lineItemId >>> 32)));
		
		return hash;
	}
}