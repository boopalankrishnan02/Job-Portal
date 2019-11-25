package com.jb.backend.model;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table
public class Friend {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private User fromId;
//	private String Fu_phoneNumber;
	@ManyToOne
	private User toId;
//	private String Tu_phoneNumber;
	private char status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getFromId() {
		return fromId;
	}
	public void setFromId(User fromId) {
		this.fromId = fromId;
	}
	public User getToId() {
		return toId;
	}
	public void setToId(User toId) {
		this.toId = toId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
//	public String getFu_phoneNumber() {
//		return Fu_phoneNumber;
//	}
//	public void setFu_phoneNumber(String fu_phoneNumber) {
//		Fu_phoneNumber = fu_phoneNumber;
//	}
//	public String getTu_phoneNumber() {
//		return Tu_phoneNumber;
//	}
//	public void setTu_phoneNumber(String tu_phoneNumber) {
//		Tu_phoneNumber = tu_phoneNumber;
//	}
	
	
}
