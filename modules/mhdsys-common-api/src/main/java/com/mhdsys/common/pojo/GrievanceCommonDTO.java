package com.mhdsys.common.pojo;

import java.util.Date;

public class GrievanceCommonDTO {
	 private long grievanceId;

	    // Administration
	    private long division;
	    private long district;
	    private long taluka;

	    // Complaint Details
	    private Date complaintDate;
	    private String complaintType;
	    private String complaintText;

	    // Self-Declaration
	    private boolean selfDeclaration;
	    private long transferApplication;

	    // Audit Fields
	    private long userId;
	    private Date createDate;
	    private Date modifiedDate;
	    
	    private String status;
	    private String divisionName;
	    private String districtName;
	    private String talukaName;
	    private String complaintDateStr;
	    private String createDateStr;
	    private String modifiedDateStr;
	    
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public long getTransferApplication() {
			return transferApplication;
		}
		public void setTransferApplication(long transferApplication) {
			this.transferApplication = transferApplication;
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
		public long getGrievanceId() {
			return grievanceId;
		}
		public void setGrievanceId(long grievanceId) {
			this.grievanceId = grievanceId;
		}
		public long getDivision() {
			return division;
		}
		public void setDivision(long division) {
			this.division = division;
		}
		public long getDistrict() {
			return district;
		}
		public void setDistrict(long district) {
			this.district = district;
		}
		public long getTaluka() {
			return taluka;
		}
		public void setTaluka(long taluka) {
			this.taluka = taluka;
		}
		public Date getComplaintDate() {
			return complaintDate;
		}
		public void setComplaintDate(Date complaintDate) {
			this.complaintDate = complaintDate;
		}
		public String getComplaintType() {
			return complaintType;
		}
		public void setComplaintType(String complaintType) {
			this.complaintType = complaintType;
		}
		public String getComplaintText() {
			return complaintText;
		}
		public void setComplaintText(String complaintText) {
			this.complaintText = complaintText;
		}
		public boolean isSelfDeclaration() {
			return selfDeclaration;
		}
		public void setSelfDeclaration(boolean selfDeclaration) {
			this.selfDeclaration = selfDeclaration;
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
		public String getDivisionName() {
			return divisionName;
		}
		public void setDivisionName(String divisionName) {
			this.divisionName = divisionName;
		}
		public String getDistrictName() {
			return districtName;
		}
		public void setDistrictName(String districtName) {
			this.districtName = districtName;
		}
		public String getTalukaName() {
			return talukaName;
		}
		public void setTalukaName(String talukaName) {
			this.talukaName = talukaName;
		}
		public String getComplaintDateStr() {
			return complaintDateStr;
		}
		public void setComplaintDateStr(String complaintDateStr) {
			this.complaintDateStr = complaintDateStr;
		}


}
