package com.jb.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Job {
	@Id
	@GeneratedValue
	private int jobId;
	private String companyName;//
	private String category;
	private String jobTilte;//
	private String jobDescription;//
	private String skillsRequired;//
	private String education;//
	private int experienceFrom;//
	private int experienceTo;//
	private String jobLocation;//
	private int salary;//
	private String employementType;//
	private String companyAddress;
	private String companyNumber;
	private String companyEmail;
	private int vaccancies;
	private Date datePostedOn;
	private String postedBy;
	
	public int getExperienceFrom() {
		return experienceFrom;
	}
	public void setExperienceFrom(int experienceFrom) {
		this.experienceFrom = experienceFrom;
	}
	public int getExperienceTo() {
		return experienceTo;
	}
	public void setExperienceTo(int experienceTo) {
		this.experienceTo = experienceTo;
	}
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getJobTilte() {
		return jobTilte;
	}
	public void setJobTilte(String jobTilte) {
		this.jobTilte = jobTilte;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getSkillsRequired() {
		return skillsRequired;
	}
	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getEmployementType() {
		return employementType;
	}
	public void setEmployementType(String employementType) {
		this.employementType = employementType;
	}
	
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public String getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}
	public int getVaccancies() {
		return vaccancies;
	}
	public void setVaccancies(int vaccancies) {
		this.vaccancies = vaccancies;
	}
	public Date getDatePostedOn() {
		return datePostedOn;
	}
	public void setDatePostedOn(Date datePostedOn) {
		this.datePostedOn = datePostedOn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	
	
	
}
