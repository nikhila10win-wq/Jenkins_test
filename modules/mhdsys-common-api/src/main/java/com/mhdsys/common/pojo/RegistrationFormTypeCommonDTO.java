package com.mhdsys.common.pojo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.Objects;

public class RegistrationFormTypeCommonDTO {

	private String uuid;
	private long typeId;
	private String formType;
	private String formName;
	
	public RegistrationFormTypeCommonDTO(String uuid, long typeId, String formType, String formName) {
		this.uuid = uuid;
		this.typeId = typeId;
		this.formType = formType;
		this.formName = formName;
	}
	public RegistrationFormTypeCommonDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(formName, formType, typeId, uuid);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationFormTypeCommonDTO other = (RegistrationFormTypeCommonDTO) obj;
		return Objects.equals(formName, other.formName) && Objects.equals(formType, other.formType)
				&& typeId == other.typeId && Objects.equals(uuid, other.uuid);
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
