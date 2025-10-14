/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.SubCategoryMasterTable;
import com.mhdsys.schema.model.impl.SubCategoryMasterImpl;
import com.mhdsys.schema.model.impl.SubCategoryMasterModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from SubCategoryMaster.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.SubCategoryMasterImpl",
		"table.name=mhdsys_SubCategoryMaster"
	},
	service = ArgumentsResolver.class
)
public class SubCategoryMasterModelArgumentsResolver
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

		SubCategoryMasterModelImpl subCategoryMasterModelImpl =
			(SubCategoryMasterModelImpl)baseModel;

		long columnBitmask = subCategoryMasterModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(subCategoryMasterModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					subCategoryMasterModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(subCategoryMasterModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return SubCategoryMasterImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return SubCategoryMasterTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		SubCategoryMasterModelImpl subCategoryMasterModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					subCategoryMasterModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] = subCategoryMasterModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}