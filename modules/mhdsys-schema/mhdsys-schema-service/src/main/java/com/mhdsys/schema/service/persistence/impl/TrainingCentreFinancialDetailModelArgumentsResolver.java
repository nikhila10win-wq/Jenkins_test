/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.TrainingCentreFinancialDetailTable;
import com.mhdsys.schema.model.impl.TrainingCentreFinancialDetailImpl;
import com.mhdsys.schema.model.impl.TrainingCentreFinancialDetailModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from TrainingCentreFinancialDetail.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.TrainingCentreFinancialDetailImpl",
		"table.name=mhdsys_TrainingCentreFinancialDetail"
	},
	service = ArgumentsResolver.class
)
public class TrainingCentreFinancialDetailModelArgumentsResolver
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

		TrainingCentreFinancialDetailModelImpl
			trainingCentreFinancialDetailModelImpl =
				(TrainingCentreFinancialDetailModelImpl)baseModel;

		long columnBitmask =
			trainingCentreFinancialDetailModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				trainingCentreFinancialDetailModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					trainingCentreFinancialDetailModelImpl.getColumnBitmask(
						columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				trainingCentreFinancialDetailModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return TrainingCentreFinancialDetailImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return TrainingCentreFinancialDetailTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		TrainingCentreFinancialDetailModelImpl
			trainingCentreFinancialDetailModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					trainingCentreFinancialDetailModelImpl.
						getColumnOriginalValue(columnName);
			}
			else {
				arguments[i] =
					trainingCentreFinancialDetailModelImpl.getColumnValue(
						columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}