package dmit2015.hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import dmit2015.hr.entity.Job;
import dmit2015.hr.entity.Location;
@Stateless
public class HumanResourceService {

	@Inject
	private EntityManager entityManager;
	
	
	public List<Job> findAllJob() {
		
		return entityManager.createQuery("SELECT j FROM Job J ORDER BY j.jobTitle",Job.class).getResultList();
	}


	public void addJob(Job newJob) {
		entityManager.persist(newJob);
		
	}


	public Job findOneJob(@NotNull(message = "Search value is required.") String idQueryValue) {
		
		return entityManager.find(Job.class, idQueryValue);
	}


	public void updateJob(Job existingJob) {
		entityManager.merge(existingJob);
		
	}


	public void deleteJob(Job existingJob) throws Exception{
		existingJob = entityManager.merge(existingJob);
		if(existingJob.getEmployees().size()>0) {
			throw new Exception("You are not allowed to delete a Job with existing employees.");
		}
		entityManager.remove(existingJob);
	}


	public void addLocation(Location newLocation) {
		entityManager.persist(newLocation);
		
	}

}
