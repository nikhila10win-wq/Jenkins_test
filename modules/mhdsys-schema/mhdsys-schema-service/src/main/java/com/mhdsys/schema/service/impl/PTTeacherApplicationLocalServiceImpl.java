/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.service.base.PTTeacherApplicationLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = "model.class.name=com.mhdsys.schema.model.PTTeacherApplication", service = AopService.class)
public class PTTeacherApplicationLocalServiceImpl extends PTTeacherApplicationLocalServiceBaseImpl {
//	OrderByComparator<PTTeacherApplication> orderByComparator = OrderByComparatorFactoryUtil
//			.create("PTTeacherApplication", "createDate", false);

//	public List<PTTeacherApplication> getPtteacherListByStatus(long status) {
//		return ptTeacherApplicationPersistence.findBystatus(status, -1, -1, orderByComparator);
//	}
	public List<PTTeacherApplication> getPtTeacherFormByStatus(List<Long> statuses) {
		return ptTeacherApplicationFinder.getPtTeacherFormByStatus(statuses);
	}
	public List<String> getParticipantNames(String participantName) {
		return ptTeacherApplicationFinder.getParticipantNames(participantName);
	}
}