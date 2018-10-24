package dmit2015.oe.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.oe.entity.ProductDescription;
import dmit2015.oe.entity.ProductInformation;
import dmit2015.oe.service.OrderEntryService;

@Named
@ViewScoped
public class ProductInformationQueryController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderEntryService oeService;
	
	private Long queryProductId;											// +getter +setter
	private ProductInformation queryProductInformationSingleResult;			// +getter
	private String selectedLanguageId = "US";								// +getter +setter

	private String queryPattern;											// +getter +setter
	private List<ProductInformation> queryProductInformationResultList;		// +getter
	
	public Long getQueryProductId() {
		return queryProductId;
	}
	public void setQueryProductId(Long queryProductId) {
		this.queryProductId = queryProductId;
	}
	public String getSelectedLanguageId() {
		return selectedLanguageId;
	}

	public void setSelectedLanguageId(String selectedLanguageId) {
		this.selectedLanguageId = selectedLanguageId;
	}

	public ProductInformation getQueryProductInformationSingleResult() {
		return queryProductInformationSingleResult;
	}
	public String getQueryPattern() {
		return queryPattern;
	}
	public void setQueryPattern(String queryPattern) {
		this.queryPattern = queryPattern;
	}
	public List<ProductInformation> getQueryProductInformationResultList() {
		return queryProductInformationResultList;
	}
	
	
	public void findProduct() {
		// TODO: Complete the code for this method
		
	}
	
	public void findProduct(Long productId) {
		queryProductId = productId;
		findProduct();
	}
	
	public void findProductsByTerm() {
		// TODO: Complete the code for this method
		
	}
	
	public void changeLanguage() {
		if (queryProductInformationSingleResult != null) {
			// TODO: Complete the code for this method
			
		}
	}
	
	public void cancel() {
		queryProductId = null;
		queryProductInformationSingleResult = null;
		queryProductInformationResultList = null;
		selectedLanguageId = "US";
	}
	
}
