package com.mhdsys.common.pojo;

import java.util.Date;

public class AwardWinnerCommonDTO {
	// Primary Key
	private long awardWinnerId;

	// Personal Details
	private String fullName;
	private Date dob;
	private String dobStr;
	private String mobileNumber;
	private String emailId;
	private long aadhaarCardFile;
	private String aadhaarCardFileName;
	private String aadhaarCardFileURL;

	private long panCardFile;
	private String panCardFileName;
	private String panCardFileURL;

	private long domicileCertificateFile;
	private String domicileCertificateFileName;
	private String domicileCertificateFileURL;

	// Competition Details
	private String competitionLevel;
	private String competitionName;
	private int competitionYear;
	private long competitionDrawFile;
	private String competitionDrawFileName;
	private String competitionDrawFileURL;

	private long organizationSelectionLetterFile;
	private String organizationSelectionLetterFileName;
	private String organizationSelectionLetterFileURL;

	private Date competitionDate;
	private String placeOfCompetition;
	private String competitionPerformance;
	private String competitionRank;
	private long competitionResultFile;
	private String competitionResultFileName;
	private String competitionResultFileURL;

	private long competitionCertificateFile;
	private String competitionCertificateFileName;
	private String competitionCertificateFileURL;

	// Bank Details
	private String bankName;
	private String ifscCode;
	private String accountNumber;
	private long bankStatementFile;
	private String bankStatementFileName;
	private String bankStatementFileURL;

	private long bankPassbookFile;
	private String bankPassbookFileName;
	private String bankPassbookFileURL;

	private long cancelledCheckFile;
	private String cancelledCheckFileName;
	private String cancelledCheckFileURL;

	private long playersConsentLetterFile;
	private String playersConsentLetterFileName;
	private String playersConsentLetterFileURL;

	private long affidavitFile;
	private String affidavitFileName;
	private String affidavitFileURL;

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

	public long getAwardWinnerId() {
		return awardWinnerId;
	}

