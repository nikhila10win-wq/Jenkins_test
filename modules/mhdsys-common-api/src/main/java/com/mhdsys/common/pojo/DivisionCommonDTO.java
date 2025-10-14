package com.mhdsys.common.pojo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author yetish
 */
public class DivisionCommonDTO {

	private long divisionId;
	private String divisionName;
	
	public DivisionCommonDTO(long divisionId, String divisionName) {
		this.divisionId = divisionId;
		this.divisionName = divisionName;
	}
	public long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(divisionId, divisionName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DivisionCommonDTO other = (DivisionCommonDTO) obj;
		return divisionId == other.divisionId && Objects.equals(divisionName, other.divisionName);
	}
	@Override
	public String toString() {
		return convertToJson();
	}
    private String convertToJson() { 
        try { 
        	if (hasNullFields()) return null; 
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
    private boolean hasNullFields() {
        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(this) == null) {
                    return true; 
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
