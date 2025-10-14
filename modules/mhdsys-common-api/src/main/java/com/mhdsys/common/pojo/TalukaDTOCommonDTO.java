package com.mhdsys.common.pojo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author yetish
 */
public class TalukaDTOCommonDTO {
	
	private DistrictCommonDTO districtDTO;
	private long talukaid;
	private String talukaName;
	
	public TalukaDTOCommonDTO(DistrictCommonDTO districtDTO, long talukaid, String talukaName) {
		this.districtDTO = districtDTO;
		this.talukaid = talukaid;
		this.talukaName = talukaName;
	}

	public DistrictCommonDTO getDistrictDTO() {
		return districtDTO;
	}

	public void setDistrictDTO(DistrictCommonDTO districtDTO) {
		this.districtDTO = districtDTO;
	}

	public long getTalukaid() {
		return talukaid;
	}

	public void setTalukaid(long talukaid) {
		this.talukaid = talukaid;
	}

	public String getTalukaName() {
		return talukaName;
	}

	public void setTalukaName(String talukaName) {
		this.talukaName = talukaName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(districtDTO, talukaName, talukaid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TalukaDTOCommonDTO other = (TalukaDTOCommonDTO) obj;
		return Objects.equals(districtDTO, other.districtDTO) && Objects.equals(talukaName, other.talukaName)
				&& talukaid == other.talukaid;
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
