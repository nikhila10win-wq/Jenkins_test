package com.mhdsys.common.pojo;

import java.util.Date;

public class RoasterStatusCommonDTO {
	private long roasterStatusId;
	private String nominationCodreWisePostWise;
	private String promotionCodreWisePostWise;
	private String typeOfRecord;
	private long userId;
	private Date createDate;
	private Date modifiedDate;
	private String createDateStr;
	private String modifiedDateStr;
	private long establishmentDetailId;

	public long getRoasterStatusId() {
		return roasterStatusId;
	}

	public void setRoasterStatusId(long roasterStatusId) {
		this.roasterStatusId = roasterStatusId;
	}

	public String getNominationCodreWisePostWise() {
		return nominationCodreWisePostWise;
	}

	public void setNominationCodreWisePostWise(String nominationCodreWisePostWise) {
		this.nominationCodreWisePostWise = nominationCodreWisePostWise;
	}

	public String getPromotionCodreWisePostWise() {
		return promotionCodreWisePostWise;
	}

	public void setPromotionCodreWisePostWise(String promotionCodreWisePostWise) {
		this.promotionCodreWisePostWise = promotionCodreWisePostWise;
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

	public long getEstablishmentDetailId() {
		return establishmentDetailId;
	}

	public void setEstablishmentDetailId(long establishmentDetailId) {
		this.establishmentDetailId = establishmentDetailId;
	}

	@Override
	public String toString() {
		return "RoasterStatusCommonDTO [roasterStatusId=" + roasterStatusId + ", nominationCodreWisePostWise="
				+ nominationCodreWisePostWise + ", promotionCodreWisePostWise=" + promotionCodreWisePostWise
				+ ", typeOfRecord=" + typeOfRecord + ", userId=" + userId + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + ", createDateStr=" + createDateStr + ", modifiedDateStr="
				+ modifiedDateStr + ", establishmentDetailId=" + establishmentDetailId + "]";
	}

}
