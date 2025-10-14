package com.mhdsys.common.pojo;

import java.util.Date;
import java.util.List;

public class ServiceDetailsCommonDTO {
	private long serviceDetailsId;
	private String nominations;
	private String promotionExam;
	private Date dateOfAppointment;
	private String dateOfAppointmentStr;
	private Date dateOfRetirement;
	private String dateOfRetirementStr;
	private String probationPeriod;
	private String entryLevelDepartmentExam;
	private String regularPromotionDetails;
	private String promotionTimeScale;
	private String regularPayFixation;
	private String retirementType;

	private long medicalCertificate;
	private String medicalCertificateName;
	private String medicalCertificateURL;

	private Date policeVerificationDate;
	private String policeVerificationDateStr;

	private long policeVerification;
	private String policeVerificationName;
	private String policeVerificationURL;

	private String permanencyCertificate;
	private String treasuryVerification;
	private String languageExamCertificate;
	private String awardCertificationOfHonor;
	private String confidentialReports;
	private String mattaAndDayitwaReceived;
	private String complaintDetails;
	private String noDuesCertificate;
	private String noEnquiryCertificate;
	private String typeOfRecord;
	private Date medicalCertificateDate;
	private String medicalCertificateDateStr;

	// Suspension Details
	private Date dateOfSuspension;
	private String reasonOfSuspension;
	private Date dateOfReinstate;

	private long departmentalEnquiryReport;
	private String departmentalEnquiryReportName;
	private String departmentalEnquiryReportURL;

	private String punishment;

	private String dateOfReinstateStr;
	private String dateOfSuspensionStr;

	// Seniority
	private long seniorityList;
	private String seniorityListName;
	private String seniorityListURL;

	// Leave Details
	private String leaveType;
	private Date leaveFromDate;
	private String leaveFromDateStr;
	private Date leaveToDate;
	private String leaveToDateStr;

	private long leaveDocument;
	private String leaveDocumentName;
	private String leaveDocumentURL;

	private long userId;
	private Date createdDate;
	private Date modifiedDate;
	private String categoryStr;
	private String modifiedStr;

	private long establishmentDetailId;

	// multiple files
	private List<String> treasuryVerificationURLs;
	private List<String> treasuryVerificationNames;
	private List<String> treasuryVerificationEntryIds;

	private List<String> noEnquiryCertificateURLs;
	private List<String> noEnquiryCertificateNames;
	private List<String> noEnquiryCertificateEntryIds;

	private List<String> complaintDetailsURLs;
	private List<String> complaintDetailsNames;
	private List<String> complaintDetailsEntryIds;

	private List<String> noDuesCertificateURLs;
	private List<String> noDuesCertificateNames;
	private List<String> noDuesCertificateEntryIds;

	private List<String> confidentialReportsURLs;
	private List<String> confidentialReportsNames;
	private List<String> confidentialReportsEntryIds;

	private List<String> awardCertificationOfHonorURLs;
	private List<String> awardCertificationOfHonorNames;
	private List<String> awardCertificationOfHonorEntryIds;

	private List<String> languageExamCertificateURLs;
	private List<String> languageExamCertificateNames;
	private List<String> languageExamCertificateEntryIds;

	private List<String> permanencyCertificateURLs;
	private List<String> permanencyCertificateNames;
	private List<String> permanencyCertificateEntryIds;

	public long getServiceDetailsId() {
		return serviceDetailsId;
	}

	public void setServiceDetailsId(long serviceDetailsId) {
		this.serviceDetailsId = serviceDetailsId;
	}

	public String getNominations() {
		return nominations;
	}

	public void setNominations(String nominations) {
		this.nominations = nominations;
	}

	public String getPromotionExam() {
		return promotionExam;
	}

	public void setPromotionExam(String promotionExam) {
		this.promotionExam = promotionExam;
	}

	public Date getDateOfAppointment() {
		return dateOfAppointment;
	}

