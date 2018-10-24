package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRODUCT_DESCRIPTIONS database table.
 * 
 */
@Entity
@Table(name="PRODUCT_DESCRIPTIONS", schema="OE")
@NamedQuery(name="ProductDescription.findAll", query="SELECT p FROM ProductDescription p")
public class ProductDescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductDescriptionPK id;

	@Column(name="TRANSLATED_DESCRIPTION")
	private String translatedDescription;

	@Column(name="TRANSLATED_NAME")
	private String translatedName;

	//bi-directional many-to-one association to ProductInformation
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", insertable=false, updatable=false)
	private ProductInformation productInformation;

	public ProductDescription() {
	}

	public ProductDescriptionPK getId() {
		return this.id;
	}

	public void setId(ProductDescriptionPK id) {
		this.id = id;
	}

	public String getTranslatedDescription() {
		return this.translatedDescription;
	}

	public void setTranslatedDescription(String translatedDescription) {
		this.translatedDescription = translatedDescription;
	}

	public String getTranslatedName() {
		return this.translatedName;
	}

	public void setTranslatedName(String translatedName) {
		this.translatedName = translatedName;
	}

	public ProductInformation getProductInformation() {
		return this.productInformation;
	}

	public void setProductInformation(ProductInformation productInformation) {
		this.productInformation = productInformation;
	}

}