package com.mhdsys.common.pojo;

import java.util.Date;

public class AwardPointsCommonDTO {

	private long awardPointsId;
	private long competitionLevelId;
	private long sportsCompetitionLevelId;
	private String awardYear;
	private long awardNameId;
	private long sportsNameId;
	private long SportsTypeId;
	private long category;
	private String winner;
	private String firstRunnerUp;
	private String secondRunnerUp;
	private String participant;

	private String competitionLevel;
	private String sportsCompetitionLevel;
	private String awardName;
	private String sportsName;
	private String sportsType;

	private Date createdDate;
	private Date modifiedDate;
	private String categoryStr;



	public long getAwardPointsId() {
		return awardPointsId;
	}

	public void setAwardPointsId(long awardPointsId) {
		this.awardPointsId = awardPointsId;
	}

	public long getCompetitionLevelId() {
		return competitionLevelId;
	}

	public void setCompetitionLevelId(long competitionLevelId) {
		this.competitionLevelId = competitionLevelId;
	}

	public long getSportsCompetitionLevelId() {
		return sportsCompetitionLevelId;
	}

	public void setSportsCompetitionLevelId(long sportsCompetitionLevelId) {
		this.sportsCompetitionLevelId = sportsCompetitionLevelId;
	}

	public String getAwardYear() {
		return awardYear;
	}

	public void setAwardYear(String awardYear) {
		this.awardYear = awardYear;
	}

	public long getAwardNameId() {
		return awardNameId;
	}

	public void setAwardNameId(long awardNameId) {
		this.awardNameId = awardNameId;
	}

	public long getSportsNameId() {
		return sportsNameId;
	}

	public void setSportsNameId(long sportsNameId) {
		this.sportsNameId = sportsNameId;
	}

	public long getSportsTypeId() {
		return SportsTypeId;
	}

	public void setSportsTypeId(long sportsTypeId) {
		SportsTypeId = sportsTypeId;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getFirstRunnerUp() {
		return firstRunnerUp;
	}

	public void setFirstRunnerUp(String firstRunnerUp) {
		this.firstRunnerUp = firstRunnerUp;
	}

	public String getSecondRunnerUp() {
		return secondRunnerUp;
	}

	public void setSecondRunnerUp(String secondRunnerUp) {
		this.secondRunnerUp = secondRunnerUp;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getCompetitionLevel() {
		return competitionLevel;
	}

	public void setCompetitionLevel(String competitionLevel) {
		this.competitionLevel = competitionLevel;
	}

	public String getSportsCompetitionLevel() {
		return sportsCompetitionLevel;
	}

	public void setSportsCompetitionLevel(String sportsCompetitionLevel) {
		this.sportsCompetitionLevel = sportsCompetitionLevel;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getSportsName() {
		return sportsName;
	}

	public void setSportsName(String sportsName) {
		this.sportsName = sportsName;
	}

	public String getSportsType() {
		return sportsType;
	}

	public void setSportsType(String sportsType) {
		this.sportsType = sportsType;
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

	public String getCategoryStr() {
		return categoryStr;
	}

	public void setCategoryStr(String categoryStr) {
		this.categoryStr = categoryStr;
	}

	@Override
	public String toString() {
		return "AwardPointsCommonDTO [awardPointsId=" + awardPointsId + ", competitionLevelId=" + competitionLevelId
				+ ", sportsCompetitionLevelId=" + sportsCompetitionLevelId + ", awardYear=" + awardYear
				+ ", awardNameId=" + awardNameId + ", sportsNameId=" + sportsNameId + ", SportsTypeId=" + SportsTypeId
				+ ", category=" + category + ", winner=" + winner + ", firstRunnerUp=" + firstRunnerUp
				+ ", secondRunnerUp=" + secondRunnerUp + ", participant=" + participant + ", competitionLevel="
				+ competitionLevel + ", sportsCompetitionLevel=" + sportsCompetitionLevel + ", awardName=" + awardName
				+ ", sportsName=" + sportsName + ", sportsType=" + sportsType + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", categoryStr=" + categoryStr + "]";
	}

}
