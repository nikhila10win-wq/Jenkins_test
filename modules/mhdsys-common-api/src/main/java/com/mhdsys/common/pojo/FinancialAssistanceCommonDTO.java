package com.mhdsys.common.pojo;

import java.util.Date;

public class FinancialAssistanceCommonDTO {
	private long financialAssistanceId;

	private String fullName;
	private String address;
	private long pinCode;
	private String mobileNumber;
	private String emailId;

	// Sports Performance
	private String sportsPerformanceDetails;

	private String certifiedLetter;
	private long certifiedLetterFile;
	private String certifiedLetterFileName;
	private String certifiedLetterFileURL;

	// Financial Details
	private String assistanceMatter;
	private long assistanceMatterFile;
	private String assistanceMatterFileName;
	private String assistanceMatterFileURL;

	private String itemsEstimate;
	private long itemsEstimateFile;
	private String itemsEstimateFileName;
	private String itemsEstimateFileURL;

	private String expenseBudget;
	private long expenseBudgetFile;
	private String expenseBudgetFileName;
	private String expenseBudgetFileURL;

	private String priceList;
	private long priceListFile;
	private String priceListFileName;
	private String priceListFileURL;

	private String expenditureEstimate;
	private long expenditureEstimateFile;
	private String expenditureEstimateFileName;
	private String expenditureEstimateFileURL;

	private String workReport;
	private long workReportFile;
	private String workReportFileName;
	private String workReportFileURL;

	// Declaration
	private boolean declarationAccepted;

	// Audit Fields
	private long userId;
	private Date createDate;
	private Date modifiedDate;

	private String hoVerification;
	private String hoRemarks;
	private String ddVerification;
	private String ddRemarks;

	public long getFinancialAssistanceId() {
		return financialAssistanceId;
	}

	public void setFinancialAssistanceId(long financialAssistanceId) {
		this.financialAssistanceId = financialAssistanceId;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}


	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getSportsPerformanceDetails() {
		return sportsPerformanceDetails;
	}

	public void setSportsPerformanceDetails(String sportsPerformanceDetails) {
		this.sportsPerformanceDetails = sportsPerformanceDetails;
	}

	public String getCertifiedLetter() {
		return certifiedLetter;
	}

	public void setCertifiedLetter(String certifiedLetter) {
		this.certifiedLetter = certifiedLetter;
	}

	public long getCertifiedLetterFile() {
		return certifiedLetterFile;
	}

	public void setCertifiedLetterFile(long certifiedLetterFile) {
		this.certifiedLetterFile = certifiedLetterFile;
	}

	public String getCertifiedLetterFileName() {
		return certifiedLetterFileName;
	}

	public void setCertifiedLetterFileName(String certifiedLetterFileName) {
		this.certifiedLetterFileName = certifiedLetterFileName;
	}

	public String getCertifiedLetterFileURL() {
		return certifiedLetterFileURL;
	}

	public void setCertifiedLetterFileURL(String certifiedLetterFileURL) {
		this.certifiedLetterFileURL = certifiedLetterFileURL;
	}

	public String getAssistanceMatter() {
		return assistanceMatter;
	}

	public void setAssistanceMatter(String assistanceMatter) {
		this.assistanceMatter = assistanceMatter;
	}

	public long getAssistanceMatterFile() {
		return assistanceMatterFile;
	}

	public void setAssistanceMatterFile(long assistanceMatterFile) {
		this.assistanceMatterFile = assistanceMatterFile;
	}

	public String getAssistanceMatterFileName() {
		return assistanceMatterFileName;
	}

	public void setAssistanceMatterFileName(String assistanceMatterFileName) {
		this.assistanceMatterFileName = assistanceMatterFileName;
	}

	public String getAssistanceMatterFileURL() {
		return assistanceMatterFileURL;
	}

	public void setAssistanceMatterFileURL(String assistanceMatterFileURL) {
		this.assistanceMatterFileURL = assistanceMatterFileURL;
	}

	public String getItemsEstimate() {
		return itemsEstimate;
	}

	public void setItemsEstimate(String itemsEstimate) {
		this.itemsEstimate = itemsEstimate;
	}

	public long getItemsEstimateFile() {
		return itemsEstimateFile;
	}

	public void setItemsEstimateFile(long itemsEstimateFile) {
		this.itemsEstimateFile = itemsEstimateFile;
	}

	public String getItemsEstimateFileName() {
		return itemsEstimateFileName;
	}

	public void setItemsEstimateFileName(String itemsEstimateFileName) {
		this.itemsEstimateFileName = itemsEstimateFileName;
	}

	public String getItemsEstimateFileURL() {
		return itemsEstimateFileURL;
	}

	public void setItemsEstimateFileURL(String itemsEstimateFileURL) {
		this.itemsEstimateFileURL = itemsEstimateFileURL;
	}

	public String getExpenseBudget() {
		return expenseBudget;
	}

	public void setExpenseBudget(String expenseBudget) {
		this.expenseBudget = expenseBudget;
	}

	public long getExpenseBudgetFile() {
		return expenseBudgetFile;
	}

	public void setExpenseBudgetFile(long expenseBudgetFile) {
		this.expenseBudgetFile = expenseBudgetFile;
	}

