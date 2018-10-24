package dmit2015.oe.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dmit2015.oe.report.ProductSales;
import dmit2015.oe.service.OrderEntryService;

@Named
@ViewScoped
public class ProductSalesReportController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderEntryService oeService;

	private List<ProductSales> productSales;
	private Integer selectedYear;
	private String reportTitle;
	private int maxResult = 5;

	public Integer getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(Integer selectedYear) {
		this.selectedYear = selectedYear;
	}

	public List<ProductSales> getProductSales() {
		return productSales;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	@PostConstruct
	public void init() {
		productSales = oeService.findProductSales(maxResult);
		reportTitle = "Top " + maxResult + " selling products of all time";
	}

	public List<Integer> retreiveYearsWithOrders() {
		return oeService.findYearsWithOrders();
	}

	public void generateReport() {
		// TODO: Complete the code for this method
		
	}

}
