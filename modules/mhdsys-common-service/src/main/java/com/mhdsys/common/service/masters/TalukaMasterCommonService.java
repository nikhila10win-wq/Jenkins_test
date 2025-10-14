package com.mhdsys.common.service.masters;

import com.mhdsys.common.api.masters.TalukaMasterCommonApi;
import com.mhdsys.common.pojo.DistrictCommonDTO;
import com.mhdsys.common.pojo.DivisionCommonDTO;
import com.mhdsys.common.pojo.TalukaDTOCommonDTO;
import com.mhdsys.schema.model.TalukaMaster;
import com.mhdsys.schema.service.TalukaMasterLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author yetish
 */
@Component(immediate = true, service = TalukaMasterCommonApi.class)
public class TalukaMasterCommonService implements TalukaMasterCommonApi {

	@Reference
	private TalukaMasterLocalService talukaLocalService;

	@Override
	public List<TalukaDTOCommonDTO> getAllTalukasByDivisionIdAndDistrictId(String language, boolean isActive, long divisionId,
			long districtId) {
		return talukaLocalService.getAllTalukasByDivisionIdAndDistrictId(language, isActive, divisionId, districtId)
				.stream().map(entity -> {
					Object[] row = (Object[]) entity;
					return new TalukaDTOCommonDTO(new DistrictCommonDTO((Long) row[2], (String) row[3],
							new DivisionCommonDTO((Long) row[0], (String) row[1])), (Long) row[4], (String) row[5]);
				}).toList();
	}

	@Override
	public TalukaDTOCommonDTO getTalukaByTalukaId(String language, boolean isActive, long takulaId) {
		Object object = (Object) talukaLocalService.getTalukaByTalukaId(language, isActive, takulaId);
		if (object instanceof Object[]) {
			Object[] row = (Object[]) object;
			return new TalukaDTOCommonDTO(
					new DistrictCommonDTO((Long) row[2], (String) row[3], new DivisionCommonDTO((Long) row[0], (String) row[1])),
					(Long) row[4], (String) row[5]);
		}
		return null;
	}

	@Override
	public TalukaDTOCommonDTO getDDTbyDDTids(String language, boolean isActive, long divisionId, long districtId,
			long takulaId) {
		Object object = (Object) talukaLocalService.getDDTbyDDTids(language, isActive, divisionId, districtId,
				takulaId);
		if (object instanceof Object[]) {
			Object[] row = (Object[]) object;
			return new TalukaDTOCommonDTO(
					new DistrictCommonDTO((Long) row[2], (String) row[3], new DivisionCommonDTO((Long) row[0], (String) row[1])),
					(Long) row[4], (String) row[5]);
		}
		return null;
	}

	@Override
	public List<TalukaMaster> isActiveState(boolean isActive) {
		return talukaLocalService.getByActiveState(isActive);
	}

	@Override
	public List<TalukaMaster> getTalukaByDistrictId(long districtId) {
		return talukaLocalService.getTalukaByDistrictId(districtId);
	}

}
