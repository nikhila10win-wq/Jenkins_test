package mhdsys.sports.coaching.wing.dto;

import java.util.Date;

public class ReportsAttachmentDTO {
	
	//private long tr
	private long trainingCentreId;
	private long reportId;
	private String description;
	private String remarks;
	private String attachmentPath;
	private String attachmenturl;
	private String attachmentName;
	private Date createDate;
	private Date modifiedDate;
	private String formStatus;
	
	public ReportsAttachmentDTO() {
	}
	public ReportsAttachmentDTO(long trainingCentreId, long reportId, String description, String remarks,
			String attachmentPath, Date createDate, Date modifiedDate, String formStatus) {
		super();
		this.trainingCentreId = trainingCentreId;
		this.reportId = reportId;
		this.description = description;
		this.remarks = remarks;
		this.attachmentPath = attachmentPath;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.formStatus = formStatus;
	}
	public long getReportId() {
		return reportId;
	}
	public void setReportId(long reportId) {
		this.reportId = reportId;
	}
	public long getTrainingCentreId() {
		return trainingCentreId;
	}
	public void setTrainingCentreId(long trainingCentreId) {
		this.trainingCentreId = trainingCentreId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
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
	
	public String getAttachmenturl() {
		return attachmenturl;
	}
	public void setAttachmenturl(String attachmenturl) {
		this.attachmenturl = attachmenturl;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	@Override
	public String toString() {
		return "ReportsAttachmentDTO [trainingCentreId=" + trainingCentreId + ", reportId=" + reportId
				+ ", description=" + description + ", remarks=" + remarks + ", attachmentPath=" + attachmentPath
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", formStatus=" + formStatus + "]";
	}
	
	
	
}	
