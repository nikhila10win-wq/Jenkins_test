/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.schema.exception.NoSuchRegistrationFormTypeException;
import com.mhdsys.schema.model.RegistrationFormType;
import com.mhdsys.schema.service.base.RegistrationFormTypeLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.RegistrationFormType",
	service = AopService.class
)
public class RegistrationFormTypeLocalServiceImpl
	extends RegistrationFormTypeLocalServiceBaseImpl {
	
	private static Log LOGGER = LogFactoryUtil.getLog(RegistrationFormTypeLocalServiceImpl.class);
	
	public RegistrationFormType getByFormTypeName(String formTypeName) {
		try {
			return registrationFormTypePersistence.findByFormTypeName(formTypeName);
		} catch (NoSuchRegistrationFormTypeException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}