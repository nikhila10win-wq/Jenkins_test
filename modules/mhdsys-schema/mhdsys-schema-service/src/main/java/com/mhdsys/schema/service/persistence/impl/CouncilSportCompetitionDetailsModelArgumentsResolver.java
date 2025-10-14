/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.CouncilSportCompetitionDetailsTable;
import com.mhdsys.schema.model.impl.CouncilSportCompetitionDetailsImpl;
import com.mhdsys.schema.model.impl.CouncilSportCompetitionDetailsModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from CouncilSportCompetitionDetails.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.CouncilSportCompetitionDetailsImpl",
		"table.name=mhdsys_CouncilSportCompetitionDetails"
	},
	service = ArgumentsResolver.class
)
public class CouncilSportCompetitionDetailsModelArgumentsResolver
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

		CouncilSportCompetitionDetailsModelImpl
			councilSportCompetitionDetailsModelImpl =
				(CouncilSportCompetitionDetailsModelImpl)baseModel;

		long columnBitmask =
			councilSportCompetitionDetailsModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				councilSportCompetitionDetailsModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					councilSportCompetitionDetailsModelImpl.getColumnBitmask(
						columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				councilSportCompetitionDetailsModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CouncilSportCompetitionDetailsImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CouncilSportCompetitionDetailsTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		CouncilSportCompetitionDetailsModelImpl
			councilSportCompetitionDetailsModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					councilSportCompetitionDetailsModelImpl.
						getColumnOriginalValue(columnName);
			}
			else {
				arguments[i] =
					councilSportCompetitionDetailsModelImpl.getColumnValue(
						columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}