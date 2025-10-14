/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.mhdsys.schema.exception.NoSuchAwardApplicationException;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.base.AwardApplicationLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = "model.class.name=com.mhdsys.schema.model.AwardApplication", service = AopService.class)
public class AwardApplicationLocalServiceImpl extends AwardApplicationLocalServiceBaseImpl {
	OrderByComparator<AwardApplication> orderByComparator = OrderByComparatorFactoryUtil.create("AwardApplication",
			"createDate", false);
	
	public List<AwardApplication> getByStatusAndUserId(long userId, String status) {
		return awardApplicationPersistence.findByStatusAndUserId(userId, status, -1, -1, orderByComparator);
	}
	
	public List<AwardApplication> getByAssociationUserId(long userId) {
		return awardApplicationPersistence.findByAssociationUserId(userId);
	}

	@Override
	public List<AwardApplication> getAwardApplicationsByuserId(long userId) {
		return awardApplicationPersistence.findByUserId(userId, -1, -1, orderByComparator);
	}

	@Override
	public List<AwardApplication> getByStatus(String status) {
		return awardApplicationPersistence.findByStatus(status);
	}
	@Override
	public AwardApplication findByCompetitionName(String competitionName) {
		try {
			return awardApplicationPersistence.findByCompetitionName(competitionName);
		} catch (NoSuchAwardApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}