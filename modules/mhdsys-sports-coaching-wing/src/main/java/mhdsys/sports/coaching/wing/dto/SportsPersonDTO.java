package mhdsys.sports.coaching.wing.dto;

import java.util.Date;

public class SportsPersonDTO {
	
	private long trainingCentreId;
	long sportsPersonId;
	String fullName;
	Date dateOfBirth;
	String dateOfBirthString;
	String schoolName;
	String mobileNumber;
	String address;
	Date entryDate;
	String entryDateStr;
	String ranking;
	String achievementLevel;
	String remarks;
	Date createDate;
	Date modifiedDate;
	String formStatus;
	
	public SportsPersonDTO() {
	}
	
	public SportsPersonDTO(long trainingCentreId, long sportsPersonId, String fullName, Date dateOfBirth,
			String schoolName, String mobileNumber, String address, Date entryDate, String ranking,
			String achievementLevel, String remarks, Date createDate, Date modifiedDate, String formStatus) {
		super();
		this.trainingCentreId = trainingCentreId;
		this.sportsPersonId = sportsPersonId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.schoolName = schoolName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.entryDate = entryDate;
		this.ranking = ranking;
		this.achievementLevel = achievementLevel;
		this.remarks = remarks;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.formStatus = formStatus;
	}
	public long getTrainingCentreId() {
		return trainingCentreId;
	}
	public void setTrainingCentreId(long trainingCentreId) {
		this.trainingCentreId = trainingCentreId;
	}
	public long getSportsPersonId() {
		return sportsPersonId;
	}
	public void setSportsPersonId(long sportsPersonId) {
		this.sportsPersonId = sportsPersonId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getAchievementLevel() {
		return achievementLevel;
	}
	public void setAchievementLevel(String achievementLevel) {
		this.achievementLevel = achievementLevel;
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
	
	public String getDateOfBirthString() {
		return dateOfBirthString;
	}

	public void setDateOfBirthString(String dateOfBirthString) {
		this.dateOfBirthString = dateOfBirthString;
	}
	
	

	public String getEntryDateStr() {
		return entryDateStr;
	}

	public void setEntryDateStr(String entryDateStr) {
		this.entryDateStr = entryDateStr;
	}

	@Override
	public String toString() {
		return "SportsPersonDTO [trainingCentreId=" + trainingCentreId + ", sportsPersonId=" + sportsPersonId
				+ ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", schoolName=" + schoolName
				+ ", mobileNumber=" + mobileNumber + ", address=" + address + ", entryDate=" + entryDate + ", ranking="
				+ ranking + ", achievementLevel=" + achievementLevel + ", remarks=" + remarks + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + ", formStatus=" + formStatus + "]";
	}
	
	
	
	
}	

