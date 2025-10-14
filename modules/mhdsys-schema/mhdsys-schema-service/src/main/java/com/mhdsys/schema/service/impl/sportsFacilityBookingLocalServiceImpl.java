/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.service.base.sportsFacilityBookingLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author VenuGopal
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.sportsFacilityBooking",
	service = AopService.class
)
public class sportsFacilityBookingLocalServiceImpl
	extends sportsFacilityBookingLocalServiceBaseImpl {
	
	public List<sportsFacilityBooking> getByType(String type) {
		return sportsFacilityBookingPersistence.findBytype(type);
	}
	
	public List<sportsFacilityBooking> getByCreatorUserId(long creatorUserId) {
		return sportsFacilityBookingPersistence.findBycreatorUserId(creatorUserId);
	}
	
	public List<sportsFacilityBooking> getByIsApproved(boolean isApproved) {
		return sportsFacilityBookingPersistence.findByisApproved(isApproved);
	}
	
}