package dmit2015.hr.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dmit2015.hr.entity.Job;
import dmit2015.hr.service.HumanResourceService;


@Named
@ViewScoped
public class JobViewController implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private HumanResourceService currenHumanResourceService;
	
	private List<Job> jobs;
	
	@PostConstruct
	public void retreiveAllJobs() {
		jobs = currenHumanResourceService.findAllJob();
	}
	
	@Produces
	@Named
	public List<Job> getJobs(){
		return jobs;
	}
	
	public void onJobListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Job job) {
		retreiveAllJobs();
	}
}
