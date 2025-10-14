package mhdsys.sports.coaching.wing.dto;

import java.util.Date;

public class CoachingWingMonthlyReportDTO {
	
	private long reportId;
	private long coachId;
	private String coachName;
	private String districtId;
	private long divisionId;
	private String division;
	private String divisionName;
	private String month;
	private String reportsStatus;
	//private String educationQualification;
	private  long userId;
	private String district;
	private String educationalQualification;
	private String otherCourse;
	private String sportsName;
	private String trainingCenterPlace;
	private String noOfPlayers;
	private String performanceOfThePlayers;
	private String trainingProgram;
	private String playerAnalysisMethod;
	private String planningForBestPlayers;
	private String idea;
	private String assistanceAndEffort;
	private Date createDate;
	private Date modifiedDate;
	private String reportStatus;
	private String status;
	private String currentstatus;
	private long dsoDirUserid;
	boolean dsoDecision;
	private String dsoRemarks;
	private Date timestamp;
	private String timestampStr;
	
	
	
	
	
	public long getReportId() {
		return reportId;
	}
	public void setReportId(long reportId) {
		this.reportId = reportId;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getReportsStatus() {
		return reportsStatus;
	}
	public void setReportsStatus(String reportsStatus) {
		this.reportsStatus = reportsStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSportsName() {
		return sportsName;
	}
	public void setSportsName(String sportsName) {
		this.sportsName = sportsName;
	}
	
	public String getCoachName() {
		return coachName;
	}
	
	public long getUserId() {
		return userId;
	}
	public String getEducationalQualification() {
		return educationalQualification;
	}
	public String getOtherCourse() {
		return otherCourse;
	}
	public String getTrainingCenterPlace() {
		return trainingCenterPlace;
	}
	public String getNoOfPlayers() {
		return noOfPlayers;
	}
	public String getPerformanceOfThePlayers() {
		return performanceOfThePlayers;
	}
	public String getTrainingProgram() {
		return trainingProgram;
	}
	public String getPlayerAnalysisMethod() {
		return playerAnalysisMethod;
	}
	public String getPlanningForBestPlayers() {
		return planningForBestPlayers;
	}
	public String getIdea() {
		return idea;
	}
	public String getAssistanceAndEffort() {
		return assistanceAndEffort;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public long getDsoDirUserid() {
		return dsoDirUserid;
	}
	public boolean isDsoDecision() {
		return dsoDecision;
	}
	public String getDsoRemarks() {
		return dsoRemarks;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getTimestampStr() {
		return timestampStr;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}
	public void setOtherCourse(String otherCourse) {
		this.otherCourse = otherCourse;
	}
	public void setTrainingCenterPlace(String trainingCenterPlace) {
		this.trainingCenterPlace = trainingCenterPlace;
	}
	public void setNoOfPlayers(String noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
	public void setPerformanceOfThePlayers(String performanceOfThePlayers) {
		this.performanceOfThePlayers = performanceOfThePlayers;
	}
	public void setTrainingProgram(String trainingProgram) {
		this.trainingProgram = trainingProgram;
	}
	public void setPlayerAnalysisMethod(String playerAnalysisMethod) {
		this.playerAnalysisMethod = playerAnalysisMethod;
	}
	public void setPlanningForBestPlayers(String planningForBestPlayers) {
		this.planningForBestPlayers = planningForBestPlayers;
	}
	public void setIdea(String idea) {
		this.idea = idea;
	}
	public void setAssistanceAndEffort(String assistanceAndEffort) {
		this.assistanceAndEffort = assistanceAndEffort;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public void setDsoDirUserid(long dsoDirUserid) {
		this.dsoDirUserid = dsoDirUserid;
	}
	public void setDsoDecision(boolean dsoDecision) {
		this.dsoDecision = dsoDecision;
	}
	public void setDsoRemarks(String dsoRemarks) {
		this.dsoRemarks = dsoRemarks;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public void setTimestampStr(String timestampStr) {
		this.timestampStr = timestampStr;
	}
	public String getCurrentstatus() {
		return currentstatus;
	}
	public void setCurrentstatus(String currentstatus) {
		this.currentstatus = currentstatus;
	}
	@Override
	public String toString() {
		return "CoachingWingMonthlyReportDTO [reportId=" + reportId + ", coachId=" + coachId + ", coachName="
				+ coachName + ", districtId=" + districtId + ", divisionId=" + divisionId + ", division=" + division
				+ ", divisionName=" + divisionName + ", month=" + month + ", reportsStatus=" + reportsStatus
				+ ", userId=" + userId + ", district=" + district
				+ ", educationalQualification=" + educationalQualification + ", otherCourse=" + otherCourse
				+ ", sportsName=" + sportsName + ", trainingCenterPlace=" + trainingCenterPlace + ", noOfPlayers="
				+ noOfPlayers + ", performanceOfThePlayers=" + performanceOfThePlayers + ", trainingProgram="
				+ trainingProgram + ", playerAnalysisMethod=" + playerAnalysisMethod + ", planningForBestPlayers="
				+ planningForBestPlayers + ", idea=" + idea + ", assistanceAndEffort=" + assistanceAndEffort
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", reportStatus=" + reportStatus
				+ ", status=" + status + ", dsoDirUserid=" + dsoDirUserid + ", dsoDecision=" + dsoDecision
				+ ", dsoRemarks=" + dsoRemarks + ", timestamp=" + timestamp + ", timestampStr=" + timestampStr + "]";
	}
	
}
