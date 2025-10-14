/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.mhdsys.schema.exception.NoSuchCompetitionMasterException;
import com.mhdsys.schema.model.CompetitionMaster;
import com.mhdsys.schema.service.base.CompetitionMasterLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = "model.class.name=com.mhdsys.schema.model.CompetitionMaster", service = AopService.class)
public class CompetitionMasterLocalServiceImpl extends CompetitionMasterLocalServiceBaseImpl {
	OrderByComparator<CompetitionMaster> orderByComparator = OrderByComparatorFactoryUtil.create("CompetitionMaster",
			"createDate", false);

	public List<CompetitionMaster> findAll() {
		return competitionMasterPersistence.findAll(-1, -1, orderByComparator);
	}
	
	public  CompetitionMaster getUserId(long userId) {
		try {
			return competitionMasterPersistence.findByUserId(userId);
		} catch (NoSuchCompetitionMasterException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}