package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PRODUCT_INFORMATION database table.
 * 
 */
@Entity
@Table(name="PRODUCT_INFORMATION", schema="OE")
@NamedQuery(name="ProductInformation.findAll", query="SELECT p FROM ProductInformation p")
public class ProductInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRODUCT_ID")
	private long productId;

	@Column(name="CATALOG_URL")
	private String catalogUrl;

	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	private Category category;

	@Column(name="LIST_PRICE")
	private BigDecimal listPrice;

	@Column(name="MIN_PRICE")
	private BigDecimal minPrice;

	@Column(name="PRODUCT_DESCRIPTION")
	private String productDescription;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="PRODUCT_STATUS")
	private String productStatus;

	@Column(name="SUPPLIER_ID")
	private Long supplierId;

	@Column(name="WARRANTY_PERIOD")
	private String warrantyPeriod;

	@Column(name="WEIGHT_CLASS")
	private Integer weightClass;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="productInformation")
	private List<Inventory> inventories;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="productInformation")
	private List<OrderItem> orderItems;

	//bi-directional many-to-one association to ProductDescription
	@OneToMany(mappedBy="productInformation")
	private List<ProductDescription> productDescriptions;

	public ProductInformation() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getCatalogUrl() {
		return this.catalogUrl;
	}

	public void setCatalogUrl(String catalogUrl) {
		this.catalogUrl = catalogUrl;
	}

	public BigDecimal getListPrice() {
		return this.listPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public Long getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getWarrantyPeriod() {
		return this.warrantyPeriod;
	}

	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public Integer getWeightClass() {
		return this.weightClass;
	}

	public void setWeightClass(Integer weightClass) {
		this.weightClass = weightClass;
	}

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setProductInformation(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setProductInformation(null);

		return inventory;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setProductInformation(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setProductInformation(null);

		return orderItem;
	}

	public List<ProductDescription> getProductDescriptions() {
		return this.productDescriptions;
	}

	public void setProductDescriptions(List<ProductDescription> productDescriptions) {
		this.productDescriptions = productDescriptions;
	}

	public ProductDescription addProductDescription(ProductDescription productDescription) {
		getProductDescriptions().add(productDescription);
		productDescription.setProductInformation(this);

		return productDescription;
	}

	public ProductDescription removeProductDescription(ProductDescription productDescription) {
		getProductDescriptions().remove(productDescription);
		productDescription.setProductInformation(null);

		return productDescription;
	}

}