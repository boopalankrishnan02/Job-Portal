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

import com.jb.backend.dao.BlogPostDao;
import com.jb.backend.dao.UserDao;
import com.jb.backend.model.BlogPost;
import com.jb.backend.model.ErrorClass;
import com.jb.backend.model.User;

@RestController
public class BlogPostController {
	
	@Autowired
	private BlogPostDao blogPostDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/addBlogPost",method= RequestMethod.POST)
	public ResponseEntity<?> addBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass=new ErrorClass(5,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		User user = userDao.getUserPn(u_phoneNumber);
		String number = user.getU_phoneNumber();
		String name = user.getU_name();
		blogPost.setB_datePostedOn(new Date());
		blogPost.setB_postedBy(number);;
		blogPost.setB_postedByUn(name);
		try {
			blogPostDao.addBlogPost(blogPost);
		}
		catch(Exception e) {
			ErrorClass errorclass=new ErrorClass(6,"Unable to post blog"+e.getMessage());
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/approvedBlogs",method=RequestMethod.GET)
	public ResponseEntity<?> getApprovedBlogs(HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(5,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> approvedBlogs = blogPostDao.listApprovedBlogPost();
		System.out.println("List of Blogs "+approvedBlogs);
		return new ResponseEntity<List<BlogPost>>(approvedBlogs,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getBlog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlog(@PathVariable int id,HttpSession session){
		
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(5,"Unauthorized access...");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		BlogPost blogPost = blogPostDao.getBlogPost(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/blogsWaitingApproval",method=RequestMethod.GET)
	public ResponseEntity<?> blogsWaitingApproval(HttpSession session){
		
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(5,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUserPn(u_phoneNumber);
		System.out.println(user.getU_role()=="ADMIN");
		System.out.println("ADMIN"=="ADMIN");
		System.out.println(user.getU_role().equals("ADMIN"));
		if(!user.getU_role().equals("ADMIN")) {
			ErrorClass errorclass = new ErrorClass(6,"Access denied....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> blogswaitingapproval = blogPostDao.listBlogWaitingforApproval();
		return new ResponseEntity<List<BlogPost>>(blogswaitingapproval,HttpStatus.OK);
	}
	
	@RequestMapping(value="/approveBlogPost",method=RequestMethod.PUT)
	public ResponseEntity<?> approveBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(5,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUserPn(u_phoneNumber);
		if(!user.getU_role().equals("ADMIN")) {
			ErrorClass errorclass = new ErrorClass(6,"Access denied....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		blogPost.setB_approvalStatus(true);
		blogPostDao.updateBlogPost(blogPost);		//notifications
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/updateBlogPost",method=RequestMethod.PUT)
		public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
			String u_phoneNumber = (String) session.getAttribute("loggedInUser");
			if(u_phoneNumber==null) {
				ErrorClass errorclass = new ErrorClass(5,"Unauthorized access....");
				return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
			}
			blogPost.setB_approvalStatus(false);
			blogPost.setB_datePostedOn(new Date());
			blogPostDao.updateBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
	
	@RequestMapping(value="/rejectBlogPost",method=RequestMethod.PUT)
	public ResponseEntity<?> rejectBlogPost(@RequestBody BlogPost blogPost, HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(5,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
//		User user = userDao.getUserPn(u_phoneNumber);
//		if(!user.getU_role().equals("ADMIN")) {
//			ErrorClass errorclass = new ErrorClass(6,"Access denied....");
//			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
//		}
		//notifcation
		blogPostDao.deleteBlogPost(blogPost);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/like",method=RequestMethod.PUT)
	public ResponseEntity<?> blike(@RequestBody BlogPost blogPost,HttpSession session){
		
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(5,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		blogPost.setLikes(blogPost.getLikes()+1);
		blogPostDao.updateLikes(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/dislike",method=RequestMethod.PUT)
	public ResponseEntity<?> bdislike(@RequestBody BlogPost blogPost,HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(5,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		blogPost.setDislikes(blogPost.getDislikes()+1);
		blogPostDao.updateLikes(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
}
