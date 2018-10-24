package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;

import dmit2015.hr.entity.Location;

import java.util.List;


/**
 * The persistent class for the WAREHOUSES database table.
 * 
 */
@Entity
@Table(name="WAREHOUSES", schema="OE")
@NamedQuery(name="Warehouse.findAll", query="SELECT w FROM Warehouse w")
public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WAREHOUSE_ID")
	private long warehouseId;

	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private Location location;
	
	@Column(name="WAREHOUSE_NAME")
	private String warehouseName;

	@Column(name="WAREHOUSE_SPEC")
	private String warehouseSpec;

	@Column(name="WH_GEO_LOCATION")
	private String whGeoLocation;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="warehouse")
	private List<Inventory> inventories;

	public Warehouse() {
	}

	public long getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public String getWarehouseName() {
		return this.warehouseName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseSpec() {
		return this.warehouseSpec;
	}

	public void setWarehouseSpec(String warehouseSpec) {
		this.warehouseSpec = warehouseSpec;
	}

	public String getWhGeoLocation() {
		return this.whGeoLocation;
	}

	public void setWhGeoLocation(String whGeoLocation) {
		this.whGeoLocation = whGeoLocation;
	}

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setWarehouse(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setWarehouse(null);

		return inventory;
	}

}