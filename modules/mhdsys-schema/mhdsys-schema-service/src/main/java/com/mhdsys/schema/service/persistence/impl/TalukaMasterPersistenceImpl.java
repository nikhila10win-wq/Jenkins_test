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

import com.mhdsys.schema.exception.NoSuchTalukaMasterException;
import com.mhdsys.schema.model.TalukaMaster;
import com.mhdsys.schema.model.TalukaMasterTable;
import com.mhdsys.schema.model.impl.TalukaMasterImpl;
import com.mhdsys.schema.model.impl.TalukaMasterModelImpl;
import com.mhdsys.schema.service.persistence.TalukaMasterPersistence;
import com.mhdsys.schema.service.persistence.TalukaMasterUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the taluka master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TalukaMasterPersistence.class)
public class TalukaMasterPersistenceImpl
	extends BasePersistenceImpl<TalukaMaster>
	implements TalukaMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TalukaMasterUtil</code> to access the taluka master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TalukaMasterImpl.class.getName();

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
	 * Returns all the taluka masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the matching taluka masters
	 */
	@Override
	public List<TalukaMaster> findByActiveState(boolean isActive) {
		return findByActiveState(
			isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the taluka masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @return the range of matching taluka masters
	 */
	@Override
	public List<TalukaMaster> findByActiveState(
		boolean isActive, int start, int end) {

		return findByActiveState(isActive, start, end, null);
	}

	/**
	 * Returns an ordered range of all the taluka masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching taluka masters
	 */
	@Override
	public List<TalukaMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<TalukaMaster> orderByComparator) {

		return findByActiveState(isActive, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the taluka masters where isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param isActive the is active
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching taluka masters
	 */
	@Override
	public List<TalukaMaster> findByActiveState(
		boolean isActive, int start, int end,
		OrderByComparator<TalukaMaster> orderByComparator,
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

		List<TalukaMaster> list = null;

		if (useFinderCache) {
			list = (List<TalukaMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TalukaMaster talukaMaster : list) {
					if (isActive != talukaMaster.isIsActive()) {
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

			sb.append(_SQL_SELECT_TALUKAMASTER_WHERE);

			sb.append(_FINDER_COLUMN_ACTIVESTATE_ISACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TalukaMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isActive);

				list = (List<TalukaMaster>)QueryUtil.list(
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
	 * Returns the first taluka master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching taluka master
	 * @throws NoSuchTalukaMasterException if a matching taluka master could not be found
	 */
	@Override
	public TalukaMaster findByActiveState_First(
			boolean isActive, OrderByComparator<TalukaMaster> orderByComparator)
		throws NoSuchTalukaMasterException {

		TalukaMaster talukaMaster = fetchByActiveState_First(
			isActive, orderByComparator);

		if (talukaMaster != null) {
			return talukaMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchTalukaMasterException(sb.toString());
	}

	/**
	 * Returns the first taluka master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching taluka master, or <code>null</code> if a matching taluka master could not be found
	 */
	@Override
	public TalukaMaster fetchByActiveState_First(
		boolean isActive, OrderByComparator<TalukaMaster> orderByComparator) {

		List<TalukaMaster> list = findByActiveState(
			isActive, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last taluka master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching taluka master
	 * @throws NoSuchTalukaMasterException if a matching taluka master could not be found
	 */
	@Override
	public TalukaMaster findByActiveState_Last(
			boolean isActive, OrderByComparator<TalukaMaster> orderByComparator)
		throws NoSuchTalukaMasterException {

		TalukaMaster talukaMaster = fetchByActiveState_Last(
			isActive, orderByComparator);

		if (talukaMaster != null) {
			return talukaMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isActive=");
		sb.append(isActive);

		sb.append("}");

		throw new NoSuchTalukaMasterException(sb.toString());
	}

	/**
	 * Returns the last taluka master in the ordered set where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching taluka master, or <code>null</code> if a matching taluka master could not be found
	 */
	@Override
	public TalukaMaster fetchByActiveState_Last(
		boolean isActive, OrderByComparator<TalukaMaster> orderByComparator) {

		int count = countByActiveState(isActive);

		if (count == 0) {
			return null;
		}

		List<TalukaMaster> list = findByActiveState(
			isActive, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the taluka masters before and after the current taluka master in the ordered set where isActive = &#63;.
	 *
	 * @param talukaId the primary key of the current taluka master
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next taluka master
	 * @throws NoSuchTalukaMasterException if a taluka master with the primary key could not be found
	 */
	@Override
	public TalukaMaster[] findByActiveState_PrevAndNext(
			long talukaId, boolean isActive,
			OrderByComparator<TalukaMaster> orderByComparator)
		throws NoSuchTalukaMasterException {

		TalukaMaster talukaMaster = findByPrimaryKey(talukaId);

		Session session = null;

		try {
			session = openSession();

			TalukaMaster[] array = new TalukaMasterImpl[3];

			array[0] = getByActiveState_PrevAndNext(
				session, talukaMaster, isActive, orderByComparator, true);

			array[1] = talukaMaster;

			array[2] = getByActiveState_PrevAndNext(
				session, talukaMaster, isActive, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TalukaMaster getByActiveState_PrevAndNext(
		Session session, TalukaMaster talukaMaster, boolean isActive,
		OrderByComparator<TalukaMaster> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TALUKAMASTER_WHERE);

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
			sb.append(TalukaMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isActive);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(talukaMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TalukaMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the taluka masters where isActive = &#63; from the database.
	 *
	 * @param isActive the is active
	 */
	@Override
	public void removeByActiveState(boolean isActive) {
		for (TalukaMaster talukaMaster :
				findByActiveState(
					isActive, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(talukaMaster);
		}
	}

	/**
	 * Returns the number of taluka masters where isActive = &#63;.
	 *
	 * @param isActive the is active
	 * @return the number of matching taluka masters
	 */
	@Override
	public int countByActiveState(boolean isActive) {
		FinderPath finderPath = _finderPathCountByActiveState;

		Object[] finderArgs = new Object[] {isActive};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TALUKAMASTER_WHERE);

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
		"talukaMaster.isActive = ?";

	private FinderPath _finderPathWithPaginationFindByDistrictId;
	private FinderPath _finderPathWithoutPaginationFindByDistrictId;
	private FinderPath _finderPathCountByDistrictId;

	/**
	 * Returns all the taluka masters where districtId = &#63;.
	 *
	 * @param districtId the district ID
	 * @return the matching taluka masters
	 */
	@Override
	public List<TalukaMaster> findByDistrictId(long districtId) {
		return findByDistrictId(
			districtId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the taluka masters where districtId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param districtId the district ID
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @return the range of matching taluka masters
	 */
	@Override
	public List<TalukaMaster> findByDistrictId(
		long districtId, int start, int end) {

		return findByDistrictId(districtId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the taluka masters where districtId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param districtId the district ID
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching taluka masters
	 */
	@Override
	public List<TalukaMaster> findByDistrictId(
		long districtId, int start, int end,
		OrderByComparator<TalukaMaster> orderByComparator) {

		return findByDistrictId(
			districtId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the taluka masters where districtId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param districtId the district ID
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching taluka masters
	 */
	@Override
	public List<TalukaMaster> findByDistrictId(
		long districtId, int start, int end,
		OrderByComparator<TalukaMaster> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByDistrictId;
				finderArgs = new Object[] {districtId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByDistrictId;
			finderArgs = new Object[] {
				districtId, start, end, orderByComparator
			};
		}

		List<TalukaMaster> list = null;

		if (useFinderCache) {
			list = (List<TalukaMaster>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TalukaMaster talukaMaster : list) {
					if (districtId != talukaMaster.getDistrictId()) {
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

			sb.append(_SQL_SELECT_TALUKAMASTER_WHERE);

			sb.append(_FINDER_COLUMN_DISTRICTID_DISTRICTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TalukaMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(districtId);

				list = (List<TalukaMaster>)QueryUtil.list(
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
	 * Returns the first taluka master in the ordered set where districtId = &#63;.
	 *
	 * @param districtId the district ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching taluka master
	 * @throws NoSuchTalukaMasterException if a matching taluka master could not be found
	 */
	@Override
	public TalukaMaster findByDistrictId_First(
			long districtId, OrderByComparator<TalukaMaster> orderByComparator)
		throws NoSuchTalukaMasterException {

		TalukaMaster talukaMaster = fetchByDistrictId_First(
			districtId, orderByComparator);

		if (talukaMaster != null) {
			return talukaMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("districtId=");
		sb.append(districtId);

		sb.append("}");

		throw new NoSuchTalukaMasterException(sb.toString());
	}

	/**
	 * Returns the first taluka master in the ordered set where districtId = &#63;.
	 *
	 * @param districtId the district ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching taluka master, or <code>null</code> if a matching taluka master could not be found
	 */
	@Override
	public TalukaMaster fetchByDistrictId_First(
		long districtId, OrderByComparator<TalukaMaster> orderByComparator) {

		List<TalukaMaster> list = findByDistrictId(
			districtId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last taluka master in the ordered set where districtId = &#63;.
	 *
	 * @param districtId the district ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching taluka master
	 * @throws NoSuchTalukaMasterException if a matching taluka master could not be found
	 */
	@Override
	public TalukaMaster findByDistrictId_Last(
			long districtId, OrderByComparator<TalukaMaster> orderByComparator)
		throws NoSuchTalukaMasterException {

		TalukaMaster talukaMaster = fetchByDistrictId_Last(
			districtId, orderByComparator);

		if (talukaMaster != null) {
			return talukaMaster;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("districtId=");
		sb.append(districtId);

		sb.append("}");

		throw new NoSuchTalukaMasterException(sb.toString());
	}

	/**
	 * Returns the last taluka master in the ordered set where districtId = &#63;.
	 *
	 * @param districtId the district ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching taluka master, or <code>null</code> if a matching taluka master could not be found
	 */
	@Override
	public TalukaMaster fetchByDistrictId_Last(
		long districtId, OrderByComparator<TalukaMaster> orderByComparator) {

		int count = countByDistrictId(districtId);

		if (count == 0) {
			return null;
		}

		List<TalukaMaster> list = findByDistrictId(
			districtId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the taluka masters before and after the current taluka master in the ordered set where districtId = &#63;.
	 *
	 * @param talukaId the primary key of the current taluka master
	 * @param districtId the district ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next taluka master
	 * @throws NoSuchTalukaMasterException if a taluka master with the primary key could not be found
	 */
	@Override
	public TalukaMaster[] findByDistrictId_PrevAndNext(
			long talukaId, long districtId,
			OrderByComparator<TalukaMaster> orderByComparator)
		throws NoSuchTalukaMasterException {

		TalukaMaster talukaMaster = findByPrimaryKey(talukaId);

		Session session = null;

		try {
			session = openSession();

			TalukaMaster[] array = new TalukaMasterImpl[3];

			array[0] = getByDistrictId_PrevAndNext(
				session, talukaMaster, districtId, orderByComparator, true);

			array[1] = talukaMaster;

			array[2] = getByDistrictId_PrevAndNext(
				session, talukaMaster, districtId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TalukaMaster getByDistrictId_PrevAndNext(
		Session session, TalukaMaster talukaMaster, long districtId,
		OrderByComparator<TalukaMaster> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TALUKAMASTER_WHERE);

		sb.append(_FINDER_COLUMN_DISTRICTID_DISTRICTID_2);

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
			sb.append(TalukaMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(districtId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(talukaMaster)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TalukaMaster> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the taluka masters where districtId = &#63; from the database.
	 *
	 * @param districtId the district ID
	 */
	@Override
	public void removeByDistrictId(long districtId) {
		for (TalukaMaster talukaMaster :
				findByDistrictId(
					districtId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(talukaMaster);
		}
	}

	/**
	 * Returns the number of taluka masters where districtId = &#63;.
	 *
	 * @param districtId the district ID
	 * @return the number of matching taluka masters
	 */
	@Override
	public int countByDistrictId(long districtId) {
		FinderPath finderPath = _finderPathCountByDistrictId;

		Object[] finderArgs = new Object[] {districtId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TALUKAMASTER_WHERE);

			sb.append(_FINDER_COLUMN_DISTRICTID_DISTRICTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(districtId);

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

	private static final String _FINDER_COLUMN_DISTRICTID_DISTRICTID_2 =
		"talukaMaster.districtId = ?";

	public TalukaMasterPersistenceImpl() {
		setModelClass(TalukaMaster.class);

		setModelImplClass(TalukaMasterImpl.class);
		setModelPKClass(long.class);

		setTable(TalukaMasterTable.INSTANCE);
	}

	/**
	 * Caches the taluka master in the entity cache if it is enabled.
	 *
	 * @param talukaMaster the taluka master
	 */
	@Override
	public void cacheResult(TalukaMaster talukaMaster) {
		entityCache.putResult(
			TalukaMasterImpl.class, talukaMaster.getPrimaryKey(), talukaMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the taluka masters in the entity cache if it is enabled.
	 *
	 * @param talukaMasters the taluka masters
	 */
	@Override
	public void cacheResult(List<TalukaMaster> talukaMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (talukaMasters.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TalukaMaster talukaMaster : talukaMasters) {
			if (entityCache.getResult(
					TalukaMasterImpl.class, talukaMaster.getPrimaryKey()) ==
						null) {

				cacheResult(talukaMaster);
			}
		}
	}

	/**
	 * Clears the cache for all taluka masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TalukaMasterImpl.class);

		finderCache.clearCache(TalukaMasterImpl.class);
	}

	/**
	 * Clears the cache for the taluka master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TalukaMaster talukaMaster) {
		entityCache.removeResult(TalukaMasterImpl.class, talukaMaster);
	}

	@Override
	public void clearCache(List<TalukaMaster> talukaMasters) {
		for (TalukaMaster talukaMaster : talukaMasters) {
			entityCache.removeResult(TalukaMasterImpl.class, talukaMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TalukaMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TalukaMasterImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new taluka master with the primary key. Does not add the taluka master to the database.
	 *
	 * @param talukaId the primary key for the new taluka master
	 * @return the new taluka master
	 */
	@Override
	public TalukaMaster create(long talukaId) {
		TalukaMaster talukaMaster = new TalukaMasterImpl();

		talukaMaster.setNew(true);
		talukaMaster.setPrimaryKey(talukaId);

		return talukaMaster;
	}

	/**
	 * Removes the taluka master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param talukaId the primary key of the taluka master
	 * @return the taluka master that was removed
	 * @throws NoSuchTalukaMasterException if a taluka master with the primary key could not be found
	 */
	@Override
	public TalukaMaster remove(long talukaId)
		throws NoSuchTalukaMasterException {

		return remove((Serializable)talukaId);
	}

	/**
	 * Removes the taluka master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the taluka master
	 * @return the taluka master that was removed
	 * @throws NoSuchTalukaMasterException if a taluka master with the primary key could not be found
	 */
	@Override
	public TalukaMaster remove(Serializable primaryKey)
		throws NoSuchTalukaMasterException {

		Session session = null;

		try {
			session = openSession();

			TalukaMaster talukaMaster = (TalukaMaster)session.get(
				TalukaMasterImpl.class, primaryKey);

			if (talukaMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTalukaMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(talukaMaster);
		}
		catch (NoSuchTalukaMasterException noSuchEntityException) {
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
	protected TalukaMaster removeImpl(TalukaMaster talukaMaster) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(talukaMaster)) {
				talukaMaster = (TalukaMaster)session.get(
					TalukaMasterImpl.class, talukaMaster.getPrimaryKeyObj());
			}

			if (talukaMaster != null) {
				session.delete(talukaMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (talukaMaster != null) {
			clearCache(talukaMaster);
		}

		return talukaMaster;
	}

	@Override
	public TalukaMaster updateImpl(TalukaMaster talukaMaster) {
		boolean isNew = talukaMaster.isNew();

		if (!(talukaMaster instanceof TalukaMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(talukaMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					talukaMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in talukaMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TalukaMaster implementation " +
					talukaMaster.getClass());
		}

		TalukaMasterModelImpl talukaMasterModelImpl =
			(TalukaMasterModelImpl)talukaMaster;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(talukaMaster);
			}
			else {
				talukaMaster = (TalukaMaster)session.merge(talukaMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			TalukaMasterImpl.class, talukaMasterModelImpl, false, true);

		if (isNew) {
			talukaMaster.setNew(false);
		}

		talukaMaster.resetOriginalValues();

		return talukaMaster;
	}

	/**
	 * Returns the taluka master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the taluka master
	 * @return the taluka master
	 * @throws NoSuchTalukaMasterException if a taluka master with the primary key could not be found
	 */
	@Override
	public TalukaMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTalukaMasterException {

		TalukaMaster talukaMaster = fetchByPrimaryKey(primaryKey);

		if (talukaMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTalukaMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return talukaMaster;
	}

	/**
	 * Returns the taluka master with the primary key or throws a <code>NoSuchTalukaMasterException</code> if it could not be found.
	 *
	 * @param talukaId the primary key of the taluka master
	 * @return the taluka master
	 * @throws NoSuchTalukaMasterException if a taluka master with the primary key could not be found
	 */
	@Override
	public TalukaMaster findByPrimaryKey(long talukaId)
		throws NoSuchTalukaMasterException {

		return findByPrimaryKey((Serializable)talukaId);
	}

	/**
	 * Returns the taluka master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param talukaId the primary key of the taluka master
	 * @return the taluka master, or <code>null</code> if a taluka master with the primary key could not be found
	 */
	@Override
	public TalukaMaster fetchByPrimaryKey(long talukaId) {
		return fetchByPrimaryKey((Serializable)talukaId);
	}

	/**
	 * Returns all the taluka masters.
	 *
	 * @return the taluka masters
	 */
	@Override
	public List<TalukaMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the taluka masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @return the range of taluka masters
	 */
	@Override
	public List<TalukaMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the taluka masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of taluka masters
	 */
	@Override
	public List<TalukaMaster> findAll(
		int start, int end, OrderByComparator<TalukaMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the taluka masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TalukaMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of taluka masters
	 * @param end the upper bound of the range of taluka masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of taluka masters
	 */
	@Override
	public List<TalukaMaster> findAll(
		int start, int end, OrderByComparator<TalukaMaster> orderByComparator,
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

		List<TalukaMaster> list = null;

		if (useFinderCache) {
			list = (List<TalukaMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TALUKAMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TALUKAMASTER;

				sql = sql.concat(TalukaMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TalukaMaster>)QueryUtil.list(
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
	 * Removes all the taluka masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TalukaMaster talukaMaster : findAll()) {
			remove(talukaMaster);
		}
	}

	/**
	 * Returns the number of taluka masters.
	 *
	 * @return the number of taluka masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TALUKAMASTER);

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
		return "talukaId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TALUKAMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TalukaMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the taluka master persistence.
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

		_finderPathWithPaginationFindByDistrictId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDistrictId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"districtId"}, true);

		_finderPathWithoutPaginationFindByDistrictId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDistrictId",
			new String[] {Long.class.getName()}, new String[] {"districtId"},
			true);

		_finderPathCountByDistrictId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDistrictId",
			new String[] {Long.class.getName()}, new String[] {"districtId"},
			false);

		TalukaMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		TalukaMasterUtil.setPersistence(null);

		entityCache.removeCache(TalukaMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_TALUKAMASTER =
		"SELECT talukaMaster FROM TalukaMaster talukaMaster";

	private static final String _SQL_SELECT_TALUKAMASTER_WHERE =
		"SELECT talukaMaster FROM TalukaMaster talukaMaster WHERE ";

	private static final String _SQL_COUNT_TALUKAMASTER =
		"SELECT COUNT(talukaMaster) FROM TalukaMaster talukaMaster";

	private static final String _SQL_COUNT_TALUKAMASTER_WHERE =
		"SELECT COUNT(talukaMaster) FROM TalukaMaster talukaMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "talukaMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TalukaMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TalukaMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TalukaMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}