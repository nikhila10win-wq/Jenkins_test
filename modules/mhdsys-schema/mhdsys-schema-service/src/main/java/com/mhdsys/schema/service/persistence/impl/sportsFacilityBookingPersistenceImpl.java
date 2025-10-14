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

import com.mhdsys.schema.exception.NoSuchsportsFacilityBookingException;
import com.mhdsys.schema.model.impl.sportsFacilityBookingImpl;
import com.mhdsys.schema.model.impl.sportsFacilityBookingModelImpl;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.model.sportsFacilityBookingTable;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;
import com.mhdsys.schema.service.persistence.sportsFacilityBookingPersistence;
import com.mhdsys.schema.service.persistence.sportsFacilityBookingUtil;

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
 * The persistence implementation for the sports facility booking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = sportsFacilityBookingPersistence.class)
public class sportsFacilityBookingPersistenceImpl
	extends BasePersistenceImpl<sportsFacilityBooking>
	implements sportsFacilityBookingPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>sportsFacilityBookingUtil</code> to access the sports facility booking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		sportsFacilityBookingImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindBytype;
	private FinderPath _finderPathWithoutPaginationFindBytype;
	private FinderPath _finderPathCountBytype;

	/**
	 * Returns all the sports facility bookings where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findBytype(String type) {
		return findBytype(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility bookings where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @return the range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findBytype(
		String type, int start, int end) {

		return findBytype(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility bookings where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findBytype(
		String type, int start, int end,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		return findBytype(type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility bookings where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findBytype(
		String type, int start, int end,
		OrderByComparator<sportsFacilityBooking> orderByComparator,
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

		List<sportsFacilityBooking> list = null;

		if (useFinderCache) {
			list = (List<sportsFacilityBooking>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (sportsFacilityBooking sportsFacilityBooking : list) {
					if (!type.equals(sportsFacilityBooking.getType())) {
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

			sb.append(_SQL_SELECT_SPORTSFACILITYBOOKING_WHERE);

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
				sb.append(sportsFacilityBookingModelImpl.ORDER_BY_JPQL);
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

				list = (List<sportsFacilityBooking>)QueryUtil.list(
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
	 * Returns the first sports facility booking in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking findBytype_First(
			String type,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking = fetchBytype_First(
			type, orderByComparator);

		if (sportsFacilityBooking != null) {
			return sportsFacilityBooking;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchsportsFacilityBookingException(sb.toString());
	}

	/**
	 * Returns the first sports facility booking in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility booking, or <code>null</code> if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking fetchBytype_First(
		String type,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		List<sportsFacilityBooking> list = findBytype(
			type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports facility booking in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking findBytype_Last(
			String type,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking = fetchBytype_Last(
			type, orderByComparator);

		if (sportsFacilityBooking != null) {
			return sportsFacilityBooking;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchsportsFacilityBookingException(sb.toString());
	}

	/**
	 * Returns the last sports facility booking in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility booking, or <code>null</code> if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking fetchBytype_Last(
		String type,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		int count = countBytype(type);

		if (count == 0) {
			return null;
		}

		List<sportsFacilityBooking> list = findBytype(
			type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports facility bookings before and after the current sports facility booking in the ordered set where type = &#63;.
	 *
	 * @param facilityBookingId the primary key of the current sports facility booking
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a sports facility booking with the primary key could not be found
	 */
	@Override
	public sportsFacilityBooking[] findBytype_PrevAndNext(
			long facilityBookingId, String type,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		type = Objects.toString(type, "");

		sportsFacilityBooking sportsFacilityBooking = findByPrimaryKey(
			facilityBookingId);

		Session session = null;

		try {
			session = openSession();

			sportsFacilityBooking[] array = new sportsFacilityBookingImpl[3];

			array[0] = getBytype_PrevAndNext(
				session, sportsFacilityBooking, type, orderByComparator, true);

			array[1] = sportsFacilityBooking;

			array[2] = getBytype_PrevAndNext(
				session, sportsFacilityBooking, type, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected sportsFacilityBooking getBytype_PrevAndNext(
		Session session, sportsFacilityBooking sportsFacilityBooking,
		String type, OrderByComparator<sportsFacilityBooking> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTSFACILITYBOOKING_WHERE);

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
			sb.append(sportsFacilityBookingModelImpl.ORDER_BY_JPQL);
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
						sportsFacilityBooking)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<sportsFacilityBooking> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports facility bookings where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeBytype(String type) {
		for (sportsFacilityBooking sportsFacilityBooking :
				findBytype(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportsFacilityBooking);
		}
	}

	/**
	 * Returns the number of sports facility bookings where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching sports facility bookings
	 */
	@Override
	public int countBytype(String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountBytype;

		Object[] finderArgs = new Object[] {type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSFACILITYBOOKING_WHERE);

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
		"sportsFacilityBooking.type = ?";

	private static final String _FINDER_COLUMN_TYPE_TYPE_3 =
		"(sportsFacilityBooking.type IS NULL OR sportsFacilityBooking.type = '')";

	private FinderPath _finderPathWithPaginationFindBycreatorUserId;
	private FinderPath _finderPathWithoutPaginationFindBycreatorUserId;
	private FinderPath _finderPathCountBycreatorUserId;

	/**
	 * Returns all the sports facility bookings where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @return the matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findBycreatorUserId(long creatorUserId) {
		return findBycreatorUserId(
			creatorUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility bookings where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @return the range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findBycreatorUserId(
		long creatorUserId, int start, int end) {

		return findBycreatorUserId(creatorUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility bookings where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findBycreatorUserId(
		long creatorUserId, int start, int end,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		return findBycreatorUserId(
			creatorUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility bookings where creatorUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param creatorUserId the creator user ID
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findBycreatorUserId(
		long creatorUserId, int start, int end,
		OrderByComparator<sportsFacilityBooking> orderByComparator,
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

		List<sportsFacilityBooking> list = null;

		if (useFinderCache) {
			list = (List<sportsFacilityBooking>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (sportsFacilityBooking sportsFacilityBooking : list) {
					if (creatorUserId !=
							sportsFacilityBooking.getCreatorUserId()) {

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

			sb.append(_SQL_SELECT_SPORTSFACILITYBOOKING_WHERE);

			sb.append(_FINDER_COLUMN_CREATORUSERID_CREATORUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(sportsFacilityBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(creatorUserId);

				list = (List<sportsFacilityBooking>)QueryUtil.list(
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
	 * Returns the first sports facility booking in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking findBycreatorUserId_First(
			long creatorUserId,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking =
			fetchBycreatorUserId_First(creatorUserId, orderByComparator);

		if (sportsFacilityBooking != null) {
			return sportsFacilityBooking;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("creatorUserId=");
		sb.append(creatorUserId);

		sb.append("}");

		throw new NoSuchsportsFacilityBookingException(sb.toString());
	}

	/**
	 * Returns the first sports facility booking in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility booking, or <code>null</code> if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking fetchBycreatorUserId_First(
		long creatorUserId,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		List<sportsFacilityBooking> list = findBycreatorUserId(
			creatorUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports facility booking in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking findBycreatorUserId_Last(
			long creatorUserId,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking = fetchBycreatorUserId_Last(
			creatorUserId, orderByComparator);

		if (sportsFacilityBooking != null) {
			return sportsFacilityBooking;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("creatorUserId=");
		sb.append(creatorUserId);

		sb.append("}");

		throw new NoSuchsportsFacilityBookingException(sb.toString());
	}

	/**
	 * Returns the last sports facility booking in the ordered set where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility booking, or <code>null</code> if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking fetchBycreatorUserId_Last(
		long creatorUserId,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		int count = countBycreatorUserId(creatorUserId);

		if (count == 0) {
			return null;
		}

		List<sportsFacilityBooking> list = findBycreatorUserId(
			creatorUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports facility bookings before and after the current sports facility booking in the ordered set where creatorUserId = &#63;.
	 *
	 * @param facilityBookingId the primary key of the current sports facility booking
	 * @param creatorUserId the creator user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a sports facility booking with the primary key could not be found
	 */
	@Override
	public sportsFacilityBooking[] findBycreatorUserId_PrevAndNext(
			long facilityBookingId, long creatorUserId,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking = findByPrimaryKey(
			facilityBookingId);

		Session session = null;

		try {
			session = openSession();

			sportsFacilityBooking[] array = new sportsFacilityBookingImpl[3];

			array[0] = getBycreatorUserId_PrevAndNext(
				session, sportsFacilityBooking, creatorUserId,
				orderByComparator, true);

			array[1] = sportsFacilityBooking;

			array[2] = getBycreatorUserId_PrevAndNext(
				session, sportsFacilityBooking, creatorUserId,
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

	protected sportsFacilityBooking getBycreatorUserId_PrevAndNext(
		Session session, sportsFacilityBooking sportsFacilityBooking,
		long creatorUserId,
		OrderByComparator<sportsFacilityBooking> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTSFACILITYBOOKING_WHERE);

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
			sb.append(sportsFacilityBookingModelImpl.ORDER_BY_JPQL);
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
						sportsFacilityBooking)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<sportsFacilityBooking> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports facility bookings where creatorUserId = &#63; from the database.
	 *
	 * @param creatorUserId the creator user ID
	 */
	@Override
	public void removeBycreatorUserId(long creatorUserId) {
		for (sportsFacilityBooking sportsFacilityBooking :
				findBycreatorUserId(
					creatorUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(sportsFacilityBooking);
		}
	}

	/**
	 * Returns the number of sports facility bookings where creatorUserId = &#63;.
	 *
	 * @param creatorUserId the creator user ID
	 * @return the number of matching sports facility bookings
	 */
	@Override
	public int countBycreatorUserId(long creatorUserId) {
		FinderPath finderPath = _finderPathCountBycreatorUserId;

		Object[] finderArgs = new Object[] {creatorUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSFACILITYBOOKING_WHERE);

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
		"sportsFacilityBooking.creatorUserId = ?";

	private FinderPath _finderPathWithPaginationFindByisApproved;
	private FinderPath _finderPathWithoutPaginationFindByisApproved;
	private FinderPath _finderPathCountByisApproved;

	/**
	 * Returns all the sports facility bookings where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @return the matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findByisApproved(Boolean isApproved) {
		return findByisApproved(
			isApproved, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility bookings where isApproved = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param isApproved the is approved
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @return the range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findByisApproved(
		Boolean isApproved, int start, int end) {

		return findByisApproved(isApproved, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility bookings where isApproved = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param isApproved the is approved
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findByisApproved(
		Boolean isApproved, int start, int end,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		return findByisApproved(
			isApproved, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility bookings where isApproved = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param isApproved the is approved
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findByisApproved(
		Boolean isApproved, int start, int end,
		OrderByComparator<sportsFacilityBooking> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByisApproved;
				finderArgs = new Object[] {isApproved};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByisApproved;
			finderArgs = new Object[] {
				isApproved, start, end, orderByComparator
			};
		}

		List<sportsFacilityBooking> list = null;

		if (useFinderCache) {
			list = (List<sportsFacilityBooking>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (sportsFacilityBooking sportsFacilityBooking : list) {
					if (!Objects.equals(
							isApproved,
							sportsFacilityBooking.getIsApproved())) {

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

			sb.append(_SQL_SELECT_SPORTSFACILITYBOOKING_WHERE);

			sb.append(_FINDER_COLUMN_ISAPPROVED_ISAPPROVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(sportsFacilityBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isApproved.booleanValue());

				list = (List<sportsFacilityBooking>)QueryUtil.list(
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
	 * Returns the first sports facility booking in the ordered set where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking findByisApproved_First(
			Boolean isApproved,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking = fetchByisApproved_First(
			isApproved, orderByComparator);

		if (sportsFacilityBooking != null) {
			return sportsFacilityBooking;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isApproved=");
		sb.append(isApproved);

		sb.append("}");

		throw new NoSuchsportsFacilityBookingException(sb.toString());
	}

	/**
	 * Returns the first sports facility booking in the ordered set where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sports facility booking, or <code>null</code> if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking fetchByisApproved_First(
		Boolean isApproved,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		List<sportsFacilityBooking> list = findByisApproved(
			isApproved, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sports facility booking in the ordered set where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking findByisApproved_Last(
			Boolean isApproved,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking = fetchByisApproved_Last(
			isApproved, orderByComparator);

		if (sportsFacilityBooking != null) {
			return sportsFacilityBooking;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isApproved=");
		sb.append(isApproved);

		sb.append("}");

		throw new NoSuchsportsFacilityBookingException(sb.toString());
	}

	/**
	 * Returns the last sports facility booking in the ordered set where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sports facility booking, or <code>null</code> if a matching sports facility booking could not be found
	 */
	@Override
	public sportsFacilityBooking fetchByisApproved_Last(
		Boolean isApproved,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		int count = countByisApproved(isApproved);

		if (count == 0) {
			return null;
		}

		List<sportsFacilityBooking> list = findByisApproved(
			isApproved, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sports facility bookings before and after the current sports facility booking in the ordered set where isApproved = &#63;.
	 *
	 * @param facilityBookingId the primary key of the current sports facility booking
	 * @param isApproved the is approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a sports facility booking with the primary key could not be found
	 */
	@Override
	public sportsFacilityBooking[] findByisApproved_PrevAndNext(
			long facilityBookingId, Boolean isApproved,
			OrderByComparator<sportsFacilityBooking> orderByComparator)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking = findByPrimaryKey(
			facilityBookingId);

		Session session = null;

		try {
			session = openSession();

			sportsFacilityBooking[] array = new sportsFacilityBookingImpl[3];

			array[0] = getByisApproved_PrevAndNext(
				session, sportsFacilityBooking, isApproved, orderByComparator,
				true);

			array[1] = sportsFacilityBooking;

			array[2] = getByisApproved_PrevAndNext(
				session, sportsFacilityBooking, isApproved, orderByComparator,
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

	protected sportsFacilityBooking getByisApproved_PrevAndNext(
		Session session, sportsFacilityBooking sportsFacilityBooking,
		Boolean isApproved,
		OrderByComparator<sportsFacilityBooking> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTSFACILITYBOOKING_WHERE);

		sb.append(_FINDER_COLUMN_ISAPPROVED_ISAPPROVED_2);

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
			sb.append(sportsFacilityBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isApproved.booleanValue());

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						sportsFacilityBooking)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<sportsFacilityBooking> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sports facility bookings where isApproved = &#63; from the database.
	 *
	 * @param isApproved the is approved
	 */
	@Override
	public void removeByisApproved(Boolean isApproved) {
		for (sportsFacilityBooking sportsFacilityBooking :
				findByisApproved(
					isApproved, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportsFacilityBooking);
		}
	}

	/**
	 * Returns the number of sports facility bookings where isApproved = &#63;.
	 *
	 * @param isApproved the is approved
	 * @return the number of matching sports facility bookings
	 */
	@Override
	public int countByisApproved(Boolean isApproved) {
		FinderPath finderPath = _finderPathCountByisApproved;

		Object[] finderArgs = new Object[] {isApproved};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTSFACILITYBOOKING_WHERE);

			sb.append(_FINDER_COLUMN_ISAPPROVED_ISAPPROVED_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isApproved.booleanValue());

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

	private static final String _FINDER_COLUMN_ISAPPROVED_ISAPPROVED_2 =
		"sportsFacilityBooking.isApproved = ?";

	public sportsFacilityBookingPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(sportsFacilityBooking.class);

		setModelImplClass(sportsFacilityBookingImpl.class);
		setModelPKClass(long.class);

		setTable(sportsFacilityBookingTable.INSTANCE);
	}

	/**
	 * Caches the sports facility booking in the entity cache if it is enabled.
	 *
	 * @param sportsFacilityBooking the sports facility booking
	 */
	@Override
	public void cacheResult(sportsFacilityBooking sportsFacilityBooking) {
		entityCache.putResult(
			sportsFacilityBookingImpl.class,
			sportsFacilityBooking.getPrimaryKey(), sportsFacilityBooking);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sports facility bookings in the entity cache if it is enabled.
	 *
	 * @param sportsFacilityBookings the sports facility bookings
	 */
	@Override
	public void cacheResult(
		List<sportsFacilityBooking> sportsFacilityBookings) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sportsFacilityBookings.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (sportsFacilityBooking sportsFacilityBooking :
				sportsFacilityBookings) {

			if (entityCache.getResult(
					sportsFacilityBookingImpl.class,
					sportsFacilityBooking.getPrimaryKey()) == null) {

				cacheResult(sportsFacilityBooking);
			}
		}
	}

	/**
	 * Clears the cache for all sports facility bookings.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(sportsFacilityBookingImpl.class);

		finderCache.clearCache(sportsFacilityBookingImpl.class);
	}

	/**
	 * Clears the cache for the sports facility booking.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(sportsFacilityBooking sportsFacilityBooking) {
		entityCache.removeResult(
			sportsFacilityBookingImpl.class, sportsFacilityBooking);
	}

	@Override
	public void clearCache(List<sportsFacilityBooking> sportsFacilityBookings) {
		for (sportsFacilityBooking sportsFacilityBooking :
				sportsFacilityBookings) {

			entityCache.removeResult(
				sportsFacilityBookingImpl.class, sportsFacilityBooking);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(sportsFacilityBookingImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				sportsFacilityBookingImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sports facility booking with the primary key. Does not add the sports facility booking to the database.
	 *
	 * @param facilityBookingId the primary key for the new sports facility booking
	 * @return the new sports facility booking
	 */
	@Override
	public sportsFacilityBooking create(long facilityBookingId) {
		sportsFacilityBooking sportsFacilityBooking =
			new sportsFacilityBookingImpl();

		sportsFacilityBooking.setNew(true);
		sportsFacilityBooking.setPrimaryKey(facilityBookingId);

		return sportsFacilityBooking;
	}

	/**
	 * Removes the sports facility booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param facilityBookingId the primary key of the sports facility booking
	 * @return the sports facility booking that was removed
	 * @throws NoSuchsportsFacilityBookingException if a sports facility booking with the primary key could not be found
	 */
	@Override
	public sportsFacilityBooking remove(long facilityBookingId)
		throws NoSuchsportsFacilityBookingException {

		return remove((Serializable)facilityBookingId);
	}

	/**
	 * Removes the sports facility booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sports facility booking
	 * @return the sports facility booking that was removed
	 * @throws NoSuchsportsFacilityBookingException if a sports facility booking with the primary key could not be found
	 */
	@Override
	public sportsFacilityBooking remove(Serializable primaryKey)
		throws NoSuchsportsFacilityBookingException {

		Session session = null;

		try {
			session = openSession();

			sportsFacilityBooking sportsFacilityBooking =
				(sportsFacilityBooking)session.get(
					sportsFacilityBookingImpl.class, primaryKey);

			if (sportsFacilityBooking == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchsportsFacilityBookingException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sportsFacilityBooking);
		}
		catch (NoSuchsportsFacilityBookingException noSuchEntityException) {
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
	protected sportsFacilityBooking removeImpl(
		sportsFacilityBooking sportsFacilityBooking) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sportsFacilityBooking)) {
				sportsFacilityBooking = (sportsFacilityBooking)session.get(
					sportsFacilityBookingImpl.class,
					sportsFacilityBooking.getPrimaryKeyObj());
			}

			if (sportsFacilityBooking != null) {
				session.delete(sportsFacilityBooking);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sportsFacilityBooking != null) {
			clearCache(sportsFacilityBooking);
		}

		return sportsFacilityBooking;
	}

	@Override
	public sportsFacilityBooking updateImpl(
		sportsFacilityBooking sportsFacilityBooking) {

		boolean isNew = sportsFacilityBooking.isNew();

		if (!(sportsFacilityBooking instanceof
				sportsFacilityBookingModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(sportsFacilityBooking.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					sportsFacilityBooking);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sportsFacilityBooking proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom sportsFacilityBooking implementation " +
					sportsFacilityBooking.getClass());
		}

		sportsFacilityBookingModelImpl sportsFacilityBookingModelImpl =
			(sportsFacilityBookingModelImpl)sportsFacilityBooking;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (sportsFacilityBooking.getCreateDate() == null)) {
			if (serviceContext == null) {
				sportsFacilityBooking.setCreateDate(date);
			}
			else {
				sportsFacilityBooking.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!sportsFacilityBookingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				sportsFacilityBooking.setModifiedDate(date);
			}
			else {
				sportsFacilityBooking.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sportsFacilityBooking);
			}
			else {
				sportsFacilityBooking = (sportsFacilityBooking)session.merge(
					sportsFacilityBooking);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			sportsFacilityBookingImpl.class, sportsFacilityBookingModelImpl,
			false, true);

		if (isNew) {
			sportsFacilityBooking.setNew(false);
		}

		sportsFacilityBooking.resetOriginalValues();

		return sportsFacilityBooking;
	}

	/**
	 * Returns the sports facility booking with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sports facility booking
	 * @return the sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a sports facility booking with the primary key could not be found
	 */
	@Override
	public sportsFacilityBooking findByPrimaryKey(Serializable primaryKey)
		throws NoSuchsportsFacilityBookingException {

		sportsFacilityBooking sportsFacilityBooking = fetchByPrimaryKey(
			primaryKey);

		if (sportsFacilityBooking == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchsportsFacilityBookingException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sportsFacilityBooking;
	}

	/**
	 * Returns the sports facility booking with the primary key or throws a <code>NoSuchsportsFacilityBookingException</code> if it could not be found.
	 *
	 * @param facilityBookingId the primary key of the sports facility booking
	 * @return the sports facility booking
	 * @throws NoSuchsportsFacilityBookingException if a sports facility booking with the primary key could not be found
	 */
	@Override
	public sportsFacilityBooking findByPrimaryKey(long facilityBookingId)
		throws NoSuchsportsFacilityBookingException {

		return findByPrimaryKey((Serializable)facilityBookingId);
	}

	/**
	 * Returns the sports facility booking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param facilityBookingId the primary key of the sports facility booking
	 * @return the sports facility booking, or <code>null</code> if a sports facility booking with the primary key could not be found
	 */
	@Override
	public sportsFacilityBooking fetchByPrimaryKey(long facilityBookingId) {
		return fetchByPrimaryKey((Serializable)facilityBookingId);
	}

	/**
	 * Returns all the sports facility bookings.
	 *
	 * @return the sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sports facility bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @return the range of sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sports facility bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findAll(
		int start, int end,
		OrderByComparator<sportsFacilityBooking> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sports facility bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>sportsFacilityBookingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sports facility bookings
	 * @param end the upper bound of the range of sports facility bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sports facility bookings
	 */
	@Override
	public List<sportsFacilityBooking> findAll(
		int start, int end,
		OrderByComparator<sportsFacilityBooking> orderByComparator,
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

		List<sportsFacilityBooking> list = null;

		if (useFinderCache) {
			list = (List<sportsFacilityBooking>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SPORTSFACILITYBOOKING);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SPORTSFACILITYBOOKING;

				sql = sql.concat(sportsFacilityBookingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<sportsFacilityBooking>)QueryUtil.list(
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
	 * Removes all the sports facility bookings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (sportsFacilityBooking sportsFacilityBooking : findAll()) {
			remove(sportsFacilityBooking);
		}
	}

	/**
	 * Returns the number of sports facility bookings.
	 *
	 * @return the number of sports facility bookings
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
					_SQL_COUNT_SPORTSFACILITYBOOKING);

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
		return "facilityBookingId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SPORTSFACILITYBOOKING;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return sportsFacilityBookingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sports facility booking persistence.
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

		_finderPathWithPaginationFindByisApproved = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByisApproved",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"isApproved"}, true);

		_finderPathWithoutPaginationFindByisApproved = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByisApproved",
			new String[] {Boolean.class.getName()}, new String[] {"isApproved"},
			true);

		_finderPathCountByisApproved = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByisApproved",
			new String[] {Boolean.class.getName()}, new String[] {"isApproved"},
			false);

		sportsFacilityBookingUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		sportsFacilityBookingUtil.setPersistence(null);

		entityCache.removeCache(sportsFacilityBookingImpl.class.getName());
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

	private static final String _SQL_SELECT_SPORTSFACILITYBOOKING =
		"SELECT sportsFacilityBooking FROM sportsFacilityBooking sportsFacilityBooking";

	private static final String _SQL_SELECT_SPORTSFACILITYBOOKING_WHERE =
		"SELECT sportsFacilityBooking FROM sportsFacilityBooking sportsFacilityBooking WHERE ";

	private static final String _SQL_COUNT_SPORTSFACILITYBOOKING =
		"SELECT COUNT(sportsFacilityBooking) FROM sportsFacilityBooking sportsFacilityBooking";

	private static final String _SQL_COUNT_SPORTSFACILITYBOOKING_WHERE =
		"SELECT COUNT(sportsFacilityBooking) FROM sportsFacilityBooking sportsFacilityBooking WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"sportsFacilityBooking.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No sportsFacilityBooking exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No sportsFacilityBooking exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		sportsFacilityBookingPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}