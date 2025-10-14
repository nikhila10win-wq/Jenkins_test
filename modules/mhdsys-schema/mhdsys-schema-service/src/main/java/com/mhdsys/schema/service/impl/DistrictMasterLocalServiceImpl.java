/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.service.base.DistrictMasterLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.DistrictMaster",
	service = AopService.class
)
public class DistrictMasterLocalServiceImpl
	extends DistrictMasterLocalServiceBaseImpl {
	public List<Object> getAllDistrictsByDivisionId(String language, boolean isActive, long divisionId){
		return districtMasterFinder.fetchAllDistrictsByDivisionId(language, isActive, divisionId);
	}
	
	public Object getDistrictByDistrictId(String language, boolean isActive, long districtId){
		return districtMasterFinder.fetchDistrictByDistrictId(language, isActive, districtId);
	}
	
	public List<DistrictMaster> getByActiveState(boolean isActive) {
		return districtMasterPersistence.findByActiveState(isActive);
	}
	
	public List<DistrictMaster> getDistrictByDivisionId(long divisionId) {
		return districtMasterPersistence.findByDivisionId(divisionId);
	}
	
	public Object [] getNewDistrictByDistrictId(String language, boolean isActive, long districtId){
		return districtMasterFinder.fetchNewDistrictByDistrictId(language, isActive, districtId);
	}
	
	public DistrictMaster findByname(String name) {
		try {
			return districtMasterPersistence.findByname(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}