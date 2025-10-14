package com.mhdsys.common.pojo;

public class GovtGrantDTO {
	private String grantType;
	private String receivedAmount;
	private String date;
	private String facilityAmount;
	private String expenditureAmount;
	private String totalAmount;
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFacilityAmount() {
		return facilityAmount;
	}
	public void setFacilityAmount(String facilityAmount) {
		this.facilityAmount = facilityAmount;
	}
	public String getExpenditureAmount() {
		return expenditureAmount;
	}
	public void setExpenditureAmount(String expenditureAmount) {
		this.expenditureAmount = expenditureAmount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "GovtGrantDTO [grantType=" + grantType + ", receivedAmount=" + receivedAmount + ", date=" + date
				+ ", facilityAmount=" + facilityAmount + ", expenditureAmount=" + expenditureAmount + ", totalAmount="
				+ totalAmount + "]";
	}
   
	
	
}
