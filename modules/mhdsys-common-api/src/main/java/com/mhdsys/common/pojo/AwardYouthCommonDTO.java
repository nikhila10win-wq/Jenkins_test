package com.mhdsys.common.pojo;

import java.util.Date;
import java.util.List;

public class AwardYouthCommonDTO {
	  private long awardYouthId;

	    // Personal Details
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String gender;
	    private String address;
	    private long contactNo;
	    private String emailId;
	    private Date dob;
	    private String dobStr;
	    private long birthCertificate;
	    private String birthCertificateName;
		private String birthCertificateURL;

	    // Education
	    private String educationDetails;

	    // Work Details
	    private String businessDetails;
	    private String workArea;
	    private String workDetailsPast3Years;
	    private String newsPaperArticle;
	    
	    private List<String> newsPaperArticleURLs;
	    private List<String> newsPaperArticleNames;
	    private List<String> newsPaperArticleEntryIds;

	    // Social Responsibility
	    private long socialResponsibility;
	    private String socialResponsibilityName;
	    private String socialResponsibilityURL;
	    private String areaWorked;
	    private String areaWorkedInfo;

	    // Recognition
	    private String recognizedByGovt;
	    private String recognizedWorkInfo;

	    // Staff
	    private String staffOf;
	    private String staffAffiliationInfo;

	    // Criminal Case
	    private String convicted;
	    private String convictedInfo;

	    // Future Plans
	    private String futurePlans;

	    // Undertaking
	    private boolean undertakingAccepted;
	    private String formStatus;

	    private int status;
	    
	    private boolean deskDecision;
	    private boolean asstDirDecision;
	    private boolean ddHoDecision;

	    // String remarks fields
	    private String deskRemarks;
	    private String asstDirRemarks;
	    private String ddHoRemarks;