	public void setDateOfAppointment(Date dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	public String getDateOfAppointmentStr() {
		return dateOfAppointmentStr;
	}

	public void setDateOfAppointmentStr(String dateOfAppointmentStr) {
		this.dateOfAppointmentStr = dateOfAppointmentStr;
	}

	public Date getDateOfRetirement() {
		return dateOfRetirement;
	}

	public void setDateOfRetirement(Date dateOfRetirement) {
		this.dateOfRetirement = dateOfRetirement;
	}

	public String getDateOfRetirementStr() {
		return dateOfRetirementStr;
	}

	public void setDateOfRetirementStr(String dateOfRetirementStr) {
		this.dateOfRetirementStr = dateOfRetirementStr;
	}

	public String getProbationPeriod() {
		return probationPeriod;
	}

	public void setProbationPeriod(String probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

	public String getEntryLevelDepartmentExam() {
		return entryLevelDepartmentExam;
	}

	public void setEntryLevelDepartmentExam(String entryLevelDepartmentExam) {
		this.entryLevelDepartmentExam = entryLevelDepartmentExam;
	}

	public String getRegularPromotionDetails() {
		return regularPromotionDetails;
	}

	public void setRegularPromotionDetails(String regularPromotionDetails) {
		this.regularPromotionDetails = regularPromotionDetails;
	}

	public String getPromotionTimeScale() {
		return promotionTimeScale;
	}

	public void setPromotionTimeScale(String promotionTimeScale) {
		this.promotionTimeScale = promotionTimeScale;
	}

	public String getRegularPayFixation() {
		return regularPayFixation;
	}

	public void setRegularPayFixation(String regularPayFixation) {
		this.regularPayFixation = regularPayFixation;
	}

	public String getRetirementType() {
		return retirementType;
	}

	public void setRetirementType(String retirementType) {
		this.retirementType = retirementType;
	}

	public long getMedicalCertificate() {
		return medicalCertificate;
	}

	public void setMedicalCertificate(long medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}

	public String getMedicalCertificateName() {
		return medicalCertificateName;
	}

	public void setMedicalCertificateName(String medicalCertificateName) {
		this.medicalCertificateName = medicalCertificateName;
	}

	public String getMedicalCertificateURL() {
		return medicalCertificateURL;
	}

	public void setMedicalCertificateURL(String medicalCertificateURL) {
		this.medicalCertificateURL = medicalCertificateURL;
	}

	public Date getPoliceVerificationDate() {
		return policeVerificationDate;
	}

	public void setPoliceVerificationDate(Date policeVerificationDate) {
		this.policeVerificationDate = policeVerificationDate;
	}

	public String getPoliceVerificationDateStr() {
		return policeVerificationDateStr;
	}

	public void setPoliceVerificationDateStr(String policeVerificationDateStr) {
		this.policeVerificationDateStr = policeVerificationDateStr;
	}

	public long getPoliceVerification() {
		return policeVerification;
	}

	public void setPoliceVerification(long policeVerification) {
		this.policeVerification = policeVerification;
	}

	public String getPoliceVerificationName() {
		return policeVerificationName;
	}

	public void setPoliceVerificationName(String policeVerificationName) {
		this.policeVerificationName = policeVerificationName;
	}

	public String getPoliceVerificationURL() {
		return policeVerificationURL;
	}

	public void setPoliceVerificationURL(String policeVerificationURL) {
		this.policeVerificationURL = policeVerificationURL;
	}

	public String getPermanencyCertificate() {
		return permanencyCertificate;
	}

	public void setPermanencyCertificate(String permanencyCertificate) {
		this.permanencyCertificate = permanencyCertificate;
	}

	public String getTreasuryVerification() {
		return treasuryVerification;
	}

	public void setTreasuryVerification(String treasuryVerification) {
		this.treasuryVerification = treasuryVerification;
	}

	public String getLanguageExamCertificate() {
		return languageExamCertificate;
	}

	public void setLanguageExamCertificate(String languageExamCertificate) {
		this.languageExamCertificate = languageExamCertificate;
	}

	public String getAwardCertificationOfHonor() {
		return awardCertificationOfHonor;
	}

	public void setAwardCertificationOfHonor(String awardCertificationOfHonor) {
		this.awardCertificationOfHonor = awardCertificationOfHonor;
	}

	public String getConfidentialReports() {
		return confidentialReports;
	}

	public void setConfidentialReports(String confidentialReports) {
		this.confidentialReports = confidentialReports;
	}

	public String getMattaAndDayitwaReceived() {
		return mattaAndDayitwaReceived;
	}

	public void setMattaAndDayitwaReceived(String mattaAndDayitwaReceived) {
		this.mattaAndDayitwaReceived = mattaAndDayitwaReceived;
	}

	public String getComplaintDetails() {
		return complaintDetails;
	}

	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}

	public String getNoDuesCertificate() {
		return noDuesCertificate;
	}

	public void setNoDuesCertificate(String noDuesCertificate) {
		this.noDuesCertificate = noDuesCertificate;
	}

	public String getNoEnquiryCertificate() {
		return noEnquiryCertificate;
	}

	public void setNoEnquiryCertificate(String noEnquiryCertificate) {
		this.noEnquiryCertificate = noEnquiryCertificate;
	}

	public String getTypeOfRecord() {
		return typeOfRecord;
	}

	public void setTypeOfRecord(String typeOfRecord) {
		this.typeOfRecord = typeOfRecord;
	}

	public Date getMedicalCertificateDate() {
		return medicalCertificateDate;
	}

	public void setMedicalCertificateDate(Date medicalCertificateDate) {
		this.medicalCertificateDate = medicalCertificateDate;
	}

	public String getMedicalCertificateDateStr() {
		return medicalCertificateDateStr;
	}

	public void setMedicalCertificateDateStr(String medicalCertificateDateStr) {
		this.medicalCertificateDateStr = medicalCertificateDateStr;
	}

	public Date getDateOfSuspension() {
		return dateOfSuspension;
	}

	public void setDateOfSuspension(Date dateOfSuspension) {
		this.dateOfSuspension = dateOfSuspension;
	}

	public String getReasonOfSuspension() {
		return reasonOfSuspension;
	}

	public void setReasonOfSuspension(String reasonOfSuspension) {
		this.reasonOfSuspension = reasonOfSuspension;
	}

	public Date getDateOfReinstate() {
		return dateOfReinstate;
	}

	public void setDateOfReinstate(Date dateOfReinstate) {
		this.dateOfReinstate = dateOfReinstate;
	}

	public long getDepartmentalEnquiryReport() {
		return departmentalEnquiryReport;
	}

	public void setDepartmentalEnquiryReport(long departmentalEnquiryReport) {
		this.departmentalEnquiryReport = departmentalEnquiryReport;
	}

	public String getDepartmentalEnquiryReportName() {
		return departmentalEnquiryReportName;
	}

	public void setDepartmentalEnquiryReportName(String departmentalEnquiryReportName) {
		this.departmentalEnquiryReportName = departmentalEnquiryReportName;
	}

	public String getDepartmentalEnquiryReportURL() {
		return departmentalEnquiryReportURL;
	}

	public void setDepartmentalEnquiryReportURL(String departmentalEnquiryReportURL) {
		this.departmentalEnquiryReportURL = departmentalEnquiryReportURL;
	}

	public String getPunishment() {
		return punishment;
	}

	public void setPunishment(String punishment) {
		this.punishment = punishment;
	}

	public String getDateOfReinstateStr() {
		return dateOfReinstateStr;
	}

	public void setDateOfReinstateStr(String dateOfReinstateStr) {
		this.dateOfReinstateStr = dateOfReinstateStr;
	}

	public String getDateOfSuspensionStr() {
		return dateOfSuspensionStr;
	}

	public void setDateOfSuspensionStr(String dateOfSuspensionStr) {
		this.dateOfSuspensionStr = dateOfSuspensionStr;
	}

	public long getSeniorityList() {
		return seniorityList;
	}

	public void setSeniorityList(long seniorityList) {
		this.seniorityList = seniorityList;
	}

	public String getSeniorityListName() {
		return seniorityListName;
	}

	public void setSeniorityListName(String seniorityListName) {
		this.seniorityListName = seniorityListName;
	}

	public String getSeniorityListURL() {
		return seniorityListURL;
	}

	public void setSeniorityListURL(String seniorityListURL) {
		this.seniorityListURL = seniorityListURL;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Date getLeaveFromDate() {
		return leaveFromDate;
	}

	public void setLeaveFromDate(Date leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}

	public String getLeaveFromDateStr() {
		return leaveFromDateStr;
	}

	public void setLeaveFromDateStr(String leaveFromDateStr) {
		this.leaveFromDateStr = leaveFromDateStr;
	}

	public Date getLeaveToDate() {
		return leaveToDate;
	}

	public void setLeaveToDate(Date leaveToDate) {
		this.leaveToDate = leaveToDate;
	}

	public String getLeaveToDateStr() {
		return leaveToDateStr;
	}

	public void setLeaveToDateStr(String leaveToDateStr) {
		this.leaveToDateStr = leaveToDateStr;
	}

	public long getLeaveDocument() {
		return leaveDocument;
	}

	public void setLeaveDocument(long leaveDocument) {
		this.leaveDocument = leaveDocument;
	}

	public String getLeaveDocumentName() {
		return leaveDocumentName;
	}

	public void setLeaveDocumentName(String leaveDocumentName) {
		this.leaveDocumentName = leaveDocumentName;
	}

	public String getLeaveDocumentURL() {
		return leaveDocumentURL;
	}

	public void setLeaveDocumentURL(String leaveDocumentURL) {
		this.leaveDocumentURL = leaveDocumentURL;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCategoryStr() {
		return categoryStr;
	}

	public void setCategoryStr(String categoryStr) {
		this.categoryStr = categoryStr;
	}

	public String getModifiedStr() {
		return modifiedStr;
	}

	public void setModifiedStr(String modifiedStr) {
		this.modifiedStr = modifiedStr;
	}

	public long getEstablishmentDetailId() {
		return establishmentDetailId;
	}

	public void setEstablishmentDetailId(long establishmentDetailId) {
		this.establishmentDetailId = establishmentDetailId;
	}

	public List<String> getTreasuryVerificationURLs() {
		return treasuryVerificationURLs;
	}

	public void setTreasuryVerificationURLs(List<String> treasuryVerificationURLs) {
		this.treasuryVerificationURLs = treasuryVerificationURLs;
	}

	public List<String> getTreasuryVerificationNames() {
		return treasuryVerificationNames;
	}

	public void setTreasuryVerificationNames(List<String> treasuryVerificationNames) {
		this.treasuryVerificationNames = treasuryVerificationNames;
	}

	public List<String> getTreasuryVerificationEntryIds() {
		return treasuryVerificationEntryIds;
	}

	public void setTreasuryVerificationEntryIds(List<String> treasuryVerificationEntryIds) {
		this.treasuryVerificationEntryIds = treasuryVerificationEntryIds;
	}

	public List<String> getNoEnquiryCertificateURLs() {
		return noEnquiryCertificateURLs;
	}

	public void setNoEnquiryCertificateURLs(List<String> noEnquiryCertificateURLs) {
		this.noEnquiryCertificateURLs = noEnquiryCertificateURLs;
	}

	public List<String> getNoEnquiryCertificateNames() {
		return noEnquiryCertificateNames;
	}

	public void setNoEnquiryCertificateNames(List<String> noEnquiryCertificateNames) {
		this.noEnquiryCertificateNames = noEnquiryCertificateNames;
	}

	public List<String> getNoEnquiryCertificateEntryIds() {
		return noEnquiryCertificateEntryIds;
	}

	public void setNoEnquiryCertificateEntryIds(List<String> noEnquiryCertificateEntryIds) {
		this.noEnquiryCertificateEntryIds = noEnquiryCertificateEntryIds;
	}

	public List<String> getComplaintDetailsURLs() {
		return complaintDetailsURLs;
	}

	public void setComplaintDetailsURLs(List<String> complaintDetailsURLs) {
		this.complaintDetailsURLs = complaintDetailsURLs;
	}

	public List<String> getComplaintDetailsNames() {
		return complaintDetailsNames;
	}

	public void setComplaintDetailsNames(List<String> complaintDetailsNames) {
		this.complaintDetailsNames = complaintDetailsNames;
	}

	public List<String> getComplaintDetailsEntryIds() {
		return complaintDetailsEntryIds;
	}

	public void setComplaintDetailsEntryIds(List<String> complaintDetailsEntryIds) {
		this.complaintDetailsEntryIds = complaintDetailsEntryIds;
	}

	public List<String> getNoDuesCertificateURLs() {
		return noDuesCertificateURLs;
	}

	public void setNoDuesCertificateURLs(List<String> noDuesCertificateURLs) {
		this.noDuesCertificateURLs = noDuesCertificateURLs;
	}

	public List<String> getNoDuesCertificateNames() {
		return noDuesCertificateNames;
	}

	public void setNoDuesCertificateNames(List<String> noDuesCertificateNames) {
		this.noDuesCertificateNames = noDuesCertificateNames;
	}

	public List<String> getNoDuesCertificateEntryIds() {
		return noDuesCertificateEntryIds;
	}

	public void setNoDuesCertificateEntryIds(List<String> noDuesCertificateEntryIds) {
		this.noDuesCertificateEntryIds = noDuesCertificateEntryIds;
	}

	public List<String> getConfidentialReportsURLs() {
		return confidentialReportsURLs;
	}

	public void setConfidentialReportsURLs(List<String> confidentialReportsURLs) {
		this.confidentialReportsURLs = confidentialReportsURLs;
	}

	public List<String> getConfidentialReportsNames() {
		return confidentialReportsNames;
	}

	public void setConfidentialReportsNames(List<String> confidentialReportsNames) {
		this.confidentialReportsNames = confidentialReportsNames;
	}

	public List<String> getConfidentialReportsEntryIds() {
		return confidentialReportsEntryIds;
	}

	public void setConfidentialReportsEntryIds(List<String> confidentialReportsEntryIds) {
		this.confidentialReportsEntryIds = confidentialReportsEntryIds;
	}

	public List<String> getAwardCertificationOfHonorURLs() {
		return awardCertificationOfHonorURLs;
	}

	public void setAwardCertificationOfHonorURLs(List<String> awardCertificationOfHonorURLs) {
		this.awardCertificationOfHonorURLs = awardCertificationOfHonorURLs;
	}

	public List<String> getAwardCertificationOfHonorNames() {
		return awardCertificationOfHonorNames;
	}

	public void setAwardCertificationOfHonorNames(List<String> awardCertificationOfHonorNames) {
		this.awardCertificationOfHonorNames = awardCertificationOfHonorNames;
	}

	public List<String> getAwardCertificationOfHonorEntryIds() {
		return awardCertificationOfHonorEntryIds;
	}

	public void setAwardCertificationOfHonorEntryIds(List<String> awardCertificationOfHonorEntryIds) {
		this.awardCertificationOfHonorEntryIds = awardCertificationOfHonorEntryIds;
	}

	public List<String> getLanguageExamCertificateURLs() {
		return languageExamCertificateURLs;
	}

	public void setLanguageExamCertificateURLs(List<String> languageExamCertificateURLs) {
		this.languageExamCertificateURLs = languageExamCertificateURLs;
	}

	public List<String> getLanguageExamCertificateNames() {
		return languageExamCertificateNames;
	}

	public void setLanguageExamCertificateNames(List<String> languageExamCertificateNames) {
		this.languageExamCertificateNames = languageExamCertificateNames;
	}

	public List<String> getLanguageExamCertificateEntryIds() {
		return languageExamCertificateEntryIds;
	}

	public void setLanguageExamCertificateEntryIds(List<String> languageExamCertificateEntryIds) {
		this.languageExamCertificateEntryIds = languageExamCertificateEntryIds;
	}

	public List<String> getPermanencyCertificateURLs() {
		return permanencyCertificateURLs;
	}

	public void setPermanencyCertificateURLs(List<String> permanencyCertificateURLs) {
		this.permanencyCertificateURLs = permanencyCertificateURLs;
	}

	public List<String> getPermanencyCertificateNames() {
		return permanencyCertificateNames;
	}

	public void setPermanencyCertificateNames(List<String> permanencyCertificateNames) {
		this.permanencyCertificateNames = permanencyCertificateNames;
	}

	public List<String> getPermanencyCertificateEntryIds() {
		return permanencyCertificateEntryIds;
	}

	public void setPermanencyCertificateEntryIds(List<String> permanencyCertificateEntryIds) {
		this.permanencyCertificateEntryIds = permanencyCertificateEntryIds;
	}

	@Override
	public String toString() {
		return "ServiceDetailsCommonDTO [serviceDetailsId=" + serviceDetailsId + ", nominations=" + nominations
				+ ", promotionExam=" + promotionExam + ", dateOfAppointment=" + dateOfAppointment
				+ ", dateOfAppointmentStr=" + dateOfAppointmentStr + ", dateOfRetirement=" + dateOfRetirement
				+ ", dateOfRetirementStr=" + dateOfRetirementStr + ", probationPeriod=" + probationPeriod
				+ ", entryLevelDepartmentExam=" + entryLevelDepartmentExam + ", regularPromotionDetails="
				+ regularPromotionDetails + ", promotionTimeScale=" + promotionTimeScale + ", regularPayFixation="
				+ regularPayFixation + ", retirementType=" + retirementType + ", medicalCertificate="
				+ medicalCertificate + ", medicalCertificateName=" + medicalCertificateName + ", medicalCertificateURL="
				+ medicalCertificateURL + ", policeVerificationDate=" + policeVerificationDate
				+ ", policeVerificationDateStr=" + policeVerificationDateStr + ", policeVerification="
				+ policeVerification + ", policeVerificationName=" + policeVerificationName + ", policeVerificationURL="
				+ policeVerificationURL + ", permanencyCertificate=" + permanencyCertificate + ", treasuryVerification="
				+ treasuryVerification + ", languageExamCertificate=" + languageExamCertificate
				+ ", awardCertificationOfHonor=" + awardCertificationOfHonor + ", confidentialReports="
				+ confidentialReports + ", mattaAndDayitwaReceived=" + mattaAndDayitwaReceived + ", complaintDetails="
				+ complaintDetails + ", noDuesCertificate=" + noDuesCertificate + ", noEnquiryCertificate="
				+ noEnquiryCertificate + ", typeOfRecord=" + typeOfRecord + ", medicalCertificateDate="
				+ medicalCertificateDate + ", medicalCertificateDateStr=" + medicalCertificateDateStr
				+ ", dateOfSuspension=" + dateOfSuspension + ", reasonOfSuspension=" + reasonOfSuspension
				+ ", dateOfReinstate=" + dateOfReinstate + ", departmentalEnquiryReport=" + departmentalEnquiryReport
				+ ", departmentalEnquiryReportName=" + departmentalEnquiryReportName + ", departmentalEnquiryReportURL="
				+ departmentalEnquiryReportURL + ", punishment=" + punishment + ", dateOfReinstateStr="
				+ dateOfReinstateStr + ", dateOfSuspensionStr=" + dateOfSuspensionStr + ", seniorityList="
				+ seniorityList + ", seniorityListName=" + seniorityListName + ", seniorityListURL=" + seniorityListURL
				+ ", leaveType=" + leaveType + ", leaveFromDate=" + leaveFromDate + ", leaveFromDateStr="
				+ leaveFromDateStr + ", leaveToDate=" + leaveToDate + ", leaveToDateStr=" + leaveToDateStr
				+ ", leaveDocument=" + leaveDocument + ", leaveDocumentName=" + leaveDocumentName
				+ ", leaveDocumentURL=" + leaveDocumentURL + ", userId=" + userId + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", categoryStr=" + categoryStr + ", modifiedStr=" + modifiedStr
				+ ", establishmentDetailId=" + establishmentDetailId + ", treasuryVerificationURLs="
				+ treasuryVerificationURLs + ", treasuryVerificationNames=" + treasuryVerificationNames
				+ ", treasuryVerificationEntryIds=" + treasuryVerificationEntryIds + ", noEnquiryCertificateURLs="
				+ noEnquiryCertificateURLs + ", noEnquiryCertificateNames=" + noEnquiryCertificateNames
				+ ", noEnquiryCertificateEntryIds=" + noEnquiryCertificateEntryIds + ", complaintDetailsURLs="
				+ complaintDetailsURLs + ", complaintDetailsNames=" + complaintDetailsNames
				+ ", complaintDetailsEntryIds=" + complaintDetailsEntryIds + ", noDuesCertificateURLs="
				+ noDuesCertificateURLs + ", noDuesCertificateNames=" + noDuesCertificateNames
				+ ", noDuesCertificateEntryIds=" + noDuesCertificateEntryIds + ", confidentialReportsURLs="
				+ confidentialReportsURLs + ", confidentialReportsNames=" + confidentialReportsNames
				+ ", confidentialReportsEntryIds=" + confidentialReportsEntryIds + ", awardCertificationOfHonorURLs="
				+ awardCertificationOfHonorURLs + ", awardCertificationOfHonorNames=" + awardCertificationOfHonorNames
				+ ", awardCertificationOfHonorEntryIds=" + awardCertificationOfHonorEntryIds
				+ ", languageExamCertificateURLs=" + languageExamCertificateURLs + ", languageExamCertificateNames="
				+ languageExamCertificateNames + ", languageExamCertificateEntryIds=" + languageExamCertificateEntryIds
				+ ", permanencyCertificateURLs=" + permanencyCertificateURLs + ", permanencyCertificateNames="
				+ permanencyCertificateNames + ", permanencyCertificateEntryIds=" + permanencyCertificateEntryIds + "]";
	}

}
