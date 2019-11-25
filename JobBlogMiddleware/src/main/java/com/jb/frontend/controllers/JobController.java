package com.jb.frontend.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.jb.backend.dao.JobDao;
import com.jb.backend.dao.UserDao;
import com.jb.backend.model.ErrorClass;
import com.jb.backend.model.Job;
import com.jb.backend.model.User;


@RestController
public class JobController {
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping(value="/addJob", method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
		
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		
		if(u_phoneNumber==null) {
			ErrorClass errorclass= new ErrorClass(4,"Unauthorized access.. please login....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		System.out.println("Entered saveJob method");
		
		User user = userDao.getUserPn(u_phoneNumber);
		String num = user.getU_phoneNumber();
		System.out.println("got user phone number");
//		if(!user.getU_role().equals("ADMIN")) {
//			ErrorClass errorclass = new ErrorClass(5,"Access denied...you are not authorized to post a job");
//			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
//		}
			
		try {
			System.out.println("add job dao");
			job.setPostedBy(num);
			job.setDatePostedOn(new Date());
			jobDao.addJob(job);
			System.out.println("added job");
		}
		catch(Exception e) {
			ErrorClass errorclass = new ErrorClass(6,"unable to post job details");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"unauthorized access");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs = jobDao.getJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getJob/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getJob(@PathVariable int id, HttpSession session){
		Job job = jobDao.getJob(id);
		if(job==null) {
			ErrorClass errorclass = new ErrorClass(6,"unable to get job");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/deleteJob/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteJob(@PathVariable int id,HttpSession session){
		
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access.. please login....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
//		User user = userDao.getUserPn(u_phoneNumber);
//		
//		if(!user.getU_role().equals("ADMIN")) {
//			ErrorClass errorclass = new ErrorClass(5,"Access denied...you are not authorized to post a job");
//			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
//		}
//		
		try {
			Job job = jobDao.getJob(id);
			jobDao.deleteJob(job);	
		}
		catch(Exception e) {
			ErrorClass errorclass= new ErrorClass(6,"unable to delete job");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateJobForm/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> updateJobForm(@PathVariable int id,HttpSession session){
		
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access.. please login....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
//		User user = userDao.getUserPn(u_phoneNumber);
//		
//		if(!user.getU_role().equals("ADMIN")) {
//			ErrorClass errorclass = new ErrorClass(5,"Access denied...you are not authorized to post a job");
//			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
//		}
		
		Job job = jobDao.getJob(id);
		
		if(job==null) {
			ErrorClass errorclass = new ErrorClass(6,"unable to get job");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/updatejob",method=RequestMethod.PUT)
	public ResponseEntity<?> updateJob(@RequestBody Job job,HttpSession session){
		
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access.. please login....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
//		User user = userDao.getUserPn(u_phoneNumber);
//		
//		if(!user.getU_role().equals("ADMIN")) {
//			ErrorClass errorclass = new ErrorClass(5,"Access denied...you are not authorized to post a job");
//			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
//		}
		
		try {
			job.setDatePostedOn(new Date());
			jobDao.updateJob(job);
		}
		catch(Exception e) {
			ErrorClass errorclass = new ErrorClass(6,"unable to delete job details");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
