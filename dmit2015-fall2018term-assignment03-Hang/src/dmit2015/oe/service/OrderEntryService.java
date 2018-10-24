package dmit2015.oe.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;

import dmit2015.oe.entity.Category;
import dmit2015.oe.entity.Customer;
import dmit2015.oe.entity.Order;
import dmit2015.oe.entity.ProductDescription;
import dmit2015.oe.entity.ProductDescriptionPK;
import dmit2015.oe.entity.ProductInformation;
import dmit2015.oe.report.CategorySales;
import dmit2015.oe.report.ProductSales;

@Stateless
public class OrderEntryService {

	@Inject
	private EntityManager entityManager;
	
	public Order findOneOrder(long orderId) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public List<Order> findAllOrderByDateRange(Date startDate, Date endDate) {
		// TODO: Complete the code for this method
		
		return null;
	}
		
	public List<Order> findAllOrderByCustomerId(Long customerId) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	
	public Customer findOneCustomer(long customerId) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public Customer findOneCustomerByUniqueValue(String queryValue) { 
		// TODO: Complete the code for this method
		
		return null;
	}
	
	
	public List<ProductInformation> findAllProductInformationByPattern(String pattern) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public ProductDescription findOneProductDescription(Long productId, String languageId) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public ProductInformation findOneProductInformation(long productId) {
		// TODO: Write the code for this method
		return null;
	}
	
	
	public Category findOneCategory(long categoryId) {
		// TODO: Complete the code for this method
		
		return null;
	}

	public List<Integer> findYearsWithOrders() {
		// TODO: Write the code for this method
		return null;
	}
	
	public List<Category> findOnlineCatalogCategories() {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public List<CategorySales> findCategorSalesForOnlineCatalog() {
		// TODO: Write the code for this method
		return null;
	}
	
	public List<CategorySales> findCategorSalesForOnlineCatalogYear(Integer year) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public List<CategorySales> findCategorSalesForParentCategoryId(Long parentCategoryId) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public List<CategorySales> findCategorSalesForParentCategoryIdAndYear(Long parentCategoryId, Integer year) {
		// TODO: Complete the code for this method
		
		return null;
	}
		
	public List<ProductSales> findProductSales(int maxResult) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public List<ProductSales> findProductSalesForYear(Integer year, int maxResult) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
}
