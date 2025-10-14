package com.mhdsys.common.pojo;

import com.mhdsys.common.api.profile.UserIdentifiable;

import java.util.Date;

public class ScoutAndGuideRegistrationCommonDTO  implements UserIdentifiable{
	private long scoutAndGuideRegistrationId;

	// Personal Details
	private String firstName;
	private String lastName;
	private String motherName;
	private String fatherName;
	private String gender;

	// Employee Details
	private String designation;
	private String schoolName;

	// Verification
	private String aadharNumber;
	private String emailId;
	private String mobileNumber;
	private long aadharCardFileEntryId;
	private String aadharCardFileURL;
	private String aadharCardFileName;

	// Audit Fields
	 private long userId;
	private Date createDate;
	private Date modifiedDate;
	
	public String getAadharCardFileURL() {
		return aadharCardFileURL;
	}
	public void setAadharCardFileURL(String aadharCardFileURL) {
		this.aadharCardFileURL = aadharCardFileURL;
	}
	public String getAadharCardFileName() {
		return aadharCardFileName;
	}
	public void setAadharCardFileName(String aadharCardFileName) {
		this.aadharCardFileName = aadharCardFileName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getScoutAndGuideRegistrationId() {
		return scoutAndGuideRegistrationId;
	}
	public void setScoutAndGuideRegistrationId(long scoutAndGuideRegistrationId) {
		this.scoutAndGuideRegistrationId = scoutAndGuideRegistrationId;
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
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public long getAadharCardFileEntryId() {
		return aadharCardFileEntryId;
	}
	public void setAadharCardFileEntryId(long aadharCardFileEntryId) {
		this.aadharCardFileEntryId = aadharCardFileEntryId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
