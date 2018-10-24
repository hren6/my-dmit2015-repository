package dmit2015.oe.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dmit2015.oe.report.CategorySales;
import dmit2015.oe.service.OrderEntryService;

@Named
@ViewScoped
public class OnlineCatalogCategorySalesReportController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderEntryService oeService;
	
	private List<CategorySales> categorySales;
	private Integer selectedYear;
	private String reportTitle;
		
	public List<CategorySales> getCategorySales() {
		return categorySales;
	}

	public void setCategorySales(List<CategorySales> categorySales) {
		this.categorySales = categorySales;
	}

	public Integer getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(Integer selectedYear) {
		this.selectedYear = selectedYear;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	@PostConstruct
	public void init() {
		categorySales = oeService.findCategorSalesForOnlineCatalog();
		reportTitle = "All Years Category Sales";
	}
	
	public List<Integer> retreiveYearsWithOrders() {
		return oeService.findYearsWithOrders();
	}
	
	public void generateReport() {
		// TODO: Complete the code for this method
		
	}
}
