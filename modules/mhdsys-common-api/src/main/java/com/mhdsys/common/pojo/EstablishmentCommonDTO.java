package com.mhdsys.common.pojo;

import java.util.Date;

public class EstablishmentCommonDTO {
	private long establishmentDetailId;
	private boolean personalDetails;
	private boolean serviceDetails;
	private boolean trainingDetails;
	private boolean npsDetails;
	private boolean gpfDetails;
	private boolean postingStatus;
	private boolean roasterStatus;

	private long userId;
	private Date createDate;
	private Date modifiedDate;
	private String createDateStr;
	private String modifiedDateStr;

	public long getEstablishmentDetailId() {
		return establishmentDetailId;
	}

	public void setEstablishmentDetailId(long establishmentDetailId) {
		this.establishmentDetailId = establishmentDetailId;
	}

	public boolean isPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(boolean personalDetails) {
		this.personalDetails = personalDetails;
	}

	public boolean isServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(boolean serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public boolean isTrainingDetails() {
		return trainingDetails;
	}

	public void setTrainingDetails(boolean trainingDetails) {
		this.trainingDetails = trainingDetails;
	}

	public boolean isNpsDetails() {
		return npsDetails;
	}

	public void setNpsDetails(boolean npsDetails) {
		this.npsDetails = npsDetails;
	}

	public boolean isGpfDetails() {
		return gpfDetails;
	}

	public void setGpfDetails(boolean gpfDetails) {
		this.gpfDetails = gpfDetails;
	}

	public boolean isPostingStatus() {
		return postingStatus;
	}

	public void setPostingStatus(boolean postingStatus) {
		this.postingStatus = postingStatus;
	}

	public boolean isRoasterStatus() {
		return roasterStatus;
	}

	public void setRoasterStatus(boolean roasterStatus) {
		this.roasterStatus = roasterStatus;
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

	

}
