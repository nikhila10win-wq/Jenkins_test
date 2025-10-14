/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.exception.NoSuchProfileException;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
import com.mhdsys.schema.service.base.ProfileLocalServiceBaseImpl;

import java.util.Optional;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.Profile",
	service = AopService.class
)
public class ProfileLocalServiceImpl extends ProfileLocalServiceBaseImpl {
	
	public Optional<Profile> getByUserId(long userId) {
	    try {
	        return Optional.ofNullable(profilePersistence.findByUserId(userId));
	    } catch (NoSuchProfileException e) {}
	    return Optional.empty();
	}
	public Profile findByUserId(long userId) {
		try {
			return profilePersistence.findByUserId(userId);
		} catch (NoSuchProfileException e) {}
		return null;
	}
	
}