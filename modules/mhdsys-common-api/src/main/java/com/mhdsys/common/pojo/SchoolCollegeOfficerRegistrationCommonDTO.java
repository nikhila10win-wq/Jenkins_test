package com.mhdsys.common.pojo;

import java.util.Date;

public class SchoolCollegeOfficerRegistrationCommonDTO {
	 private long schoolCollegeOfficerRegistrationId;
	    private String firstName;
	    private String lastName;
	    private String mothersName;
	    private String fathersName;
	    private long currentDesignation;
	    private long gender;
	    private String schoolOrCollegeName;
	    private String aadharNumber;
	    private String emailId;
	    private String mobileNumber;
	    private String type;
	    private long aadharCardRecieptFileEntryId;
	    private long userId;
	    private Date createDate;
	    private Date modifiedDate;
	    
	    //modification
	    private String currentDesignationName;
	    
	    
	    
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public long getGender() {
			return gender;
		}
		public void setGender(long gender) {
			this.gender = gender;
		}
		public String getCurrentDesignationName() {
			return currentDesignationName;
		}
		public void setCurrentDesignationName(String currentDesignationName) {
			this.currentDesignationName = currentDesignationName;
		}
		public long getSchoolCollegeOfficerRegistrationId() {
			return schoolCollegeOfficerRegistrationId;
		}
		public void setSchoolCollegeOfficerRegistrationId(long schoolCollegeOfficerRegistrationId) {
			this.schoolCollegeOfficerRegistrationId = schoolCollegeOfficerRegistrationId;
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
		public String getMothersName() {
			return mothersName;
		}
		public void setMothersName(String mothersName) {
			this.mothersName = mothersName;
		}
		public String getFathersName() {
			return fathersName;
		}
		public void setFathersName(String fathersName) {
			this.fathersName = fathersName;
		}
		public long getCurrentDesignation() {
			return currentDesignation;
		}
		public void setCurrentDesignation(long currentDesignation) {
			this.currentDesignation = currentDesignation;
		}
		public String getSchoolOrCollegeName() {
			return schoolOrCollegeName;
		}
		public void setSchoolOrCollegeName(String schoolOrCollegeName) {
			this.schoolOrCollegeName = schoolOrCollegeName;
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
		public long getAadharCardRecieptFileEntryId() {
			return aadharCardRecieptFileEntryId;
		}
		public void setAadharCardRecieptFileEntryId(long aadharCardRecieptFileEntryId) {
			this.aadharCardRecieptFileEntryId = aadharCardRecieptFileEntryId;
		}
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
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
