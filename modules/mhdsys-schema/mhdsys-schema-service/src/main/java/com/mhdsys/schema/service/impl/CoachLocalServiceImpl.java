/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.exception.NoSuchCoachException;
import com.mhdsys.schema.model.Coach;
import com.mhdsys.schema.service.base.CoachLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.Coach",
	service = AopService.class
)
public class CoachLocalServiceImpl extends CoachLocalServiceBaseImpl {
	
	public List<Coach> findByTrainingCentreId(long trainingCentreId){
		List<Coach> coaches = coachPersistence.findByTrainingCentreId(trainingCentreId);
		return coaches;
	}
	
	public Coach findCoachByEmail(String email) throws NoSuchCoachException {
		return coachPersistence.findByEmail(email);
	}
	
	
}