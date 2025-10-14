/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.StudentRegistration;
import com.mhdsys.schema.service.base.StudentRegistrationLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.StudentRegistration",
	service = AopService.class
)
public class StudentRegistrationLocalServiceImpl
	extends StudentRegistrationLocalServiceBaseImpl {
	public List<StudentRegistration> findByGender(String gender){
		return studentRegistrationPersistence.findByGender(gender);
	}
	public List<StudentRegistration> findByRegisterTo(String registerTo){
		return studentRegistrationPersistence.findByRegisterTo(registerTo);
	}
}
