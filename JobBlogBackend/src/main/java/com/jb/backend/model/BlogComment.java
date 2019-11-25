package com.jb.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class BlogComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bc_id;
	@ManyToOne
	private BlogPost blogPost;
	private String commentedBy;
	private String u_number;
	private String bc_text;
	private Date bc_postedOn;
	public int getBc_id() {
		return bc_id;
	}
	public void setBc_id(int bc_id) {
		this.bc_id = bc_id;
	}
	public BlogPost getBlogPost() {
		return blogPost;
	}
	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}
	public String getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}
	
	public String getU_number() {
		return u_number;
	}
	public void setU_number(String u_number) {
		this.u_number = u_number;
	}
	public String getBc_text() {
		return bc_text;
	}
	public void setBc_text(String bc_text) {
		this.bc_text = bc_text;
	}
	public Date getBc_postedOn() {
		return bc_postedOn;
	}
	public void setBc_postedOn(Date bc_postedOn) {
		this.bc_postedOn = bc_postedOn;
	}
	
	
	
}
