package com.mhdsys.common.pojo;

import java.util.Date;

public class NCCGrantCommonDTO {
	private long nccGrantId;
	private String financialYear;
	private String department;
	private String groupName;
	private String unitType;
	private String commandingOfficer;
	private int demandedNumber;
	private int schemeNumber;
	private String detailHead;
	private long utilizationCertificate;

	// === Grants Details ===
	private int grantsReceived;
	private int grantSurrenderReapp;
	private String grantsWithdrawn;
	private String grantsWithdrawnBy;
	private String grantsSurrender;
	private String grantsSurrenderTo;
	private int grantsAllowed;
	private int grantRecievedReapp;
	private double balance;
	private double expenses;
	private double actualExpense;
	private double balanceWithDdo;

	// === Remark Details ===
	private String status;
	private String remarks;
	private long otherUpload;
	private Date createDate;
	private Date modifiedDated;
	private String utilizationCertificateName;
	private String utilizationCertificateURL;
	private String otherUploadName;
	private String otherUploadURL;

	
	public String getOtherUploadName() {
		return otherUploadName;
	}
	public void setOtherUploadName(String otherUploadName) {
		this.otherUploadName = otherUploadName;
	}
	public String getOtherUploadURL() {
		return otherUploadURL;
	}
	public void setOtherUploadURL(String otherUploadURL) {
		this.otherUploadURL = otherUploadURL;
	}
	public String getUtilizationCertificateName() {
		return utilizationCertificateName;
	}
	public void setUtilizationCertificateName(String utilizationCertificateName) {
		this.utilizationCertificateName = utilizationCertificateName;
	}
	public String getUtilizationCertificateURL() {
		return utilizationCertificateURL;
	}
	public void setUtilizationCertificateURL(String utilizationCertificateURL) {
		this.utilizationCertificateURL = utilizationCertificateURL;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public long getNccGrantId() {
		return nccGrantId;
	}
	public void setNccGrantId(long nccGrantId) {
		this.nccGrantId = nccGrantId;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getCommandingOfficer() {
		return commandingOfficer;
	}
	public void setCommandingOfficer(String commandingOfficer) {
		this.commandingOfficer = commandingOfficer;
	}
	public int getDemandedNumber() {
		return demandedNumber;
	}
	public void setDemandedNumber(int demandedNumber) {
		this.demandedNumber = demandedNumber;
	}
	public int getSchemeNumber() {
		return schemeNumber;
	}
	public void setSchemeNumber(int schemeNumber) {
		this.schemeNumber = schemeNumber;
	}
	public String getDetailHead() {
		return detailHead;
	}
	public void setDetailHead(String detailHead) {
		this.detailHead = detailHead;
	}
	public long getUtilizationCertificate() {
		return utilizationCertificate;
	}
	public void setUtilizationCertificate(long utilizationCertificate) {
		this.utilizationCertificate = utilizationCertificate;
	}
	public int getGrantsReceived() {
		return grantsReceived;
	}
	public void setGrantsReceived(int grantsReceived) {
		this.grantsReceived = grantsReceived;
	}
	public int getGrantSurrenderReapp() {
		return grantSurrenderReapp;
	}
	public void setGrantSurrenderReapp(int grantSurrenderReapp) {
		this.grantSurrenderReapp = grantSurrenderReapp;
	}
	public String getGrantsWithdrawn() {
		return grantsWithdrawn;
	}
	public void setGrantsWithdrawn(String grantsWithdrawn) {
		this.grantsWithdrawn = grantsWithdrawn;
	}
	public String getGrantsWithdrawnBy() {
		return grantsWithdrawnBy;
	}
	public void setGrantsWithdrawnBy(String grantsWithdrawnBy) {
		this.grantsWithdrawnBy = grantsWithdrawnBy;
	}
	public String getGrantsSurrender() {
		return grantsSurrender;
	}
	public void setGrantsSurrender(String grantsSurrender) {
		this.grantsSurrender = grantsSurrender;
	}
	public String getGrantsSurrenderTo() {
		return grantsSurrenderTo;
	}
	public void setGrantsSurrenderTo(String grantsSurrenderTo) {
		this.grantsSurrenderTo = grantsSurrenderTo;
	}
	public int getGrantsAllowed() {
		return grantsAllowed;
	}
	public void setGrantsAllowed(int grantsAllowed) {
		this.grantsAllowed = grantsAllowed;
	}
	public int getGrantRecievedReapp() {
		return grantRecievedReapp;
	}
	public void setGrantRecievedReapp(int grantRecievedReapp) {
		this.grantRecievedReapp = grantRecievedReapp;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getExpenses() {
		return expenses;
	}
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	public double getActualExpense() {
		return actualExpense;
	}
	public void setActualExpense(double actualExpense) {
		this.actualExpense = actualExpense;
	}
	public double getBalanceWithDdo() {
		return balanceWithDdo;
	}
	public void setBalanceWithDdo(double balanceWithDdo) {
		this.balanceWithDdo = balanceWithDdo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public long getOtherUpload() {
		return otherUpload;
	}
	public void setOtherUpload(long otherUpload) {
		this.otherUpload = otherUpload;
	}

}
