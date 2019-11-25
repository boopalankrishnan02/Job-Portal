package com.jb.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
public class ProfilePicture {
		@Id
		private String mobileNumber;
		@Lob
		private byte[] profilePicture;
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public byte[] getProfilePicture() {
			return profilePicture;
		}
		public void setProfilePicture(byte[] profilePicture) {
			this.profilePicture = profilePicture;
		}
		
}
