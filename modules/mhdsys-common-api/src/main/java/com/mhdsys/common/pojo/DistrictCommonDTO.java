package com.mhdsys.common.pojo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author yetish
 */
public class DistrictCommonDTO {
	
	private long districtId;
	private String districtName;
	private DivisionCommonDTO divisionDTO;
	
	public DistrictCommonDTO(long districtId, String districtName, DivisionCommonDTO divisionDTO) {
		this.districtId = districtId;
		this.districtName = districtName;
		this.divisionDTO = divisionDTO;
	}

	public DivisionCommonDTO getDivisionDTO() {
		return divisionDTO;
	}

	public void setDivisionDTO(DivisionCommonDTO divisionDTO) {
		this.divisionDTO = divisionDTO;
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(districtId, districtName, divisionDTO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DistrictCommonDTO other = (DistrictCommonDTO) obj;
		return districtId == other.districtId && Objects.equals(districtName, other.districtName)
				&& Objects.equals(divisionDTO, other.divisionDTO);
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
