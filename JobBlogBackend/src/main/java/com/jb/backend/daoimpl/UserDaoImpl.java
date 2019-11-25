package com.jb.backend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jb.backend.dao.UserDao;
import com.jb.backend.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
			
			sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<User> listUsers() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public User getUser(String u_phoneNumber) {
		Session session = sessionFactory.openSession();
		User user = session.get(User.class, u_phoneNumber);
		session.close();
		return user;
	}

	@Override
	public boolean isU_NumberUnique(String u_phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from User where u_phoneNumber=?0");
		query.setParameter(0, u_phoneNumber);
		User user = (User) query.uniqueResult();
		if(user!=null) {
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from User where u_phoneNumber=?0 and u_password=?1");
		query.setParameter(0, user.getU_phoneNumber());
		query.setParameter(1, user.getU_password());
		return (User) query.uniqueResult();
	}

	@Override
	public User getUserPn(String u_phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from User where u_phoneNumber=?0");
		query.setParameter(0, u_phoneNumber);
		return (User) query.uniqueResult();
	}

}
