package com.mhdsys.common.pojo;

public class OtherSourceDTO {

    private String otherSources;
    private String date;
    private String facilityAmount;
    private String totalAmount;
    private String receivedAmount;
    private String expenditureAmount;

    // Getters and Setters

    public String getOtherSources() {
        return otherSources;
    }

    public void setOtherSources(String otherSources) {
        this.otherSources = otherSources;
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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(String receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getExpenditureAmount() {
        return expenditureAmount;
    }

    public void setExpenditureAmount(String expenditureAmount) {
        this.expenditureAmount = expenditureAmount;
    }

	@Override
	public String toString() {
		return "OtherSourceDTO [otherSources=" + otherSources + ", date=" + date + ", facilityAmount=" + facilityAmount
				+ ", totalAmount=" + totalAmount + ", receivedAmount=" + receivedAmount + ", expenditureAmount="
				+ expenditureAmount + "]";
	}
    
}