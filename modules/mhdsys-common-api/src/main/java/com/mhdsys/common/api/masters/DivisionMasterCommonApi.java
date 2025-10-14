package com.mhdsys.common.api.masters;

import com.mhdsys.common.pojo.DivisionCommonDTO;
import com.mhdsys.schema.model.DivisionMaster;

import java.util.List;

/**
 * @author yetish
 */
public interface DivisionMasterCommonApi {

	List<DivisionMaster> getDivisionByActiveState(boolean isActive);
	
	List<DivisionCommonDTO> getAllDivisions(String language, boolean isActive);
	
	DivisionCommonDTO getDivisionByDivisionId(String language,boolean isActive, long divisionId);
}
