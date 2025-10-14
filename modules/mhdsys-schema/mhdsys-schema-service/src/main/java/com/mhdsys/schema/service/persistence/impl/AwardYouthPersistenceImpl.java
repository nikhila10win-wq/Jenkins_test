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

import com.mhdsys.schema.exception.NoSuchAwardYouthException;
import com.mhdsys.schema.model.AwardYouth;
import com.mhdsys.schema.model.AwardYouthTable;
import com.mhdsys.schema.model.impl.AwardYouthImpl;
import com.mhdsys.schema.model.impl.AwardYouthModelImpl;
import com.mhdsys.schema.service.persistence.AwardYouthPersistence;
import com.mhdsys.schema.service.persistence.AwardYouthUtil;
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
 * The persistence implementation for the award youth service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AwardYouthPersistence.class)
public class AwardYouthPersistenceImpl
	extends BasePersistenceImpl<AwardYouth> implements AwardYouthPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AwardYouthUtil</code> to access the award youth persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AwardYouthImpl.class.getName();

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
	 * Returns the award youth where userId = &#63; or throws a <code>NoSuchAwardYouthException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching award youth
	 * @throws NoSuchAwardYouthException if a matching award youth could not be found
	 */
	@Override
	public AwardYouth findByUserId(long userId)
		throws NoSuchAwardYouthException {

		AwardYouth awardYouth = fetchByUserId(userId);

		if (awardYouth == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAwardYouthException(sb.toString());
		}

		return awardYouth;
	}

	/**
	 * Returns the award youth where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching award youth, or <code>null</code> if a matching award youth could not be found
	 */
	@Override
	public AwardYouth fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the award youth where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching award youth, or <code>null</code> if a matching award youth could not be found
	 */
	@Override
	public AwardYouth fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof AwardYouth) {
			AwardYouth awardYouth = (AwardYouth)result;

			if (userId != awardYouth.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_AWARDYOUTH_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<AwardYouth> list = query.list();

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
								"AwardYouthPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AwardYouth awardYouth = list.get(0);

					result = awardYouth;

					cacheResult(awardYouth);
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
			return (AwardYouth)result;
		}
	}

	/**
	 * Removes the award youth where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the award youth that was removed
	 */
	@Override
	public AwardYouth removeByUserId(long userId)
		throws NoSuchAwardYouthException {

		AwardYouth awardYouth = findByUserId(userId);

		return remove(awardYouth);
	}

	/**
	 * Returns the number of award youths where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching award youths
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDYOUTH_WHERE);

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
		"awardYouth.userId = ?";

	public AwardYouthPersistenceImpl() {
		setModelClass(AwardYouth.class);

		setModelImplClass(AwardYouthImpl.class);
		setModelPKClass(long.class);

		setTable(AwardYouthTable.INSTANCE);
	}

	/**
	 * Caches the award youth in the entity cache if it is enabled.
	 *
	 * @param awardYouth the award youth
	 */
	@Override
	public void cacheResult(AwardYouth awardYouth) {
		entityCache.putResult(
			AwardYouthImpl.class, awardYouth.getPrimaryKey(), awardYouth);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {awardYouth.getUserId()},
			awardYouth);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the award youths in the entity cache if it is enabled.
	 *
	 * @param awardYouths the award youths
	 */
	@Override
	public void cacheResult(List<AwardYouth> awardYouths) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (awardYouths.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AwardYouth awardYouth : awardYouths) {
			if (entityCache.getResult(
					AwardYouthImpl.class, awardYouth.getPrimaryKey()) == null) {

				cacheResult(awardYouth);
			}
		}
	}

	/**
	 * Clears the cache for all award youths.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AwardYouthImpl.class);

		finderCache.clearCache(AwardYouthImpl.class);
	}

	/**
	 * Clears the cache for the award youth.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AwardYouth awardYouth) {
		entityCache.removeResult(AwardYouthImpl.class, awardYouth);
	}

	@Override
	public void clearCache(List<AwardYouth> awardYouths) {
		for (AwardYouth awardYouth : awardYouths) {
			entityCache.removeResult(AwardYouthImpl.class, awardYouth);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AwardYouthImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AwardYouthImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AwardYouthModelImpl awardYouthModelImpl) {

		Object[] args = new Object[] {awardYouthModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, awardYouthModelImpl);
	}

	/**
	 * Creates a new award youth with the primary key. Does not add the award youth to the database.
	 *
	 * @param awardYouthId the primary key for the new award youth
	 * @return the new award youth
	 */
	@Override
	public AwardYouth create(long awardYouthId) {
		AwardYouth awardYouth = new AwardYouthImpl();

		awardYouth.setNew(true);
		awardYouth.setPrimaryKey(awardYouthId);

		return awardYouth;
	}

	/**
	 * Removes the award youth with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param awardYouthId the primary key of the award youth
	 * @return the award youth that was removed
	 * @throws NoSuchAwardYouthException if a award youth with the primary key could not be found
	 */
	@Override
	public AwardYouth remove(long awardYouthId)
		throws NoSuchAwardYouthException {

		return remove((Serializable)awardYouthId);
	}

	/**
	 * Removes the award youth with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the award youth
	 * @return the award youth that was removed
	 * @throws NoSuchAwardYouthException if a award youth with the primary key could not be found
	 */
	@Override
	public AwardYouth remove(Serializable primaryKey)
		throws NoSuchAwardYouthException {

		Session session = null;

		try {
			session = openSession();

			AwardYouth awardYouth = (AwardYouth)session.get(
				AwardYouthImpl.class, primaryKey);

			if (awardYouth == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAwardYouthException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(awardYouth);
		}
		catch (NoSuchAwardYouthException noSuchEntityException) {
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
	protected AwardYouth removeImpl(AwardYouth awardYouth) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(awardYouth)) {
				awardYouth = (AwardYouth)session.get(
					AwardYouthImpl.class, awardYouth.getPrimaryKeyObj());
			}

			if (awardYouth != null) {
				session.delete(awardYouth);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (awardYouth != null) {
			clearCache(awardYouth);
		}

		return awardYouth;
	}

	@Override
	public AwardYouth updateImpl(AwardYouth awardYouth) {
		boolean isNew = awardYouth.isNew();

		if (!(awardYouth instanceof AwardYouthModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(awardYouth.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(awardYouth);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in awardYouth proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AwardYouth implementation " +
					awardYouth.getClass());
		}

		AwardYouthModelImpl awardYouthModelImpl =
			(AwardYouthModelImpl)awardYouth;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (awardYouth.getCreateDate() == null)) {
			if (serviceContext == null) {
				awardYouth.setCreateDate(date);
			}
			else {
				awardYouth.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!awardYouthModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				awardYouth.setModifiedDate(date);
			}
			else {
				awardYouth.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(awardYouth);
			}
			else {
				awardYouth = (AwardYouth)session.merge(awardYouth);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AwardYouthImpl.class, awardYouthModelImpl, false, true);

		cacheUniqueFindersCache(awardYouthModelImpl);

		if (isNew) {
			awardYouth.setNew(false);
		}

		awardYouth.resetOriginalValues();

		return awardYouth;
	}

	/**
	 * Returns the award youth with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the award youth
	 * @return the award youth
	 * @throws NoSuchAwardYouthException if a award youth with the primary key could not be found
	 */
	@Override
	public AwardYouth findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAwardYouthException {

		AwardYouth awardYouth = fetchByPrimaryKey(primaryKey);

		if (awardYouth == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAwardYouthException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return awardYouth;
	}

	/**
	 * Returns the award youth with the primary key or throws a <code>NoSuchAwardYouthException</code> if it could not be found.
	 *
	 * @param awardYouthId the primary key of the award youth
	 * @return the award youth
	 * @throws NoSuchAwardYouthException if a award youth with the primary key could not be found
	 */
	@Override
	public AwardYouth findByPrimaryKey(long awardYouthId)
		throws NoSuchAwardYouthException {

		return findByPrimaryKey((Serializable)awardYouthId);
	}

	/**
	 * Returns the award youth with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param awardYouthId the primary key of the award youth
	 * @return the award youth, or <code>null</code> if a award youth with the primary key could not be found
	 */
	@Override
	public AwardYouth fetchByPrimaryKey(long awardYouthId) {
		return fetchByPrimaryKey((Serializable)awardYouthId);
	}

	/**
	 * Returns all the award youths.
	 *
	 * @return the award youths
	 */
	@Override
	public List<AwardYouth> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award youths.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardYouthModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award youths
	 * @param end the upper bound of the range of award youths (not inclusive)
	 * @return the range of award youths
	 */
	@Override
	public List<AwardYouth> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the award youths.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardYouthModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award youths
	 * @param end the upper bound of the range of award youths (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of award youths
	 */
	@Override
	public List<AwardYouth> findAll(
		int start, int end, OrderByComparator<AwardYouth> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award youths.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardYouthModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award youths
	 * @param end the upper bound of the range of award youths (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of award youths
	 */
	@Override
	public List<AwardYouth> findAll(
		int start, int end, OrderByComparator<AwardYouth> orderByComparator,
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

		List<AwardYouth> list = null;

		if (useFinderCache) {
			list = (List<AwardYouth>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AWARDYOUTH);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AWARDYOUTH;

				sql = sql.concat(AwardYouthModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AwardYouth>)QueryUtil.list(
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
	 * Removes all the award youths from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AwardYouth awardYouth : findAll()) {
			remove(awardYouth);
		}
	}

	/**
	 * Returns the number of award youths.
	 *
	 * @return the number of award youths
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AWARDYOUTH);

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
		return "awardYouthId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AWARDYOUTH;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AwardYouthModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the award youth persistence.
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

		AwardYouthUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AwardYouthUtil.setPersistence(null);

		entityCache.removeCache(AwardYouthImpl.class.getName());
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

	private static final String _SQL_SELECT_AWARDYOUTH =
		"SELECT awardYouth FROM AwardYouth awardYouth";

	private static final String _SQL_SELECT_AWARDYOUTH_WHERE =
		"SELECT awardYouth FROM AwardYouth awardYouth WHERE ";

	private static final String _SQL_COUNT_AWARDYOUTH =
		"SELECT COUNT(awardYouth) FROM AwardYouth awardYouth";

	private static final String _SQL_COUNT_AWARDYOUTH_WHERE =
		"SELECT COUNT(awardYouth) FROM AwardYouth awardYouth WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "awardYouth.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AwardYouth exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AwardYouth exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AwardYouthPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}