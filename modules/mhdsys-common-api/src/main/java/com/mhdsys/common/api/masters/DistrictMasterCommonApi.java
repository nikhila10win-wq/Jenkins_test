package com.mhdsys.common.api.masters;

import com.mhdsys.common.pojo.DistrictCommonDTO;
import com.mhdsys.schema.model.DistrictMaster;

import java.util.List;

/**
 * @author yetish
 */
public interface DistrictMasterCommonApi {

	List<DistrictMaster> getDistrictByDivisionId(long divisionId);
	
	List<DistrictMaster> getByActiveState(boolean isActive);
	
	DistrictCommonDTO getDistrictByDistrictId(String language, boolean isActive, long districtId);
	
	List<DistrictCommonDTO> getAllDistrictsByDivisionId(String language, boolean isActive, long divisionId);
}
