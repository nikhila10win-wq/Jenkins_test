/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.CommittedMasterTable;
import com.mhdsys.schema.model.impl.CommittedMasterImpl;
import com.mhdsys.schema.model.impl.CommittedMasterModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from CommittedMaster.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.CommittedMasterImpl",
		"table.name=mhdsys_CommittedMaster"
	},
	service = ArgumentsResolver.class
)
public class CommittedMasterModelArgumentsResolver
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

		CommittedMasterModelImpl committedMasterModelImpl =
			(CommittedMasterModelImpl)baseModel;

		long columnBitmask = committedMasterModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(committedMasterModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					committedMasterModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(committedMasterModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CommittedMasterImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CommittedMasterTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		CommittedMasterModelImpl committedMasterModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = committedMasterModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = committedMasterModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}