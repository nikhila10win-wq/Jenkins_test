package com.mhdsys.common.pojo;

import java.util.Date;

public class GPFDetailsCommonDTO {

// Primary Key
	private long gPFDetailsId;
// Custom Fields
	private String gpfNumber;
	private String refundableAmount;
	private Date refundableAmountDate;
	private String nonRefundableAmount;
	private Date nonRefundableAmountDate;
	private String monthlyDeductionAmount;
	private String monthlyInstallmentAmount;
	private String numberOfMonthlyInstallment;
	private String balanceAmount;
	private String typeOfRecord;
// Common Fields
	private long userId;
	private Date createDate;
	private Date modifiedDate;
// Utility Fields
	private String refundableAmountDateStr;
	private String nonRefundableAmountDateStr;
	private String createDateStr;
	private String modifiedDateStr;
	private long establishmentDetailId;

	public long getgPFDetailsId() {
		return gPFDetailsId;
	}

	public void setgPFDetailsId(long gPFDetailsId) {
		this.gPFDetailsId = gPFDetailsId;
	}

	public String getGpfNumber() {
		return gpfNumber;
	}

	public void setGpfNumber(String gpfNumber) {
		this.gpfNumber = gpfNumber;
	}

	public String getRefundableAmount() {
		return refundableAmount;
	}

	public void setRefundableAmount(String refundableAmount) {
		this.refundableAmount = refundableAmount;
	}

	public Date getRefundableAmountDate() {
		return refundableAmountDate;
	}

	public void setRefundableAmountDate(Date refundableAmountDate) {
		this.refundableAmountDate = refundableAmountDate;
	}

	public String getNonRefundableAmount() {
		return nonRefundableAmount;
	}

	public void setNonRefundableAmount(String nonRefundableAmount) {
		this.nonRefundableAmount = nonRefundableAmount;
	}

	public Date getNonRefundableAmountDate() {
		return nonRefundableAmountDate;
	}

	public void setNonRefundableAmountDate(Date nonRefundableAmountDate) {
		this.nonRefundableAmountDate = nonRefundableAmountDate;
	}

	public String getMonthlyDeductionAmount() {
		return monthlyDeductionAmount;
	}

	public void setMonthlyDeductionAmount(String monthlyDeductionAmount) {
		this.monthlyDeductionAmount = monthlyDeductionAmount;
	}

	public String getMonthlyInstallmentAmount() {
		return monthlyInstallmentAmount;
	}

	public void setMonthlyInstallmentAmount(String monthlyInstallmentAmount) {
		this.monthlyInstallmentAmount = monthlyInstallmentAmount;
	}

	public String getNumberOfMonthlyInstallment() {
		return numberOfMonthlyInstallment;
	}

	public void setNumberOfMonthlyInstallment(String numberOfMonthlyInstallment) {
		this.numberOfMonthlyInstallment = numberOfMonthlyInstallment;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
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

	public String getRefundableAmountDateStr() {
		return refundableAmountDateStr;
	}

	public void setRefundableAmountDateStr(String refundableAmountDateStr) {
		this.refundableAmountDateStr = refundableAmountDateStr;
	}

	public String getNonRefundableAmountDateStr() {
		return nonRefundableAmountDateStr;
	}

	public void setNonRefundableAmountDateStr(String nonRefundableAmountDateStr) {
		this.nonRefundableAmountDateStr = nonRefundableAmountDateStr;
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
		return "GPFDetailsCommonDTO [gPFDetailsId=" + gPFDetailsId + ", gpfNumber=" + gpfNumber + ", refundableAmount="
				+ refundableAmount + ", refundableAmountDate=" + refundableAmountDate + ", nonRefundableAmount="
				+ nonRefundableAmount + ", nonRefundableAmountDate=" + nonRefundableAmountDate
				+ ", monthlyDeductionAmount=" + monthlyDeductionAmount + ", monthlyInstallmentAmount="
				+ monthlyInstallmentAmount + ", numberOfMonthlyInstallment=" + numberOfMonthlyInstallment
				+ ", balanceAmount=" + balanceAmount + ", typeOfRecord=" + typeOfRecord + ", userId=" + userId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", refundableAmountDateStr="
				+ refundableAmountDateStr + ", nonRefundableAmountDateStr=" + nonRefundableAmountDateStr
				+ ", createDateStr=" + createDateStr + ", modifiedDateStr=" + modifiedDateStr
				+ ", establishmentDetailId=" + establishmentDetailId + "]";
	}

}
