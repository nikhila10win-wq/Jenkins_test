/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.schema.exception.NoSuchMobileSessionException;
import com.mhdsys.schema.model.MobileSession;
import com.mhdsys.schema.service.base.MobileSessionLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.MobileSession",
	service = AopService.class
)
public class MobileSessionLocalServiceImpl
	extends MobileSessionLocalServiceBaseImpl {
	
	private static final Log LOG = LogFactoryUtil.getLog(MobileSessionLocalServiceImpl.class);
	
	public int countBystatusAndUserId(int status, long userId) {
		return mobileSessionPersistence.countBystatusAndUserId(status, userId);
	}
	
	public MobileSession getBySidAndUserId(long sid, long userId) {
		try {
			return mobileSessionPersistence.findBysidAndUserId(sid, userId);
		} catch (NoSuchMobileSessionException e) {
			LOG.error(e);
		}
		return null;
	}
	
	public List<MobileSession> getBySidAndUserIdByList(long sid, long userId) {
		return mobileSessionPersistence.findBystatusAndUserId(sid, userId);
	}
	
}