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

import com.mhdsys.schema.exception.NoSuchScoutAndGuideRegistrationException;
import com.mhdsys.schema.model.ScoutAndGuideRegistration;
import com.mhdsys.schema.model.ScoutAndGuideRegistrationTable;
import com.mhdsys.schema.model.impl.ScoutAndGuideRegistrationImpl;
import com.mhdsys.schema.model.impl.ScoutAndGuideRegistrationModelImpl;
import com.mhdsys.schema.service.persistence.ScoutAndGuideRegistrationPersistence;
import com.mhdsys.schema.service.persistence.ScoutAndGuideRegistrationUtil;
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
 * The persistence implementation for the scout and guide registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ScoutAndGuideRegistrationPersistence.class)
public class ScoutAndGuideRegistrationPersistenceImpl
	extends BasePersistenceImpl<ScoutAndGuideRegistration>
	implements ScoutAndGuideRegistrationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ScoutAndGuideRegistrationUtil</code> to access the scout and guide registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ScoutAndGuideRegistrationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns the scout and guide registration where userId = &#63; or throws a <code>NoSuchScoutAndGuideRegistrationException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching scout and guide registration
	 * @throws NoSuchScoutAndGuideRegistrationException if a matching scout and guide registration could not be found
	 */
	@Override
	public ScoutAndGuideRegistration findByUserId(long userId)
		throws NoSuchScoutAndGuideRegistrationException {

		ScoutAndGuideRegistration scoutAndGuideRegistration = fetchByUserId(
			userId);

		if (scoutAndGuideRegistration == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchScoutAndGuideRegistrationException(sb.toString());
		}

		return scoutAndGuideRegistration;
	}

	/**
	 * Returns the scout and guide registration where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching scout and guide registration, or <code>null</code> if a matching scout and guide registration could not be found
	 */
	@Override
	public ScoutAndGuideRegistration fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the scout and guide registration where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching scout and guide registration, or <code>null</code> if a matching scout and guide registration could not be found
	 */
	@Override
	public ScoutAndGuideRegistration fetchByUserId(
		long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof ScoutAndGuideRegistration) {
			ScoutAndGuideRegistration scoutAndGuideRegistration =
				(ScoutAndGuideRegistration)result;

			if (userId != scoutAndGuideRegistration.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_SCOUTANDGUIDEREGISTRATION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<ScoutAndGuideRegistration> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUserId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {userId};
							}

							_log.warn(
								"ScoutAndGuideRegistrationPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ScoutAndGuideRegistration scoutAndGuideRegistration =
						list.get(0);

					result = scoutAndGuideRegistration;

					cacheResult(scoutAndGuideRegistration);
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
			return (ScoutAndGuideRegistration)result;
		}
	}

	/**
	 * Removes the scout and guide registration where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the scout and guide registration that was removed
	 */
	@Override
	public ScoutAndGuideRegistration removeByUserId(long userId)
		throws NoSuchScoutAndGuideRegistrationException {

		ScoutAndGuideRegistration scoutAndGuideRegistration = findByUserId(
			userId);

		return remove(scoutAndGuideRegistration);
	}

	/**
	 * Returns the number of scout and guide registrations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching scout and guide registrations
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SCOUTANDGUIDEREGISTRATION_WHERE);

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
		"scoutAndGuideRegistration.userId = ?";

	public ScoutAndGuideRegistrationPersistenceImpl() {
		setModelClass(ScoutAndGuideRegistration.class);

		setModelImplClass(ScoutAndGuideRegistrationImpl.class);
		setModelPKClass(long.class);

		setTable(ScoutAndGuideRegistrationTable.INSTANCE);
	}

	/**
	 * Caches the scout and guide registration in the entity cache if it is enabled.
	 *
	 * @param scoutAndGuideRegistration the scout and guide registration
	 */
	@Override
	public void cacheResult(
		ScoutAndGuideRegistration scoutAndGuideRegistration) {

		entityCache.putResult(
			ScoutAndGuideRegistrationImpl.class,
			scoutAndGuideRegistration.getPrimaryKey(),
			scoutAndGuideRegistration);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {scoutAndGuideRegistration.getUserId()},
			scoutAndGuideRegistration);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the scout and guide registrations in the entity cache if it is enabled.
	 *
	 * @param scoutAndGuideRegistrations the scout and guide registrations
	 */
	@Override
	public void cacheResult(
		List<ScoutAndGuideRegistration> scoutAndGuideRegistrations) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (scoutAndGuideRegistrations.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ScoutAndGuideRegistration scoutAndGuideRegistration :
				scoutAndGuideRegistrations) {

			if (entityCache.getResult(
					ScoutAndGuideRegistrationImpl.class,
					scoutAndGuideRegistration.getPrimaryKey()) == null) {

				cacheResult(scoutAndGuideRegistration);
			}
		}
	}

	/**
	 * Clears the cache for all scout and guide registrations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ScoutAndGuideRegistrationImpl.class);

		finderCache.clearCache(ScoutAndGuideRegistrationImpl.class);
	}

	/**
	 * Clears the cache for the scout and guide registration.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		ScoutAndGuideRegistration scoutAndGuideRegistration) {

		entityCache.removeResult(
			ScoutAndGuideRegistrationImpl.class, scoutAndGuideRegistration);
	}

	@Override
	public void clearCache(
		List<ScoutAndGuideRegistration> scoutAndGuideRegistrations) {

		for (ScoutAndGuideRegistration scoutAndGuideRegistration :
				scoutAndGuideRegistrations) {

			entityCache.removeResult(
				ScoutAndGuideRegistrationImpl.class, scoutAndGuideRegistration);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ScoutAndGuideRegistrationImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ScoutAndGuideRegistrationImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ScoutAndGuideRegistrationModelImpl scoutAndGuideRegistrationModelImpl) {

		Object[] args = new Object[] {
			scoutAndGuideRegistrationModelImpl.getUserId()
		};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, scoutAndGuideRegistrationModelImpl);
	}

	/**
	 * Creates a new scout and guide registration with the primary key. Does not add the scout and guide registration to the database.
	 *
	 * @param scoutAndGuideRegistrationId the primary key for the new scout and guide registration
	 * @return the new scout and guide registration
	 */
	@Override
	public ScoutAndGuideRegistration create(long scoutAndGuideRegistrationId) {
		ScoutAndGuideRegistration scoutAndGuideRegistration =
			new ScoutAndGuideRegistrationImpl();

		scoutAndGuideRegistration.setNew(true);
		scoutAndGuideRegistration.setPrimaryKey(scoutAndGuideRegistrationId);

		return scoutAndGuideRegistration;
	}

	/**
	 * Removes the scout and guide registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scoutAndGuideRegistrationId the primary key of the scout and guide registration
	 * @return the scout and guide registration that was removed
	 * @throws NoSuchScoutAndGuideRegistrationException if a scout and guide registration with the primary key could not be found
	 */
	@Override
	public ScoutAndGuideRegistration remove(long scoutAndGuideRegistrationId)
		throws NoSuchScoutAndGuideRegistrationException {

		return remove((Serializable)scoutAndGuideRegistrationId);
	}

	/**
	 * Removes the scout and guide registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the scout and guide registration
	 * @return the scout and guide registration that was removed
	 * @throws NoSuchScoutAndGuideRegistrationException if a scout and guide registration with the primary key could not be found
	 */
	@Override
	public ScoutAndGuideRegistration remove(Serializable primaryKey)
		throws NoSuchScoutAndGuideRegistrationException {

		Session session = null;

		try {
			session = openSession();

			ScoutAndGuideRegistration scoutAndGuideRegistration =
				(ScoutAndGuideRegistration)session.get(
					ScoutAndGuideRegistrationImpl.class, primaryKey);

			if (scoutAndGuideRegistration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScoutAndGuideRegistrationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(scoutAndGuideRegistration);
		}
		catch (NoSuchScoutAndGuideRegistrationException noSuchEntityException) {
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
	protected ScoutAndGuideRegistration removeImpl(
		ScoutAndGuideRegistration scoutAndGuideRegistration) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scoutAndGuideRegistration)) {
				scoutAndGuideRegistration =
					(ScoutAndGuideRegistration)session.get(
						ScoutAndGuideRegistrationImpl.class,
						scoutAndGuideRegistration.getPrimaryKeyObj());
			}

			if (scoutAndGuideRegistration != null) {
				session.delete(scoutAndGuideRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (scoutAndGuideRegistration != null) {
			clearCache(scoutAndGuideRegistration);
		}

		return scoutAndGuideRegistration;
	}

	@Override
	public ScoutAndGuideRegistration updateImpl(
		ScoutAndGuideRegistration scoutAndGuideRegistration) {

		boolean isNew = scoutAndGuideRegistration.isNew();

		if (!(scoutAndGuideRegistration instanceof
				ScoutAndGuideRegistrationModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(scoutAndGuideRegistration.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					scoutAndGuideRegistration);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in scoutAndGuideRegistration proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ScoutAndGuideRegistration implementation " +
					scoutAndGuideRegistration.getClass());
		}

		ScoutAndGuideRegistrationModelImpl scoutAndGuideRegistrationModelImpl =
			(ScoutAndGuideRegistrationModelImpl)scoutAndGuideRegistration;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (scoutAndGuideRegistration.getCreateDate() == null)) {
			if (serviceContext == null) {
				scoutAndGuideRegistration.setCreateDate(date);
			}
			else {
				scoutAndGuideRegistration.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!scoutAndGuideRegistrationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				scoutAndGuideRegistration.setModifiedDate(date);
			}
			else {
				scoutAndGuideRegistration.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(scoutAndGuideRegistration);
			}
			else {
				scoutAndGuideRegistration =
					(ScoutAndGuideRegistration)session.merge(
						scoutAndGuideRegistration);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ScoutAndGuideRegistrationImpl.class,
			scoutAndGuideRegistrationModelImpl, false, true);

		cacheUniqueFindersCache(scoutAndGuideRegistrationModelImpl);

		if (isNew) {
			scoutAndGuideRegistration.setNew(false);
		}

		scoutAndGuideRegistration.resetOriginalValues();

		return scoutAndGuideRegistration;
	}

	/**
	 * Returns the scout and guide registration with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the scout and guide registration
	 * @return the scout and guide registration
	 * @throws NoSuchScoutAndGuideRegistrationException if a scout and guide registration with the primary key could not be found
	 */
	@Override
	public ScoutAndGuideRegistration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScoutAndGuideRegistrationException {

		ScoutAndGuideRegistration scoutAndGuideRegistration = fetchByPrimaryKey(
			primaryKey);

		if (scoutAndGuideRegistration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScoutAndGuideRegistrationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return scoutAndGuideRegistration;
	}

	/**
	 * Returns the scout and guide registration with the primary key or throws a <code>NoSuchScoutAndGuideRegistrationException</code> if it could not be found.
	 *
	 * @param scoutAndGuideRegistrationId the primary key of the scout and guide registration
	 * @return the scout and guide registration
	 * @throws NoSuchScoutAndGuideRegistrationException if a scout and guide registration with the primary key could not be found
	 */
	@Override
	public ScoutAndGuideRegistration findByPrimaryKey(
			long scoutAndGuideRegistrationId)
		throws NoSuchScoutAndGuideRegistrationException {

		return findByPrimaryKey((Serializable)scoutAndGuideRegistrationId);
	}

	/**
	 * Returns the scout and guide registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scoutAndGuideRegistrationId the primary key of the scout and guide registration
	 * @return the scout and guide registration, or <code>null</code> if a scout and guide registration with the primary key could not be found
	 */
	@Override
	public ScoutAndGuideRegistration fetchByPrimaryKey(
		long scoutAndGuideRegistrationId) {

		return fetchByPrimaryKey((Serializable)scoutAndGuideRegistrationId);
	}

	/**
	 * Returns all the scout and guide registrations.
	 *
	 * @return the scout and guide registrations
	 */
	@Override
	public List<ScoutAndGuideRegistration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scout and guide registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScoutAndGuideRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of scout and guide registrations
	 * @param end the upper bound of the range of scout and guide registrations (not inclusive)
	 * @return the range of scout and guide registrations
	 */
	@Override
	public List<ScoutAndGuideRegistration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the scout and guide registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScoutAndGuideRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of scout and guide registrations
	 * @param end the upper bound of the range of scout and guide registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of scout and guide registrations
	 */
	@Override
	public List<ScoutAndGuideRegistration> findAll(
		int start, int end,
		OrderByComparator<ScoutAndGuideRegistration> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scout and guide registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScoutAndGuideRegistrationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of scout and guide registrations
	 * @param end the upper bound of the range of scout and guide registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of scout and guide registrations
	 */
	@Override
	public List<ScoutAndGuideRegistration> findAll(
		int start, int end,
		OrderByComparator<ScoutAndGuideRegistration> orderByComparator,
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

		List<ScoutAndGuideRegistration> list = null;

		if (useFinderCache) {
			list = (List<ScoutAndGuideRegistration>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SCOUTANDGUIDEREGISTRATION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SCOUTANDGUIDEREGISTRATION;

				sql = sql.concat(
					ScoutAndGuideRegistrationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ScoutAndGuideRegistration>)QueryUtil.list(
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
	 * Removes all the scout and guide registrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ScoutAndGuideRegistration scoutAndGuideRegistration : findAll()) {
			remove(scoutAndGuideRegistration);
		}
	}

	/**
	 * Returns the number of scout and guide registrations.
	 *
	 * @return the number of scout and guide registrations
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
					_SQL_COUNT_SCOUTANDGUIDEREGISTRATION);

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
		return "scoutAndGuideRegistrationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SCOUTANDGUIDEREGISTRATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ScoutAndGuideRegistrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the scout and guide registration persistence.
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

		_finderPathFetchByUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		ScoutAndGuideRegistrationUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ScoutAndGuideRegistrationUtil.setPersistence(null);

		entityCache.removeCache(ScoutAndGuideRegistrationImpl.class.getName());
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

	private static final String _SQL_SELECT_SCOUTANDGUIDEREGISTRATION =
		"SELECT scoutAndGuideRegistration FROM ScoutAndGuideRegistration scoutAndGuideRegistration";

	private static final String _SQL_SELECT_SCOUTANDGUIDEREGISTRATION_WHERE =
		"SELECT scoutAndGuideRegistration FROM ScoutAndGuideRegistration scoutAndGuideRegistration WHERE ";

	private static final String _SQL_COUNT_SCOUTANDGUIDEREGISTRATION =
		"SELECT COUNT(scoutAndGuideRegistration) FROM ScoutAndGuideRegistration scoutAndGuideRegistration";

	private static final String _SQL_COUNT_SCOUTANDGUIDEREGISTRATION_WHERE =
		"SELECT COUNT(scoutAndGuideRegistration) FROM ScoutAndGuideRegistration scoutAndGuideRegistration WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"scoutAndGuideRegistration.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ScoutAndGuideRegistration exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ScoutAndGuideRegistration exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ScoutAndGuideRegistrationPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}