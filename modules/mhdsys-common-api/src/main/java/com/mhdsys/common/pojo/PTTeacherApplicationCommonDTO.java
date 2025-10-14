package com.mhdsys.common.pojo;

import java.util.Date;

public class PTTeacherApplicationCommonDTO {
	private long ptTeacherApplicationId;
	private String applicationNumber;
	private long competitionInitiationId;
	private double fees;
	private double affiliationFees;
	private double totalFees;
	private int countOfTeamOrIndividual;
	private long userId;
	private long status;
	private Date createDate;
	private Date modifiedDate;
	private long feesRecieptFileEntryId;
	private String participantName;
	private long participantUserId;
	private boolean scheduled;
	
	private String scheduledStr;
	private String statusName;
	private String sportName;
	private String fileEntryName;
	private String fileURL;
	

	public long getParticipantUserId() {
		return participantUserId;
	}

	public void setParticipantUserId(long participantUserId) {
		this.participantUserId = participantUserId;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public String getScheduledStr() {
		return scheduledStr;
	}

	public void setScheduledStr(String scheduledStr) {
		this.scheduledStr = scheduledStr;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public long getFeesRecieptFileEntryId() {
		return feesRecieptFileEntryId;
	}

	public void setFeesRecieptFileEntryId(long feesRecieptFileEntryId) {
		this.feesRecieptFileEntryId = feesRecieptFileEntryId;
	}

	public String getFileEntryName() {
		return fileEntryName;
	}

	public void setFileEntryName(String fileEntryName) {
		this.fileEntryName = fileEntryName;
	}

	public long getPtTeacherApplicationId() {
		return ptTeacherApplicationId;
	}

	public void setPtTeacherApplicationId(long ptTeacherApplicationId) {
		this.ptTeacherApplicationId = ptTeacherApplicationId;
	}

	public long getCompetitionInitiationId() {
		return competitionInitiationId;
	}

	public void setCompetitionInitiationId(long competitionInitiationId) {
		this.competitionInitiationId = competitionInitiationId;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public double getAffiliationFees() {
		return affiliationFees;
	}

	public void setAffiliationFees(double affiliationFees) {
		this.affiliationFees = affiliationFees;
	}

	public double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(double totalFees) {
		this.totalFees = totalFees;
	}

	public int getCountOfTeamOrIndividual() {
		return countOfTeamOrIndividual;
	}

	public void setCountOfTeamOrIndividual(int countOfTeamOrIndividual) {
		this.countOfTeamOrIndividual = countOfTeamOrIndividual;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


	
	
}
