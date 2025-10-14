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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

import com.mhdsys.schema.exception.NoSuchSportsFacilityMasterException;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.model.SportsFacilityMasterTable;
import com.mhdsys.schema.model.impl.SportsFacilityMasterImpl;
import com.mhdsys.schema.model.impl.SportsFacilityMasterModelImpl;
import com.mhdsys.schema.service.persistence.SportsFacilityMasterPersistence;
import com.mhdsys.schema.service.persistence.SportsFacilityMasterUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
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
 * The persistence implementation for the sports facility master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SportsFacilityMasterPersistence.class)
public class SportsFacilityMasterPersistenceImpl
	extends BasePersistenceImpl<SportsFacilityMaster>
	implements SportsFacilityMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SportsFacilityMasterUtil</code> to access the sports facility master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SportsFacilityMasterImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByfacilityName;
	private FinderPath _finderPathWithoutPaginationFindByfacilityName;
	private FinderPath _finderPathCountByfacilityName;

	/**
	 * Returns all the sports facility masters where facilityName = &#63;.
	 *
	 * @param facilityName the facility name
	 * @return the matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findByfacilityName(String facilityName) {
		return findByfacilityName(
			facilityName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility masters where facilityName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param facilityName the facility name
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @return the range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findByfacilityName(
		String facilityName, int start, int end) {

		return findByfacilityName(facilityName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility masters where facilityName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param facilityName the facility name
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findByfacilityName(
		String facilityName, int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		return findByfacilityName(
			facilityName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility masters where facilityName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param facilityName the facility name
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findByfacilityName(
		String facilityName, int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
		boolean useFinderCache) {

		facilityName = Objects.toString(facilityName, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByfacilityName;
				finderArgs = new Object[] {facilityName};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByfacilityName;
			finderArgs = new Object[] {
				facilityName, start, end, orderByComparator
			};
		}

		List<SportsFacilityMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsFacilityMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportsFacilityMaster sportsFacilityMaster : list) {
					if (!facilityName.equals(
							sportsFacilityMaster.getFacilityName())) {

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

			sb.append(_SQL_SELECT_SPORTSFACILITYMASTER_WHERE);

			boolean bindFacilityName = false;

			if (facilityName.isEmpty()) {
				sb.append(_FINDER_COLUMN_FACILITYNAME_FACILITYNAME_3);
			}
			else {
				bindFacilityName = true;

				sb.append(_FINDER_COLUMN_FACILITYNAME_FACILITYNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFacilityName) {
					queryPos.add(facilityName);
				}

				list = (List<SportsFacilityMaster>)QueryUtil.list(
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
	 * Returns the first sports facility master in the ordered set where facilityName = &#63;.
	 *
	 * @param facilityName the facility name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster findByfacilityName_First(
			String facilityName,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchByfacilityName_First(
			facilityName, orderByComparator);

		if (sportsFacilityMaster != null) {
			return sportsFacilityMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("facilityName=");
		sb.append(facilityName);

		sb.append("}");

		throw new NoSuchSportsFacilityMasterException(sb.toString());
	}

	/**
	 * Returns the first sports facility master in the ordered set where facilityName = &#63;.
	 *
	 * @param facilityName the facility name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility master, or <code>null</code> if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster fetchByfacilityName_First(
		String facilityName,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		List<SportsFacilityMaster> list = findByfacilityName(
			facilityName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports facility master in the ordered set where facilityName = &#63;.
	 *
	 * @param facilityName the facility name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster findByfacilityName_Last(
			String facilityName,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchByfacilityName_Last(
			facilityName, orderByComparator);

		if (sportsFacilityMaster != null) {
			return sportsFacilityMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("facilityName=");
		sb.append(facilityName);

		sb.append("}");

		throw new NoSuchSportsFacilityMasterException(sb.toString());
	}

	/**
	 * Returns the last sports facility master in the ordered set where facilityName = &#63;.
	 *
	 * @param facilityName the facility name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility master, or <code>null</code> if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster fetchByfacilityName_Last(
		String facilityName,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		int count = countByfacilityName(facilityName);

		if (count == 0) {
			return null;
		}

		List<SportsFacilityMaster> list = findByfacilityName(
			facilityName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports facility masters before and after the current sports facility master in the ordered set where facilityName = &#63;.
	 *
	 * @param sportsFacilityId the primary key of the current sports facility master
	 * @param facilityName the facility name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster[] findByfacilityName_PrevAndNext(
			long sportsFacilityId, String facilityName,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		facilityName = Objects.toString(facilityName, "");

		SportsFacilityMaster sportsFacilityMaster = findByPrimaryKey(
			sportsFacilityId);

		Session session = null;

		try {
			session = openSession();

			SportsFacilityMaster[] array = new SportsFacilityMasterImpl[3];

			array[0] = getByfacilityName_PrevAndNext(
				session, sportsFacilityMaster, facilityName, orderByComparator,
				true);

			array[1] = sportsFacilityMaster;

			array[2] = getByfacilityName_PrevAndNext(
				session, sportsFacilityMaster, facilityName, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SportsFacilityMaster getByfacilityName_PrevAndNext(
		Session session, SportsFacilityMaster sportsFacilityMaster,
		String facilityName,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTSFACILITYMASTER_WHERE);

		boolean bindFacilityName = false;

		if (facilityName.isEmpty()) {
			sb.append(_FINDER_COLUMN_FACILITYNAME_FACILITYNAME_3);
		}
		else {
			bindFacilityName = true;

			sb.append(_FINDER_COLUMN_FACILITYNAME_FACILITYNAME_2);
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
			sb.append(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindFacilityName) {
			queryPos.add(facilityName);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sportsFacilityMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportsFacilityMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports facility masters where facilityName = &#63; from the database.
	 *
	 * @param facilityName the facility name
	 */
	@Override
	public void removeByfacilityName(String facilityName) {
		for (SportsFacilityMaster sportsFacilityMaster :
				findByfacilityName(
					facilityName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportsFacilityMaster);
		}
	}

	/**
	 * Returns the number of sports facility masters where facilityName = &#63;.
	 *
	 * @param facilityName the facility name
	 * @return the number of matching sports facility masters
	 */
	@Override
	public int countByfacilityName(String facilityName) {
		facilityName = Objects.toString(facilityName, "");

		FinderPath finderPath = _finderPathCountByfacilityName;

		Object[] finderArgs = new Object[] {facilityName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSFACILITYMASTER_WHERE);

			boolean bindFacilityName = false;

			if (facilityName.isEmpty()) {
				sb.append(_FINDER_COLUMN_FACILITYNAME_FACILITYNAME_3);
			}
			else {
				bindFacilityName = true;

				sb.append(_FINDER_COLUMN_FACILITYNAME_FACILITYNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFacilityName) {
					queryPos.add(facilityName);
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

	private static final String _FINDER_COLUMN_FACILITYNAME_FACILITYNAME_2 =
		"sportsFacilityMaster.facilityName = ?";

	private static final String _FINDER_COLUMN_FACILITYNAME_FACILITYNAME_3 =
		"(sportsFacilityMaster.facilityName IS NULL OR sportsFacilityMaster.facilityName = '')";

	private FinderPath _finderPathWithPaginationFindByfacilityType;
	private FinderPath _finderPathWithoutPaginationFindByfacilityType;
	private FinderPath _finderPathCountByfacilityType;

	/**
	 * Returns all the sports facility masters where facilityType = &#63;.
	 *
	 * @param facilityType the facility type
	 * @return the matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findByfacilityType(String facilityType) {
		return findByfacilityType(
			facilityType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility masters where facilityType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param facilityType the facility type
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @return the range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findByfacilityType(
		String facilityType, int start, int end) {

		return findByfacilityType(facilityType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility masters where facilityType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param facilityType the facility type
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findByfacilityType(
		String facilityType, int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		return findByfacilityType(
			facilityType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility masters where facilityType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param facilityType the facility type
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findByfacilityType(
		String facilityType, int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
		boolean useFinderCache) {

		facilityType = Objects.toString(facilityType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByfacilityType;
				finderArgs = new Object[] {facilityType};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByfacilityType;
			finderArgs = new Object[] {
				facilityType, start, end, orderByComparator
			};
		}

		List<SportsFacilityMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsFacilityMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportsFacilityMaster sportsFacilityMaster : list) {
					if (!facilityType.equals(
							sportsFacilityMaster.getFacilityType())) {

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

			sb.append(_SQL_SELECT_SPORTSFACILITYMASTER_WHERE);

			boolean bindFacilityType = false;

			if (facilityType.isEmpty()) {
				sb.append(_FINDER_COLUMN_FACILITYTYPE_FACILITYTYPE_3);
			}
			else {
				bindFacilityType = true;

				sb.append(_FINDER_COLUMN_FACILITYTYPE_FACILITYTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFacilityType) {
					queryPos.add(facilityType);
				}

				list = (List<SportsFacilityMaster>)QueryUtil.list(
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
	 * Returns the first sports facility master in the ordered set where facilityType = &#63;.
	 *
	 * @param facilityType the facility type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster findByfacilityType_First(
			String facilityType,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchByfacilityType_First(
			facilityType, orderByComparator);

		if (sportsFacilityMaster != null) {
			return sportsFacilityMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("facilityType=");
		sb.append(facilityType);

		sb.append("}");

		throw new NoSuchSportsFacilityMasterException(sb.toString());
	}

	/**
	 * Returns the first sports facility master in the ordered set where facilityType = &#63;.
	 *
	 * @param facilityType the facility type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility master, or <code>null</code> if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster fetchByfacilityType_First(
		String facilityType,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		List<SportsFacilityMaster> list = findByfacilityType(
			facilityType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports facility master in the ordered set where facilityType = &#63;.
	 *
	 * @param facilityType the facility type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster findByfacilityType_Last(
			String facilityType,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchByfacilityType_Last(
			facilityType, orderByComparator);

		if (sportsFacilityMaster != null) {
			return sportsFacilityMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("facilityType=");
		sb.append(facilityType);

		sb.append("}");

		throw new NoSuchSportsFacilityMasterException(sb.toString());
	}

	/**
	 * Returns the last sports facility master in the ordered set where facilityType = &#63;.
	 *
	 * @param facilityType the facility type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility master, or <code>null</code> if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster fetchByfacilityType_Last(
		String facilityType,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		int count = countByfacilityType(facilityType);

		if (count == 0) {
			return null;
		}

		List<SportsFacilityMaster> list = findByfacilityType(
			facilityType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports facility masters before and after the current sports facility master in the ordered set where facilityType = &#63;.
	 *
	 * @param sportsFacilityId the primary key of the current sports facility master
	 * @param facilityType the facility type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster[] findByfacilityType_PrevAndNext(
			long sportsFacilityId, String facilityType,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		facilityType = Objects.toString(facilityType, "");

		SportsFacilityMaster sportsFacilityMaster = findByPrimaryKey(
			sportsFacilityId);

		Session session = null;

		try {
			session = openSession();

			SportsFacilityMaster[] array = new SportsFacilityMasterImpl[3];

			array[0] = getByfacilityType_PrevAndNext(
				session, sportsFacilityMaster, facilityType, orderByComparator,
				true);

			array[1] = sportsFacilityMaster;

			array[2] = getByfacilityType_PrevAndNext(
				session, sportsFacilityMaster, facilityType, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SportsFacilityMaster getByfacilityType_PrevAndNext(
		Session session, SportsFacilityMaster sportsFacilityMaster,
		String facilityType,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTSFACILITYMASTER_WHERE);

		boolean bindFacilityType = false;

		if (facilityType.isEmpty()) {
			sb.append(_FINDER_COLUMN_FACILITYTYPE_FACILITYTYPE_3);
		}
		else {
			bindFacilityType = true;

			sb.append(_FINDER_COLUMN_FACILITYTYPE_FACILITYTYPE_2);
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
			sb.append(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindFacilityType) {
			queryPos.add(facilityType);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sportsFacilityMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportsFacilityMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports facility masters where facilityType = &#63; from the database.
	 *
	 * @param facilityType the facility type
	 */
	@Override
	public void removeByfacilityType(String facilityType) {
		for (SportsFacilityMaster sportsFacilityMaster :
				findByfacilityType(
					facilityType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportsFacilityMaster);
		}
	}

	/**
	 * Returns the number of sports facility masters where facilityType = &#63;.
	 *
	 * @param facilityType the facility type
	 * @return the number of matching sports facility masters
	 */
	@Override
	public int countByfacilityType(String facilityType) {
		facilityType = Objects.toString(facilityType, "");

		FinderPath finderPath = _finderPathCountByfacilityType;

		Object[] finderArgs = new Object[] {facilityType};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSFACILITYMASTER_WHERE);

			boolean bindFacilityType = false;

			if (facilityType.isEmpty()) {
				sb.append(_FINDER_COLUMN_FACILITYTYPE_FACILITYTYPE_3);
			}
			else {
				bindFacilityType = true;

				sb.append(_FINDER_COLUMN_FACILITYTYPE_FACILITYTYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFacilityType) {
					queryPos.add(facilityType);
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

	private static final String _FINDER_COLUMN_FACILITYTYPE_FACILITYTYPE_2 =
		"sportsFacilityMaster.facilityType = ?";

	private static final String _FINDER_COLUMN_FACILITYTYPE_FACILITYTYPE_3 =
		"(sportsFacilityMaster.facilityType IS NULL OR sportsFacilityMaster.facilityType = '')";

	private FinderPath _finderPathWithPaginationFindBytype;
	private FinderPath _finderPathWithoutPaginationFindBytype;
	private FinderPath _finderPathCountBytype;

	/**
	 * Returns all the sports facility masters where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findBytype(String type) {
		return findBytype(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility masters where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @return the range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findBytype(
		String type, int start, int end) {

		return findBytype(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility masters where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findBytype(
		String type, int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		return findBytype(type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility masters where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findBytype(
		String type, int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
		boolean useFinderCache) {

		type = Objects.toString(type, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBytype;
				finderArgs = new Object[] {type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBytype;
			finderArgs = new Object[] {type, start, end, orderByComparator};
		}

		List<SportsFacilityMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsFacilityMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportsFacilityMaster sportsFacilityMaster : list) {
					if (!type.equals(sportsFacilityMaster.getType())) {
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

			sb.append(_SQL_SELECT_SPORTSFACILITYMASTER_WHERE);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindType) {
					queryPos.add(type);
				}

				list = (List<SportsFacilityMaster>)QueryUtil.list(
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
	 * Returns the first sports facility master in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster findBytype_First(
			String type,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchBytype_First(
			type, orderByComparator);

		if (sportsFacilityMaster != null) {
			return sportsFacilityMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchSportsFacilityMasterException(sb.toString());
	}

	/**
	 * Returns the first sports facility master in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility master, or <code>null</code> if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster fetchBytype_First(
		String type,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		List<SportsFacilityMaster> list = findBytype(
			type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports facility master in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster findBytype_Last(
			String type,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchBytype_Last(
			type, orderByComparator);

		if (sportsFacilityMaster != null) {
			return sportsFacilityMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchSportsFacilityMasterException(sb.toString());
	}

	/**
	 * Returns the last sports facility master in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility master, or <code>null</code> if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster fetchBytype_Last(
		String type,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		int count = countBytype(type);

		if (count == 0) {
			return null;
		}

		List<SportsFacilityMaster> list = findBytype(
			type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports facility masters before and after the current sports facility master in the ordered set where type = &#63;.
	 *
	 * @param sportsFacilityId the primary key of the current sports facility master
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster[] findBytype_PrevAndNext(
			long sportsFacilityId, String type,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		type = Objects.toString(type, "");

		SportsFacilityMaster sportsFacilityMaster = findByPrimaryKey(
			sportsFacilityId);

		Session session = null;

		try {
			session = openSession();

			SportsFacilityMaster[] array = new SportsFacilityMasterImpl[3];

			array[0] = getBytype_PrevAndNext(
				session, sportsFacilityMaster, type, orderByComparator, true);

			array[1] = sportsFacilityMaster;

			array[2] = getBytype_PrevAndNext(
				session, sportsFacilityMaster, type, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SportsFacilityMaster getBytype_PrevAndNext(
		Session session, SportsFacilityMaster sportsFacilityMaster, String type,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTSFACILITYMASTER_WHERE);

		boolean bindType = false;

		if (type.isEmpty()) {
			sb.append(_FINDER_COLUMN_TYPE_TYPE_3);
		}
		else {
			bindType = true;

			sb.append(_FINDER_COLUMN_TYPE_TYPE_2);
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
			sb.append(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindType) {
			queryPos.add(type);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sportsFacilityMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportsFacilityMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports facility masters where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeBytype(String type) {
		for (SportsFacilityMaster sportsFacilityMaster :
				findBytype(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportsFacilityMaster);
		}
	}

	/**
	 * Returns the number of sports facility masters where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching sports facility masters
	 */
	@Override
	public int countBytype(String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountBytype;

		Object[] finderArgs = new Object[] {type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSFACILITYMASTER_WHERE);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindType) {
					queryPos.add(type);
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

	private static final String _FINDER_COLUMN_TYPE_TYPE_2 =
		"sportsFacilityMaster.type = ?";

	private static final String _FINDER_COLUMN_TYPE_TYPE_3 =
		"(sportsFacilityMaster.type IS NULL OR sportsFacilityMaster.type = '')";

	private FinderPath _finderPathWithPaginationFindBycreatorUserId;
	private FinderPath _finderPathWithoutPaginationFindBycreatorUserId;
	private FinderPath _finderPathCountBycreatorUserId;

	/**
	 * Returns all the sports facility masters where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @return the matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findBycreatorUserId(long creatorUserId) {
		return findBycreatorUserId(
			creatorUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility masters where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @return the range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findBycreatorUserId(
		long creatorUserId, int start, int end) {

		return findBycreatorUserId(creatorUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility masters where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findBycreatorUserId(
		long creatorUserId, int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		return findBycreatorUserId(
			creatorUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility masters where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findBycreatorUserId(
		long creatorUserId, int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBycreatorUserId;
				finderArgs = new Object[] {creatorUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBycreatorUserId;
			finderArgs = new Object[] {
				creatorUserId, start, end, orderByComparator
			};
		}

		List<SportsFacilityMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsFacilityMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportsFacilityMaster sportsFacilityMaster : list) {
					if (creatorUserId !=
							sportsFacilityMaster.getCreatorUserId()) {

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

			sb.append(_SQL_SELECT_SPORTSFACILITYMASTER_WHERE);

			sb.append(_FINDER_COLUMN_CREATORUSERID_CREATORUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(creatorUserId);

				list = (List<SportsFacilityMaster>)QueryUtil.list(
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
	 * Returns the first sports facility master in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster findBycreatorUserId_First(
			long creatorUserId,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchBycreatorUserId_First(
			creatorUserId, orderByComparator);

		if (sportsFacilityMaster != null) {
			return sportsFacilityMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("creatorUserId=");
		sb.append(creatorUserId);

		sb.append("}");

		throw new NoSuchSportsFacilityMasterException(sb.toString());
	}

	/**
	 * Returns the first sports facility master in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility master, or <code>null</code> if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster fetchBycreatorUserId_First(
		long creatorUserId,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		List<SportsFacilityMaster> list = findBycreatorUserId(
			creatorUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports facility master in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster findBycreatorUserId_Last(
			long creatorUserId,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchBycreatorUserId_Last(
			creatorUserId, orderByComparator);

		if (sportsFacilityMaster != null) {
			return sportsFacilityMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("creatorUserId=");
		sb.append(creatorUserId);

		sb.append("}");

		throw new NoSuchSportsFacilityMasterException(sb.toString());
	}

	/**
	 * Returns the last sports facility master in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility master, or <code>null</code> if a matching sports facility master could not be found
	 */
	@Override
	public SportsFacilityMaster fetchBycreatorUserId_Last(
		long creatorUserId,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		int count = countBycreatorUserId(creatorUserId);

		if (count == 0) {
			return null;
		}

		List<SportsFacilityMaster> list = findBycreatorUserId(
			creatorUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports facility masters before and after the current sports facility master in the ordered set where creatorUserId = &#63;.
	 *
	 * @param sportsFacilityId the primary key of the current sports facility master
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster[] findBycreatorUserId_PrevAndNext(
			long sportsFacilityId, long creatorUserId,
			OrderByComparator<SportsFacilityMaster> orderByComparator)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = findByPrimaryKey(
			sportsFacilityId);

		Session session = null;

		try {
			session = openSession();

			SportsFacilityMaster[] array = new SportsFacilityMasterImpl[3];

			array[0] = getBycreatorUserId_PrevAndNext(
				session, sportsFacilityMaster, creatorUserId, orderByComparator,
				true);

			array[1] = sportsFacilityMaster;

			array[2] = getBycreatorUserId_PrevAndNext(
				session, sportsFacilityMaster, creatorUserId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected SportsFacilityMaster getBycreatorUserId_PrevAndNext(
		Session session, SportsFacilityMaster sportsFacilityMaster,
		long creatorUserId,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTSFACILITYMASTER_WHERE);

		sb.append(_FINDER_COLUMN_CREATORUSERID_CREATORUSERID_2);

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
			sb.append(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(creatorUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sportsFacilityMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportsFacilityMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports facility masters where creatorUserId = &#63; from the database.
	 *
	 * @param creatorUserId the creator user ID
	 */
	@Override
	public void removeBycreatorUserId(long creatorUserId) {
		for (SportsFacilityMaster sportsFacilityMaster :
				findBycreatorUserId(
					creatorUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(sportsFacilityMaster);
		}
	}

	/**
	 * Returns the number of sports facility masters where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @return the number of matching sports facility masters
	 */
	@Override
	public int countBycreatorUserId(long creatorUserId) {
		FinderPath finderPath = _finderPathCountBycreatorUserId;

		Object[] finderArgs = new Object[] {creatorUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSFACILITYMASTER_WHERE);

			sb.append(_FINDER_COLUMN_CREATORUSERID_CREATORUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(creatorUserId);

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

	private static final String _FINDER_COLUMN_CREATORUSERID_CREATORUSERID_2 =
		"sportsFacilityMaster.creatorUserId = ?";

	public SportsFacilityMasterPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(SportsFacilityMaster.class);

		setModelImplClass(SportsFacilityMasterImpl.class);
		setModelPKClass(long.class);

		setTable(SportsFacilityMasterTable.INSTANCE);
	}

	/**
	 * Caches the sports facility master in the entity cache if it is enabled.
	 *
	 * @param sportsFacilityMaster the sports facility master
	 */
	@Override
	public void cacheResult(SportsFacilityMaster sportsFacilityMaster) {
		entityCache.putResult(
			SportsFacilityMasterImpl.class,
			sportsFacilityMaster.getPrimaryKey(), sportsFacilityMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sports facility masters in the entity cache if it is enabled.
	 *
	 * @param sportsFacilityMasters the sports facility masters
	 */
	@Override
	public void cacheResult(List<SportsFacilityMaster> sportsFacilityMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sportsFacilityMasters.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SportsFacilityMaster sportsFacilityMaster :
				sportsFacilityMasters) {

			if (entityCache.getResult(
					SportsFacilityMasterImpl.class,
					sportsFacilityMaster.getPrimaryKey()) == null) {

				cacheResult(sportsFacilityMaster);
			}
		}
	}

	/**
	 * Clears the cache for all sports facility masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SportsFacilityMasterImpl.class);

		finderCache.clearCache(SportsFacilityMasterImpl.class);
	}

	/**
	 * Clears the cache for the sports facility master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SportsFacilityMaster sportsFacilityMaster) {
		entityCache.removeResult(
			SportsFacilityMasterImpl.class, sportsFacilityMaster);
	}

	@Override
	public void clearCache(List<SportsFacilityMaster> sportsFacilityMasters) {
		for (SportsFacilityMaster sportsFacilityMaster :
				sportsFacilityMasters) {

			entityCache.removeResult(
				SportsFacilityMasterImpl.class, sportsFacilityMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SportsFacilityMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				SportsFacilityMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sports facility master with the primary key. Does not add the sports facility master to the database.
	 *
	 * @param sportsFacilityId the primary key for the new sports facility master
	 * @return the new sports facility master
	 */
	@Override
	public SportsFacilityMaster create(long sportsFacilityId) {
		SportsFacilityMaster sportsFacilityMaster =
			new SportsFacilityMasterImpl();

		sportsFacilityMaster.setNew(true);
		sportsFacilityMaster.setPrimaryKey(sportsFacilityId);

		return sportsFacilityMaster;
	}

	/**
	 * Removes the sports facility master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sportsFacilityId the primary key of the sports facility master
	 * @return the sports facility master that was removed
	 * @throws NoSuchSportsFacilityMasterException if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster remove(long sportsFacilityId)
		throws NoSuchSportsFacilityMasterException {

		return remove((Serializable)sportsFacilityId);
	}

	/**
	 * Removes the sports facility master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sports facility master
	 * @return the sports facility master that was removed
	 * @throws NoSuchSportsFacilityMasterException if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster remove(Serializable primaryKey)
		throws NoSuchSportsFacilityMasterException {

		Session session = null;

		try {
			session = openSession();

			SportsFacilityMaster sportsFacilityMaster =
				(SportsFacilityMaster)session.get(
					SportsFacilityMasterImpl.class, primaryKey);

			if (sportsFacilityMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSportsFacilityMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sportsFacilityMaster);
		}
		catch (NoSuchSportsFacilityMasterException noSuchEntityException) {
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
	protected SportsFacilityMaster removeImpl(
		SportsFacilityMaster sportsFacilityMaster) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sportsFacilityMaster)) {
				sportsFacilityMaster = (SportsFacilityMaster)session.get(
					SportsFacilityMasterImpl.class,
					sportsFacilityMaster.getPrimaryKeyObj());
			}

			if (sportsFacilityMaster != null) {
				session.delete(sportsFacilityMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sportsFacilityMaster != null) {
			clearCache(sportsFacilityMaster);
		}

		return sportsFacilityMaster;
	}

	@Override
	public SportsFacilityMaster updateImpl(
		SportsFacilityMaster sportsFacilityMaster) {

		boolean isNew = sportsFacilityMaster.isNew();

		if (!(sportsFacilityMaster instanceof SportsFacilityMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sportsFacilityMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sportsFacilityMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sportsFacilityMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SportsFacilityMaster implementation " +
					sportsFacilityMaster.getClass());
		}

		SportsFacilityMasterModelImpl sportsFacilityMasterModelImpl =
			(SportsFacilityMasterModelImpl)sportsFacilityMaster;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (sportsFacilityMaster.getCreateDate() == null)) {
			if (serviceContext == null) {
				sportsFacilityMaster.setCreateDate(date);
			}
			else {
				sportsFacilityMaster.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!sportsFacilityMasterModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				sportsFacilityMaster.setModifiedDate(date);
			}
			else {
				sportsFacilityMaster.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sportsFacilityMaster);
			}
			else {
				sportsFacilityMaster = (SportsFacilityMaster)session.merge(
					sportsFacilityMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SportsFacilityMasterImpl.class, sportsFacilityMasterModelImpl,
			false, true);

		if (isNew) {
			sportsFacilityMaster.setNew(false);
		}

		sportsFacilityMaster.resetOriginalValues();

		return sportsFacilityMaster;
	}

	/**
	 * Returns the sports facility master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sports facility master
	 * @return the sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSportsFacilityMasterException {

		SportsFacilityMaster sportsFacilityMaster = fetchByPrimaryKey(
			primaryKey);

		if (sportsFacilityMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSportsFacilityMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sportsFacilityMaster;
	}

	/**
	 * Returns the sports facility master with the primary key or throws a <code>NoSuchSportsFacilityMasterException</code> if it could not be found.
	 *
	 * @param sportsFacilityId the primary key of the sports facility master
	 * @return the sports facility master
	 * @throws NoSuchSportsFacilityMasterException if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster findByPrimaryKey(long sportsFacilityId)
		throws NoSuchSportsFacilityMasterException {

		return findByPrimaryKey((Serializable)sportsFacilityId);
	}

	/**
	 * Returns the sports facility master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sportsFacilityId the primary key of the sports facility master
	 * @return the sports facility master, or <code>null</code> if a sports facility master with the primary key could not be found
	 */
	@Override
	public SportsFacilityMaster fetchByPrimaryKey(long sportsFacilityId) {
		return fetchByPrimaryKey((Serializable)sportsFacilityId);
	}

	/**
	 * Returns all the sports facility masters.
	 *
	 * @return the sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @return the range of sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findAll(
		int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportsFacilityMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports facility masters
	 * @param end the upper bound of the range of sports facility masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sports facility masters
	 */
	@Override
	public List<SportsFacilityMaster> findAll(
		int start, int end,
		OrderByComparator<SportsFacilityMaster> orderByComparator,
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

		List<SportsFacilityMaster> list = null;

		if (useFinderCache) {
			list = (List<SportsFacilityMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SPORTSFACILITYMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SPORTSFACILITYMASTER;

				sql = sql.concat(SportsFacilityMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SportsFacilityMaster>)QueryUtil.list(
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
	 * Removes all the sports facility masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SportsFacilityMaster sportsFacilityMaster : findAll()) {
			remove(sportsFacilityMaster);
		}
	}

	/**
	 * Returns the number of sports facility masters.
	 *
	 * @return the number of sports facility masters
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
					_SQL_COUNT_SPORTSFACILITYMASTER);

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
		return "sportsFacilityId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SPORTSFACILITYMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SportsFacilityMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sports facility master persistence.
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

		_finderPathWithPaginationFindByfacilityName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfacilityName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"facilityName"}, true);

		_finderPathWithoutPaginationFindByfacilityName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfacilityName",
			new String[] {String.class.getName()},
			new String[] {"facilityName"}, true);

		_finderPathCountByfacilityName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByfacilityName",
			new String[] {String.class.getName()},
			new String[] {"facilityName"}, false);

		_finderPathWithPaginationFindByfacilityType = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfacilityType",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"facilityType"}, true);

		_finderPathWithoutPaginationFindByfacilityType = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfacilityType",
			new String[] {String.class.getName()},
			new String[] {"facilityType"}, true);

		_finderPathCountByfacilityType = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByfacilityType",
			new String[] {String.class.getName()},
			new String[] {"facilityType"}, false);

		_finderPathWithPaginationFindBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBytype",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"type_"}, true);

		_finderPathWithoutPaginationFindBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBytype",
			new String[] {String.class.getName()}, new String[] {"type_"},
			true);

		_finderPathCountBytype = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBytype",
			new String[] {String.class.getName()}, new String[] {"type_"},
			false);

		_finderPathWithPaginationFindBycreatorUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycreatorUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"creatorUserId"}, true);

		_finderPathWithoutPaginationFindBycreatorUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBycreatorUserId",
			new String[] {Long.class.getName()}, new String[] {"creatorUserId"},
			true);

		_finderPathCountBycreatorUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycreatorUserId",
			new String[] {Long.class.getName()}, new String[] {"creatorUserId"},
			false);

		SportsFacilityMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SportsFacilityMasterUtil.setPersistence(null);

		entityCache.removeCache(SportsFacilityMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_SPORTSFACILITYMASTER =
		"SELECT sportsFacilityMaster FROM SportsFacilityMaster sportsFacilityMaster";

	private static final String _SQL_SELECT_SPORTSFACILITYMASTER_WHERE =
		"SELECT sportsFacilityMaster FROM SportsFacilityMaster sportsFacilityMaster WHERE ";

	private static final String _SQL_COUNT_SPORTSFACILITYMASTER =
		"SELECT COUNT(sportsFacilityMaster) FROM SportsFacilityMaster sportsFacilityMaster";

	private static final String _SQL_COUNT_SPORTSFACILITYMASTER_WHERE =
		"SELECT COUNT(sportsFacilityMaster) FROM SportsFacilityMaster sportsFacilityMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"sportsFacilityMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SportsFacilityMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SportsFacilityMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SportsFacilityMasterPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}