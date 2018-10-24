package dmit2015.oe.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dmit2015.oe.entity.Category;
import dmit2015.oe.report.CategorySales;
import dmit2015.oe.service.OrderEntryService;

@Named
@ViewScoped
public class CategorySalesReportController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderEntryService oeService;
	
	private List<CategorySales> categorySales;
	private String reportTitle;
	private Integer selectedYear;
	private Long selectedCategoryId = 90L;
	

	public List<CategorySales> getCategorySales() {
		return categorySales;
	}
	public void setCategorySales(List<CategorySales> categorySales) {
		this.categorySales = categorySales;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public Long getSelectedCategoryId() {
		return selectedCategoryId;
	}
	public void setSelectedCategoryId(Long selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}
	public Integer getSelectedYear() {
		return selectedYear;
	}
	public void setSelectedYear(Integer selectedYear) {
		this.selectedYear = selectedYear;
	}

	@PostConstruct
	public void init() {		
		Category defaultCategory = oeService.findOneCategory(selectedCategoryId);
		categorySales = oeService.findCategorSalesForParentCategoryId(selectedCategoryId);
		reportTitle = "All Years " + defaultCategory.getCategoryName() + " Sales";
	}
	
	public List<Category> retreiveOnlineCatalogCategories() {
		return oeService.findOnlineCatalogCategories();
	}
	
	public List<Integer> retreiveYearsWithOrders() {
		return oeService.findYearsWithOrders();
	}
		
	public void generateReport() {
		// TODO: Complete the code for this method
		
	}
	
}
