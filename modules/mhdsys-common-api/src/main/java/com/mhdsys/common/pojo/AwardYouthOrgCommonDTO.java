package com.mhdsys.common.pojo;

import java.util.Date;
import java.util.List;

public class AwardYouthOrgCommonDTO {
	 private long awardYouthOrgId;

	    // Personal Details
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String gender;
	    private String address;
	    private long contactNo;
	    private String emailId;

	    // Organisation Details
	    private long attestedCopy;
	    private String attestedCopyName;
	    private String attestedCopyURL;

	    private long constitution;
	    private String constitutionName;
	    private String constitutionURL;

	    private long officersList;
	    private String officersListName;
	    private String officersListURL;

	    // Work Details
	    private String workArea;
	    private String workDetailsPast3Years;
	    private String newsPaperArticle;
	    
	    private List<String> newsPaperArticleURLs;
	    private List<String> newsPaperArticleNames;
	    private List<String> newsPaperArticleEntryIds;
	    
	    private String govtFinancialAssistance;
	    private String areaWorked;
	    private String areaWorkedInfo;
	    private String recognizedByGovt;
	    private String recognizedWorkInfo;
	    private String convicted;
	    private String convictedInfo;
	    private String futurePlans;
	    private String formStatus;
	    
	    private boolean deskDecision;
	    private boolean asstDirDecision;
	    private boolean ddHoDecision;

	    // String remarks fields
	    private String deskRemarks;
	    private String asstDirRemarks;
	    private String ddHoRemarks;


	    // Undertaking
	    private boolean undertakingAccepted;

	    // Default Fields
	    private int status;
	    private long userId;
	    private Date createDate;
	    private Date modifiedDate;
	    private String createDateStr;
	    private String modifiedDateStr;
		public long getAwardYouthOrgId() {
			return awardYouthOrgId;
		}
		public void setAwardYouthOrgId(long awardYouthOrgId) {
			this.awardYouthOrgId = awardYouthOrgId;
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
		public long getAttestedCopy() {
			return attestedCopy;
		}
		public void setAttestedCopy(long attestedCopy) {
			this.attestedCopy = attestedCopy;
		}
		public long getConstitution() {
			return constitution;
		}
		public void setConstitution(long constitution) {
			this.constitution = constitution;
		}
		public long getOfficersList() {
			return officersList;
		}
		public void setOfficersList(long officersList) {
			this.officersList = officersList;
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
		public String getGovtFinancialAssistance() {
			return govtFinancialAssistance;
		}
		public void setGovtFinancialAssistance(String govtFinancialAssistance) {
			this.govtFinancialAssistance = govtFinancialAssistance;
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

		
		public String getAttestedCopyName() {
			return attestedCopyName;
		}
		public void setAttestedCopyName(String attestedCopyName) {
			this.attestedCopyName = attestedCopyName;
		}
		public String getAttestedCopyURL() {
			return attestedCopyURL;
		}
		public void setAttestedCopyURL(String attestedCopyURL) {
			this.attestedCopyURL = attestedCopyURL;
		}
		public String getConstitutionName() {
			return constitutionName;
		}
		public void setConstitutionName(String constitutionName) {
			this.constitutionName = constitutionName;
		}
		public String getConstitutionURL() {
			return constitutionURL;
		}
		public void setConstitutionURL(String constitutionURL) {
			this.constitutionURL = constitutionURL;
		}
		public String getOfficersListName() {
			return officersListName;
		}
		public void setOfficersListName(String officersListName) {
			this.officersListName = officersListName;
		}
		public String getOfficersListURL() {
			return officersListURL;
		}
		public void setOfficersListURL(String officersListURL) {
			this.officersListURL = officersListURL;
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
			return "AwardYouthOrgCommonDTO [awardYouthOrgId=" + awardYouthOrgId + ", firstName=" + firstName
					+ ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", address="
					+ address + ", contactNo=" + contactNo + ", emailId=" + emailId + ", attestedCopy=" + attestedCopy
					+ ", attestedCopyName=" + attestedCopyName + ", attestedCopyURL=" + attestedCopyURL
					+ ", constitution=" + constitution + ", constitutionName=" + constitutionName + ", constitutionURL="
					+ constitutionURL + ", officersList=" + officersList + ", officersListName=" + officersListName
					+ ", officersListURL=" + officersListURL + ", workArea=" + workArea + ", workDetailsPast3Years="
					+ workDetailsPast3Years + ", newsPaperArticle=" + newsPaperArticle + ", newsPaperArticleURLs="
					+ newsPaperArticleURLs + ", newsPaperArticleNames=" + newsPaperArticleNames
					+ ", newsPaperArticleEntryIds=" + newsPaperArticleEntryIds + ", govtFinancialAssistance="
					+ govtFinancialAssistance + ", areaWorked=" + areaWorked + ", areaWorkedInfo=" + areaWorkedInfo
					+ ", recognizedByGovt=" + recognizedByGovt + ", recognizedWorkInfo=" + recognizedWorkInfo
					+ ", convicted=" + convicted + ", convictedInfo=" + convictedInfo + ", futurePlans=" + futurePlans
					+ ", formStatus=" + formStatus + ", deskDecision=" + deskDecision + ", asstDirDecision="
					+ asstDirDecision + ", ddHoDecision=" + ddHoDecision + ", deskRemarks=" + deskRemarks
					+ ", asstDirRemarks=" + asstDirRemarks + ", ddHoRemarks=" + ddHoRemarks + ", undertakingAccepted="
					+ undertakingAccepted + ", status=" + status + ", userId=" + userId + ", createDate=" + createDate
					+ ", modifiedDate=" + modifiedDate + ", createDateStr=" + createDateStr + ", modifiedDateStr="
					+ modifiedDateStr + "]";
		}
		
		
	    
	    
}
