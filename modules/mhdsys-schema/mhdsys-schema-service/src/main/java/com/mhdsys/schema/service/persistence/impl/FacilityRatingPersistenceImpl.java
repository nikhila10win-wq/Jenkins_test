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
import com.liferay.portal.kernel.util.StringUtil;

import com.mhdsys.schema.exception.NoSuchFacilityRatingException;
import com.mhdsys.schema.model.FacilityRating;
import com.mhdsys.schema.model.FacilityRatingTable;
import com.mhdsys.schema.model.impl.FacilityRatingImpl;
import com.mhdsys.schema.model.impl.FacilityRatingModelImpl;
import com.mhdsys.schema.service.persistence.FacilityRatingPersistence;
import com.mhdsys.schema.service.persistence.FacilityRatingUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
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
 * The persistence implementation for the facility rating service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = FacilityRatingPersistence.class)
public class FacilityRatingPersistenceImpl
	extends BasePersistenceImpl<FacilityRating>
	implements FacilityRatingPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FacilityRatingUtil</code> to access the facility rating persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FacilityRatingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByratingUniqueId;
	private FinderPath _finderPathCountByratingUniqueId;

	/**
	 * Returns the facility rating where ratingUniqueId = &#63; or throws a <code>NoSuchFacilityRatingException</code> if it could not be found.
	 *
	 * @param ratingUniqueId the rating unique ID
	 * @return the matching facility rating
	 * @throws NoSuchFacilityRatingException if a matching facility rating could not be found
	 */
	@Override
	public FacilityRating findByratingUniqueId(String ratingUniqueId)
		throws NoSuchFacilityRatingException {

		FacilityRating facilityRating = fetchByratingUniqueId(ratingUniqueId);

		if (facilityRating == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("ratingUniqueId=");
			sb.append(ratingUniqueId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFacilityRatingException(sb.toString());
		}

		return facilityRating;
	}

	/**
	 * Returns the facility rating where ratingUniqueId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ratingUniqueId the rating unique ID
	 * @return the matching facility rating, or <code>null</code> if a matching facility rating could not be found
	 */
	@Override
	public FacilityRating fetchByratingUniqueId(String ratingUniqueId) {
		return fetchByratingUniqueId(ratingUniqueId, true);
	}

	/**
	 * Returns the facility rating where ratingUniqueId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ratingUniqueId the rating unique ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching facility rating, or <code>null</code> if a matching facility rating could not be found
	 */
	@Override
	public FacilityRating fetchByratingUniqueId(
		String ratingUniqueId, boolean useFinderCache) {

		ratingUniqueId = Objects.toString(ratingUniqueId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {ratingUniqueId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByratingUniqueId, finderArgs, this);
		}

		if (result instanceof FacilityRating) {
			FacilityRating facilityRating = (FacilityRating)result;

			if (!Objects.equals(
					ratingUniqueId, facilityRating.getRatingUniqueId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_FACILITYRATING_WHERE);

			boolean bindRatingUniqueId = false;

			if (ratingUniqueId.isEmpty()) {
				sb.append(_FINDER_COLUMN_RATINGUNIQUEID_RATINGUNIQUEID_3);
			}
			else {
				bindRatingUniqueId = true;

				sb.append(_FINDER_COLUMN_RATINGUNIQUEID_RATINGUNIQUEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindRatingUniqueId) {
					queryPos.add(ratingUniqueId);
				}

				List<FacilityRating> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByratingUniqueId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {ratingUniqueId};
							}

							_log.warn(
								"FacilityRatingPersistenceImpl.fetchByratingUniqueId(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					FacilityRating facilityRating = list.get(0);

					result = facilityRating;

					cacheResult(facilityRating);
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
			return (FacilityRating)result;
		}
	}

	/**
	 * Removes the facility rating where ratingUniqueId = &#63; from the database.
	 *
	 * @param ratingUniqueId the rating unique ID
	 * @return the facility rating that was removed
	 */
	@Override
	public FacilityRating removeByratingUniqueId(String ratingUniqueId)
		throws NoSuchFacilityRatingException {

		FacilityRating facilityRating = findByratingUniqueId(ratingUniqueId);

		return remove(facilityRating);
	}

	/**
	 * Returns the number of facility ratings where ratingUniqueId = &#63;.
	 *
	 * @param ratingUniqueId the rating unique ID
	 * @return the number of matching facility ratings
	 */
	@Override
	public int countByratingUniqueId(String ratingUniqueId) {
		ratingUniqueId = Objects.toString(ratingUniqueId, "");

		FinderPath finderPath = _finderPathCountByratingUniqueId;

		Object[] finderArgs = new Object[] {ratingUniqueId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FACILITYRATING_WHERE);

			boolean bindRatingUniqueId = false;

			if (ratingUniqueId.isEmpty()) {
				sb.append(_FINDER_COLUMN_RATINGUNIQUEID_RATINGUNIQUEID_3);
			}
			else {
				bindRatingUniqueId = true;

				sb.append(_FINDER_COLUMN_RATINGUNIQUEID_RATINGUNIQUEID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindRatingUniqueId) {
					queryPos.add(ratingUniqueId);
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

	private static final String _FINDER_COLUMN_RATINGUNIQUEID_RATINGUNIQUEID_2 =
		"facilityRating.ratingUniqueId = ?";

	private static final String _FINDER_COLUMN_RATINGUNIQUEID_RATINGUNIQUEID_3 =
		"(facilityRating.ratingUniqueId IS NULL OR facilityRating.ratingUniqueId = '')";

	private FinderPath _finderPathWithPaginationFindBycreatorUserId;
	private FinderPath _finderPathWithoutPaginationFindBycreatorUserId;
	private FinderPath _finderPathCountBycreatorUserId;

	/**
	 * Returns all the facility ratings where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @return the matching facility ratings
	 */
	@Override
	public List<FacilityRating> findBycreatorUserId(long creatorUserId) {
		return findBycreatorUserId(
			creatorUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the facility ratings where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FacilityRatingModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of facility ratings
	 * @param end the upper bound of the range of facility ratings (not inclusive)
	 * @return the range of matching facility ratings
	 */
	@Override
	public List<FacilityRating> findBycreatorUserId(
		long creatorUserId, int start, int end) {

		return findBycreatorUserId(creatorUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the facility ratings where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FacilityRatingModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of facility ratings
	 * @param end the upper bound of the range of facility ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching facility ratings
	 */
	@Override
	public List<FacilityRating> findBycreatorUserId(
		long creatorUserId, int start, int end,
		OrderByComparator<FacilityRating> orderByComparator) {

		return findBycreatorUserId(
			creatorUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the facility ratings where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FacilityRatingModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of facility ratings
	 * @param end the upper bound of the range of facility ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching facility ratings
	 */
	@Override
	public List<FacilityRating> findBycreatorUserId(
		long creatorUserId, int start, int end,
		OrderByComparator<FacilityRating> orderByComparator,
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

		List<FacilityRating> list = null;

		if (useFinderCache) {
			list = (List<FacilityRating>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FacilityRating facilityRating : list) {
					if (creatorUserId != facilityRating.getCreatorUserId()) {
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

			sb.append(_SQL_SELECT_FACILITYRATING_WHERE);

			sb.append(_FINDER_COLUMN_CREATORUSERID_CREATORUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FacilityRatingModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(creatorUserId);

				list = (List<FacilityRating>)QueryUtil.list(
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
	 * Returns the first facility rating in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching facility rating
	 * @throws NoSuchFacilityRatingException if a matching facility rating could not be found
	 */
	@Override
	public FacilityRating findBycreatorUserId_First(
			long creatorUserId,
			OrderByComparator<FacilityRating> orderByComparator)
		throws NoSuchFacilityRatingException {

		FacilityRating facilityRating = fetchBycreatorUserId_First(
			creatorUserId, orderByComparator);

		if (facilityRating != null) {
			return facilityRating;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("creatorUserId=");
		sb.append(creatorUserId);

		sb.append("}");

		throw new NoSuchFacilityRatingException(sb.toString());
	}

	/**
	 * Returns the first facility rating in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching facility rating, or <code>null</code> if a matching facility rating could not be found
	 */
	@Override
	public FacilityRating fetchBycreatorUserId_First(
		long creatorUserId,
		OrderByComparator<FacilityRating> orderByComparator) {

		List<FacilityRating> list = findBycreatorUserId(
			creatorUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last facility rating in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching facility rating
	 * @throws NoSuchFacilityRatingException if a matching facility rating could not be found
	 */
	@Override
	public FacilityRating findBycreatorUserId_Last(
			long creatorUserId,
			OrderByComparator<FacilityRating> orderByComparator)
		throws NoSuchFacilityRatingException {

		FacilityRating facilityRating = fetchBycreatorUserId_Last(
			creatorUserId, orderByComparator);

		if (facilityRating != null) {
			return facilityRating;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("creatorUserId=");
		sb.append(creatorUserId);

		sb.append("}");

		throw new NoSuchFacilityRatingException(sb.toString());
	}

	/**
	 * Returns the last facility rating in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching facility rating, or <code>null</code> if a matching facility rating could not be found
	 */
	@Override
	public FacilityRating fetchBycreatorUserId_Last(
		long creatorUserId,
		OrderByComparator<FacilityRating> orderByComparator) {

		int count = countBycreatorUserId(creatorUserId);

		if (count == 0) {
			return null;
		}

		List<FacilityRating> list = findBycreatorUserId(
			creatorUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the facility ratings before and after the current facility rating in the ordered set where creatorUserId = &#63;.
	 *
	 * @param facilityRatingId the primary key of the current facility rating
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next facility rating
	 * @throws NoSuchFacilityRatingException if a facility rating with the primary key could not be found
	 */
	@Override
	public FacilityRating[] findBycreatorUserId_PrevAndNext(
			long facilityRatingId, long creatorUserId,
			OrderByComparator<FacilityRating> orderByComparator)
		throws NoSuchFacilityRatingException {

		FacilityRating facilityRating = findByPrimaryKey(facilityRatingId);

		Session session = null;

		try {
			session = openSession();

			FacilityRating[] array = new FacilityRatingImpl[3];

			array[0] = getBycreatorUserId_PrevAndNext(
				session, facilityRating, creatorUserId, orderByComparator,
				true);

			array[1] = facilityRating;

			array[2] = getBycreatorUserId_PrevAndNext(
				session, facilityRating, creatorUserId, orderByComparator,
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

	protected FacilityRating getBycreatorUserId_PrevAndNext(
		Session session, FacilityRating facilityRating, long creatorUserId,
		OrderByComparator<FacilityRating> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FACILITYRATING_WHERE);

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
			sb.append(FacilityRatingModelImpl.ORDER_BY_JPQL);
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
						facilityRating)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FacilityRating> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the facility ratings where creatorUserId = &#63; from the database.
	 *
	 * @param creatorUserId the creator user ID
	 */
	@Override
	public void removeBycreatorUserId(long creatorUserId) {
		for (FacilityRating facilityRating :
				findBycreatorUserId(
					creatorUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(facilityRating);
		}
	}

	/**
	 * Returns the number of facility ratings where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @return the number of matching facility ratings
	 */
	@Override
	public int countBycreatorUserId(long creatorUserId) {
		FinderPath finderPath = _finderPathCountBycreatorUserId;

		Object[] finderArgs = new Object[] {creatorUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FACILITYRATING_WHERE);

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
		"facilityRating.creatorUserId = ?";

	public FacilityRatingPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("comment", "comment_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FacilityRating.class);

		setModelImplClass(FacilityRatingImpl.class);
		setModelPKClass(long.class);

		setTable(FacilityRatingTable.INSTANCE);
	}

	/**
	 * Caches the facility rating in the entity cache if it is enabled.
	 *
	 * @param facilityRating the facility rating
	 */
	@Override
	public void cacheResult(FacilityRating facilityRating) {
		entityCache.putResult(
			FacilityRatingImpl.class, facilityRating.getPrimaryKey(),
			facilityRating);

		finderCache.putResult(
			_finderPathFetchByratingUniqueId,
			new Object[] {facilityRating.getRatingUniqueId()}, facilityRating);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the facility ratings in the entity cache if it is enabled.
	 *
	 * @param facilityRatings the facility ratings
	 */
	@Override
	public void cacheResult(List<FacilityRating> facilityRatings) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (facilityRatings.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FacilityRating facilityRating : facilityRatings) {
			if (entityCache.getResult(
					FacilityRatingImpl.class, facilityRating.getPrimaryKey()) ==
						null) {

				cacheResult(facilityRating);
			}
		}
	}

	/**
	 * Clears the cache for all facility ratings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FacilityRatingImpl.class);

		finderCache.clearCache(FacilityRatingImpl.class);
	}

	/**
	 * Clears the cache for the facility rating.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FacilityRating facilityRating) {
		entityCache.removeResult(FacilityRatingImpl.class, facilityRating);
	}

	@Override
	public void clearCache(List<FacilityRating> facilityRatings) {
		for (FacilityRating facilityRating : facilityRatings) {
			entityCache.removeResult(FacilityRatingImpl.class, facilityRating);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FacilityRatingImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FacilityRatingImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FacilityRatingModelImpl facilityRatingModelImpl) {

		Object[] args = new Object[] {
			facilityRatingModelImpl.getRatingUniqueId()
		};

		finderCache.putResult(
			_finderPathCountByratingUniqueId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByratingUniqueId, args, facilityRatingModelImpl);
	}

	/**
	 * Creates a new facility rating with the primary key. Does not add the facility rating to the database.
	 *
	 * @param facilityRatingId the primary key for the new facility rating
	 * @return the new facility rating
	 */
	@Override
	public FacilityRating create(long facilityRatingId) {
		FacilityRating facilityRating = new FacilityRatingImpl();

		facilityRating.setNew(true);
		facilityRating.setPrimaryKey(facilityRatingId);

		return facilityRating;
	}

	/**
	 * Removes the facility rating with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param facilityRatingId the primary key of the facility rating
	 * @return the facility rating that was removed
	 * @throws NoSuchFacilityRatingException if a facility rating with the primary key could not be found
	 */
	@Override
	public FacilityRating remove(long facilityRatingId)
		throws NoSuchFacilityRatingException {

		return remove((Serializable)facilityRatingId);
	}

	/**
	 * Removes the facility rating with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the facility rating
	 * @return the facility rating that was removed
	 * @throws NoSuchFacilityRatingException if a facility rating with the primary key could not be found
	 */
	@Override
	public FacilityRating remove(Serializable primaryKey)
		throws NoSuchFacilityRatingException {

		Session session = null;

		try {
			session = openSession();

			FacilityRating facilityRating = (FacilityRating)session.get(
				FacilityRatingImpl.class, primaryKey);

			if (facilityRating == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFacilityRatingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(facilityRating);
		}
		catch (NoSuchFacilityRatingException noSuchEntityException) {
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
	protected FacilityRating removeImpl(FacilityRating facilityRating) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(facilityRating)) {
				facilityRating = (FacilityRating)session.get(
					FacilityRatingImpl.class,
					facilityRating.getPrimaryKeyObj());
			}

			if (facilityRating != null) {
				session.delete(facilityRating);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (facilityRating != null) {
			clearCache(facilityRating);
		}

		return facilityRating;
	}

	@Override
	public FacilityRating updateImpl(FacilityRating facilityRating) {
		boolean isNew = facilityRating.isNew();

		if (!(facilityRating instanceof FacilityRatingModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(facilityRating.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					facilityRating);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in facilityRating proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FacilityRating implementation " +
					facilityRating.getClass());
		}

		FacilityRatingModelImpl facilityRatingModelImpl =
			(FacilityRatingModelImpl)facilityRating;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (facilityRating.getCreateDate() == null)) {
			if (serviceContext == null) {
				facilityRating.setCreateDate(date);
			}
			else {
				facilityRating.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!facilityRatingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				facilityRating.setModifiedDate(date);
			}
			else {
				facilityRating.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(facilityRating);
			}
			else {
				facilityRating = (FacilityRating)session.merge(facilityRating);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FacilityRatingImpl.class, facilityRatingModelImpl, false, true);

		cacheUniqueFindersCache(facilityRatingModelImpl);

		if (isNew) {
			facilityRating.setNew(false);
		}

		facilityRating.resetOriginalValues();

		return facilityRating;
	}

	/**
	 * Returns the facility rating with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the facility rating
	 * @return the facility rating
	 * @throws NoSuchFacilityRatingException if a facility rating with the primary key could not be found
	 */
	@Override
	public FacilityRating findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFacilityRatingException {

		FacilityRating facilityRating = fetchByPrimaryKey(primaryKey);

		if (facilityRating == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFacilityRatingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return facilityRating;
	}

	/**
	 * Returns the facility rating with the primary key or throws a <code>NoSuchFacilityRatingException</code> if it could not be found.
	 *
	 * @param facilityRatingId the primary key of the facility rating
	 * @return the facility rating
	 * @throws NoSuchFacilityRatingException if a facility rating with the primary key could not be found
	 */
	@Override
	public FacilityRating findByPrimaryKey(long facilityRatingId)
		throws NoSuchFacilityRatingException {

		return findByPrimaryKey((Serializable)facilityRatingId);
	}

	/**
	 * Returns the facility rating with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param facilityRatingId the primary key of the facility rating
	 * @return the facility rating, or <code>null</code> if a facility rating with the primary key could not be found
	 */
	@Override
	public FacilityRating fetchByPrimaryKey(long facilityRatingId) {
		return fetchByPrimaryKey((Serializable)facilityRatingId);
	}

	/**
	 * Returns all the facility ratings.
	 *
	 * @return the facility ratings
	 */
	@Override
	public List<FacilityRating> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the facility ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FacilityRatingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of facility ratings
	 * @param end the upper bound of the range of facility ratings (not inclusive)
	 * @return the range of facility ratings
	 */
	@Override
	public List<FacilityRating> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the facility ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FacilityRatingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of facility ratings
	 * @param end the upper bound of the range of facility ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of facility ratings
	 */
	@Override
	public List<FacilityRating> findAll(
		int start, int end,
		OrderByComparator<FacilityRating> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the facility ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FacilityRatingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of facility ratings
	 * @param end the upper bound of the range of facility ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of facility ratings
	 */
	@Override
	public List<FacilityRating> findAll(
		int start, int end, OrderByComparator<FacilityRating> orderByComparator,
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

		List<FacilityRating> list = null;

		if (useFinderCache) {
			list = (List<FacilityRating>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FACILITYRATING);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FACILITYRATING;

				sql = sql.concat(FacilityRatingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FacilityRating>)QueryUtil.list(
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
	 * Removes all the facility ratings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FacilityRating facilityRating : findAll()) {
			remove(facilityRating);
		}
	}

	/**
	 * Returns the number of facility ratings.
	 *
	 * @return the number of facility ratings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FACILITYRATING);

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
		return "facilityRatingId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FACILITYRATING;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FacilityRatingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the facility rating persistence.
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

		_finderPathFetchByratingUniqueId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByratingUniqueId",
			new String[] {String.class.getName()},
			new String[] {"ratingUniqueId"}, true);

		_finderPathCountByratingUniqueId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByratingUniqueId",
			new String[] {String.class.getName()},
			new String[] {"ratingUniqueId"}, false);

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

		FacilityRatingUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		FacilityRatingUtil.setPersistence(null);

		entityCache.removeCache(FacilityRatingImpl.class.getName());
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

	private static final String _SQL_SELECT_FACILITYRATING =
		"SELECT facilityRating FROM FacilityRating facilityRating";

	private static final String _SQL_SELECT_FACILITYRATING_WHERE =
		"SELECT facilityRating FROM FacilityRating facilityRating WHERE ";

	private static final String _SQL_COUNT_FACILITYRATING =
		"SELECT COUNT(facilityRating) FROM FacilityRating facilityRating";

	private static final String _SQL_COUNT_FACILITYRATING_WHERE =
		"SELECT COUNT(facilityRating) FROM FacilityRating facilityRating WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "facilityRating.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FacilityRating exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FacilityRating exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FacilityRatingPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"comment"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}