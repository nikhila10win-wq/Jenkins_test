package com.mhdsys.common.pojo.profile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.api.profile.UserIdentifiable;

import java.util.Date;
import java.util.Objects;

public class CoachAndSportPersonDTO implements UserIdentifiable{

	private Date dateOfBirth;
	private long birthCertificateEntryId;
	private String coachingDates;
	private String postalAddress;
	private String pinCode;
	private long photoEntryId;
	private long nationalityCertificateEntryId;
	private boolean disabilityApplicable;
	private long disabilityCertificateEntryId;
	private String qualification;
	private String typeofJobBusiness;
	private String designation;
	private String officeAddress;
	private boolean anyCrimeRegistered;
	private String policeStationName;
	private String policeStationAddress;
	private Date dateOfRegistration;
	private long characterCertificateEntryId;
	private String bankName;
	private String branchName;
	private String accountNumber;
	private String IFSCcode;
	private boolean acknowledgement;
	private long userId;
	private String roleName;
	private String videoLink;
	private long cancelledChequeEntryId;
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public long getBirthCertificateEntryId() {
		return birthCertificateEntryId;
	}
	public void setBirthCertificateEntryId(long birthCertificateEntryId) {
		this.birthCertificateEntryId = birthCertificateEntryId;
	}
	public String getCoachingDates() {
		return coachingDates;
	}
	public void setCoachingDates(String coachingDates) {
		this.coachingDates = coachingDates;
	}
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
	public long getNationalityCertificateEntryId() {
		return nationalityCertificateEntryId;
	}
	public void setNationalityCertificateEntryId(long nationalityCertificateEntryId) {
		this.nationalityCertificateEntryId = nationalityCertificateEntryId;
	}
	public boolean isDisabilityApplicable() {
		return disabilityApplicable;
	}
	public void setDisabilityApplicable(boolean disabilityApplicable) {
		this.disabilityApplicable = disabilityApplicable;
	}
	public long getDisabilityCertificateEntryId() {
		return disabilityCertificateEntryId;
	}
	public void setDisabilityCertificateEntryId(long disabilityCertificateEntryId) {
		this.disabilityCertificateEntryId = disabilityCertificateEntryId;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getTypeofJobBusiness() {
		return typeofJobBusiness;
	}
	public void setTypeofJobBusiness(String typeofJobBusiness) {
		this.typeofJobBusiness = typeofJobBusiness;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public boolean isAnyCrimeRegistered() {
		return anyCrimeRegistered;
	}
	public void setAnyCrimeRegistered(boolean anyCrimeRegistered) {
		this.anyCrimeRegistered = anyCrimeRegistered;
	}
	public String getPoliceStationName() {
		return policeStationName;
	}
	public void setPoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName;
	}
	public String getPoliceStationAddress() {
		return policeStationAddress;
	}
	public void setPoliceStationAddress(String policeStationAddress) {
		this.policeStationAddress = policeStationAddress;
	}
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public long getCharacterCertificateEntryId() {
		return characterCertificateEntryId;
	}
	public void setCharacterCertificateEntryId(long characterCertificateEntryId) {
		this.characterCertificateEntryId = characterCertificateEntryId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIFSCcode() {
		return IFSCcode;
	}
	public void setIFSCcode(String iFSCcode) {
		IFSCcode = iFSCcode;
	}
	public boolean isAcknowledgement() {
		return acknowledgement;
	}
	public void setAcknowledgement(boolean acknowledgement) {
		this.acknowledgement = acknowledgement;
	}
	@Override
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	public long getCancelledChequeEntryId() {
		return cancelledChequeEntryId;
	}
	public void setCancelledChequeEntryId(long cancelledChequeEntryId) {
		this.cancelledChequeEntryId = cancelledChequeEntryId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(IFSCcode, accountNumber, acknowledgement, anyCrimeRegistered, bankName,
				birthCertificateEntryId, branchName, cancelledChequeEntryId, characterCertificateEntryId, coachingDates,
				dateOfBirth, dateOfRegistration, designation, disabilityApplicable, disabilityCertificateEntryId,
				nationalityCertificateEntryId, officeAddress, photoEntryId, pinCode, policeStationAddress,
				policeStationName, postalAddress, qualification, roleName, typeofJobBusiness, userId, videoLink);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoachAndSportPersonDTO other = (CoachAndSportPersonDTO) obj;
		return Objects.equals(IFSCcode, other.IFSCcode) && Objects.equals(accountNumber, other.accountNumber)
				&& acknowledgement == other.acknowledgement && anyCrimeRegistered == other.anyCrimeRegistered
				&& Objects.equals(bankName, other.bankName) && birthCertificateEntryId == other.birthCertificateEntryId
				&& Objects.equals(branchName, other.branchName)
				&& cancelledChequeEntryId == other.cancelledChequeEntryId
				&& characterCertificateEntryId == other.characterCertificateEntryId
				&& Objects.equals(coachingDates, other.coachingDates) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(dateOfRegistration, other.dateOfRegistration)
				&& Objects.equals(designation, other.designation) && disabilityApplicable == other.disabilityApplicable
				&& disabilityCertificateEntryId == other.disabilityCertificateEntryId
				&& nationalityCertificateEntryId == other.nationalityCertificateEntryId
				&& Objects.equals(officeAddress, other.officeAddress) && photoEntryId == other.photoEntryId
				&& Objects.equals(pinCode, other.pinCode)
				&& Objects.equals(policeStationAddress, other.policeStationAddress)
				&& Objects.equals(policeStationName, other.policeStationName)
				&& Objects.equals(postalAddress, other.postalAddress)
				&& Objects.equals(qualification, other.qualification) && Objects.equals(roleName, other.roleName)
				&& Objects.equals(typeofJobBusiness, other.typeofJobBusiness) && userId == other.userId
				&& Objects.equals(videoLink, other.videoLink);
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
    
    public CoachAndSportPersonDTO validateFields() {
        if (Validator.isNull(dateOfBirth) || Validator.isNull(birthCertificateEntryId) ||
            Validator.isNull(coachingDates) || Validator.isNull(postalAddress) ||
            Validator.isNull(pinCode) ||Validator.isNull(nationalityCertificateEntryId) || Validator.isNull(disabilityApplicable) ||
            Validator.isNull(qualification) ||Validator.isNull(typeofJobBusiness) || Validator.isNull(designation) || 
            Validator.isNull(officeAddress) || Validator.isNull(anyCrimeRegistered) ||
            Validator.isNull(characterCertificateEntryId) ||
            Validator.isNull(bankName) || Validator.isNull(branchName) || 
            Validator.isNull(accountNumber) || Validator.isNull(IFSCcode) ||
            Validator.isNull(acknowledgement) || Validator.isNull(userId) ||
            Validator.isNull(roleName) || Validator.isNull(cancelledChequeEntryId)) {
            return null; 
        }
        if(disabilityApplicable) {
        	if(Validator.isNull(disabilityCertificateEntryId))
        		return null;
        }
        if(anyCrimeRegistered) {
        	if(Validator.isNull(policeStationName) || Validator.isNull(policeStationAddress) || Validator.isNull(dateOfRegistration))
        		return null;
        }
        if(roleName.trim().equalsIgnoreCase("Adventure Person")) {
        	if(Validator.isNull(videoLink))
        		return null;
        }
        return this; 
    }
	
}
