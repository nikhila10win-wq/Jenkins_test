package mhdsys.sports.coaching.wing.dto;

import java.util.Date;

public class CoachDTO {
	private long trainingCentreId;
	private long coachId;
	private String coachType;
	private String fullName;
	private String mobileNumber;
	private String email;
	private String sportsName;
	private String address;
	private String tracksuitSize;
	private String tshirtSize;
	private String shortsSize;
	private String shoesSize;
	private String remarks;
	private Date createDate;
	private Date modifiedDate;
	private String formStatus;
	private String district;
	private String districtName;
	private String division;
	private String divisionName;
	
	
	
	public CoachDTO() {
		
	}
	public CoachDTO(long trainingCentreId, long coachId, String coachType, String fullName, String mobileNumber,
			String email, String sportsName, String address, String tracksuitSize, String tshirtSize, String shortsSize,
			String shoesSize, String remarks, Date createDate, Date modifiedDate, String formStatus, String district) {
		this.trainingCentreId = trainingCentreId;
		this.coachId = coachId;
		this.coachType = coachType;
		this.fullName = fullName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.sportsName = sportsName;
		this.address = address;
		this.tracksuitSize = tracksuitSize;
		this.tshirtSize = tshirtSize;
		this.shortsSize = shortsSize;
		this.shoesSize = shoesSize;
		this.remarks = remarks;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.formStatus = formStatus;
		this.district = district;
	}
	public long getTrainingCentreId() {
		return trainingCentreId;
	}
	public void setTrainingCentreId(long trainingCentreId) {
		this.trainingCentreId = trainingCentreId;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public String getCoachType() {
		return coachType;
	}
	public void setCoachType(String coachType) {
		this.coachType = coachType;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSportsName() {
		return sportsName;
	}
	public void setSportsName(String sportsName) {
		this.sportsName = sportsName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTracksuitSize() {
		return tracksuitSize;
	}
	public void setTracksuitSize(String tracksuitSize) {
		this.tracksuitSize = tracksuitSize;
	}
	public String getTshirtSize() {
		return tshirtSize;
	}
	public void setTshirtSize(String tshirtSize) {
		this.tshirtSize = tshirtSize;
	}
	public String getShortsSize() {
		return shortsSize;
	}
	public void setShortsSize(String shortsSize) {
		this.shortsSize = shortsSize;
	}
	public String getShoesSize() {
		return shoesSize;
	}
	public void setShoesSize(String shoesSize) {
		this.shoesSize = shoesSize;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getFormStatus() {
		return formStatus;
	}
	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	@Override
	public String toString() {
		return "CoachDTO [trainingCentreId=" + trainingCentreId + ", coachId=" + coachId + ", coachType=" + coachType
				+ ", fullName=" + fullName + ", mobileNumber=" + mobileNumber + ", email=" + email + ", sportsName="
				+ sportsName + ", address=" + address + ", tracksuitSize=" + tracksuitSize + ", tshirtSize="
				+ tshirtSize + ", shortsSize=" + shortsSize + ", shoesSize=" + shoesSize + ", remarks=" + remarks
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", formStatus=" + formStatus
				+ ", district=" + district + "]";
	}
}	
