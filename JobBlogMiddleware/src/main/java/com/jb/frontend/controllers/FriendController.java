package com.jb.frontend.controllers;

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

import com.jb.backend.dao.FriendDao;
import com.jb.backend.dao.UserDao;
import com.jb.backend.model.ErrorClass;
import com.jb.backend.model.Friend;
import com.jb.backend.model.User;

@RestController
public class FriendController {
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/suggestedUsers",method=RequestMethod.GET)
	public ResponseEntity<?> getSuggestedUsers(HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		List<User> suggestedUsers = friendDao.getSuggestedUsers(u_phoneNumber);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	}

	@RequestMapping(value="/friendRequest",method=RequestMethod.POST)
	public ResponseEntity<?> addFriendRequest(@RequestBody User friendRequestToId,HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		User fromId = userDao.getUserPn(u_phoneNumber);
		System.out.println("user id in friend controller:"+fromId.getU_id());
//		String tonum = friendRequestToId.getU_phoneNumber();
		Friend friend = new Friend();
		friend.setFromId(fromId);
		friend.setToId(friendRequestToId);
//		friend.setFu_phoneNumber(u_phoneNumber);
//		friend.setTu_phoneNumber(tonum);
		friend.setStatus('P');
		friendDao.addFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/pendingRequest",method=RequestMethod.GET)
	public ResponseEntity<?> getPendingRequest(HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		List<Friend> pendingRequest = friendDao.getPendingRequest(u_phoneNumber);
		return new ResponseEntity<List<Friend>>(pendingRequest,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptRequest",method=RequestMethod.PUT)
	public ResponseEntity<?> acceptFriendRequest(@RequestBody Friend friend, HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		friend.setStatus('A');
		friendDao.acceptFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteRequest",method=RequestMethod.PUT)
	public ResponseEntity<?> deleteFriendRequest(@RequestBody Friend friend, HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		friendDao.deleteFriendRequest(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/listOfFriends",method=RequestMethod.GET)
	public ResponseEntity<?> listFriends(HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		
		List<User> friends = friendDao.listOfFriends(u_phoneNumber);
		return new ResponseEntity<List<User>>(friends,HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteFriend/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteFriend(@PathVariable int id, HttpSession session){
		String u_phoneNumber = (String) session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorclass = new ErrorClass(4,"Unauthorized access....");
			return new ResponseEntity<ErrorClass>(errorclass,HttpStatus.UNAUTHORIZED);
		}
		System.out.println(id);
		User user = userDao.getUserPn(u_phoneNumber);
		int fid=user.getU_id();
		System.out.println(fid);
		friendDao.deleteFriend(id,fid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
