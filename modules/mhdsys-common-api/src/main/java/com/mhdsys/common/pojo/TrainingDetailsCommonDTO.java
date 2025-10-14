package com.mhdsys.common.pojo;

import java.util.Date;

public class TrainingDetailsCommonDTO {

// Primary Key
	private long trainingDetailsId;
// Custom Fields
	private String numberOfTrainings;
	private String trainingName;
	private Date trainingStartDate;
	private Date trainingEndDate;
	private long trainingCertificate;
	private String typeOfRecord;
// Common Fields
	private long userId;
	private Date createDate;
	private Date modifiedDate;
	private long establishmentDetailId;
// Utility Fields
	private String trainingStartDateStr;
	private String trainingEndDateStr;
	private String createDateStr;
	private String modifiedDateStr;
	private String trainingCertificateName;
	private String trainingCertificateURL;

	public String getTrainingCertificateName() {
		return trainingCertificateName;
	}

	public void setTrainingCertificateName(String trainingCertificateName) {
		this.trainingCertificateName = trainingCertificateName;
	}

	public long getTrainingDetailsId() {
		return trainingDetailsId;
	}

	public void setTrainingDetailsId(long trainingDetailsId) {
		this.trainingDetailsId = trainingDetailsId;
	}

	public String getNumberOfTrainings() {
		return numberOfTrainings;
	}

	public void setNumberOfTrainings(String numberOfTrainings) {
		this.numberOfTrainings = numberOfTrainings;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public Date getTrainingStartDate() {
		return trainingStartDate;
	}

	public void setTrainingStartDate(Date trainingStartDate) {
		this.trainingStartDate = trainingStartDate;
	}

	public Date getTrainingEndDate() {
		return trainingEndDate;
	}

	public void setTrainingEndDate(Date trainingEndDate) {
		this.trainingEndDate = trainingEndDate;
	}

	public long getTrainingCertificate() {
		return trainingCertificate;
	}

	public void setTrainingCertificate(long trainingCertificate) {
		this.trainingCertificate = trainingCertificate;
	}

	public String getTypeOfRecord() {
		return typeOfRecord;
	}

	public void setTypeOfRecord(String typeOfRecord) {
		this.typeOfRecord = typeOfRecord;
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

	public long getEstablishmentDetailId() {
		return establishmentDetailId;
	}

	public void setEstablishmentDetailId(long establishmentDetailId) {
		this.establishmentDetailId = establishmentDetailId;
	}

	
	public String getTrainingStartDateStr() {
		return trainingStartDateStr;
	}

	public void setTrainingStartDateStr(String trainingStartDateStr) {
		this.trainingStartDateStr = trainingStartDateStr;
	}

	public String getTrainingEndDateStr() {
		return trainingEndDateStr;
	}

	public void setTrainingEndDateStr(String trainingEndDateStr) {
		this.trainingEndDateStr = trainingEndDateStr;
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

	

	public String getTrainingCertificateURL() {
		return trainingCertificateURL;
	}

	public void setTrainingCertificateURL(String trainingCertificateURL) {
		this.trainingCertificateURL = trainingCertificateURL;
	}

	@Override
	public String toString() {
		return "TrainingDetailsCommonDTO [trainingDetailsId=" + trainingDetailsId + ", numberOfTrainings="
				+ numberOfTrainings + ", trainingName=" + trainingName + ", trainingStartDate=" + trainingStartDate
				+ ", trainingEndDate=" + trainingEndDate + ", trainingCertificate=" + trainingCertificate
				+ ", typeOfRecord=" + typeOfRecord + ", userId=" + userId + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + ", establishmentDetailId=" + establishmentDetailId
				+ ", trainingStartDateStr=" + trainingStartDateStr + ", trainingEndDateStr=" + trainingEndDateStr
				+ ", createDateStr=" + createDateStr + ", modifiedDateStr=" + modifiedDateStr
			    + ", trainingCertificateURL="
				+ trainingCertificateURL + "]";
	}

}
