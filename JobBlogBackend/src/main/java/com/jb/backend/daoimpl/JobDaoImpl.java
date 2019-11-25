package com.jb.backend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jb.backend.dao.JobDao;
import com.jb.backend.model.Job;

@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}

	}

	@Override
	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<Job> getJobs() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Job");
		@SuppressWarnings("unchecked")
		List<Job> jobs = query.getResultList();
		return jobs;
		
		
	}

	@Override
	public Job getJob(int jobId) {
		Session session = sessionFactory.openSession();
		Job job = session.get(Job.class, jobId);
		session.close();
		return job;

	}

	@Override
	public List<Job> getJobsByCategory(String category) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Job where category=?0");
		query.setParameter(0, category);
		@SuppressWarnings("unchecked")
		List<Job> jobs = query.getResultList();
		return jobs;
	}

}
