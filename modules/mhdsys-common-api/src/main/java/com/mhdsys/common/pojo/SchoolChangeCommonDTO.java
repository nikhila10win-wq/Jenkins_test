package com.mhdsys.common.pojo;

public class SchoolChangeCommonDTO {

	private long schoolChangeId;
	private String participantName;
	private String standard;
	private String fromSChool;
	private String toSchool;
	private String year;
	private String fileName;
	private long fileEntryId;
	private String createdDate;
	private String modifiedDate;

	public long getSchoolChangeId() {
		return schoolChangeId;
	}

	public void setSchoolChangeId(long schoolChangeId) {
		this.schoolChangeId = schoolChangeId;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getFromSChool() {
		return fromSChool;
	}

	public void setFromSChool(String fromSChool) {
		this.fromSChool = fromSChool;
	}

	public String getToSchool() {
		return toSchool;
	}

	public void setToSchool(String toSchool) {
		this.toSchool = toSchool;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
