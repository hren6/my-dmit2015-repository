package dmit2015.oe.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

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
	
	public Order findOneOrder(Long orderId) {
		Order queryOrderSingleResult = null;
		try {
			queryOrderSingleResult = entityManager.createQuery("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.orderId = :orderIdValue",Order.class).setParameter("orderIdValue", orderId).getSingleResult();
		}catch(NoResultException e) {
			queryOrderSingleResult = null;
		}
		
		return queryOrderSingleResult;
	}
	
	public List<Order> findAllOrderByDateRange(Date startDate, Date endDate) {
		List<Order> orderList = entityManager.createQuery(
				"SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDateValue AND :endDateValue",Order.class)
				.setParameter("startDateValue", startDate,TemporalType.DATE)
				.setParameter("endDateValue", DateUtils.addDays(endDate, 1),TemporalType.DATE)
				.getResultList();
		
		return orderList;
	}
		
	public List<Order> findAllOrderByCustomerId(Long customerId) {
		List <Order> orders = entityManager.createQuery(
				"SELECT o FROM Order o WHERE o.customer.customerId = :customerIdValue",Order.class)
				.setParameter("customerIdValue", customerId)
				.getResultList();
		
		return orders;
	}
	
	
	public Customer findOneCustomer(long customerId) {
		// TODO: Complete the code for this method
		
		return null;
	}
	
	public Customer findOneCustomerByUniqueValue(String queryValue) { 
		Customer customer = null;
		try {
			customer = (Customer) entityManager.createNativeQuery(
					"SELECT * FROM CUSTOMERS c, TABLE(c.PHONE_NUMBERS) p WHERE c.CUST_EMAIL = :seachValue OR TO_CHAR(c.CUSTOMER_ID) = :seachValue OR p.Column_Value = :seachValue",Customer.class)
					.setParameter("seachValue", queryValue)
					.getSingleResult();
			
		}catch(NoResultException e) {
			customer = null;
		}
		
		
		return customer;
	}
	
	
	public List<ProductInformation> findAllProductInformationByPattern(String pattern) {
		List<ProductInformation> productInformationList = entityManager.createQuery(
				"SELECT p FROM ProductInformation p" +
				" WHERE p.productName LIKE CONCAT('%',:searchItem,'%') OR p.productDescription LIKE CONCAT('%',:searchItem,'%')",ProductInformation.class)
				.setParameter("searchItem", pattern)
				.getResultList();
		
		return productInformationList;
	}
	
	public ProductDescription findOneProductDescription(Long productId, String languageId) {
		ProductDescription description = (ProductDescription)entityManager.createQuery(
				"SELECT pd FROM ProductDescription pd"+
				" WHERE pd.id.productId = :productIdValue AND pd.id.languageId = :languageIdValue")
				.setParameter("productIdValue", productId)
				.setParameter("languageIdValue", languageId)
				.getSingleResult();
		
		return description;
	}
	
	public ProductInformation findOneProductInformation(long productId) {
		ProductInformation productInformation = (ProductInformation)entityManager.createQuery(
				"SELECT p FROM ProductInformation p" +
				" WHERE p.productId = :productIdValue",ProductInformation.class)
				.setParameter("productIdValue", productId)
				.getSingleResult();
		return productInformation;
	}
	
	
	public Category findOneCategory(long categoryId) {
		Category category = (Category)entityManager.createQuery(
				"SELECT c FROM Category c WHERE c.categoryId = :categoryIdValue",Category.class)
				.setParameter("categoryIdValue", categoryId)
				.getSingleResult();
		
		return category;
	}

	public List<Integer> findYearsWithOrders() {
		List<Integer> years = entityManager.createQuery(
				"SELECT YEAR(o.orderDate) FROM Order o WHERE YEAR(o.orderDate) IS NOT NULL GROUP BY YEAR(o.orderDate) ORDER BY YEAR(o.orderDate)",Integer.class)
				.getResultList();

		return years;
	}
	
	public List<Category> findOnlineCatalogCategories() {
		List<Category> categories = entityManager.createQuery(
				"SELECT DISTINCT pc FROM Category c, IN (c.parentCategory) pc", Category.class)
				.getResultList();
		
		return categories;
	}
	
	public List<CategorySales> findCategorSalesForOnlineCatalog() {
		return entityManager.createQuery(
				"SELECT new dmit2015.oe.report.CategorySales(pc.categoryName, SUM(oi.unitPrice * oi.quantity))"
				+ " FROM OrderItem oi, IN (oi.productInformation) p, IN (p.category) c, IN (c.parentCategory) pc, IN (oi.order) o"
				+ " WHERE pc.categoryId != 90"
				+ " GROUP BY pc.categoryId, pc.categoryName",
				CategorySales.class)
				.getResultList();
	}
	
	public List<CategorySales> findCategorSalesForOnlineCatalogYear(Integer year) {
		if (year == null) {
			return findCategorSalesForOnlineCatalog();
		}
				
		return entityManager.createQuery(
				"SELECT new dmit2015.oe.report.CategorySales(pc.categoryName, SUM(oi.unitPrice * oi.quantity))"
				+ " FROM OrderItem oi, IN (oi.productInformation) p, IN (p.category) c, IN (c.parentCategory) pc, IN (oi.order) o"
				+ " WHERE YEAR(o.orderDate) = :yearValue AND pc.categoryId != 90"
				+ " GROUP BY pc.categoryId, pc.categoryName",
				CategorySales.class)
				.setParameter("yearValue", year)
				.getResultList();
	}
	
	public List<CategorySales> findCategorSalesForParentCategoryId(Long parentCategoryId) {
		List<CategorySales> categorySales =  entityManager.createQuery(
				"SELECT new dmit2015.oe.report.CategorySales(c.categoryName, SUM(oi.unitPrice * oi.quantity))"
						+ " FROM OrderItem oi, IN (oi.productInformation) p, IN (p.category) c, IN (c.parentCategory) pc, IN (pc.parentCategory) pcc, IN (oi.order) o"
						+ " WHERE pc.categoryId = :categoryIdValue OR pcc.categoryId = :categoryIdValue"
						+ " GROUP BY c.categoryId, c.categoryName"
						+ " ORDER BY c.categoryName",
						CategorySales.class)
						.setParameter("categoryIdValue", parentCategoryId)
						.getResultList();
		return categorySales;
	}
	
	public List<CategorySales> findCategorSalesForParentCategoryIdAndYear(Long parentCategoryId, Integer year) {
		return entityManager.createQuery(
				"SELECT new dmit2015.oe.report.CategorySales(c.categoryName, SUM(oi.unitPrice * oi.quantity))"
						+ " FROM OrderItem oi, IN (oi.productInformation) p, IN (p.category) c, IN (c.parentCategory) pc, IN (pc.parentCategory) pcc, IN (oi.order) o"
						+ " WHERE (pc.categoryId = :categoryIdValue OR pcc.categoryId = :categoryIdValue) AND YEAR(o.orderDate) = :yearValue"
						+ " GROUP BY c.categoryId, c.categoryName",
						CategorySales.class)
						.setParameter("categoryIdValue", parentCategoryId)
						.setParameter("yearValue", year)
						.getResultList();
	}
		
	public List<ProductSales> findProductSales(int maxResult) {
		List<ProductSales> productSales = entityManager.createQuery(
				"SELECT new dmit2015.oe.report.ProductSales(p.productName, SUM(oi.unitPrice * oi.quantity), c.categoryName, SUM(oi.quantity), p.productId)"
				+ " FROM OrderItem oi, IN (oi.productInformation) p, IN(p.category) c, IN(oi.order) o"
				+ " GROUP BY c.categoryId, c.categoryName, p.productId, p.productName", ProductSales.class)
				.setMaxResults(maxResult)
				.getResultList();
		
		return productSales;
	}
	
	public List<ProductSales> findProductSalesForYear(Integer year, int maxResult) {
		List<ProductSales> productSales = entityManager.createQuery(
				"SELECT new dmit2015.oe.report.ProductSales(p.productName, SUM(oi.unitPrice * oi.quantity), c.categoryName, SUM(oi.quantity), p.productId)"
				+ " FROM OrderItem oi, IN (oi.productInformation) p, IN(p.category) c, IN(oi.order) o"
				+ " WHERE YEAR(o.orderDate) = :yearValue"
				+ " GROUP BY c.categoryId, c.categoryName, p.productId, p.productName", ProductSales.class)
				.setMaxResults(maxResult)
				.setParameter("yearValue", year)
				.getResultList();
		
		return productSales;
	}
	
}
