package com.mhdsys.common.pojo;

import java.util.Date;

public class SportsFacilityBookingDTO {

	private long facilityBookingId;
	private long selectedFacility;
	private String type;
	private Date Date;
	private String batch;
	private String timeFrom;
	private String timeTo;
	private String sportCourt;
	private String name;
	private String contact;
	private String purpose;
	private String fees;
	private long medicalCertificate;

	private long creatorUserId;
	private long actionTakenByUserId;
	private Boolean isApproved;
	private String remarks;
	private long bookingStatus;

	private Date createDate;
	private Date modifiedDate;
	private String numberOfMonths;
	private String dailyOrDate;
	
	
	public long getFacilityBookingId() {
		return facilityBookingId;
	}
	public void setFacilityBookingId(long facilityBookingId) {
		this.facilityBookingId = facilityBookingId;
	}
	public long getSelectedFacility() {
		return selectedFacility;
	}
	public void setSelectedFacility(long selectedFacility) {
		this.selectedFacility = selectedFacility;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public String getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	public String getSportCourt() {
		return sportCourt;
	}
	public void setSportCourt(String sportCourt) {
		this.sportCourt = sportCourt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public long getMedicalCertificate() {
		return medicalCertificate;
	}
	public void setMedicalCertificate(long medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}
	public long getCreatorUserId() {
		return creatorUserId;
	}
	public void setCreatorUserId(long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}
	public long getActionTakenByUserId() {
		return actionTakenByUserId;
	}
	public void setActionTakenByUserId(long actionTakenByUserId) {
		this.actionTakenByUserId = actionTakenByUserId;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	public long getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(long bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getNumberOfMonths() {
		return numberOfMonths;
	}
	public void setNumberOfMonths(String numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}
	
	public String getDailyOrDate() {
		return dailyOrDate;
	}
	public void setDailyOrDate(String dailyOrDate) {
		this.dailyOrDate = dailyOrDate;
	}
	@Override
	public String toString() {
		return "SportsFacilityBookingDTO [facilityBookingId=" + facilityBookingId + ", selectedFacility="
				+ selectedFacility + ", type=" + type + ", Date=" + Date + ", batch=" + batch + ", timeFrom=" + timeFrom
				+ ", timeTo=" + timeTo + ", sportCourt=" + sportCourt + ", name=" + name + ", contact=" + contact
				+ ", purpose=" + purpose + ", fees=" + fees + ", medicalCertificate=" + medicalCertificate
				+ ", creatorUserId=" + creatorUserId + ", actionTakenByUserId=" + actionTakenByUserId + ", isApproved="
				+ isApproved + ", remarks=" + remarks + ", bookingStatus=" + bookingStatus + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + ", numberOfMonths=" + numberOfMonths
				+ ", dailyOrDate=" + dailyOrDate + "]";
	}

	
}
