/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.service.base.SportsFacilityMasterLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author VenuGopal
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.SportsFacilityMaster",
	service = AopService.class
)
public class SportsFacilityMasterLocalServiceImpl
	extends SportsFacilityMasterLocalServiceBaseImpl {
	
	public List<SportsFacilityMaster> getByfacilityName(String facilityName) {
		return sportsFacilityMasterPersistence.findByfacilityName(facilityName);
	}
	
	public List<SportsFacilityMaster> getByfacilityType(String facilityType) {
		return sportsFacilityMasterPersistence.findByfacilityType(facilityType);
	}
	
	public List<SportsFacilityMaster> getByType(String type) {
		return sportsFacilityMasterPersistence.findBytype(type);
	}
	
	public List<SportsFacilityMaster> getByCreatorUserId(long creatorUserId) {
		return sportsFacilityMasterPersistence.findBycreatorUserId(creatorUserId);
	}
	
}