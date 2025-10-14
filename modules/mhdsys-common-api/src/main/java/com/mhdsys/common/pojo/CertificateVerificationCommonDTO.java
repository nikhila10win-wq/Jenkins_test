package com.mhdsys.common.pojo;

import java.util.Date;
import java.util.List;

public class CertificateVerificationCommonDTO {
	private long certificateVerificationId;

    private String firstName;
    private String lastName;
    private String mothersName;
    private String fathersName;
    private int gender;

    private Date dateOfBirth;
    private String dateOfBirthStr;
    private String aadharNumber;

    private String certificate;
    private boolean approveReject;
    private String remarks;

    // Audit fields
    private long userId;
    private Date createDate;
    private Date modifiedDate;
    private String createDateStr;
    private String modifiedDateStr;
	
    
    private List<String> certificateURLs;
    private List<String> certificateNames;
    private List<String> certificateEntryIds;
	public long getCertificateVerificationId() {
		return certificateVerificationId;
	}
	public void setCertificateVerificationId(long certificateVerificationId) {
		this.certificateVerificationId = certificateVerificationId;
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public boolean isApproveReject() {
		return approveReject;
	}
	public void setApproveReject(boolean approveReject) {
		this.approveReject = approveReject;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getModifiedDateStr() {
		return modifiedDateStr;
	}
	public void setModifiedDateStr(String modifiedDateStr) {
		this.modifiedDateStr = modifiedDateStr;
	}
	public List<String> getCertificateURLs() {
		return certificateURLs;
	}
	public void setCertificateURLs(List<String> certificateURLs) {
		this.certificateURLs = certificateURLs;
	}
	public List<String> getCertificateNames() {
		return certificateNames;
	}
	public void setCertificateNames(List<String> certificateNames) {
		this.certificateNames = certificateNames;
	}
	public List<String> getCertificateEntryIds() {
		return certificateEntryIds;
	}
	public void setCertificateEntryIds(List<String> certificateEntryIds) {
		this.certificateEntryIds = certificateEntryIds;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfBirthStr() {
		return dateOfBirthStr;
	}
	public void setDateOfBirthStr(String dateOfBirthStr) {
		this.dateOfBirthStr = dateOfBirthStr;
	}
	@Override
	public String toString() {
		return "CertificateVerificationCommonDTO [certificateVerificationId=" + certificateVerificationId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", mothersName=" + mothersName
				+ ", fathersName=" + fathersName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", dateOfBirthStr=" + dateOfBirthStr + ", aadharNumber=" + aadharNumber + ", certificate="
				+ certificate + ", approveReject=" + approveReject + ", remarks=" + remarks + ", userId=" + userId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", createDateStr=" + createDateStr
				+ ", modifiedDateStr=" + modifiedDateStr + ", certificateURLs=" + certificateURLs
				+ ", certificateNames=" + certificateNames + ", certificateEntryIds=" + certificateEntryIds + "]";
	}
	
	
	
    
    
}
