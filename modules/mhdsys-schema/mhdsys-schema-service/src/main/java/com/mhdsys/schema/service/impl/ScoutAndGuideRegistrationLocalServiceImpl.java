/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.exception.NoSuchScoutAndGuideRegistrationException;
import com.mhdsys.schema.model.ScoutAndGuideRegistration;
import com.mhdsys.schema.service.base.ScoutAndGuideRegistrationLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.ScoutAndGuideRegistration",
	service = AopService.class
)
public class ScoutAndGuideRegistrationLocalServiceImpl
	extends ScoutAndGuideRegistrationLocalServiceBaseImpl {
	
	public ScoutAndGuideRegistration findByUserId(long userId) {
		try {
			return scoutAndGuideRegistrationPersistence.findByUserId(userId);
		} catch (NoSuchScoutAndGuideRegistrationException e) {
			e.printStackTrace();
		}
		return null;
	}
}