package dmit2015.oe.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the CATEGORIES_TAB database table.
 * 
 */
@Entity
@Table(name="CATEGORIES_TAB")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CATEGORY_ID")
	private long categoryId;

	@Column(name="CATEGORY_DESCRIPTION")
	private String categoryDescription;

	@Column(name="CATEGORY_NAME")
	private String categoryName;

	@ManyToOne
	@JoinColumn(name="PARENT_CATEGORY_ID")
	private Category parentCategory;
	
	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="category")
	private List<ProductInformation> products;
	
	public Category() {
	}

	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryDescription() {
		return this.categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<ProductInformation> getProducts() {
		return products;
	}

	public void setProducts(List<ProductInformation> products) {
		this.products = products;
	}

	public ProductInformation addProduct(ProductInformation productInformation) {
		getProducts().add(productInformation);
		productInformation.setCategory(this);
		
		return productInformation;
	}

	public ProductInformation removeProduct(ProductInformation productInformation) {
		getProducts().remove(productInformation);
		productInformation.setCategory(null);
		
		return productInformation;
	}
}