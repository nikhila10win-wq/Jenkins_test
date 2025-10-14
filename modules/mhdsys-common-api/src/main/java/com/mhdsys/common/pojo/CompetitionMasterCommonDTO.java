package com.mhdsys.common.pojo;

import java.util.Date;

public class CompetitionMasterCommonDTO {
	private long competitionMasterId;
	private int sportTypeId;
	private long sportId;
	private long categoryId;
	private boolean underForteen;
	private boolean underSeventeen;
	private boolean underNineteen;
	private Date underForteenCutOffDate;
	private Date underSeventeenCutOffDate;
	private Date underNineteenCutOffDate;
	private double fees;
	private boolean currentYear;
	private Date createDate;
	private Date modifiedDate;
	private long userId;
	
//	Manupulation
	
	private String sportType;
	private String sportName;
	private String categoryName;
	private String createDateStr;
	private String modifiedDateStr;
	private String underForteenCutOffDateStr;
	private String underSeventeenCutOffDateStr;
	private String underNineteenCutOffDateStr;
	
	
	
	
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
	public String getUnderForteenCutOffDateStr() {
		return underForteenCutOffDateStr;
	}
	public void setUnderForteenCutOffDateStr(String underForteenCutOffDateStr) {
		this.underForteenCutOffDateStr = underForteenCutOffDateStr;
	}
	public String getUnderSeventeenCutOffDateStr() {
		return underSeventeenCutOffDateStr;
	}
	public void setUnderSeventeenCutOffDateStr(String underSeventeenCutOffDateStr) {
		this.underSeventeenCutOffDateStr = underSeventeenCutOffDateStr;
	}
	public String getUnderNineteenCutOffDateStr() {
		return underNineteenCutOffDateStr;
	}
	public void setUnderNineteenCutOffDateStr(String underNineteenCutOffDateStr) {
		this.underNineteenCutOffDateStr = underNineteenCutOffDateStr;
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
	
	public long getCompetitionMasterId() {
		return competitionMasterId;
	}
	public void setCompetitionMasterId(long competitionMasterId) {
		this.competitionMasterId = competitionMasterId;
	}
	public int getSportTypeId() {
		return sportTypeId;
	}
	public void setSportTypeId(int sportTypeId) {
		this.sportTypeId = sportTypeId;
	}
	public long getSportId() {
		return sportId;
	}
	public void setSportId(long sportId) {
		this.sportId = sportId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public boolean isUnderForteen() {
		return underForteen;
	}
	public void setUnderForteen(boolean underForteen) {
		this.underForteen = underForteen;
	}
	public boolean isUnderSeventeen() {
		return underSeventeen;
	}
	public void setUnderSeventeen(boolean underSeventeen) {
		this.underSeventeen = underSeventeen;
	}
	public boolean isUnderNineteen() {
		return underNineteen;
	}
	public void setUnderNineteen(boolean underNineteen) {
		this.underNineteen = underNineteen;
	}
	public Date getUnderForteenCutOffDate() {
		return underForteenCutOffDate;
	}
	public void setUnderForteenCutOffDate(Date underForteenCutOffDate) {
		this.underForteenCutOffDate = underForteenCutOffDate;
	}
	public Date getUnderSeventeenCutOffDate() {
		return underSeventeenCutOffDate;
	}
	public void setUnderSeventeenCutOffDate(Date underSeventeenCutOffDate) {
		this.underSeventeenCutOffDate = underSeventeenCutOffDate;
	}
	public Date getUnderNineteenCutOffDate() {
		return underNineteenCutOffDate;
	}
	public void setUnderNineteenCutOffDate(Date underNineteenCutOffDate) {
		this.underNineteenCutOffDate = underNineteenCutOffDate;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public boolean isCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(boolean currentYear) {
		this.currentYear = currentYear;
	}
	public String getSportType() {
		return sportType;
	}
	public void setSportType(String sportType) {
		this.sportType = sportType;
	}
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "CompetitionMasterDTO [competitionMasterId=" + competitionMasterId + ", sportTypeId=" + sportTypeId
				+ ", sportId=" + sportId + ", categoryId=" + categoryId + ", underForteen=" + underForteen
				+ ", underSeventeen=" + underSeventeen + ", underNineteen=" + underNineteen
				+ ", underForteenCutOffDate=" + underForteenCutOffDate + ", underSeventeenCutOffDate="
				+ underSeventeenCutOffDate + ", underNineteenCutOffDate=" + underNineteenCutOffDate + ", fees=" + fees
				+ ", currentYear=" + currentYear + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate
				+ ", userId=" + userId + ", sportType=" + sportType + ", sportName=" + sportName + ", categoryName="
				+ categoryName + ", createDateStr=" + createDateStr + ", modifiedDateStr=" + modifiedDateStr
				+ ", underForteenCutOffDateStr=" + underForteenCutOffDateStr + ", underSeventeenCutOffDateStr="
				+ underSeventeenCutOffDateStr + ", underNineteenCutOffDateStr=" + underNineteenCutOffDateStr + "]";
	}
	
	
	
	
}
