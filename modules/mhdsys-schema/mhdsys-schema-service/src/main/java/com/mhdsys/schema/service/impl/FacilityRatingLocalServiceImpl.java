/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.exception.NoSuchFacilityRatingException;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.model.FacilityRating;
import com.mhdsys.schema.service.base.FacilityRatingLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Venu
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.FacilityRating",
	service = AopService.class
)
public class FacilityRatingLocalServiceImpl
	extends FacilityRatingLocalServiceBaseImpl {
	
	public List<FacilityRating> getByBycreatorUserId(long userId) {
		return facilityRatingPersistence.findBycreatorUserId(userId);
	}
	public FacilityRating getByByratingUniqueId(String ratingUniqueId) {
		try {
			return facilityRatingPersistence.findByratingUniqueId(ratingUniqueId);
		} catch (NoSuchFacilityRatingException e) {
			e.printStackTrace();
		}
		return null;
	}
}