package com.jb.backend.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jb.backend.dao.ProfilePictureDao;
import com.jb.backend.model.ProfilePicture;

@Repository("profilePictureDao")
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void uploadProfilePicture(ProfilePicture profilePicture) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);

	}

	@Override
	public ProfilePicture getProfilePicture(String mobileNumber) {
		Session session = sessionFactory.getCurrentSession();
		ProfilePicture profilepicture = session.get(ProfilePicture.class, mobileNumber);
		return profilepicture;
	}

}
