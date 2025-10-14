/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.TrainingCentreReportTable;
import com.mhdsys.schema.model.impl.TrainingCentreReportImpl;
import com.mhdsys.schema.model.impl.TrainingCentreReportModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from TrainingCentreReport.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.TrainingCentreReportImpl",
		"table.name=mhdsys_TrainingCentreReport"
	},
	service = ArgumentsResolver.class
)
public class TrainingCentreReportModelArgumentsResolver
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

		TrainingCentreReportModelImpl trainingCentreReportModelImpl =
			(TrainingCentreReportModelImpl)baseModel;

		long columnBitmask = trainingCentreReportModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				trainingCentreReportModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					trainingCentreReportModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				trainingCentreReportModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return TrainingCentreReportImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return TrainingCentreReportTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		TrainingCentreReportModelImpl trainingCentreReportModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					trainingCentreReportModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] = trainingCentreReportModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}