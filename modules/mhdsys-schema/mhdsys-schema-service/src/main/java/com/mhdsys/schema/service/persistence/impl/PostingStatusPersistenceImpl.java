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

import com.mhdsys.schema.exception.NoSuchPostingStatusException;
import com.mhdsys.schema.model.PostingStatus;
import com.mhdsys.schema.model.PostingStatusTable;
import com.mhdsys.schema.model.impl.PostingStatusImpl;
import com.mhdsys.schema.model.impl.PostingStatusModelImpl;
import com.mhdsys.schema.service.persistence.PostingStatusPersistence;
import com.mhdsys.schema.service.persistence.PostingStatusUtil;
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
 * The persistence implementation for the posting status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = PostingStatusPersistence.class)
public class PostingStatusPersistenceImpl
	extends BasePersistenceImpl<PostingStatus>
	implements PostingStatusPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PostingStatusUtil</code> to access the posting status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PostingStatusImpl.class.getName();

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
	 * Returns the posting status where userId = &#63; or throws a <code>NoSuchPostingStatusException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching posting status
	 * @throws NoSuchPostingStatusException if a matching posting status could not be found
	 */
	@Override
	public PostingStatus findByUserId(long userId)
		throws NoSuchPostingStatusException {

		PostingStatus postingStatus = fetchByUserId(userId);

		if (postingStatus == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPostingStatusException(sb.toString());
		}

		return postingStatus;
	}

	/**
	 * Returns the posting status where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching posting status, or <code>null</code> if a matching posting status could not be found
	 */
	@Override
	public PostingStatus fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the posting status where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching posting status, or <code>null</code> if a matching posting status could not be found
	 */
	@Override
	public PostingStatus fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof PostingStatus) {
			PostingStatus postingStatus = (PostingStatus)result;

			if (userId != postingStatus.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_POSTINGSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<PostingStatus> list = query.list();

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
								"PostingStatusPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PostingStatus postingStatus = list.get(0);

					result = postingStatus;

					cacheResult(postingStatus);
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
			return (PostingStatus)result;
		}
	}

	/**
	 * Removes the posting status where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the posting status that was removed
	 */
	@Override
	public PostingStatus removeByUserId(long userId)
		throws NoSuchPostingStatusException {

		PostingStatus postingStatus = findByUserId(userId);

		return remove(postingStatus);
	}

	/**
	 * Returns the number of posting statuses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching posting statuses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_POSTINGSTATUS_WHERE);

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
		"postingStatus.userId = ?";

	private FinderPath _finderPathFetchByEstablishmentDetailId;
	private FinderPath _finderPathCountByEstablishmentDetailId;

	/**
	 * Returns the posting status where establishmentDetailId = &#63; or throws a <code>NoSuchPostingStatusException</code> if it could not be found.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching posting status
	 * @throws NoSuchPostingStatusException if a matching posting status could not be found
	 */
	@Override
	public PostingStatus findByEstablishmentDetailId(long establishmentDetailId)
		throws NoSuchPostingStatusException {

		PostingStatus postingStatus = fetchByEstablishmentDetailId(
			establishmentDetailId);

		if (postingStatus == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("establishmentDetailId=");
			sb.append(establishmentDetailId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPostingStatusException(sb.toString());
		}

		return postingStatus;
	}

	/**
	 * Returns the posting status where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the matching posting status, or <code>null</code> if a matching posting status could not be found
	 */
	@Override
	public PostingStatus fetchByEstablishmentDetailId(
		long establishmentDetailId) {

		return fetchByEstablishmentDetailId(establishmentDetailId, true);
	}

	/**
	 * Returns the posting status where establishmentDetailId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching posting status, or <code>null</code> if a matching posting status could not be found
	 */
	@Override
	public PostingStatus fetchByEstablishmentDetailId(
		long establishmentDetailId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {establishmentDetailId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByEstablishmentDetailId, finderArgs, this);
		}

		if (result instanceof PostingStatus) {
			PostingStatus postingStatus = (PostingStatus)result;

			if (establishmentDetailId !=
					postingStatus.getEstablishmentDetailId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_POSTINGSTATUS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

				List<PostingStatus> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByEstablishmentDetailId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									establishmentDetailId
								};
							}

							_log.warn(
								"PostingStatusPersistenceImpl.fetchByEstablishmentDetailId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PostingStatus postingStatus = list.get(0);

					result = postingStatus;

					cacheResult(postingStatus);
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
			return (PostingStatus)result;
		}
	}

	/**
	 * Removes the posting status where establishmentDetailId = &#63; from the database.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the posting status that was removed
	 */
	@Override
	public PostingStatus removeByEstablishmentDetailId(
			long establishmentDetailId)
		throws NoSuchPostingStatusException {

		PostingStatus postingStatus = findByEstablishmentDetailId(
			establishmentDetailId);

		return remove(postingStatus);
	}

	/**
	 * Returns the number of posting statuses where establishmentDetailId = &#63;.
	 *
	 * @param establishmentDetailId the establishment detail ID
	 * @return the number of matching posting statuses
	 */
	@Override
	public int countByEstablishmentDetailId(long establishmentDetailId) {
		FinderPath finderPath = _finderPathCountByEstablishmentDetailId;

		Object[] finderArgs = new Object[] {establishmentDetailId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_POSTINGSTATUS_WHERE);

			sb.append(
				_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(establishmentDetailId);

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
		_FINDER_COLUMN_ESTABLISHMENTDETAILID_ESTABLISHMENTDETAILID_2 =
			"postingStatus.establishmentDetailId = ?";

	private FinderPath _finderPathFetchBypostingStatusId;
	private FinderPath _finderPathCountBypostingStatusId;

	/**
	 * Returns the posting status where postingStatusId = &#63; or throws a <code>NoSuchPostingStatusException</code> if it could not be found.
	 *
	 * @param postingStatusId the posting status ID
	 * @return the matching posting status
	 * @throws NoSuchPostingStatusException if a matching posting status could not be found
	 */
	@Override
	public PostingStatus findBypostingStatusId(long postingStatusId)
		throws NoSuchPostingStatusException {

		PostingStatus postingStatus = fetchBypostingStatusId(postingStatusId);

		if (postingStatus == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("postingStatusId=");
			sb.append(postingStatusId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchPostingStatusException(sb.toString());
		}

		return postingStatus;
	}

	/**
	 * Returns the posting status where postingStatusId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param postingStatusId the posting status ID
	 * @return the matching posting status, or <code>null</code> if a matching posting status could not be found
	 */
	@Override
	public PostingStatus fetchBypostingStatusId(long postingStatusId) {
		return fetchBypostingStatusId(postingStatusId, true);
	}

	/**
	 * Returns the posting status where postingStatusId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param postingStatusId the posting status ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching posting status, or <code>null</code> if a matching posting status could not be found
	 */
	@Override
	public PostingStatus fetchBypostingStatusId(
		long postingStatusId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {postingStatusId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBypostingStatusId, finderArgs, this);
		}

		if (result instanceof PostingStatus) {
			PostingStatus postingStatus = (PostingStatus)result;

			if (postingStatusId != postingStatus.getPostingStatusId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_POSTINGSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_POSTINGSTATUSID_POSTINGSTATUSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(postingStatusId);

				List<PostingStatus> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBypostingStatusId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {postingStatusId};
							}

							_log.warn(
								"PostingStatusPersistenceImpl.fetchBypostingStatusId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PostingStatus postingStatus = list.get(0);

					result = postingStatus;

					cacheResult(postingStatus);
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
			return (PostingStatus)result;
		}
	}

	/**
	 * Removes the posting status where postingStatusId = &#63; from the database.
	 *
	 * @param postingStatusId the posting status ID
	 * @return the posting status that was removed
	 */
	@Override
	public PostingStatus removeBypostingStatusId(long postingStatusId)
		throws NoSuchPostingStatusException {

		PostingStatus postingStatus = findBypostingStatusId(postingStatusId);

		return remove(postingStatus);
	}

	/**
	 * Returns the number of posting statuses where postingStatusId = &#63;.
	 *
	 * @param postingStatusId the posting status ID
	 * @return the number of matching posting statuses
	 */
	@Override
	public int countBypostingStatusId(long postingStatusId) {
		FinderPath finderPath = _finderPathCountBypostingStatusId;

		Object[] finderArgs = new Object[] {postingStatusId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_POSTINGSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_POSTINGSTATUSID_POSTINGSTATUSID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(postingStatusId);

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
		_FINDER_COLUMN_POSTINGSTATUSID_POSTINGSTATUSID_2 =
			"postingStatus.postingStatusId = ?";

	public PostingStatusPersistenceImpl() {
		setModelClass(PostingStatus.class);

		setModelImplClass(PostingStatusImpl.class);
		setModelPKClass(long.class);

		setTable(PostingStatusTable.INSTANCE);
	}

	/**
	 * Caches the posting status in the entity cache if it is enabled.
	 *
	 * @param postingStatus the posting status
	 */
	@Override
	public void cacheResult(PostingStatus postingStatus) {
		entityCache.putResult(
			PostingStatusImpl.class, postingStatus.getPrimaryKey(),
			postingStatus);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {postingStatus.getUserId()},
			postingStatus);

		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId,
			new Object[] {postingStatus.getEstablishmentDetailId()},
			postingStatus);

		finderCache.putResult(
			_finderPathFetchBypostingStatusId,
			new Object[] {postingStatus.getPostingStatusId()}, postingStatus);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the posting statuses in the entity cache if it is enabled.
	 *
	 * @param postingStatuses the posting statuses
	 */
	@Override
	public void cacheResult(List<PostingStatus> postingStatuses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (postingStatuses.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (PostingStatus postingStatus : postingStatuses) {
			if (entityCache.getResult(
					PostingStatusImpl.class, postingStatus.getPrimaryKey()) ==
						null) {

				cacheResult(postingStatus);
			}
		}
	}

	/**
	 * Clears the cache for all posting statuses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PostingStatusImpl.class);

		finderCache.clearCache(PostingStatusImpl.class);
	}

	/**
	 * Clears the cache for the posting status.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PostingStatus postingStatus) {
		entityCache.removeResult(PostingStatusImpl.class, postingStatus);
	}

	@Override
	public void clearCache(List<PostingStatus> postingStatuses) {
		for (PostingStatus postingStatus : postingStatuses) {
			entityCache.removeResult(PostingStatusImpl.class, postingStatus);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(PostingStatusImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(PostingStatusImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		PostingStatusModelImpl postingStatusModelImpl) {

		Object[] args = new Object[] {postingStatusModelImpl.getUserId()};

		finderCache.putResult(_finderPathCountByUserId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByUserId, args, postingStatusModelImpl);

		args = new Object[] {postingStatusModelImpl.getEstablishmentDetailId()};

		finderCache.putResult(
			_finderPathCountByEstablishmentDetailId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByEstablishmentDetailId, args,
			postingStatusModelImpl);

		args = new Object[] {postingStatusModelImpl.getPostingStatusId()};

		finderCache.putResult(
			_finderPathCountBypostingStatusId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchBypostingStatusId, args, postingStatusModelImpl);
	}

	/**
	 * Creates a new posting status with the primary key. Does not add the posting status to the database.
	 *
	 * @param postingStatusId the primary key for the new posting status
	 * @return the new posting status
	 */
	@Override
	public PostingStatus create(long postingStatusId) {
		PostingStatus postingStatus = new PostingStatusImpl();

		postingStatus.setNew(true);
		postingStatus.setPrimaryKey(postingStatusId);

		return postingStatus;
	}

	/**
	 * Removes the posting status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param postingStatusId the primary key of the posting status
	 * @return the posting status that was removed
	 * @throws NoSuchPostingStatusException if a posting status with the primary key could not be found
	 */
	@Override
	public PostingStatus remove(long postingStatusId)
		throws NoSuchPostingStatusException {

		return remove((Serializable)postingStatusId);
	}

	/**
	 * Removes the posting status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the posting status
	 * @return the posting status that was removed
	 * @throws NoSuchPostingStatusException if a posting status with the primary key could not be found
	 */
	@Override
	public PostingStatus remove(Serializable primaryKey)
		throws NoSuchPostingStatusException {

		Session session = null;

		try {
			session = openSession();

			PostingStatus postingStatus = (PostingStatus)session.get(
				PostingStatusImpl.class, primaryKey);

			if (postingStatus == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPostingStatusException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(postingStatus);
		}
		catch (NoSuchPostingStatusException noSuchEntityException) {
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
	protected PostingStatus removeImpl(PostingStatus postingStatus) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(postingStatus)) {
				postingStatus = (PostingStatus)session.get(
					PostingStatusImpl.class, postingStatus.getPrimaryKeyObj());
			}

			if (postingStatus != null) {
				session.delete(postingStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (postingStatus != null) {
			clearCache(postingStatus);
		}

		return postingStatus;
	}

	@Override
	public PostingStatus updateImpl(PostingStatus postingStatus) {
		boolean isNew = postingStatus.isNew();

		if (!(postingStatus instanceof PostingStatusModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(postingStatus.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					postingStatus);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in postingStatus proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PostingStatus implementation " +
					postingStatus.getClass());
		}

		PostingStatusModelImpl postingStatusModelImpl =
			(PostingStatusModelImpl)postingStatus;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (postingStatus.getCreateDate() == null)) {
			if (serviceContext == null) {
				postingStatus.setCreateDate(date);
			}
			else {
				postingStatus.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!postingStatusModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				postingStatus.setModifiedDate(date);
			}
			else {
				postingStatus.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(postingStatus);
			}
			else {
				postingStatus = (PostingStatus)session.merge(postingStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			PostingStatusImpl.class, postingStatusModelImpl, false, true);

		cacheUniqueFindersCache(postingStatusModelImpl);

		if (isNew) {
			postingStatus.setNew(false);
		}

		postingStatus.resetOriginalValues();

		return postingStatus;
	}

	/**
	 * Returns the posting status with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the posting status
	 * @return the posting status
	 * @throws NoSuchPostingStatusException if a posting status with the primary key could not be found
	 */
	@Override
	public PostingStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPostingStatusException {

		PostingStatus postingStatus = fetchByPrimaryKey(primaryKey);

		if (postingStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPostingStatusException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return postingStatus;
	}

	/**
	 * Returns the posting status with the primary key or throws a <code>NoSuchPostingStatusException</code> if it could not be found.
	 *
	 * @param postingStatusId the primary key of the posting status
	 * @return the posting status
	 * @throws NoSuchPostingStatusException if a posting status with the primary key could not be found
	 */
	@Override
	public PostingStatus findByPrimaryKey(long postingStatusId)
		throws NoSuchPostingStatusException {

		return findByPrimaryKey((Serializable)postingStatusId);
	}

	/**
	 * Returns the posting status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param postingStatusId the primary key of the posting status
	 * @return the posting status, or <code>null</code> if a posting status with the primary key could not be found
	 */
	@Override
	public PostingStatus fetchByPrimaryKey(long postingStatusId) {
		return fetchByPrimaryKey((Serializable)postingStatusId);
	}

	/**
	 * Returns all the posting statuses.
	 *
	 * @return the posting statuses
	 */
	@Override
	public List<PostingStatus> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the posting statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PostingStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of posting statuses
	 * @param end the upper bound of the range of posting statuses (not inclusive)
	 * @return the range of posting statuses
	 */
	@Override
	public List<PostingStatus> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the posting statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PostingStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of posting statuses
	 * @param end the upper bound of the range of posting statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of posting statuses
	 */
	@Override
	public List<PostingStatus> findAll(
		int start, int end,
		OrderByComparator<PostingStatus> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the posting statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PostingStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of posting statuses
	 * @param end the upper bound of the range of posting statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of posting statuses
	 */
	@Override
	public List<PostingStatus> findAll(
		int start, int end, OrderByComparator<PostingStatus> orderByComparator,
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

		List<PostingStatus> list = null;

		if (useFinderCache) {
			list = (List<PostingStatus>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_POSTINGSTATUS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_POSTINGSTATUS;

				sql = sql.concat(PostingStatusModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<PostingStatus>)QueryUtil.list(
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
	 * Removes all the posting statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PostingStatus postingStatus : findAll()) {
			remove(postingStatus);
		}
	}

	/**
	 * Returns the number of posting statuses.
	 *
	 * @return the number of posting statuses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_POSTINGSTATUS);

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
		return "postingStatusId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_POSTINGSTATUS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PostingStatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the posting status persistence.
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

		_finderPathFetchByEstablishmentDetailId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByEstablishmentDetailId",
			new String[] {Long.class.getName()},
			new String[] {"establishmentDetailId"}, true);

		_finderPathCountByEstablishmentDetailId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEstablishmentDetailId", new String[] {Long.class.getName()},
			new String[] {"establishmentDetailId"}, false);

		_finderPathFetchBypostingStatusId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBypostingStatusId",
			new String[] {Long.class.getName()},
			new String[] {"postingStatusId"}, true);

		_finderPathCountBypostingStatusId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBypostingStatusId",
			new String[] {Long.class.getName()},
			new String[] {"postingStatusId"}, false);

		PostingStatusUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		PostingStatusUtil.setPersistence(null);

		entityCache.removeCache(PostingStatusImpl.class.getName());
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

	private static final String _SQL_SELECT_POSTINGSTATUS =
		"SELECT postingStatus FROM PostingStatus postingStatus";

	private static final String _SQL_SELECT_POSTINGSTATUS_WHERE =
		"SELECT postingStatus FROM PostingStatus postingStatus WHERE ";

	private static final String _SQL_COUNT_POSTINGSTATUS =
		"SELECT COUNT(postingStatus) FROM PostingStatus postingStatus";

	private static final String _SQL_COUNT_POSTINGSTATUS_WHERE =
		"SELECT COUNT(postingStatus) FROM PostingStatus postingStatus WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "postingStatus.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PostingStatus exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No PostingStatus exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PostingStatusPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}