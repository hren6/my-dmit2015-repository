package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the INVENTORIES database table.
 * 
 */
@Embeddable
public class InventoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PRODUCT_ID", insertable=false, updatable=false)
	private long productId;

	@Column(name="WAREHOUSE_ID", insertable=false, updatable=false)
	private long warehouseId;

	public InventoryPK() {
	}
	public long getProductId() {
		return this.productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getWarehouseId() {
		return this.warehouseId;
	}
	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InventoryPK)) {
			return false;
		}
		InventoryPK castOther = (InventoryPK)other;
		return 
			(this.productId == castOther.productId)
			&& (this.warehouseId == castOther.warehouseId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.productId ^ (this.productId >>> 32)));
		hash = hash * prime + ((int) (this.warehouseId ^ (this.warehouseId >>> 32)));
		
		return hash;
	}
}