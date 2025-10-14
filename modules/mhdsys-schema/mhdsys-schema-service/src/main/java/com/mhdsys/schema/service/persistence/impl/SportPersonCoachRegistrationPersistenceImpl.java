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

import com.mhdsys.schema.exception.NoSuchSportPersonCoachRegistrationException;
import com.mhdsys.schema.model.SportPersonCoachRegistration;
import com.mhdsys.schema.model.SportPersonCoachRegistrationTable;
import com.mhdsys.schema.model.impl.SportPersonCoachRegistrationImpl;
import com.mhdsys.schema.model.impl.SportPersonCoachRegistrationModelImpl;
import com.mhdsys.schema.service.persistence.SportPersonCoachRegistrationPersistence;
import com.mhdsys.schema.service.persistence.SportPersonCoachRegistrationUtil;
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
 * The persistence implementation for the sport person coach registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SportPersonCoachRegistrationPersistence.class)
public class SportPersonCoachRegistrationPersistenceImpl
	extends BasePersistenceImpl<SportPersonCoachRegistration>
	implements SportPersonCoachRegistrationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SportPersonCoachRegistrationUtil</code> to access the sport person coach registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SportPersonCoachRegistrationImpl.class.getName();

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
	 * Returns all the sport person coach registrations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching sport person coach registrations
	 */
	@Override
	public List<SportPersonCoachRegistration> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sport person coach registrations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportPersonCoachRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of sport person coach registrations
	 * @param end the upper bound of the range of sport person coach registrations (not inclusive)
	 * @return the range of matching sport person coach registrations
	 */
	@Override
	public List<SportPersonCoachRegistration> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sport person coach registrations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportPersonCoachRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of sport person coach registrations
	 * @param end the upper bound of the range of sport person coach registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sport person coach registrations
	 */
	@Override
	public List<SportPersonCoachRegistration> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SportPersonCoachRegistration> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sport person coach registrations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportPersonCoachRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of sport person coach registrations
	 * @param end the upper bound of the range of sport person coach registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching sport person coach registrations
	 */
	@Override
	public List<SportPersonCoachRegistration> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SportPersonCoachRegistration> orderByComparator,
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

		List<SportPersonCoachRegistration> list = null;

		if (useFinderCache) {
			list = (List<SportPersonCoachRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SportPersonCoachRegistration sportPersonCoachRegistration :
						list) {

					if (userId != sportPersonCoachRegistration.getUserId()) {
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

			sb.append(_SQL_SELECT_SPORTPERSONCOACHREGISTRATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SportPersonCoachRegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<SportPersonCoachRegistration>)QueryUtil.list(
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
	 * Returns the first sport person coach registration in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sport person coach registration
	 * @throws NoSuchSportPersonCoachRegistrationException if a matching sport person coach registration could not be found
	 */
	@Override
	public SportPersonCoachRegistration findByUserId_First(
			long userId,
			OrderByComparator<SportPersonCoachRegistration> orderByComparator)
		throws NoSuchSportPersonCoachRegistrationException {

		SportPersonCoachRegistration sportPersonCoachRegistration =
			fetchByUserId_First(userId, orderByComparator);

		if (sportPersonCoachRegistration != null) {
			return sportPersonCoachRegistration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchSportPersonCoachRegistrationException(sb.toString());
	}

	/**
	 * Returns the first sport person coach registration in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sport person coach registration, or <code>null</code> if a matching sport person coach registration could not be found
	 */
	@Override
	public SportPersonCoachRegistration fetchByUserId_First(
		long userId,
		OrderByComparator<SportPersonCoachRegistration> orderByComparator) {

		List<SportPersonCoachRegistration> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sport person coach registration in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sport person coach registration
	 * @throws NoSuchSportPersonCoachRegistrationException if a matching sport person coach registration could not be found
	 */
	@Override
	public SportPersonCoachRegistration findByUserId_Last(
			long userId,
			OrderByComparator<SportPersonCoachRegistration> orderByComparator)
		throws NoSuchSportPersonCoachRegistrationException {

		SportPersonCoachRegistration sportPersonCoachRegistration =
			fetchByUserId_Last(userId, orderByComparator);

		if (sportPersonCoachRegistration != null) {
			return sportPersonCoachRegistration;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchSportPersonCoachRegistrationException(sb.toString());
	}

	/**
	 * Returns the last sport person coach registration in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sport person coach registration, or <code>null</code> if a matching sport person coach registration could not be found
	 */
	@Override
	public SportPersonCoachRegistration fetchByUserId_Last(
		long userId,
		OrderByComparator<SportPersonCoachRegistration> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<SportPersonCoachRegistration> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sport person coach registrations before and after the current sport person coach registration in the ordered set where userId = &#63;.
	 *
	 * @param sportPersonRegistrationId the primary key of the current sport person coach registration
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sport person coach registration
	 * @throws NoSuchSportPersonCoachRegistrationException if a sport person coach registration with the primary key could not be found
	 */
	@Override
	public SportPersonCoachRegistration[] findByUserId_PrevAndNext(
			long sportPersonRegistrationId, long userId,
			OrderByComparator<SportPersonCoachRegistration> orderByComparator)
		throws NoSuchSportPersonCoachRegistrationException {

		SportPersonCoachRegistration sportPersonCoachRegistration =
			findByPrimaryKey(sportPersonRegistrationId);

		Session session = null;

		try {
			session = openSession();

			SportPersonCoachRegistration[] array =
				new SportPersonCoachRegistrationImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, sportPersonCoachRegistration, userId,
				orderByComparator, true);

			array[1] = sportPersonCoachRegistration;

			array[2] = getByUserId_PrevAndNext(
				session, sportPersonCoachRegistration, userId,
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

	protected SportPersonCoachRegistration getByUserId_PrevAndNext(
		Session session,
		SportPersonCoachRegistration sportPersonCoachRegistration, long userId,
		OrderByComparator<SportPersonCoachRegistration> orderByComparator,
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

		sb.append(_SQL_SELECT_SPORTPERSONCOACHREGISTRATION_WHERE);

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
			sb.append(SportPersonCoachRegistrationModelImpl.ORDER_BY_JPQL);
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
						sportPersonCoachRegistration)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SportPersonCoachRegistration> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sport person coach registrations where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (SportPersonCoachRegistration sportPersonCoachRegistration :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(sportPersonCoachRegistration);
		}
	}

	/**
	 * Returns the number of sport person coach registrations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching sport person coach registrations
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SPORTPERSONCOACHREGISTRATION_WHERE);

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
		"sportPersonCoachRegistration.userId = ?";

	public SportPersonCoachRegistrationPersistenceImpl() {
		setModelClass(SportPersonCoachRegistration.class);

		setModelImplClass(SportPersonCoachRegistrationImpl.class);
		setModelPKClass(long.class);

		setTable(SportPersonCoachRegistrationTable.INSTANCE);
	}

	/**
	 * Caches the sport person coach registration in the entity cache if it is enabled.
	 *
	 * @param sportPersonCoachRegistration the sport person coach registration
	 */
	@Override
	public void cacheResult(
		SportPersonCoachRegistration sportPersonCoachRegistration) {

		entityCache.putResult(
			SportPersonCoachRegistrationImpl.class,
			sportPersonCoachRegistration.getPrimaryKey(),
			sportPersonCoachRegistration);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the sport person coach registrations in the entity cache if it is enabled.
	 *
	 * @param sportPersonCoachRegistrations the sport person coach registrations
	 */
	@Override
	public void cacheResult(
		List<SportPersonCoachRegistration> sportPersonCoachRegistrations) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (sportPersonCoachRegistrations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SportPersonCoachRegistration sportPersonCoachRegistration :
				sportPersonCoachRegistrations) {

			if (entityCache.getResult(
					SportPersonCoachRegistrationImpl.class,
					sportPersonCoachRegistration.getPrimaryKey()) == null) {

				cacheResult(sportPersonCoachRegistration);
			}
		}
	}

	/**
	 * Clears the cache for all sport person coach registrations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SportPersonCoachRegistrationImpl.class);

		finderCache.clearCache(SportPersonCoachRegistrationImpl.class);
	}

	/**
	 * Clears the cache for the sport person coach registration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		SportPersonCoachRegistration sportPersonCoachRegistration) {

		entityCache.removeResult(
			SportPersonCoachRegistrationImpl.class,
			sportPersonCoachRegistration);
	}

	@Override
	public void clearCache(
		List<SportPersonCoachRegistration> sportPersonCoachRegistrations) {

		for (SportPersonCoachRegistration sportPersonCoachRegistration :
				sportPersonCoachRegistrations) {

			entityCache.removeResult(
				SportPersonCoachRegistrationImpl.class,
				sportPersonCoachRegistration);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(SportPersonCoachRegistrationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				SportPersonCoachRegistrationImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new sport person coach registration with the primary key. Does not add the sport person coach registration to the database.
	 *
	 * @param sportPersonRegistrationId the primary key for the new sport person coach registration
	 * @return the new sport person coach registration
	 */
	@Override
	public SportPersonCoachRegistration create(long sportPersonRegistrationId) {
		SportPersonCoachRegistration sportPersonCoachRegistration =
			new SportPersonCoachRegistrationImpl();

		sportPersonCoachRegistration.setNew(true);
		sportPersonCoachRegistration.setPrimaryKey(sportPersonRegistrationId);

		return sportPersonCoachRegistration;
	}

	/**
	 * Removes the sport person coach registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sportPersonRegistrationId the primary key of the sport person coach registration
	 * @return the sport person coach registration that was removed
	 * @throws NoSuchSportPersonCoachRegistrationException if a sport person coach registration with the primary key could not be found
	 */
	@Override
	public SportPersonCoachRegistration remove(long sportPersonRegistrationId)
		throws NoSuchSportPersonCoachRegistrationException {

		return remove((Serializable)sportPersonRegistrationId);
	}

	/**
	 * Removes the sport person coach registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sport person coach registration
	 * @return the sport person coach registration that was removed
	 * @throws NoSuchSportPersonCoachRegistrationException if a sport person coach registration with the primary key could not be found
	 */
	@Override
	public SportPersonCoachRegistration remove(Serializable primaryKey)
		throws NoSuchSportPersonCoachRegistrationException {

		Session session = null;

		try {
			session = openSession();

			SportPersonCoachRegistration sportPersonCoachRegistration =
				(SportPersonCoachRegistration)session.get(
					SportPersonCoachRegistrationImpl.class, primaryKey);

			if (sportPersonCoachRegistration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSportPersonCoachRegistrationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(sportPersonCoachRegistration);
		}
		catch (NoSuchSportPersonCoachRegistrationException
					noSuchEntityException) {

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
	protected SportPersonCoachRegistration removeImpl(
		SportPersonCoachRegistration sportPersonCoachRegistration) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sportPersonCoachRegistration)) {
				sportPersonCoachRegistration =
					(SportPersonCoachRegistration)session.get(
						SportPersonCoachRegistrationImpl.class,
						sportPersonCoachRegistration.getPrimaryKeyObj());
			}

			if (sportPersonCoachRegistration != null) {
				session.delete(sportPersonCoachRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sportPersonCoachRegistration != null) {
			clearCache(sportPersonCoachRegistration);
		}

		return sportPersonCoachRegistration;
	}

	@Override
	public SportPersonCoachRegistration updateImpl(
		SportPersonCoachRegistration sportPersonCoachRegistration) {

		boolean isNew = sportPersonCoachRegistration.isNew();

		if (!(sportPersonCoachRegistration instanceof
				SportPersonCoachRegistrationModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					sportPersonCoachRegistration.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					sportPersonCoachRegistration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in sportPersonCoachRegistration proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SportPersonCoachRegistration implementation " +
					sportPersonCoachRegistration.getClass());
		}

		SportPersonCoachRegistrationModelImpl
			sportPersonCoachRegistrationModelImpl =
				(SportPersonCoachRegistrationModelImpl)
					sportPersonCoachRegistration;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (sportPersonCoachRegistration.getCreateDate() == null)) {
			if (serviceContext == null) {
				sportPersonCoachRegistration.setCreateDate(date);
			}
			else {
				sportPersonCoachRegistration.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!sportPersonCoachRegistrationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				sportPersonCoachRegistration.setModifiedDate(date);
			}
			else {
				sportPersonCoachRegistration.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sportPersonCoachRegistration);
			}
			else {
				sportPersonCoachRegistration =
					(SportPersonCoachRegistration)session.merge(
						sportPersonCoachRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			SportPersonCoachRegistrationImpl.class,
			sportPersonCoachRegistrationModelImpl, false, true);

		if (isNew) {
			sportPersonCoachRegistration.setNew(false);
		}

		sportPersonCoachRegistration.resetOriginalValues();

		return sportPersonCoachRegistration;
	}

	/**
	 * Returns the sport person coach registration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sport person coach registration
	 * @return the sport person coach registration
	 * @throws NoSuchSportPersonCoachRegistrationException if a sport person coach registration with the primary key could not be found
	 */
	@Override
	public SportPersonCoachRegistration findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchSportPersonCoachRegistrationException {

		SportPersonCoachRegistration sportPersonCoachRegistration =
			fetchByPrimaryKey(primaryKey);

		if (sportPersonCoachRegistration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSportPersonCoachRegistrationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return sportPersonCoachRegistration;
	}

	/**
	 * Returns the sport person coach registration with the primary key or throws a <code>NoSuchSportPersonCoachRegistrationException</code> if it could not be found.
	 *
	 * @param sportPersonRegistrationId the primary key of the sport person coach registration
	 * @return the sport person coach registration
	 * @throws NoSuchSportPersonCoachRegistrationException if a sport person coach registration with the primary key could not be found
	 */
	@Override
	public SportPersonCoachRegistration findByPrimaryKey(
			long sportPersonRegistrationId)
		throws NoSuchSportPersonCoachRegistrationException {

		return findByPrimaryKey((Serializable)sportPersonRegistrationId);
	}

	/**
	 * Returns the sport person coach registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sportPersonRegistrationId the primary key of the sport person coach registration
	 * @return the sport person coach registration, or <code>null</code> if a sport person coach registration with the primary key could not be found
	 */
	@Override
	public SportPersonCoachRegistration fetchByPrimaryKey(
		long sportPersonRegistrationId) {

		return fetchByPrimaryKey((Serializable)sportPersonRegistrationId);
	}

	/**
	 * Returns all the sport person coach registrations.
	 *
	 * @return the sport person coach registrations
	 */
	@Override
	public List<SportPersonCoachRegistration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sport person coach registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportPersonCoachRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sport person coach registrations
	 * @param end the upper bound of the range of sport person coach registrations (not inclusive)
	 * @return the range of sport person coach registrations
	 */
	@Override
	public List<SportPersonCoachRegistration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sport person coach registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportPersonCoachRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sport person coach registrations
	 * @param end the upper bound of the range of sport person coach registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sport person coach registrations
	 */
	@Override
	public List<SportPersonCoachRegistration> findAll(
		int start, int end,
		OrderByComparator<SportPersonCoachRegistration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sport person coach registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SportPersonCoachRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sport person coach registrations
	 * @param end the upper bound of the range of sport person coach registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of sport person coach registrations
	 */
	@Override
	public List<SportPersonCoachRegistration> findAll(
		int start, int end,
		OrderByComparator<SportPersonCoachRegistration> orderByComparator,
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

		List<SportPersonCoachRegistration> list = null;

		if (useFinderCache) {
			list = (List<SportPersonCoachRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SPORTPERSONCOACHREGISTRATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SPORTPERSONCOACHREGISTRATION;

				sql = sql.concat(
					SportPersonCoachRegistrationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SportPersonCoachRegistration>)QueryUtil.list(
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
	 * Removes all the sport person coach registrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SportPersonCoachRegistration sportPersonCoachRegistration :
				findAll()) {

			remove(sportPersonCoachRegistration);
		}
	}

	/**
	 * Returns the number of sport person coach registrations.
	 *
	 * @return the number of sport person coach registrations
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
					_SQL_COUNT_SPORTPERSONCOACHREGISTRATION);

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
		return "sportPersonRegistrationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SPORTPERSONCOACHREGISTRATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SportPersonCoachRegistrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sport person coach registration persistence.
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

		SportPersonCoachRegistrationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SportPersonCoachRegistrationUtil.setPersistence(null);

		entityCache.removeCache(
			SportPersonCoachRegistrationImpl.class.getName());
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

	private static final String _SQL_SELECT_SPORTPERSONCOACHREGISTRATION =
		"SELECT sportPersonCoachRegistration FROM SportPersonCoachRegistration sportPersonCoachRegistration";

	private static final String _SQL_SELECT_SPORTPERSONCOACHREGISTRATION_WHERE =
		"SELECT sportPersonCoachRegistration FROM SportPersonCoachRegistration sportPersonCoachRegistration WHERE ";

	private static final String _SQL_COUNT_SPORTPERSONCOACHREGISTRATION =
		"SELECT COUNT(sportPersonCoachRegistration) FROM SportPersonCoachRegistration sportPersonCoachRegistration";

	private static final String _SQL_COUNT_SPORTPERSONCOACHREGISTRATION_WHERE =
		"SELECT COUNT(sportPersonCoachRegistration) FROM SportPersonCoachRegistration sportPersonCoachRegistration WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"sportPersonCoachRegistration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SportPersonCoachRegistration exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SportPersonCoachRegistration exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SportPersonCoachRegistrationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}