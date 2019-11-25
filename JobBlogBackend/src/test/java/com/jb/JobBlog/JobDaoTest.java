package com.jb.JobBlog;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jb.backend.dao.JobDao;
import com.jb.backend.model.Job;

public class JobDaoTest {
	private static JobDao jobDao;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.jb.backend");
		context.refresh();
		jobDao = (JobDao) context.getBean("jobDao");
	}
	
	@Test
	public void addJobTest() {
		Job j= new Job();
		j.setCompanyAddress("v sdijbvswjdn");
		jobDao.addJob(j);
		
	}
	
}
