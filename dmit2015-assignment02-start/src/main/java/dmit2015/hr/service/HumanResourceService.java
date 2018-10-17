package dmit2015.hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import dmit2015.hr.entity.Job;
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

}
