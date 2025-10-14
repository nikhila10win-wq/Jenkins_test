package com.mhdsys.common.pojo;

import java.util.Date;

public class RegistrationDTO {

	private long registrationId;
	private long state;
	private long division;
	private long district;
	private long taluka;

	private String stateName;
	private String divisionName;
	private String districtName;
	private String talukaName;
	private String udiseCode;

	private String firstName;
	private String PrincipalName;
	private String sportsTeacher;
	private String schoolRegNo;
	private String aadharNo;

	private String mobileNo;
	private String landlineNo;
	private String email;
	private String website;
	private long userId;

	private Date createdDate;
	private Date modifiedDate;

	private String associationName;
	private String associationRegNo;
	private String secretaryName;
	private String departmentName;
	private String subDepartment;
	private String officialName;
	private String designation;
	private String secretaryOrgName;
	private String organizationName;
	private Date foundingDate;
	private long employeeId;

	public String getUdiseCode() {
		return udiseCode;
	}

	public void setUdiseCode(String udiseCode) {
		this.udiseCode = udiseCode;
	}

	public long getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(long registrationId) {
		this.registrationId = registrationId;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public long getDivision() {
		return division;
	}

	public void setDivision(long division) {
		this.division = division;
	}

	public long getDistrict() {
		return district;
	}

	public void setDistrict(long district) {
		this.district = district;
	}

	public long getTaluka() {
		return taluka;
	}

	public void setTaluka(long taluka) {
		this.taluka = taluka;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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

	public String getTalukaName() {
		return talukaName;
	}

	public void setTalukaName(String talukaName) {
		this.talukaName = talukaName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPrincipalName() {
		return PrincipalName;
	}

	public void setPrincipalName(String principalName) {
		PrincipalName = principalName;
	}

	public String getSportsTeacher() {
		return sportsTeacher;
	}

	public void setSportsTeacher(String sportsTeacher) {
		this.sportsTeacher = sportsTeacher;
	}

	public String getSchoolRegNo() {
		return schoolRegNo;
	}

	public void setSchoolRegNo(String schoolRegNo) {
		this.schoolRegNo = schoolRegNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getAssociationName() {
		return associationName;
	}

	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}

	public String getAssociationRegNo() {
		return associationRegNo;
	}

	public void setAssociationRegNo(String associationRegNo) {
		this.associationRegNo = associationRegNo;
	}

	public String getSecretaryName() {
		return secretaryName;
	}

	public void setSecretaryName(String secretaryName) {
		this.secretaryName = secretaryName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(String subDepartment) {
		this.subDepartment = subDepartment;
	}

	public String getOfficialName() {
		return officialName;
	}

	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSecretaryOrgName() {
		return secretaryOrgName;
	}

	public void setSecretaryOrgName(String secretaryOrgName) {
		this.secretaryOrgName = secretaryOrgName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Date getFoundingDate() {
		return foundingDate;
	}

	public void setFoundingDate(Date foundingDate) {
		this.foundingDate = foundingDate;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "RegistrationDTO [registrationId=" + registrationId + ", state=" + state + ", division=" + division
				+ ", district=" + district + ", taluka=" + taluka + ", stateName=" + stateName + ", divisionName="
				+ divisionName + ", districtName=" + districtName + ", talukaName=" + talukaName + ", firstName="
				+ firstName + ", PrincipalName=" + PrincipalName + ", sportsTeacher=" + sportsTeacher + ", schoolRegNo="
				+ schoolRegNo + ", aadharNo=" + aadharNo + ", mobileNo=" + mobileNo + ", landlineNo=" + landlineNo
				+ ", email=" + email + ", website=" + website + ", userId=" + userId + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", associationName=" + associationName + ", associationRegNo="
				+ associationRegNo + ", secretaryName=" + secretaryName + ", departmentName=" + departmentName
				+ ", subDepartment=" + subDepartment + ", officialName=" + officialName + ", designation=" + designation
				+ ", secretaryOrgName=" + secretaryOrgName + ", organizationName=" + organizationName
				+ ", foundingDate=" + foundingDate + ", employeeId=" + employeeId + "]";
	}

}
