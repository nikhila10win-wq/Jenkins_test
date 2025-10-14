package com.mhdsys.common.pojo;

import java.util.Date;

public class SportsFacilityMasterDTO {

	private long sportsFacilityId;
	private String facilityName;
	private String facilityType;
	private String facilityArea;
	private String longitude;
	private String latitude;
	private String geotagPhotos;
	private String type;
	private String fees;
	private String bookingUrl;
	private String contactPersonName;
	private String contactPersonNumber;

	private long creatorUserId;
	private long modifierUserId;

	private Date createDate;
	private Date modifiedDate;
	
	private boolean isUpdatedByHO;
	private boolean isUpdatedByDSO;
	
	private boolean hoAction;
	
	public boolean isHoAction() {
		return hoAction;
	}
	public void setHoAction(boolean hoAction) {
		this.hoAction = hoAction;
	}
	
	public long getSportsFacilityId() {
		return sportsFacilityId;
	}
	public void setSportsFacilityId(long sportsFacilityId) {
		this.sportsFacilityId = sportsFacilityId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	public String getFacilityArea() {
		return facilityArea;
	}
	public void setFacilityArea(String facilityArea) {
		this.facilityArea = facilityArea;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getGeotagPhotos() {
		return geotagPhotos;
	}
	public void setGeotagPhotos(String geotagPhotos) {
		this.geotagPhotos = geotagPhotos;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getBookingUrl() {
		return bookingUrl;
	}
	public void setBookingUrl(String bookingUrl) {
		this.bookingUrl = bookingUrl;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactPersonNumber() {
		return contactPersonNumber;
	}
	public void setContactPersonNumber(String contactPersonNumber) {
		this.contactPersonNumber = contactPersonNumber;
	}
	public long getCreatorUserId() {
		return creatorUserId;
	}
	public void setCreatorUserId(long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}
	public long getModifierUserId() {
		return modifierUserId;
	}
	public void setModifierUserId(long modifierUserId) {
		this.modifierUserId = modifierUserId;
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
	public boolean getIsUpdatedByHO() {
		return isUpdatedByHO;
	}
	public void setIsUpdatedByHO(boolean isUpdatedByHO) {
		this.isUpdatedByHO = isUpdatedByHO;
	}
	public boolean getIsUpdatedByDSO() {
		return isUpdatedByDSO;
	}
	public void setIsUpdatedByDSO(boolean isUpdatedByDSO) {
		this.isUpdatedByDSO = isUpdatedByDSO;
	}
	@Override
	public String toString() {
		return "SportsFacilityMasterDTO [sportsFacilityId=" + sportsFacilityId + ", facilityName=" + facilityName
				+ ", facilityType=" + facilityType + ", facilityArea=" + facilityArea + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", geotagPhotos=" + geotagPhotos + ", type=" + type + ", fees=" + fees
				+ ", bookingUrl=" + bookingUrl + ", contactPersonName=" + contactPersonName + ", contactPersonNumber="
				+ contactPersonNumber + ", creatorUserId=" + creatorUserId + ", modifierUserId=" + modifierUserId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", isUpdatedByHO=" + isUpdatedByHO
				+ ", isUpdatedByDSO=" + isUpdatedByDSO + ", hoAction=" + hoAction + "]";
	}
	
	

}
