package com.jb.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table
public class BlogPost {
		@Id
		@GeneratedValue
		private int b_id;
		private String b_title;
		@Lob
		private String b_content;
		private Date b_datePostedOn;
		private String b_postedBy;
		private String b_postedByUn;
		
		private boolean b_approvalStatus;
		private int likes;
		private int dislikes;
		
		public int getB_id() {
			return b_id;
		}
		public void setB_id(int b_id) {
			this.b_id = b_id;
		}
		public String getB_title() {
			return b_title;
		}
		public void setB_title(String b_title) {
			this.b_title = b_title;
		}
		public String getB_content() {
			return b_content;
		}
		public void setB_content(String b_content) {
			this.b_content = b_content;
		}
		public Date getB_datePostedOn() {
			return b_datePostedOn;
		}
		public void setB_datePostedOn(Date b_datePostedOn) {
			this.b_datePostedOn = b_datePostedOn;
		}
		
		public boolean isB_approvalStatus() {
			return b_approvalStatus;
		}
		public String getB_postedBy() {
			return b_postedBy;
		}
		public void setB_postedBy(String b_postedBy) {
			this.b_postedBy = b_postedBy;
		}
		public String getB_postedByUn() {
			return b_postedByUn;
		}
		public void setB_postedByUn(String b_postedByUn) {
			this.b_postedByUn = b_postedByUn;
		}
		public void setB_approvalStatus(boolean b_approvalStatus) {
			this.b_approvalStatus = b_approvalStatus;
		}
		public int getLikes() {
			return likes;
		}
		public void setLikes(int likes) {
			this.likes = likes;
		}
		public int getDislikes() {
			return dislikes;
		}
		public void setDislikes(int dislikes) {
			this.dislikes = dislikes;
		}
		
		
}
