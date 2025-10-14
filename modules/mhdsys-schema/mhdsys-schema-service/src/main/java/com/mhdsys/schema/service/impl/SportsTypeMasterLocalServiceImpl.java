/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.SportsTypeMaster;
import com.mhdsys.schema.service.base.SportsTypeMasterLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.SportsTypeMaster",
	service = AopService.class
)
public class SportsTypeMasterLocalServiceImpl
	extends SportsTypeMasterLocalServiceBaseImpl {
	
	public List<SportsTypeMaster> getByActiveState(boolean isActive){
		return sportsTypeMasterPersistence.findByActiveState(isActive);
	}
}