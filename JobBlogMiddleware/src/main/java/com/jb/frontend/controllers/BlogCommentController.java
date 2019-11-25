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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.backend.dao.BlogCommentDao;
import com.jb.backend.dao.UserDao;
import com.jb.backend.model.BlogComment;
import com.jb.backend.model.BlogPost;
import com.jb.backend.model.ErrorClass;
import com.jb.backend.model.User;

@RestController
public class BlogCommentController {
	
	@Autowired
	private BlogCommentDao blogCommentDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogPost blogPost,@RequestParam String commentTxt,HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access.....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUserPn(u_phoneNumber);
		String name = user.getU_name();
		String u_number = user.getU_phoneNumber();
		BlogComment blogComment = new BlogComment();
		blogComment.setBc_text(commentTxt);
		blogComment.setBlogPost(blogPost);
		blogComment.setCommentedBy(name);
		blogComment.setU_number(u_number);
		blogComment.setBc_postedOn(new Date());
		blogCommentDao.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getComments/{blogPostId}",method=RequestMethod.GET)
	public ResponseEntity<?> getComments(@PathVariable int blogPostId,HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments = blogCommentDao.getBlogComment(blogPostId);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/deleteComment",method=RequestMethod.PUT)
	public ResponseEntity<?> deleteBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		blogCommentDao.deleteBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
	}
}
