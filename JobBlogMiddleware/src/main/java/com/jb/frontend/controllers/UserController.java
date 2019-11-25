package com.jb.frontend.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jb.backend.dao.UserDao;
import com.jb.backend.model.ErrorClass;
import com.jb.backend.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	public UserController() {
		System.out.println("User controller bean created");
}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user){
		try {
			if(!userDao.isU_NumberUnique(user.getU_phoneNumber())) {
				ErrorClass errorclass = new ErrorClass(2,"phone number already exists");
				return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			user.setU_role("USER");
			userDao.addUser(user);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		}
		catch(Exception e) {
			ErrorClass errorclass = new ErrorClass(1,"Something went wrong"+e.getMessage());
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.PUT)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
		
		System.out.println("Entered login");
		User validUser = userDao.login(user);
		
		if(validUser==null) {
			System.out.println("login phone number or password invalid");
			ErrorClass errorclass = new ErrorClass(4,"Invalid phoneNumber or password");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		else {
			System.out.println("login success");
			validUser.setU_online(true);
			userDao.updateUser(validUser);
			session.setAttribute("loggedInUser", validUser.getU_phoneNumber());
			System.out.println("Session Attribute : "+session.getAttribute("loggedInUser"));
			System.out.println("Session id : "+session.getId());
			System.out.println("Created on : "+session.getCreationTime());
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}	
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session) {
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		System.out.println("Entered logout method for user "+u_phoneNumber);
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"unauthorized access..please login....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		User user = userDao.getUserPn(u_phoneNumber);
		user.setU_online(false);
		userDao.updateUser(user);
		session.removeAttribute("loggedInUser");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateProfile",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfile(@RequestBody User user, HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"unauthorized access..please login....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		try {
			userDao.updateUser(user);
		}
		catch(Exception e) {
			ErrorClass errorclass = new ErrorClass(5,"unable to update user details....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
