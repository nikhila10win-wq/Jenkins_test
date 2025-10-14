/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.CompetitionWorkflowStatusTable;
import com.mhdsys.schema.model.impl.CompetitionWorkflowStatusImpl;
import com.mhdsys.schema.model.impl.CompetitionWorkflowStatusModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from CompetitionWorkflowStatus.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.CompetitionWorkflowStatusImpl",
		"table.name=mhdsys_CompetitionWorkflowStatus"
	},
	service = ArgumentsResolver.class
)
public class CompetitionWorkflowStatusModelArgumentsResolver
	implements ArgumentsResolver {

	@Override
	public Object[] getArguments(
		FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
		boolean original) {

		String[] columnNames = finderPath.getColumnNames();

		if ((columnNames == null) || (columnNames.length == 0)) {
			if (baseModel.isNew()) {
				return new Object[0];
			}

			return null;
		}

		CompetitionWorkflowStatusModelImpl competitionWorkflowStatusModelImpl =
			(CompetitionWorkflowStatusModelImpl)baseModel;

		long columnBitmask =
			competitionWorkflowStatusModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				competitionWorkflowStatusModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					competitionWorkflowStatusModelImpl.getColumnBitmask(
						columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				competitionWorkflowStatusModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CompetitionWorkflowStatusImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CompetitionWorkflowStatusTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		CompetitionWorkflowStatusModelImpl competitionWorkflowStatusModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					competitionWorkflowStatusModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] =
					competitionWorkflowStatusModelImpl.getColumnValue(
						columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}