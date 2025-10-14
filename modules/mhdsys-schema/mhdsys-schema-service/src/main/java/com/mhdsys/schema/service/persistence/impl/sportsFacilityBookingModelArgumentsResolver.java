/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.impl.sportsFacilityBookingImpl;
import com.mhdsys.schema.model.impl.sportsFacilityBookingModelImpl;
import com.mhdsys.schema.model.sportsFacilityBookingTable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from sportsFacilityBooking.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.sportsFacilityBookingImpl",
		"table.name=mhdsys_sportsFacilityBooking"
	},
	service = ArgumentsResolver.class
)
public class sportsFacilityBookingModelArgumentsResolver
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

		sportsFacilityBookingModelImpl sportsFacilityBookingModelImpl =
			(sportsFacilityBookingModelImpl)baseModel;

		long columnBitmask = sportsFacilityBookingModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				sportsFacilityBookingModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					sportsFacilityBookingModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				sportsFacilityBookingModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return sportsFacilityBookingImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return sportsFacilityBookingTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		sportsFacilityBookingModelImpl sportsFacilityBookingModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					sportsFacilityBookingModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] = sportsFacilityBookingModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}