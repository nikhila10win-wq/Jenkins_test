/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.RoasterStatus;
import com.mhdsys.schema.service.base.RoasterStatusLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.RoasterStatus",
	service = AopService.class
)
public class RoasterStatusLocalServiceImpl
	extends RoasterStatusLocalServiceBaseImpl {
	public RoasterStatus findByUserId(long userId) {
		try {
			return roasterStatusPersistence.findByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}