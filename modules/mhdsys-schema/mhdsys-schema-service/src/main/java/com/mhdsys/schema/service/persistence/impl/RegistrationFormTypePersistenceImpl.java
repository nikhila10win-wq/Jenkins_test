/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.mhdsys.schema.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.mhdsys.schema.exception.NoSuchRegistrationFormTypeException;
import com.mhdsys.schema.model.RegistrationFormType;
import com.mhdsys.schema.model.RegistrationFormTypeTable;
import com.mhdsys.schema.model.impl.RegistrationFormTypeImpl;
import com.mhdsys.schema.model.impl.RegistrationFormTypeModelImpl;
import com.mhdsys.schema.service.persistence.RegistrationFormTypePersistence;
import com.mhdsys.schema.service.persistence.RegistrationFormTypeUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the registration form type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = RegistrationFormTypePersistence.class)
public class RegistrationFormTypePersistenceImpl
	extends BasePersistenceImpl<RegistrationFormType>
	implements RegistrationFormTypePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RegistrationFormTypeUtil</code> to access the registration form type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RegistrationFormTypeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the registration form types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching registration form types
	 */
	@Override
	public List<RegistrationFormType> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration form types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationFormTypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration form types
	 * @param end the upper bound of the range of registration form types (not inclusive)
	 * @return the range of matching registration form types
	 */
	@Override
	public List<RegistrationFormType> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration form types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationFormTypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration form types
	 * @param end the upper bound of the range of registration form types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching registration form types
	 */
	@Override
	public List<RegistrationFormType> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RegistrationFormType> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration form types where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationFormTypeModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of registration form types
	 * @param end the upper bound of the range of registration form types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching registration form types
	 */
	@Override
	public List<RegistrationFormType> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RegistrationFormType> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<RegistrationFormType> list = null;

		if (useFinderCache) {
			list = (List<RegistrationFormType>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RegistrationFormType registrationFormType : list) {
					if (!uuid.equals(registrationFormType.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_REGISTRATIONFORMTYPE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RegistrationFormTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<RegistrationFormType>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first registration form type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form type
	 * @throws NoSuchRegistrationFormTypeException if a matching registration form type could not be found
	 */
	@Override
	public RegistrationFormType findByUuid_First(
			String uuid,
			OrderByComparator<RegistrationFormType> orderByComparator)
		throws NoSuchRegistrationFormTypeException {

		RegistrationFormType registrationFormType = fetchByUuid_First(
			uuid, orderByComparator);

		if (registrationFormType != null) {
			return registrationFormType;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchRegistrationFormTypeException(sb.toString());
	}

	/**
	 * Returns the first registration form type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching registration form type, or <code>null</code> if a matching registration form type could not be found
	 */
	@Override
	public RegistrationFormType fetchByUuid_First(
		String uuid,
		OrderByComparator<RegistrationFormType> orderByComparator) {

		List<RegistrationFormType> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last registration form type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form type
	 * @throws NoSuchRegistrationFormTypeException if a matching registration form type could not be found
	 */
	@Override
	public RegistrationFormType findByUuid_Last(
			String uuid,
			OrderByComparator<RegistrationFormType> orderByComparator)
		throws NoSuchRegistrationFormTypeException {

		RegistrationFormType registrationFormType = fetchByUuid_Last(
			uuid, orderByComparator);

		if (registrationFormType != null) {
			return registrationFormType;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchRegistrationFormTypeException(sb.toString());
	}

	/**
	 * Returns the last registration form type in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching registration form type, or <code>null</code> if a matching registration form type could not be found
	 */
	@Override
	public RegistrationFormType fetchByUuid_Last(
		String uuid,
		OrderByComparator<RegistrationFormType> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<RegistrationFormType> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the registration form types before and after the current registration form type in the ordered set where uuid = &#63;.
	 *
	 * @param typeId the primary key of the current registration form type
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next registration form type
	 * @throws NoSuchRegistrationFormTypeException if a registration form type with the primary key could not be found
	 */
	@Override
	public RegistrationFormType[] findByUuid_PrevAndNext(
			long typeId, String uuid,
			OrderByComparator<RegistrationFormType> orderByComparator)
		throws NoSuchRegistrationFormTypeException {

		uuid = Objects.toString(uuid, "");

		RegistrationFormType registrationFormType = findByPrimaryKey(typeId);

		Session session = null;

		try {
			session = openSession();

			RegistrationFormType[] array = new RegistrationFormTypeImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, registrationFormType, uuid, orderByComparator, true);

			array[1] = registrationFormType;

			array[2] = getByUuid_PrevAndNext(
				session, registrationFormType, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RegistrationFormType getByUuid_PrevAndNext(
		Session session, RegistrationFormType registrationFormType, String uuid,
		OrderByComparator<RegistrationFormType> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_REGISTRATIONFORMTYPE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(RegistrationFormTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						registrationFormType)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RegistrationFormType> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the registration form types where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (RegistrationFormType registrationFormType :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(registrationFormType);
		}
	}

	/**
	 * Returns the number of registration form types where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching registration form types
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_REGISTRATIONFORMTYPE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"registrationFormType.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(registrationFormType.uuid IS NULL OR registrationFormType.uuid = '')";

	private FinderPath _finderPathFetchByFormTypeName;
	private FinderPath _finderPathCountByFormTypeName;

	/**
	 * Returns the registration form type where formName = &#63; or throws a <code>NoSuchRegistrationFormTypeException</code> if it could not be found.
	 *
	 * @param formName the form name
	 * @return the matching registration form type
	 * @throws NoSuchRegistrationFormTypeException if a matching registration form type could not be found
	 */
	@Override
	public RegistrationFormType findByFormTypeName(String formName)
		throws NoSuchRegistrationFormTypeException {

		RegistrationFormType registrationFormType = fetchByFormTypeName(
			formName);

		if (registrationFormType == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("formName=");
			sb.append(formName);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRegistrationFormTypeException(sb.toString());
		}

		return registrationFormType;
	}

	/**
	 * Returns the registration form type where formName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formName the form name
	 * @return the matching registration form type, or <code>null</code> if a matching registration form type could not be found
	 */
	@Override
	public RegistrationFormType fetchByFormTypeName(String formName) {
		return fetchByFormTypeName(formName, true);
	}

	/**
	 * Returns the registration form type where formName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formName the form name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching registration form type, or <code>null</code> if a matching registration form type could not be found
	 */
	@Override
	public RegistrationFormType fetchByFormTypeName(
		String formName, boolean useFinderCache) {

		formName = Objects.toString(formName, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {formName};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByFormTypeName, finderArgs, this);
		}

		if (result instanceof RegistrationFormType) {
			RegistrationFormType registrationFormType =
				(RegistrationFormType)result;

			if (!Objects.equals(formName, registrationFormType.getFormName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_REGISTRATIONFORMTYPE_WHERE);

			boolean bindFormName = false;

			if (formName.isEmpty()) {
				sb.append(_FINDER_COLUMN_FORMTYPENAME_FORMNAME_3);
			}
			else {
				bindFormName = true;

				sb.append(_FINDER_COLUMN_FORMTYPENAME_FORMNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFormName) {
					queryPos.add(formName);
				}

				List<RegistrationFormType> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByFormTypeName, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {formName};
							}

							_log.warn(
								"RegistrationFormTypePersistenceImpl.fetchByFormTypeName(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					RegistrationFormType registrationFormType = list.get(0);

					result = registrationFormType;

					cacheResult(registrationFormType);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (RegistrationFormType)result;
		}
	}

	/**
	 * Removes the registration form type where formName = &#63; from the database.
	 *
	 * @param formName the form name
	 * @return the registration form type that was removed
	 */
	@Override
	public RegistrationFormType removeByFormTypeName(String formName)
		throws NoSuchRegistrationFormTypeException {

		RegistrationFormType registrationFormType = findByFormTypeName(
			formName);

		return remove(registrationFormType);
	}

	/**
	 * Returns the number of registration form types where formName = &#63;.
	 *
	 * @param formName the form name
	 * @return the number of matching registration form types
	 */
	@Override
	public int countByFormTypeName(String formName) {
		formName = Objects.toString(formName, "");

		FinderPath finderPath = _finderPathCountByFormTypeName;

		Object[] finderArgs = new Object[] {formName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_REGISTRATIONFORMTYPE_WHERE);

			boolean bindFormName = false;

			if (formName.isEmpty()) {
				sb.append(_FINDER_COLUMN_FORMTYPENAME_FORMNAME_3);
			}
			else {
				bindFormName = true;

				sb.append(_FINDER_COLUMN_FORMTYPENAME_FORMNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFormName) {
					queryPos.add(formName);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FORMTYPENAME_FORMNAME_2 =
		"registrationFormType.formName = ?";

	private static final String _FINDER_COLUMN_FORMTYPENAME_FORMNAME_3 =
		"(registrationFormType.formName IS NULL OR registrationFormType.formName = '')";

	public RegistrationFormTypePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(RegistrationFormType.class);

		setModelImplClass(RegistrationFormTypeImpl.class);
		setModelPKClass(long.class);

		setTable(RegistrationFormTypeTable.INSTANCE);
	}

	/**
	 * Caches the registration form type in the entity cache if it is enabled.
	 *
	 * @param registrationFormType the registration form type
	 */
	@Override
	public void cacheResult(RegistrationFormType registrationFormType) {
		entityCache.putResult(
			RegistrationFormTypeImpl.class,
			registrationFormType.getPrimaryKey(), registrationFormType);

		finderCache.putResult(
			_finderPathFetchByFormTypeName,
			new Object[] {registrationFormType.getFormName()},
			registrationFormType);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the registration form types in the entity cache if it is enabled.
	 *
	 * @param registrationFormTypes the registration form types
	 */
	@Override
	public void cacheResult(List<RegistrationFormType> registrationFormTypes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (registrationFormTypes.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (RegistrationFormType registrationFormType :
				registrationFormTypes) {

			if (entityCache.getResult(
					RegistrationFormTypeImpl.class,
					registrationFormType.getPrimaryKey()) == null) {

				cacheResult(registrationFormType);
			}
		}
	}

	/**
	 * Clears the cache for all registration form types.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RegistrationFormTypeImpl.class);

		finderCache.clearCache(RegistrationFormTypeImpl.class);
	}

	/**
	 * Clears the cache for the registration form type.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RegistrationFormType registrationFormType) {
		entityCache.removeResult(
			RegistrationFormTypeImpl.class, registrationFormType);
	}

	@Override
	public void clearCache(List<RegistrationFormType> registrationFormTypes) {
		for (RegistrationFormType registrationFormType :
				registrationFormTypes) {

			entityCache.removeResult(
				RegistrationFormTypeImpl.class, registrationFormType);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(RegistrationFormTypeImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				RegistrationFormTypeImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		RegistrationFormTypeModelImpl registrationFormTypeModelImpl) {

		Object[] args = new Object[] {
			registrationFormTypeModelImpl.getFormName()
		};

		finderCache.putResult(
			_finderPathCountByFormTypeName, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByFormTypeName, args,
			registrationFormTypeModelImpl);
	}

	/**
	 * Creates a new registration form type with the primary key. Does not add the registration form type to the database.
	 *
	 * @param typeId the primary key for the new registration form type
	 * @return the new registration form type
	 */
	@Override
	public RegistrationFormType create(long typeId) {
		RegistrationFormType registrationFormType =
			new RegistrationFormTypeImpl();

		registrationFormType.setNew(true);
		registrationFormType.setPrimaryKey(typeId);

		String uuid = PortalUUIDUtil.generate();

		registrationFormType.setUuid(uuid);

		return registrationFormType;
	}

	/**
	 * Removes the registration form type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param typeId the primary key of the registration form type
	 * @return the registration form type that was removed
	 * @throws NoSuchRegistrationFormTypeException if a registration form type with the primary key could not be found
	 */
	@Override
	public RegistrationFormType remove(long typeId)
		throws NoSuchRegistrationFormTypeException {

		return remove((Serializable)typeId);
	}

	/**
	 * Removes the registration form type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the registration form type
	 * @return the registration form type that was removed
	 * @throws NoSuchRegistrationFormTypeException if a registration form type with the primary key could not be found
	 */
	@Override
	public RegistrationFormType remove(Serializable primaryKey)
		throws NoSuchRegistrationFormTypeException {

		Session session = null;

		try {
			session = openSession();

			RegistrationFormType registrationFormType =
				(RegistrationFormType)session.get(
					RegistrationFormTypeImpl.class, primaryKey);

			if (registrationFormType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRegistrationFormTypeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(registrationFormType);
		}
		catch (NoSuchRegistrationFormTypeException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected RegistrationFormType removeImpl(
		RegistrationFormType registrationFormType) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(registrationFormType)) {
				registrationFormType = (RegistrationFormType)session.get(
					RegistrationFormTypeImpl.class,
					registrationFormType.getPrimaryKeyObj());
			}

			if (registrationFormType != null) {
				session.delete(registrationFormType);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (registrationFormType != null) {
			clearCache(registrationFormType);
		}

		return registrationFormType;
	}

	@Override
	public RegistrationFormType updateImpl(
		RegistrationFormType registrationFormType) {

		boolean isNew = registrationFormType.isNew();

		if (!(registrationFormType instanceof RegistrationFormTypeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(registrationFormType.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					registrationFormType);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in registrationFormType proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RegistrationFormType implementation " +
					registrationFormType.getClass());
		}

		RegistrationFormTypeModelImpl registrationFormTypeModelImpl =
			(RegistrationFormTypeModelImpl)registrationFormType;

		if (Validator.isNull(registrationFormType.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			registrationFormType.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(registrationFormType);
			}
			else {
				registrationFormType = (RegistrationFormType)session.merge(
					registrationFormType);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			RegistrationFormTypeImpl.class, registrationFormTypeModelImpl,
			false, true);

		cacheUniqueFindersCache(registrationFormTypeModelImpl);

		if (isNew) {
			registrationFormType.setNew(false);
		}

		registrationFormType.resetOriginalValues();

		return registrationFormType;
	}

	/**
	 * Returns the registration form type with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the registration form type
	 * @return the registration form type
	 * @throws NoSuchRegistrationFormTypeException if a registration form type with the primary key could not be found
	 */
	@Override
	public RegistrationFormType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRegistrationFormTypeException {

		RegistrationFormType registrationFormType = fetchByPrimaryKey(
			primaryKey);

		if (registrationFormType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRegistrationFormTypeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return registrationFormType;
	}

	/**
	 * Returns the registration form type with the primary key or throws a <code>NoSuchRegistrationFormTypeException</code> if it could not be found.
	 *
	 * @param typeId the primary key of the registration form type
	 * @return the registration form type
	 * @throws NoSuchRegistrationFormTypeException if a registration form type with the primary key could not be found
	 */
	@Override
	public RegistrationFormType findByPrimaryKey(long typeId)
		throws NoSuchRegistrationFormTypeException {

		return findByPrimaryKey((Serializable)typeId);
	}

	/**
	 * Returns the registration form type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param typeId the primary key of the registration form type
	 * @return the registration form type, or <code>null</code> if a registration form type with the primary key could not be found
	 */
	@Override
	public RegistrationFormType fetchByPrimaryKey(long typeId) {
		return fetchByPrimaryKey((Serializable)typeId);
	}

	/**
	 * Returns all the registration form types.
	 *
	 * @return the registration form types
	 */
	@Override
	public List<RegistrationFormType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the registration form types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationFormTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration form types
	 * @param end the upper bound of the range of registration form types (not inclusive)
	 * @return the range of registration form types
	 */
	@Override
	public List<RegistrationFormType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the registration form types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationFormTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration form types
	 * @param end the upper bound of the range of registration form types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of registration form types
	 */
	@Override
	public List<RegistrationFormType> findAll(
		int start, int end,
		OrderByComparator<RegistrationFormType> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the registration form types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RegistrationFormTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of registration form types
	 * @param end the upper bound of the range of registration form types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of registration form types
	 */
	@Override
	public List<RegistrationFormType> findAll(
		int start, int end,
		OrderByComparator<RegistrationFormType> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<RegistrationFormType> list = null;

		if (useFinderCache) {
			list = (List<RegistrationFormType>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_REGISTRATIONFORMTYPE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_REGISTRATIONFORMTYPE;

				sql = sql.concat(RegistrationFormTypeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<RegistrationFormType>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the registration form types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RegistrationFormType registrationFormType : findAll()) {
			remove(registrationFormType);
		}
	}

	/**
	 * Returns the number of registration form types.
	 *
	 * @return the number of registration form types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_REGISTRATIONFORMTYPE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "typeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REGISTRATIONFORMTYPE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RegistrationFormTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the registration form type persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByFormTypeName = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByFormTypeName",
			new String[] {String.class.getName()}, new String[] {"formName"},
			true);

		_finderPathCountByFormTypeName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFormTypeName",
			new String[] {String.class.getName()}, new String[] {"formName"},
			false);

		RegistrationFormTypeUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		RegistrationFormTypeUtil.setPersistence(null);

		entityCache.removeCache(RegistrationFormTypeImpl.class.getName());
	}

	@Override
	@Reference(
		target = mhdsysPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = mhdsysPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = mhdsysPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_REGISTRATIONFORMTYPE =
		"SELECT registrationFormType FROM RegistrationFormType registrationFormType";

	private static final String _SQL_SELECT_REGISTRATIONFORMTYPE_WHERE =
		"SELECT registrationFormType FROM RegistrationFormType registrationFormType WHERE ";

	private static final String _SQL_COUNT_REGISTRATIONFORMTYPE =
		"SELECT COUNT(registrationFormType) FROM RegistrationFormType registrationFormType";

	private static final String _SQL_COUNT_REGISTRATIONFORMTYPE_WHERE =
		"SELECT COUNT(registrationFormType) FROM RegistrationFormType registrationFormType WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"registrationFormType.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No RegistrationFormType exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No RegistrationFormType exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RegistrationFormTypePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}