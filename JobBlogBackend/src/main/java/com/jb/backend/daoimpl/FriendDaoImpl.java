package com.jb.backend.daoimpl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jb.backend.dao.FriendDao;
import com.jb.backend.model.Friend;
import com.jb.backend.model.User;

@Repository("friendDao")
@Transactional
public class FriendDaoImpl implements FriendDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getSuggestedUsers(String u_phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
//		String queryString="select * from User where u_phoneNumber in(select u_phoneNumber from User where u_phoneNumber!=?0 "
//				+ "minus "
//				+ "(select fromId_u_id from Friend where toId.u_phoneNumber=?1 "
//				+ "union "
//				+ "select toId_u_phoneNumber from Friend where fromId.u_phoneNumber=?2))";
//		
		String queryString ="select *from user where u_id in "
				+ "((select u_id from user where u_phoneNumber!=?0 and u_role!='ADMIN') "
				+ "minus (select fromid_u_id from friend where toid_u_id ="
				+ "(select u_id from user where u_phoneNumber=?1)"
				+ " union select toid_u_id from friend where fromid_u_id ="
				+ "(select u_id from user where u_phoneNumber=?2)))";
		
		
		@SuppressWarnings("rawtypes")
		NativeQuery query=session.createSQLQuery(queryString);
		query.setParameter(0, u_phoneNumber);
		query.setParameter(1, u_phoneNumber);
		query.setParameter(2, u_phoneNumber);
		query.addEntity(User.class);
		return query.list();
		
		
//		@SuppressWarnings("rawtypes")
//		Query query = session.createQuery("from User where u_role='USER'");
//		return query.getResultList();
		
	}

	@Override
	public void addFriendRequest(Friend friend) {
		System.out.println("Friend Id : " + friend.getId());
		Session session= sessionFactory.getCurrentSession();
		User user = friend.getFromId();
		System.out.println("from id :" + user.getU_phoneNumber());
		System.out.println("user id"+ user.getU_id());
		session.save(friend);

	}

	@Override
	public List<Friend> getPendingRequest(String u_phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Friend f where f.toId.u_phoneNumber=?0 and f.status =?1");
		query.setParameter(0, u_phoneNumber);
		query.setParameter(1, 'P');
		@SuppressWarnings("unchecked")
		List<Friend> pendingRequest = query.getResultList();
		return pendingRequest;
	}

	@Override
	public void acceptFriendRequest(Friend friend) {
		Session session = sessionFactory.getCurrentSession();
		session.update(friend);
	}

	@Override
	public void deleteFriendRequest(Friend friend) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(friend);

	}

	@Override
	public List<User> listOfFriends(String u_phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query1 = session.createQuery("select f.toId from Friend f where f.fromId.u_phoneNumber=?0 and status=?1");
		query1.setParameter(0, u_phoneNumber);
		query1.setParameter(1, 'A');
		@SuppressWarnings("unchecked")
		List<User> list1 = query1.getResultList();
		
		@SuppressWarnings("rawtypes")
		Query query2 = session.createQuery("select f.fromId from Friend f where f.toId.u_phoneNumber=?0 and status=?1");
		query2.setParameter(0, u_phoneNumber);
		query2.setParameter(1, 'A');
		@SuppressWarnings("unchecked")
		List<User> list2 = query2.getResultList();
		list1.addAll(list2);
		return list1;
	}
	
	@Override
	public void deleteFriend(int id,int fid) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "delete from friend where ((fromid_u_id=?0 and toid_u_id=?1) or (fromid_u_id=?0 and toid_u_id=?1))";				
		@SuppressWarnings("rawtypes")
		NativeQuery query=session.createSQLQuery(queryString);
		query.setParameter(0, id);
		query.setParameter(1, fid);
		query.executeUpdate();
		return;
	}
}
