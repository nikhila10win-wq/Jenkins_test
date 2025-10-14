/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.TalukaMaster;
import com.mhdsys.schema.service.base.TalukaMasterLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.TalukaMaster",
	service = AopService.class
)
public class TalukaMasterLocalServiceImpl
	extends TalukaMasterLocalServiceBaseImpl {
	public List<Object> getAllTalukasByDivisionIdAndDistrictId(String language, boolean isActive, long divisionId,  long districtId){
		return talukaMasterFinder.fetchAllTalukasByDivisionIdAndDistrictId(language, isActive, divisionId, districtId);
	}
	
	public Object getTalukaByTalukaId(String language, boolean isActive, long takulaId) {
		return talukaMasterFinder.fetchTalukaByTalukaId(language, isActive, takulaId);
	}
	
	public Object getDDTbyDDTids(String language, boolean isActive, long divisionId,  long districtId, long takulaId) {
		return talukaMasterFinder.fetchDDTbyDDTids(language, isActive, divisionId, districtId, takulaId);
	}
	
	public List<TalukaMaster> getByActiveState(boolean isActive) {
		return talukaMasterPersistence.findByActiveState(isActive);
	}
	
	public List<TalukaMaster> getTalukaByDistrictId(long districtId) {
		return talukaMasterPersistence.findByDistrictId(districtId);
	}
	
	public Object[] getNewTalukaByTalukaId(String language, boolean isActive, long takulaId) {
		return talukaMasterFinder.fetchNewTalukaByTalukaId(language, isActive, takulaId);
	}
}