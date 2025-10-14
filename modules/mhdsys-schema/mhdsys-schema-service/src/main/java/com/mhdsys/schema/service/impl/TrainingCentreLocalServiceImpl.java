/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.exception.NoSuchTrainingCentreException;
import com.mhdsys.schema.model.TrainingCentre;
import com.mhdsys.schema.service.base.TrainingCentreLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.TrainingCentre",
	service = AopService.class
)
public class TrainingCentreLocalServiceImpl
	extends TrainingCentreLocalServiceBaseImpl {
	
	
	public TrainingCentre getByUserIdAndFormStatus(long userId,String formStatus) throws NoSuchTrainingCentreException {
		return trainingCentrePersistence.findByGetByUserIdAndFormStatus(userId, formStatus);
	}
	
	
}