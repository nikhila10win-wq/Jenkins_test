package com.mhdsys.common.pojo;

import java.util.Date;

public class UnitRegistrationCommonDTO {
		private long unitRegistrationId;
		private String unitType;
		private String unitName;
		private int unitCharterNumber;
		private String year;
		private String udis;
		private String schoolName;
		private String address;
		private String pincode;
		private String schoolEmail;
		private String schoolContact;
		private String principalMobile;
		private boolean selfDeclaration;
		// Audit Fields
		 private long userId;
		private Date createDate;
		private Date modifiedDate;
		
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

		public long getUnitRegistrationId() {
			return unitRegistrationId;
		}

		public void setUnitRegistrationId(long unitRegistrationId) {
			this.unitRegistrationId = unitRegistrationId;
		}

		public String getUnitType() {
			return unitType;
		}

		public void setUnitType(String unitType) {
			this.unitType = unitType;
		}

		public String getUnitName() {
			return unitName;
		}

		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}

		public int getUnitCharterNumber() {
			return unitCharterNumber;
		}

		public void setUnitCharterNumber(int unitCharterNumber) {
			this.unitCharterNumber = unitCharterNumber;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getUdis() {
			return udis;
		}

		public void setUdis(String udis) {
			this.udis = udis;
		}

		public String getSchoolName() {
			return schoolName;
		}

		public void setSchoolName(String schoolName) {
			this.schoolName = schoolName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getSchoolEmail() {
			return schoolEmail;
		}

		public void setSchoolEmail(String schoolEmail) {
			this.schoolEmail = schoolEmail;
		}

		public String getSchoolContact() {
			return schoolContact;
		}

		public void setSchoolContact(String schoolContact) {
			this.schoolContact = schoolContact;
		}

		public String getPrincipalMobile() {
			return principalMobile;
		}

		public void setPrincipalMobile(String principalMobile) {
			this.principalMobile = principalMobile;
		}

		public boolean isSelfDeclaration() {
			return selfDeclaration;
		}

		public void setSelfDeclaration(boolean selfDeclaration) {
			this.selfDeclaration = selfDeclaration;
		}


}
