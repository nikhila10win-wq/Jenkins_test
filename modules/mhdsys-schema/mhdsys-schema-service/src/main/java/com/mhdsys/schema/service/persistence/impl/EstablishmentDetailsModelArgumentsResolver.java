/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.EstablishmentDetailsTable;
import com.mhdsys.schema.model.impl.EstablishmentDetailsImpl;
import com.mhdsys.schema.model.impl.EstablishmentDetailsModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from EstablishmentDetails.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.EstablishmentDetailsImpl",
		"table.name=mhdsys_EstablishmentDetails"
	},
	service = ArgumentsResolver.class
)
public class EstablishmentDetailsModelArgumentsResolver
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

		EstablishmentDetailsModelImpl establishmentDetailsModelImpl =
			(EstablishmentDetailsModelImpl)baseModel;

		long columnBitmask = establishmentDetailsModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				establishmentDetailsModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					establishmentDetailsModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				establishmentDetailsModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return EstablishmentDetailsImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return EstablishmentDetailsTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		EstablishmentDetailsModelImpl establishmentDetailsModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					establishmentDetailsModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] = establishmentDetailsModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}