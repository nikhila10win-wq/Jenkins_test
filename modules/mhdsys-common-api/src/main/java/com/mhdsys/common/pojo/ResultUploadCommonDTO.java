package com.mhdsys.common.pojo;

import java.util.Date;

public class ResultUploadCommonDTO {
	public long competitionResultUploadId;
	public long competitionScheduledId;
	public long competitionInitiationId;
	public boolean reachedGround;
	public String winnerName;
	public String firstRunnerUpName;
	public String secondRunnerUpName;
	public String thirdRunnerUpName;
	public long firstScoreSheetFileEntryId;
	public long secondAndThirdScoreSheetFileEntryId;
	public boolean selfDeclaration;
	public long userId;
	public Date createDate;
	public Date modifiedDate;

	
	public String competitionName;
	private String firstFileEntryName;
	private String firstFileURL;
	private String secondFileEntryName;
	private String secondFileURL;
	
	

	public String getFirstFileEntryName() {
		return firstFileEntryName;
	}

	public void setFirstFileEntryName(String firstFileEntryName) {
		this.firstFileEntryName = firstFileEntryName;
	}

	public String getFirstFileURL() {
		return firstFileURL;
	}

	public void setFirstFileURL(String firstFileURL) {
		this.firstFileURL = firstFileURL;
	}

	public String getSecondFileEntryName() {
		return secondFileEntryName;
	}

	public void setSecondFileEntryName(String secondFileEntryName) {
		this.secondFileEntryName = secondFileEntryName;
	}

	public String getSecondFileURL() {
		return secondFileURL;
	}

	public void setSecondFileURL(String secondFileURL) {
		this.secondFileURL = secondFileURL;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public ResultUploadCommonDTO() {
		// Default constructor
	}

	public long getCompetitionResultUploadId() {
		return competitionResultUploadId;
	}

	public void setCompetitionResultUploadId(long competitionResultUploadId) {
		this.competitionResultUploadId = competitionResultUploadId;
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

	public void setCompetitionInitiationId(long competitionInitiationId) {
		this.competitionInitiationId = competitionInitiationId;
	}

	public boolean isReachedGround() {
		return reachedGround;
	}

	public void setReachedGround(boolean reachedGround) {
		this.reachedGround = reachedGround;
	}

	public String getWinnerName() {
		return winnerName;
	}

	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	public String getFirstRunnerUpName() {
		return firstRunnerUpName;
	}

	public void setFirstRunnerUpName(String firstRunnerUpName) {
		this.firstRunnerUpName = firstRunnerUpName;
	}

	public String getSecondRunnerUpName() {
		return secondRunnerUpName;
	}

	public void setSecondRunnerUpName(String secondRunnerUpName) {
		this.secondRunnerUpName = secondRunnerUpName;
	}

	public String getThirdRunnerUpName() {
		return thirdRunnerUpName;
	}

	public void setThirdRunnerUpName(String thirdRunnerUpName) {
		this.thirdRunnerUpName = thirdRunnerUpName;
	}

	public long getFirstScoreSheetFileEntryId() {
		return firstScoreSheetFileEntryId;
	}

	public void setFirstScoreSheetFileEntryId(long firstScoreSheetFileEntryId) {
		this.firstScoreSheetFileEntryId = firstScoreSheetFileEntryId;
	}

	public long getSecondAndThirdScoreSheetFileEntryId() {
		return secondAndThirdScoreSheetFileEntryId;
	}

	public void setSecondAndThirdScoreSheetFileEntryId(long secondAndThirdScoreSheetFileEntryId) {
		this.secondAndThirdScoreSheetFileEntryId = secondAndThirdScoreSheetFileEntryId;
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

	@Override
	public String toString() {
		return "CompetitionResultUpload [competitionResultUploadId=" + competitionResultUploadId
				+ ", competitionScheduledId=" + competitionScheduledId + ", competitionInitiationId="
				+ competitionInitiationId + ", reachedGround=" + reachedGround + ", winnerName=" + winnerName
				+ ", firstRunnerUpName=" + firstRunnerUpName + ", secondRunnerUpName=" + secondRunnerUpName
				+ ", thirdRunnerUpName=" + thirdRunnerUpName + ", firstScoreSheetFileEntryId="
				+ firstScoreSheetFileEntryId + ", secondAndThirdScoreSheetFileEntryId="
				+ secondAndThirdScoreSheetFileEntryId + ", selfDeclaration=" + selfDeclaration + ", userId=" + userId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
}
