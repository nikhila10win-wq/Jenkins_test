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

import com.mhdsys.schema.exception.NoSuchAwardPointsException;
import com.mhdsys.schema.model.AwardPoints;
import com.mhdsys.schema.model.AwardPointsTable;
import com.mhdsys.schema.model.impl.AwardPointsImpl;
import com.mhdsys.schema.model.impl.AwardPointsModelImpl;
import com.mhdsys.schema.service.persistence.AwardPointsPersistence;
import com.mhdsys.schema.service.persistence.AwardPointsUtil;
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
 * The persistence implementation for the award points service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AwardPointsPersistence.class)
public class AwardPointsPersistenceImpl
	extends BasePersistenceImpl<AwardPoints> implements AwardPointsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AwardPointsUtil</code> to access the award points persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AwardPointsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByAwardPointsId;
	private FinderPath _finderPathCountByAwardPointsId;

	/**
	 * Returns the award points where awardPointsId = &#63; or throws a <code>NoSuchAwardPointsException</code> if it could not be found.
	 *
	 * @param awardPointsId the award points ID
	 * @return the matching award points
	 * @throws NoSuchAwardPointsException if a matching award points could not be found
	 */
	@Override
	public AwardPoints findByAwardPointsId(long awardPointsId)
		throws NoSuchAwardPointsException {

		AwardPoints awardPoints = fetchByAwardPointsId(awardPointsId);

		if (awardPoints == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("awardPointsId=");
			sb.append(awardPointsId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAwardPointsException(sb.toString());
		}

		return awardPoints;
	}

	/**
	 * Returns the award points where awardPointsId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param awardPointsId the award points ID
	 * @return the matching award points, or <code>null</code> if a matching award points could not be found
	 */
	@Override
	public AwardPoints fetchByAwardPointsId(long awardPointsId) {
		return fetchByAwardPointsId(awardPointsId, true);
	}

	/**
	 * Returns the award points where awardPointsId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param awardPointsId the award points ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching award points, or <code>null</code> if a matching award points could not be found
	 */
	@Override
	public AwardPoints fetchByAwardPointsId(
		long awardPointsId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {awardPointsId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByAwardPointsId, finderArgs, this);
		}

		if (result instanceof AwardPoints) {
			AwardPoints awardPoints = (AwardPoints)result;

			if (awardPointsId != awardPoints.getAwardPointsId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_AWARDPOINTS_WHERE);

			sb.append(_FINDER_COLUMN_AWARDPOINTSID_AWARDPOINTSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(awardPointsId);

				List<AwardPoints> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByAwardPointsId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {awardPointsId};
							}

							_log.warn(
								"AwardPointsPersistenceImpl.fetchByAwardPointsId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AwardPoints awardPoints = list.get(0);

					result = awardPoints;

					cacheResult(awardPoints);
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
			return (AwardPoints)result;
		}
	}

	/**
	 * Removes the award points where awardPointsId = &#63; from the database.
	 *
	 * @param awardPointsId the award points ID
	 * @return the award points that was removed
	 */
	@Override
	public AwardPoints removeByAwardPointsId(long awardPointsId)
		throws NoSuchAwardPointsException {

		AwardPoints awardPoints = findByAwardPointsId(awardPointsId);

		return remove(awardPoints);
	}

	/**
	 * Returns the number of award pointses where awardPointsId = &#63;.
	 *
	 * @param awardPointsId the award points ID
	 * @return the number of matching award pointses
	 */
	@Override
	public int countByAwardPointsId(long awardPointsId) {
		FinderPath finderPath = _finderPathCountByAwardPointsId;

		Object[] finderArgs = new Object[] {awardPointsId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AWARDPOINTS_WHERE);

			sb.append(_FINDER_COLUMN_AWARDPOINTSID_AWARDPOINTSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(awardPointsId);

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

	private static final String _FINDER_COLUMN_AWARDPOINTSID_AWARDPOINTSID_2 =
		"awardPoints.awardPointsId = ?";

	public AwardPointsPersistenceImpl() {
		setModelClass(AwardPoints.class);

		setModelImplClass(AwardPointsImpl.class);
		setModelPKClass(long.class);

		setTable(AwardPointsTable.INSTANCE);
	}

	/**
	 * Caches the award points in the entity cache if it is enabled.
	 *
	 * @param awardPoints the award points
	 */
	@Override
	public void cacheResult(AwardPoints awardPoints) {
		entityCache.putResult(
			AwardPointsImpl.class, awardPoints.getPrimaryKey(), awardPoints);

		finderCache.putResult(
			_finderPathFetchByAwardPointsId,
			new Object[] {awardPoints.getAwardPointsId()}, awardPoints);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the award pointses in the entity cache if it is enabled.
	 *
	 * @param awardPointses the award pointses
	 */
	@Override
	public void cacheResult(List<AwardPoints> awardPointses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (awardPointses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AwardPoints awardPoints : awardPointses) {
			if (entityCache.getResult(
					AwardPointsImpl.class, awardPoints.getPrimaryKey()) ==
						null) {

				cacheResult(awardPoints);
			}
		}
	}

	/**
	 * Clears the cache for all award pointses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AwardPointsImpl.class);

		finderCache.clearCache(AwardPointsImpl.class);
	}

	/**
	 * Clears the cache for the award points.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AwardPoints awardPoints) {
		entityCache.removeResult(AwardPointsImpl.class, awardPoints);
	}

	@Override
	public void clearCache(List<AwardPoints> awardPointses) {
		for (AwardPoints awardPoints : awardPointses) {
			entityCache.removeResult(AwardPointsImpl.class, awardPoints);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(AwardPointsImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(AwardPointsImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AwardPointsModelImpl awardPointsModelImpl) {

		Object[] args = new Object[] {awardPointsModelImpl.getAwardPointsId()};

		finderCache.putResult(
			_finderPathCountByAwardPointsId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByAwardPointsId, args, awardPointsModelImpl);
	}

	/**
	 * Creates a new award points with the primary key. Does not add the award points to the database.
	 *
	 * @param awardPointsId the primary key for the new award points
	 * @return the new award points
	 */
	@Override
	public AwardPoints create(long awardPointsId) {
		AwardPoints awardPoints = new AwardPointsImpl();

		awardPoints.setNew(true);
		awardPoints.setPrimaryKey(awardPointsId);

		return awardPoints;
	}

	/**
	 * Removes the award points with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param awardPointsId the primary key of the award points
	 * @return the award points that was removed
	 * @throws NoSuchAwardPointsException if a award points with the primary key could not be found
	 */
	@Override
	public AwardPoints remove(long awardPointsId)
		throws NoSuchAwardPointsException {

		return remove((Serializable)awardPointsId);
	}

	/**
	 * Removes the award points with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the award points
	 * @return the award points that was removed
	 * @throws NoSuchAwardPointsException if a award points with the primary key could not be found
	 */
	@Override
	public AwardPoints remove(Serializable primaryKey)
		throws NoSuchAwardPointsException {

		Session session = null;

		try {
			session = openSession();

			AwardPoints awardPoints = (AwardPoints)session.get(
				AwardPointsImpl.class, primaryKey);

			if (awardPoints == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAwardPointsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(awardPoints);
		}
		catch (NoSuchAwardPointsException noSuchEntityException) {
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
	protected AwardPoints removeImpl(AwardPoints awardPoints) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(awardPoints)) {
				awardPoints = (AwardPoints)session.get(
					AwardPointsImpl.class, awardPoints.getPrimaryKeyObj());
			}

			if (awardPoints != null) {
				session.delete(awardPoints);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (awardPoints != null) {
			clearCache(awardPoints);
		}

		return awardPoints;
	}

	@Override
	public AwardPoints updateImpl(AwardPoints awardPoints) {
		boolean isNew = awardPoints.isNew();

		if (!(awardPoints instanceof AwardPointsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(awardPoints.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(awardPoints);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in awardPoints proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AwardPoints implementation " +
					awardPoints.getClass());
		}

		AwardPointsModelImpl awardPointsModelImpl =
			(AwardPointsModelImpl)awardPoints;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (awardPoints.getCreateDate() == null)) {
			if (serviceContext == null) {
				awardPoints.setCreateDate(date);
			}
			else {
				awardPoints.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!awardPointsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				awardPoints.setModifiedDate(date);
			}
			else {
				awardPoints.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(awardPoints);
			}
			else {
				awardPoints = (AwardPoints)session.merge(awardPoints);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			AwardPointsImpl.class, awardPointsModelImpl, false, true);

		cacheUniqueFindersCache(awardPointsModelImpl);

		if (isNew) {
			awardPoints.setNew(false);
		}

		awardPoints.resetOriginalValues();

		return awardPoints;
	}

	/**
	 * Returns the award points with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the award points
	 * @return the award points
	 * @throws NoSuchAwardPointsException if a award points with the primary key could not be found
	 */
	@Override
	public AwardPoints findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAwardPointsException {

		AwardPoints awardPoints = fetchByPrimaryKey(primaryKey);

		if (awardPoints == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAwardPointsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return awardPoints;
	}

	/**
	 * Returns the award points with the primary key or throws a <code>NoSuchAwardPointsException</code> if it could not be found.
	 *
	 * @param awardPointsId the primary key of the award points
	 * @return the award points
	 * @throws NoSuchAwardPointsException if a award points with the primary key could not be found
	 */
	@Override
	public AwardPoints findByPrimaryKey(long awardPointsId)
		throws NoSuchAwardPointsException {

		return findByPrimaryKey((Serializable)awardPointsId);
	}

	/**
	 * Returns the award points with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param awardPointsId the primary key of the award points
	 * @return the award points, or <code>null</code> if a award points with the primary key could not be found
	 */
	@Override
	public AwardPoints fetchByPrimaryKey(long awardPointsId) {
		return fetchByPrimaryKey((Serializable)awardPointsId);
	}

	/**
	 * Returns all the award pointses.
	 *
	 * @return the award pointses
	 */
	@Override
	public List<AwardPoints> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the award pointses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardPointsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award pointses
	 * @param end the upper bound of the range of award pointses (not inclusive)
	 * @return the range of award pointses
	 */
	@Override
	public List<AwardPoints> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the award pointses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardPointsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award pointses
	 * @param end the upper bound of the range of award pointses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of award pointses
	 */
	@Override
	public List<AwardPoints> findAll(
		int start, int end, OrderByComparator<AwardPoints> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the award pointses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AwardPointsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of award pointses
	 * @param end the upper bound of the range of award pointses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of award pointses
	 */
	@Override
	public List<AwardPoints> findAll(
		int start, int end, OrderByComparator<AwardPoints> orderByComparator,
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

		List<AwardPoints> list = null;

		if (useFinderCache) {
			list = (List<AwardPoints>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AWARDPOINTS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AWARDPOINTS;

				sql = sql.concat(AwardPointsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AwardPoints>)QueryUtil.list(
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
	 * Removes all the award pointses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AwardPoints awardPoints : findAll()) {
			remove(awardPoints);
		}
	}

	/**
	 * Returns the number of award pointses.
	 *
	 * @return the number of award pointses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AWARDPOINTS);

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
		return "awardPointsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AWARDPOINTS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AwardPointsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the award points persistence.
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

		_finderPathFetchByAwardPointsId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByAwardPointsId",
			new String[] {Long.class.getName()}, new String[] {"awardPointsId"},
			true);

		_finderPathCountByAwardPointsId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAwardPointsId",
			new String[] {Long.class.getName()}, new String[] {"awardPointsId"},
			false);

		AwardPointsUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AwardPointsUtil.setPersistence(null);

		entityCache.removeCache(AwardPointsImpl.class.getName());
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

	private static final String _SQL_SELECT_AWARDPOINTS =
		"SELECT awardPoints FROM AwardPoints awardPoints";

	private static final String _SQL_SELECT_AWARDPOINTS_WHERE =
		"SELECT awardPoints FROM AwardPoints awardPoints WHERE ";

	private static final String _SQL_COUNT_AWARDPOINTS =
		"SELECT COUNT(awardPoints) FROM AwardPoints awardPoints";

	private static final String _SQL_COUNT_AWARDPOINTS_WHERE =
		"SELECT COUNT(awardPoints) FROM AwardPoints awardPoints WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "awardPoints.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AwardPoints exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AwardPoints exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AwardPointsPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}