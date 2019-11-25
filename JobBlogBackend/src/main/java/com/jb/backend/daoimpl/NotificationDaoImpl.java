package com.jb.backend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jb.backend.dao.NotificationDao;
import com.jb.backend.model.Notification;

@Repository("notificationDao")
@Transactional
public class NotificationDaoImpl implements NotificationDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Notification getNotification(int n_id) {
		Session session = sessionFactory.getCurrentSession();
		Notification notification =session.get(Notification.class, n_id);
		return notification;
	}

	@Override
	public void addNotification(Notification notification) {
		Session session = sessionFactory.getCurrentSession();
		session.save(notification);;

	}

	@Override
	public void updateNotification(int n_id) {
		Session session =sessionFactory.getCurrentSession();
		Notification notification = session.get(Notification.class, n_id);
		notification.setViewed(true);
		session.update(notification);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotificationNotViewed(String u_phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Notification where viewed=0 and u_phoneNumber=?");
		query.setParameter(0, u_phoneNumber);
		return query.getResultList();
	}

}
