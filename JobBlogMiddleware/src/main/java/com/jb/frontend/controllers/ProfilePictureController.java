package com.jb.frontend.controllers;

import java.net.URI;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jb.backend.dao.ProfilePictureDao;
import com.jb.backend.model.ErrorClass;
import com.jb.backend.model.ProfilePicture;

@RestController
public class ProfilePictureController {
	
	@Autowired
	private ProfilePictureDao profilePictureDao;
	
	@RequestMapping(value="/uploadimage", method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image, HttpSession session){
		System.out.println("Entering to the upload picutre");
		String u_phoneNumber=(String)session.getAttribute("loggedInUser");
		if(u_phoneNumber==null) {
			ErrorClass errorClass=new ErrorClass(4,"Uauthorized access.. please login.....");
			return new ResponseEntity<ErrorClass>(errorClass,HttpStatus.UNAUTHORIZED);
		}
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setMobileNumber(u_phoneNumber);
		profilePicture.setProfilePicture(image.getBytes());
		profilePictureDao.uploadProfilePicture(profilePicture);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "http://localhost:8080/BlogFrontend/#/updateProfile");
		return new ResponseEntity<byte[]>(null,headers,HttpStatus.FOUND);
	}
	
	@RequestMapping(value= {"/getimage/{u_phoneNumber}","/getimage/(u_phoneNumber)"}, method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@PathVariable String u_phoneNumber, HttpSession session) {
		System.out.println(u_phoneNumber);
		String authNumber = (String) session.getAttribute("loggedInUser");
		if(authNumber==null) {
			return null;
		}
		ProfilePicture profilePicture = profilePictureDao.getProfilePicture(u_phoneNumber);
		if(profilePicture==null) {
			return null;
		}
		else {
			return profilePicture.getProfilePicture();
		}
	}

}
