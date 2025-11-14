package com.nt.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name = "JR701_CITIZEN_APPLICATION")
public class CitizenAppRegistrationEntity 
{
	
   @Id
   @SequenceGenerator(name="gen1",sequenceName = "APP_ID_SEQ",initialValue = 1000,allocationSize = 1)
   @GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
   private Integer appId;
   @Column(length = 30)
   private String fullName;
   @Column(length = 30)
   private String email;
   @Column(length = 1)
   private String gender;
   @Column(length = 30)
   private Long phNo;
   @Column(length = 9)
   private Long ssn;
   @Column(length = 30)
   private String stateName;
   @Column(length = 30)
   private LocalDate dob;
   @Column(length = 30)
   private String createdBy;
   @Column(length = 30)
   private String updatedBy;
   @CreationTimestamp
   @Column(updatable = false)
   private LocalDate crationDate;
   @UpdateTimestamp
   @Column(insertable = false)
   private LocalDate updationDate;
public Integer getAppId() {
	return appId;
}
public void setAppId(Integer appId) {
	this.appId = appId;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public Long getPhNo() {
	return phNo;
}
public void setPhNo(Long phNo) {
	this.phNo = phNo;
}
public Long getSsn() {
	return ssn;
}
public void setSsn(Long ssn) {
	this.ssn = ssn;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
public LocalDate getDob() {
	return dob;
}
public void setDob(LocalDate dob) {
	this.dob = dob;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public String getUpdatedBy() {
	return updatedBy;
}
public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}
public LocalDate getCrationDate() {
	return crationDate;
}
public void setCrationDate(LocalDate crationDate) {
	this.crationDate = crationDate;
}
public LocalDate getUpdationDate() {
	return updationDate;
}
public void setUpdationDate(LocalDate updationDate) {
	this.updationDate = updationDate;
}
   
   
   
   
}
