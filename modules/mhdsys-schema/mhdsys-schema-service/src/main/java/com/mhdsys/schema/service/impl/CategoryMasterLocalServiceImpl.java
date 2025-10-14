/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.model.CategoryMaster;
import com.mhdsys.schema.service.base.CategoryMasterLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.CategoryMaster",
	service = AopService.class
)
public class CategoryMasterLocalServiceImpl
	extends CategoryMasterLocalServiceBaseImpl {
	
	public List<CategoryMaster> getByActiveState(boolean isActive){
		return categoryMasterPersistence.findByActiveState(isActive);
	}
}