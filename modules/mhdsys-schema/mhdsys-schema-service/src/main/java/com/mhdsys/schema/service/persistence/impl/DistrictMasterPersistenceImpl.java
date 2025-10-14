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
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchDistrictMasterException;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.DistrictMasterTable;
import com.mhdsys.schema.model.impl.DistrictMasterImpl;
import com.mhdsys.schema.model.impl.DistrictMasterModelImpl;
import com.mhdsys.schema.service.persistence.DistrictMasterPersistence;
import com.mhdsys.schema.service.persistence.DistrictMasterUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
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
 * The persistence implementation for the district master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DistrictMasterPersistence.class)
public class DistrictMasterPersistenceImpl
	extends BasePersistenceImpl<DistrictMaster>
	implements DistrictMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DistrictMasterUtil</code> to access the district master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DistrictMasterImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByActiveState;
	private FinderPath _finderPathWithoutPaginationFindByActiveState;
	private FinderPath _finderPathCountByActiveState;

	/**
	 * Returns all the district masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching district masters
	 */
	@Override
	public List<DistrictMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the district masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @return the range of matching district masters
	 */
	@Override
	public List<DistrictMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the district masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching district masters
	 */
	@Override
	public List<DistrictMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<DistrictMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the district masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching district masters
	 */
	@Override
	public List<DistrictMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<DistrictMaster> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByActiveState;
				finderArgs = new Object[] {isActive};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByActiveState;
			finderArgs = new Object[] {isActive, start, end, orderByComparator};
		}

		List<DistrictMaster> list = null;

		if (useFinderCache) {
			list = (List<DistrictMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DistrictMaster districtMaster : list) {
					if (isActive != districtMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_DISTRICTMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DistrictMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<DistrictMaster>)QueryUtil.list(
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
	 * Returns the first district master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching district master
	 * @throws NoSuchDistrictMasterException if a matching district master could not be found
	 */
	@Override
	public DistrictMaster findByActiveState_First(
			boolean isActive,
			OrderByComparator<DistrictMaster> orderByComparator)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (districtMaster != null) {
			return districtMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchDistrictMasterException(sb.toString());
	}

	/**
	 * Returns the first district master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching district master, or <code>null</code> if a matching district master could not be found
	 */
	@Override
	public DistrictMaster fetchByActiveState_First(
		boolean isActive, OrderByComparator<DistrictMaster> orderByComparator) {

		List<DistrictMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last district master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching district master
	 * @throws NoSuchDistrictMasterException if a matching district master could not be found
	 */
	@Override
	public DistrictMaster findByActiveState_Last(
			boolean isActive,
			OrderByComparator<DistrictMaster> orderByComparator)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (districtMaster != null) {
			return districtMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchDistrictMasterException(sb.toString());
	}

	/**
	 * Returns the last district master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching district master, or <code>null</code> if a matching district master could not be found
	 */
	@Override
	public DistrictMaster fetchByActiveState_Last(
		boolean isActive, OrderByComparator<DistrictMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<DistrictMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the district masters before and after the current district master in the ordered set where isActive = &#63;.
	 *
	 * @param districtId the primary key of the current district master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next district master
	 * @throws NoSuchDistrictMasterException if a district master with the primary key could not be found
	 */
	@Override
	public DistrictMaster[] findByActiveState_PrevAndNext(
			long districtId, boolean isActive,
			OrderByComparator<DistrictMaster> orderByComparator)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = findByPrimaryKey(districtId);

		Session session = null;

		try {
			session = openSession();

			DistrictMaster[] array = new DistrictMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, districtMaster, isActive, orderByComparator, true);

			array[1] = districtMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, districtMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DistrictMaster getByActiveState_PrevAndNext(
		Session session, DistrictMaster districtMaster, boolean isActive,
		OrderByComparator<DistrictMaster> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DISTRICTMASTER_WHERE);

		sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

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
			sb.append(DistrictMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						districtMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DistrictMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the district masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (DistrictMaster districtMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(districtMaster);
		}
	}

	/**
	 * Returns the number of district masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching district masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DISTRICTMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

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

	private static final String _FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2 =
		"districtMaster.isActive = ?";

	private FinderPath _finderPathWithPaginationFindByDivisionId;
	private FinderPath _finderPathWithoutPaginationFindByDivisionId;
	private FinderPath _finderPathCountByDivisionId;

	/**
	 * Returns all the district masters where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @return the matching district masters
	 */
	@Override
	public List<DistrictMaster> findByDivisionId(long divisionId) {
		return findByDivisionId(
			divisionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the district masters where divisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param divisionId the division ID
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @return the range of matching district masters
	 */
	@Override
	public List<DistrictMaster> findByDivisionId(
		long divisionId, int start, int end) {

		return findByDivisionId(divisionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the district masters where divisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param divisionId the division ID
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching district masters
	 */
	@Override
	public List<DistrictMaster> findByDivisionId(
		long divisionId, int start, int end,
		OrderByComparator<DistrictMaster> orderByComparator) {

		return findByDivisionId(
			divisionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the district masters where divisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param divisionId the division ID
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching district masters
	 */
	@Override
	public List<DistrictMaster> findByDivisionId(
		long divisionId, int start, int end,
		OrderByComparator<DistrictMaster> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByDivisionId;
				finderArgs = new Object[] {divisionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByDivisionId;
			finderArgs = new Object[] {
				divisionId, start, end, orderByComparator
			};
		}

		List<DistrictMaster> list = null;

		if (useFinderCache) {
			list = (List<DistrictMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DistrictMaster districtMaster : list) {
					if (divisionId != districtMaster.getDivisionId()) {
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

			sb.append(_SQL_SELECT_DISTRICTMASTER_WHERE);

			sb.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(DistrictMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(divisionId);

				list = (List<DistrictMaster>)QueryUtil.list(
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
	 * Returns the first district master in the ordered set where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching district master
	 * @throws NoSuchDistrictMasterException if a matching district master could not be found
	 */
	@Override
	public DistrictMaster findByDivisionId_First(
			long divisionId,
			OrderByComparator<DistrictMaster> orderByComparator)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = fetchByDivisionId_First(
			divisionId, orderByComparator);

		if (districtMaster != null) {
			return districtMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("divisionId=");
		sb.append(divisionId);

		sb.append("}");

		throw new NoSuchDistrictMasterException(sb.toString());
	}

	/**
	 * Returns the first district master in the ordered set where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching district master, or <code>null</code> if a matching district master could not be found
	 */
	@Override
	public DistrictMaster fetchByDivisionId_First(
		long divisionId, OrderByComparator<DistrictMaster> orderByComparator) {

		List<DistrictMaster> list = findByDivisionId(
			divisionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last district master in the ordered set where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching district master
	 * @throws NoSuchDistrictMasterException if a matching district master could not be found
	 */
	@Override
	public DistrictMaster findByDivisionId_Last(
			long divisionId,
			OrderByComparator<DistrictMaster> orderByComparator)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = fetchByDivisionId_Last(
			divisionId, orderByComparator);

		if (districtMaster != null) {
			return districtMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("divisionId=");
		sb.append(divisionId);

		sb.append("}");

		throw new NoSuchDistrictMasterException(sb.toString());
	}

	/**
	 * Returns the last district master in the ordered set where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching district master, or <code>null</code> if a matching district master could not be found
	 */
	@Override
	public DistrictMaster fetchByDivisionId_Last(
		long divisionId, OrderByComparator<DistrictMaster> orderByComparator) {

		int count = countByDivisionId(divisionId);

		if (count == 0) {
			return null;
		}

		List<DistrictMaster> list = findByDivisionId(
			divisionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the district masters before and after the current district master in the ordered set where divisionId = &#63;.
	 *
	 * @param districtId the primary key of the current district master
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next district master
	 * @throws NoSuchDistrictMasterException if a district master with the primary key could not be found
	 */
	@Override
	public DistrictMaster[] findByDivisionId_PrevAndNext(
			long districtId, long divisionId,
			OrderByComparator<DistrictMaster> orderByComparator)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = findByPrimaryKey(districtId);

		Session session = null;

		try {
			session = openSession();

			DistrictMaster[] array = new DistrictMasterImpl[3];

			array[0] = getByDivisionId_PrevAndNext(
				session, districtMaster, divisionId, orderByComparator, true);

			array[1] = districtMaster;

			array[2] = getByDivisionId_PrevAndNext(
				session, districtMaster, divisionId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DistrictMaster getByDivisionId_PrevAndNext(
		Session session, DistrictMaster districtMaster, long divisionId,
		OrderByComparator<DistrictMaster> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_DISTRICTMASTER_WHERE);

		sb.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);

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
			sb.append(DistrictMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(divisionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						districtMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<DistrictMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the district masters where divisionId = &#63; from the database.
	 *
	 * @param divisionId the division ID
	 */
	@Override
	public void removeByDivisionId(long divisionId) {
		for (DistrictMaster districtMaster :
				findByDivisionId(
					divisionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(districtMaster);
		}
	}

	/**
	 * Returns the number of district masters where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @return the number of matching district masters
	 */
	@Override
	public int countByDivisionId(long divisionId) {
		FinderPath finderPath = _finderPathCountByDivisionId;

		Object[] finderArgs = new Object[] {divisionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DISTRICTMASTER_WHERE);

			sb.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(divisionId);

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

	private static final String _FINDER_COLUMN_DIVISIONID_DIVISIONID_2 =
		"districtMaster.divisionId = ?";

	private FinderPath _finderPathFetchByname;
	private FinderPath _finderPathCountByname;

	/**
	 * Returns the district master where districtName_en = &#63; or throws a <code>NoSuchDistrictMasterException</code> if it could not be found.
	 *
	 * @param districtName_en the district name_en
	 * @return the matching district master
	 * @throws NoSuchDistrictMasterException if a matching district master could not be found
	 */
	@Override
	public DistrictMaster findByname(String districtName_en)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = fetchByname(districtName_en);

		if (districtMaster == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("districtName_en=");
			sb.append(districtName_en);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchDistrictMasterException(sb.toString());
		}

		return districtMaster;
	}

	/**
	 * Returns the district master where districtName_en = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param districtName_en the district name_en
	 * @return the matching district master, or <code>null</code> if a matching district master could not be found
	 */
	@Override
	public DistrictMaster fetchByname(String districtName_en) {
		return fetchByname(districtName_en, true);
	}

	/**
	 * Returns the district master where districtName_en = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param districtName_en the district name_en
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching district master, or <code>null</code> if a matching district master could not be found
	 */
	@Override
	public DistrictMaster fetchByname(
		String districtName_en, boolean useFinderCache) {

		districtName_en = Objects.toString(districtName_en, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {districtName_en};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByname, finderArgs, this);
		}

		if (result instanceof DistrictMaster) {
			DistrictMaster districtMaster = (DistrictMaster)result;

			if (!Objects.equals(
					districtName_en, districtMaster.getDistrictName_en())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_DISTRICTMASTER_WHERE);

			boolean bindDistrictName_en = false;

			if (districtName_en.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_DISTRICTNAME_EN_3);
			}
			else {
				bindDistrictName_en = true;

				sb.append(_FINDER_COLUMN_NAME_DISTRICTNAME_EN_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDistrictName_en) {
					queryPos.add(districtName_en);
				}

				List<DistrictMaster> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByname, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {districtName_en};
							}

							_log.warn(
								"DistrictMasterPersistenceImpl.fetchByname(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DistrictMaster districtMaster = list.get(0);

					result = districtMaster;

					cacheResult(districtMaster);
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
			return (DistrictMaster)result;
		}
	}

	/**
	 * Removes the district master where districtName_en = &#63; from the database.
	 *
	 * @param districtName_en the district name_en
	 * @return the district master that was removed
	 */
	@Override
	public DistrictMaster removeByname(String districtName_en)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = findByname(districtName_en);

		return remove(districtMaster);
	}

	/**
	 * Returns the number of district masters where districtName_en = &#63;.
	 *
	 * @param districtName_en the district name_en
	 * @return the number of matching district masters
	 */
	@Override
	public int countByname(String districtName_en) {
		districtName_en = Objects.toString(districtName_en, "");

		FinderPath finderPath = _finderPathCountByname;

		Object[] finderArgs = new Object[] {districtName_en};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DISTRICTMASTER_WHERE);

			boolean bindDistrictName_en = false;

			if (districtName_en.isEmpty()) {
				sb.append(_FINDER_COLUMN_NAME_DISTRICTNAME_EN_3);
			}
			else {
				bindDistrictName_en = true;

				sb.append(_FINDER_COLUMN_NAME_DISTRICTNAME_EN_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDistrictName_en) {
					queryPos.add(districtName_en);
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

	private static final String _FINDER_COLUMN_NAME_DISTRICTNAME_EN_2 =
		"districtMaster.districtName_en = ?";

	private static final String _FINDER_COLUMN_NAME_DISTRICTNAME_EN_3 =
		"(districtMaster.districtName_en IS NULL OR districtMaster.districtName_en = '')";

	public DistrictMasterPersistenceImpl() {
		setModelClass(DistrictMaster.class);

		setModelImplClass(DistrictMasterImpl.class);
		setModelPKClass(long.class);

		setTable(DistrictMasterTable.INSTANCE);
	}

	/**
	 * Caches the district master in the entity cache if it is enabled.
	 *
	 * @param districtMaster the district master
	 */
	@Override
	public void cacheResult(DistrictMaster districtMaster) {
		entityCache.putResult(
			DistrictMasterImpl.class, districtMaster.getPrimaryKey(),
			districtMaster);

		finderCache.putResult(
			_finderPathFetchByname,
			new Object[] {districtMaster.getDistrictName_en()}, districtMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the district masters in the entity cache if it is enabled.
	 *
	 * @param districtMasters the district masters
	 */
	@Override
	public void cacheResult(List<DistrictMaster> districtMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (districtMasters.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (DistrictMaster districtMaster : districtMasters) {
			if (entityCache.getResult(
					DistrictMasterImpl.class, districtMaster.getPrimaryKey()) ==
						null) {

				cacheResult(districtMaster);
			}
		}
	}

	/**
	 * Clears the cache for all district masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DistrictMasterImpl.class);

		finderCache.clearCache(DistrictMasterImpl.class);
	}

	/**
	 * Clears the cache for the district master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DistrictMaster districtMaster) {
		entityCache.removeResult(DistrictMasterImpl.class, districtMaster);
	}

	@Override
	public void clearCache(List<DistrictMaster> districtMasters) {
		for (DistrictMaster districtMaster : districtMasters) {
			entityCache.removeResult(DistrictMasterImpl.class, districtMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(DistrictMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DistrictMasterImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DistrictMasterModelImpl districtMasterModelImpl) {

		Object[] args = new Object[] {
			districtMasterModelImpl.getDistrictName_en()
		};

		finderCache.putResult(_finderPathCountByname, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByname, args, districtMasterModelImpl);
	}

	/**
	 * Creates a new district master with the primary key. Does not add the district master to the database.
	 *
	 * @param districtId the primary key for the new district master
	 * @return the new district master
	 */
	@Override
	public DistrictMaster create(long districtId) {
		DistrictMaster districtMaster = new DistrictMasterImpl();

		districtMaster.setNew(true);
		districtMaster.setPrimaryKey(districtId);

		return districtMaster;
	}

	/**
	 * Removes the district master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param districtId the primary key of the district master
	 * @return the district master that was removed
	 * @throws NoSuchDistrictMasterException if a district master with the primary key could not be found
	 */
	@Override
	public DistrictMaster remove(long districtId)
		throws NoSuchDistrictMasterException {

		return remove((Serializable)districtId);
	}

	/**
	 * Removes the district master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the district master
	 * @return the district master that was removed
	 * @throws NoSuchDistrictMasterException if a district master with the primary key could not be found
	 */
	@Override
	public DistrictMaster remove(Serializable primaryKey)
		throws NoSuchDistrictMasterException {

		Session session = null;

		try {
			session = openSession();

			DistrictMaster districtMaster = (DistrictMaster)session.get(
				DistrictMasterImpl.class, primaryKey);

			if (districtMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDistrictMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(districtMaster);
		}
		catch (NoSuchDistrictMasterException noSuchEntityException) {
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
	protected DistrictMaster removeImpl(DistrictMaster districtMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(districtMaster)) {
				districtMaster = (DistrictMaster)session.get(
					DistrictMasterImpl.class,
					districtMaster.getPrimaryKeyObj());
			}

			if (districtMaster != null) {
				session.delete(districtMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (districtMaster != null) {
			clearCache(districtMaster);
		}

		return districtMaster;
	}

	@Override
	public DistrictMaster updateImpl(DistrictMaster districtMaster) {
		boolean isNew = districtMaster.isNew();

		if (!(districtMaster instanceof DistrictMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(districtMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					districtMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in districtMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DistrictMaster implementation " +
					districtMaster.getClass());
		}

		DistrictMasterModelImpl districtMasterModelImpl =
			(DistrictMasterModelImpl)districtMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(districtMaster);
			}
			else {
				districtMaster = (DistrictMaster)session.merge(districtMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DistrictMasterImpl.class, districtMasterModelImpl, false, true);

		cacheUniqueFindersCache(districtMasterModelImpl);

		if (isNew) {
			districtMaster.setNew(false);
		}

		districtMaster.resetOriginalValues();

		return districtMaster;
	}

	/**
	 * Returns the district master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the district master
	 * @return the district master
	 * @throws NoSuchDistrictMasterException if a district master with the primary key could not be found
	 */
	@Override
	public DistrictMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDistrictMasterException {

		DistrictMaster districtMaster = fetchByPrimaryKey(primaryKey);

		if (districtMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDistrictMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return districtMaster;
	}

	/**
	 * Returns the district master with the primary key or throws a <code>NoSuchDistrictMasterException</code> if it could not be found.
	 *
	 * @param districtId the primary key of the district master
	 * @return the district master
	 * @throws NoSuchDistrictMasterException if a district master with the primary key could not be found
	 */
	@Override
	public DistrictMaster findByPrimaryKey(long districtId)
		throws NoSuchDistrictMasterException {

		return findByPrimaryKey((Serializable)districtId);
	}

	/**
	 * Returns the district master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param districtId the primary key of the district master
	 * @return the district master, or <code>null</code> if a district master with the primary key could not be found
	 */
	@Override
	public DistrictMaster fetchByPrimaryKey(long districtId) {
		return fetchByPrimaryKey((Serializable)districtId);
	}

	/**
	 * Returns all the district masters.
	 *
	 * @return the district masters
	 */
	@Override
	public List<DistrictMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the district masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @return the range of district masters
	 */
	@Override
	public List<DistrictMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the district masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of district masters
	 */
	@Override
	public List<DistrictMaster> findAll(
		int start, int end,
		OrderByComparator<DistrictMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the district masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DistrictMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of district masters
	 * @param end the upper bound of the range of district masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of district masters
	 */
	@Override
	public List<DistrictMaster> findAll(
		int start, int end, OrderByComparator<DistrictMaster> orderByComparator,
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

		List<DistrictMaster> list = null;

		if (useFinderCache) {
			list = (List<DistrictMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DISTRICTMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DISTRICTMASTER;

				sql = sql.concat(DistrictMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DistrictMaster>)QueryUtil.list(
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
	 * Removes all the district masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DistrictMaster districtMaster : findAll()) {
			remove(districtMaster);
		}
	}

	/**
	 * Returns the number of district masters.
	 *
	 * @return the number of district masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_DISTRICTMASTER);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "districtId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DISTRICTMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DistrictMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the district master persistence.
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

		_finderPathWithPaginationFindByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveState",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"isActive"}, true);

		_finderPathWithoutPaginationFindByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveState",
			new String[] {Boolean.class.getName()}, new String[] {"isActive"},
			true);

		_finderPathCountByActiveState = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveState",
			new String[] {Boolean.class.getName()}, new String[] {"isActive"},
			false);

		_finderPathWithPaginationFindByDivisionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDivisionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"divisionId"}, true);

		_finderPathWithoutPaginationFindByDivisionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDivisionId",
			new String[] {Long.class.getName()}, new String[] {"divisionId"},
			true);

		_finderPathCountByDivisionId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDivisionId",
			new String[] {Long.class.getName()}, new String[] {"divisionId"},
			false);

		_finderPathFetchByname = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByname",
			new String[] {String.class.getName()},
			new String[] {"districtName_en"}, true);

		_finderPathCountByname = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByname",
			new String[] {String.class.getName()},
			new String[] {"districtName_en"}, false);

		DistrictMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		DistrictMasterUtil.setPersistence(null);

		entityCache.removeCache(DistrictMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_DISTRICTMASTER =
		"SELECT districtMaster FROM DistrictMaster districtMaster";

	private static final String _SQL_SELECT_DISTRICTMASTER_WHERE =
		"SELECT districtMaster FROM DistrictMaster districtMaster WHERE ";

	private static final String _SQL_COUNT_DISTRICTMASTER =
		"SELECT COUNT(districtMaster) FROM DistrictMaster districtMaster";

	private static final String _SQL_COUNT_DISTRICTMASTER_WHERE =
		"SELECT COUNT(districtMaster) FROM DistrictMaster districtMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "districtMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DistrictMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DistrictMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DistrictMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}