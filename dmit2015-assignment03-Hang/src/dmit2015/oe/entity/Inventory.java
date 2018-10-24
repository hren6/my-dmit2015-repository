package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the INVENTORIES database table.
 * 
 */
@Entity
@Table(name="INVENTORIES", schema="OE")
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InventoryPK id;

	@Column(name="QUANTITY_ON_HAND")
	private Integer quantityOnHand;

	//bi-directional many-to-one association to ProductInformation
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", insertable=false, updatable=false)
	private ProductInformation productInformation;

	//bi-directional many-to-one association to Warehouse
	@ManyToOne
	@JoinColumn(name="WAREHOUSE_ID", insertable=false, updatable=false)
	private Warehouse warehouse;

	public Inventory() {
	}

	public InventoryPK getId() {
		return this.id;
	}

	public void setId(InventoryPK id) {
		this.id = id;
	}

	public Integer getQuantityOnHand() {
		return this.quantityOnHand;
	}

	public void setQuantityOnHand(Integer quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}

	public ProductInformation getProductInformation() {
		return this.productInformation;
	}

	public void setProductInformation(ProductInformation productInformation) {
		this.productInformation = productInformation;
	}

	public Warehouse getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

}