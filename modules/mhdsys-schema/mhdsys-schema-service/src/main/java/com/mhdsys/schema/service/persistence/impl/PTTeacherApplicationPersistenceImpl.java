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

import com.mhdsys.schema.exception.NoSuchPTTeacherApplicationException;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.model.PTTeacherApplicationTable;
import com.mhdsys.schema.model.impl.PTTeacherApplicationImpl;
import com.mhdsys.schema.model.impl.PTTeacherApplicationModelImpl;
import com.mhdsys.schema.service.persistence.PTTeacherApplicationPersistence;
import com.mhdsys.schema.service.persistence.PTTeacherApplicationUtil;
import com.mhdsys.schema.service.persistence.impl.constants.mhdsysPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the pt teacher application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = PTTeacherApplicationPersistence.class)
public class PTTeacherApplicationPersistenceImpl
	extends BasePersistenceImpl<PTTeacherApplication>
	implements PTTeacherApplicationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PTTeacherApplicationUtil</code> to access the pt teacher application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PTTeacherApplicationImpl.class.getName();

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
	 * Returns all the pt teacher applications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the pt teacher applications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @return the range of matching pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the pt teacher applications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findByUserId(
		long userId, int start, int end,
		OrderByComparator<PTTeacherApplication> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the pt teacher applications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findByUserId(
		long userId, int start, int end,
		OrderByComparator<PTTeacherApplication> orderByComparator,
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

		List<PTTeacherApplication> list = null;

		if (useFinderCache) {
			list = (List<PTTeacherApplication>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PTTeacherApplication ptTeacherApplication : list) {
					if (userId != ptTeacherApplication.getUserId()) {
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

			sb.append(_SQL_SELECT_PTTEACHERAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PTTeacherApplicationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<PTTeacherApplication>)QueryUtil.list(
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
	 * Returns the first pt teacher application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching pt teacher application
	 * @throws NoSuchPTTeacherApplicationException if a matching pt teacher application could not be found
	 */
	@Override
	public PTTeacherApplication findByUserId_First(
			long userId,
			OrderByComparator<PTTeacherApplication> orderByComparator)
		throws NoSuchPTTeacherApplicationException {

		PTTeacherApplication ptTeacherApplication = fetchByUserId_First(
			userId, orderByComparator);

		if (ptTeacherApplication != null) {
			return ptTeacherApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchPTTeacherApplicationException(sb.toString());
	}

	/**
	 * Returns the first pt teacher application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching pt teacher application, or <code>null</code> if a matching pt teacher application could not be found
	 */
	@Override
	public PTTeacherApplication fetchByUserId_First(
		long userId,
		OrderByComparator<PTTeacherApplication> orderByComparator) {

		List<PTTeacherApplication> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last pt teacher application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching pt teacher application
	 * @throws NoSuchPTTeacherApplicationException if a matching pt teacher application could not be found
	 */
	@Override
	public PTTeacherApplication findByUserId_Last(
			long userId,
			OrderByComparator<PTTeacherApplication> orderByComparator)
		throws NoSuchPTTeacherApplicationException {

		PTTeacherApplication ptTeacherApplication = fetchByUserId_Last(
			userId, orderByComparator);

		if (ptTeacherApplication != null) {
			return ptTeacherApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchPTTeacherApplicationException(sb.toString());
	}

	/**
	 * Returns the last pt teacher application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching pt teacher application, or <code>null</code> if a matching pt teacher application could not be found
	 */
	@Override
	public PTTeacherApplication fetchByUserId_Last(
		long userId,
		OrderByComparator<PTTeacherApplication> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<PTTeacherApplication> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the pt teacher applications before and after the current pt teacher application in the ordered set where userId = &#63;.
	 *
	 * @param ptTeacherApplicationId the primary key of the current pt teacher application
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next pt teacher application
	 * @throws NoSuchPTTeacherApplicationException if a pt teacher application with the primary key could not be found
	 */
	@Override
	public PTTeacherApplication[] findByUserId_PrevAndNext(
			long ptTeacherApplicationId, long userId,
			OrderByComparator<PTTeacherApplication> orderByComparator)
		throws NoSuchPTTeacherApplicationException {

		PTTeacherApplication ptTeacherApplication = findByPrimaryKey(
			ptTeacherApplicationId);

		Session session = null;

		try {
			session = openSession();

			PTTeacherApplication[] array = new PTTeacherApplicationImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, ptTeacherApplication, userId, orderByComparator, true);

			array[1] = ptTeacherApplication;

			array[2] = getByUserId_PrevAndNext(
				session, ptTeacherApplication, userId, orderByComparator,
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

	protected PTTeacherApplication getByUserId_PrevAndNext(
		Session session, PTTeacherApplication ptTeacherApplication, long userId,
		OrderByComparator<PTTeacherApplication> orderByComparator,
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

		sb.append(_SQL_SELECT_PTTEACHERAPPLICATION_WHERE);

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
			sb.append(PTTeacherApplicationModelImpl.ORDER_BY_JPQL);
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
						ptTeacherApplication)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PTTeacherApplication> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the pt teacher applications where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (PTTeacherApplication ptTeacherApplication :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ptTeacherApplication);
		}
	}

	/**
	 * Returns the number of pt teacher applications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching pt teacher applications
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PTTEACHERAPPLICATION_WHERE);

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
		"ptTeacherApplication.userId = ?";

	private FinderPath _finderPathWithPaginationFindBystatus;
	private FinderPath _finderPathWithoutPaginationFindBystatus;
	private FinderPath _finderPathCountBystatus;

	/**
	 * Returns all the pt teacher applications where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findBystatus(long status) {
		return findBystatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the pt teacher applications where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @return the range of matching pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findBystatus(
		long status, int start, int end) {

		return findBystatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the pt teacher applications where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findBystatus(
		long status, int start, int end,
		OrderByComparator<PTTeacherApplication> orderByComparator) {

		return findBystatus(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the pt teacher applications where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findBystatus(
		long status, int start, int end,
		OrderByComparator<PTTeacherApplication> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBystatus;
				finderArgs = new Object[] {status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBystatus;
			finderArgs = new Object[] {status, start, end, orderByComparator};
		}

		List<PTTeacherApplication> list = null;

		if (useFinderCache) {
			list = (List<PTTeacherApplication>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PTTeacherApplication ptTeacherApplication : list) {
					if (status != ptTeacherApplication.getStatus()) {
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

			sb.append(_SQL_SELECT_PTTEACHERAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(PTTeacherApplicationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(status);

				list = (List<PTTeacherApplication>)QueryUtil.list(
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
	 * Returns the first pt teacher application in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching pt teacher application
	 * @throws NoSuchPTTeacherApplicationException if a matching pt teacher application could not be found
	 */
	@Override
	public PTTeacherApplication findBystatus_First(
			long status,
			OrderByComparator<PTTeacherApplication> orderByComparator)
		throws NoSuchPTTeacherApplicationException {

		PTTeacherApplication ptTeacherApplication = fetchBystatus_First(
			status, orderByComparator);

		if (ptTeacherApplication != null) {
			return ptTeacherApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchPTTeacherApplicationException(sb.toString());
	}

	/**
	 * Returns the first pt teacher application in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching pt teacher application, or <code>null</code> if a matching pt teacher application could not be found
	 */
	@Override
	public PTTeacherApplication fetchBystatus_First(
		long status,
		OrderByComparator<PTTeacherApplication> orderByComparator) {

		List<PTTeacherApplication> list = findBystatus(
			status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last pt teacher application in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching pt teacher application
	 * @throws NoSuchPTTeacherApplicationException if a matching pt teacher application could not be found
	 */
	@Override
	public PTTeacherApplication findBystatus_Last(
			long status,
			OrderByComparator<PTTeacherApplication> orderByComparator)
		throws NoSuchPTTeacherApplicationException {

		PTTeacherApplication ptTeacherApplication = fetchBystatus_Last(
			status, orderByComparator);

		if (ptTeacherApplication != null) {
			return ptTeacherApplication;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchPTTeacherApplicationException(sb.toString());
	}

	/**
	 * Returns the last pt teacher application in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching pt teacher application, or <code>null</code> if a matching pt teacher application could not be found
	 */
	@Override
	public PTTeacherApplication fetchBystatus_Last(
		long status,
		OrderByComparator<PTTeacherApplication> orderByComparator) {

		int count = countBystatus(status);

		if (count == 0) {
			return null;
		}

		List<PTTeacherApplication> list = findBystatus(
			status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the pt teacher applications before and after the current pt teacher application in the ordered set where status = &#63;.
	 *
	 * @param ptTeacherApplicationId the primary key of the current pt teacher application
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next pt teacher application
	 * @throws NoSuchPTTeacherApplicationException if a pt teacher application with the primary key could not be found
	 */
	@Override
	public PTTeacherApplication[] findBystatus_PrevAndNext(
			long ptTeacherApplicationId, long status,
			OrderByComparator<PTTeacherApplication> orderByComparator)
		throws NoSuchPTTeacherApplicationException {

		PTTeacherApplication ptTeacherApplication = findByPrimaryKey(
			ptTeacherApplicationId);

		Session session = null;

		try {
			session = openSession();

			PTTeacherApplication[] array = new PTTeacherApplicationImpl[3];

			array[0] = getBystatus_PrevAndNext(
				session, ptTeacherApplication, status, orderByComparator, true);

			array[1] = ptTeacherApplication;

			array[2] = getBystatus_PrevAndNext(
				session, ptTeacherApplication, status, orderByComparator,
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

	protected PTTeacherApplication getBystatus_PrevAndNext(
		Session session, PTTeacherApplication ptTeacherApplication, long status,
		OrderByComparator<PTTeacherApplication> orderByComparator,
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

		sb.append(_SQL_SELECT_PTTEACHERAPPLICATION_WHERE);

		sb.append(_FINDER_COLUMN_STATUS_STATUS_2);

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
			sb.append(PTTeacherApplicationModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						ptTeacherApplication)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<PTTeacherApplication> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the pt teacher applications where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeBystatus(long status) {
		for (PTTeacherApplication ptTeacherApplication :
				findBystatus(
					status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ptTeacherApplication);
		}
	}

	/**
	 * Returns the number of pt teacher applications where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching pt teacher applications
	 */
	@Override
	public int countBystatus(long status) {
		FinderPath finderPath = _finderPathCountBystatus;

		Object[] finderArgs = new Object[] {status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PTTEACHERAPPLICATION_WHERE);

			sb.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(status);

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
		"ptTeacherApplication.status = ?";

	public PTTeacherApplicationPersistenceImpl() {
		setModelClass(PTTeacherApplication.class);

		setModelImplClass(PTTeacherApplicationImpl.class);
		setModelPKClass(long.class);

		setTable(PTTeacherApplicationTable.INSTANCE);
	}

	/**
	 * Caches the pt teacher application in the entity cache if it is enabled.
	 *
	 * @param ptTeacherApplication the pt teacher application
	 */
	@Override
	public void cacheResult(PTTeacherApplication ptTeacherApplication) {
		entityCache.putResult(
			PTTeacherApplicationImpl.class,
			ptTeacherApplication.getPrimaryKey(), ptTeacherApplication);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the pt teacher applications in the entity cache if it is enabled.
	 *
	 * @param ptTeacherApplications the pt teacher applications
	 */
	@Override
	public void cacheResult(List<PTTeacherApplication> ptTeacherApplications) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (ptTeacherApplications.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (PTTeacherApplication ptTeacherApplication :
				ptTeacherApplications) {

			if (entityCache.getResult(
					PTTeacherApplicationImpl.class,
					ptTeacherApplication.getPrimaryKey()) == null) {

				cacheResult(ptTeacherApplication);
			}
		}
	}

	/**
	 * Clears the cache for all pt teacher applications.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PTTeacherApplicationImpl.class);

		finderCache.clearCache(PTTeacherApplicationImpl.class);
	}

	/**
	 * Clears the cache for the pt teacher application.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PTTeacherApplication ptTeacherApplication) {
		entityCache.removeResult(
			PTTeacherApplicationImpl.class, ptTeacherApplication);
	}

	@Override
	public void clearCache(List<PTTeacherApplication> ptTeacherApplications) {
		for (PTTeacherApplication ptTeacherApplication :
				ptTeacherApplications) {

			entityCache.removeResult(
				PTTeacherApplicationImpl.class, ptTeacherApplication);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(PTTeacherApplicationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				PTTeacherApplicationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new pt teacher application with the primary key. Does not add the pt teacher application to the database.
	 *
	 * @param ptTeacherApplicationId the primary key for the new pt teacher application
	 * @return the new pt teacher application
	 */
	@Override
	public PTTeacherApplication create(long ptTeacherApplicationId) {
		PTTeacherApplication ptTeacherApplication =
			new PTTeacherApplicationImpl();

		ptTeacherApplication.setNew(true);
		ptTeacherApplication.setPrimaryKey(ptTeacherApplicationId);

		return ptTeacherApplication;
	}

	/**
	 * Removes the pt teacher application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ptTeacherApplicationId the primary key of the pt teacher application
	 * @return the pt teacher application that was removed
	 * @throws NoSuchPTTeacherApplicationException if a pt teacher application with the primary key could not be found
	 */
	@Override
	public PTTeacherApplication remove(long ptTeacherApplicationId)
		throws NoSuchPTTeacherApplicationException {

		return remove((Serializable)ptTeacherApplicationId);
	}

	/**
	 * Removes the pt teacher application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the pt teacher application
	 * @return the pt teacher application that was removed
	 * @throws NoSuchPTTeacherApplicationException if a pt teacher application with the primary key could not be found
	 */
	@Override
	public PTTeacherApplication remove(Serializable primaryKey)
		throws NoSuchPTTeacherApplicationException {

		Session session = null;

		try {
			session = openSession();

			PTTeacherApplication ptTeacherApplication =
				(PTTeacherApplication)session.get(
					PTTeacherApplicationImpl.class, primaryKey);

			if (ptTeacherApplication == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPTTeacherApplicationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ptTeacherApplication);
		}
		catch (NoSuchPTTeacherApplicationException noSuchEntityException) {
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
	protected PTTeacherApplication removeImpl(
		PTTeacherApplication ptTeacherApplication) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ptTeacherApplication)) {
				ptTeacherApplication = (PTTeacherApplication)session.get(
					PTTeacherApplicationImpl.class,
					ptTeacherApplication.getPrimaryKeyObj());
			}

			if (ptTeacherApplication != null) {
				session.delete(ptTeacherApplication);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ptTeacherApplication != null) {
			clearCache(ptTeacherApplication);
		}

		return ptTeacherApplication;
	}

	@Override
	public PTTeacherApplication updateImpl(
		PTTeacherApplication ptTeacherApplication) {

		boolean isNew = ptTeacherApplication.isNew();

		if (!(ptTeacherApplication instanceof PTTeacherApplicationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ptTeacherApplication.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					ptTeacherApplication);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ptTeacherApplication proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PTTeacherApplication implementation " +
					ptTeacherApplication.getClass());
		}

		PTTeacherApplicationModelImpl ptTeacherApplicationModelImpl =
			(PTTeacherApplicationModelImpl)ptTeacherApplication;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (ptTeacherApplication.getCreateDate() == null)) {
			if (serviceContext == null) {
				ptTeacherApplication.setCreateDate(date);
			}
			else {
				ptTeacherApplication.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!ptTeacherApplicationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ptTeacherApplication.setModifiedDate(date);
			}
			else {
				ptTeacherApplication.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ptTeacherApplication);
			}
			else {
				ptTeacherApplication = (PTTeacherApplication)session.merge(
					ptTeacherApplication);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			PTTeacherApplicationImpl.class, ptTeacherApplicationModelImpl,
			false, true);

		if (isNew) {
			ptTeacherApplication.setNew(false);
		}

		ptTeacherApplication.resetOriginalValues();

		return ptTeacherApplication;
	}

	/**
	 * Returns the pt teacher application with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the pt teacher application
	 * @return the pt teacher application
	 * @throws NoSuchPTTeacherApplicationException if a pt teacher application with the primary key could not be found
	 */
	@Override
	public PTTeacherApplication findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPTTeacherApplicationException {

		PTTeacherApplication ptTeacherApplication = fetchByPrimaryKey(
			primaryKey);

		if (ptTeacherApplication == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPTTeacherApplicationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ptTeacherApplication;
	}

	/**
	 * Returns the pt teacher application with the primary key or throws a <code>NoSuchPTTeacherApplicationException</code> if it could not be found.
	 *
	 * @param ptTeacherApplicationId the primary key of the pt teacher application
	 * @return the pt teacher application
	 * @throws NoSuchPTTeacherApplicationException if a pt teacher application with the primary key could not be found
	 */
	@Override
	public PTTeacherApplication findByPrimaryKey(long ptTeacherApplicationId)
		throws NoSuchPTTeacherApplicationException {

		return findByPrimaryKey((Serializable)ptTeacherApplicationId);
	}

	/**
	 * Returns the pt teacher application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ptTeacherApplicationId the primary key of the pt teacher application
	 * @return the pt teacher application, or <code>null</code> if a pt teacher application with the primary key could not be found
	 */
	@Override
	public PTTeacherApplication fetchByPrimaryKey(long ptTeacherApplicationId) {
		return fetchByPrimaryKey((Serializable)ptTeacherApplicationId);
	}

	/**
	 * Returns all the pt teacher applications.
	 *
	 * @return the pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the pt teacher applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @return the range of pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the pt teacher applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findAll(
		int start, int end,
		OrderByComparator<PTTeacherApplication> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the pt teacher applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PTTeacherApplicationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of pt teacher applications
	 * @param end the upper bound of the range of pt teacher applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of pt teacher applications
	 */
	@Override
	public List<PTTeacherApplication> findAll(
		int start, int end,
		OrderByComparator<PTTeacherApplication> orderByComparator,
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

		List<PTTeacherApplication> list = null;

		if (useFinderCache) {
			list = (List<PTTeacherApplication>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PTTEACHERAPPLICATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PTTEACHERAPPLICATION;

				sql = sql.concat(PTTeacherApplicationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<PTTeacherApplication>)QueryUtil.list(
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
	 * Removes all the pt teacher applications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PTTeacherApplication ptTeacherApplication : findAll()) {
			remove(ptTeacherApplication);
		}
	}

	/**
	 * Returns the number of pt teacher applications.
	 *
	 * @return the number of pt teacher applications
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
					_SQL_COUNT_PTTEACHERAPPLICATION);

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
		return "ptTeacherApplicationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PTTEACHERAPPLICATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PTTeacherApplicationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the pt teacher application persistence.
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

		_finderPathWithPaginationFindBystatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBystatus",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"status"}, true);

		_finderPathWithoutPaginationFindBystatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBystatus",
			new String[] {Long.class.getName()}, new String[] {"status"}, true);

		_finderPathCountBystatus = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBystatus",
			new String[] {Long.class.getName()}, new String[] {"status"},
			false);

		PTTeacherApplicationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		PTTeacherApplicationUtil.setPersistence(null);

		entityCache.removeCache(PTTeacherApplicationImpl.class.getName());
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

	private static final String _SQL_SELECT_PTTEACHERAPPLICATION =
		"SELECT ptTeacherApplication FROM PTTeacherApplication ptTeacherApplication";

	private static final String _SQL_SELECT_PTTEACHERAPPLICATION_WHERE =
		"SELECT ptTeacherApplication FROM PTTeacherApplication ptTeacherApplication WHERE ";

	private static final String _SQL_COUNT_PTTEACHERAPPLICATION =
		"SELECT COUNT(ptTeacherApplication) FROM PTTeacherApplication ptTeacherApplication";

	private static final String _SQL_COUNT_PTTEACHERAPPLICATION_WHERE =
		"SELECT COUNT(ptTeacherApplication) FROM PTTeacherApplication ptTeacherApplication WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"ptTeacherApplication.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PTTeacherApplication exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No PTTeacherApplication exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PTTeacherApplicationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}