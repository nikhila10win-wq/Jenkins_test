/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.mhdsys.schema.model.RegistrationFormTypeTable;
import com.mhdsys.schema.model.impl.RegistrationFormTypeImpl;
import com.mhdsys.schema.model.impl.RegistrationFormTypeModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from RegistrationFormType.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(
	property = {
		"class.name=com.mhdsys.schema.model.impl.RegistrationFormTypeImpl",
		"table.name=mhdsys_RegistrationFormType"
	},
	service = ArgumentsResolver.class
)
public class RegistrationFormTypeModelArgumentsResolver
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

		RegistrationFormTypeModelImpl registrationFormTypeModelImpl =
			(RegistrationFormTypeModelImpl)baseModel;

		long columnBitmask = registrationFormTypeModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				registrationFormTypeModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					registrationFormTypeModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				registrationFormTypeModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return RegistrationFormTypeImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return RegistrationFormTypeTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		RegistrationFormTypeModelImpl registrationFormTypeModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					registrationFormTypeModelImpl.getColumnOriginalValue(
						columnName);
			}
			else {
				arguments[i] = registrationFormTypeModelImpl.getColumnValue(
					columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}