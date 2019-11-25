package com.jb.backend.dao;

import java.util.List;

import com.jb.backend.model.BlogPost;

public interface BlogPostDao {
	public BlogPost getBlogPost(int b_id);
	void addBlogPost(BlogPost blogPost);
	void updateBlogPost(BlogPost blogPost);
	void deleteBlogPost(BlogPost blogPost);
	List<BlogPost> listApprovedBlogPost();
	List<BlogPost> listBlogWaitingforApproval();
	void updateLikes(BlogPost blogPost);
	
	
}