	public void setAwardWinnerId(long awardWinnerId) {
		this.awardWinnerId = awardWinnerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDobStr() {
		return dobStr;
	}

	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
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

	public long getAadhaarCardFile() {
		return aadhaarCardFile;
	}

	public void setAadhaarCardFile(long aadhaarCardFile) {
		this.aadhaarCardFile = aadhaarCardFile;
	}

	public String getAadhaarCardFileName() {
		return aadhaarCardFileName;
	}

	public void setAadhaarCardFileName(String aadhaarCardFileName) {
		this.aadhaarCardFileName = aadhaarCardFileName;
	}

	public String getAadhaarCardFileURL() {
		return aadhaarCardFileURL;
	}

	public void setAadhaarCardFileURL(String aadhaarCardFileURL) {
		this.aadhaarCardFileURL = aadhaarCardFileURL;
	}

	public long getPanCardFile() {
		return panCardFile;
	}

	public void setPanCardFile(long panCardFile) {
		this.panCardFile = panCardFile;
	}

	public String getPanCardFileName() {
		return panCardFileName;
	}

	public void setPanCardFileName(String panCardFileName) {
		this.panCardFileName = panCardFileName;
	}

	public String getPanCardFileURL() {
		return panCardFileURL;
	}

	public void setPanCardFileURL(String panCardFileURL) {
		this.panCardFileURL = panCardFileURL;
	}

	public long getDomicileCertificateFile() {
		return domicileCertificateFile;
	}

	public void setDomicileCertificateFile(long domicileCertificateFile) {
		this.domicileCertificateFile = domicileCertificateFile;
	}

	public String getDomicileCertificateFileName() {
		return domicileCertificateFileName;
	}

	public void setDomicileCertificateFileName(String domicileCertificateFileName) {
		this.domicileCertificateFileName = domicileCertificateFileName;
	}

	public String getDomicileCertificateFileURL() {
		return domicileCertificateFileURL;
	}

	public void setDomicileCertificateFileURL(String domicileCertificateFileURL) {
		this.domicileCertificateFileURL = domicileCertificateFileURL;
	}

	public String getCompetitionLevel() {
		return competitionLevel;
	}

	public void setCompetitionLevel(String competitionLevel) {
		this.competitionLevel = competitionLevel;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public int getCompetitionYear() {
		return competitionYear;
	}

	public void setCompetitionYear(int competitionYear) {
		this.competitionYear = competitionYear;
	}

	public long getCompetitionDrawFile() {
		return competitionDrawFile;
	}

	public void setCompetitionDrawFile(long competitionDrawFile) {
		this.competitionDrawFile = competitionDrawFile;
	}

	public String getCompetitionDrawFileName() {
		return competitionDrawFileName;
	}

	public void setCompetitionDrawFileName(String competitionDrawFileName) {
		this.competitionDrawFileName = competitionDrawFileName;
	}

	public String getCompetitionDrawFileURL() {
		return competitionDrawFileURL;
	}

	public void setCompetitionDrawFileURL(String competitionDrawFileURL) {
		this.competitionDrawFileURL = competitionDrawFileURL;
	}

	public long getOrganizationSelectionLetterFile() {
		return organizationSelectionLetterFile;
	}

	public void setOrganizationSelectionLetterFile(long organizationSelectionLetterFile) {
		this.organizationSelectionLetterFile = organizationSelectionLetterFile;
	}

	public String getOrganizationSelectionLetterFileName() {
		return organizationSelectionLetterFileName;
	}

	public void setOrganizationSelectionLetterFileName(String organizationSelectionLetterFileName) {
		this.organizationSelectionLetterFileName = organizationSelectionLetterFileName;
	}

	public String getOrganizationSelectionLetterFileURL() {
		return organizationSelectionLetterFileURL;
	}

	public void setOrganizationSelectionLetterFileURL(String organizationSelectionLetterFileURL) {
		this.organizationSelectionLetterFileURL = organizationSelectionLetterFileURL;
	}

	public Date getCompetitionDate() {
		return competitionDate;
	}

	public void setCompetitionDate(Date competitionDate) {
		this.competitionDate = competitionDate;
	}

	public String getPlaceOfCompetition() {
		return placeOfCompetition;
	}

	public void setPlaceOfCompetition(String placeOfCompetition) {
		this.placeOfCompetition = placeOfCompetition;
	}

	public String getCompetitionPerformance() {
		return competitionPerformance;
	}

	public void setCompetitionPerformance(String competitionPerformance) {
		this.competitionPerformance = competitionPerformance;
	}

	public String getCompetitionRank() {
		return competitionRank;
	}

	public void setCompetitionRank(String competitionRank) {
		this.competitionRank = competitionRank;
	}

	public long getCompetitionResultFile() {
		return competitionResultFile;
	}

	public void setCompetitionResultFile(long competitionResultFile) {
		this.competitionResultFile = competitionResultFile;
	}

	public String getCompetitionResultFileName() {
		return competitionResultFileName;
	}

	public void setCompetitionResultFileName(String competitionResultFileName) {
		this.competitionResultFileName = competitionResultFileName;
	}

	public String getCompetitionResultFileURL() {
		return competitionResultFileURL;
	}

	public void setCompetitionResultFileURL(String competitionResultFileURL) {
		this.competitionResultFileURL = competitionResultFileURL;
	}

	public long getCompetitionCertificateFile() {
		return competitionCertificateFile;
	}

	public void setCompetitionCertificateFile(long competitionCertificateFile) {
		this.competitionCertificateFile = competitionCertificateFile;
	}

	public String getCompetitionCertificateFileName() {
		return competitionCertificateFileName;
	}

	public void setCompetitionCertificateFileName(String competitionCertificateFileName) {
		this.competitionCertificateFileName = competitionCertificateFileName;
	}

	public String getCompetitionCertificateFileURL() {
		return competitionCertificateFileURL;
	}

	public void setCompetitionCertificateFileURL(String competitionCertificateFileURL) {
		this.competitionCertificateFileURL = competitionCertificateFileURL;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBankStatementFile() {
		return bankStatementFile;
	}

	public void setBankStatementFile(long bankStatementFile) {
		this.bankStatementFile = bankStatementFile;
	}

	public String getBankStatementFileName() {
		return bankStatementFileName;
	}

	public void setBankStatementFileName(String bankStatementFileName) {
		this.bankStatementFileName = bankStatementFileName;
	}

	public String getBankStatementFileURL() {
		return bankStatementFileURL;
	}

	public void setBankStatementFileURL(String bankStatementFileURL) {
		this.bankStatementFileURL = bankStatementFileURL;
	}

	public long getBankPassbookFile() {
		return bankPassbookFile;
	}

	public void setBankPassbookFile(long bankPassbookFile) {
		this.bankPassbookFile = bankPassbookFile;
	}

	public String getBankPassbookFileName() {
		return bankPassbookFileName;
	}

	public void setBankPassbookFileName(String bankPassbookFileName) {
		this.bankPassbookFileName = bankPassbookFileName;
	}

	public String getBankPassbookFileURL() {
		return bankPassbookFileURL;
	}

	public void setBankPassbookFileURL(String bankPassbookFileURL) {
		this.bankPassbookFileURL = bankPassbookFileURL;
	}

	public long getCancelledCheckFile() {
		return cancelledCheckFile;
	}

	public void setCancelledCheckFile(long cancelledCheckFile) {
		this.cancelledCheckFile = cancelledCheckFile;
	}

	public String getCancelledCheckFileName() {
		return cancelledCheckFileName;
	}

	public void setCancelledCheckFileName(String cancelledCheckFileName) {
		this.cancelledCheckFileName = cancelledCheckFileName;
	}

	public String getCancelledCheckFileURL() {
		return cancelledCheckFileURL;
	}

	public void setCancelledCheckFileURL(String cancelledCheckFileURL) {
		this.cancelledCheckFileURL = cancelledCheckFileURL;
	}

	public long getPlayersConsentLetterFile() {
		return playersConsentLetterFile;
	}

	public void setPlayersConsentLetterFile(long playersConsentLetterFile) {
		this.playersConsentLetterFile = playersConsentLetterFile;
	}

	public String getPlayersConsentLetterFileName() {
		return playersConsentLetterFileName;
	}

	public void setPlayersConsentLetterFileName(String playersConsentLetterFileName) {
		this.playersConsentLetterFileName = playersConsentLetterFileName;
	}

	public String getPlayersConsentLetterFileURL() {
		return playersConsentLetterFileURL;
	}

	public void setPlayersConsentLetterFileURL(String playersConsentLetterFileURL) {
		this.playersConsentLetterFileURL = playersConsentLetterFileURL;
	}

	public long getAffidavitFile() {
		return affidavitFile;
	}

	public void setAffidavitFile(long affidavitFile) {
		this.affidavitFile = affidavitFile;
	}

	public String getAffidavitFileName() {
		return affidavitFileName;
	}

	public void setAffidavitFileName(String affidavitFileName) {
		this.affidavitFileName = affidavitFileName;
	}

	public String getAffidavitFileURL() {
		return affidavitFileURL;
	}

	public void setAffidavitFileURL(String affidavitFileURL) {
		this.affidavitFileURL = affidavitFileURL;
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

	@Override
	public String toString() {
		return "AwardWinnerCommonDTO [awardWinnerId=" + awardWinnerId + ", fullName=" + fullName + ", dob=" + dob
				+ ", dobStr=" + dobStr + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
				+ ", aadhaarCardFile=" + aadhaarCardFile + ", aadhaarCardFileName=" + aadhaarCardFileName
				+ ", aadhaarCardFileURL=" + aadhaarCardFileURL + ", panCardFile=" + panCardFile + ", panCardFileName="
				+ panCardFileName + ", panCardFileURL=" + panCardFileURL + ", domicileCertificateFile="
				+ domicileCertificateFile + ", domicileCertificateFileName=" + domicileCertificateFileName
				+ ", domicileCertificateFileURL=" + domicileCertificateFileURL + ", competitionLevel="
				+ competitionLevel + ", competitionName=" + competitionName + ", competitionYear=" + competitionYear
				+ ", competitionDrawFile=" + competitionDrawFile + ", competitionDrawFileName="
				+ competitionDrawFileName + ", competitionDrawFileURL=" + competitionDrawFileURL
				+ ", organizationSelectionLetterFile=" + organizationSelectionLetterFile
				+ ", organizationSelectionLetterFileName=" + organizationSelectionLetterFileName
				+ ", organizationSelectionLetterFileURL=" + organizationSelectionLetterFileURL + ", competitionDate="
				+ competitionDate + ", placeOfCompetition=" + placeOfCompetition + ", competitionPerformance="
				+ competitionPerformance + ", competitionRank=" + competitionRank + ", competitionResultFile="
				+ competitionResultFile + ", competitionResultFileName=" + competitionResultFileName
				+ ", competitionResultFileURL=" + competitionResultFileURL + ", competitionCertificateFile="
				+ competitionCertificateFile + ", competitionCertificateFileName=" + competitionCertificateFileName
				+ ", competitionCertificateFileURL=" + competitionCertificateFileURL + ", bankName=" + bankName
				+ ", ifscCode=" + ifscCode + ", accountNumber=" + accountNumber + ", bankStatementFile="
				+ bankStatementFile + ", bankStatementFileName=" + bankStatementFileName + ", bankStatementFileURL="
				+ bankStatementFileURL + ", bankPassbookFile=" + bankPassbookFile + ", bankPassbookFileName="
				+ bankPassbookFileName + ", bankPassbookFileURL=" + bankPassbookFileURL + ", cancelledCheckFile="
				+ cancelledCheckFile + ", cancelledCheckFileName=" + cancelledCheckFileName + ", cancelledCheckFileURL="
				+ cancelledCheckFileURL + ", playersConsentLetterFile=" + playersConsentLetterFile
				+ ", playersConsentLetterFileName=" + playersConsentLetterFileName + ", playersConsentLetterFileURL="
				+ playersConsentLetterFileURL + ", affidavitFile=" + affidavitFile + ", affidavitFileName="
				+ affidavitFileName + ", affidavitFileURL=" + affidavitFileURL + ", declarationAccepted="
				+ declarationAccepted + ", userId=" + userId + ", createDate=" + createDate + ", modifiedDate="
				+ modifiedDate + ", hoVerification=" + hoVerification + ", hoRemarks=" + hoRemarks + ", ddVerification="
				+ ddVerification + ", ddRemarks=" + ddRemarks + "]";
	}

}
