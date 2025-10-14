package com.mhdsys.common.pojo;

import java.util.Date;
import java.util.List;

public class DistrictGrantSchemeCommonDTO {

	// Primary Key
	private long districtGrantSchemeId;

	// Grants Details
	private String category;
	private String subCategory;
	private String project;
	private String type;

	// Other Details
	private String details;
	private String geoTagPhotos;
	private List<String> geoTagPhotosURLs;
	private List<String> geoTagPhotosNames;
	private List<String> geoTagPhotosIds;
	private String latitude;
	private String longitude;

	// Required Documents
	private long dprDocument;
	private String dprDocumentURL;
	private String dprDocumentName;
	private String supportedDocuments;
	private List<String> supportedDocumentsURLs;
	private List<String> supportedDocumentsNames;
	private List<String> supportedDocumentsIds;

	// User Id
	private long userId;

	private String status;

	// Verification and Remarks
	private String hoVerification;
	private String hoRemarks;
	private String ddVerification;
	private String ddRemarks;

	// Date and Time
	private Date createDate;
	private Date modifiedDated;
	private String createDateStr;
	private String modifiedDatedStr;

	private String distributedFund;

	public long getDistrictGrantSchemeId() {
		return districtGrantSchemeId;
	}

	public void setDistrictGrantSchemeId(long districtGrantSchemeId) {
		this.districtGrantSchemeId = districtGrantSchemeId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getGeoTagPhotos() {
		return geoTagPhotos;
	}

	public void setGeoTagPhotos(String geoTagPhotos) {
		this.geoTagPhotos = geoTagPhotos;
	}

	public List<String> getGeoTagPhotosURLs() {
		return geoTagPhotosURLs;
	}

	public void setGeoTagPhotosURLs(List<String> geoTagPhotosURLs) {
		this.geoTagPhotosURLs = geoTagPhotosURLs;
	}

	public List<String> getGeoTagPhotosNames() {
		return geoTagPhotosNames;
	}

	public void setGeoTagPhotosNames(List<String> geoTagPhotosNames) {
		this.geoTagPhotosNames = geoTagPhotosNames;
	}

	public List<String> getGeoTagPhotosIds() {
		return geoTagPhotosIds;
	}

	public void setGeoTagPhotosIds(List<String> geoTagPhotosIds) {
		this.geoTagPhotosIds = geoTagPhotosIds;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public long getDprDocument() {
		return dprDocument;
	}

	public void setDprDocument(long dprDocument) {
		this.dprDocument = dprDocument;
	}

	public String getDprDocumentURL() {
		return dprDocumentURL;
	}

	public void setDprDocumentURL(String dprDocumentURL) {
		this.dprDocumentURL = dprDocumentURL;
	}

	public String getDprDocumentName() {
		return dprDocumentName;
	}

	public void setDprDocumentName(String dprDocumentName) {
		this.dprDocumentName = dprDocumentName;
	}

	public String getSupportedDocuments() {
		return supportedDocuments;
	}

	public void setSupportedDocuments(String supportedDocuments) {
		this.supportedDocuments = supportedDocuments;
	}

	public List<String> getSupportedDocumentsURLs() {
		return supportedDocumentsURLs;
	}

	public void setSupportedDocumentsURLs(List<String> supportedDocumentsURLs) {
		this.supportedDocumentsURLs = supportedDocumentsURLs;
	}

	public List<String> getSupportedDocumentsNames() {
		return supportedDocumentsNames;
	}

	public void setSupportedDocumentsNames(List<String> supportedDocumentsNames) {
		this.supportedDocumentsNames = supportedDocumentsNames;
	}

	public List<String> getSupportedDocumentsIds() {
		return supportedDocumentsIds;
	}

	public void setSupportedDocumentsIds(List<String> supportedDocumentsIds) {
		this.supportedDocumentsIds = supportedDocumentsIds;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHoVerification() {
		return hoVerification;
	}

	public void setHoVerification(String hoVerification) {
		this.hoVerification = hoVerification;
	}

	public String getHoRemarks() {
		return hoRemarks;
	}

	public void setHoRemarks(String hoRemarks) {
		this.hoRemarks = hoRemarks;
	}

	public String getDdVerification() {
		return ddVerification;
	}

	public void setDdVerification(String ddVerification) {
		this.ddVerification = ddVerification;
	}

	public String getDdRemarks() {
		return ddRemarks;
	}

	public void setDdRemarks(String ddRemarks) {
		this.ddRemarks = ddRemarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDated() {
		return modifiedDated;
	}

	public void setModifiedDated(Date modifiedDated) {
		this.modifiedDated = modifiedDated;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getModifiedDatedStr() {
		return modifiedDatedStr;
	}

	public void setModifiedDatedStr(String modifiedDatedStr) {
		this.modifiedDatedStr = modifiedDatedStr;
	}

	public String getDistributedFund() {
		return distributedFund;
	}

	public void setDistributedFund(String distributedFund) {
		this.distributedFund = distributedFund;
	}

	@Override
	public String toString() {
		return "DistrictGrantSchemeCommonDTO [districtGrantSchemeId=" + districtGrantSchemeId + ", category=" + category
				+ ", subCategory=" + subCategory + ", project=" + project + ", type=" + type + ", details=" + details
				+ ", geoTagPhotos=" + geoTagPhotos + ", geoTagPhotosURLs=" + geoTagPhotosURLs + ", geoTagPhotosNames="
				+ geoTagPhotosNames + ", geoTagPhotosIds=" + geoTagPhotosIds + ", latitude=" + latitude + ", longitude="
				+ longitude + ", dprDocument=" + dprDocument + ", dprDocumentURL=" + dprDocumentURL
				+ ", dprDocumentName=" + dprDocumentName + ", supportedDocuments=" + supportedDocuments
				+ ", supportedDocumentsURLs=" + supportedDocumentsURLs + ", supportedDocumentsNames="
				+ supportedDocumentsNames + ", supportedDocumentsIds=" + supportedDocumentsIds + ", userId=" + userId
				+ ", status=" + status + ", hoVerification=" + hoVerification + ", hoRemarks=" + hoRemarks
				+ ", ddVerification=" + ddVerification + ", ddRemarks=" + ddRemarks + ", createDate=" + createDate
				+ ", modifiedDated=" + modifiedDated + ", createDateStr=" + createDateStr + ", modifiedDatedStr="
				+ modifiedDatedStr + ", distributedFund=" + distributedFund + "]";
	}

}
