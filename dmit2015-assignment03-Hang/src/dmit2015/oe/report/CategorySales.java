package dmit2015.oe.report;

import java.math.BigDecimal;

public class CategorySales {

	private String categoryName;
	private BigDecimal categorySalesTotal;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public BigDecimal getCategorySalesTotal() {
		return categorySalesTotal;
	}
	public void setCategorySalesTotal(BigDecimal categorySalesTotal) {
		this.categorySalesTotal = categorySalesTotal;
	}
	
	public CategorySales(String categoryName, BigDecimal categorySalesTotal) {
		super();
		this.categoryName = categoryName;
		this.categorySalesTotal = categorySalesTotal;
	}
}
