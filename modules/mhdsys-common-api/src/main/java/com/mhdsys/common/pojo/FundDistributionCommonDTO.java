package com.mhdsys.common.pojo;

import java.util.Date;

public class FundDistributionCommonDTO {

	private long fundDistributionId;
	private long division;
	private long district;
	private long category;
	private long subCategory;

	private String budget;
	private String remarks;

	private long userId;
	private Date createDate;
	private Date modifiedDate;

	private String createDateStr;
	private String modifiedDateStr;

	private String divisionStr;
	private String districtStr;
	private String categoryStr;
	private String subCategoryStr;

	public long getFundDistributionId() {
		return fundDistributionId;
	}

	public void setFundDistributionId(long fundDistributionId) {
		this.fundDistributionId = fundDistributionId;
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

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public long getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(long subCategory) {
		this.subCategory = subCategory;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getDivisionStr() {
		return divisionStr;
	}

	public void setDivisionStr(String divisionStr) {
		this.divisionStr = divisionStr;
	}

	public String getDistrictStr() {
		return districtStr;
	}

	public void setDistrictStr(String districtStr) {
		this.districtStr = districtStr;
	}

	public String getCategoryStr() {
		return categoryStr;
	}

	public void setCategoryStr(String categoryStr) {
		this.categoryStr = categoryStr;
	}

	public String getSubCategoryStr() {
		return subCategoryStr;
	}

	public void setSubCategoryStr(String subCategoryStr) {
		this.subCategoryStr = subCategoryStr;
	}

	@Override
	public String toString() {
		return "FundDistributionCommonDTO [fundDistributionId=" + fundDistributionId + ", division=" + division
				+ ", district=" + district + ", category=" + category + ", subCategory=" + subCategory + ", budget="
				+ budget + ", remarks=" + remarks + ", userId=" + userId + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + ", createDateStr=" + createDateStr + ", modifiedDateStr="
				+ modifiedDateStr + ", divisionStr=" + divisionStr + ", districtStr=" + districtStr + ", categoryStr="
				+ categoryStr + ", subCategoryStr=" + subCategoryStr + "]";
	}

}
