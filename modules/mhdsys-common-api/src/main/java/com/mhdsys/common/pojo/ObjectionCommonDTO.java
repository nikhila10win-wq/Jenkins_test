package com.mhdsys.common.pojo;

public class ObjectionCommonDTO {

	private long objectionId;
	private String playerName;
	private String objectorName;
	private String email;
	private String mobileNo;
	private String category;
	private long sportNo;
	private String awardYear;
	private long sportName;
	private long awardType;
	private long district;
	private String place;
	private String objectionSummary;
	private long userId;
	private String finalPointsByHo;
	private String finalRemarksByHO;
	private String finalRemarksByFed;
	private long objectionFileEntryId;

	private String fileEntryName;
	private String fileURL;

	private String districtStr;
	private String sportNameStr;
	private String awardStr;

	private String doVerification;
	private String doRemarks;
	private String ddVerification;
	private String ddRemarks;

	public long getObjectionId() {
		return objectionId;
	}

	public void setObjectionId(long objectionId) {
		this.objectionId = objectionId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getObjectorName() {
		return objectorName;
	}

	public void setObjectorName(String objectorName) {
		this.objectorName = objectorName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getSportNo() {
		return sportNo;
	}

	public void setSportNo(long sportNo) {
		this.sportNo = sportNo;
	}

	public String getAwardYear() {
		return awardYear;
	}

	public void setAwardYear(String awardYear) {
		this.awardYear = awardYear;
	}

	public long getSportName() {
		return sportName;
	}

	public void setSportName(long sportName) {
		this.sportName = sportName;
	}

	public long getAwardType() {
		return awardType;
	}

	public void setAwardType(long awardType) {
		this.awardType = awardType;
	}

	public long getDistrict() {
		return district;
	}

	public void setDistrict(long district) {
		this.district = district;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getObjectionSummary() {
		return objectionSummary;
	}

	public void setObjectionSummary(String objectionSummary) {
		this.objectionSummary = objectionSummary;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFinalPointsByHo() {
		return finalPointsByHo;
	}

	public void setFinalPointsByHo(String finalPointsByHo) {
		this.finalPointsByHo = finalPointsByHo;
	}

	public String getFinalRemarksByHO() {
		return finalRemarksByHO;
	}

	public void setFinalRemarksByHO(String finalRemarksByHO) {
		this.finalRemarksByHO = finalRemarksByHO;
	}

	public String getFinalRemarksByFed() {
		return finalRemarksByFed;
	}

	public void setFinalRemarksByFed(String finalRemarksByFed) {
		this.finalRemarksByFed = finalRemarksByFed;
	}

	public long getObjectionFileEntryId() {
		return objectionFileEntryId;
	}

	public void setObjectionFileEntryId(long objectionFileEntryId) {
		this.objectionFileEntryId = objectionFileEntryId;
	}

	public String getFileEntryName() {
		return fileEntryName;
	}

	public void setFileEntryName(String fileEntryName) {
		this.fileEntryName = fileEntryName;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public String getDistrictStr() {
		return districtStr;
	}

	public void setDistrictStr(String districtStr) {
		this.districtStr = districtStr;
	}

	public String getSportNameStr() {
		return sportNameStr;
	}

	public void setSportNameStr(String sportNameStr) {
		this.sportNameStr = sportNameStr;
	}

	public String getAwardStr() {
		return awardStr;
	}

	public void setAwardStr(String awardStr) {
		this.awardStr = awardStr;
	}

	public String getDoVerification() {
		return doVerification;
	}

	public void setDoVerification(String doVerification) {
		this.doVerification = doVerification;
	}

	public String getDoRemarks() {
		return doRemarks;
	}

	public void setDoRemarks(String doRemarks) {
		this.doRemarks = doRemarks;
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
		return "ObjectionCommonDTO [objectionId=" + objectionId + ", playerName=" + playerName + ", objectorName="
				+ objectorName + ", email=" + email + ", mobileNo=" + mobileNo + ", category=" + category + ", sportNo="
				+ sportNo + ", awardYear=" + awardYear + ", sportName=" + sportName + ", awardType=" + awardType
				+ ", district=" + district + ", place=" + place + ", objectionSummary=" + objectionSummary + ", userId="
				+ userId + ", finalPointsByHo=" + finalPointsByHo + ", finalRemarksByHO=" + finalRemarksByHO
				+ ", finalRemarksByFed=" + finalRemarksByFed + ", objectionFileEntryId=" + objectionFileEntryId
				+ ", fileEntryName=" + fileEntryName + ", fileURL=" + fileURL + ", districtStr=" + districtStr
				+ ", sportNameStr=" + sportNameStr + ", awardStr=" + awardStr + ", doVerification=" + doVerification
				+ ", doRemarks=" + doRemarks + ", ddVerification=" + ddVerification + ", ddRemarks=" + ddRemarks + "]";
	}

}
