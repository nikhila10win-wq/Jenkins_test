package com.mhdsys.common.pojo.profile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhdsys.common.api.profile.UserIdentifiable;

import java.util.Objects;

public class SchoolCollageDTO implements UserIdentifiable{
	
	private String postalAddress;
	private String pinCode;
	private long photoEntryId;
	private long totalStudentCountBoys;
	private long totalStudentCountGirls;
	private String registrationWith;
	private long schoolCollegeRegNumberEntryId;
	private long userId;
	
	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public long getPhotoEntryId() {
		return photoEntryId;
	}

	public void setPhotoEntryId(long photoEntryId) {
		this.photoEntryId = photoEntryId;
	}

	public long getTotalStudentCountBoys() {
		return totalStudentCountBoys;
	}

	public void setTotalStudentCountBoys(long totalStudentCountBoys) {
		this.totalStudentCountBoys = totalStudentCountBoys;
	}

	public long getTotalStudentCountGirls() {
		return totalStudentCountGirls;
	}

	public void setTotalStudentCountGirls(long totalStudentCountGirls) {
		this.totalStudentCountGirls = totalStudentCountGirls;
	}

	public String getRegistrationWith() {
		return registrationWith;
	}

	public void setRegistrationWith(String registrationWith) {
		this.registrationWith = registrationWith;
	}

	public long getSchoolCollegeRegNumberEntryId() {
		return schoolCollegeRegNumberEntryId;
	}

	public void setSchoolCollegeRegNumberEntryId(long schoolCollegeRegNumberEntryId) {
		this.schoolCollegeRegNumberEntryId = schoolCollegeRegNumberEntryId;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(photoEntryId, pinCode, postalAddress, registrationWith, schoolCollegeRegNumberEntryId,
				totalStudentCountBoys, totalStudentCountGirls, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolCollageDTO other = (SchoolCollageDTO) obj;
		return photoEntryId == other.photoEntryId && Objects.equals(pinCode, other.pinCode)
				&& Objects.equals(postalAddress, other.postalAddress)
				&& Objects.equals(registrationWith, other.registrationWith)
				&& schoolCollegeRegNumberEntryId == other.schoolCollegeRegNumberEntryId
				&& totalStudentCountBoys == other.totalStudentCountBoys
				&& totalStudentCountGirls == other.totalStudentCountGirls && userId == other.userId;
	}

	@Override
	public String toString() {
		return convertToJson();
	}
	
    private String convertToJson() { 
        try { 
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.configure(
    				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
 
            // Convert the Java object to a JSON string 
            String jsonString = objectMapper.writeValueAsString(this); 
 
            return jsonString; 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return null; 
        } 
    } 

	
}
