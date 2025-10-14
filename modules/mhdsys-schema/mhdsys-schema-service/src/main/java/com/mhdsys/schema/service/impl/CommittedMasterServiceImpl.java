/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;

import com.mhdsys.schema.service.base.CommittedMasterServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=mhdsys",
		"json.web.service.context.path=CommittedMaster"
	},
	service = AopService.class
)
public class CommittedMasterServiceImpl extends CommittedMasterServiceBaseImpl {
}