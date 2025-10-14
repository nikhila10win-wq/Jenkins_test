package com.mhdsys.common.pojo.profile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhdsys.common.api.profile.UserIdentifiable;

import java.util.Date;
import java.util.Objects;

public class CoachSportPersonDTO implements UserIdentifiable{
	
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
	private String Designation;
	private String officeAddress;
	private boolean isAnyCrimeRegistered;
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
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public boolean isAnyCrimeRegistered() {
		return isAnyCrimeRegistered;
	}

	public void setAnyCrimeRegistered(boolean isAnyCrimeRegistered) {
		this.isAnyCrimeRegistered = isAnyCrimeRegistered;
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

	@Override
	public int hashCode() {
		return Objects.hash(Designation, IFSCcode, accountNumber, acknowledgement, bankName, birthCertificateEntryId,
				branchName, characterCertificateEntryId, coachingDates, dateOfBirth, dateOfRegistration,
				disabilityApplicable, disabilityCertificateEntryId, isAnyCrimeRegistered, nationalityCertificateEntryId,
				officeAddress, photoEntryId, pinCode, policeStationAddress, policeStationName, postalAddress,
				qualification, typeofJobBusiness, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoachSportPersonDTO other = (CoachSportPersonDTO) obj;
		return Objects.equals(Designation, other.Designation) && Objects.equals(IFSCcode, other.IFSCcode)
				&& Objects.equals(accountNumber, other.accountNumber) && acknowledgement == other.acknowledgement
				&& Objects.equals(bankName, other.bankName) && birthCertificateEntryId == other.birthCertificateEntryId
				&& Objects.equals(branchName, other.branchName)
				&& characterCertificateEntryId == other.characterCertificateEntryId
				&& Objects.equals(coachingDates, other.coachingDates) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(dateOfRegistration, other.dateOfRegistration)
				&& disabilityApplicable == other.disabilityApplicable
				&& disabilityCertificateEntryId == other.disabilityCertificateEntryId
				&& isAnyCrimeRegistered == other.isAnyCrimeRegistered
				&& nationalityCertificateEntryId == other.nationalityCertificateEntryId
				&& Objects.equals(officeAddress, other.officeAddress) && photoEntryId == other.photoEntryId
				&& Objects.equals(pinCode, other.pinCode)
				&& Objects.equals(policeStationAddress, other.policeStationAddress)
				&& Objects.equals(policeStationName, other.policeStationName)
				&& Objects.equals(postalAddress, other.postalAddress)
				&& Objects.equals(qualification, other.qualification)
				&& Objects.equals(typeofJobBusiness, other.typeofJobBusiness) && userId == other.userId;
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
