package com.mhdsys.common.pojo;

import java.util.Date;

public class CompetitionScheduleCommonDTO {
	private long competitionScheduledId;
	private long competitionInitiationId;
	private long ptTeacherApplicationId;
	private String competitionName;
	private boolean gender;
	private int districtOrTaluka;
	private Date registrationStartDate;
	private Date registrationEndDate;
	private Date lastSubmissionDate;
	private double fees;
	private String competitionOrganizerName;
	private String competitionOrganizerContact;
	private int srNumber;
	private Date startTime;
	private Date reportingTime;
	private Date createDate;
	private Date modifiedDate;
	private long userId;
	private boolean resultUpload;
	
//	Manupulation
	
	private String createDateStr;
	private String modifiedDateStr;
	private String registrationStartDateStr;
	private String registrationEndDateStr;
	private String lastSubmissionDateStr;
	private String genderName;
	private String districtOrTalukaName;
	private String competitionStartDateStr;
	private String competitionEndDateStr;
	private String formattedStartTime;
	private String formattedReportingTime;
	private String sportName;
	
	
	
	public boolean isResultUpload() {
		return resultUpload;
	}
	public void setResultUpload(boolean resultUpload) {
		this.resultUpload = resultUpload;
	}
	public String getCompetitionEndDateStr() {
		return competitionEndDateStr;
	}
	public void setCompetitionEndDateStr(String competitionEndDateStr) {
		this.competitionEndDateStr = competitionEndDateStr;
	}
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public String getFormattedReportingTime() {
		return formattedReportingTime;
	}
	public void setFormattedReportingTime(String formattedReportingTime) {
		this.formattedReportingTime = formattedReportingTime;
	}
	public String getFormattedStartTime() {
		return formattedStartTime;
	}
	public void setFormattedStartTime(String formattedStartTime) {
		this.formattedStartTime = formattedStartTime;
	}
	public long getCompetitionScheduledId() {
		return competitionScheduledId;
	}
	public void setCompetitionScheduledId(long competitionScheduledId) {
		this.competitionScheduledId = competitionScheduledId;
	}
	public long getCompetitionInitiationId() {
		return competitionInitiationId;
	}
	public String getCompetitionStartDateStr() {
		return competitionStartDateStr;
	}
	public void setCompetitionStartDateStr(String competitionStartDateStr) {
		this.competitionStartDateStr = competitionStartDateStr;
	}
	public void setCompetitionInitiationId(long competitionInitiationId) {
		this.competitionInitiationId = competitionInitiationId;
	}
	public long getPtTeacherApplicationId() {
		return ptTeacherApplicationId;
	}
	public void setPtTeacherApplicationId(long ptTeacherApplicationId) {
		this.ptTeacherApplicationId = ptTeacherApplicationId;
	}
	public String getCompetitionName() {
		return competitionName;
	}
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public int getDistrictOrTaluka() {
		return districtOrTaluka;
	}
	public void setDistrictOrTaluka(int districtOrTaluka) {
		this.districtOrTaluka = districtOrTaluka;
	}
	public Date getRegistrationStartDate() {
		return registrationStartDate;
	}
	public void setRegistrationStartDate(Date registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}
	public Date getRegistrationEndDate() {
		return registrationEndDate;
	}
	public void setRegistrationEndDate(Date registrationEndDate) {
		this.registrationEndDate = registrationEndDate;
	}
	public Date getLastSubmissionDate() {
		return lastSubmissionDate;
	}
	public void setLastSubmissionDate(Date lastSubmissionDate) {
		this.lastSubmissionDate = lastSubmissionDate;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public String getCompetitionOrganizerName() {
		return competitionOrganizerName;
	}
	public void setCompetitionOrganizerName(String competitionOrganizerName) {
		this.competitionOrganizerName = competitionOrganizerName;
	}
	public String getCompetitionOrganizerContact() {
		return competitionOrganizerContact;
	}
	public void setCompetitionOrganizerContact(String competitionOrganizerContact) {
		this.competitionOrganizerContact = competitionOrganizerContact;
	}
	public int getSrNumber() {
		return srNumber;
	}
	public void setSrNumber(int srNumber) {
		this.srNumber = srNumber;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getReportingTime() {
		return reportingTime;
	}
	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public String getRegistrationStartDateStr() {
		return registrationStartDateStr;
	}
	public void setRegistrationStartDateStr(String registrationStartDateStr) {
		this.registrationStartDateStr = registrationStartDateStr;
	}
	public String getRegistrationEndDateStr() {
		return registrationEndDateStr;
	}
	public void setRegistrationEndDateStr(String registrationEndDateStr) {
		this.registrationEndDateStr = registrationEndDateStr;
	}
	public String getLastSubmissionDateStr() {
		return lastSubmissionDateStr;
	}
	public void setLastSubmissionDateStr(String lastSubmissionDateStr) {
		this.lastSubmissionDateStr = lastSubmissionDateStr;
	}
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	public String getDistrictOrTalukaName() {
		return districtOrTalukaName;
	}
	public void setDistrictOrTalukaName(String districtOrTalukaName) {
		this.districtOrTalukaName = districtOrTalukaName;
	}
	
}
