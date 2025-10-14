package com.mhdsys.common.pojo;

import java.util.Date;
import java.util.List;

public class EventCertificateCommonDTO {

	    private long eventCertificateId;

	    private String year;
	    private Date eventDate;
	    private String eventName;
	    private String address;

	    private String certificateFiles;

	    private List<String> certificateFileNames; // Optional helper field
	    private List<String> certificateURLs;
	    // Audit fields
	    private long userId;
	    private Date createDate;
	    private Date modifiedDate;
	    private String eventDateStr;
	    
		public List<String> getCertificateURLs() {
			return certificateURLs;
		}
		public void setCertificateURLs(List<String> certificateURLs) {
			this.certificateURLs = certificateURLs;
		}
		public String getEventDateStr() {
			return eventDateStr;
		}
		public void setEventDateStr(String eventDateStr) {
			this.eventDateStr = eventDateStr;
		}
		public long getEventCertificateId() {
			return eventCertificateId;
		}
		public void setEventCertificateId(long eventCertificateId) {
			this.eventCertificateId = eventCertificateId;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public Date getEventDate() {
			return eventDate;
		}
		public void setEventDate(Date eventDate) {
			this.eventDate = eventDate;
		}
		public String getEventName() {
			return eventName;
		}
		public void setEventName(String eventName) {
			this.eventName = eventName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCertificateFiles() {
			return certificateFiles;
		}
		public void setCertificateFiles(String certificateFiles) {
			this.certificateFiles = certificateFiles;
		}
		public List<String> getCertificateFileNames() {
			return certificateFileNames;
		}
		public void setCertificateFileNames(List<String> certificateFileNames) {
			this.certificateFileNames = certificateFileNames;
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
