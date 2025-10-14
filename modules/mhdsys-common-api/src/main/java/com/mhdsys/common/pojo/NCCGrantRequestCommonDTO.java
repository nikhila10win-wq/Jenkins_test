package com.mhdsys.common.pojo;

import java.util.Date;

public class NCCGrantRequestCommonDTO {
	private long nccGrantRequestId;
	private String headquarterName;
    private String unitType;

    // Salary & Allowance I
    private String caretakerAllowance;
    private String refreshmentAllowance;
    private String salaries;
    private String overTimeAllowance;
    private String telephone;
    private String electricity;
    private String waterCharges;
    private String contractualServices;
    private String domesticTravellingExpenses;
    private String officeExpenses;

    // Salary & Allowance II
    private String rent;
    private String rates;
    private String taxes;
    private String trainingActivities;
    private String atg;
    private String washingAndPolishingAllowance;
    private String petrolOilLubricants;
    private String minorWork;
    private String honorariumToAno;
    private String outfitMaintenanceAllowance;
    private String othersIfAny;
    private String grantsInAid;
    private String scholarshipOrStipend;
    private String total;
    private String status;
    private String approvalStatus;
    private String remarks;
    private String actualTotal;
    private long userId;
    private Date createDate;
    private Date modifiedDate;
    
    
	public String getActualTotal() {
		return actualTotal;
	}
	public void setActualTotal(String actualTotal) {
		this.actualTotal = actualTotal;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getNccGrantRequestId() {
		return nccGrantRequestId;
	}
	public void setNccGrantRequestId(long nccGrantRequestId) {
		this.nccGrantRequestId = nccGrantRequestId;
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
	public String getHeadquarterName() {
		return headquarterName;
	}
	public void setHeadquarterName(String headquarterName) {
		this.headquarterName = headquarterName;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getCaretakerAllowance() {
		return caretakerAllowance;
	}
	public void setCaretakerAllowance(String caretakerAllowance) {
		this.caretakerAllowance = caretakerAllowance;
	}
	public String getRefreshmentAllowance() {
		return refreshmentAllowance;
	}
	public void setRefreshmentAllowance(String refreshmentAllowance) {
		this.refreshmentAllowance = refreshmentAllowance;
	}
	public String getSalaries() {
		return salaries;
	}
	public void setSalaries(String salaries) {
		this.salaries = salaries;
	}
	public String getOverTimeAllowance() {
		return overTimeAllowance;
	}
	public void setOverTimeAllowance(String overTimeAllowance) {
		this.overTimeAllowance = overTimeAllowance;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getElectricity() {
		return electricity;
	}
	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}
	public String getWaterCharges() {
		return waterCharges;
	}
	public void setWaterCharges(String waterCharges) {
		this.waterCharges = waterCharges;
	}
	public String getContractualServices() {
		return contractualServices;
	}
	public void setContractualServices(String contractualServices) {
		this.contractualServices = contractualServices;
	}
	public String getDomesticTravellingExpenses() {
		return domesticTravellingExpenses;
	}
	public void setDomesticTravellingExpenses(String domesticTravellingExpenses) {
		this.domesticTravellingExpenses = domesticTravellingExpenses;
	}
	public String getOfficeExpenses() {
		return officeExpenses;
	}
	public void setOfficeExpenses(String officeExpenses) {
		this.officeExpenses = officeExpenses;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getRates() {
		return rates;
	}
	public void setRates(String rates) {
		this.rates = rates;
	}
	public String getTaxes() {
		return taxes;
	}
	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}
	public String getTrainingActivities() {
		return trainingActivities;
	}
	public void setTrainingActivities(String trainingActivities) {
		this.trainingActivities = trainingActivities;
	}
	public String getAtg() {
		return atg;
	}
	public void setAtg(String atg) {
		this.atg = atg;
	}
	public String getWashingAndPolishingAllowance() {
		return washingAndPolishingAllowance;
	}
	public void setWashingAndPolishingAllowance(String washingAndPolishingAllowance) {
		this.washingAndPolishingAllowance = washingAndPolishingAllowance;
	}
	public String getPetrolOilLubricants() {
		return petrolOilLubricants;
	}
	public void setPetrolOilLubricants(String petrolOilLubricants) {
		this.petrolOilLubricants = petrolOilLubricants;
	}
	public String getMinorWork() {
		return minorWork;
	}
	public void setMinorWork(String minorWork) {
		this.minorWork = minorWork;
	}
	public String getHonorariumToAno() {
		return honorariumToAno;
	}
	public void setHonorariumToAno(String honorariumToAno) {
		this.honorariumToAno = honorariumToAno;
	}
	public String getOutfitMaintenanceAllowance() {
		return outfitMaintenanceAllowance;
	}
	public void setOutfitMaintenanceAllowance(String outfitMaintenanceAllowance) {
		this.outfitMaintenanceAllowance = outfitMaintenanceAllowance;
	}
	public String getOthersIfAny() {
		return othersIfAny;
	}
	public void setOthersIfAny(String othersIfAny) {
		this.othersIfAny = othersIfAny;
	}
	public String getGrantsInAid() {
		return grantsInAid;
	}
	public void setGrantsInAid(String grantsInAid) {
		this.grantsInAid = grantsInAid;
	}
	public String getScholarshipOrStipend() {
		return scholarshipOrStipend;
	}
	public void setScholarshipOrStipend(String scholarshipOrStipend) {
		this.scholarshipOrStipend = scholarshipOrStipend;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}

}
