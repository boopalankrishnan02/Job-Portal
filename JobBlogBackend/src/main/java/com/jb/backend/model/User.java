package com.jb.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int u_id;
	private String u_phoneNumber;
	private String u_name;
	private String u_email;
	private String u_password;
	private String u_role;
	private boolean u_online;
	@Lob
	private byte[] u_profilePicture;
	
	
	public byte[] getU_profilePicture() {
		return u_profilePicture;
	}
	public void setU_profilePicture(byte[] u_profilePicture) {
		this.u_profilePicture = u_profilePicture;
	}
	public String getU_phoneNumber() {
		return u_phoneNumber;
	}
	public void setU_phoneNumber(String u_phoneNumber) {
		this.u_phoneNumber = u_phoneNumber;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	
	public String getU_role() {
		return u_role;
	}
	public void setU_role(String u_role) {
		this.u_role = u_role;
	}
	public boolean isU_online() {
		return u_online;
	}
	public void setU_online(boolean u_online) {
		this.u_online = u_online;
	}
	
	public int getU_id() {
		return u_id;
	}

}