	    // Audit
	    private long userId;
	    private Date createDate;
	    private Date modifiedDate;
	    private String createDateStr;
	    private String modifiedDateStr;
		public long getAwardYouthId() {
			return awardYouthId;
		}
		public void setAwardYouthId(long awardYouthId) {
			this.awardYouthId = awardYouthId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public long getContactNo() {
			return contactNo;
		}
		public void setContactNo(long contactNo) {
			this.contactNo = contactNo;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
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
		public long getBirthCertificate() {
			return birthCertificate;
		}
		public void setBirthCertificate(long birthCertificate) {
			this.birthCertificate = birthCertificate;
		}
		public String getEducationDetails() {
			return educationDetails;
		}
		public void setEducationDetails(String educationDetails) {
			this.educationDetails = educationDetails;
		}
		public String getBusinessDetails() {
			return businessDetails;
		}
		public void setBusinessDetails(String businessDetails) {
			this.businessDetails = businessDetails;
		}
		public String getWorkArea() {
			return workArea;
		}
		public void setWorkArea(String workArea) {
			this.workArea = workArea;
		}
		public String getWorkDetailsPast3Years() {
			return workDetailsPast3Years;
		}
		public void setWorkDetailsPast3Years(String workDetailsPast3Years) {
			this.workDetailsPast3Years = workDetailsPast3Years;
		}
		public String getNewsPaperArticle() {
			return newsPaperArticle;
		}
		public void setNewsPaperArticle(String newsPaperArticle) {
			this.newsPaperArticle = newsPaperArticle;
		}
		public List<String> getNewsPaperArticleURLs() {
			return newsPaperArticleURLs;
		}
		public void setNewsPaperArticleURLs(List<String> newsPaperArticleURLs) {
			this.newsPaperArticleURLs = newsPaperArticleURLs;
		}
		public List<String> getNewsPaperArticleNames() {
			return newsPaperArticleNames;
		}
		public void setNewsPaperArticleNames(List<String> newsPaperArticleNames) {
			this.newsPaperArticleNames = newsPaperArticleNames;
		}
		public List<String> getNewsPaperArticleEntryIds() {
			return newsPaperArticleEntryIds;
		}
		public void setNewsPaperArticleEntryIds(List<String> newsPaperArticleEntryIds) {
			this.newsPaperArticleEntryIds = newsPaperArticleEntryIds;
		}
		public long getSocialResponsibility() {
			return socialResponsibility;
		}
		public void setSocialResponsibility(long socialResponsibility) {
			this.socialResponsibility = socialResponsibility;
		}
		public String getAreaWorked() {
			return areaWorked;
		}
		public void setAreaWorked(String areaWorked) {
			this.areaWorked = areaWorked;
		}
		public String getAreaWorkedInfo() {
			return areaWorkedInfo;
		}
		public void setAreaWorkedInfo(String areaWorkedInfo) {
			this.areaWorkedInfo = areaWorkedInfo;
		}
		public String getRecognizedByGovt() {
			return recognizedByGovt;
		}
		public void setRecognizedByGovt(String recognizedByGovt) {
			this.recognizedByGovt = recognizedByGovt;
		}
		public String getRecognizedWorkInfo() {
			return recognizedWorkInfo;
		}
		public void setRecognizedWorkInfo(String recognizedWorkInfo) {
			this.recognizedWorkInfo = recognizedWorkInfo;
		}
		public String getStaffOf() {
			return staffOf;
		}
		public void setStaffOf(String staffOf) {
			this.staffOf = staffOf;
		}
		public String getStaffAffiliationInfo() {
			return staffAffiliationInfo;
		}
		public void setStaffAffiliationInfo(String staffAffiliationInfo) {
			this.staffAffiliationInfo = staffAffiliationInfo;
		}
		public String getConvicted() {
			return convicted;
		}
		public void setConvicted(String convicted) {
			this.convicted = convicted;
		}
		public String getConvictedInfo() {
			return convictedInfo;
		}
		public void setConvictedInfo(String convictedInfo) {
			this.convictedInfo = convictedInfo;
		}
		public String getFuturePlans() {
			return futurePlans;
		}
		public void setFuturePlans(String futurePlans) {
			this.futurePlans = futurePlans;
		}
		public boolean isUndertakingAccepted() {
			return undertakingAccepted;
		}
		public void setUndertakingAccepted(boolean undertakingAccepted) {
			this.undertakingAccepted = undertakingAccepted;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
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
		public String getBirthCertificateName() {
			return birthCertificateName;
		}
		public void setBirthCertificateName(String birthCertificateName) {
			this.birthCertificateName = birthCertificateName;
		}
		public String getBirthCertificateURL() {
			return birthCertificateURL;
		}
		public void setBirthCertificateURL(String birthCertificateURL) {
			this.birthCertificateURL = birthCertificateURL;
		}
		
		
		
		public String getSocialResponsibilityName() {
			return socialResponsibilityName;
		}
		public void setSocialResponsibilityName(String socialResponsibilityName) {
			this.socialResponsibilityName = socialResponsibilityName;
		}
		public String getSocialResponsibilityURL() {
			return socialResponsibilityURL;
		}
		public void setSocialResponsibilityURL(String socialResponsibilityURL) {
			this.socialResponsibilityURL = socialResponsibilityURL;
		}
		
		public String getFormStatus() {
			return formStatus;
		}
		public void setFormStatus(String formStatus) {
			this.formStatus = formStatus;
		}
		
		
		public boolean isDeskDecision() {
			return deskDecision;
		}
		public void setDeskDecision(boolean deskDecision) {
			this.deskDecision = deskDecision;
		}
		public boolean isAsstDirDecision() {
			return asstDirDecision;
		}
		public void setAsstDirDecision(boolean asstDirDecision) {
			this.asstDirDecision = asstDirDecision;
		}
		public boolean isDdHoDecision() {
			return ddHoDecision;
		}
		public void setDdHoDecision(boolean ddHoDecision) {
			this.ddHoDecision = ddHoDecision;
		}
		public String getDeskRemarks() {
			return deskRemarks;
		}
		public void setDeskRemarks(String deskRemarks) {
			this.deskRemarks = deskRemarks;
		}
		public String getAsstDirRemarks() {
			return asstDirRemarks;
		}
		public void setAsstDirRemarks(String asstDirRemarks) {
			this.asstDirRemarks = asstDirRemarks;
		}
		public String getDdHoRemarks() {
			return ddHoRemarks;
		}
		public void setDdHoRemarks(String ddHoRemarks) {
			this.ddHoRemarks = ddHoRemarks;
		}
		@Override
		public String toString() {
			return "AwardYouthCommonDTO [awardYouthId=" + awardYouthId + ", firstName=" + firstName + ", middleName="
					+ middleName + ", lastName=" + lastName + ", gender=" + gender + ", address=" + address
					+ ", contactNo=" + contactNo + ", emailId=" + emailId + ", dob=" + dob + ", dobStr=" + dobStr
					+ ", birthCertificate=" + birthCertificate + ", birthCertificateName=" + birthCertificateName
					+ ", birthCertificateURL=" + birthCertificateURL + ", educationDetails=" + educationDetails
					+ ", businessDetails=" + businessDetails + ", workArea=" + workArea + ", workDetailsPast3Years="
					+ workDetailsPast3Years + ", newsPaperArticle=" + newsPaperArticle + ", newsPaperArticleURLs="
					+ newsPaperArticleURLs + ", newsPaperArticleNames=" + newsPaperArticleNames
					+ ", newsPaperArticleEntryIds=" + newsPaperArticleEntryIds + ", socialResponsibility="
					+ socialResponsibility + ", socialResponsibilityName=" + socialResponsibilityName
					+ ", socialResponsibilityURL=" + socialResponsibilityURL + ", areaWorked=" + areaWorked
					+ ", areaWorkedInfo=" + areaWorkedInfo + ", recognizedByGovt=" + recognizedByGovt
					+ ", recognizedWorkInfo=" + recognizedWorkInfo + ", staffOf=" + staffOf + ", staffAffiliationInfo="
					+ staffAffiliationInfo + ", convicted=" + convicted + ", convictedInfo=" + convictedInfo
					+ ", futurePlans=" + futurePlans + ", undertakingAccepted=" + undertakingAccepted + ", formStatus="
					+ formStatus + ", status=" + status + ", deskDecision=" + deskDecision + ", asstDirDecision="
					+ asstDirDecision + ", ddHoDecision=" + ddHoDecision + ", deskRemarks=" + deskRemarks
					+ ", asstDirRemarks=" + asstDirRemarks + ", ddHoRemarks=" + ddHoRemarks + ", userId=" + userId
					+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", createDateStr="
					+ createDateStr + ", modifiedDateStr=" + modifiedDateStr + "]";
		}
		
	    
}
