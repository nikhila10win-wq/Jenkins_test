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

import com.mhdsys.schema.exception.NoSuchAspiringAthleteException;
import com.mhdsys.schema.model.AspiringAthlete;
import com.mhdsys.schema.model.AspiringAthleteTable;
import com.mhdsys.schema.model.impl.AspiringAthleteImpl;
import com.mhdsys.schema.model.impl.AspiringAthleteModelImpl;
import com.mhdsys.schema.service.persistence.AspiringAthletePersistence;
import com.mhdsys.schema.service.persistence.AspiringAthleteUtil;
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
 * The persistence implementation for the aspiring athlete service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AspiringAthletePersistence.class)
public class AspiringAthletePersistenceImpl
	extends BasePersistenceImpl<AspiringAthlete>
	implements AspiringAthletePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AspiringAthleteUtil</code> to access the aspiring athlete persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AspiringAthleteImpl.class.getName();

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
	 * Returns all the aspiring athletes where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching aspiring athletes
	 */
	@Override
	public List<AspiringAthlete> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the aspiring athletes where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AspiringAthleteModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of aspiring athletes
	 * @param end the upper bound of the range of aspiring athletes (not inclusive)
	 * @return the range of matching aspiring athletes
	 */
	@Override
	public List<AspiringAthlete> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the aspiring athletes where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AspiringAthleteModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of aspiring athletes
	 * @param end the upper bound of the range of aspiring athletes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching aspiring athletes
	 */
	@Override
	public List<AspiringAthlete> findByUserId(
		long userId, int start, int end,
		OrderByComparator<AspiringAthlete> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the aspiring athletes where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AspiringAthleteModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of aspiring athletes
	 * @param end the upper bound of the range of aspiring athletes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching aspiring athletes
	 */
	@Override
	public List<AspiringAthlete> findByUserId(
		long userId, int start, int end,
		OrderByComparator<AspiringAthlete> orderByComparator,
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

		List<AspiringAthlete> list = null;

		if (useFinderCache) {
			list = (List<AspiringAthlete>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AspiringAthlete aspiringAthlete : list) {
					if (userId != aspiringAthlete.getUserId()) {
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

			sb.append(_SQL_SELECT_ASPIRINGATHLETE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AspiringAthleteModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<AspiringAthlete>)QueryUtil.list(
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
	 * Returns the first aspiring athlete in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching aspiring athlete
	 * @throws NoSuchAspiringAthleteException if a matching aspiring athlete could not be found
	 */
	@Override
	public AspiringAthlete findByUserId_First(
			long userId, OrderByComparator<AspiringAthlete> orderByComparator)
		throws NoSuchAspiringAthleteException {

		AspiringAthlete aspiringAthlete = fetchByUserId_First(
			userId, orderByComparator);

		if (aspiringAthlete != null) {
			return aspiringAthlete;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchAspiringAthleteException(sb.toString());
	}

	/**
	 * Returns the first aspiring athlete in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching aspiring athlete, or <code>null</code> if a matching aspiring athlete could not be found
	 */
	@Override
	public AspiringAthlete fetchByUserId_First(
		long userId, OrderByComparator<AspiringAthlete> orderByComparator) {

		List<AspiringAthlete> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last aspiring athlete in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching aspiring athlete
	 * @throws NoSuchAspiringAthleteException if a matching aspiring athlete could not be found
	 */
	@Override
	public AspiringAthlete findByUserId_Last(
			long userId, OrderByComparator<AspiringAthlete> orderByComparator)
		throws NoSuchAspiringAthleteException {

		AspiringAthlete aspiringAthlete = fetchByUserId_Last(
			userId, orderByComparator);

		if (aspiringAthlete != null) {
			return aspiringAthlete;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchAspiringAthleteException(sb.toString());
	}

	/**
	 * Returns the last aspiring athlete in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching aspiring athlete, or <code>null</code> if a matching aspiring athlete could not be found
	 */
	@Override
	public AspiringAthlete fetchByUserId_Last(
		long userId, OrderByComparator<AspiringAthlete> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<AspiringAthlete> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the aspiring athletes before and after the current aspiring athlete in the ordered set where userId = &#63;.
	 *
	 * @param aspiringAthleteId the primary key of the current aspiring athlete
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next aspiring athlete
	 * @throws NoSuchAspiringAthleteException if a aspiring athlete with the primary key could not be found
	 */
	@Override
	public AspiringAthlete[] findByUserId_PrevAndNext(
			long aspiringAthleteId, long userId,
			OrderByComparator<AspiringAthlete> orderByComparator)
		throws NoSuchAspiringAthleteException {

		AspiringAthlete aspiringAthlete = findByPrimaryKey(aspiringAthleteId);

		Session session = null;

		try {
			session = openSession();

			AspiringAthlete[] array = new AspiringAthleteImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, aspiringAthlete, userId, orderByComparator, true);

			array[1] = aspiringAthlete;

			array[2] = getByUserId_PrevAndNext(
				session, aspiringAthlete, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AspiringAthlete getByUserId_PrevAndNext(
		Session session, AspiringAthlete aspiringAthlete, long userId,
		OrderByComparator<AspiringAthlete> orderByComparator,
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

		sb.append(_SQL_SELECT_ASPIRINGATHLETE_WHERE);

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
			sb.append(AspiringAthleteModelImpl.ORDER_BY_JPQL);
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
						aspiringAthlete)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AspiringAthlete> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the aspiring athletes where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (AspiringAthlete aspiringAthlete :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(aspiringAthlete);
		}
	}

	/**
	 * Returns the number of aspiring athletes where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching aspiring athletes
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ASPIRINGATHLETE_WHERE);

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
		"aspiringAthlete.userId = ?";

	public AspiringAthletePersistenceImpl() {
		setModelClass(AspiringAthlete.class);

		setModelImplClass(AspiringAthleteImpl.class);
		setModelPKClass(long.class);

		setTable(AspiringAthleteTable.INSTANCE);
	}

	/**
	 * Caches the aspiring athlete in the entity cache if it is enabled.
	 *
	 * @param aspiringAthlete the aspiring athlete
	 */
	@Override
	public void cacheResult(AspiringAthlete aspiringAthlete) {
		entityCache.putResult(
			AspiringAthleteImpl.class, aspiringAthlete.getPrimaryKey(),
			aspiringAthlete);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the aspiring athletes in the entity cache if it is enabled.
	 *
	 * @param aspiringAthletes the aspiring athletes
	 */
	@Override
	public void cacheResult(List<AspiringAthlete> aspiringAthletes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (aspiringAthletes.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AspiringAthlete aspiringAthlete : aspiringAthletes) {
			if (entityCache.getResult(
					AspiringAthleteImpl.class,
					aspiringAthlete.getPrimaryKey()) == null) {

				cacheResult(aspiringAthlete);
			}
		}
	}

	/**
	 * Clears the cache for all aspiring athletes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AspiringAthleteImpl.class);

		finderCache.clearCache(AspiringAthleteImpl.class);
	}

	/**
	 * Clears the cache for the aspiring athlete.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AspiringAthlete aspiringAthlete) {
		entityCache.removeResult(AspiringAthleteImpl.class, aspiringAthlete);
	}

	@Override
	public void clearCache(List<AspiringAthlete> aspiringAthletes) {
		for (AspiringAthlete aspiringAthlete : aspiringAthletes) {
			entityCache.removeResult(
				AspiringAthleteImpl.class, aspiringAthlete);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AspiringAthleteImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AspiringAthleteImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new aspiring athlete with the primary key. Does not add the aspiring athlete to the database.
	 *
	 * @param aspiringAthleteId the primary key for the new aspiring athlete
	 * @return the new aspiring athlete
	 */
	@Override
	public AspiringAthlete create(long aspiringAthleteId) {
		AspiringAthlete aspiringAthlete = new AspiringAthleteImpl();

		aspiringAthlete.setNew(true);
		aspiringAthlete.setPrimaryKey(aspiringAthleteId);

		return aspiringAthlete;
	}

	/**
	 * Removes the aspiring athlete with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param aspiringAthleteId the primary key of the aspiring athlete
	 * @return the aspiring athlete that was removed
	 * @throws NoSuchAspiringAthleteException if a aspiring athlete with the primary key could not be found
	 */
	@Override
	public AspiringAthlete remove(long aspiringAthleteId)
		throws NoSuchAspiringAthleteException {

		return remove((Serializable)aspiringAthleteId);
	}

	/**
	 * Removes the aspiring athlete with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the aspiring athlete
	 * @return the aspiring athlete that was removed
	 * @throws NoSuchAspiringAthleteException if a aspiring athlete with the primary key could not be found
	 */
	@Override
	public AspiringAthlete remove(Serializable primaryKey)
		throws NoSuchAspiringAthleteException {

		Session session = null;

		try {
			session = openSession();

			AspiringAthlete aspiringAthlete = (AspiringAthlete)session.get(
				AspiringAthleteImpl.class, primaryKey);

			if (aspiringAthlete == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAspiringAthleteException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(aspiringAthlete);
		}
		catch (NoSuchAspiringAthleteException noSuchEntityException) {
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
	protected AspiringAthlete removeImpl(AspiringAthlete aspiringAthlete) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(aspiringAthlete)) {
				aspiringAthlete = (AspiringAthlete)session.get(
					AspiringAthleteImpl.class,
					aspiringAthlete.getPrimaryKeyObj());
			}

			if (aspiringAthlete != null) {
				session.delete(aspiringAthlete);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (aspiringAthlete != null) {
			clearCache(aspiringAthlete);
		}

		return aspiringAthlete;
	}

	@Override
	public AspiringAthlete updateImpl(AspiringAthlete aspiringAthlete) {
		boolean isNew = aspiringAthlete.isNew();

		if (!(aspiringAthlete instanceof AspiringAthleteModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(aspiringAthlete.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					aspiringAthlete);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in aspiringAthlete proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AspiringAthlete implementation " +
					aspiringAthlete.getClass());
		}

		AspiringAthleteModelImpl aspiringAthleteModelImpl =
			(AspiringAthleteModelImpl)aspiringAthlete;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (aspiringAthlete.getCreateDate() == null)) {
			if (serviceContext == null) {
				aspiringAthlete.setCreateDate(date);
			}
			else {
				aspiringAthlete.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!aspiringAthleteModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				aspiringAthlete.setModifiedDate(date);
			}
			else {
				aspiringAthlete.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(aspiringAthlete);
			}
			else {
				aspiringAthlete = (AspiringAthlete)session.merge(
					aspiringAthlete);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AspiringAthleteImpl.class, aspiringAthleteModelImpl, false, true);

		if (isNew) {
			aspiringAthlete.setNew(false);
		}

		aspiringAthlete.resetOriginalValues();

		return aspiringAthlete;
	}

	/**
	 * Returns the aspiring athlete with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the aspiring athlete
	 * @return the aspiring athlete
	 * @throws NoSuchAspiringAthleteException if a aspiring athlete with the primary key could not be found
	 */
	@Override
	public AspiringAthlete findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAspiringAthleteException {

		AspiringAthlete aspiringAthlete = fetchByPrimaryKey(primaryKey);

		if (aspiringAthlete == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAspiringAthleteException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return aspiringAthlete;
	}

	/**
	 * Returns the aspiring athlete with the primary key or throws a <code>NoSuchAspiringAthleteException</code> if it could not be found.
	 *
	 * @param aspiringAthleteId the primary key of the aspiring athlete
	 * @return the aspiring athlete
	 * @throws NoSuchAspiringAthleteException if a aspiring athlete with the primary key could not be found
	 */
	@Override
	public AspiringAthlete findByPrimaryKey(long aspiringAthleteId)
		throws NoSuchAspiringAthleteException {

		return findByPrimaryKey((Serializable)aspiringAthleteId);
	}

	/**
	 * Returns the aspiring athlete with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param aspiringAthleteId the primary key of the aspiring athlete
	 * @return the aspiring athlete, or <code>null</code> if a aspiring athlete with the primary key could not be found
	 */
	@Override
	public AspiringAthlete fetchByPrimaryKey(long aspiringAthleteId) {
		return fetchByPrimaryKey((Serializable)aspiringAthleteId);
	}

	/**
	 * Returns all the aspiring athletes.
	 *
	 * @return the aspiring athletes
	 */
	@Override
	public List<AspiringAthlete> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the aspiring athletes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AspiringAthleteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of aspiring athletes
	 * @param end the upper bound of the range of aspiring athletes (not inclusive)
	 * @return the range of aspiring athletes
	 */
	@Override
	public List<AspiringAthlete> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the aspiring athletes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AspiringAthleteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of aspiring athletes
	 * @param end the upper bound of the range of aspiring athletes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of aspiring athletes
	 */
	@Override
	public List<AspiringAthlete> findAll(
		int start, int end,
		OrderByComparator<AspiringAthlete> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the aspiring athletes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AspiringAthleteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of aspiring athletes
	 * @param end the upper bound of the range of aspiring athletes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of aspiring athletes
	 */
	@Override
	public List<AspiringAthlete> findAll(
		int start, int end,
		OrderByComparator<AspiringAthlete> orderByComparator,
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

		List<AspiringAthlete> list = null;

		if (useFinderCache) {
			list = (List<AspiringAthlete>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ASPIRINGATHLETE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ASPIRINGATHLETE;

				sql = sql.concat(AspiringAthleteModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AspiringAthlete>)QueryUtil.list(
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
	 * Removes all the aspiring athletes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AspiringAthlete aspiringAthlete : findAll()) {
			remove(aspiringAthlete);
		}
	}

	/**
	 * Returns the number of aspiring athletes.
	 *
	 * @return the number of aspiring athletes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ASPIRINGATHLETE);

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
		return "aspiringAthleteId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ASPIRINGATHLETE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AspiringAthleteModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the aspiring athlete persistence.
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

		AspiringAthleteUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AspiringAthleteUtil.setPersistence(null);

		entityCache.removeCache(AspiringAthleteImpl.class.getName());
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

	private static final String _SQL_SELECT_ASPIRINGATHLETE =
		"SELECT aspiringAthlete FROM AspiringAthlete aspiringAthlete";

	private static final String _SQL_SELECT_ASPIRINGATHLETE_WHERE =
		"SELECT aspiringAthlete FROM AspiringAthlete aspiringAthlete WHERE ";

	private static final String _SQL_COUNT_ASPIRINGATHLETE =
		"SELECT COUNT(aspiringAthlete) FROM AspiringAthlete aspiringAthlete";

	private static final String _SQL_COUNT_ASPIRINGATHLETE_WHERE =
		"SELECT COUNT(aspiringAthlete) FROM AspiringAthlete aspiringAthlete WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "aspiringAthlete.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AspiringAthlete exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AspiringAthlete exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AspiringAthletePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}