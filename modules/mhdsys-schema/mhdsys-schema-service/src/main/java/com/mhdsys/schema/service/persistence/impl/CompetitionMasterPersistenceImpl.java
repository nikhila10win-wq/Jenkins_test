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

import com.mhdsys.schema.exception.NoSuchCompetitionMasterException;
import com.mhdsys.schema.model.CompetitionMaster;
import com.mhdsys.schema.model.CompetitionMasterTable;
import com.mhdsys.schema.model.impl.CompetitionMasterImpl;
import com.mhdsys.schema.model.impl.CompetitionMasterModelImpl;
import com.mhdsys.schema.service.persistence.CompetitionMasterPersistence;
import com.mhdsys.schema.service.persistence.CompetitionMasterUtil;
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
 * The persistence implementation for the competition master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CompetitionMasterPersistence.class)
public class CompetitionMasterPersistenceImpl
	extends BasePersistenceImpl<CompetitionMaster>
	implements CompetitionMasterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CompetitionMasterUtil</code> to access the competition master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CompetitionMasterImpl.class.getName();

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
	 * Returns the competition master where userId = &#63; or throws a <code>NoSuchCompetitionMasterException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching competition master
	 * @throws NoSuchCompetitionMasterException if a matching competition master could not be found
	 */
	@Override
	public CompetitionMaster findByUserId(long userId)
		throws NoSuchCompetitionMasterException {

		CompetitionMaster competitionMaster = fetchByUserId(userId);

		if (competitionMaster == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompetitionMasterException(sb.toString());
		}

		return competitionMaster;
	}

	/**
	 * Returns the competition master where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching competition master, or <code>null</code> if a matching competition master could not be found
	 */
	@Override
	public CompetitionMaster fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the competition master where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching competition master, or <code>null</code> if a matching competition master could not be found
	 */
	@Override
	public CompetitionMaster fetchByUserId(
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

		if (result instanceof CompetitionMaster) {
			CompetitionMaster competitionMaster = (CompetitionMaster)result;

			if (userId != competitionMaster.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMPETITIONMASTER_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<CompetitionMaster> list = query.list();

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
								"CompetitionMasterPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CompetitionMaster competitionMaster = list.get(0);

					result = competitionMaster;

					cacheResult(competitionMaster);
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
			return (CompetitionMaster)result;
		}
	}

	/**
	 * Removes the competition master where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the competition master that was removed
	 */
	@Override
	public CompetitionMaster removeByUserId(long userId)
		throws NoSuchCompetitionMasterException {

		CompetitionMaster competitionMaster = findByUserId(userId);

		return remove(competitionMaster);
	}

	/**
	 * Returns the number of competition masters where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching competition masters
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPETITIONMASTER_WHERE);

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
		"competitionMaster.userId = ?";

	public CompetitionMasterPersistenceImpl() {
		setModelClass(CompetitionMaster.class);

		setModelImplClass(CompetitionMasterImpl.class);
		setModelPKClass(long.class);

		setTable(CompetitionMasterTable.INSTANCE);
	}

	/**
	 * Caches the competition master in the entity cache if it is enabled.
	 *
	 * @param competitionMaster the competition master
	 */
	@Override
	public void cacheResult(CompetitionMaster competitionMaster) {
		entityCache.putResult(
			CompetitionMasterImpl.class, competitionMaster.getPrimaryKey(),
			competitionMaster);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {competitionMaster.getUserId()}, competitionMaster);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the competition masters in the entity cache if it is enabled.
	 *
	 * @param competitionMasters the competition masters
	 */
	@Override
	public void cacheResult(List<CompetitionMaster> competitionMasters) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (competitionMasters.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CompetitionMaster competitionMaster : competitionMasters) {
			if (entityCache.getResult(
					CompetitionMasterImpl.class,
					competitionMaster.getPrimaryKey()) == null) {

				cacheResult(competitionMaster);
			}
		}
	}

	/**
	 * Clears the cache for all competition masters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompetitionMasterImpl.class);

		finderCache.clearCache(CompetitionMasterImpl.class);
	}

	/**
	 * Clears the cache for the competition master.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompetitionMaster competitionMaster) {
		entityCache.removeResult(
			CompetitionMasterImpl.class, competitionMaster);
	}

	@Override
	public void clearCache(List<CompetitionMaster> competitionMasters) {
		for (CompetitionMaster competitionMaster : competitionMasters) {
			entityCache.removeResult(
				CompetitionMasterImpl.class, competitionMaster);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CompetitionMasterImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CompetitionMasterImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CompetitionMasterModelImpl competitionMasterModelImpl) {

		Object[] args = new Object[] {competitionMasterModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, competitionMasterModelImpl);
	}

	/**
	 * Creates a new competition master with the primary key. Does not add the competition master to the database.
	 *
	 * @param competitionMasterId the primary key for the new competition master
	 * @return the new competition master
	 */
	@Override
	public CompetitionMaster create(long competitionMasterId) {
		CompetitionMaster competitionMaster = new CompetitionMasterImpl();

		competitionMaster.setNew(true);
		competitionMaster.setPrimaryKey(competitionMasterId);

		return competitionMaster;
	}

	/**
	 * Removes the competition master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param competitionMasterId the primary key of the competition master
	 * @return the competition master that was removed
	 * @throws NoSuchCompetitionMasterException if a competition master with the primary key could not be found
	 */
	@Override
	public CompetitionMaster remove(long competitionMasterId)
		throws NoSuchCompetitionMasterException {

		return remove((Serializable)competitionMasterId);
	}

	/**
	 * Removes the competition master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the competition master
	 * @return the competition master that was removed
	 * @throws NoSuchCompetitionMasterException if a competition master with the primary key could not be found
	 */
	@Override
	public CompetitionMaster remove(Serializable primaryKey)
		throws NoSuchCompetitionMasterException {

		Session session = null;

		try {
			session = openSession();

			CompetitionMaster competitionMaster =
				(CompetitionMaster)session.get(
					CompetitionMasterImpl.class, primaryKey);

			if (competitionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompetitionMasterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(competitionMaster);
		}
		catch (NoSuchCompetitionMasterException noSuchEntityException) {
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
	protected CompetitionMaster removeImpl(
		CompetitionMaster competitionMaster) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(competitionMaster)) {
				competitionMaster = (CompetitionMaster)session.get(
					CompetitionMasterImpl.class,
					competitionMaster.getPrimaryKeyObj());
			}

			if (competitionMaster != null) {
				session.delete(competitionMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (competitionMaster != null) {
			clearCache(competitionMaster);
		}

		return competitionMaster;
	}

	@Override
	public CompetitionMaster updateImpl(CompetitionMaster competitionMaster) {
		boolean isNew = competitionMaster.isNew();

		if (!(competitionMaster instanceof CompetitionMasterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(competitionMaster.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					competitionMaster);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in competitionMaster proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CompetitionMaster implementation " +
					competitionMaster.getClass());
		}

		CompetitionMasterModelImpl competitionMasterModelImpl =
			(CompetitionMasterModelImpl)competitionMaster;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (competitionMaster.getCreateDate() == null)) {
			if (serviceContext == null) {
				competitionMaster.setCreateDate(date);
			}
			else {
				competitionMaster.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!competitionMasterModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				competitionMaster.setModifiedDate(date);
			}
			else {
				competitionMaster.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(competitionMaster);
			}
			else {
				competitionMaster = (CompetitionMaster)session.merge(
					competitionMaster);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CompetitionMasterImpl.class, competitionMasterModelImpl, false,
			true);

		cacheUniqueFindersCache(competitionMasterModelImpl);

		if (isNew) {
			competitionMaster.setNew(false);
		}

		competitionMaster.resetOriginalValues();

		return competitionMaster;
	}

	/**
	 * Returns the competition master with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the competition master
	 * @return the competition master
	 * @throws NoSuchCompetitionMasterException if a competition master with the primary key could not be found
	 */
	@Override
	public CompetitionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompetitionMasterException {

		CompetitionMaster competitionMaster = fetchByPrimaryKey(primaryKey);

		if (competitionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompetitionMasterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return competitionMaster;
	}

	/**
	 * Returns the competition master with the primary key or throws a <code>NoSuchCompetitionMasterException</code> if it could not be found.
	 *
	 * @param competitionMasterId the primary key of the competition master
	 * @return the competition master
	 * @throws NoSuchCompetitionMasterException if a competition master with the primary key could not be found
	 */
	@Override
	public CompetitionMaster findByPrimaryKey(long competitionMasterId)
		throws NoSuchCompetitionMasterException {

		return findByPrimaryKey((Serializable)competitionMasterId);
	}

	/**
	 * Returns the competition master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param competitionMasterId the primary key of the competition master
	 * @return the competition master, or <code>null</code> if a competition master with the primary key could not be found
	 */
	@Override
	public CompetitionMaster fetchByPrimaryKey(long competitionMasterId) {
		return fetchByPrimaryKey((Serializable)competitionMasterId);
	}

	/**
	 * Returns all the competition masters.
	 *
	 * @return the competition masters
	 */
	@Override
	public List<CompetitionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the competition masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition masters
	 * @param end the upper bound of the range of competition masters (not inclusive)
	 * @return the range of competition masters
	 */
	@Override
	public List<CompetitionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the competition masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition masters
	 * @param end the upper bound of the range of competition masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of competition masters
	 */
	@Override
	public List<CompetitionMaster> findAll(
		int start, int end,
		OrderByComparator<CompetitionMaster> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the competition masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionMasterModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition masters
	 * @param end the upper bound of the range of competition masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of competition masters
	 */
	@Override
	public List<CompetitionMaster> findAll(
		int start, int end,
		OrderByComparator<CompetitionMaster> orderByComparator,
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

		List<CompetitionMaster> list = null;

		if (useFinderCache) {
			list = (List<CompetitionMaster>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMPETITIONMASTER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMPETITIONMASTER;

				sql = sql.concat(CompetitionMasterModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CompetitionMaster>)QueryUtil.list(
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
	 * Removes all the competition masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompetitionMaster competitionMaster : findAll()) {
			remove(competitionMaster);
		}
	}

	/**
	 * Returns the number of competition masters.
	 *
	 * @return the number of competition masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COMPETITIONMASTER);

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
		return "competitionMasterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMPETITIONMASTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CompetitionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the competition master persistence.
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

		CompetitionMasterUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CompetitionMasterUtil.setPersistence(null);

		entityCache.removeCache(CompetitionMasterImpl.class.getName());
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

	private static final String _SQL_SELECT_COMPETITIONMASTER =
		"SELECT competitionMaster FROM CompetitionMaster competitionMaster";

	private static final String _SQL_SELECT_COMPETITIONMASTER_WHERE =
		"SELECT competitionMaster FROM CompetitionMaster competitionMaster WHERE ";

	private static final String _SQL_COUNT_COMPETITIONMASTER =
		"SELECT COUNT(competitionMaster) FROM CompetitionMaster competitionMaster";

	private static final String _SQL_COUNT_COMPETITIONMASTER_WHERE =
		"SELECT COUNT(competitionMaster) FROM CompetitionMaster competitionMaster WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "competitionMaster.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CompetitionMaster exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CompetitionMaster exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CompetitionMasterPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}