/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.TrainingCentreFinancialDetail;
import com.mhdsys.schema.service.base.TrainingCentreFinancialDetailLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.TrainingCentreFinancialDetail",
	service = AopService.class
)
public class TrainingCentreFinancialDetailLocalServiceImpl
	extends TrainingCentreFinancialDetailLocalServiceBaseImpl {
	
	/**
	 * Find By TrainingCentreId
	 * @param trainingCentreId
	 * @return
	 */
	public List<TrainingCentreFinancialDetail> findByTrainingCentreId(long trainingCentreId) {
		return trainingCentreFinancialDetailPersistence.findByTrainingCentreId(trainingCentreId);
	}
}