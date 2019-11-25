package com.jb.backend.dao;

import java.util.List;

import com.jb.backend.model.Job;

public interface JobDao {
	public boolean addJob(Job job);
	public boolean deleteJob(Job job);
	public boolean updateJob(Job job);
	public List<Job> getJobs();
	public Job getJob(int jobId);
	public List<Job> getJobsByCategory(String category);
	
}
