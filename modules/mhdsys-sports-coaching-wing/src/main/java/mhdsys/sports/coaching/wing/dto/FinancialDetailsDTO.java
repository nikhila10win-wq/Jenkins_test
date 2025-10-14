package mhdsys.sports.coaching.wing.dto;

import java.util.Date;

public class FinancialDetailsDTO {
	
	private long trainingCentreId;
	private long financialDetailId;
	private String description;
	private double grAmount;
	private double requestedAmount;
	private Date createDate;
	private Date modifiedDate;
	private String formStatus;
	private double total;
	
	public FinancialDetailsDTO() {
	}
	
	public FinancialDetailsDTO(long trainingCentreId, long financialDetailId, String description, double grAmount,
			double requestedAmount, Date createDate, Date modifiedDate, String formStatus, double total) {
		super();
		this.trainingCentreId = trainingCentreId;
		this.financialDetailId = financialDetailId;
		this.description = description;
		this.grAmount = grAmount;
		this.requestedAmount = requestedAmount;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.formStatus = formStatus;
		this.total = total;
	}

	public long getTrainingCentreId() {
		return trainingCentreId;
	}
	public void setTrainingCentreId(long trainingCentreId) {
		this.trainingCentreId = trainingCentreId;
	}
	public long getFinancialDetailId() {
		return financialDetailId;
	}
	public void setFinancialDetailId(long financialDetailId) {
		this.financialDetailId = financialDetailId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getGrAmount() {
		return grAmount;
	}
	public void setGrAmount(double grAmount) {
		this.grAmount = grAmount;
	}
	public double getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
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
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "FinancialDetailsDTO [trainingCentreId=" + trainingCentreId + ", financialDetailId=" + financialDetailId
				+ ", description=" + description + ", grAmount=" + grAmount + ", requestedAmount=" + requestedAmount
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", formStatus=" + formStatus
				+ ", total=" + total + "]";
	}
	
}	
