package com.mhdsys.common.service.masters;

import com.mhdsys.common.api.masters.DistrictMasterCommonApi;
import com.mhdsys.common.pojo.DistrictCommonDTO;
import com.mhdsys.common.pojo.DivisionCommonDTO;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.service.DistrictMasterLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author yetish
 */
@Component(immediate = true, service = DistrictMasterCommonApi.class)
public class DistrictMasterCommonService implements DistrictMasterCommonApi{
	
	@Reference private DistrictMasterLocalService districtLocalService;

	@Override
	public List<DistrictMaster> getDistrictByDivisionId(long divisionId) {
		return districtLocalService.getDistrictByDivisionId(divisionId);
	}

	@Override
	public List<DistrictMaster> getByActiveState(boolean isActive) {
		return districtLocalService.getByActiveState(isActive);
	}

	@Override
	public DistrictCommonDTO getDistrictByDistrictId(String language, boolean isActive, long districtId) {
		Object object = (Object)districtLocalService.getDistrictByDistrictId(language, isActive, districtId);
		if (object instanceof Object[]) {
		    Object[] row = (Object[]) object;
		    return new DistrictCommonDTO((Long) row[0], (String) row[1], new DivisionCommonDTO((Long) row[2], (String) row[3]));
		}
		return null;
	}

	@Override
	public List<DistrictCommonDTO> getAllDistrictsByDivisionId(String language, boolean isActive, long divisionId) {
		return districtLocalService.getAllDistrictsByDivisionId(language, isActive, divisionId)
		        .stream()
		        .map(entity ->{
		        	Object[] row = (Object[]) entity; 
		            return new DistrictCommonDTO((Long) row[0], (String) row[1], new DivisionCommonDTO((Long) row[2], (String) row[3]));
		        })
		        .toList();
	}

	

}
