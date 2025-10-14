package com.mhdsys.common.pojo;

import java.util.Date;

public class ConstructionTrackerCommonDTO {
	// Primary Key
		private long constructionTrackerId;
	
//		Name of Sports Complex
		private String division;
		private String district;
		private String taluka;
		private String divisionId;
		private String districtId;
		private String talukaId;
		private String statusOfComplex;
		
//		Address Details
		private String address;
		private String latitude;
		private String longitude;
		private String GeoTagPhoto;
		
//		Officer Details
		private String officerName;
		private String contactNumber;
		
//		Land details
		private String landAvailable;
		private String typeOfLand;
		private long area;
		private String ownership;
		
//		Administration
		private String administrationJSON;
		
//		Funds
		private String fundsJSON;
		
//		Govt. Grants
		private String govtGrantsJSON;
		
//		Other Sources
		private String otherSourcesJSON;
		
//		Other
		private String otherTotalReceivedAmount;
		private String otherTotalExpenditureAmount;
		private String otherTotalBalanceAmount;
		
//		Agency Details
		private String architectAppointed;
		private String executingAgency;
		private String nameOfTheFirm;
		
//		Progress details
		private String progressDetailsJSON;
		
//		UC/CC Status
		private String UCCCStatus;
		private String UCCCAmount;
		private String UCCCExtension;
		private Date UCCCRevisedCompletionDate;
		private String workCompletedInTimeline;
		private String UCCCReason;
		private String panalAction;
		private long panalActionDoc;
		
//		HO Review
		private String hoReview;
		private long hoReviewDoc;
		
		
		
//		Status
		private long applicationStatus;
		private long holdWithUserId;
		private boolean isSaveAsDraft;
		
		private String constructionTrackerUniqueId;
		private long IntiatorUserId;
		
		private String dddReview;
		private long dddReviewDoc;
		
		private Date createdDate;
		private Date modifiedDate;
		
