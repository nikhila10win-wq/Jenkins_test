/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.schema.exception.NoSuchSchoolCollegeOfficerRegistrationException;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.base.SchoolCollegeOfficerRegistrationLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.SchoolCollegeOfficerRegistration",
	service = AopService.class
)
public class SchoolCollegeOfficerRegistrationLocalServiceImpl
	extends SchoolCollegeOfficerRegistrationLocalServiceBaseImpl {
	private Log LOGGER = LogFactoryUtil.getLog(SchoolCollegeOfficerRegistrationLocalServiceBaseImpl.class);

	public List<Object[]>  getParticipantNames(String participantName) {
		return schoolCollegeOfficerRegistrationFinder.fetchParticipantNameAndUserId(participantName);
	}
	public  SchoolCollegeOfficerRegistration findByUserId(long userId) {
		try {
			return schoolCollegeOfficerRegistrationPersistence.findByUserId(userId);
		} catch (NoSuchSchoolCollegeOfficerRegistrationException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);
		}
		return null;
	}
	public  SchoolCollegeOfficerRegistration findByUserIdAndDesignation(long userId,long currentDesignation) {
		try {
			return schoolCollegeOfficerRegistrationPersistence.findByUserIdAndCurrentDesignation(userId, currentDesignation);
		} catch (NoSuchSchoolCollegeOfficerRegistrationException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);
		}
		return null;
	}
}