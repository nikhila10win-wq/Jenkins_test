package com.mhdsys.common.pojo;

import java.util.Date;
import java.util.List;

public class BudgetCommonDTO {

	private long budgetId;

	private String financialYear;
	private String department;
	private String requestedBudget;
	private String approvedBudget;
	private String totalBudget;
	private String recievedAmount;
	private String totalSpend;
	private String overallPercentage;
	private Date budgetDate;
	private String remarks;

	private String uniqueId;
	private String master;
	private String masterStr;
	private String attachments;
	private List<String> attachmentsNames;
	private List<String> attachmentsURLs;
	private List<String> attachmentsIds;
	private String totalFund;
	private String supplementaryDemand;
	private String category;

	private long userId;
	private Date createDate;
	private Date modifiedDate;

	private String budgetDateStr;
	private String createDateStr;
	private String modifiedDateStr;

	private double supplementaryDemandSum;
	private double totalFundSum;
	private double recievedAmountSum;
	private double totalSpendSum;
	private double approvedBudgetSum;

	public long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(long budgetId) {
		this.budgetId = budgetId;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRequestedBudget() {
		return requestedBudget;
	}

	public void setRequestedBudget(String requestedBudget) {
		this.requestedBudget = requestedBudget;
	}

	public String getApprovedBudget() {
		return approvedBudget;
	}

	public void setApprovedBudget(String approvedBudget) {
		this.approvedBudget = approvedBudget;
	}

	public String getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(String totalBudget) {
		this.totalBudget = totalBudget;
	}

	public String getRecievedAmount() {
		return recievedAmount;
	}

	public void setRecievedAmount(String recievedAmount) {
		this.recievedAmount = recievedAmount;
	}

	public String getTotalSpend() {
		return totalSpend;
	}

	public void setTotalSpend(String totalSpend) {
		this.totalSpend = totalSpend;
	}

	public String getOverallPercentage() {
		return overallPercentage;
	}

	public void setOverallPercentage(String overallPercentage) {
		this.overallPercentage = overallPercentage;
	}

	public Date getBudgetDate() {
		return budgetDate;
	}

	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getMasterStr() {
		return masterStr;
	}

	public void setMasterStr(String masterStr) {
		this.masterStr = masterStr;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public List<String> getAttachmentsNames() {
		return attachmentsNames;
	}

	public void setAttachmentsNames(List<String> attachmentsNames) {
		this.attachmentsNames = attachmentsNames;
	}

	public List<String> getAttachmentsURLs() {
		return attachmentsURLs;
	}

	public void setAttachmentsURLs(List<String> attachmentsURLs) {
		this.attachmentsURLs = attachmentsURLs;
	}

	public List<String> getAttachmentsIds() {
		return attachmentsIds;
	}

	public void setAttachmentsIds(List<String> attachmentsIds) {
		this.attachmentsIds = attachmentsIds;
	}

	public String getTotalFund() {
		return totalFund;
	}

	public void setTotalFund(String totalFund) {
		this.totalFund = totalFund;
	}

	public String getSupplementaryDemand() {
		return supplementaryDemand;
	}

	public void setSupplementaryDemand(String supplementaryDemand) {
		this.supplementaryDemand = supplementaryDemand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getBudgetDateStr() {
		return budgetDateStr;
	}

	public void setBudgetDateStr(String budgetDateStr) {
		this.budgetDateStr = budgetDateStr;
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

	public double getSupplementaryDemandSum() {
		return supplementaryDemandSum;
	}

	public void setSupplementaryDemandSum(double supplementaryDemandSum) {
		this.supplementaryDemandSum = supplementaryDemandSum;
	}

	public double getTotalFundSum() {
		return totalFundSum;
	}

	public void setTotalFundSum(double totalFundSum) {
		this.totalFundSum = totalFundSum;
	}

	public double getRecievedAmountSum() {
		return recievedAmountSum;
	}

	public void setRecievedAmountSum(double recievedAmountSum) {
		this.recievedAmountSum = recievedAmountSum;
	}

	public double getTotalSpendSum() {
		return totalSpendSum;
	}

	public void setTotalSpendSum(double totalSpendSum) {
		this.totalSpendSum = totalSpendSum;
	}

	public double getApprovedBudgetSum() {
		return approvedBudgetSum;
	}

	public void setApprovedBudgetSum(double approvedBudgetSum) {
		this.approvedBudgetSum = approvedBudgetSum;
	}

	@Override
	public String toString() {
		return "BudgetCommonDTO [budgetId=" + budgetId + ", financialYear=" + financialYear + ", department="
				+ department + ", requestedBudget=" + requestedBudget + ", approvedBudget=" + approvedBudget
				+ ", totalBudget=" + totalBudget + ", recievedAmount=" + recievedAmount + ", totalSpend=" + totalSpend
				+ ", overallPercentage=" + overallPercentage + ", budgetDate=" + budgetDate + ", remarks=" + remarks
				+ ", uniqueId=" + uniqueId + ", master=" + master + ", masterStr=" + masterStr + ", attachments="
				+ attachments + ", attachmentsNames=" + attachmentsNames + ", attachmentsURLs=" + attachmentsURLs
				+ ", attachmentsIds=" + attachmentsIds + ", totalFund=" + totalFund + ", supplementaryDemand="
				+ supplementaryDemand + ", category=" + category + ", userId=" + userId + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + ", budgetDateStr=" + budgetDateStr + ", createDateStr="
				+ createDateStr + ", modifiedDateStr=" + modifiedDateStr + ", supplementaryDemandSum="
				+ supplementaryDemandSum + ", totalFundSum=" + totalFundSum + ", recievedAmountSum=" + recievedAmountSum
				+ ", totalSpendSum=" + totalSpendSum + ", approvedBudgetSum=" + approvedBudgetSum + "]";
	}

}