	public String getExpenseBudgetFileName() {
		return expenseBudgetFileName;
	}

	public void setExpenseBudgetFileName(String expenseBudgetFileName) {
		this.expenseBudgetFileName = expenseBudgetFileName;
	}

	public String getExpenseBudgetFileURL() {
		return expenseBudgetFileURL;
	}

	public void setExpenseBudgetFileURL(String expenseBudgetFileURL) {
		this.expenseBudgetFileURL = expenseBudgetFileURL;
	}

	public String getPriceList() {
		return priceList;
	}

	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}

	public long getPriceListFile() {
		return priceListFile;
	}

	public void setPriceListFile(long priceListFile) {
		this.priceListFile = priceListFile;
	}

	public String getPriceListFileName() {
		return priceListFileName;
	}

	public void setPriceListFileName(String priceListFileName) {
		this.priceListFileName = priceListFileName;
	}

	public String getPriceListFileURL() {
		return priceListFileURL;
	}

	public void setPriceListFileURL(String priceListFileURL) {
		this.priceListFileURL = priceListFileURL;
	}

	public String getExpenditureEstimate() {
		return expenditureEstimate;
	}

	public void setExpenditureEstimate(String expenditureEstimate) {
		this.expenditureEstimate = expenditureEstimate;
	}

	public long getExpenditureEstimateFile() {
		return expenditureEstimateFile;
	}

	public void setExpenditureEstimateFile(long expenditureEstimateFile) {
		this.expenditureEstimateFile = expenditureEstimateFile;
	}

	public String getExpenditureEstimateFileName() {
		return expenditureEstimateFileName;
	}

	public void setExpenditureEstimateFileName(String expenditureEstimateFileName) {
		this.expenditureEstimateFileName = expenditureEstimateFileName;
	}

	public String getExpenditureEstimateFileURL() {
		return expenditureEstimateFileURL;
	}

	public void setExpenditureEstimateFileURL(String expenditureEstimateFileURL) {
		this.expenditureEstimateFileURL = expenditureEstimateFileURL;
	}

	public String getWorkReport() {
		return workReport;
	}

	public void setWorkReport(String workReport) {
		this.workReport = workReport;
	}

	public long getWorkReportFile() {
		return workReportFile;
	}

	public void setWorkReportFile(long workReportFile) {
		this.workReportFile = workReportFile;
	}

	public String getWorkReportFileName() {
		return workReportFileName;
	}

	public void setWorkReportFileName(String workReportFileName) {
		this.workReportFileName = workReportFileName;
	}

	public String getWorkReportFileURL() {
		return workReportFileURL;
	}

	public void setWorkReportFileURL(String workReportFileURL) {
		this.workReportFileURL = workReportFileURL;
	}

	public boolean isDeclarationAccepted() {
		return declarationAccepted;
	}

	public void setDeclarationAccepted(boolean declarationAccepted) {
		this.declarationAccepted = declarationAccepted;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "FinancialAssistanceCommonDTO [financialAssistanceId=" + financialAssistanceId + ", fullName=" + fullName
				+ ", address=" + address + ", pinCode=" + pinCode + ", mobileNumber=" + mobileNumber + ", emailId="
				+ emailId + ", sportsPerformanceDetails=" + sportsPerformanceDetails + ", certifiedLetter="
				+ certifiedLetter + ", certifiedLetterFile=" + certifiedLetterFile + ", certifiedLetterFileName="
				+ certifiedLetterFileName + ", certifiedLetterFileURL=" + certifiedLetterFileURL + ", assistanceMatter="
				+ assistanceMatter + ", assistanceMatterFile=" + assistanceMatterFile + ", assistanceMatterFileName="
				+ assistanceMatterFileName + ", assistanceMatterFileURL=" + assistanceMatterFileURL + ", itemsEstimate="
				+ itemsEstimate + ", itemsEstimateFile=" + itemsEstimateFile + ", itemsEstimateFileName="
				+ itemsEstimateFileName + ", itemsEstimateFileURL=" + itemsEstimateFileURL + ", expenseBudget="
				+ expenseBudget + ", expenseBudgetFile=" + expenseBudgetFile + ", expenseBudgetFileName="
				+ expenseBudgetFileName + ", expenseBudgetFileURL=" + expenseBudgetFileURL + ", priceList=" + priceList
				+ ", priceListFile=" + priceListFile + ", priceListFileName=" + priceListFileName
				+ ", priceListFileURL=" + priceListFileURL + ", expenditureEstimate=" + expenditureEstimate
				+ ", expenditureEstimateFile=" + expenditureEstimateFile + ", expenditureEstimateFileName="
				+ expenditureEstimateFileName + ", expenditureEstimateFileURL=" + expenditureEstimateFileURL
				+ ", workReport=" + workReport + ", workReportFile=" + workReportFile + ", workReportFileName="
				+ workReportFileName + ", workReportFileURL=" + workReportFileURL + ", declarationAccepted="
				+ declarationAccepted + ", userId=" + userId + ", createDate=" + createDate + ", modifiedDate="
				+ modifiedDate + ", hoVerification=" + hoVerification + ", hoRemarks=" + hoRemarks + ", ddVerification="
				+ ddVerification + ", ddRemarks=" + ddRemarks + "]";
	}

	

	

}
