package com.mhdsys.common.pojo;

import java.util.Date;

public class NPSDetailsCommonDTO {

// Primary Key
	private long nPSDetailsId;
// Custom Fields
	private String npsId;
	private String npsRefundableAmount;
	private Date npsRefundableAmountDate;
	private String npsNonRefundableAmount;
	private Date npsNonRefundableAmountDate;
	private String npsMonthlyDeductionAmount;
	private String npsMonthlyInstallmentAmount;
	private String npsNumberOfMonthlyInstallment;
	private String npsBalanceAmount;
	private String typeOfRecord;
// Common Fields
	private long userId;
	private Date createDate;
	private Date modifiedDate;
// Utility Fields
	private String npsRefundableAmountDateStr;
	private String npsNonRefundableAmountDateStr;
	private String createDateStr;
	private String modifiedDateStr;
	private long establishmentDetailId;

	public long getnPSDetailsId() {
		return nPSDetailsId;
	}

	public void setnPSDetailsId(long nPSDetailsId) {
		this.nPSDetailsId = nPSDetailsId;
	}

	public String getNpsId() {
		return npsId;
	}

	public void setNpsId(String npsId) {
		this.npsId = npsId;
	}

	public String getNpsRefundableAmount() {
		return npsRefundableAmount;
	}

	public void setNpsRefundableAmount(String npsRefundableAmount) {
		this.npsRefundableAmount = npsRefundableAmount;
	}

	public Date getNpsRefundableAmountDate() {
		return npsRefundableAmountDate;
	}

	public void setNpsRefundableAmountDate(Date npsRefundableAmountDate) {
		this.npsRefundableAmountDate = npsRefundableAmountDate;
	}

	public String getNpsNonRefundableAmount() {
		return npsNonRefundableAmount;
	}

	public void setNpsNonRefundableAmount(String npsNonRefundableAmount) {
		this.npsNonRefundableAmount = npsNonRefundableAmount;
	}

	public Date getNpsNonRefundableAmountDate() {
		return npsNonRefundableAmountDate;
	}

	public void setNpsNonRefundableAmountDate(Date npsNonRefundableAmountDate) {
		this.npsNonRefundableAmountDate = npsNonRefundableAmountDate;
	}

	public String getNpsMonthlyDeductionAmount() {
		return npsMonthlyDeductionAmount;
	}

	public void setNpsMonthlyDeductionAmount(String npsMonthlyDeductionAmount) {
		this.npsMonthlyDeductionAmount = npsMonthlyDeductionAmount;
	}

	public String getNpsMonthlyInstallmentAmount() {
		return npsMonthlyInstallmentAmount;
	}

	public void setNpsMonthlyInstallmentAmount(String npsMonthlyInstallmentAmount) {
		this.npsMonthlyInstallmentAmount = npsMonthlyInstallmentAmount;
	}

	public String getNpsNumberOfMonthlyInstallment() {
		return npsNumberOfMonthlyInstallment;
	}

	public void setNpsNumberOfMonthlyInstallment(String npsNumberOfMonthlyInstallment) {
		this.npsNumberOfMonthlyInstallment = npsNumberOfMonthlyInstallment;
	}

	public String getNpsBalanceAmount() {
		return npsBalanceAmount;
	}

	public void setNpsBalanceAmount(String npsBalanceAmount) {
		this.npsBalanceAmount = npsBalanceAmount;
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

	

	public String getNpsRefundableAmountDateStr() {
		return npsRefundableAmountDateStr;
	}

	public void setNpsRefundableAmountDateStr(String npsRefundableAmountDateStr) {
		this.npsRefundableAmountDateStr = npsRefundableAmountDateStr;
	}

	public String getNpsNonRefundableAmountDateStr() {
		return npsNonRefundableAmountDateStr;
	}

	public void setNpsNonRefundableAmountDateStr(String npsNonRefundableAmountDateStr) {
		this.npsNonRefundableAmountDateStr = npsNonRefundableAmountDateStr;
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
		return "NPSDetailsCommonDTO [nPSDetailsId=" + nPSDetailsId + ", npsId=" + npsId + ", npsRefundableAmount="
				+ npsRefundableAmount + ", npsRefundableAmountDate=" + npsRefundableAmountDate
				+ ", npsNonRefundableAmount=" + npsNonRefundableAmount + ", npsNonRefundableAmountDate="
				+ npsNonRefundableAmountDate + ", npsMonthlyDeductionAmount=" + npsMonthlyDeductionAmount
				+ ", npsMonthlyInstallmentAmount=" + npsMonthlyInstallmentAmount + ", npsNumberOfMonthlyInstallment="
				+ npsNumberOfMonthlyInstallment + ", npsBalanceAmount=" + npsBalanceAmount + ", typeOfRecord="
				+ typeOfRecord + ", userId=" + userId + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate
				+ ", npsRefundableAmountDateStr=" + npsRefundableAmountDateStr + ", npsNonRefundableAmountDateStr="
				+ npsNonRefundableAmountDateStr + ", createDateStr=" + createDateStr + ", modifiedDateStr="
				+ modifiedDateStr + ", establishmentDetailId=" + establishmentDetailId + "]";
	}

}
