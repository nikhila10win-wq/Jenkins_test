package com.mhdsys.common.api.masters;

import com.mhdsys.common.pojo.TalukaDTOCommonDTO;
import com.mhdsys.schema.model.TalukaMaster;

import java.util.List;

/**
 * @author yetish
 */
public interface TalukaMasterCommonApi {

	List<TalukaDTOCommonDTO> getAllTalukasByDivisionIdAndDistrictId(String language, boolean isActive, long divisionId,  long districtId);
	
	TalukaDTOCommonDTO getTalukaByTalukaId(String language, boolean isActive, long takulaId);
	
	TalukaDTOCommonDTO getDDTbyDDTids(String language, boolean isActive, long divisionId,  long districtId, long takulaId);
	
	List<TalukaMaster> isActiveState(boolean isActive);
	
	List<TalukaMaster> getTalukaByDistrictId(long districtId);
	
}
