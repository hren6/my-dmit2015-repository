package dmit2015.oe.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRODUCT_DESCRIPTIONS database table.
 * 
 */
@Embeddable
public class ProductDescriptionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PRODUCT_ID", insertable=false, updatable=false)
	private long productId;

	@Column(name="LANGUAGE_ID")
	private String languageId;

	public ProductDescriptionPK() {
	}
	public long getProductId() {
		return this.productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getLanguageId() {
		return this.languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductDescriptionPK)) {
			return false;
		}
		ProductDescriptionPK castOther = (ProductDescriptionPK)other;
		return 
			(this.productId == castOther.productId)
			&& this.languageId.equals(castOther.languageId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.productId ^ (this.productId >>> 32)));
		hash = hash * prime + this.languageId.hashCode();
		
		return hash;
	}
}