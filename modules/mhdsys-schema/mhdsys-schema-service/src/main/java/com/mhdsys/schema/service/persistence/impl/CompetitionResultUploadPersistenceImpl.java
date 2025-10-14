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

import com.mhdsys.schema.exception.NoSuchCompetitionResultUploadException;
import com.mhdsys.schema.model.CompetitionResultUpload;
import com.mhdsys.schema.model.CompetitionResultUploadTable;
import com.mhdsys.schema.model.impl.CompetitionResultUploadImpl;
import com.mhdsys.schema.model.impl.CompetitionResultUploadModelImpl;
import com.mhdsys.schema.service.persistence.CompetitionResultUploadPersistence;
import com.mhdsys.schema.service.persistence.CompetitionResultUploadUtil;
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
 * The persistence implementation for the competition result upload service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CompetitionResultUploadPersistence.class)
public class CompetitionResultUploadPersistenceImpl
	extends BasePersistenceImpl<CompetitionResultUpload>
	implements CompetitionResultUploadPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CompetitionResultUploadUtil</code> to access the competition result upload persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CompetitionResultUploadImpl.class.getName();

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
	 * Returns the competition result upload where userId = &#63; or throws a <code>NoSuchCompetitionResultUploadException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching competition result upload
	 * @throws NoSuchCompetitionResultUploadException if a matching competition result upload could not be found
	 */
	@Override
	public CompetitionResultUpload findByUserId(long userId)
		throws NoSuchCompetitionResultUploadException {

		CompetitionResultUpload competitionResultUpload = fetchByUserId(userId);

		if (competitionResultUpload == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompetitionResultUploadException(sb.toString());
		}

		return competitionResultUpload;
	}

	/**
	 * Returns the competition result upload where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching competition result upload, or <code>null</code> if a matching competition result upload could not be found
	 */
	@Override
	public CompetitionResultUpload fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the competition result upload where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching competition result upload, or <code>null</code> if a matching competition result upload could not be found
	 */
	@Override
	public CompetitionResultUpload fetchByUserId(
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

		if (result instanceof CompetitionResultUpload) {
			CompetitionResultUpload competitionResultUpload =
				(CompetitionResultUpload)result;

			if (userId != competitionResultUpload.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMPETITIONRESULTUPLOAD_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<CompetitionResultUpload> list = query.list();

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
								"CompetitionResultUploadPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CompetitionResultUpload competitionResultUpload = list.get(
						0);

					result = competitionResultUpload;

					cacheResult(competitionResultUpload);
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
			return (CompetitionResultUpload)result;
		}
	}

	/**
	 * Removes the competition result upload where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the competition result upload that was removed
	 */
	@Override
	public CompetitionResultUpload removeByUserId(long userId)
		throws NoSuchCompetitionResultUploadException {

		CompetitionResultUpload competitionResultUpload = findByUserId(userId);

		return remove(competitionResultUpload);
	}

	/**
	 * Returns the number of competition result uploads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching competition result uploads
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPETITIONRESULTUPLOAD_WHERE);

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
		"competitionResultUpload.userId = ?";

	private FinderPath _finderPathFetchByCompetitionScheduledId;
	private FinderPath _finderPathCountByCompetitionScheduledId;

	/**
	 * Returns the competition result upload where competitionScheduledId = &#63; or throws a <code>NoSuchCompetitionResultUploadException</code> if it could not be found.
	 *
	 * @param competitionScheduledId the competition scheduled ID
	 * @return the matching competition result upload
	 * @throws NoSuchCompetitionResultUploadException if a matching competition result upload could not be found
	 */
	@Override
	public CompetitionResultUpload findByCompetitionScheduledId(
			long competitionScheduledId)
		throws NoSuchCompetitionResultUploadException {

		CompetitionResultUpload competitionResultUpload =
			fetchByCompetitionScheduledId(competitionScheduledId);

		if (competitionResultUpload == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("competitionScheduledId=");
			sb.append(competitionScheduledId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCompetitionResultUploadException(sb.toString());
		}

		return competitionResultUpload;
	}

	/**
	 * Returns the competition result upload where competitionScheduledId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param competitionScheduledId the competition scheduled ID
	 * @return the matching competition result upload, or <code>null</code> if a matching competition result upload could not be found
	 */
	@Override
	public CompetitionResultUpload fetchByCompetitionScheduledId(
		long competitionScheduledId) {

		return fetchByCompetitionScheduledId(competitionScheduledId, true);
	}

	/**
	 * Returns the competition result upload where competitionScheduledId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param competitionScheduledId the competition scheduled ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching competition result upload, or <code>null</code> if a matching competition result upload could not be found
	 */
	@Override
	public CompetitionResultUpload fetchByCompetitionScheduledId(
		long competitionScheduledId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {competitionScheduledId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByCompetitionScheduledId, finderArgs, this);
		}

		if (result instanceof CompetitionResultUpload) {
			CompetitionResultUpload competitionResultUpload =
				(CompetitionResultUpload)result;

			if (competitionScheduledId !=
					competitionResultUpload.getCompetitionScheduledId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMPETITIONRESULTUPLOAD_WHERE);

			sb.append(
				_FINDER_COLUMN_COMPETITIONSCHEDULEDID_COMPETITIONSCHEDULEDID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(competitionScheduledId);

				List<CompetitionResultUpload> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByCompetitionScheduledId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									competitionScheduledId
								};
							}

							_log.warn(
								"CompetitionResultUploadPersistenceImpl.fetchByCompetitionScheduledId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CompetitionResultUpload competitionResultUpload = list.get(
						0);

					result = competitionResultUpload;

					cacheResult(competitionResultUpload);
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
			return (CompetitionResultUpload)result;
		}
	}

	/**
	 * Removes the competition result upload where competitionScheduledId = &#63; from the database.
	 *
	 * @param competitionScheduledId the competition scheduled ID
	 * @return the competition result upload that was removed
	 */
	@Override
	public CompetitionResultUpload removeByCompetitionScheduledId(
			long competitionScheduledId)
		throws NoSuchCompetitionResultUploadException {

		CompetitionResultUpload competitionResultUpload =
			findByCompetitionScheduledId(competitionScheduledId);

		return remove(competitionResultUpload);
	}

	/**
	 * Returns the number of competition result uploads where competitionScheduledId = &#63;.
	 *
	 * @param competitionScheduledId the competition scheduled ID
	 * @return the number of matching competition result uploads
	 */
	@Override
	public int countByCompetitionScheduledId(long competitionScheduledId) {
		FinderPath finderPath = _finderPathCountByCompetitionScheduledId;

		Object[] finderArgs = new Object[] {competitionScheduledId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMPETITIONRESULTUPLOAD_WHERE);

			sb.append(
				_FINDER_COLUMN_COMPETITIONSCHEDULEDID_COMPETITIONSCHEDULEDID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(competitionScheduledId);

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
		_FINDER_COLUMN_COMPETITIONSCHEDULEDID_COMPETITIONSCHEDULEDID_2 =
			"competitionResultUpload.competitionScheduledId = ?";

	public CompetitionResultUploadPersistenceImpl() {
		setModelClass(CompetitionResultUpload.class);

		setModelImplClass(CompetitionResultUploadImpl.class);
		setModelPKClass(long.class);

		setTable(CompetitionResultUploadTable.INSTANCE);
	}

	/**
	 * Caches the competition result upload in the entity cache if it is enabled.
	 *
	 * @param competitionResultUpload the competition result upload
	 */
	@Override
	public void cacheResult(CompetitionResultUpload competitionResultUpload) {
		entityCache.putResult(
			CompetitionResultUploadImpl.class,
			competitionResultUpload.getPrimaryKey(), competitionResultUpload);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {competitionResultUpload.getUserId()},
			competitionResultUpload);

		finderCache.putResult(
			_finderPathFetchByCompetitionScheduledId,
			new Object[] {competitionResultUpload.getCompetitionScheduledId()},
			competitionResultUpload);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the competition result uploads in the entity cache if it is enabled.
	 *
	 * @param competitionResultUploads the competition result uploads
	 */
	@Override
	public void cacheResult(
		List<CompetitionResultUpload> competitionResultUploads) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (competitionResultUploads.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CompetitionResultUpload competitionResultUpload :
				competitionResultUploads) {

			if (entityCache.getResult(
					CompetitionResultUploadImpl.class,
					competitionResultUpload.getPrimaryKey()) == null) {

				cacheResult(competitionResultUpload);
			}
		}
	}

	/**
	 * Clears the cache for all competition result uploads.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompetitionResultUploadImpl.class);

		finderCache.clearCache(CompetitionResultUploadImpl.class);
	}

	/**
	 * Clears the cache for the competition result upload.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompetitionResultUpload competitionResultUpload) {
		entityCache.removeResult(
			CompetitionResultUploadImpl.class, competitionResultUpload);
	}

	@Override
	public void clearCache(
		List<CompetitionResultUpload> competitionResultUploads) {

		for (CompetitionResultUpload competitionResultUpload :
				competitionResultUploads) {

			entityCache.removeResult(
				CompetitionResultUploadImpl.class, competitionResultUpload);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CompetitionResultUploadImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CompetitionResultUploadImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CompetitionResultUploadModelImpl competitionResultUploadModelImpl) {

		Object[] args = new Object[] {
			competitionResultUploadModelImpl.getUserId()
		};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, competitionResultUploadModelImpl);

		args = new Object[] {
			competitionResultUploadModelImpl.getCompetitionScheduledId()
		};

		finderCache.putResult(
			_finderPathCountByCompetitionScheduledId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByCompetitionScheduledId, args,
			competitionResultUploadModelImpl);
	}

	/**
	 * Creates a new competition result upload with the primary key. Does not add the competition result upload to the database.
	 *
	 * @param competitionResultUploadId the primary key for the new competition result upload
	 * @return the new competition result upload
	 */
	@Override
	public CompetitionResultUpload create(long competitionResultUploadId) {
		CompetitionResultUpload competitionResultUpload =
			new CompetitionResultUploadImpl();

		competitionResultUpload.setNew(true);
		competitionResultUpload.setPrimaryKey(competitionResultUploadId);

		return competitionResultUpload;
	}

	/**
	 * Removes the competition result upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param competitionResultUploadId the primary key of the competition result upload
	 * @return the competition result upload that was removed
	 * @throws NoSuchCompetitionResultUploadException if a competition result upload with the primary key could not be found
	 */
	@Override
	public CompetitionResultUpload remove(long competitionResultUploadId)
		throws NoSuchCompetitionResultUploadException {

		return remove((Serializable)competitionResultUploadId);
	}

	/**
	 * Removes the competition result upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the competition result upload
	 * @return the competition result upload that was removed
	 * @throws NoSuchCompetitionResultUploadException if a competition result upload with the primary key could not be found
	 */
	@Override
	public CompetitionResultUpload remove(Serializable primaryKey)
		throws NoSuchCompetitionResultUploadException {

		Session session = null;

		try {
			session = openSession();

			CompetitionResultUpload competitionResultUpload =
				(CompetitionResultUpload)session.get(
					CompetitionResultUploadImpl.class, primaryKey);

			if (competitionResultUpload == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompetitionResultUploadException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(competitionResultUpload);
		}
		catch (NoSuchCompetitionResultUploadException noSuchEntityException) {
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
	protected CompetitionResultUpload removeImpl(
		CompetitionResultUpload competitionResultUpload) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(competitionResultUpload)) {
				competitionResultUpload = (CompetitionResultUpload)session.get(
					CompetitionResultUploadImpl.class,
					competitionResultUpload.getPrimaryKeyObj());
			}

			if (competitionResultUpload != null) {
				session.delete(competitionResultUpload);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (competitionResultUpload != null) {
			clearCache(competitionResultUpload);
		}

		return competitionResultUpload;
	}

	@Override
	public CompetitionResultUpload updateImpl(
		CompetitionResultUpload competitionResultUpload) {

		boolean isNew = competitionResultUpload.isNew();

		if (!(competitionResultUpload instanceof
				CompetitionResultUploadModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(competitionResultUpload.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					competitionResultUpload);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in competitionResultUpload proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CompetitionResultUpload implementation " +
					competitionResultUpload.getClass());
		}

		CompetitionResultUploadModelImpl competitionResultUploadModelImpl =
			(CompetitionResultUploadModelImpl)competitionResultUpload;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (competitionResultUpload.getCreateDate() == null)) {
			if (serviceContext == null) {
				competitionResultUpload.setCreateDate(date);
			}
			else {
				competitionResultUpload.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!competitionResultUploadModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				competitionResultUpload.setModifiedDate(date);
			}
			else {
				competitionResultUpload.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(competitionResultUpload);
			}
			else {
				competitionResultUpload =
					(CompetitionResultUpload)session.merge(
						competitionResultUpload);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CompetitionResultUploadImpl.class, competitionResultUploadModelImpl,
			false, true);

		cacheUniqueFindersCache(competitionResultUploadModelImpl);

		if (isNew) {
			competitionResultUpload.setNew(false);
		}

		competitionResultUpload.resetOriginalValues();

		return competitionResultUpload;
	}

	/**
	 * Returns the competition result upload with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the competition result upload
	 * @return the competition result upload
	 * @throws NoSuchCompetitionResultUploadException if a competition result upload with the primary key could not be found
	 */
	@Override
	public CompetitionResultUpload findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompetitionResultUploadException {

		CompetitionResultUpload competitionResultUpload = fetchByPrimaryKey(
			primaryKey);

		if (competitionResultUpload == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompetitionResultUploadException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return competitionResultUpload;
	}

	/**
	 * Returns the competition result upload with the primary key or throws a <code>NoSuchCompetitionResultUploadException</code> if it could not be found.
	 *
	 * @param competitionResultUploadId the primary key of the competition result upload
	 * @return the competition result upload
	 * @throws NoSuchCompetitionResultUploadException if a competition result upload with the primary key could not be found
	 */
	@Override
	public CompetitionResultUpload findByPrimaryKey(
			long competitionResultUploadId)
		throws NoSuchCompetitionResultUploadException {

		return findByPrimaryKey((Serializable)competitionResultUploadId);
	}

	/**
	 * Returns the competition result upload with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param competitionResultUploadId the primary key of the competition result upload
	 * @return the competition result upload, or <code>null</code> if a competition result upload with the primary key could not be found
	 */
	@Override
	public CompetitionResultUpload fetchByPrimaryKey(
		long competitionResultUploadId) {

		return fetchByPrimaryKey((Serializable)competitionResultUploadId);
	}

	/**
	 * Returns all the competition result uploads.
	 *
	 * @return the competition result uploads
	 */
	@Override
	public List<CompetitionResultUpload> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the competition result uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionResultUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition result uploads
	 * @param end the upper bound of the range of competition result uploads (not inclusive)
	 * @return the range of competition result uploads
	 */
	@Override
	public List<CompetitionResultUpload> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the competition result uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionResultUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition result uploads
	 * @param end the upper bound of the range of competition result uploads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of competition result uploads
	 */
	@Override
	public List<CompetitionResultUpload> findAll(
		int start, int end,
		OrderByComparator<CompetitionResultUpload> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the competition result uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CompetitionResultUploadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of competition result uploads
	 * @param end the upper bound of the range of competition result uploads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of competition result uploads
	 */
	@Override
	public List<CompetitionResultUpload> findAll(
		int start, int end,
		OrderByComparator<CompetitionResultUpload> orderByComparator,
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

		List<CompetitionResultUpload> list = null;

		if (useFinderCache) {
			list = (List<CompetitionResultUpload>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMPETITIONRESULTUPLOAD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMPETITIONRESULTUPLOAD;

				sql = sql.concat(
					CompetitionResultUploadModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CompetitionResultUpload>)QueryUtil.list(
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
	 * Removes all the competition result uploads from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompetitionResultUpload competitionResultUpload : findAll()) {
			remove(competitionResultUpload);
		}
	}

	/**
	 * Returns the number of competition result uploads.
	 *
	 * @return the number of competition result uploads
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
					_SQL_COUNT_COMPETITIONRESULTUPLOAD);

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
		return "competitionResultUploadId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMPETITIONRESULTUPLOAD;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CompetitionResultUploadModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the competition result upload persistence.
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

		_finderPathFetchByCompetitionScheduledId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByCompetitionScheduledId",
			new String[] {Long.class.getName()},
			new String[] {"competitionScheduledId"}, true);

		_finderPathCountByCompetitionScheduledId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompetitionScheduledId",
			new String[] {Long.class.getName()},
			new String[] {"competitionScheduledId"}, false);

		CompetitionResultUploadUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CompetitionResultUploadUtil.setPersistence(null);

		entityCache.removeCache(CompetitionResultUploadImpl.class.getName());
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

	private static final String _SQL_SELECT_COMPETITIONRESULTUPLOAD =
		"SELECT competitionResultUpload FROM CompetitionResultUpload competitionResultUpload";

	private static final String _SQL_SELECT_COMPETITIONRESULTUPLOAD_WHERE =
		"SELECT competitionResultUpload FROM CompetitionResultUpload competitionResultUpload WHERE ";

	private static final String _SQL_COUNT_COMPETITIONRESULTUPLOAD =
		"SELECT COUNT(competitionResultUpload) FROM CompetitionResultUpload competitionResultUpload";

	private static final String _SQL_COUNT_COMPETITIONRESULTUPLOAD_WHERE =
		"SELECT COUNT(competitionResultUpload) FROM CompetitionResultUpload competitionResultUpload WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"competitionResultUpload.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CompetitionResultUpload exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CompetitionResultUpload exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CompetitionResultUploadPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}