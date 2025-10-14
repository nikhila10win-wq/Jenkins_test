/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.exception.NoSuchConstructionTrackerException;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.service.base.ConstructionTrackerLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author VenuGopal
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.ConstructionTracker",
	service = AopService.class
)
public class ConstructionTrackerLocalServiceImpl
	extends ConstructionTrackerLocalServiceBaseImpl {
	
	public List<ConstructionTracker> getByApplicationStatus(long statusId) {
		return constructionTrackerPersistence.findByapplicationStatus(statusId);
	}
	
	public List<ConstructionTracker> getByByHoldWithUserId(long userId) {
		return constructionTrackerPersistence.findByholdWithUserId(userId);
	}
	
	public ConstructionTracker getByIntiatorUserId(long userId) {
		try {
			return constructionTrackerPersistence.findByIntiatorUserId(userId);
		} catch (NoSuchConstructionTrackerException e) {
			e.printStackTrace();
		}
		return null;
	}
}