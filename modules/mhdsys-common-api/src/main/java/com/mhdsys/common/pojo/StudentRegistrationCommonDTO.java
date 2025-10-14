package com.mhdsys.common.pojo;

import java.util.Date;

public class StudentRegistrationCommonDTO {
	private long studentRegistrationId;
	private String firstName;
	private String lastName;
	private String fathersName;
	private String mothersName;
	private String gender;
	private String aadharNumber;
	private String standard;
	private String schoolCollegeName;
	private long userId;
	private String transferTo;
	private String unitType;
	private Boolean transfer;
	private String registerTo;
	private Date createdDate;
	private Date modifiedDate;
	
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getTransferTo() {
		return transferTo;
	}
	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}
	
	public Boolean getTransfer() {
		return transfer;
	}
	public void setTransfer(Boolean transfer) {
		this.transfer = transfer;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getRegisterTo() {
		return registerTo;
	}
	public void setRegisterTo(String registerTo) {
		this.registerTo = registerTo;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public long getStudentRegistrationId() {
		return studentRegistrationId;
	}
	public void setStudentRegistrationId(long studentRegistrationId) {
		this.studentRegistrationId = studentRegistrationId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFathersName() {
		return fathersName;
	}
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}
	public String getMothersName() {
		return mothersName;
	}
	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getSchoolCollegeName() {
		return schoolCollegeName;
	}
	public void setSchoolCollegeName(String schoolCollegeName) {
		this.schoolCollegeName = schoolCollegeName;
	}

}
