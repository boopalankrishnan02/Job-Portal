package com.jb.backend.dao;

import java.util.List;

import com.jb.backend.model.BlogComment;

public interface BlogCommentDao {
		void addBlogComment(BlogComment blogComment);
		void deleteBlogComment(BlogComment blogComment);
		List<BlogComment> getBlogComment(int b_id);
}