/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.service.base.DivisionMasterLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = "model.class.name=com.mhdsys.schema.model.DivisionMaster", service = AopService.class)
public class DivisionMasterLocalServiceImpl extends DivisionMasterLocalServiceBaseImpl {
	public List<DivisionMaster> getByActiveState(boolean isActive) {
		return divisionMasterPersistence.findByActiveState(isActive);
	}

	public List<Object> getAllDivisions(String language, boolean isActive) {
		return divisionMasterFinder.fetchAllDivisions(language, isActive);
	}

	public Object getDivisionByDivisionId(String language, boolean isActive, long divisionId) {
		return divisionMasterFinder.fetchDivisionByDivisionId(language, isActive, divisionId);
	}
	
	public List<Object[]> getAllNewDivisionByDivisionId(String language, boolean isActive){
		return divisionMasterFinder.findNewActiveDivisionsByLanguage(language, isActive);
	}

	
	public Object[] getNewDivisionByDivisionId(String language, boolean isActive, long districtId) {
		return divisionMasterFinder.fetchNewDivisionByDivisionId(language,isActive,districtId);
	}
	
	
	
}