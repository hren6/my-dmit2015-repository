package dmit2015.hr.controller;
import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import dmit2015.hr.entity.Job;
import dmit2015.hr.service.HumanResourceService;

@Named
@ViewScoped
public class JobDeleteController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private HumanResourceService currentHumanResourceService;
	
	@Produces
	@Named
	private Job existingJob;
	
	
	@NotNull(message = "Search value is required.")
	private String idQueryValue;
	
	public String getidQueryValue() {
		return idQueryValue;
	}
	
	public void setIdQueryValue(String idQueryValue) {
		this.idQueryValue = idQueryValue;
	}
	
	public void findJob() {
		try {
			existingJob = currentHumanResourceService.findOneJob(idQueryValue);
			if(existingJob !=null) {
				Messages.addGlobalInfo("Query successful");
			}else {
				Messages.addGlobalError("Query unsuccessful");
		}
		}catch (Exception e) {
			Messages.addGlobalError("Query unsucessful");
			Messages.addGlobalError("{0}", e.getMessage());	
		}
		
	}
	
	public String deleteJob() {
		String nextPage = null;
		try {
			currentHumanResourceService.deleteJob(existingJob);
			existingJob =null;
			idQueryValue = null;
			Messages.addGlobalInfo("Delete successful");
			nextPage = "viewShippers?faces-redirect=true";
		}catch (Exception e) {
			Messages.addGlobalInfo("Delete unsuccessful");
			Messages.addGlobalError("{0}", e.getMessage());
		}
		return nextPage;
	}
	
	public void cancel() {
		existingJob = null;
		idQueryValue = null;
	}
	
	
	public void findByQueryParameterId() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (idQueryValue != null && existingJob == null) {
				findJob();		
			}
		}
	}
}
