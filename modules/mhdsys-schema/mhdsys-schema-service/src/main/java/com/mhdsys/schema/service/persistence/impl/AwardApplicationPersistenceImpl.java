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

import com.mhdsys.schema.exception.NoSuchAwardApplicationException;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.AwardApplicationTable;
import com.mhdsys.schema.model.impl.AwardApplicationImpl;
import com.mhdsys.schema.model.impl.AwardApplicationModelImpl;
import com.mhdsys.schema.service.persistence.AwardApplicationPersistence;
import com.mhdsys.schema.service.persistence.AwardApplicationUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
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
 * The persistence implementation for the award application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AwardApplicationPersistence.class)
public class AwardApplicationPersistenceImpl
	extends BasePersistenceImpl<AwardApplication>
	implements AwardApplicationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AwardApplicationUtil</code> to access the award application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AwardApplicationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the award applications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching award applications
	 */
	@Override
	public List<AwardApplication> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award applications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @return the range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the award applications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByUserId(
		long userId, int start, int end,
		OrderByComparator<AwardApplication> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award applications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByUserId(
		long userId, int start, int end,
		OrderByComparator<AwardApplication> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<AwardApplication> list = null;

		if (useFinderCache) {
			list = (List<AwardApplication>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AwardApplication awardApplication : list) {
					if (userId != awardApplication.getUserId()) {
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

			sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AwardApplicationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<AwardApplication>)QueryUtil.list(
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
	 * Returns the first award application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByUserId_First(
			long userId, OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByUserId_First(
			userId, orderByComparator);

		if (awardApplication != null) {
			return awardApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchAwardApplicationException(sb.toString());
	}

	/**
	 * Returns the first award application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByUserId_First(
		long userId, OrderByComparator<AwardApplication> orderByComparator) {

		List<AwardApplication> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last award application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByUserId_Last(
			long userId, OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByUserId_Last(
			userId, orderByComparator);

		if (awardApplication != null) {
			return awardApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchAwardApplicationException(sb.toString());
	}

	/**
	 * Returns the last award application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByUserId_Last(
		long userId, OrderByComparator<AwardApplication> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<AwardApplication> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the award applications before and after the current award application in the ordered set where userId = &#63;.
	 *
	 * @param awardApplicationId the primary key of the current award application
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next award application
	 * @throws NoSuchAwardApplicationException if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication[] findByUserId_PrevAndNext(
			long awardApplicationId, long userId,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = findByPrimaryKey(
			awardApplicationId);

		Session session = null;

		try {
			session = openSession();

			AwardApplication[] array = new AwardApplicationImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, awardApplication, userId, orderByComparator, true);

			array[1] = awardApplication;

			array[2] = getByUserId_PrevAndNext(
				session, awardApplication, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AwardApplication getByUserId_PrevAndNext(
		Session session, AwardApplication awardApplication, long userId,
		OrderByComparator<AwardApplication> orderByComparator,
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

		sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

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
			sb.append(AwardApplicationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						awardApplication)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AwardApplication> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the award applications where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (AwardApplication awardApplication :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(awardApplication);
		}
	}

	/**
	 * Returns the number of award applications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching award applications
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"awardApplication.userId = ?";

	private FinderPath _finderPathWithPaginationFindByStatus;
	private FinderPath _finderPathWithoutPaginationFindByStatus;
	private FinderPath _finderPathCountByStatus;

	/**
	 * Returns all the award applications where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching award applications
	 */
	@Override
	public List<AwardApplication> findByStatus(String status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award applications where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @return the range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByStatus(
		String status, int start, int end) {

		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the award applications where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByStatus(
		String status, int start, int end,
		OrderByComparator<AwardApplication> orderByComparator) {

		return findByStatus(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award applications where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByStatus(
		String status, int start, int end,
		OrderByComparator<AwardApplication> orderByComparator,
		boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStatus;
				finderArgs = new Object[] {status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStatus;
			finderArgs = new Object[] {status, start, end, orderByComparator};
		}

		List<AwardApplication> list = null;

		if (useFinderCache) {
			list = (List<AwardApplication>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AwardApplication awardApplication : list) {
					if (!status.equals(awardApplication.getStatus())) {
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

			sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AwardApplicationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
				}

				list = (List<AwardApplication>)QueryUtil.list(
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
	 * Returns the first award application in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByStatus_First(
			String status,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByStatus_First(
			status, orderByComparator);

		if (awardApplication != null) {
			return awardApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchAwardApplicationException(sb.toString());
	}

	/**
	 * Returns the first award application in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByStatus_First(
		String status, OrderByComparator<AwardApplication> orderByComparator) {

		List<AwardApplication> list = findByStatus(
			status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last award application in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByStatus_Last(
			String status,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByStatus_Last(
			status, orderByComparator);

		if (awardApplication != null) {
			return awardApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchAwardApplicationException(sb.toString());
	}

	/**
	 * Returns the last award application in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByStatus_Last(
		String status, OrderByComparator<AwardApplication> orderByComparator) {

		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<AwardApplication> list = findByStatus(
			status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the award applications before and after the current award application in the ordered set where status = &#63;.
	 *
	 * @param awardApplicationId the primary key of the current award application
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next award application
	 * @throws NoSuchAwardApplicationException if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication[] findByStatus_PrevAndNext(
			long awardApplicationId, String status,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		status = Objects.toString(status, "");

		AwardApplication awardApplication = findByPrimaryKey(
			awardApplicationId);

		Session session = null;

		try {
			session = openSession();

			AwardApplication[] array = new AwardApplicationImpl[3];

			array[0] = getByStatus_PrevAndNext(
				session, awardApplication, status, orderByComparator, true);

			array[1] = awardApplication;

			array[2] = getByStatus_PrevAndNext(
				session, awardApplication, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AwardApplication getByStatus_PrevAndNext(
		Session session, AwardApplication awardApplication, String status,
		OrderByComparator<AwardApplication> orderByComparator,
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

		sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
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
			sb.append(AwardApplicationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindStatus) {
			queryPos.add(status);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						awardApplication)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AwardApplication> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the award applications where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(String status) {
		for (AwardApplication awardApplication :
				findByStatus(
					status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(awardApplication);
		}
	}

	/**
	 * Returns the number of award applications where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching award applications
	 */
	@Override
	public int countByStatus(String status) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByStatus;

		Object[] finderArgs = new Object[] {status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDAPPLICATION_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 =
		"awardApplication.status = ?";

	private static final String _FINDER_COLUMN_STATUS_STATUS_3 =
		"(awardApplication.status IS NULL OR awardApplication.status = '')";

	private FinderPath _finderPathWithPaginationFindByStatusAndUserId;
	private FinderPath _finderPathWithoutPaginationFindByStatusAndUserId;
	private FinderPath _finderPathCountByStatusAndUserId;

	/**
	 * Returns all the award applications where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching award applications
	 */
	@Override
	public List<AwardApplication> findByStatusAndUserId(
		long userId, String status) {

		return findByStatusAndUserId(
			userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award applications where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @return the range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByStatusAndUserId(
		long userId, String status, int start, int end) {

		return findByStatusAndUserId(userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the award applications where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByStatusAndUserId(
		long userId, String status, int start, int end,
		OrderByComparator<AwardApplication> orderByComparator) {

		return findByStatusAndUserId(
			userId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award applications where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByStatusAndUserId(
		long userId, String status, int start, int end,
		OrderByComparator<AwardApplication> orderByComparator,
		boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStatusAndUserId;
				finderArgs = new Object[] {userId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStatusAndUserId;
			finderArgs = new Object[] {
				userId, status, start, end, orderByComparator
			};
		}

		List<AwardApplication> list = null;

		if (useFinderCache) {
			list = (List<AwardApplication>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AwardApplication awardApplication : list) {
					if ((userId != awardApplication.getUserId()) ||
						!status.equals(awardApplication.getStatus())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_USERID_2);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AwardApplicationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				if (bindStatus) {
					queryPos.add(status);
				}

				list = (List<AwardApplication>)QueryUtil.list(
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
	 * Returns the first award application in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByStatusAndUserId_First(
			long userId, String status,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByStatusAndUserId_First(
			userId, status, orderByComparator);

		if (awardApplication != null) {
			return awardApplication;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchAwardApplicationException(sb.toString());
	}

	/**
	 * Returns the first award application in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByStatusAndUserId_First(
		long userId, String status,
		OrderByComparator<AwardApplication> orderByComparator) {

		List<AwardApplication> list = findByStatusAndUserId(
			userId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last award application in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByStatusAndUserId_Last(
			long userId, String status,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByStatusAndUserId_Last(
			userId, status, orderByComparator);

		if (awardApplication != null) {
			return awardApplication;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchAwardApplicationException(sb.toString());
	}

	/**
	 * Returns the last award application in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByStatusAndUserId_Last(
		long userId, String status,
		OrderByComparator<AwardApplication> orderByComparator) {

		int count = countByStatusAndUserId(userId, status);

		if (count == 0) {
			return null;
		}

		List<AwardApplication> list = findByStatusAndUserId(
			userId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the award applications before and after the current award application in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param awardApplicationId the primary key of the current award application
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next award application
	 * @throws NoSuchAwardApplicationException if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication[] findByStatusAndUserId_PrevAndNext(
			long awardApplicationId, long userId, String status,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		status = Objects.toString(status, "");

		AwardApplication awardApplication = findByPrimaryKey(
			awardApplicationId);

		Session session = null;

		try {
			session = openSession();

			AwardApplication[] array = new AwardApplicationImpl[3];

			array[0] = getByStatusAndUserId_PrevAndNext(
				session, awardApplication, userId, status, orderByComparator,
				true);

			array[1] = awardApplication;

			array[2] = getByStatusAndUserId_PrevAndNext(
				session, awardApplication, userId, status, orderByComparator,
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

	protected AwardApplication getByStatusAndUserId_PrevAndNext(
		Session session, AwardApplication awardApplication, long userId,
		String status, OrderByComparator<AwardApplication> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

		sb.append(_FINDER_COLUMN_STATUSANDUSERID_USERID_2);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_2);
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
			sb.append(AwardApplicationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (bindStatus) {
			queryPos.add(status);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						awardApplication)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AwardApplication> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the award applications where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByStatusAndUserId(long userId, String status) {
		for (AwardApplication awardApplication :
				findByStatusAndUserId(
					userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(awardApplication);
		}
	}

	/**
	 * Returns the number of award applications where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching award applications
	 */
	@Override
	public int countByStatusAndUserId(long userId, String status) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByStatusAndUserId;

		Object[] finderArgs = new Object[] {userId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_AWARDAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_STATUSANDUSERID_USERID_2);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUSANDUSERID_STATUS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				if (bindStatus) {
					queryPos.add(status);
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

	private static final String _FINDER_COLUMN_STATUSANDUSERID_USERID_2 =
		"awardApplication.userId = ? AND ";

	private static final String _FINDER_COLUMN_STATUSANDUSERID_STATUS_2 =
		"awardApplication.status = ?";

	private static final String _FINDER_COLUMN_STATUSANDUSERID_STATUS_3 =
		"(awardApplication.status IS NULL OR awardApplication.status = '')";

	private FinderPath _finderPathFetchByAwardApplicationId;
	private FinderPath _finderPathCountByAwardApplicationId;

	/**
	 * Returns the award application where awardApplicationId = &#63; or throws a <code>NoSuchAwardApplicationException</code> if it could not be found.
	 *
	 * @param awardApplicationId the award application ID
	 * @return the matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByAwardApplicationId(long awardApplicationId)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByAwardApplicationId(
			awardApplicationId);

		if (awardApplication == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("awardApplicationId=");
			sb.append(awardApplicationId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAwardApplicationException(sb.toString());
		}

		return awardApplication;
	}

	/**
	 * Returns the award application where awardApplicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param awardApplicationId the award application ID
	 * @return the matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByAwardApplicationId(long awardApplicationId) {
		return fetchByAwardApplicationId(awardApplicationId, true);
	}

	/**
	 * Returns the award application where awardApplicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param awardApplicationId the award application ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByAwardApplicationId(
		long awardApplicationId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {awardApplicationId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByAwardApplicationId, finderArgs, this);
		}

		if (result instanceof AwardApplication) {
			AwardApplication awardApplication = (AwardApplication)result;

			if (awardApplicationId !=
					awardApplication.getAwardApplicationId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_AWARDAPPLICATIONID_AWARDAPPLICATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(awardApplicationId);

				List<AwardApplication> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByAwardApplicationId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {awardApplicationId};
							}

							_log.warn(
								"AwardApplicationPersistenceImpl.fetchByAwardApplicationId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AwardApplication awardApplication = list.get(0);

					result = awardApplication;

					cacheResult(awardApplication);
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
			return (AwardApplication)result;
		}
	}

	/**
	 * Removes the award application where awardApplicationId = &#63; from the database.
	 *
	 * @param awardApplicationId the award application ID
	 * @return the award application that was removed
	 */
	@Override
	public AwardApplication removeByAwardApplicationId(long awardApplicationId)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = findByAwardApplicationId(
			awardApplicationId);

		return remove(awardApplication);
	}

	/**
	 * Returns the number of award applications where awardApplicationId = &#63;.
	 *
	 * @param awardApplicationId the award application ID
	 * @return the number of matching award applications
	 */
	@Override
	public int countByAwardApplicationId(long awardApplicationId) {
		FinderPath finderPath = _finderPathCountByAwardApplicationId;

		Object[] finderArgs = new Object[] {awardApplicationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_AWARDAPPLICATIONID_AWARDAPPLICATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(awardApplicationId);

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
		_FINDER_COLUMN_AWARDAPPLICATIONID_AWARDAPPLICATIONID_2 =
			"awardApplication.awardApplicationId = ?";

	private FinderPath _finderPathFetchByCompetitionName;
	private FinderPath _finderPathCountByCompetitionName;

	/**
	 * Returns the award application where competitionName = &#63; or throws a <code>NoSuchAwardApplicationException</code> if it could not be found.
	 *
	 * @param competitionName the competition name
	 * @return the matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByCompetitionName(String competitionName)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByCompetitionName(
			competitionName);

		if (awardApplication == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("competitionName=");
			sb.append(competitionName);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAwardApplicationException(sb.toString());
		}

		return awardApplication;
	}

	/**
	 * Returns the award application where competitionName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param competitionName the competition name
	 * @return the matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByCompetitionName(String competitionName) {
		return fetchByCompetitionName(competitionName, true);
	}

	/**
	 * Returns the award application where competitionName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param competitionName the competition name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByCompetitionName(
		String competitionName, boolean useFinderCache) {

		competitionName = Objects.toString(competitionName, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {competitionName};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByCompetitionName, finderArgs, this);
		}

		if (result instanceof AwardApplication) {
			AwardApplication awardApplication = (AwardApplication)result;

			if (!Objects.equals(
					competitionName, awardApplication.getCompetitionName())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

			boolean bindCompetitionName = false;

			if (competitionName.isEmpty()) {
				sb.append(_FINDER_COLUMN_COMPETITIONNAME_COMPETITIONNAME_3);
			}
			else {
				bindCompetitionName = true;

				sb.append(_FINDER_COLUMN_COMPETITIONNAME_COMPETITIONNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindCompetitionName) {
					queryPos.add(competitionName);
				}

				List<AwardApplication> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByCompetitionName, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {competitionName};
							}

							_log.warn(
								"AwardApplicationPersistenceImpl.fetchByCompetitionName(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AwardApplication awardApplication = list.get(0);

					result = awardApplication;

					cacheResult(awardApplication);
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
			return (AwardApplication)result;
		}
	}

	/**
	 * Removes the award application where competitionName = &#63; from the database.
	 *
	 * @param competitionName the competition name
	 * @return the award application that was removed
	 */
	@Override
	public AwardApplication removeByCompetitionName(String competitionName)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = findByCompetitionName(
			competitionName);

		return remove(awardApplication);
	}

	/**
	 * Returns the number of award applications where competitionName = &#63;.
	 *
	 * @param competitionName the competition name
	 * @return the number of matching award applications
	 */
	@Override
	public int countByCompetitionName(String competitionName) {
		competitionName = Objects.toString(competitionName, "");

		FinderPath finderPath = _finderPathCountByCompetitionName;

		Object[] finderArgs = new Object[] {competitionName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDAPPLICATION_WHERE);

			boolean bindCompetitionName = false;

			if (competitionName.isEmpty()) {
				sb.append(_FINDER_COLUMN_COMPETITIONNAME_COMPETITIONNAME_3);
			}
			else {
				bindCompetitionName = true;

				sb.append(_FINDER_COLUMN_COMPETITIONNAME_COMPETITIONNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindCompetitionName) {
					queryPos.add(competitionName);
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

	private static final String
		_FINDER_COLUMN_COMPETITIONNAME_COMPETITIONNAME_2 =
			"awardApplication.competitionName = ?";

	private static final String
		_FINDER_COLUMN_COMPETITIONNAME_COMPETITIONNAME_3 =
			"(awardApplication.competitionName IS NULL OR awardApplication.competitionName = '')";

	private FinderPath _finderPathWithPaginationFindByAssociationUserId;
	private FinderPath _finderPathWithoutPaginationFindByAssociationUserId;
	private FinderPath _finderPathCountByAssociationUserId;

	/**
	 * Returns all the award applications where associationUserId = &#63;.
	 *
	 * @param associationUserId the association user ID
	 * @return the matching award applications
	 */
	@Override
	public List<AwardApplication> findByAssociationUserId(
		long associationUserId) {

		return findByAssociationUserId(
			associationUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award applications where associationUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param associationUserId the association user ID
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @return the range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByAssociationUserId(
		long associationUserId, int start, int end) {

		return findByAssociationUserId(associationUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the award applications where associationUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param associationUserId the association user ID
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByAssociationUserId(
		long associationUserId, int start, int end,
		OrderByComparator<AwardApplication> orderByComparator) {

		return findByAssociationUserId(
			associationUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award applications where associationUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param associationUserId the association user ID
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching award applications
	 */
	@Override
	public List<AwardApplication> findByAssociationUserId(
		long associationUserId, int start, int end,
		OrderByComparator<AwardApplication> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByAssociationUserId;
				finderArgs = new Object[] {associationUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAssociationUserId;
			finderArgs = new Object[] {
				associationUserId, start, end, orderByComparator
			};
		}

		List<AwardApplication> list = null;

		if (useFinderCache) {
			list = (List<AwardApplication>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AwardApplication awardApplication : list) {
					if (associationUserId !=
							awardApplication.getAssociationUserId()) {

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

			sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_ASSOCIATIONUSERID_ASSOCIATIONUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AwardApplicationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(associationUserId);

				list = (List<AwardApplication>)QueryUtil.list(
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
	 * Returns the first award application in the ordered set where associationUserId = &#63;.
	 *
	 * @param associationUserId the association user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByAssociationUserId_First(
			long associationUserId,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByAssociationUserId_First(
			associationUserId, orderByComparator);

		if (awardApplication != null) {
			return awardApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("associationUserId=");
		sb.append(associationUserId);

		sb.append("}");

		throw new NoSuchAwardApplicationException(sb.toString());
	}

	/**
	 * Returns the first award application in the ordered set where associationUserId = &#63;.
	 *
	 * @param associationUserId the association user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByAssociationUserId_First(
		long associationUserId,
		OrderByComparator<AwardApplication> orderByComparator) {

		List<AwardApplication> list = findByAssociationUserId(
			associationUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last award application in the ordered set where associationUserId = &#63;.
	 *
	 * @param associationUserId the association user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award application
	 * @throws NoSuchAwardApplicationException if a matching award application could not be found
	 */
	@Override
	public AwardApplication findByAssociationUserId_Last(
			long associationUserId,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByAssociationUserId_Last(
			associationUserId, orderByComparator);

		if (awardApplication != null) {
			return awardApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("associationUserId=");
		sb.append(associationUserId);

		sb.append("}");

		throw new NoSuchAwardApplicationException(sb.toString());
	}

	/**
	 * Returns the last award application in the ordered set where associationUserId = &#63;.
	 *
	 * @param associationUserId the association user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching award application, or <code>null</code> if a matching award application could not be found
	 */
	@Override
	public AwardApplication fetchByAssociationUserId_Last(
		long associationUserId,
		OrderByComparator<AwardApplication> orderByComparator) {

		int count = countByAssociationUserId(associationUserId);

		if (count == 0) {
			return null;
		}

		List<AwardApplication> list = findByAssociationUserId(
			associationUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the award applications before and after the current award application in the ordered set where associationUserId = &#63;.
	 *
	 * @param awardApplicationId the primary key of the current award application
	 * @param associationUserId the association user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next award application
	 * @throws NoSuchAwardApplicationException if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication[] findByAssociationUserId_PrevAndNext(
			long awardApplicationId, long associationUserId,
			OrderByComparator<AwardApplication> orderByComparator)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = findByPrimaryKey(
			awardApplicationId);

		Session session = null;

		try {
			session = openSession();

			AwardApplication[] array = new AwardApplicationImpl[3];

			array[0] = getByAssociationUserId_PrevAndNext(
				session, awardApplication, associationUserId, orderByComparator,
				true);

			array[1] = awardApplication;

			array[2] = getByAssociationUserId_PrevAndNext(
				session, awardApplication, associationUserId, orderByComparator,
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

	protected AwardApplication getByAssociationUserId_PrevAndNext(
		Session session, AwardApplication awardApplication,
		long associationUserId,
		OrderByComparator<AwardApplication> orderByComparator,
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

		sb.append(_SQL_SELECT_AWARDAPPLICATION_WHERE);

		sb.append(_FINDER_COLUMN_ASSOCIATIONUSERID_ASSOCIATIONUSERID_2);

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
			sb.append(AwardApplicationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(associationUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						awardApplication)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AwardApplication> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the award applications where associationUserId = &#63; from the database.
	 *
	 * @param associationUserId the association user ID
	 */
	@Override
	public void removeByAssociationUserId(long associationUserId) {
		for (AwardApplication awardApplication :
				findByAssociationUserId(
					associationUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(awardApplication);
		}
	}

	/**
	 * Returns the number of award applications where associationUserId = &#63;.
	 *
	 * @param associationUserId the association user ID
	 * @return the number of matching award applications
	 */
	@Override
	public int countByAssociationUserId(long associationUserId) {
		FinderPath finderPath = _finderPathCountByAssociationUserId;

		Object[] finderArgs = new Object[] {associationUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_ASSOCIATIONUSERID_ASSOCIATIONUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(associationUserId);

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
		_FINDER_COLUMN_ASSOCIATIONUSERID_ASSOCIATIONUSERID_2 =
			"awardApplication.associationUserId = ?";

	public AwardApplicationPersistenceImpl() {
		setModelClass(AwardApplication.class);

		setModelImplClass(AwardApplicationImpl.class);
		setModelPKClass(long.class);

		setTable(AwardApplicationTable.INSTANCE);
	}

	/**
	 * Caches the award application in the entity cache if it is enabled.
	 *
	 * @param awardApplication the award application
	 */
	@Override
	public void cacheResult(AwardApplication awardApplication) {
		entityCache.putResult(
			AwardApplicationImpl.class, awardApplication.getPrimaryKey(),
			awardApplication);

		finderCache.putResult(
			_finderPathFetchByAwardApplicationId,
			new Object[] {awardApplication.getAwardApplicationId()},
			awardApplication);

		finderCache.putResult(
			_finderPathFetchByCompetitionName,
			new Object[] {awardApplication.getCompetitionName()},
			awardApplication);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the award applications in the entity cache if it is enabled.
	 *
	 * @param awardApplications the award applications
	 */
	@Override
	public void cacheResult(List<AwardApplication> awardApplications) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (awardApplications.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AwardApplication awardApplication : awardApplications) {
			if (entityCache.getResult(
					AwardApplicationImpl.class,
					awardApplication.getPrimaryKey()) == null) {

				cacheResult(awardApplication);
			}
		}
	}

	/**
	 * Clears the cache for all award applications.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AwardApplicationImpl.class);

		finderCache.clearCache(AwardApplicationImpl.class);
	}

	/**
	 * Clears the cache for the award application.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AwardApplication awardApplication) {
		entityCache.removeResult(AwardApplicationImpl.class, awardApplication);
	}

	@Override
	public void clearCache(List<AwardApplication> awardApplications) {
		for (AwardApplication awardApplication : awardApplications) {
			entityCache.removeResult(
				AwardApplicationImpl.class, awardApplication);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AwardApplicationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AwardApplicationImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AwardApplicationModelImpl awardApplicationModelImpl) {

		Object[] args = new Object[] {
			awardApplicationModelImpl.getAwardApplicationId()
		};

		finderCache.putResult(
			_finderPathCountByAwardApplicationId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByAwardApplicationId, args,
			awardApplicationModelImpl);

		args = new Object[] {awardApplicationModelImpl.getCompetitionName()};

		finderCache.putResult(
			_finderPathCountByCompetitionName, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByCompetitionName, args, awardApplicationModelImpl);
	}

	/**
	 * Creates a new award application with the primary key. Does not add the award application to the database.
	 *
	 * @param awardApplicationId the primary key for the new award application
	 * @return the new award application
	 */
	@Override
	public AwardApplication create(long awardApplicationId) {
		AwardApplication awardApplication = new AwardApplicationImpl();

		awardApplication.setNew(true);
		awardApplication.setPrimaryKey(awardApplicationId);

		return awardApplication;
	}

	/**
	 * Removes the award application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param awardApplicationId the primary key of the award application
	 * @return the award application that was removed
	 * @throws NoSuchAwardApplicationException if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication remove(long awardApplicationId)
		throws NoSuchAwardApplicationException {

		return remove((Serializable)awardApplicationId);
	}

	/**
	 * Removes the award application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the award application
	 * @return the award application that was removed
	 * @throws NoSuchAwardApplicationException if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication remove(Serializable primaryKey)
		throws NoSuchAwardApplicationException {

		Session session = null;

		try {
			session = openSession();

			AwardApplication awardApplication = (AwardApplication)session.get(
				AwardApplicationImpl.class, primaryKey);

			if (awardApplication == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAwardApplicationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(awardApplication);
		}
		catch (NoSuchAwardApplicationException noSuchEntityException) {
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
	protected AwardApplication removeImpl(AwardApplication awardApplication) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(awardApplication)) {
				awardApplication = (AwardApplication)session.get(
					AwardApplicationImpl.class,
					awardApplication.getPrimaryKeyObj());
			}

			if (awardApplication != null) {
				session.delete(awardApplication);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (awardApplication != null) {
			clearCache(awardApplication);
		}

		return awardApplication;
	}

	@Override
	public AwardApplication updateImpl(AwardApplication awardApplication) {
		boolean isNew = awardApplication.isNew();

		if (!(awardApplication instanceof AwardApplicationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(awardApplication.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					awardApplication);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in awardApplication proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AwardApplication implementation " +
					awardApplication.getClass());
		}

		AwardApplicationModelImpl awardApplicationModelImpl =
			(AwardApplicationModelImpl)awardApplication;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (awardApplication.getCreateDate() == null)) {
			if (serviceContext == null) {
				awardApplication.setCreateDate(date);
			}
			else {
				awardApplication.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!awardApplicationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				awardApplication.setModifiedDate(date);
			}
			else {
				awardApplication.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(awardApplication);
			}
			else {
				awardApplication = (AwardApplication)session.merge(
					awardApplication);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AwardApplicationImpl.class, awardApplicationModelImpl, false, true);

		cacheUniqueFindersCache(awardApplicationModelImpl);

		if (isNew) {
			awardApplication.setNew(false);
		}

		awardApplication.resetOriginalValues();

		return awardApplication;
	}

	/**
	 * Returns the award application with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the award application
	 * @return the award application
	 * @throws NoSuchAwardApplicationException if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAwardApplicationException {

		AwardApplication awardApplication = fetchByPrimaryKey(primaryKey);

		if (awardApplication == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAwardApplicationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return awardApplication;
	}

	/**
	 * Returns the award application with the primary key or throws a <code>NoSuchAwardApplicationException</code> if it could not be found.
	 *
	 * @param awardApplicationId the primary key of the award application
	 * @return the award application
	 * @throws NoSuchAwardApplicationException if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication findByPrimaryKey(long awardApplicationId)
		throws NoSuchAwardApplicationException {

		return findByPrimaryKey((Serializable)awardApplicationId);
	}

	/**
	 * Returns the award application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param awardApplicationId the primary key of the award application
	 * @return the award application, or <code>null</code> if a award application with the primary key could not be found
	 */
	@Override
	public AwardApplication fetchByPrimaryKey(long awardApplicationId) {
		return fetchByPrimaryKey((Serializable)awardApplicationId);
	}

	/**
	 * Returns all the award applications.
	 *
	 * @return the award applications
	 */
	@Override
	public List<AwardApplication> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @return the range of award applications
	 */
	@Override
	public List<AwardApplication> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the award applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of award applications
	 */
	@Override
	public List<AwardApplication> findAll(
		int start, int end,
		OrderByComparator<AwardApplication> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award applications
	 * @param end the upper bound of the range of award applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of award applications
	 */
	@Override
	public List<AwardApplication> findAll(
		int start, int end,
		OrderByComparator<AwardApplication> orderByComparator,
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

		List<AwardApplication> list = null;

		if (useFinderCache) {
			list = (List<AwardApplication>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AWARDAPPLICATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AWARDAPPLICATION;

				sql = sql.concat(AwardApplicationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AwardApplication>)QueryUtil.list(
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
	 * Removes all the award applications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AwardApplication awardApplication : findAll()) {
			remove(awardApplication);
		}
	}

	/**
	 * Returns the number of award applications.
	 *
	 * @return the number of award applications
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AWARDAPPLICATION);

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
		return "awardApplicationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AWARDAPPLICATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AwardApplicationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the award application persistence.
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

		_finderPathWithPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathWithPaginationFindByStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"status"}, true);

		_finderPathWithoutPaginationFindByStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] {String.class.getName()}, new String[] {"status"},
			true);

		_finderPathCountByStatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] {String.class.getName()}, new String[] {"status"},
			false);

		_finderPathWithPaginationFindByStatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatusAndUserId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"userId", "status"}, true);

		_finderPathWithoutPaginationFindByStatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatusAndUserId",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"userId", "status"}, true);

		_finderPathCountByStatusAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatusAndUserId",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"userId", "status"}, false);

		_finderPathFetchByAwardApplicationId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByAwardApplicationId",
			new String[] {Long.class.getName()},
			new String[] {"awardApplicationId"}, true);

		_finderPathCountByAwardApplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAwardApplicationId", new String[] {Long.class.getName()},
			new String[] {"awardApplicationId"}, false);

		_finderPathFetchByCompetitionName = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByCompetitionName",
			new String[] {String.class.getName()},
			new String[] {"competitionName"}, true);

		_finderPathCountByCompetitionName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompetitionName",
			new String[] {String.class.getName()},
			new String[] {"competitionName"}, false);

		_finderPathWithPaginationFindByAssociationUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssociationUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"associationUserId"}, true);

		_finderPathWithoutPaginationFindByAssociationUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAssociationUserId", new String[] {Long.class.getName()},
			new String[] {"associationUserId"}, true);

		_finderPathCountByAssociationUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAssociationUserId", new String[] {Long.class.getName()},
			new String[] {"associationUserId"}, false);

		AwardApplicationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AwardApplicationUtil.setPersistence(null);

		entityCache.removeCache(AwardApplicationImpl.class.getName());
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

	private static final String _SQL_SELECT_AWARDAPPLICATION =
		"SELECT awardApplication FROM AwardApplication awardApplication";

	private static final String _SQL_SELECT_AWARDAPPLICATION_WHERE =
		"SELECT awardApplication FROM AwardApplication awardApplication WHERE ";

	private static final String _SQL_COUNT_AWARDAPPLICATION =
		"SELECT COUNT(awardApplication) FROM AwardApplication awardApplication";

	private static final String _SQL_COUNT_AWARDAPPLICATION_WHERE =
		"SELECT COUNT(awardApplication) FROM AwardApplication awardApplication WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "awardApplication.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AwardApplication exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AwardApplication exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AwardApplicationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}