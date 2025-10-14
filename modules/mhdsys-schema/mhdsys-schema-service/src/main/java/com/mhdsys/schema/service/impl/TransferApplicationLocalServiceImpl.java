/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.exception.NoSuchTransferApplicationException;
import com.mhdsys.schema.model.TransferApplication;
import com.mhdsys.schema.service.base.TransferApplicationLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.mhdsys.schema.model.TransferApplication",
	service = AopService.class
)
public class TransferApplicationLocalServiceImpl
	extends TransferApplicationLocalServiceBaseImpl {
	public List<TransferApplication> findByTransfer(String transfer){
		return transferApplicationPersistence.findByTransfer(transfer);
	}
	public TransferApplication findByGrievanceId(long grievanceId){
		try {
			return transferApplicationPersistence.findByGrievanceId(grievanceId);
		} catch (NoSuchTransferApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}