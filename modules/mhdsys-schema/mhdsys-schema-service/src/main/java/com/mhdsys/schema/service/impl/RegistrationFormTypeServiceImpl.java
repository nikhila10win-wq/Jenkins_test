/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.schema.model.RegistrationFormType;
import com.mhdsys.schema.service.base.RegistrationFormTypeServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=mhdsys",
		"json.web.service.context.path=RegistrationFormType"
	},
	service = AopService.class
)
public class RegistrationFormTypeServiceImpl
	extends RegistrationFormTypeServiceBaseImpl {
	
	@JSONWebService
	public RegistrationFormType addRegistrationFormType(String formTypeName) {
		if(Validator.isNotNull(formTypeName)) {
		    RegistrationFormType registrationFormType = registrationFormTypePersistence.fetchByFormTypeName(formTypeName);
		    
		    if (Validator.isNull(registrationFormType)) {
		        registrationFormType = registrationFormTypeLocalService.createRegistrationFormType(
		                counterLocalService.increment(RegistrationFormType.class.getName()));
		        registrationFormType.setFormType(formTypeName);
		        String resultType = formTypeName.replaceAll("[^a-zA-Z0-9\\s]", " ");
		        registrationFormType.setFormName(resultType);
		        registrationFormType = registrationFormTypeLocalService.updateRegistrationFormType(registrationFormType);
		    }
		    
		    return registrationFormType;
		}
		return null;
	}
}