package mhdsys.sports.coaching.wing.dto;

import java.util.Date;
import java.util.List;

public class TrainingCenterDto {
	
	private long trainingCentreId;
	private String division;
	private String divisionName;
	private String district;
	private String districtName;
	private String centreType;
	private String centreTypeValue;
	private String sportsType;
	private String geoTag;
	private List<String> geoTagPhotosURLs;
	private List<String> geoTagPhotosNames;
	private List<String> geoTagPhotosIds;
	private String latitude;
	private String longitude;
	private String gisMap;
	private String dprDocPath;
	private String dprDocurl;
	private String dprDocName;
	private Date createDate;
	private Date modifiedDate;
	private String formstatus;
	private long userid;
	private long divdepDirUserid;
	private boolean divdepDirDecision;
	private String divdepDirRemarks;
	private long hoDirUserid;
	private boolean hoDecision;
	private String hoRemarks;
	private String status;
	private String statusValue;
	
	public TrainingCenterDto() {
	}

	public TrainingCenterDto(long trainingCentreId, String division, String divisionName, String district,
			String districtName, String centreType, String centreTypeValue, String sportsType, String geoTag,
			List<String> geoTagPhotosURLs, List<String> geoTagPhotosNames, List<String> geoTagPhotosIds,
			String latitude, String longitude, String gisMap, String dprDocPath, String dprDocurl, String dprDocName,
			Date createDate, Date modifiedDate, String formstatus, long userid, long divdepDirUserid,
			boolean divdepDirDecision, String divdepDirRemarks, long hoDirUserid, boolean hoDecision, String hoRemarks,
			String status, String statusValue) {
		super();
		this.trainingCentreId = trainingCentreId;
		this.division = division;
		this.divisionName = divisionName;
		this.district = district;
		this.districtName = districtName;
		this.centreType = centreType;
		this.centreTypeValue = centreTypeValue;
		this.sportsType = sportsType;
		this.geoTag = geoTag;
		this.geoTagPhotosURLs = geoTagPhotosURLs;
		this.geoTagPhotosNames = geoTagPhotosNames;
		this.geoTagPhotosIds = geoTagPhotosIds;
		this.latitude = latitude;
		this.longitude = longitude;
		this.gisMap = gisMap;
		this.dprDocPath = dprDocPath;
		this.dprDocurl = dprDocurl;
		this.dprDocName = dprDocName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.formstatus = formstatus;
		this.userid = userid;
		this.divdepDirUserid = divdepDirUserid;
		this.divdepDirDecision = divdepDirDecision;
		this.divdepDirRemarks = divdepDirRemarks;
		this.hoDirUserid = hoDirUserid;
		this.hoDecision = hoDecision;
		this.hoRemarks = hoRemarks;
		this.status = status;
		this.statusValue = statusValue;
	}

	public long getTrainingCentreId() {
		return trainingCentreId;
	}
	public void setTrainingCentreId(long trainingCentreId) {
		this.trainingCentreId = trainingCentreId;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCentreType() {
		return centreType;
	}
	public void setCentreType(String centreType) {
		this.centreType = centreType;
	}
	public String getSportsType() {
		return sportsType;
	}
	public void setSportsType(String sportsType) {
		this.sportsType = sportsType;
	}
	public String getGeoTag() {
		return geoTag;
	}
	public void setGeoTag(String geoTag) {
		this.geoTag = geoTag;
	}
	public List<String> getGeoTagPhotosURLs() {
		return geoTagPhotosURLs;
	}
	public void setGeoTagPhotosURLs(List<String> geoTagPhotosURLs) {
		this.geoTagPhotosURLs = geoTagPhotosURLs;
	}
	public List<String> getGeoTagPhotosNames() {
		return geoTagPhotosNames;
	}
	public void setGeoTagPhotosNames(List<String> geoTagPhotosNames) {
		this.geoTagPhotosNames = geoTagPhotosNames;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getGisMap() {
		return gisMap;
	}
	public void setGisMap(String gisMap) {
		this.gisMap = gisMap;
	}
	public String getDprDocPath() {
		return dprDocPath;
	}
	public void setDprDocPath(String dprDocPath) {
		this.dprDocPath = dprDocPath;
	}
	public String getDprDocurl() {
		return dprDocurl;
	}
	public void setDprDocurl(String dprDocurl) {
		this.dprDocurl = dprDocurl;
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
	public String getFormstatus() {
		return formstatus;
	}
	public void setFormstatus(String formstatus) {
		this.formstatus = formstatus;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getDivdepDirUserid() {
		return divdepDirUserid;
	}
	public void setDivdepDirUserid(long divdepDirUserid) {
		this.divdepDirUserid = divdepDirUserid;
	}
	public boolean isDivdepDirDecision() {
		return divdepDirDecision;
	}
	public void setDivdepDirDecision(boolean divdepDirDecision) {
		this.divdepDirDecision = divdepDirDecision;
	}
	public String getDivdepDirRemarks() {
		return divdepDirRemarks;
	}
	public void setDivdepDirRemarks(String divdepDirRemarks) {
		this.divdepDirRemarks = divdepDirRemarks;
	}
	public long getHoDirUserid() {
		return hoDirUserid;
	}
	public void setHoDirUserid(long hoDirUserid) {
		this.hoDirUserid = hoDirUserid;
	}
	public boolean isHoDecision() {
		return hoDecision;
	}
	public void setHoDecision(boolean hoDecision) {
		this.hoDecision = hoDecision;
	}
	public String getHoRemarks() {
		return hoRemarks;
	}
	public void setHoRemarks(String hoRemarks) {
		this.hoRemarks = hoRemarks;
	}
	
	public String getDprDocName() {
		return dprDocName;
	}

	public void setDprDocName(String dprDocName) {
		this.dprDocName = dprDocName;
	}
	
	public List<String> getGeoTagPhotosIds() {
		return geoTagPhotosIds;
	}

	public void setGeoTagPhotosIds(List<String> geoTagPhotosIds) {
		this.geoTagPhotosIds = geoTagPhotosIds;
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

	public String getCentreTypeValue() {
		return centreTypeValue;
	}

	public void setCentreTypeValue(String centreTypeValue) {
		this.centreTypeValue = centreTypeValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	@Override
	public String toString() {
		return "TrainingCenterDto [trainingCentreId=" + trainingCentreId + ", division=" + division + ", divisionName="
				+ divisionName + ", district=" + district + ", districtName=" + districtName + ", centreType="
				+ centreType + ", centreTypeValue=" + centreTypeValue + ", sportsType=" + sportsType + ", geoTag="
				+ geoTag + ", geoTagPhotosURLs=" + geoTagPhotosURLs + ", geoTagPhotosNames=" + geoTagPhotosNames
				+ ", geoTagPhotosIds=" + geoTagPhotosIds + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", gisMap=" + gisMap + ", dprDocPath=" + dprDocPath + ", dprDocurl=" + dprDocurl + ", dprDocName="
				+ dprDocName + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", formstatus="
				+ formstatus + ", userid=" + userid + ", divdepDirUserid=" + divdepDirUserid + ", divdepDirDecision="
				+ divdepDirDecision + ", divdepDirRemarks=" + divdepDirRemarks + ", hoDirUserid=" + hoDirUserid
				+ ", hoDecision=" + hoDecision + ", hoRemarks=" + hoRemarks + ", status=" + status + ", statusValue="
				+ statusValue + "]";
	}

}	
