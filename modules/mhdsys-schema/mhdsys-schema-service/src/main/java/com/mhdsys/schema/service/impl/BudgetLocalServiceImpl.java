/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.impl;

import com.liferay.portal.aop.AopService;
import com.mhdsys.schema.exception.NoSuchBudgetException;
import com.mhdsys.schema.model.Budget;
import com.mhdsys.schema.service.base.BudgetLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = "model.class.name=com.mhdsys.schema.model.Budget", service = AopService.class)
public class BudgetLocalServiceImpl extends BudgetLocalServiceBaseImpl {

	public Budget getByUserId(long userId) {
		try {
			return budgetPersistence.findByUserId(userId);
		} catch (NoSuchBudgetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Budget> getByFinancialYear(String year) {
		return budgetPersistence.findByFinancialYear(year);
	}

	public List<Budget> getByFinancialYearAndCategory(String year, String category) {
		return budgetPersistence.findByFinancialYearAndCategory(year, category);
	}
}