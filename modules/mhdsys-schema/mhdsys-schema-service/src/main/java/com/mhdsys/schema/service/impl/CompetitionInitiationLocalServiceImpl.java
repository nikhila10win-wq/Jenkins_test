/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.service.base.CompetitionInitiationLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.CompetitionInitiation",
	service = AopService.class
)
public class CompetitionInitiationLocalServiceImpl
	extends CompetitionInitiationLocalServiceBaseImpl {
	OrderByComparator<CompetitionInitiation> orderByComparator = OrderByComparatorFactoryUtil.create("CompetitionInitiation",
			"createDate", false);
	public List<CompetitionInitiation> findByUserId(long userId){
		return competitionInitiationPersistence.findByUserId(userId, -1, -1, orderByComparator);
	}
	

	public List<CompetitionInitiation> findAll() {
		return competitionInitiationPersistence.findAll(-1, -1, orderByComparator);
	}
}