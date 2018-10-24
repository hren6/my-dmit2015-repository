package dmit2015.oe.report;

import java.math.BigDecimal;

public class ProductSales {

	private String productName;
	private BigDecimal productSalesTotal;
	private String categoryName;
	private Long quantityCount;
	private long productId;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductSalesTotal() {
		return productSalesTotal;
	}
	public void setProductSalesTotal(BigDecimal productSalesTotal) {
		this.productSalesTotal = productSalesTotal;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getQuantityCount() {
		return quantityCount;
	}
	public void setQuantityCount(Long quantityCount) {
		this.quantityCount = quantityCount;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public ProductSales(String productName, BigDecimal productSalesTotal, String categoryName, Long quantityCount,
			long productId) {
		super();
		this.productName = productName;
		this.productSalesTotal = productSalesTotal;
		this.categoryName = categoryName;
		this.quantityCount = quantityCount;
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return "ProductSales [productName=" + productName + ", productSalesTotal=" + productSalesTotal
				+ ", categoryName=" + categoryName + ", quantityCount=" + quantityCount + ", productId=" + productId
				+ "]";
	}
	
}
