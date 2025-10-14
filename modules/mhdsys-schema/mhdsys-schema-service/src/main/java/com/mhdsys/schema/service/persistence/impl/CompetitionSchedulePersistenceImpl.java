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

import com.mhdsys.schema.exception.NoSuchCompetitionScheduleException;
import com.mhdsys.schema.model.CompetitionSchedule;
import com.mhdsys.schema.model.CompetitionScheduleTable;
import com.mhdsys.schema.model.impl.CompetitionScheduleImpl;
import com.mhdsys.schema.model.impl.CompetitionScheduleModelImpl;
import com.mhdsys.schema.service.persistence.CompetitionSchedulePersistence;
import com.mhdsys.schema.service.persistence.CompetitionScheduleUtil;
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
 * The persistence implementation for the competition schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CompetitionSchedulePersistence.class)
public class CompetitionSchedulePersistenceImpl
	extends BasePersistenceImpl<CompetitionSchedule>
	implements CompetitionSchedulePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CompetitionScheduleUtil</code> to access the competition schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CompetitionScheduleImpl.class.getName();

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
	 * Returns the competition schedule where userId = &#63; or throws a <code>NoSuchCompetitionScheduleException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching competition schedule
	 * @throws NoSuchCompetitionScheduleException if a matching competition schedule could not be found
	 */
	@Override
	public CompetitionSchedule findByUserId(long userId)
		throws NoSuchCompetitionScheduleException {

		CompetitionSchedule competitionSchedule = fetchByUserId(userId);

		if (competitionSchedule == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompetitionScheduleException(sb.toString());
		}

		return competitionSchedule;
	}

	/**
	 * Returns the competition schedule where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching competition schedule, or <code>null</code> if a matching competition schedule could not be found
	 */
	@Override
	public CompetitionSchedule fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the competition schedule where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching competition schedule, or <code>null</code> if a matching competition schedule could not be found
	 */
	@Override
	public CompetitionSchedule fetchByUserId(
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

		if (result instanceof CompetitionSchedule) {
			CompetitionSchedule competitionSchedule =
				(CompetitionSchedule)result;

			if (userId != competitionSchedule.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMPETITIONSCHEDULE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<CompetitionSchedule> list = query.list();

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
								"CompetitionSchedulePersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CompetitionSchedule competitionSchedule = list.get(0);

					result = competitionSchedule;

					cacheResult(competitionSchedule);
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
			return (CompetitionSchedule)result;
		}
	}

	/**
	 * Removes the competition schedule where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the competition schedule that was removed
	 */
	@Override
	public CompetitionSchedule removeByUserId(long userId)
		throws NoSuchCompetitionScheduleException {

		CompetitionSchedule competitionSchedule = findByUserId(userId);

		return remove(competitionSchedule);
	}

	/**
	 * Returns the number of competition schedules where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching competition schedules
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPETITIONSCHEDULE_WHERE);

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
		"competitionSchedule.userId = ?";

	private FinderPath _finderPathFetchByPtTeacherApplicationId;
	private FinderPath _finderPathCountByPtTeacherApplicationId;

	/**
	 * Returns the competition schedule where ptTeacherApplicationId = &#63; or throws a <code>NoSuchCompetitionScheduleException</code> if it could not be found.
	 *
	 * @param ptTeacherApplicationId the pt teacher application ID
	 * @return the matching competition schedule
	 * @throws NoSuchCompetitionScheduleException if a matching competition schedule could not be found
	 */
	@Override
	public CompetitionSchedule findByPtTeacherApplicationId(
			long ptTeacherApplicationId)
		throws NoSuchCompetitionScheduleException {

		CompetitionSchedule competitionSchedule = fetchByPtTeacherApplicationId(
			ptTeacherApplicationId);

		if (competitionSchedule == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("ptTeacherApplicationId=");
			sb.append(ptTeacherApplicationId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompetitionScheduleException(sb.toString());
		}

		return competitionSchedule;
	}

	/**
	 * Returns the competition schedule where ptTeacherApplicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ptTeacherApplicationId the pt teacher application ID
	 * @return the matching competition schedule, or <code>null</code> if a matching competition schedule could not be found
	 */
	@Override
	public CompetitionSchedule fetchByPtTeacherApplicationId(
		long ptTeacherApplicationId) {

		return fetchByPtTeacherApplicationId(ptTeacherApplicationId, true);
	}

	/**
	 * Returns the competition schedule where ptTeacherApplicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ptTeacherApplicationId the pt teacher application ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching competition schedule, or <code>null</code> if a matching competition schedule could not be found
	 */
	@Override
	public CompetitionSchedule fetchByPtTeacherApplicationId(
		long ptTeacherApplicationId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {ptTeacherApplicationId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByPtTeacherApplicationId, finderArgs, this);
		}

		if (result instanceof CompetitionSchedule) {
			CompetitionSchedule competitionSchedule =
				(CompetitionSchedule)result;

			if (ptTeacherApplicationId !=
					competitionSchedule.getPtTeacherApplicationId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMPETITIONSCHEDULE_WHERE);

			sb.append(
				_FINDER_COLUMN_PTTEACHERAPPLICATIONID_PTTEACHERAPPLICATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ptTeacherApplicationId);

				List<CompetitionSchedule> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByPtTeacherApplicationId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									ptTeacherApplicationId
								};
							}

							_log.warn(
								"CompetitionSchedulePersistenceImpl.fetchByPtTeacherApplicationId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CompetitionSchedule competitionSchedule = list.get(0);

					result = competitionSchedule;

					cacheResult(competitionSchedule);
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
			return (CompetitionSchedule)result;
		}
	}

	/**
	 * Removes the competition schedule where ptTeacherApplicationId = &#63; from the database.
	 *
	 * @param ptTeacherApplicationId the pt teacher application ID
	 * @return the competition schedule that was removed
	 */
	@Override
	public CompetitionSchedule removeByPtTeacherApplicationId(
			long ptTeacherApplicationId)
		throws NoSuchCompetitionScheduleException {

		CompetitionSchedule competitionSchedule = findByPtTeacherApplicationId(
			ptTeacherApplicationId);

		return remove(competitionSchedule);
	}

	/**
	 * Returns the number of competition schedules where ptTeacherApplicationId = &#63;.
	 *
	 * @param ptTeacherApplicationId the pt teacher application ID
	 * @return the number of matching competition schedules
	 */
	@Override
	public int countByPtTeacherApplicationId(long ptTeacherApplicationId) {
		FinderPath finderPath = _finderPathCountByPtTeacherApplicationId;

		Object[] finderArgs = new Object[] {ptTeacherApplicationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPETITIONSCHEDULE_WHERE);

			sb.append(
				_FINDER_COLUMN_PTTEACHERAPPLICATIONID_PTTEACHERAPPLICATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ptTeacherApplicationId);

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
		_FINDER_COLUMN_PTTEACHERAPPLICATIONID_PTTEACHERAPPLICATIONID_2 =
			"competitionSchedule.ptTeacherApplicationId = ?";

	public CompetitionSchedulePersistenceImpl() {
		setModelClass(CompetitionSchedule.class);

		setModelImplClass(CompetitionScheduleImpl.class);
		setModelPKClass(long.class);

		setTable(CompetitionScheduleTable.INSTANCE);
	}

	/**
	 * Caches the competition schedule in the entity cache if it is enabled.
	 *
	 * @param competitionSchedule the competition schedule
	 */
	@Override
	public void cacheResult(CompetitionSchedule competitionSchedule) {
		entityCache.putResult(
			CompetitionScheduleImpl.class, competitionSchedule.getPrimaryKey(),
			competitionSchedule);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {competitionSchedule.getUserId()},
			competitionSchedule);

		finderCache.putResult(
			_finderPathFetchByPtTeacherApplicationId,
			new Object[] {competitionSchedule.getPtTeacherApplicationId()},
			competitionSchedule);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the competition schedules in the entity cache if it is enabled.
	 *
	 * @param competitionSchedules the competition schedules
	 */
	@Override
	public void cacheResult(List<CompetitionSchedule> competitionSchedules) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (competitionSchedules.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CompetitionSchedule competitionSchedule : competitionSchedules) {
			if (entityCache.getResult(
					CompetitionScheduleImpl.class,
					competitionSchedule.getPrimaryKey()) == null) {

				cacheResult(competitionSchedule);
			}
		}
	}

	/**
	 * Clears the cache for all competition schedules.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompetitionScheduleImpl.class);

		finderCache.clearCache(CompetitionScheduleImpl.class);
	}

	/**
	 * Clears the cache for the competition schedule.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompetitionSchedule competitionSchedule) {
		entityCache.removeResult(
			CompetitionScheduleImpl.class, competitionSchedule);
	}

	@Override
	public void clearCache(List<CompetitionSchedule> competitionSchedules) {
		for (CompetitionSchedule competitionSchedule : competitionSchedules) {
			entityCache.removeResult(
				CompetitionScheduleImpl.class, competitionSchedule);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CompetitionScheduleImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CompetitionScheduleImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CompetitionScheduleModelImpl competitionScheduleModelImpl) {

		Object[] args = new Object[] {competitionScheduleModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, competitionScheduleModelImpl);

		args = new Object[] {
			competitionScheduleModelImpl.getPtTeacherApplicationId()
		};

		finderCache.putResult(
			_finderPathCountByPtTeacherApplicationId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByPtTeacherApplicationId, args,
			competitionScheduleModelImpl);
	}

	/**
	 * Creates a new competition schedule with the primary key. Does not add the competition schedule to the database.
	 *
	 * @param competitionScheduleId the primary key for the new competition schedule
	 * @return the new competition schedule
	 */
	@Override
	public CompetitionSchedule create(long competitionScheduleId) {
		CompetitionSchedule competitionSchedule = new CompetitionScheduleImpl();

		competitionSchedule.setNew(true);
		competitionSchedule.setPrimaryKey(competitionScheduleId);

		return competitionSchedule;
	}

	/**
	 * Removes the competition schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param competitionScheduleId the primary key of the competition schedule
	 * @return the competition schedule that was removed
	 * @throws NoSuchCompetitionScheduleException if a competition schedule with the primary key could not be found
	 */
	@Override
	public CompetitionSchedule remove(long competitionScheduleId)
		throws NoSuchCompetitionScheduleException {

		return remove((Serializable)competitionScheduleId);
	}

	/**
	 * Removes the competition schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the competition schedule
	 * @return the competition schedule that was removed
	 * @throws NoSuchCompetitionScheduleException if a competition schedule with the primary key could not be found
	 */
	@Override
	public CompetitionSchedule remove(Serializable primaryKey)
		throws NoSuchCompetitionScheduleException {

		Session session = null;

		try {
			session = openSession();

			CompetitionSchedule competitionSchedule =
				(CompetitionSchedule)session.get(
					CompetitionScheduleImpl.class, primaryKey);

			if (competitionSchedule == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompetitionScheduleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(competitionSchedule);
		}
		catch (NoSuchCompetitionScheduleException noSuchEntityException) {
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
	protected CompetitionSchedule removeImpl(
		CompetitionSchedule competitionSchedule) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(competitionSchedule)) {
				competitionSchedule = (CompetitionSchedule)session.get(
					CompetitionScheduleImpl.class,
					competitionSchedule.getPrimaryKeyObj());
			}

			if (competitionSchedule != null) {
				session.delete(competitionSchedule);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (competitionSchedule != null) {
			clearCache(competitionSchedule);
		}

		return competitionSchedule;
	}

	@Override
	public CompetitionSchedule updateImpl(
		CompetitionSchedule competitionSchedule) {

		boolean isNew = competitionSchedule.isNew();

		if (!(competitionSchedule instanceof CompetitionScheduleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(competitionSchedule.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					competitionSchedule);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in competitionSchedule proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CompetitionSchedule implementation " +
					competitionSchedule.getClass());
		}

		CompetitionScheduleModelImpl competitionScheduleModelImpl =
			(CompetitionScheduleModelImpl)competitionSchedule;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (competitionSchedule.getCreateDate() == null)) {
			if (serviceContext == null) {
				competitionSchedule.setCreateDate(date);
			}
			else {
				competitionSchedule.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!competitionScheduleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				competitionSchedule.setModifiedDate(date);
			}
			else {
				competitionSchedule.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(competitionSchedule);
			}
			else {
				competitionSchedule = (CompetitionSchedule)session.merge(
					competitionSchedule);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CompetitionScheduleImpl.class, competitionScheduleModelImpl, false,
			true);

		cacheUniqueFindersCache(competitionScheduleModelImpl);

		if (isNew) {
			competitionSchedule.setNew(false);
		}

		competitionSchedule.resetOriginalValues();

		return competitionSchedule;
	}

	/**
	 * Returns the competition schedule with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the competition schedule
	 * @return the competition schedule
	 * @throws NoSuchCompetitionScheduleException if a competition schedule with the primary key could not be found
	 */
	@Override
	public CompetitionSchedule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompetitionScheduleException {

		CompetitionSchedule competitionSchedule = fetchByPrimaryKey(primaryKey);

		if (competitionSchedule == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompetitionScheduleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return competitionSchedule;
	}

	/**
	 * Returns the competition schedule with the primary key or throws a <code>NoSuchCompetitionScheduleException</code> if it could not be found.
	 *
	 * @param competitionScheduleId the primary key of the competition schedule
	 * @return the competition schedule
	 * @throws NoSuchCompetitionScheduleException if a competition schedule with the primary key could not be found
	 */
	@Override
	public CompetitionSchedule findByPrimaryKey(long competitionScheduleId)
		throws NoSuchCompetitionScheduleException {

		return findByPrimaryKey((Serializable)competitionScheduleId);
	}

	/**
	 * Returns the competition schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param competitionScheduleId the primary key of the competition schedule
	 * @return the competition schedule, or <code>null</code> if a competition schedule with the primary key could not be found
	 */
	@Override
	public CompetitionSchedule fetchByPrimaryKey(long competitionScheduleId) {
		return fetchByPrimaryKey((Serializable)competitionScheduleId);
	}

	/**
	 * Returns all the competition schedules.
	 *
	 * @return the competition schedules
	 */
	@Override
	public List<CompetitionSchedule> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the competition schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition schedules
	 * @param end the upper bound of the range of competition schedules (not inclusive)
	 * @return the range of competition schedules
	 */
	@Override
	public List<CompetitionSchedule> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the competition schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition schedules
	 * @param end the upper bound of the range of competition schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of competition schedules
	 */
	@Override
	public List<CompetitionSchedule> findAll(
		int start, int end,
		OrderByComparator<CompetitionSchedule> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the competition schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition schedules
	 * @param end the upper bound of the range of competition schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of competition schedules
	 */
	@Override
	public List<CompetitionSchedule> findAll(
		int start, int end,
		OrderByComparator<CompetitionSchedule> orderByComparator,
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

		List<CompetitionSchedule> list = null;

		if (useFinderCache) {
			list = (List<CompetitionSchedule>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMPETITIONSCHEDULE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMPETITIONSCHEDULE;

				sql = sql.concat(CompetitionScheduleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CompetitionSchedule>)QueryUtil.list(
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
	 * Removes all the competition schedules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompetitionSchedule competitionSchedule : findAll()) {
			remove(competitionSchedule);
		}
	}

	/**
	 * Returns the number of competition schedules.
	 *
	 * @return the number of competition schedules
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
					_SQL_COUNT_COMPETITIONSCHEDULE);

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
		return "competitionScheduleId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMPETITIONSCHEDULE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CompetitionScheduleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the competition schedule persistence.
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

		_finderPathFetchByPtTeacherApplicationId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByPtTeacherApplicationId",
			new String[] {Long.class.getName()},
			new String[] {"ptTeacherApplicationId"}, true);

		_finderPathCountByPtTeacherApplicationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPtTeacherApplicationId",
			new String[] {Long.class.getName()},
			new String[] {"ptTeacherApplicationId"}, false);

		CompetitionScheduleUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CompetitionScheduleUtil.setPersistence(null);

		entityCache.removeCache(CompetitionScheduleImpl.class.getName());
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

	private static final String _SQL_SELECT_COMPETITIONSCHEDULE =
		"SELECT competitionSchedule FROM CompetitionSchedule competitionSchedule";

	private static final String _SQL_SELECT_COMPETITIONSCHEDULE_WHERE =
		"SELECT competitionSchedule FROM CompetitionSchedule competitionSchedule WHERE ";

	private static final String _SQL_COUNT_COMPETITIONSCHEDULE =
		"SELECT COUNT(competitionSchedule) FROM CompetitionSchedule competitionSchedule";

	private static final String _SQL_COUNT_COMPETITIONSCHEDULE_WHERE =
		"SELECT COUNT(competitionSchedule) FROM CompetitionSchedule competitionSchedule WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "competitionSchedule.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CompetitionSchedule exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CompetitionSchedule exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CompetitionSchedulePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}