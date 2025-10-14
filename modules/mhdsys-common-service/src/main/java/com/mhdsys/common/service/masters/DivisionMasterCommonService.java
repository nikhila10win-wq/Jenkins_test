package com.mhdsys.common.service.masters;

import com.mhdsys.common.api.masters.DivisionMasterCommonApi;
import com.mhdsys.common.pojo.DivisionCommonDTO;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.service.DivisionMasterLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author yetish
 */
@Component(immediate = true, service = DivisionMasterCommonApi.class)
public class DivisionMasterCommonService implements DivisionMasterCommonApi{
	
	@Reference private DivisionMasterLocalService divisionLocalService;

	@Override
	public List<DivisionMaster> getDivisionByActiveState(boolean isActive) {
		return divisionLocalService.getByActiveState(isActive);
	}

	@Override
	public List<DivisionCommonDTO> getAllDivisions(String language, boolean isActive) {
		return divisionLocalService.getAllDivisions(language,isActive)
		        .stream()
		        .map(entity ->{
		        	Object[] row = (Object[]) entity; 
		            return new DivisionCommonDTO((Long) row[0], (String) row[1]);
		        })
		        .toList();
		}

	@Override
	public DivisionCommonDTO getDivisionByDivisionId(String language, boolean isActive, long divisionId) {
		Object object = (Object)divisionLocalService.getDivisionByDivisionId(language,isActive,divisionId);
		if (object instanceof Object[]) {
		    Object[] row = (Object[]) object;
		    return new DivisionCommonDTO((Long) row[0], (String) row[1]);
		}
		return null;
	}

}