		public String getDivisionId() {
			return divisionId;
		}
		public void setDivisionId(String divisionId) {
			this.divisionId = divisionId;
		}
		public String getDistrictId() {
			return districtId;
		}
		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}
		public String getTalukaId() {
			return talukaId;
		}
		public void setTalukaId(String talukaId) {
			this.talukaId = talukaId;
		}
		public void setSaveAsDraft(boolean isSaveAsDraft) {
			this.isSaveAsDraft = isSaveAsDraft;
		}
		public long getConstructionTrackerId() {
			return constructionTrackerId;
		}
		public void setConstructionTrackerId(long constructionTrackerId) {
			this.constructionTrackerId = constructionTrackerId;
		}
		public String getDivision() {
			return division;
		}
		public void setDivision(String division) {
			this.division = division;
		}
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public String getTaluka() {
			return taluka;
		}
		public void setTaluka(String taluka) {
			this.taluka = taluka;
		}
		public String getStatusOfComplex() {
			return statusOfComplex;
		}
		public void setStatusOfComplex(String statusOfComplex) {
			this.statusOfComplex = statusOfComplex;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getGeoTagPhoto() {
			return GeoTagPhoto;
		}
		public void setGeoTagPhoto(String geoTagPhoto) {
			GeoTagPhoto = geoTagPhoto;
		}
		public String getOfficerName() {
			return officerName;
		}
		public void setOfficerName(String officerName) {
			this.officerName = officerName;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getLandAvailable() {
			return landAvailable;
		}
		public void setLandAvailable(String landAvailable) {
			this.landAvailable = landAvailable;
		}
		public String getTypeOfLand() {
			return typeOfLand;
		}
		public void setTypeOfLand(String typeOfLand) {
			this.typeOfLand = typeOfLand;
		}
		public long getArea() {
			return area;
		}
		public void setArea(long area) {
			this.area = area;
		}
		public String getOwnership() {
			return ownership;
		}
		public void setOwnership(String ownership) {
			this.ownership = ownership;
		}
		public String getAdministrationJSON() {
			return administrationJSON;
		}
		public void setAdministrationJSON(String administrationJSON) {
			this.administrationJSON = administrationJSON;
		}
		public String getFundsJSON() {
			return fundsJSON;
		}
		public void setFundsJSON(String fundsJSON) {
			this.fundsJSON = fundsJSON;
		}
		public String getGovtGrantsJSON() {
			return govtGrantsJSON;
		}
		public void setGovtGrantsJSON(String govtGrantsJSON) {
			this.govtGrantsJSON = govtGrantsJSON;
		}
		public String getOtherSourcesJSON() {
			return otherSourcesJSON;
		}
		public void setOtherSourcesJSON(String otherSourcesJSON) {
			this.otherSourcesJSON = otherSourcesJSON;
		}
		public String getOtherTotalReceivedAmount() {
			return otherTotalReceivedAmount;
		}
		public void setOtherTotalReceivedAmount(String otherTotalReceivedAmount) {
			this.otherTotalReceivedAmount = otherTotalReceivedAmount;
		}
		public String getOtherTotalExpenditureAmount() {
			return otherTotalExpenditureAmount;
		}
		public void setOtherTotalExpenditureAmount(String otherTotalExpenditureAmount) {
			this.otherTotalExpenditureAmount = otherTotalExpenditureAmount;
		}
		public String getOtherTotalBalanceAmount() {
			return otherTotalBalanceAmount;
		}
		public void setOtherTotalBalanceAmount(String otherTotalBalanceAmount) {
			this.otherTotalBalanceAmount = otherTotalBalanceAmount;
		}
		public String getArchitectAppointed() {
			return architectAppointed;
		}
		public void setArchitectAppointed(String architectAppointed) {
			this.architectAppointed = architectAppointed;
		}
		public String getExecutingAgency() {
			return executingAgency;
		}
		public void setExecutingAgency(String executingAgency) {
			this.executingAgency = executingAgency;
		}
		public String getNameOfTheFirm() {
			return nameOfTheFirm;
		}
		public void setNameOfTheFirm(String nameOfTheFirm) {
			this.nameOfTheFirm = nameOfTheFirm;
		}
		public String getProgressDetailsJSON() {
			return progressDetailsJSON;
		}
		public void setProgressDetailsJSON(String progressDetailsJSON) {
			this.progressDetailsJSON = progressDetailsJSON;
		}
		public String getUCCCStatus() {
			return UCCCStatus;
		}
		public void setUCCCStatus(String uCCCStatus) {
			UCCCStatus = uCCCStatus;
		}
		public String getUCCCAmount() {
			return UCCCAmount;
		}
		public void setUCCCAmount(String uCCCAmount) {
			UCCCAmount = uCCCAmount;
		}
		public String getUCCCExtension() {
			return UCCCExtension;
		}
		public void setUCCCExtension(String uCCCExtension) {
			UCCCExtension = uCCCExtension;
		}
		public Date getUCCCRevisedCompletionDate() {
			return UCCCRevisedCompletionDate;
		}
		public void setUCCCRevisedCompletionDate(Date uCCCRevisedCompletionDate) {
			UCCCRevisedCompletionDate = uCCCRevisedCompletionDate;
		}
		public String getWorkCompletedInTimeline() {
			return workCompletedInTimeline;
		}
		public void setWorkCompletedInTimeline(String workCompletedInTimeline) {
			this.workCompletedInTimeline = workCompletedInTimeline;
		}
		public String getUCCCReason() {
			return UCCCReason;
		}
		public void setUCCCReason(String uCCCReason) {
			UCCCReason = uCCCReason;
		}
		public String getPanalAction() {
			return panalAction;
		}
		public void setPanalAction(String panalAction) {
			this.panalAction = panalAction;
		}
		public long getPanalActionDoc() {
			return panalActionDoc;
		}
		public void setPanalActionDoc(long panalActionDoc) {
			this.panalActionDoc = panalActionDoc;
		}
		public String getHoReview() {
			return hoReview;
		}
		public void setHoReview(String hoReview) {
			this.hoReview = hoReview;
		}
		public long getHoReviewDoc() {
			return hoReviewDoc;
		}
		public void setHoReviewDoc(long hoReviewDoc) {
			this.hoReviewDoc = hoReviewDoc;
		}
		public long getApplicationStatus() {
			return applicationStatus;
		}
		public void setApplicationStatus(long applicationStatus) {
			this.applicationStatus = applicationStatus;
		}
		public long getHoldWithUserId() {
			return holdWithUserId;
		}
		public void setHoldWithUserId(long holdWithUserId) {
			this.holdWithUserId = holdWithUserId;
		}
		public boolean getIsSaveAsDraft() {
			return isSaveAsDraft;
		}
		public void setIsSaveAsDraft(boolean isSaveAsDraft) {
			this.isSaveAsDraft = isSaveAsDraft;
		}
		
		public String getConstructionTrackerUniqueId() {
			return constructionTrackerUniqueId;
		}
		public void setConstructionTrackerUniqueId(String constructionTrackerUniqueId) {
			this.constructionTrackerUniqueId = constructionTrackerUniqueId;
		}
		public long getIntiatorUserId() {
			return IntiatorUserId;
		}
		public void setIntiatorUserId(long intiatorUserId) {
			IntiatorUserId = intiatorUserId;
		}
		public String getDddReview() {
			return dddReview;
		}
		public void setDddReview(String dddReview) {
			this.dddReview = dddReview;
		}
		public long getDddReviewDoc() {
			return dddReviewDoc;
		}
		public void setDddReviewDoc(long dddReviewDoc) {
			this.dddReviewDoc = dddReviewDoc;
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
		@Override
		public String toString() {
			return "ConstructionTrackerCommonDTO [constructionTrackerId=" + constructionTrackerId + ", division="
					+ division + ", district=" + district + ", taluka=" + taluka + ", divisionId=" + divisionId
					+ ", districtId=" + districtId + ", talukaId=" + talukaId + ", statusOfComplex=" + statusOfComplex
					+ ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", GeoTagPhoto="
					+ GeoTagPhoto + ", officerName=" + officerName + ", contactNumber=" + contactNumber
					+ ", landAvailable=" + landAvailable + ", typeOfLand=" + typeOfLand + ", area=" + area
					+ ", ownership=" + ownership + ", administrationJSON=" + administrationJSON + ", fundsJSON="
					+ fundsJSON + ", govtGrantsJSON=" + govtGrantsJSON + ", otherSourcesJSON=" + otherSourcesJSON
					+ ", otherTotalReceivedAmount=" + otherTotalReceivedAmount + ", otherTotalExpenditureAmount="
					+ otherTotalExpenditureAmount + ", otherTotalBalanceAmount=" + otherTotalBalanceAmount
					+ ", architectAppointed=" + architectAppointed + ", executingAgency=" + executingAgency
					+ ", nameOfTheFirm=" + nameOfTheFirm + ", progressDetailsJSON=" + progressDetailsJSON
					+ ", UCCCStatus=" + UCCCStatus + ", UCCCAmount=" + UCCCAmount + ", UCCCExtension=" + UCCCExtension
					+ ", UCCCRevisedCompletionDate=" + UCCCRevisedCompletionDate + ", workCompletedInTimeline="
					+ workCompletedInTimeline + ", UCCCReason=" + UCCCReason + ", panalAction=" + panalAction
					+ ", panalActionDoc=" + panalActionDoc + ", hoReview=" + hoReview + ", hoReviewDoc=" + hoReviewDoc
					+ ", applicationStatus=" + applicationStatus + ", holdWithUserId=" + holdWithUserId
					+ ", isSaveAsDraft=" + isSaveAsDraft + ", constructionTrackerUniqueId="
					+ constructionTrackerUniqueId + ", IntiatorUserId=" + IntiatorUserId + ", dddReview=" + dddReview
					+ ", dddReviewDoc=" + dddReviewDoc + ", createdDate=" + createdDate + ", modifiedDate="
					+ modifiedDate + "]";
		}
		
		
		
		
}
