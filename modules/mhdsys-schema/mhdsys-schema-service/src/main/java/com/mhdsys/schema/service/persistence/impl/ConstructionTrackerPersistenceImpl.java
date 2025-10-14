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
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchConstructionTrackerException;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.model.ConstructionTrackerTable;
import com.mhdsys.schema.model.impl.ConstructionTrackerImpl;
import com.mhdsys.schema.model.impl.ConstructionTrackerModelImpl;
import com.mhdsys.schema.service.persistence.ConstructionTrackerPersistence;
import com.mhdsys.schema.service.persistence.ConstructionTrackerUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the construction tracker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ConstructionTrackerPersistence.class)
public class ConstructionTrackerPersistenceImpl
	extends BasePersistenceImpl<ConstructionTracker>
	implements ConstructionTrackerPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ConstructionTrackerUtil</code> to access the construction tracker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ConstructionTrackerImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByconstructionTrackerId;
	private FinderPath _finderPathCountByconstructionTrackerId;

	/**
	 * Returns the construction tracker where constructionTrackerId = &#63; or throws a <code>NoSuchConstructionTrackerException</code> if it could not be found.
	 *
	 * @param constructionTrackerId the construction tracker ID
	 * @return the matching construction tracker
	 * @throws NoSuchConstructionTrackerException if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker findByconstructionTrackerId(
			long constructionTrackerId)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = fetchByconstructionTrackerId(
			constructionTrackerId);

		if (constructionTracker == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("constructionTrackerId=");
			sb.append(constructionTrackerId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchConstructionTrackerException(sb.toString());
		}

		return constructionTracker;
	}

	/**
	 * Returns the construction tracker where constructionTrackerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param constructionTrackerId the construction tracker ID
	 * @return the matching construction tracker, or <code>null</code> if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker fetchByconstructionTrackerId(
		long constructionTrackerId) {

		return fetchByconstructionTrackerId(constructionTrackerId, true);
	}

	/**
	 * Returns the construction tracker where constructionTrackerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param constructionTrackerId the construction tracker ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching construction tracker, or <code>null</code> if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker fetchByconstructionTrackerId(
		long constructionTrackerId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {constructionTrackerId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByconstructionTrackerId, finderArgs, this);
		}

		if (result instanceof ConstructionTracker) {
			ConstructionTracker constructionTracker =
				(ConstructionTracker)result;

			if (constructionTrackerId !=
					constructionTracker.getConstructionTrackerId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_CONSTRUCTIONTRACKER_WHERE);

			sb.append(
				_FINDER_COLUMN_CONSTRUCTIONTRACKERID_CONSTRUCTIONTRACKERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(constructionTrackerId);

				List<ConstructionTracker> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByconstructionTrackerId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									constructionTrackerId
								};
							}

							_log.warn(
								"ConstructionTrackerPersistenceImpl.fetchByconstructionTrackerId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ConstructionTracker constructionTracker = list.get(0);

					result = constructionTracker;

					cacheResult(constructionTracker);
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
			return (ConstructionTracker)result;
		}
	}

	/**
	 * Removes the construction tracker where constructionTrackerId = &#63; from the database.
	 *
	 * @param constructionTrackerId the construction tracker ID
	 * @return the construction tracker that was removed
	 */
	@Override
	public ConstructionTracker removeByconstructionTrackerId(
			long constructionTrackerId)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = findByconstructionTrackerId(
			constructionTrackerId);

		return remove(constructionTracker);
	}

	/**
	 * Returns the number of construction trackers where constructionTrackerId = &#63;.
	 *
	 * @param constructionTrackerId the construction tracker ID
	 * @return the number of matching construction trackers
	 */
	@Override
	public int countByconstructionTrackerId(long constructionTrackerId) {
		FinderPath finderPath = _finderPathCountByconstructionTrackerId;

		Object[] finderArgs = new Object[] {constructionTrackerId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONSTRUCTIONTRACKER_WHERE);

			sb.append(
				_FINDER_COLUMN_CONSTRUCTIONTRACKERID_CONSTRUCTIONTRACKERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(constructionTrackerId);

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

	private static final String
		_FINDER_COLUMN_CONSTRUCTIONTRACKERID_CONSTRUCTIONTRACKERID_2 =
			"constructionTracker.constructionTrackerId = ?";

	private FinderPath _finderPathWithPaginationFindByholdWithUserId;
	private FinderPath _finderPathWithoutPaginationFindByholdWithUserId;
	private FinderPath _finderPathCountByholdWithUserId;

	/**
	 * Returns all the construction trackers where holdWithUserId = &#63;.
	 *
	 * @param holdWithUserId the hold with user ID
	 * @return the matching construction trackers
	 */
	@Override
	public List<ConstructionTracker> findByholdWithUserId(long holdWithUserId) {
		return findByholdWithUserId(
			holdWithUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the construction trackers where holdWithUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param holdWithUserId the hold with user ID
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @return the range of matching construction trackers
	 */
	@Override
	public List<ConstructionTracker> findByholdWithUserId(
		long holdWithUserId, int start, int end) {

		return findByholdWithUserId(holdWithUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the construction trackers where holdWithUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param holdWithUserId the hold with user ID
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching construction trackers
	 */
	@Override
	public List<ConstructionTracker> findByholdWithUserId(
		long holdWithUserId, int start, int end,
		OrderByComparator<ConstructionTracker> orderByComparator) {

		return findByholdWithUserId(
			holdWithUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the construction trackers where holdWithUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param holdWithUserId the hold with user ID
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching construction trackers
	 */
	@Override
	public List<ConstructionTracker> findByholdWithUserId(
		long holdWithUserId, int start, int end,
		OrderByComparator<ConstructionTracker> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByholdWithUserId;
				finderArgs = new Object[] {holdWithUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByholdWithUserId;
			finderArgs = new Object[] {
				holdWithUserId, start, end, orderByComparator
			};
		}

		List<ConstructionTracker> list = null;

		if (useFinderCache) {
			list = (List<ConstructionTracker>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ConstructionTracker constructionTracker : list) {
					if (holdWithUserId !=
							constructionTracker.getHoldWithUserId()) {

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

			sb.append(_SQL_SELECT_CONSTRUCTIONTRACKER_WHERE);

			sb.append(_FINDER_COLUMN_HOLDWITHUSERID_HOLDWITHUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ConstructionTrackerModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(holdWithUserId);

				list = (List<ConstructionTracker>)QueryUtil.list(
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
	 * Returns the first construction tracker in the ordered set where holdWithUserId = &#63;.
	 *
	 * @param holdWithUserId the hold with user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching construction tracker
	 * @throws NoSuchConstructionTrackerException if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker findByholdWithUserId_First(
			long holdWithUserId,
			OrderByComparator<ConstructionTracker> orderByComparator)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = fetchByholdWithUserId_First(
			holdWithUserId, orderByComparator);

		if (constructionTracker != null) {
			return constructionTracker;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("holdWithUserId=");
		sb.append(holdWithUserId);

		sb.append("}");

		throw new NoSuchConstructionTrackerException(sb.toString());
	}

	/**
	 * Returns the first construction tracker in the ordered set where holdWithUserId = &#63;.
	 *
	 * @param holdWithUserId the hold with user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching construction tracker, or <code>null</code> if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker fetchByholdWithUserId_First(
		long holdWithUserId,
		OrderByComparator<ConstructionTracker> orderByComparator) {

		List<ConstructionTracker> list = findByholdWithUserId(
			holdWithUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last construction tracker in the ordered set where holdWithUserId = &#63;.
	 *
	 * @param holdWithUserId the hold with user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching construction tracker
	 * @throws NoSuchConstructionTrackerException if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker findByholdWithUserId_Last(
			long holdWithUserId,
			OrderByComparator<ConstructionTracker> orderByComparator)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = fetchByholdWithUserId_Last(
			holdWithUserId, orderByComparator);

		if (constructionTracker != null) {
			return constructionTracker;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("holdWithUserId=");
		sb.append(holdWithUserId);

		sb.append("}");

		throw new NoSuchConstructionTrackerException(sb.toString());
	}

	/**
	 * Returns the last construction tracker in the ordered set where holdWithUserId = &#63;.
	 *
	 * @param holdWithUserId the hold with user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching construction tracker, or <code>null</code> if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker fetchByholdWithUserId_Last(
		long holdWithUserId,
		OrderByComparator<ConstructionTracker> orderByComparator) {

		int count = countByholdWithUserId(holdWithUserId);

		if (count == 0) {
			return null;
		}

		List<ConstructionTracker> list = findByholdWithUserId(
			holdWithUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the construction trackers before and after the current construction tracker in the ordered set where holdWithUserId = &#63;.
	 *
	 * @param constructionTrackerId the primary key of the current construction tracker
	 * @param holdWithUserId the hold with user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next construction tracker
	 * @throws NoSuchConstructionTrackerException if a construction tracker with the primary key could not be found
	 */
	@Override
	public ConstructionTracker[] findByholdWithUserId_PrevAndNext(
			long constructionTrackerId, long holdWithUserId,
			OrderByComparator<ConstructionTracker> orderByComparator)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = findByPrimaryKey(
			constructionTrackerId);

		Session session = null;

		try {
			session = openSession();

			ConstructionTracker[] array = new ConstructionTrackerImpl[3];

			array[0] = getByholdWithUserId_PrevAndNext(
				session, constructionTracker, holdWithUserId, orderByComparator,
				true);

			array[1] = constructionTracker;

			array[2] = getByholdWithUserId_PrevAndNext(
				session, constructionTracker, holdWithUserId, orderByComparator,
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

	protected ConstructionTracker getByholdWithUserId_PrevAndNext(
		Session session, ConstructionTracker constructionTracker,
		long holdWithUserId,
		OrderByComparator<ConstructionTracker> orderByComparator,
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

		sb.append(_SQL_SELECT_CONSTRUCTIONTRACKER_WHERE);

		sb.append(_FINDER_COLUMN_HOLDWITHUSERID_HOLDWITHUSERID_2);

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
			sb.append(ConstructionTrackerModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(holdWithUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						constructionTracker)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ConstructionTracker> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the construction trackers where holdWithUserId = &#63; from the database.
	 *
	 * @param holdWithUserId the hold with user ID
	 */
	@Override
	public void removeByholdWithUserId(long holdWithUserId) {
		for (ConstructionTracker constructionTracker :
				findByholdWithUserId(
					holdWithUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(constructionTracker);
		}
	}

	/**
	 * Returns the number of construction trackers where holdWithUserId = &#63;.
	 *
	 * @param holdWithUserId the hold with user ID
	 * @return the number of matching construction trackers
	 */
	@Override
	public int countByholdWithUserId(long holdWithUserId) {
		FinderPath finderPath = _finderPathCountByholdWithUserId;

		Object[] finderArgs = new Object[] {holdWithUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONSTRUCTIONTRACKER_WHERE);

			sb.append(_FINDER_COLUMN_HOLDWITHUSERID_HOLDWITHUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(holdWithUserId);

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

	private static final String _FINDER_COLUMN_HOLDWITHUSERID_HOLDWITHUSERID_2 =
		"constructionTracker.holdWithUserId = ?";

	private FinderPath _finderPathWithPaginationFindByapplicationStatus;
	private FinderPath _finderPathWithoutPaginationFindByapplicationStatus;
	private FinderPath _finderPathCountByapplicationStatus;

	/**
	 * Returns all the construction trackers where applicationStatus = &#63;.
	 *
	 * @param applicationStatus the application status
	 * @return the matching construction trackers
	 */
	@Override
	public List<ConstructionTracker> findByapplicationStatus(
		long applicationStatus) {

		return findByapplicationStatus(
			applicationStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the construction trackers where applicationStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param applicationStatus the application status
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @return the range of matching construction trackers
	 */
	@Override
	public List<ConstructionTracker> findByapplicationStatus(
		long applicationStatus, int start, int end) {

		return findByapplicationStatus(applicationStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the construction trackers where applicationStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param applicationStatus the application status
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching construction trackers
	 */
	@Override
	public List<ConstructionTracker> findByapplicationStatus(
		long applicationStatus, int start, int end,
		OrderByComparator<ConstructionTracker> orderByComparator) {

		return findByapplicationStatus(
			applicationStatus, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the construction trackers where applicationStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param applicationStatus the application status
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching construction trackers
	 */
	@Override
	public List<ConstructionTracker> findByapplicationStatus(
		long applicationStatus, int start, int end,
		OrderByComparator<ConstructionTracker> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByapplicationStatus;
				finderArgs = new Object[] {applicationStatus};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByapplicationStatus;
			finderArgs = new Object[] {
				applicationStatus, start, end, orderByComparator
			};
		}

		List<ConstructionTracker> list = null;

		if (useFinderCache) {
			list = (List<ConstructionTracker>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ConstructionTracker constructionTracker : list) {
					if (applicationStatus !=
							constructionTracker.getApplicationStatus()) {

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

			sb.append(_SQL_SELECT_CONSTRUCTIONTRACKER_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONSTATUS_APPLICATIONSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ConstructionTrackerModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationStatus);

				list = (List<ConstructionTracker>)QueryUtil.list(
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
	 * Returns the first construction tracker in the ordered set where applicationStatus = &#63;.
	 *
	 * @param applicationStatus the application status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching construction tracker
	 * @throws NoSuchConstructionTrackerException if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker findByapplicationStatus_First(
			long applicationStatus,
			OrderByComparator<ConstructionTracker> orderByComparator)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker =
			fetchByapplicationStatus_First(
				applicationStatus, orderByComparator);

		if (constructionTracker != null) {
			return constructionTracker;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationStatus=");
		sb.append(applicationStatus);

		sb.append("}");

		throw new NoSuchConstructionTrackerException(sb.toString());
	}

	/**
	 * Returns the first construction tracker in the ordered set where applicationStatus = &#63;.
	 *
	 * @param applicationStatus the application status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching construction tracker, or <code>null</code> if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker fetchByapplicationStatus_First(
		long applicationStatus,
		OrderByComparator<ConstructionTracker> orderByComparator) {

		List<ConstructionTracker> list = findByapplicationStatus(
			applicationStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last construction tracker in the ordered set where applicationStatus = &#63;.
	 *
	 * @param applicationStatus the application status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching construction tracker
	 * @throws NoSuchConstructionTrackerException if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker findByapplicationStatus_Last(
			long applicationStatus,
			OrderByComparator<ConstructionTracker> orderByComparator)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = fetchByapplicationStatus_Last(
			applicationStatus, orderByComparator);

		if (constructionTracker != null) {
			return constructionTracker;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("applicationStatus=");
		sb.append(applicationStatus);

		sb.append("}");

		throw new NoSuchConstructionTrackerException(sb.toString());
	}

	/**
	 * Returns the last construction tracker in the ordered set where applicationStatus = &#63;.
	 *
	 * @param applicationStatus the application status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching construction tracker, or <code>null</code> if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker fetchByapplicationStatus_Last(
		long applicationStatus,
		OrderByComparator<ConstructionTracker> orderByComparator) {

		int count = countByapplicationStatus(applicationStatus);

		if (count == 0) {
			return null;
		}

		List<ConstructionTracker> list = findByapplicationStatus(
			applicationStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the construction trackers before and after the current construction tracker in the ordered set where applicationStatus = &#63;.
	 *
	 * @param constructionTrackerId the primary key of the current construction tracker
	 * @param applicationStatus the application status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next construction tracker
	 * @throws NoSuchConstructionTrackerException if a construction tracker with the primary key could not be found
	 */
	@Override
	public ConstructionTracker[] findByapplicationStatus_PrevAndNext(
			long constructionTrackerId, long applicationStatus,
			OrderByComparator<ConstructionTracker> orderByComparator)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = findByPrimaryKey(
			constructionTrackerId);

		Session session = null;

		try {
			session = openSession();

			ConstructionTracker[] array = new ConstructionTrackerImpl[3];

			array[0] = getByapplicationStatus_PrevAndNext(
				session, constructionTracker, applicationStatus,
				orderByComparator, true);

			array[1] = constructionTracker;

			array[2] = getByapplicationStatus_PrevAndNext(
				session, constructionTracker, applicationStatus,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ConstructionTracker getByapplicationStatus_PrevAndNext(
		Session session, ConstructionTracker constructionTracker,
		long applicationStatus,
		OrderByComparator<ConstructionTracker> orderByComparator,
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

		sb.append(_SQL_SELECT_CONSTRUCTIONTRACKER_WHERE);

		sb.append(_FINDER_COLUMN_APPLICATIONSTATUS_APPLICATIONSTATUS_2);

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
			sb.append(ConstructionTrackerModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(applicationStatus);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						constructionTracker)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ConstructionTracker> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the construction trackers where applicationStatus = &#63; from the database.
	 *
	 * @param applicationStatus the application status
	 */
	@Override
	public void removeByapplicationStatus(long applicationStatus) {
		for (ConstructionTracker constructionTracker :
				findByapplicationStatus(
					applicationStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(constructionTracker);
		}
	}

	/**
	 * Returns the number of construction trackers where applicationStatus = &#63;.
	 *
	 * @param applicationStatus the application status
	 * @return the number of matching construction trackers
	 */
	@Override
	public int countByapplicationStatus(long applicationStatus) {
		FinderPath finderPath = _finderPathCountByapplicationStatus;

		Object[] finderArgs = new Object[] {applicationStatus};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONSTRUCTIONTRACKER_WHERE);

			sb.append(_FINDER_COLUMN_APPLICATIONSTATUS_APPLICATIONSTATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(applicationStatus);

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

	private static final String
		_FINDER_COLUMN_APPLICATIONSTATUS_APPLICATIONSTATUS_2 =
			"constructionTracker.applicationStatus = ?";

	private FinderPath _finderPathFetchByIntiatorUserId;
	private FinderPath _finderPathCountByIntiatorUserId;

	/**
	 * Returns the construction tracker where IntiatorUserId = &#63; or throws a <code>NoSuchConstructionTrackerException</code> if it could not be found.
	 *
	 * @param IntiatorUserId the intiator user ID
	 * @return the matching construction tracker
	 * @throws NoSuchConstructionTrackerException if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker findByIntiatorUserId(long IntiatorUserId)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = fetchByIntiatorUserId(
			IntiatorUserId);

		if (constructionTracker == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("IntiatorUserId=");
			sb.append(IntiatorUserId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchConstructionTrackerException(sb.toString());
		}

		return constructionTracker;
	}

	/**
	 * Returns the construction tracker where IntiatorUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param IntiatorUserId the intiator user ID
	 * @return the matching construction tracker, or <code>null</code> if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker fetchByIntiatorUserId(long IntiatorUserId) {
		return fetchByIntiatorUserId(IntiatorUserId, true);
	}

	/**
	 * Returns the construction tracker where IntiatorUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param IntiatorUserId the intiator user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching construction tracker, or <code>null</code> if a matching construction tracker could not be found
	 */
	@Override
	public ConstructionTracker fetchByIntiatorUserId(
		long IntiatorUserId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {IntiatorUserId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByIntiatorUserId, finderArgs, this);
		}

		if (result instanceof ConstructionTracker) {
			ConstructionTracker constructionTracker =
				(ConstructionTracker)result;

			if (IntiatorUserId != constructionTracker.getIntiatorUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_CONSTRUCTIONTRACKER_WHERE);

			sb.append(_FINDER_COLUMN_INTIATORUSERID_INTIATORUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(IntiatorUserId);

				List<ConstructionTracker> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByIntiatorUserId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {IntiatorUserId};
							}

							_log.warn(
								"ConstructionTrackerPersistenceImpl.fetchByIntiatorUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ConstructionTracker constructionTracker = list.get(0);

					result = constructionTracker;

					cacheResult(constructionTracker);
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
			return (ConstructionTracker)result;
		}
	}

	/**
	 * Removes the construction tracker where IntiatorUserId = &#63; from the database.
	 *
	 * @param IntiatorUserId the intiator user ID
	 * @return the construction tracker that was removed
	 */
	@Override
	public ConstructionTracker removeByIntiatorUserId(long IntiatorUserId)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = findByIntiatorUserId(
			IntiatorUserId);

		return remove(constructionTracker);
	}

	/**
	 * Returns the number of construction trackers where IntiatorUserId = &#63;.
	 *
	 * @param IntiatorUserId the intiator user ID
	 * @return the number of matching construction trackers
	 */
	@Override
	public int countByIntiatorUserId(long IntiatorUserId) {
		FinderPath finderPath = _finderPathCountByIntiatorUserId;

		Object[] finderArgs = new Object[] {IntiatorUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONSTRUCTIONTRACKER_WHERE);

			sb.append(_FINDER_COLUMN_INTIATORUSERID_INTIATORUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(IntiatorUserId);

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

	private static final String _FINDER_COLUMN_INTIATORUSERID_INTIATORUSERID_2 =
		"constructionTracker.IntiatorUserId = ?";

	public ConstructionTrackerPersistenceImpl() {
		setModelClass(ConstructionTracker.class);

		setModelImplClass(ConstructionTrackerImpl.class);
		setModelPKClass(long.class);

		setTable(ConstructionTrackerTable.INSTANCE);
	}

	/**
	 * Caches the construction tracker in the entity cache if it is enabled.
	 *
	 * @param constructionTracker the construction tracker
	 */
	@Override
	public void cacheResult(ConstructionTracker constructionTracker) {
		entityCache.putResult(
			ConstructionTrackerImpl.class, constructionTracker.getPrimaryKey(),
			constructionTracker);

		finderCache.putResult(
			_finderPathFetchByconstructionTrackerId,
			new Object[] {constructionTracker.getConstructionTrackerId()},
			constructionTracker);

		finderCache.putResult(
			_finderPathFetchByIntiatorUserId,
			new Object[] {constructionTracker.getIntiatorUserId()},
			constructionTracker);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the construction trackers in the entity cache if it is enabled.
	 *
	 * @param constructionTrackers the construction trackers
	 */
	@Override
	public void cacheResult(List<ConstructionTracker> constructionTrackers) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (constructionTrackers.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ConstructionTracker constructionTracker : constructionTrackers) {
			if (entityCache.getResult(
					ConstructionTrackerImpl.class,
					constructionTracker.getPrimaryKey()) == null) {

				cacheResult(constructionTracker);
			}
		}
	}

	/**
	 * Clears the cache for all construction trackers.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ConstructionTrackerImpl.class);

		finderCache.clearCache(ConstructionTrackerImpl.class);
	}

	/**
	 * Clears the cache for the construction tracker.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ConstructionTracker constructionTracker) {
		entityCache.removeResult(
			ConstructionTrackerImpl.class, constructionTracker);
	}

	@Override
	public void clearCache(List<ConstructionTracker> constructionTrackers) {
		for (ConstructionTracker constructionTracker : constructionTrackers) {
			entityCache.removeResult(
				ConstructionTrackerImpl.class, constructionTracker);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ConstructionTrackerImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ConstructionTrackerImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ConstructionTrackerModelImpl constructionTrackerModelImpl) {

		Object[] args = new Object[] {
			constructionTrackerModelImpl.getConstructionTrackerId()
		};

		finderCache.putResult(
			_finderPathCountByconstructionTrackerId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByconstructionTrackerId, args,
			constructionTrackerModelImpl);

		args = new Object[] {constructionTrackerModelImpl.getIntiatorUserId()};

		finderCache.putResult(
			_finderPathCountByIntiatorUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByIntiatorUserId, args,
			constructionTrackerModelImpl);
	}

	/**
	 * Creates a new construction tracker with the primary key. Does not add the construction tracker to the database.
	 *
	 * @param constructionTrackerId the primary key for the new construction tracker
	 * @return the new construction tracker
	 */
	@Override
	public ConstructionTracker create(long constructionTrackerId) {
		ConstructionTracker constructionTracker = new ConstructionTrackerImpl();

		constructionTracker.setNew(true);
		constructionTracker.setPrimaryKey(constructionTrackerId);

		return constructionTracker;
	}

	/**
	 * Removes the construction tracker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param constructionTrackerId the primary key of the construction tracker
	 * @return the construction tracker that was removed
	 * @throws NoSuchConstructionTrackerException if a construction tracker with the primary key could not be found
	 */
	@Override
	public ConstructionTracker remove(long constructionTrackerId)
		throws NoSuchConstructionTrackerException {

		return remove((Serializable)constructionTrackerId);
	}

	/**
	 * Removes the construction tracker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the construction tracker
	 * @return the construction tracker that was removed
	 * @throws NoSuchConstructionTrackerException if a construction tracker with the primary key could not be found
	 */
	@Override
	public ConstructionTracker remove(Serializable primaryKey)
		throws NoSuchConstructionTrackerException {

		Session session = null;

		try {
			session = openSession();

			ConstructionTracker constructionTracker =
				(ConstructionTracker)session.get(
					ConstructionTrackerImpl.class, primaryKey);

			if (constructionTracker == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConstructionTrackerException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(constructionTracker);
		}
		catch (NoSuchConstructionTrackerException noSuchEntityException) {
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
	protected ConstructionTracker removeImpl(
		ConstructionTracker constructionTracker) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(constructionTracker)) {
				constructionTracker = (ConstructionTracker)session.get(
					ConstructionTrackerImpl.class,
					constructionTracker.getPrimaryKeyObj());
			}

			if (constructionTracker != null) {
				session.delete(constructionTracker);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (constructionTracker != null) {
			clearCache(constructionTracker);
		}

		return constructionTracker;
	}

	@Override
	public ConstructionTracker updateImpl(
		ConstructionTracker constructionTracker) {

		boolean isNew = constructionTracker.isNew();

		if (!(constructionTracker instanceof ConstructionTrackerModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(constructionTracker.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					constructionTracker);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in constructionTracker proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ConstructionTracker implementation " +
					constructionTracker.getClass());
		}

		ConstructionTrackerModelImpl constructionTrackerModelImpl =
			(ConstructionTrackerModelImpl)constructionTracker;

		if (!constructionTrackerModelImpl.hasSetModifiedDate()) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				constructionTracker.setModifiedDate(date);
			}
			else {
				constructionTracker.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(constructionTracker);
			}
			else {
				constructionTracker = (ConstructionTracker)session.merge(
					constructionTracker);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ConstructionTrackerImpl.class, constructionTrackerModelImpl, false,
			true);

		cacheUniqueFindersCache(constructionTrackerModelImpl);

		if (isNew) {
			constructionTracker.setNew(false);
		}

		constructionTracker.resetOriginalValues();

		return constructionTracker;
	}

	/**
	 * Returns the construction tracker with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the construction tracker
	 * @return the construction tracker
	 * @throws NoSuchConstructionTrackerException if a construction tracker with the primary key could not be found
	 */
	@Override
	public ConstructionTracker findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConstructionTrackerException {

		ConstructionTracker constructionTracker = fetchByPrimaryKey(primaryKey);

		if (constructionTracker == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConstructionTrackerException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return constructionTracker;
	}

	/**
	 * Returns the construction tracker with the primary key or throws a <code>NoSuchConstructionTrackerException</code> if it could not be found.
	 *
	 * @param constructionTrackerId the primary key of the construction tracker
	 * @return the construction tracker
	 * @throws NoSuchConstructionTrackerException if a construction tracker with the primary key could not be found
	 */
	@Override
	public ConstructionTracker findByPrimaryKey(long constructionTrackerId)
		throws NoSuchConstructionTrackerException {

		return findByPrimaryKey((Serializable)constructionTrackerId);
	}

	/**
	 * Returns the construction tracker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param constructionTrackerId the primary key of the construction tracker
	 * @return the construction tracker, or <code>null</code> if a construction tracker with the primary key could not be found
	 */
	@Override
	public ConstructionTracker fetchByPrimaryKey(long constructionTrackerId) {
		return fetchByPrimaryKey((Serializable)constructionTrackerId);
	}

	/**
	 * Returns all the construction trackers.
	 *
	 * @return the construction trackers
	 */
	@Override
	public List<ConstructionTracker> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the construction trackers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @return the range of construction trackers
	 */
	@Override
	public List<ConstructionTracker> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the construction trackers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of construction trackers
	 */
	@Override
	public List<ConstructionTracker> findAll(
		int start, int end,
		OrderByComparator<ConstructionTracker> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the construction trackers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ConstructionTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of construction trackers
	 * @param end the upper bound of the range of construction trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of construction trackers
	 */
	@Override
	public List<ConstructionTracker> findAll(
		int start, int end,
		OrderByComparator<ConstructionTracker> orderByComparator,
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

		List<ConstructionTracker> list = null;

		if (useFinderCache) {
			list = (List<ConstructionTracker>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CONSTRUCTIONTRACKER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CONSTRUCTIONTRACKER;

				sql = sql.concat(ConstructionTrackerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ConstructionTracker>)QueryUtil.list(
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
	 * Removes all the construction trackers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ConstructionTracker constructionTracker : findAll()) {
			remove(constructionTracker);
		}
	}

	/**
	 * Returns the number of construction trackers.
	 *
	 * @return the number of construction trackers
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
					_SQL_COUNT_CONSTRUCTIONTRACKER);

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
		return "constructionTrackerId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CONSTRUCTIONTRACKER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ConstructionTrackerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the construction tracker persistence.
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

		_finderPathFetchByconstructionTrackerId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByconstructionTrackerId",
			new String[] {Long.class.getName()},
			new String[] {"constructionTrackerId"}, true);

		_finderPathCountByconstructionTrackerId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByconstructionTrackerId", new String[] {Long.class.getName()},
			new String[] {"constructionTrackerId"}, false);

		_finderPathWithPaginationFindByholdWithUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByholdWithUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"holdWithUserId"}, true);

		_finderPathWithoutPaginationFindByholdWithUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByholdWithUserId",
			new String[] {Long.class.getName()},
			new String[] {"holdWithUserId"}, true);

		_finderPathCountByholdWithUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByholdWithUserId",
			new String[] {Long.class.getName()},
			new String[] {"holdWithUserId"}, false);

		_finderPathWithPaginationFindByapplicationStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByapplicationStatus",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"applicationStatus"}, true);

		_finderPathWithoutPaginationFindByapplicationStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByapplicationStatus", new String[] {Long.class.getName()},
			new String[] {"applicationStatus"}, true);

		_finderPathCountByapplicationStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByapplicationStatus", new String[] {Long.class.getName()},
			new String[] {"applicationStatus"}, false);

		_finderPathFetchByIntiatorUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByIntiatorUserId",
			new String[] {Long.class.getName()},
			new String[] {"IntiatorUserId"}, true);

		_finderPathCountByIntiatorUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIntiatorUserId",
			new String[] {Long.class.getName()},
			new String[] {"IntiatorUserId"}, false);

		ConstructionTrackerUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ConstructionTrackerUtil.setPersistence(null);

		entityCache.removeCache(ConstructionTrackerImpl.class.getName());
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

	private static final String _SQL_SELECT_CONSTRUCTIONTRACKER =
		"SELECT constructionTracker FROM ConstructionTracker constructionTracker";

	private static final String _SQL_SELECT_CONSTRUCTIONTRACKER_WHERE =
		"SELECT constructionTracker FROM ConstructionTracker constructionTracker WHERE ";

	private static final String _SQL_COUNT_CONSTRUCTIONTRACKER =
		"SELECT COUNT(constructionTracker) FROM ConstructionTracker constructionTracker";

	private static final String _SQL_COUNT_CONSTRUCTIONTRACKER_WHERE =
		"SELECT COUNT(constructionTracker) FROM ConstructionTracker constructionTracker WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "constructionTracker.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ConstructionTracker exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ConstructionTracker exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ConstructionTrackerPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}