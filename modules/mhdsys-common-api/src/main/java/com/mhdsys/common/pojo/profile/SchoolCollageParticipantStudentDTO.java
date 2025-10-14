package com.mhdsys.common.pojo.profile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhdsys.common.api.profile.UserIdentifiable;

import java.util.Date;
import java.util.Objects;

public class SchoolCollageParticipantStudentDTO implements UserIdentifiable{
	
	private String postalAddress;
	private String pinCode;
	private long photoEntryId;
	private Date dateofAdmissionInSchoolCollege;
	private String nameofCoach;
	private String nameofTrainingCenter;
	private String heightInCm;
	private String weightInKg;
	private String bloodGroup;
	private String identificationMarkOnBody;
	private String castCategory;
	private long birthCertificateEntryId;
	private long formNoTwoEntryId;
	private long aadharCardEntryId;
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

	public Date getDateofAdmissionInSchoolCollege() {
		return dateofAdmissionInSchoolCollege;
	}

	public void setDateofAdmissionInSchoolCollege(Date dateofAdmissionInSchoolCollege) {
		this.dateofAdmissionInSchoolCollege = dateofAdmissionInSchoolCollege;
	}

	public String getNameofCoach() {
		return nameofCoach;
	}

	public void setNameofCoach(String nameofCoach) {
		this.nameofCoach = nameofCoach;
	}

	public String getNameofTrainingCenter() {
		return nameofTrainingCenter;
	}

	public void setNameofTrainingCenter(String nameofTrainingCenter) {
		this.nameofTrainingCenter = nameofTrainingCenter;
	}

	public String getHeightInCm() {
		return heightInCm;
	}

	public void setHeightInCm(String heightInCm) {
		this.heightInCm = heightInCm;
	}

	public String getWeightInKg() {
		return weightInKg;
	}

	public void setWeightInKg(String weightInKg) {
		this.weightInKg = weightInKg;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getIdentificationMarkOnBody() {
		return identificationMarkOnBody;
	}

	public void setIdentificationMarkOnBody(String identificationMarkOnBody) {
		this.identificationMarkOnBody = identificationMarkOnBody;
	}

	public String getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}

	public long getBirthCertificateEntryId() {
		return birthCertificateEntryId;
	}

	public void setBirthCertificateEntryId(long birthCertificateEntryId) {
		this.birthCertificateEntryId = birthCertificateEntryId;
	}

	public long getFormNoTwoEntryId() {
		return formNoTwoEntryId;
	}

	public void setFormNoTwoEntryId(long formNoTwoEntryId) {
		this.formNoTwoEntryId = formNoTwoEntryId;
	}

	public long getAadharCardEntryId() {
		return aadharCardEntryId;
	}

	public void setAadharCardEntryId(long aadharCardEntryId) {
		this.aadharCardEntryId = aadharCardEntryId;
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
		return Objects.hash(aadharCardEntryId, birthCertificateEntryId, bloodGroup, castCategory,
				dateofAdmissionInSchoolCollege, formNoTwoEntryId, heightInCm, identificationMarkOnBody, nameofCoach,
				nameofTrainingCenter, photoEntryId, pinCode, postalAddress, userId, weightInKg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolCollageParticipantStudentDTO other = (SchoolCollageParticipantStudentDTO) obj;
		return aadharCardEntryId == other.aadharCardEntryId && birthCertificateEntryId == other.birthCertificateEntryId
				&& Objects.equals(bloodGroup, other.bloodGroup) && Objects.equals(castCategory, other.castCategory)
				&& Objects.equals(dateofAdmissionInSchoolCollege, other.dateofAdmissionInSchoolCollege)
				&& formNoTwoEntryId == other.formNoTwoEntryId && Objects.equals(heightInCm, other.heightInCm)
				&& Objects.equals(identificationMarkOnBody, other.identificationMarkOnBody)
				&& Objects.equals(nameofCoach, other.nameofCoach)
				&& Objects.equals(nameofTrainingCenter, other.nameofTrainingCenter)
				&& photoEntryId == other.photoEntryId && Objects.equals(pinCode, other.pinCode)
				&& Objects.equals(postalAddress, other.postalAddress) && userId == other.userId
				&& Objects.equals(weightInKg, other.weightInKg);
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
