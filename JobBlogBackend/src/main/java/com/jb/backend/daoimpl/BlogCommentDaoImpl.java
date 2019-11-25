package com.jb.backend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jb.backend.dao.BlogCommentDao;
import com.jb.backend.model.BlogComment;

@Repository("blogCommentDao")
@Transactional
public class BlogCommentDaoImpl implements BlogCommentDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addBlogComment(BlogComment blogComment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(blogComment);

	}

	@Override
	public void deleteBlogComment(BlogComment blogComment) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(blogComment);

	}

	@Override
	public List<BlogComment> getBlogComment(int b_id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from BlogComment where blogPost.b_id=?0");
		query.setParameter(0, b_id);
		@SuppressWarnings("unchecked")
		List<BlogComment> blogcomments = query.getResultList();
		return blogcomments;
	}

}
