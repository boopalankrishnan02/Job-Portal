package com.jb.backend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jb.backend.dao.BlogPostDao;
import com.jb.backend.model.BlogPost;

@Repository("blogPostDao")
@Transactional
public class BlogPostDaoImpl implements BlogPostDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BlogPost getBlogPost(int b_id) {
		Session session = sessionFactory.getCurrentSession();
		BlogPost blogPost = session.get(BlogPost.class, b_id); //not asking for cast
		return blogPost;
	}

	@Override
	public void addBlogPost(BlogPost blogPost) {		// try catch
		Session session = sessionFactory.getCurrentSession();
		session.save(blogPost);

	}

	@Override
	public void updateBlogPost(BlogPost blogPost) {
		Session session = sessionFactory.getCurrentSession();
		session.update(blogPost);

	}

	@Override
	public void deleteBlogPost(BlogPost blogPost) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(blogPost);

	}

	@Override
	public List<BlogPost> listApprovedBlogPost() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from BlogPost where b_approvalStatus=true");
		@SuppressWarnings("unchecked")
		List<BlogPost> listapprovedblogpost = query.getResultList(); // list instead of resultlist
		return listapprovedblogpost;
	}

	@Override
	public List<BlogPost> listBlogWaitingforApproval() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from BlogPost where b_approvalStatus=false");
		@SuppressWarnings("unchecked")
		List<BlogPost> listblogwaitingforapproval = query.getResultList();
		return listblogwaitingforapproval;
	}

	@Override
	public void updateLikes(BlogPost blogPost) {
		Session session = sessionFactory.getCurrentSession();
		session.update(blogPost);

	}

}
