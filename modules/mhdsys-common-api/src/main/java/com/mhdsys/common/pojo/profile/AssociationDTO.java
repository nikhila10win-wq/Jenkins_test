package com.mhdsys.common.pojo.profile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhdsys.common.api.profile.UserIdentifiable;

import java.util.Date;
import java.util.Objects;

public class AssociationDTO implements UserIdentifiable{

	private String postalAddress;
	private String pinCode;
	private String website;
	private Date foundingDate;
	private String affiliatedWith;
	private String tanNo;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getFoundingDate() {
		return foundingDate;
	}

	public void setFoundingDate(Date foundingDate) {
		this.foundingDate = foundingDate;
	}

	public String getAffiliatedWith() {
		return affiliatedWith;
	}

	public void setAffiliatedWith(String affiliatedWith) {
		this.affiliatedWith = affiliatedWith;
	}

	public String getTanNo() {
		return tanNo;
	}

	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
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
		return Objects.hash(affiliatedWith, foundingDate, pinCode, postalAddress, tanNo, userId, website);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssociationDTO other = (AssociationDTO) obj;
		return Objects.equals(affiliatedWith, other.affiliatedWith) && Objects.equals(foundingDate, other.foundingDate)
				&& Objects.equals(pinCode, other.pinCode) && Objects.equals(postalAddress, other.postalAddress)
				&& Objects.equals(tanNo, other.tanNo) && userId == other.userId
				&& Objects.equals(website, other.website);
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
